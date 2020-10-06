/*
 * main.c
 *
 *  Created on: May 25, 2020
 *      Author:Anis
 */



#include "system.h"
#include "alt_types.h"
#include "sys_gpio.h"
#include "sys_uart.h"
#include <stdbool.h>




int *mes , i ;
int light_val = 100 ;
bool endTransmision = true ;
// initializing commands variables
 alt_u16 f_cmd = 0x0000  ;                          // Fan's commands
 alt_u16 l_cmd = 0x0000  ;							// Light's commands
 alt_u16 p_cmd = 0x0000  ;							// Pump's commands
 alt_u16 r_cmd=  0x00f0  ;							// Roof's commands
 alt_u8  c_cmd = 0x00  ;							// Curtains' commands

unsigned int measurements[7];                       // integer array to hold measurements
char blt_cmd_st[20] = "0123456789ABCDEF\n";         // a String to hold incoming data
char bt_mes_st[]=""; 								// a String to hold out data
char data [] =  "\n"
		        "Tem in  is : 000 \n"
		        "Hum in  is : 000 \n"
		        "Tem out is : 000 \n"
		        "Hum out is : 000 \n"
		        "soilMo  is : 000 \n"
		        "rain    is : 000 \n"
		 	 	"Bright  is : 000 \n";


typedef struct actuators {                          // structure to hold control signals of each actuators
	char isEnabled ;
	int  setPoint  ;
	int  value     ;
	char state     ;

}actuators_cmd;

actuators_cmd  fan      = {'D',25,25,'-'};
actuators_cmd  lights   = {'D',25,25,'-'};
actuators_cmd  pump     = {'D',25,25,'L'};
actuators_cmd  roof     = {'D',25,25,'O'};
actuators_cmd  curtains = {'D',25,25,'O'};


/*
 * Fetch data from PIO and assign it to its corresponding
 * measurement.
 */
void getMeasurements(){

    alt_u32 data1 = pio_read(TEM_AND_HUMIDITY_DATA_BASE);
    alt_u32 data2 = pio_read(SOIL_RAIN_SOLAR_DATA_BASE);

	measurements[0] = (unsigned int) (data1& 0x000000ff) ;      // Tem_out data
    measurements[1] = (unsigned int) ((data1& 0x0000ff00)>>8) ; // Hum_out data
	measurements[2] = (unsigned int) ((data1& 0xff000000)>>24) ;// Tem_in data
	measurements[3] = (unsigned int) ((data1& 0x00ff0000)>>16) ;// Hum_in data
    measurements[4] = (unsigned int) (data2&  0x000000ff) ;     // soil_moisture data
	measurements[5] = (unsigned int) ((data2& 0x0000ff00)>>8) ; // rain data
	measurements[6] = (unsigned int) ((data2& 0x00ff0000)>>16) ;// brightness data


	measurements[3] = ( measurements[3]-5)/2.5;     		    // mapping measurement to (0-100)
	measurements[0] = measurements[2];
	measurements[1] = measurements[3];
	measurements[4] = (255-measurements[4]-5)/2.5;                  // mapping measurement to (0-100)
	measurements[5] = (255-measurements[5]-5)/100;                  // mapping measurement to (0-100)
	measurements[6] = (255-measurements[6]-5)/2.5;                  // mapping measurement to (0-100)

}


/*
 * Displays the data collected from sensors and display
 * it in the console.*
*/
void display_data_in_console(){
	*(data + 15 )= (char)(measurements[2] / 10 + 48);
	*(data + 16 )= (char)(measurements[2]  % 10 + 48);
	*(data + 33 )= (char)(measurements[3]  / 10 + 48);
	*(data + 34 )= (char)(measurements[3]  % 10 + 48);

	*(data + 51 )= (char)(measurements[2]  / 10 + 48);
	*(data + 52 )= (char)(measurements[2]  % 10 + 48);

	*(data + 69 )= (char)(measurements[3]  / 10 + 48);
	*(data + 70 )= (char)(measurements[3]  % 10 + 48);

	*(data + 86 )= (char)(measurements[4] / 100 + 48);
	*(data + 87 )= (char)((measurements[4]%100) / 10 + 48);
	*(data + 88 )= (char)((measurements[4]) % 10 + 48);

	*(data +104 )= (char)(measurements[5] / 100   + 48);
	*(data +105 )= (char)((measurements[5] % 100 ) / 10 + 48);
	*(data +106 )= (char)( measurements[5] % 10    + 48);

	*(data +122 )= (char)(((measurements[6]-55)/4) / 100 + 48);
	*(data +123 )= (char)((((measurements[6]-55)/4) % 100 ) / 10 + 48);
	*(data +124 )= (char)(((measurements[6]-55)/4) % 10 + 48);

	 jtaguart_wt_str(JTAG_UART_BASE,data);          //sending data to JTAG UART
}


/*
 * This function  sends the data to BLUETOOTH.
*/
void blt_send_data(){
	 make_data_as_bytes();
	 btl_wt_str(BLUETOOTH_UART_BASE,bt_mes_st);   // sending data to BLUETOOTH UART
 }


/*
 * Converts the data to bytes (characters).
*/
void make_data_as_bytes(){

	*(bt_mes_st)   = (char)( 2);
	*(bt_mes_st+1) = (char)(measurements[2]);
    *(bt_mes_st+2) = (char)(measurements[3]);

	 for(i=2; i<7;i++){
		 if(measurements[i]== 0){
			 *(bt_mes_st+i+1) = 0x2D;
		 } else{
			 *(bt_mes_st+i+1) = (char)(measurements[i]);
		 }
	 }
 }


/*
 * Receives the commands packet and stores in string
*/
void blt_get_commands(){
	int idx=0 ;
	     if(blt_rd_wspace(BLUETOOTH_UART_BASE)==0x01){
			   char intChar = (char)blt_rd_ch(BLUETOOTH_UART_BASE);
			   while ('X'== intChar) {
				   if(blt_rd_wspace(BLUETOOTH_UART_BASE)==0x01){
					   char intCha = (char)blt_rd_ch(BLUETOOTH_UART_BASE);
					   blt_cmd_st[idx] = intCha ;

					   idx++ ;

					   if(idx >=16) {
						   break;
					   }
				   }
			   }
	     }
}


/*
 * Save commands received from BLUETOOTH in structures
*/
void make_cmd(char *blt_cmd ,char *m){

 jtaguart_wt_str(JTAG_UART_BASE,blt_cmd);    // getting commands from Bluetooth

 // setting mode command
 *(m)         = *(blt_cmd +1);

 // setting fan commands
 fan.isEnabled = *(blt_cmd +2);
 fan.setPoint  = *(blt_cmd +3) - 25 ;       // 25 is an offset to avoid special chars
 fan.value     = (*(blt_cmd +4)-25)*5 +1 ;

 // setting lights commands
 lights.isEnabled = *(blt_cmd +5);
 lights.setPoint  = *(blt_cmd +6) - 25 ;
 lights.value     = (*(blt_cmd +7)-25)*2.5+1 ;

 // setting pump commands
 pump.isEnabled = *(blt_cmd +8);
 pump.setPoint  = *(blt_cmd +10) - 25 ;
 pump.state     = *(blt_cmd +9)  ;

 // setting roof commands
 roof.isEnabled = *(blt_cmd +11);
 roof.state     = *(blt_cmd +12);

 // setting curtains commands
 curtains.isEnabled = *(blt_cmd +13);
 curtains.state     = *(blt_cmd +14);

 }


/*
 * Set control commands logic of each Actuator and send
 * them to their corresponding PIO
*/
void set_control_modes(char *m){

	 if(*m=='M'){    									// if control mode in manual

          set_manual_mode();

	 }else if(*m == 'A'){  								// if control mode is automatic

		 set_automatic_mode();
	 }
	 // Fan
	 	 if(fan.isEnabled == 'E')
	 	 	f_cmd |=0x0100;      						// Setting bit 9 to enables Fan
	 	  else if(fan.isEnabled == 'D')
	 		f_cmd &= ~(0x0100);  						// Clearing bit 9 to disable Fan
	 	 // Lights
	 	 if(lights.isEnabled == 'E')
	 	 	l_cmd |=0x0100;      						// Enable Lights
	 	 else if(lights.isEnabled == 'D')
	 	 	l_cmd &= ~(0x0100);  						// Disable Lights
	 	 // Pump
	 	 if(pump.isEnabled == 'E')
	 	 	p_cmd |=0x0100;     						// Enable Pump
	 	 else if(pump.isEnabled == 'D')
	 	 	p_cmd &= ~(0x0100);  						// Disable Pump
	 	 // Roof
	 	 if(roof.isEnabled == 'E')
	 	 	r_cmd |=0x02;      							// Enable Roof
	 	 else if(roof.isEnabled == 'D')
	 	 	r_cmd &= ~(0x02);  							// Disable Roof

	 	 // Curtains
	 	 if(curtains.isEnabled == 'E')
	 	 	c_cmd |=0x04;      					   		// Enable Curtains
	 	 else if(curtains.isEnabled == 'D')
	 	 	c_cmd &= ~(0x04);  					   		// Disable Curtains


	 pio_write(FAN_BASE,     ( f_cmd & 0x01ff ));		// Sending commands to Fan PIO
	 pio_write(LIGHTS_BASE,  ( l_cmd & 0x01ff )); 		// Sending commands to Lights PIO
	 pio_write(IRR_PUMP_BASE,( p_cmd & 0x01ff ));		// Sending commands to Pump PIO
	 pio_write(ROOF_BASE,    ( r_cmd & 0x02ff ));		// Sending commands to Roof PIO
	 pio_write(CURTAINS_BASE,( c_cmd & 0x07   ));  		// Sending commands to Curtains PIO

 }


/*
 * Setting the manual mode control logic
*/
void set_manual_mode(){

	 // Setting commands to Fan
	 f_cmd = ((fan.value) & 0x01ff) ;     		   		// Setting Fan speed

	 // Setting commands to Lights
	 l_cmd = ((lights.value) & 0x01ff) ;         		// Setting Lights Intensity

	 // Setting commands to Pump
	 if(pump.state == 'L')
		 p_cmd = (50 & 0xff) ;     	           			// Setting pump speed to low
	 else if(pump.state == 'M')
		 p_cmd = (100 & 0xff) ;					    	// Setting pump speed to medium
	 else if(pump.state == 'I')
		 p_cmd = (255 & 0xff) ;							// Setting pump speed to High

	 // Setting commands to Roof
	 if(roof.state == 'C'){
		 r_cmd |=0x0001;      					   	    // Close Roof
		 *(data +124 )= (char)'C';
		 r_cmd |= 0x0004 ;
		 r_cmd &= ~(0x08);

	 }else if(roof.state == 'O'){
		 r_cmd &= ~(0x01);  					   	    // Open Roof
		 *(data +124 )= (char)'O';
		 r_cmd |= 0x0008 ;
		 r_cmd &= ~(0x04);
	 }
	 // Setting commands to Curtains
	 if(curtains.state == 'O')
		 c_cmd |=0x01;      					     	// Open Curtains
	 else if(curtains.state == 'H')
		 c_cmd |=0x02;  					        	// Half-open Curtains
	 else if(curtains.state == 'C')
		 c_cmd |=0x03;  					            // Close Curtains

 }


/*
 * Setting the Automatic mode control logic
*/
void set_automatic_mode(){

	// soilMoisture control loop
	 if(measurements[4]>= pump.setPoint){
		 r_cmd |=0x0001;  								// Close Roof
	 	r_cmd |= 0x0004 ;
		r_cmd &= ~(0x08);

	 	p_cmd = (1 & 0xff) ;  					        // Disable Pump
	 }else{
	 	if(measurements[5]<1){
	 		 r_cmd |=0x0001;  								// Close Roof
	 			 	r_cmd |= 0x0004 ;
	 				r_cmd &= ~(0x08);
	 		p_cmd = (1 & 0xff) ;  					    // Disable Pump
	 		}else{
	 			r_cmd &= ~(0x01);      				// Open Roof
	 			r_cmd |= 0x0008 ;
	 			r_cmd &= ~(0x04);
	 			if(1 /* a condition yet to be set*/){
	 				p_cmd = (150 & 0xff) ;     			// Enable Pump
	 				}else{


	 					p_cmd = (1 & 0xff) ;  		    // Disable Pump
	 				 }
	 		}
	 	 }
	 // Temperature control loop
	 if(measurements[2]<= fan.setPoint){
	 	f_cmd = (1 & 0x00ff) ;               			// set Fan off
	 }else{
	 	if(measurements[2]<= measurements[0]){
	 		f_cmd |= (230 & 0x00ff) ;          			// set Fan on
	 	}
	 	else{
	 		f_cmd |= (1 & 0x00ff) ;            			// set Fan off
	 	}
	 }
	 // Lights control loop


	 if(light_val < 10 )  light_val = 10;

	 if(((int)measurements[6]) > lights.setPoint ){
	 	light_val -= 5 ;
	 	l_cmd = (light_val & 0x00ff) ;					// Setting Lights Intensity
	 	if(light_val < 0) light_val = 1;
	 }else if((((int)measurements[6])) < lights.setPoint ){
		light_val += 5 ;
	 	l_cmd = (light_val & 0x00ff) ;					// Setting Lights Intensity

	 	if(light_val > 245) light_val = 250;
	 }
 }



int main(){
 int i , counter=0 , setTime = 5 ;
 char mode = 'A' ;

 while (1){

	 getMeasurements();                       	// getting measurements from sensors

	 blt_get_commands(); 					 	// receive commands from phone via bluetooth
	 make_cmd(blt_cmd_st ,&mode);             	// decode the commands

	 set_control_modes(&mode);                  // set the control logic of each mode

     // sending data every 20 s
	 if(counter >= setTime){
	 blt_send_data();				     		// send data to phone via bluetooth
     display_data_in_console();         		// send data to console via jtag uart

		 counter = 0 ;
	 }

	 for(i= 0 ; i<100000;i++){}
	 counter ++ ;

 }
}
