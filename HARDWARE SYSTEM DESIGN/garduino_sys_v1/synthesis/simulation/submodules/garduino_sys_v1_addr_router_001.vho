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

--synthesis_resources = mux21 121 
 LIBRARY ieee;
 USE ieee.std_logic_1164.all;

 ENTITY  garduino_sys_v1_addr_router_001 IS 
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
 END garduino_sys_v1_addr_router_001;

 ARCHITECTURE RTL OF garduino_sys_v1_addr_router_001 IS

	 ATTRIBUTE synthesis_clearbox : natural;
	 ATTRIBUTE synthesis_clearbox OF RTL : ARCHITECTURE IS 1;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_101m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_103m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_104m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_105m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_111m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_112m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_119m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_121m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_122m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_123m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_129m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_130m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_131m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_137m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_139m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_140m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_141m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_147m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_148m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_149m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_150m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_155m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_157m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_158m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_159m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_165m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_166m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_167m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_168m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_169m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_173m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_175m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_176m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_177m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_183m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_184m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_185m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_186m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_187m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_188m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_191m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_193m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_194m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_195m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_201m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_202m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_203m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_204m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_205m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_206m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_207m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_209m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_211m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_212m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_213m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_219m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_220m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_221m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_222m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_223m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_224m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_225m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_226m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_227m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_229m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_230m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_231m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_49m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_50m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_67m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_68m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_69m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_83m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_85m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_86m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_87m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_channel_93m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_106m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_107m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_108m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_109m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_124m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_125m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_126m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_127m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_142m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_143m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_144m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_145m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_160m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_161m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_162m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_163m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_178m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_179m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_180m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_181m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_196m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_197m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_198m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_199m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_214m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_215m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_216m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_217m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_232m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_233m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_234m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_235m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_52m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_53m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_54m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_55m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_70m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_71m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_72m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_73m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_88m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_89m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_90m_dataout	:	STD_LOGIC;
	 SIGNAL	wire_garduino_sys_v1_addr_router_001_src_data_91m_dataout	:	STD_LOGIC;
	 SIGNAL  wire_w_lg_w_sink_data_range119w530w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_lg_w_sink_data_range122w411w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_lg_w_sink_data_range143w331w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_lg_w_sink_data_range146w308w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w1w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_lg_w_sink_data_range122w390w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_lg_w_sink_data_range125w344w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_lg_w_sink_data_range146w330w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_lg_w_sink_data_range149w307w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_1_527_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_2_553_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_3_579_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout :	STD_LOGIC;
	 SIGNAL  s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout :	STD_LOGIC;
	 SIGNAL  wire_w_sink_data_range119w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_sink_data_range122w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_sink_data_range125w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_sink_data_range143w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_sink_data_range146w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
	 SIGNAL  wire_w_sink_data_range149w	:	STD_LOGIC_VECTOR (0 DOWNTO 0);
 BEGIN

	wire_w_lg_w_sink_data_range119w530w(0) <= wire_w_sink_data_range119w(0) AND wire_w_lg_w_sink_data_range122w390w(0);
	wire_w_lg_w_sink_data_range122w411w(0) <= wire_w_sink_data_range122w(0) AND wire_w_lg_w_sink_data_range125w344w(0);
	wire_w_lg_w_sink_data_range143w331w(0) <= wire_w_sink_data_range143w(0) AND wire_w_lg_w_sink_data_range146w330w(0);
	wire_w_lg_w_sink_data_range146w308w(0) <= wire_w_sink_data_range146w(0) AND wire_w_lg_w_sink_data_range149w307w(0);
	wire_w1w(0) <= NOT s_wire_garduino_sys_v1_addr_router_001_src_channel_1_527_dataout;
	wire_w_lg_w_sink_data_range122w390w(0) <= NOT wire_w_sink_data_range122w(0);
	wire_w_lg_w_sink_data_range125w344w(0) <= NOT wire_w_sink_data_range125w(0);
	wire_w_lg_w_sink_data_range146w330w(0) <= NOT wire_w_sink_data_range146w(0);
	wire_w_lg_w_sink_data_range149w307w(0) <= NOT wire_w_sink_data_range149w(0);
	s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout <= (((((((((((((((((((wire_w_lg_w_sink_data_range122w411w(0) AND (NOT sink_data(42))) AND sink_data(43)) AND (NOT sink_data(44))) AND (NOT sink_data(45))) AND (NOT sink_data(46))) AND (NOT sink_data(47))) AND sink_data(48)) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout <= ((((((((((((((((((((((NOT sink_data(39)) AND wire_w_lg_w_sink_data_range122w390w(0)) AND sink_data(41)) AND (NOT sink_data(42))) AND sink_data(43)) AND (NOT sink_data(44))) AND (NOT sink_data(45))) AND (NOT sink_data(46))) AND (NOT sink_data(47))) AND sink_data(48)) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout <= ((((((((((((((((((((wire_w_lg_w_sink_data_range119w530w(0) AND sink_data(41)) AND (NOT sink_data(42))) AND sink_data(43)) AND (NOT sink_data(44))) AND (NOT sink_data(45))) AND (NOT sink_data(46))) AND (NOT sink_data(47))) AND sink_data(48)) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_1_527_dataout <= (((((((((((wire_w_lg_w_sink_data_range146w308w(0) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_2_553_dataout <= ((((((((((((wire_w_lg_w_sink_data_range143w331w(0) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_3_579_dataout <= (((((((((((((((((((wire_w_lg_w_sink_data_range125w344w(0) AND (NOT sink_data(42))) AND (NOT sink_data(43))) AND (NOT sink_data(44))) AND (NOT sink_data(45))) AND (NOT sink_data(46))) AND (NOT sink_data(47))) AND sink_data(48)) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout <= ((((((((((((((((((((sink_data(40) AND sink_data(41)) AND (NOT sink_data(42))) AND (NOT sink_data(43))) AND (NOT sink_data(44))) AND (NOT sink_data(45))) AND (NOT sink_data(46))) AND (NOT sink_data(47))) AND sink_data(48)) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout <= ((((((((((((((((((((wire_w_lg_w_sink_data_range122w390w(0) AND wire_w_lg_w_sink_data_range125w344w(0)) AND sink_data(42)) AND (NOT sink_data(43))) AND (NOT sink_data(44))) AND (NOT sink_data(45))) AND (NOT sink_data(46))) AND (NOT sink_data(47))) AND sink_data(48)) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout <= (((((((((((((((((((wire_w_lg_w_sink_data_range122w411w(0) AND sink_data(42)) AND (NOT sink_data(43))) AND (NOT sink_data(44))) AND (NOT sink_data(45))) AND (NOT sink_data(46))) AND (NOT sink_data(47))) AND sink_data(48)) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout <= ((((((((((((((((((((wire_w_lg_w_sink_data_range122w390w(0) AND sink_data(41)) AND sink_data(42)) AND (NOT sink_data(43))) AND (NOT sink_data(44))) AND (NOT sink_data(45))) AND (NOT sink_data(46))) AND (NOT sink_data(47))) AND sink_data(48)) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout <= ((((((((((((((((((((sink_data(40) AND sink_data(41)) AND sink_data(42)) AND (NOT sink_data(43))) AND (NOT sink_data(44))) AND (NOT sink_data(45))) AND (NOT sink_data(46))) AND (NOT sink_data(47))) AND sink_data(48)) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout <= ((((((((((((((((((((wire_w_lg_w_sink_data_range122w390w(0) AND wire_w_lg_w_sink_data_range125w344w(0)) AND (NOT sink_data(42))) AND sink_data(43)) AND (NOT sink_data(44))) AND (NOT sink_data(45))) AND (NOT sink_data(46))) AND (NOT sink_data(47))) AND sink_data(48)) AND sink_data(49)) AND (NOT sink_data(50))) AND (NOT sink_data(51))) AND (NOT sink_data(52))) AND (NOT sink_data(53))) AND (NOT sink_data(54))) AND (NOT sink_data(55))) AND (NOT sink_data(56))) AND (NOT sink_data(57))) AND (NOT sink_data(58))) AND (NOT sink_data(59))) AND sink_data(60));
	sink_ready <= src_ready;
	src_channel <= ( wire_garduino_sys_v1_addr_router_001_src_channel_219m_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_220m_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_221m_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_222m_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_223m_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_224m_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_225m_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_226m_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_227m_dataout & s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_229m_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_230m_dataout & wire_garduino_sys_v1_addr_router_001_src_channel_231m_dataout);
	src_data <= ( sink_data(99 DOWNTO 90) & wire_garduino_sys_v1_addr_router_001_src_data_232m_dataout & wire_garduino_sys_v1_addr_router_001_src_data_233m_dataout & wire_garduino_sys_v1_addr_router_001_src_data_234m_dataout & wire_garduino_sys_v1_addr_router_001_src_data_235m_dataout & sink_data(85 DOWNTO 0));
	src_endofpacket <= sink_endofpacket;
	src_startofpacket <= sink_startofpacket;
	src_valid <= sink_valid;
	wire_w_sink_data_range119w(0) <= sink_data(39);
	wire_w_sink_data_range122w(0) <= sink_data(40);
	wire_w_sink_data_range125w(0) <= sink_data(41);
	wire_w_sink_data_range143w(0) <= sink_data(47);
	wire_w_sink_data_range146w(0) <= sink_data(48);
	wire_w_sink_data_range149w(0) <= sink_data(49);
	wire_garduino_sys_v1_addr_router_001_src_channel_101m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_83m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_103m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_85m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_104m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_86m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_105m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_87m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_111m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_93m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_112m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_119m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_101m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_121m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_103m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_122m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_104m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_123m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_105m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_129m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_111m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_130m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_112m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_131m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_137m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_119m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_139m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_121m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_140m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_122m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_141m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_123m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_147m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_129m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_148m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_130m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_149m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_131m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_150m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_155m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_137m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_157m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_139m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_158m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_140m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_159m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_141m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_165m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_147m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_166m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_148m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_167m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_149m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_168m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_150m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_169m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_173m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_155m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_175m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_157m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_176m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_158m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_177m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_159m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_183m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_165m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_184m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_166m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_185m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_167m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_186m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_168m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_187m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_169m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_188m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_191m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_173m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_193m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_175m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_194m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_176m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_195m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_177m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_201m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_183m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_202m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_184m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_203m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_185m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_204m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_186m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_205m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_187m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_206m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_188m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_207m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_209m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_191m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_211m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_193m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_212m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_194m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_213m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_195m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_219m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_201m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_220m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_202m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_221m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_203m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_222m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_204m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_223m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_205m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_224m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_206m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_225m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_207m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_226m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_227m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_209m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_229m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_211m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_230m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_212m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_231m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_213m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_49m_dataout <= wire_w1w(0) AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_2_553_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_50m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_1_527_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_2_553_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_67m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_49m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_3_579_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_68m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_50m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_3_579_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_69m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_2_553_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_3_579_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_83m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_3_579_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_85m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_67m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_86m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_68m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_87m_dataout <= wire_garduino_sys_v1_addr_router_001_src_channel_69m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout);
	wire_garduino_sys_v1_addr_router_001_src_channel_93m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_106m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_88m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_107m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_89m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_108m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_90m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_109m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_91m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_5_631_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_124m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_106m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_125m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_107m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_126m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_108m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_127m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_109m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_6_657_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_142m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_124m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_143m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_125m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_144m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_126m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_145m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_127m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_7_683_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_160m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_142m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_161m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_143m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_162m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_144m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_163m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_145m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_8_709_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_178m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_160m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_179m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_161m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_180m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_162m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_181m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_163m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_9_735_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_196m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_178m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_197m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_179m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_198m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_180m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_199m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_181m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_10_761_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_214m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_196m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_215m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_197m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_216m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_198m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_217m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_199m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_11_787_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_232m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_214m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_233m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_215m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_234m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_216m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_235m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_217m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_12_813_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_52m_dataout <= wire_w1w(0) OR s_wire_garduino_sys_v1_addr_router_001_src_channel_2_553_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_53m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_1_527_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_2_553_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_54m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_1_527_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_2_553_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_55m_dataout <= s_wire_garduino_sys_v1_addr_router_001_src_channel_1_527_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_2_553_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_70m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_52m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_3_579_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_71m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_53m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_3_579_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_72m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_54m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_3_579_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_73m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_55m_dataout OR s_wire_garduino_sys_v1_addr_router_001_src_channel_3_579_dataout;
	wire_garduino_sys_v1_addr_router_001_src_data_88m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_70m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_89m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_71m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_90m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_72m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout);
	wire_garduino_sys_v1_addr_router_001_src_data_91m_dataout <= wire_garduino_sys_v1_addr_router_001_src_data_73m_dataout AND NOT(s_wire_garduino_sys_v1_addr_router_001_src_channel_4_605_dataout);

 END RTL; --garduino_sys_v1_addr_router_001
--synopsys translate_on
--VALID FILE
