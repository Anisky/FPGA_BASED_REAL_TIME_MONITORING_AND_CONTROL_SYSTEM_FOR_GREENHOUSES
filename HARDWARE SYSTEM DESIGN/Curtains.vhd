--=============================
--  Cutains unit
--=============================
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity Cutains is
   port(
      En         		: in std_logic;
	   CLK         	: in std_logic;
      state   		   : in std_logic_vector(1 downto 0);
		open_s , close_s , h_open : in std_logic ;
		speed          : out std_logic ;
      out_curtains      : out std_logic_vector(3 downto 0)
   );
end Cutains;

architecture beha of Cutains is

Component PWM is 
	port(
		reset				 : in std_logic;
		clk				 : in std_logic; 
		w       			 : in unsigned(7 downto 0);
		pwm_pulse: out std_logic);
end component;

component stepper_motor is 
	port (
		clk             : in std_logic; 
		rst             : in std_logic;
		clkwise         : in std_logic ;
		out_stm :  out std_logic_vector(3 downto 0)
	  );
 end component ;  
 
 signal direction ,reset: std_logic; 
 signal output    : std_logic_vector(3 downto 0);
 signal pre_state : std_logic_vector(1 downto 0);
 
 begin
 
  
  process (open_s , close_s , h_open, state, clk)
  begin 
       case state is
         when "00"   => if (open_s  = '1') then reset <= '1' ;
						      else reset    <= '0';
							       direction <= '0';
									 pre_state <= "00" ;
							   end if ; 
			when "10"   => if (close_s  = '1') then reset <= '1' ;
						      else reset <= '0';
							       direction<= '1';
									 pre_state <= "10" ;
							   end if ;
			when "01"   => if (h_open = '1')  then reset <= '1' ;
						      else reset <= '0';
								     if(pre_state = "00") then  direction<= '1';
									  elsif ( pre_state = "10") then direction<='0';
									  end if; 

							   end if ;
         when others => reset <= '1' ; 
       end case ;	
     end process;    
  
   U01 : pwm port map(reset,clk,to_unsigned(127,8),speed);
	U02 : stepper_motor port map (clk , reset, direction , output);
	
	out_curtains <= output ;
											
end beha;
