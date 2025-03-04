Library IEEE;
use IEEE.std_logic_1164.all;

Entity clock_divider is
	port(
   RST    : in std_logic;
	CLK_in : in std_logic;
   --	OE     : in std_logic;
	CLK_out: out std_logic
	--EOC_in : out std_logic
	);
end clock_divider;

Architecture Behavioral of clock_divider is
signal counter : integer := 1;
signal temp    : std_logic :='0';

begin
 divider : process(RST, CLK_in)
	begin
		if RST = '1' then
			counter <= 0;
			temp <= '0' ;
		elsif CLK_in'event and CLK_in = '1' then
			counter <= counter + 1 ;
			if (counter = 60 ) then 
			   temp <= not (temp) ;
				counter <= 1 ;
			end if ;
		end if;
	end process divider;
  
	   
	CLK_out <= temp ;
end Behavioral;