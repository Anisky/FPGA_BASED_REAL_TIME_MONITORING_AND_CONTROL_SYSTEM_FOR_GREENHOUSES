# FPGA_BASED_REAL_TIME_MONITORING_AND_CONTROL_SYSTEM_FOR_GREENHOUSES

  This project describes the design and implementation of an SoPC-based real time
monitoring and control system for a Greenhouse, using the Field Programmable Gate
Array (FPGA), to allow manual or automatic control of the environmental parameters
inside the greenhouse, in order to suit the requirements of the plants growing inside it.
Also, an Android application has been created to allow the user to see the
status of the greenhouse, and to manually control the actuators or to enter set points for
the environmental parameters in the automatic mode
  
  The system desgin can be divided into tow main parts: hardware system design and software
  system design.
  
Hardware system design  
    The hardware part of this project consists of on-chip hardware and off-chip hardware.
The on-chip hardware contains the FPGA chip and all the component designed inside it, inculuding
the nios II processor, the on-chip memory, and all the vhdl components. The off-chip hardware 
contains all the external components used on the protobaord and on the Intel DE2 board.

Software system design
    The software system in composed of the Firmware and the Android application.
   
FIRMWARE   
The firmware provides control and monitoring of the system and manages all the communications 
and data manipulation between the processor and the peripherals. The C programming language is used
to program the Nios II softcore processor. 


ANDROID APPLICATION
user-friendly android application is built, so that the farmer can be in touch with his greenhouse
in real time around the clock. This application allows him to visualize the environment parameters to
keep track of the state of his crops. Also, it provides him with the needed tools to control all the
actuators inside his house. In addition to that, an authentication and a cloud storage system are
implemented in the application, so that it can securely save user data in the cloud and provide the
same personalized experience on all of the user's devices.
We used for that google firebase platform, that provides these backend services to make the application
developpemet more easier and customizable, and incorporates years of google experience.


