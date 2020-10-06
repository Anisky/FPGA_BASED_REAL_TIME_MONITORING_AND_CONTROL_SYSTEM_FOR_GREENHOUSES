/*
 * main.c
 *
 *  Created on: May 25, 2020
 *      Author: aniss
 */



#include <system.h>
#include "alt_types.h"
#include "io.h"
#include <altera_avalon_uart_regs.h>
#include "sys_uart.h"
/********************************************************************
 * constant definitions
 *******************************************************************/
/* write an 8-bit char */
#define jtaguart_wr_ch(base, data) 	IOWR(base,JUART_DATA_REG_OFT, data &0x000000ff)
#define jtaguart_rd_ch(base)        IORD(base,JUART_DATA_REG_OFT)
#define blt_wr_ch(base, data) 	    IOWR_ALTERA_AVALON_UART_TXDATA(base, data & 0x00ff)
#define blt_rd_ch(base) 	        IOWR_ALTERA_AVALON_UART_TXDATA(base, data & 0x00ff)



/********************************************************************
 * function definitions
 *******************************************************************/
void jtaguart_wt_str(alt_u32 jtag_base , char* msg){

	alt_u32 data32 ;
	while(*msg){
		data32 = (alt_u32) *msg;
		if(jtaguart_rd_wspace(jtag_base)!=0){

			jtaguart_wr_ch(jtag_base, data32);
			msg++;
		}
	}
}
void btl_wt_str(alt_u32 jtag_base , char* msg){

	alt_u32 data32 ;
	while(*msg){
		data32 = (alt_u32) *msg;

		if(blt_rd_wspace(jtag_base)!=0){

			blt_wr_ch(jtag_base, data32);
			msg++;
		}
	}
}
alt_u8 get_data (alt_u32 pio_base){
  return (alt_u8)pio_read(pio_base);
}
void display_data(char* data){
	char d = get_adc_data();
    unsigned int tem = (unsigned int) pio_read(TEM_DATA_BASE);
    unsigned int hum = (unsigned int) pio_read(HUM_DATA_BASE);
	*(data + 12 )= (char)(tem/10 + 48);
	*(data + 13 )= (char)(tem%10 + 48);
	*(data + 28 )= (char)(hum/10 + 48);
	*(data + 29 )= (char)(hum%10 + 48);
	*(data + 44 )= (char)(d%10 + 48);
	*(data + 45 )= (char)(d/10 + 48);
	/**(data + 60 )= (char)(d[1]%10 + 48);
	*(data + 61 )= (char)(d[1]%10 + 48);
	*(data + 76 )= (char)(d[2]%10 + 48);
	*(data + 77 )= (char)(d[2]%10 + 48);
	*(data + 92 )= (char)(d[3]%10 + 48);
	*(data + 93 )= (char)(d[3]%10 + 48);*/

	jtaguart_wt_str(JTAG_UART_BASE,data);
	//btl_wt_str(0x1003000,data);
}
void send_integers (){
	char tem = (char) pio_read(TEM_DATA_BASE);
	char hum = (char) pio_read(HUM_DATA_BASE);
	char bytes []={tem,hum};
	/*bytes[0]  = (char)((tem & 0x00ff0000) >> 16);
	bytes[1]= (char)((tem & 0xffff0000) >> 24);
	bytes[2]= (char)((hum & 0x00ff0000) >> 16);
	bytes[3]= (char)((hum & 0xff000000) >> 24);
*/
	//jtaguart_wt_str(JTAG_UART_BASE,bytes);
	btl_wt_str(0x1003000,bytes);
}


int main(){
 char a = 'k';
 int i;
 char data [] = "Temp  is : 000 \n"
		        "Hum   is : 000 \n"
		        "Pot1  is : 000 \n"
		        "Pot2  is : 000 \n"
		        "Pot3  is : 000 \n"
		        "LDR   is : 000 \n";
 	while (1){
 	start_conv();
 	char msg [] = "\ndata sent is : 0 \n";


 	a=	jtaguart_rd_ch(0x1003000);
     msg[16]= a ;
     jtaguart_wt_str(JTAG_UART_BASE,msg);
     //btl_wt_str(0x1003000,msg);
     display_data(data);

     send_integers();
     if(a=='1'){
     	IOWR(GREEN_LEDS_BASE,0,0x55);
     }
     if(a=='0')
     	IOWR(GREEN_LEDS_BASE,0,0x00);



 	// for(i= 0 ; i<100000;i++){}


 	}
}
