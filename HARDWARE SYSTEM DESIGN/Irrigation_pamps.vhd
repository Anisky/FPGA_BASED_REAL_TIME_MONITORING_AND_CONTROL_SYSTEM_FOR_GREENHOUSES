--=============================
-- Pump unit
--=============================
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity pump is
   port(
      En         		: in std_logic;
	   CLK         	: in std_logic;
      pamp_speed     : in std_logic_vector(7 downto 0);
      out_pamp       : out std_logic
   );
end pump;

architecture beha of pump is
Component PWM is 
	port(
	reset		: in std_logic;
   clk		: in std_logic; 
   w        : in std_logic_vector(7 downto 0);
   pwm_pulse: out std_logic);
end component;
   
signal ou : std_logic ;
	
begin
   
  U01 :  PWM port map('0', CLK, pamp_speed,ou);
	
	out_pamp <= ou when (En = '1') else '0';
end beha;
