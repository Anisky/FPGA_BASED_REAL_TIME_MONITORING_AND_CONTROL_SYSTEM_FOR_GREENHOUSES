--IP Functional Simulation Model
--VERSION_BEGIN 13.0 cbx_mgl 2013:06:12:18:04:42:SJ cbx_simgen 2013:06:12:18:03:40:SJ  VERSION_END


-- Copyright (C) 1991-2013 Altera Corporation
-- Your use of Altera Corporation's design tools, logic functions 
-- and other software and tools, and its AMPP partner logic 
-- functions, and any output files from any of the foregoing 
-- (including device programming or simulation files), and any 
-- associated documentation or information are expressly subject 
-- to the terms and conditions of the Altera Program License 
-- Subscription Agreement, Altera MegaCore Function License 
-- Agreement, or other applicable license agreement, including, 
-- without limitation, that your use is for the sole purpose of 
-- programming logic devices manufactured by Altera and sold by 
-- Altera or its authorized distributors.  Please refer to the 
-- applicable agreement for further details.

-- You may only use these simulation model output files for simulation
-- purposes and expressly not for synthesis or any other purposes (in which
-- event Altera disclaims all warranties of any kind).


--synopsys translate_off

--synthesis_resources = mux21 6 
 LIBRARY ieee;
 USE ieee.std_logic_1164.all;

 ENTITY  garduino_sys_v1_addr_router IS 
	 PORT 
	 ( 
		 clk	:	IN  STD_LOGIC;
		 reset	:	IN  STD_LOGIC;
		 sink_data	:	IN  STD_LOGIC_VECTOR (99 DOWNTO 0);
		 sink_endofpacket	:	IN  STD_LOGIC;
		 sink_ready	:	OUT  STD_LOGIC;
		 sink_startofpacket	:	IN  STD_LOGIC;
		 sink_valid	:	IN  STD_LOGIC;
		 src_channel	:	OUT  STD_LOGIC_VECTOR (12 DOWNTO 0);
		 src_data	:	OUT  STD_LOGIC_VECTOR (99 DOWNTO 0);
		 src_endofpacket	:	OUT  STD_LOGIC;
		 src_ready	:	IN  STD_LOGIC;
		 src_startofpacket	:	OUT  STD_LOGIC;
		 src_valid	:	OUT  STD_LOGIC
	 ); 
 END garduino_sys_v1_addr_router;

 ARCHITECTURE RTL OF garduino_sys_v1_addr_router IS

	 ATTRIBUTE synthesis_clearbox : natural;
	 ATTRIBUTE synthesis_clearbox OF RTL : ARCHITECTURE IS 1;
	 SIGNAL	wire_garduino_sys_v1_addr_router_src_channel_49m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_src_channel_50m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_src_data_52m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_src_data_53m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_src_data_54m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_src_data_55m_dataout	:	STD_LOGIC;
	 SIGNAL  wire_w_lg_w_sink_data_range143w341w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_lg_w_sink_data_range146w318w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w1w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_lg_w_sink_data_range146w340w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_lg_w_sink_data_range149w317w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_src_channel_1_347_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_src_channel_2_373_dataout :	STD_LOGIC;
	 SIGNAL  wire_w_sink_data_range143w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_sink_data_range146w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_sink_data_range149w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
 BEGIN

	wire_w_lg_w_sink_data_range143w341w(0) <= wire_w_sink_data_range143w(0) AND wire_w_lg_w_sink_data_range146w340w(0);
	wire_w_lg_w_sink_data_range146w318w(0) <= wire_w_sink_data_range146w(0) AND wire_w_lg_w_sink_data_range149w317w(0);
	wire_w1w(0) <= NOT s_wire_garduino_sys_v1_addr_router_src_channel_1_347_dataout;
	wire_w_lg_w_sink_data_range146w340w(0) <= NOT wire_w_sink_data_range146w(0);
	wire_w_lg_w_sink_data_range149w317w(0) <= NOT wire_w_sink_data_range149w(0);
	s_wire_garduino_sys_v1_addr_router_src_channel_1_347_dataout <= (((((((((((wire_w_lg_w_sink_data_range146w318w(0) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_src_channel_2_373_dataout <= ((((((((((((wire_w_lg_w_sink_data_range143w341w(0) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	sink_ready <= src_ready;
	src_channel <= ( "0" & "0" & "0" & "0" & "0" & "0" & "0" & "0" & "0" & "0" & wire_garduino_sys_v1_addr_router_src_channel_49m_dataout & wire_garduino_sys_v1_addr_router_src_channel_50m_dataout & s_wire_garduino_sys_v1_addr_router_src_channel_2_373_dataout);
	src_data <= ( sink_data(99 DOWNTO 90) & wire_garduino_sys_v1_addr_router_src_data_52m_dataout & wire_garduino_sys_v1_addr_router_src_data_53m_dataout & wire_garduino_sys_v1_addr_router_src_data_54m_dataout & wire_garduino_sys_v1_addr_router_src_data_55m_dataout & sink_data(85 DOWNTO 0));
	src_endofpacket <= sink_endofpacket;
	src_startofpacket <= sink_startofpacket;
	src_valid <= sink_valid;
	wire_w_sink_data_range143w(0) <= sink_data(47);
	wire_w_sink_data_range146w(0) <= sink_data(48);
	wire_w_sink_data_range149w(0) <= sink_data(49);
	wire_garduino_sys_v1_addr_router_src_channel_49m_dataout <= wire_w1w(0) AND NOT(s_wire_garduino_sys_v1_addr_router_src_channel_2_373_dataout);
	wire_garduino_sys_v1_addr_router_src_channel_50m_dataout <= s_wire_garduino_sys_v1_addr_router_src_channel_1_347_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_src_channel_2_373_dataout);
	wire_garduino_sys_v1_addr_router_src_data_52m_dataout <= wire_w1w(0) OR s_wire_garduino_sys_v1_addr_router_src_channel_2_373_dataout;
	wire_garduino_sys_v1_addr_router_src_data_53m_dataout <= s_wire_garduino_sys_v1_addr_router_src_channel_1_347_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_src_channel_2_373_dataout);
	wire_garduino_sys_v1_addr_router_src_data_54m_dataout <= s_wire_garduino_sys_v1_addr_router_src_channel_1_347_dataout OR s_wire_garduino_sys_v1_addr_router_src_channel_2_373_dataout;
	wire_garduino_sys_v1_addr_router_src_data_55m_dataout <= s_wire_garduino_sys_v1_addr_router_src_channel_1_347_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_src_channel_2_373_dataout);

 END RTL; --garduino_sys_v1_addr_router
--synopsys translate_on
--VALID FILE
