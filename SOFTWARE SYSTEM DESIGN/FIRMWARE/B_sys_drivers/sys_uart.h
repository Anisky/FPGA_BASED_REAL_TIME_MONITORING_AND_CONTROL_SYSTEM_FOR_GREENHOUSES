/*
 * sys_uart.h
 *
 *  Created on: Jun 26, 2020
 *      Author: Anis
 */

#ifndef SYS_UART_H_
#define SYS_UART_H_

#include "alt_types.h"
#include "io.h"
#include <altera_avalon_uart_regs.h>
/********************************************************************
 * constant definitions
 *******************************************************************/
#define JUART_DATA_REG_OFT 0          // data register address offset
#define JUART_CTRL_REG_OFT 1          // control register address offset

/*check # slots available in FIFO buffer */
#define jtaguart_rd_wspace(base)	((IORD(base, JUART_CTRL_REG_OFT)& 0xffff0000) >> 16 )
#define blt_wr_wspace(base)      	\
((IORD_ALTERA_AVALON_UART_STATUS(base)&ALTERA_AVALON_UART_STATUS_TRDY_MSK )>> ALTERA_AVALON_UART_STATUS_TRDY_OFST)
#define blt_rd_wspace(base)      	\
((IORD_ALTERA_AVALON_UART_STATUS(base)&ALTERA_AVALON_UART_STATUS_RRDY_MSK )>> ALTERA_AVALON_UART_STATUS_RRDY_OFST)

/* write an 8-bit char */
#define jtaguart_wr_ch(base, data) 	IOWR(base,JUART_DATA_REG_OFT, data &0x000000ff)
#define blt_wr_ch(base, data) 	    IOWR_ALTERA_AVALON_UART_TXDATA(base, data & 0x00ff)

/* read an 8-bit char */
#define jtaguart_rd_ch(base)        IORD(base,JUART_DATA_REG_OFT)
#define blt_rd_ch(base) 	        IORD_ALTERA_AVALON_UART_RXDATA(base)
/********************************************************************
 * function definitions
 *******************************************************************/
void jtaguart_wt_str(alt_u32 jtag_base , char * msg);
void btl_wt_str(alt_u32 blt_base , char* msg);
#endif /* SYS_UART_H_ */
