/*
 * sys_jtaguart.c
 *
 *  Created on: Apr 5, 2020
 *      Author: Anis
 */

#include "sys_uart.h"
#include "stdio.h"
int i ;
// reading from jtagUart
void jtaguart_wt_str(alt_u32 jtag_base , char* msg){

	alt_u16 data16 ;
	while(*msg){
		data16 = (alt_u16) *msg;
		if(jtaguart_rd_wspace(jtag_base)!=0){  // buffer space available

			jtaguart_wr_ch(jtag_base, data16); // send a char
			msg++;
		}
	}
}
// reading from Bluetooth UART
void btl_wt_str(alt_u32 blt_base , char* msg){
	alt_u32 data32 ;
		while(*msg){
			data32 = (alt_u32) *msg;

			if(blt_wr_wspace( blt_base )!=0){ // buffer space available

				blt_wr_ch(blt_base, data32);  // send a char
				msg++;
			}
		}
}







