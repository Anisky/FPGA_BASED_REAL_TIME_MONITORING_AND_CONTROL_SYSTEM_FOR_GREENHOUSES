Library IEEE;
use IEEE.std_logic_1164.all;

Entity ADC_C is
	port(
   RST      	   : in std_logic;                    -- reset signal
	CLK_in 			: in std_logic;						  -- clock 50 MHz
	EOC_in 	 		: in std_logic;						  -- end of conversion signal from the ADC CHIP		
	Start  	 		: out std_logic;						  -- start conversoin signal to the ADC CHIP
	DATA_in 	 		: in std_logic_vector(7 downto 0); -- 8bit data bus from the ADC chip output Data
	
	CLK_out 			: out std_logic;						  -- clock to ADC clock 1 Mhz
	Chanel_0 		: out std_logic_vector(7 downto 0);-- Converted data from the adc IN0
	Chanel_1		 	: out std_logic_vector(7 downto 0);-- Converted data from the adc IN1
	Chanel_2 		: out std_logic_vector(7 downto 0);-- Converted data from the adc IN2
--	Chanel_3  		: out std_logic_vector(7 downto 0);-- Converted data from the adc IN3
--	Chanel_4 		: out std_logic_vector(7 downto 0);-- Converted data from the adc IN4
--	Chanel_5 		: out std_logic_vector(7 downto 0);-- Converted data from the adc IN5
--	Chanel_6			: out std_logic_vector(7 downto 0);-- Converted data from the adc IN6
--	Chanel_7  		: out std_logic_vector(7 downto 0);-- Converted data from the adc IN7
	Add       		: out std_logic_vector(1 downto 0);-- address bus to ADC chip
	
	Test_red_leds  : out std_logic_vector(7 downto 0);  
	Test_green_leds: out std_logic_vector(3 downto 0));
	
end ADC_C;

Architecture Behavioral of ADC_C is

type data_pack is array (2 downto 0) of std_logic_vector(7 downto 0);  -- array type to collect data from ADC output
type address is array (2 downto 0) of std_logic_vector(1 downto 0);    
type state_type is(reset , start_conv ,converting, end_conv) ;         -- FSM states to control the ADC chip

signal da : data_pack := ("00000000","00000000","00000000"); --,"00000000","00000000","00000000","00000000","00000000") ; 
signal addr : address := ("10","01","00"); -- address 
signal state : state_type;

signal counter       : integer := 1;                                   -- counter for the clock divider
signal del, index    : integer range  0 to 3 ;								  -- delay and index 
signal clk_slow      : std_logic :='0';                                -- slow clokc edge
signal str           : std_logic ;												  -- start signal 	
signal edge_det      : std_logic_vector(2 downto 0);
signal  eoc_falling_edge, eoc_rising_edge : boolean ;



begin

 clk_divider : process(RST, CLK_in)                                   -- This process in a clock divider 
	            begin																	 -- which divids the clock from 50Mhz to 1Mz		
						if RST = '1' then
							counter <= 0;
							clk_slow <= '0' ;
						elsif rising_edge(CLK_in) then
							counter <= counter + 1 ;
							if (counter = 50  ) then 
								clk_slow <= not (clk_slow) ;
								counter <= 1 ;
							end if ;
						end if;
 end process clk_divider;
	
	
 adc_contr : process (RST, EOC_in,clk_slow)  -- this process is an implientation of an FSM to control the different 
				 begin 									--	states of ADC working							
					 if(RST ='1') then 									 -- rest all the signal and set data to "00000000"
						da    <= ("00000000","00000000","00000000");--,"00000000","00000000","00000000","00000000","00000000");
						Add   <= "00";
						str   <= '0' ;
						index <= 0 ;
						state <= reset ;
						Test_green_leds  <= "0000" ;
					elsif rising_edge( clk_slow ) then 
							case state is 
								  when reset =>                               
										 Add <= addr(index);
										 str <= '0' ;
							          state <= start_conv ;
					    				 Test_green_leds <= "0001" ; -- for test purpose 
										 del <= 3 ;   
									when start_conv =>
										 str <='1';
										 if(del <= 0) then 
											state <= converting ;
											Test_green_leds <= "0010" ;
										 else 
											del <= del - 1 ; 
										 end if ;
									when converting => 
										 str <= '0' ; 
										 state <= end_conv ;
										 Test_green_leds <= "0100" ;
									when end_conv =>
										 if(eoc_rising_edge) then 
											da(index) <= DATA_in ;
											state <= reset ;
											Test_green_leds <= "1000" ;
							  
							            if ( index >= 2 ) then 
												index <= 0 ;
											else index <= index + 1 ;
											end if ;
										 else
											state <= end_conv ;
										 end if ;
						end case;	    	  				  
					end if ;
 end process adc_contr ;
	
	
 eoc_edge_det : process (clk_slow ) -- this is an edge detector. it stores the EOC_in signal's state each clk cycle 
                                    -- in a vector than compare them. if the transition is from 1 to 0 than its 
											   -- falling_edge and vise versa	                 
					 begin 
						 if rising_edge(clk_slow) then
						 edge_det <= EOC_in & edge_det(2 downto 1);
						 end if;
        
 end process eoc_edge_det;
		  
 eoc_rising_edge  <= edge_det(1 downto 0) = "10";
 eoc_falling_edge <= edge_det(1 downto 0) = "01";

	CLK_out <= clk_slow ;
	Test_red_leds <= da(2) ;
	
	Start <= str;	
	Chanel_0 <= da(0);
	Chanel_1 <= da(1);
	Chanel_2 <= da(2);
--	Chanel_3 <= da(3);
--	Chanel_4 <= da(4);
--	Chanel_5 <= da(5);
--	Chanel_6 <= da(6);
--	Chanel_7 <= da(7);
	
	
	
end Behavioral;