Library ieee;
Use ieee.std_logic_1164.all;
Use ieee.std_logic_unsigned.all;
Use ieee.std_logic_arith.all;

entity stepper_motor is
     port (
	  clk         : in std_logic; 
	  rst         : in std_logic;
	  clkwise     : in std_logic ;
     out_stm     : out std_logic_vector(3 downto 0)
	  ); 
	  
end stepper_motor;

architecture stm_st_b of stepper_motor is

type  state_type is (s0,s1,s2,s3);
signal state : state_type;
signal div : std_logic_vector(20 downto 0);
signal lk,start : std_logic;
begin
 process(clk,rst) -- clock divider
 begin
 
     if (rst='0') then
         div<=(others=>'0');
     elsif(clk'event and clk='1') then
			div<=div+1;
	  end if;
	  
  end process;
  
lk<=div(15);

process(lk,rst,clkwise)

  begin
		if(rst='1')then
			state<=s0;
		elsif lk'event and lk='1' then
				if clkwise='0' then
							case state is
								when s0=>state<=s1 ;
								when s1=>state<=s2;
								when s2=>state<=s3;
								when s3=>state<=s0;
	                  	when others=>null;
							end case;
				elsif( clkwise='1')then 
				       case state is
							when s0=>state<=s3;
							when s3=>state<=s2;
							when s2=>state<=s1;
							when s1=>state<=s0;
							when others=>null;
						end case ;
				end if;
				
		end if;
end process;

with state select
	out_stm<="0110" when s0,
				"1010" when s1, 
				"1001" when s2,
				"0101" when s3;
End stm_st_b;