--=============================
--  lights unit
--=============================
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity Lights is
   port(
      En         		: in std_logic;
	   CLK         	: in std_logic;
      pwm_rate   		: in std_logic_vector(7 downto 0);
      out_lights     : out std_logic
   );
end lights;

architecture beha of Lights is
Component PWM is 
port(
      reset		: in std_logic;
      clk		: in std_logic; 
	   w        : in unsigned(7 downto 0);
      pwm_pulse: out std_logic
   );
end component;
   
signal ou : std_logic ;
	
begin
   
	  U01 :  PWM port map('0', CLK, unsigned(pwm_rate),ou);
	
	out_lights <= ou when (En = '1') else '0';
end beha;
