--=============================
--  Roof unit
--=============================
library ieee ;
use ieee.std_logic_1164.all ;
use ieee.numeric_std.all;

entity Roof is
port (
      En       : in std_logic ;
		clk      : in std_logic ;
      state    : in std_logic ;
		spd_in : in std_logic_vector(7 downto 0);
      open_s , close_s : in std_logic ;
      output : out std_logic_vector (1 downto 0);
		speed: out std_logic 
		) ;

      
  end Roof ;
  
architecture arch of Roof is 

Component PWM is 
	port(
		reset				 : in std_logic;
		clk				 : in std_logic; 
		w       			 : in unsigned(7 downto 0);
		pwm_pulse       : out std_logic);
end component;

signal reset : std_logic;
signal ou    : std_logic;
begin 


process (open_s , close_s , state )
begin 
  
       if(En = '0') then output <= "00" ;
		                   speed  <= '0'  ;
		 elsif(En = '1')then 
		    if(state = '0')then 
             if (open_s  = '1') then speed <= '0';
				 else output <= "01";
						speed  <= ou ;
				end if ; 
          elsif( state = '1')  then                                                              
             if (close_s  = '1') then speed <= '0' ;
				 else output <= "10";
						speed  <= ou ;

				 end if ; 
           end if ;        
       end if ;

 end process  ;

  U01 :  PWM port map(reset, CLK, unsigned(spd_in),ou);
  
											
end arch;
