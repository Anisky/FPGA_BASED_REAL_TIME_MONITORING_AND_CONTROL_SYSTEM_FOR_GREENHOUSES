Library IEEE;
use IEEE.std_logic_1164.all;

Entity Thermometer is
   generic(prd_ns : integer := 20 ); --- 50MHz clock
	port(
	RST : in std_logic;
	CLK : in std_logic;
	DIO : inout std_logic;
	tem_data: out std_logic_vector (7 downto 0);
	hum_data: out std_logic_vector(7 downto 0);
	SEG : out std_logic_vector(27 downto 0)
	--ANO : out std_logic_vector(3 downto 0)
	);
end Thermometer;

Architecture Structural of Thermometer is
Component Timer is generic(Ticks : integer := 10);
	port(
	RST : in std_logic;
	CLK : in std_logic;
	EOT : out std_logic);
end Component;
Component DHT11 is port(
	RST : in std_logic;
	CLK : in std_logic;
	STR : in std_logic;
	DIN : in std_logic;
    DOUT : out std_logic;
	HUM : out std_logic_vector(15 downto 0);
	TEM : out std_logic_vector(15 downto 0);
	CHK : out std_logic_vector(7 downto 0) );
end Component;
Component BinaryToDecimal is port(
	RST : in std_logic;
	CLK : in std_logic;
	STR : in std_logic;
	DIN : in std_logic_vector(7 downto 0);
	ONE : out std_logic_vector(3 downto 0);
	TEN : out std_logic_vector(3 downto 0);
	HUN : out std_logic_vector(3 downto 0);
	THO : out std_logic_vector(3 downto 0));
end Component;
--Component DisplayDriver is port(
--	RST  : in std_logic;
--	CLK  : in std_logic;
--	DIG1 : in std_logic_vector(3 downto 0);
--	DIG2 : in std_logic_vector(3 downto 0);
--	DIG3 : in std_logic_vector(3 downto 0);
--	DIG4 : in std_logic_vector(3 downto 0);
--	SEG  : out std_logic_vector(7 downto 0);
--	ANO  : out std_logic_vector(3 downto 0));
--end Component;

Component DisplayDri is port(
   code : in std_logic_vector (3 downto 0);
	seg  : out std_logic_vector (6 downto 0));
end Component ;


signal STR : std_logic;
signal TEM,HUM : std_logic_vector(15 downto 0);
signal TEM_ONE, TEM_TEN, HUM_ONE, HUM_TEN : std_logic_vector(3 downto 0);
begin
	U01 : Timer generic map(1500000000/prd_ns) port map(RST, CLK, STR);
	U02 : DHT11 port map(RST, CLK, STR, DIO, DIO, HUM, TEM, OPEN);
 	U03 : BinaryToDecimal port map(RST, CLK, STR, TEM(15 downto 8), TEM_ONE, TEM_TEN, open, open);
	U04 : BinaryToDecimal port map(RST, CLK, STR, HUM(15 downto 8), HUM_ONE, HUM_TEN, open, open);
	--U04 : DisplayDriver port map(RST, CLK, ONE, TEN, HUN, THO, SEG ); --, ANO);
	U05 : DisplayDri port map (TEM_ONE , SEG(6 downto 0));
	U06 : DisplayDri port map (TEM_TEN , SEG(13 downto 7));
	U07 : DisplayDri port map (HUM_ONE , SEG(20 downto 14));
	U08 : DisplayDri port map (HUM_TEN , SEG(27 downto 21));
	
	tem_data <= TEM(15 downto 8) ;
	hum_data <= HUM(15 downto 8);
end Structural;