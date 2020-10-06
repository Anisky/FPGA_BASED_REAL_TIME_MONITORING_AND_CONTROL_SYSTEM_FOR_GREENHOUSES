# Legal Notice: (C)2020 Altera Corporation. All rights reserved.  Your
# use of Altera Corporation's design tools, logic functions and other
# software and tools, and its AMPP partner logic functions, and any
# output files any of the foregoing (including device programming or
# simulation files), and any associated documentation or information are
# expressly subject to the terms and conditions of the Altera Program
# License Subscription Agreement or other applicable license agreement,
# including, without limitation, that your use is for the sole purpose
# of programming logic devices manufactured by Altera and sold by Altera
# or its authorized distributors.  Please refer to the applicable
# agreement for further details.

#**************************************************************
# Timequest JTAG clock definition
#   Uncommenting the following lines will define the JTAG
#   clock in TimeQuest Timing Analyzer
#**************************************************************

#create_clock -period 10MHz {altera_internal_jtag|tckutap}
#set_clock_groups -asynchronous -group {altera_internal_jtag|tckutap}

#**************************************************************
# Set TCL Path Variables 
#**************************************************************

set 	garduino_sys_v1_sys_cpu_v1 	garduino_sys_v1_sys_cpu_v1:*
set 	garduino_sys_v1_sys_cpu_v1_oci 	garduino_sys_v1_sys_cpu_v1_nios2_oci:the_garduino_sys_v1_sys_cpu_v1_nios2_oci
set 	garduino_sys_v1_sys_cpu_v1_oci_break 	garduino_sys_v1_sys_cpu_v1_nios2_oci_break:the_garduino_sys_v1_sys_cpu_v1_nios2_oci_break
set 	garduino_sys_v1_sys_cpu_v1_ocimem 	garduino_sys_v1_sys_cpu_v1_nios2_ocimem:the_garduino_sys_v1_sys_cpu_v1_nios2_ocimem
set 	garduino_sys_v1_sys_cpu_v1_oci_debug 	garduino_sys_v1_sys_cpu_v1_nios2_oci_debug:the_garduino_sys_v1_sys_cpu_v1_nios2_oci_debug
set 	garduino_sys_v1_sys_cpu_v1_wrapper 	garduino_sys_v1_sys_cpu_v1_jtag_debug_module_wrapper:the_garduino_sys_v1_sys_cpu_v1_jtag_debug_module_wrapper
set 	garduino_sys_v1_sys_cpu_v1_jtag_tck 	garduino_sys_v1_sys_cpu_v1_jtag_debug_module_tck:the_garduino_sys_v1_sys_cpu_v1_jtag_debug_module_tck
set 	garduino_sys_v1_sys_cpu_v1_jtag_sysclk 	garduino_sys_v1_sys_cpu_v1_jtag_debug_module_sysclk:the_garduino_sys_v1_sys_cpu_v1_jtag_debug_module_sysclk
set 	garduino_sys_v1_sys_cpu_v1_oci_path 	 [format "%s|%s" $garduino_sys_v1_sys_cpu_v1 $garduino_sys_v1_sys_cpu_v1_oci]
set 	garduino_sys_v1_sys_cpu_v1_oci_break_path 	 [format "%s|%s" $garduino_sys_v1_sys_cpu_v1_oci_path $garduino_sys_v1_sys_cpu_v1_oci_break]
set 	garduino_sys_v1_sys_cpu_v1_ocimem_path 	 [format "%s|%s" $garduino_sys_v1_sys_cpu_v1_oci_path $garduino_sys_v1_sys_cpu_v1_ocimem]
set 	garduino_sys_v1_sys_cpu_v1_oci_debug_path 	 [format "%s|%s" $garduino_sys_v1_sys_cpu_v1_oci_path $garduino_sys_v1_sys_cpu_v1_oci_debug]
set 	garduino_sys_v1_sys_cpu_v1_jtag_tck_path 	 [format "%s|%s|%s" $garduino_sys_v1_sys_cpu_v1_oci_path $garduino_sys_v1_sys_cpu_v1_wrapper $garduino_sys_v1_sys_cpu_v1_jtag_tck]
set 	garduino_sys_v1_sys_cpu_v1_jtag_sysclk_path 	 [format "%s|%s|%s" $garduino_sys_v1_sys_cpu_v1_oci_path $garduino_sys_v1_sys_cpu_v1_wrapper $garduino_sys_v1_sys_cpu_v1_jtag_sysclk]
set 	garduino_sys_v1_sys_cpu_v1_jtag_sr 	 [format "%s|*sr" $garduino_sys_v1_sys_cpu_v1_jtag_tck_path]

#**************************************************************
# Set False Paths
#**************************************************************

set_false_path -from [get_keepers *$garduino_sys_v1_sys_cpu_v1_oci_break_path|break_readreg*] -to [get_keepers *$garduino_sys_v1_sys_cpu_v1_jtag_sr*]
set_false_path -from [get_keepers *$garduino_sys_v1_sys_cpu_v1_oci_debug_path|*resetlatch]     -to [get_keepers *$garduino_sys_v1_sys_cpu_v1_jtag_sr[33]]
set_false_path -from [get_keepers *$garduino_sys_v1_sys_cpu_v1_oci_debug_path|monitor_ready]  -to [get_keepers *$garduino_sys_v1_sys_cpu_v1_jtag_sr[0]]
set_false_path -from [get_keepers *$garduino_sys_v1_sys_cpu_v1_oci_debug_path|monitor_error]  -to [get_keepers *$garduino_sys_v1_sys_cpu_v1_jtag_sr[34]]
set_false_path -from [get_keepers *$garduino_sys_v1_sys_cpu_v1_ocimem_path|*MonDReg*] -to [get_keepers *$garduino_sys_v1_sys_cpu_v1_jtag_sr*]
set_false_path -from *$garduino_sys_v1_sys_cpu_v1_jtag_sr*    -to *$garduino_sys_v1_sys_cpu_v1_jtag_sysclk_path|*jdo*
set_false_path -from sld_hub:*|irf_reg* -to *$garduino_sys_v1_sys_cpu_v1_jtag_sysclk_path|ir*
set_false_path -from sld_hub:*|sld_shadow_jsm:shadow_jsm|state[1] -to *$garduino_sys_v1_sys_cpu_v1_oci_debug_path|monitor_go
