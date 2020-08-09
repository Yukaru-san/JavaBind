import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GolangBinder {
	
	// Interface containing every needed Method
	// Should be inherited by Main Class
	public interface Bindings {
		public void OnMessageReceived(String msg);
	}
	
	// Hardcoded connection
    private static final int PORT = 8916;
    private static final String IP = "127.0.0.1";
    
    // Hardcoded signal-codes
    private static final String CONNECTION_HELLO = "WZ99$DY3S=42A#WHKUJ?WU&W6&X3F15|=P^LLCY9K4Z#-ZH2F_T9DK%B8@W2O^8IRI?4_!71Z5S48J$B7Y!REG_W2-ICNIWO?^S?M#D+97%ZVXY5K0C%+B#HVF+*9TVZ?SZP^Z|*D504!8IN3KT1&48|9+?3$A92G+VZ#^8=7-G0A?5N0JUJ&+8$$27%I3$DN|YC_J&!HDI@8IW_S-4I&BF5UH@28DJEF0LY8MGR4N^ULE*W3SOT#24!IS39O_GNV@786ZLEAN!+JIW$NY$VK|BFEDYD=A?7UCZ@%=A0G=QLCW6=$1NWRV%=IH=2O=R|RR6MHFC7NJK4G*P#9H94-!_1YO@VL8#|V-F7!G#$#P_I?IGPNG9#ER9Z0|$O1XYR3W1?+X^-YZ6TH-RS!0==#1RUAWD=WDJ623+W_-V0J6DLEJU#||2@#_YG?%36YSW35EPE$&3MIB?OCILUU8SFD&+YM!!-+TKIKUO|%80+EBKS&T?HQ^-X#I00#26Y^B@DEF?75VJE+0@=CO5QCM%_PG7XR@#Y#RM7URVSBAAN!5Q-TK$KT&ZF^APAX9KAP&6T=L9OT#S?U-AC1UTREE8H%OS8X5C8DUSPYW_%PR0E@#4!$=Q&D4KSBDARB*C+Q*YUE8X%AC2??-G4COKG%%+7J^W7_DR=UR+J|EP=6??|ZU0PK4+0!UY#03I^=?-4NBH=RAQ5I&K9S3-164ZY_D15_0NN7*NTBY9A&%ACS|JE4@MDH9*NGVJ8C3@O7ZFYQ4L!CVV!-79AO55^24@H!%X!VKXXCZ$|KA|8X5VKMW-3%77XK+=O|L3@XAA@DJIWQIY+L|7CD$0492G^L%|Q=3VS6#A-&I-0%N@G$ZH&*2&3|8B&RN29S8^1G+VUCUMSI!5!H#SM80E?IBJ|Q9-V!B3M6DSB+AX%2FSU9YV=1@B?=2E8A+#WFVYBH%KUM1SCHAJUXV267@=7ZC#M8?=1F@C_XVU#M|ZAV+K87&40XM@+2M8Y_2U@Q68REC3Y#G+!-DS1D%__TI6P7DWUI9B#%?_||QN+P5CWKX*ZGITU=HQIFJ?$LG#MAJY&D?3$_%79*49J%-@HGDWO27TN@WWJ&F9DF0&NH-1E%-KTB1BH^?0VM?!YCM=L6LX+$ZZ56U#GT5Q0E%BN|=6GH10@X20^HL5!6VU725%3CZ6IFPFI-Y?^*704BV1G5XOQI53@ZLGLV=MW$NC$??4V?1@QFIX-OC=YXA3#D$WJ*A612+E9ZZ7HL!=DWG^4I+ZTES7FPC@HR6M1KNZ1TZVODH!X&57R44MDZU|+I0XS_B|K#@A!OB%&-_FISNFXP@%E%10C^+TC@BD|G8GYZN#OU-Z4KYT|_QD21|_?EO|G60OY1V&4+II0TVJ40ZP|7%?WJMHTWZ=HB&@YE8%4*=!-DUP&UAJWE-_31S08-K+P+N*D-7-?GV^?TBVH^3&MAYI-ZAE6+QOZY#^+O#FX|5_R=3O=C4!FD!HULCV6YG|YB#3^-AKONE|S$+J7$&U|I8WO-LN1RQ|*F!HLZ94Y@KSOMNI1DX_AOQ58|R0NED-@J9=5FYWG4$2JN?%Y=S%VU0-PNOB$VHQPY2I7!!1_J?=65NLO-V59AT=G+_!O?M-5+?Q|VDM&#M9&GL+U%ZE4WYF|$+W#D98=C9&VD9RGIW+K^Z|?Z4BQ^?+857X?*V6YX?079*#0GK7BO?OL0+6ZHD4H7CACVA6$NI7=J@*JV8_AI$%KOWEWHA$T?0QZI8=7|F$#+1U7L+|JJ&@2UMQSDLB&#&2MC-P_XC7#F@*!L6?2JD-77PP=PVS|!2G?ES?ZO88O9I+!&Z@LE2Y==4UZ^$_#?V6GGV=CM#@Y7%0A|QHMLT^IC*#R&_%O+WMD^QH+JJ8_&H^8R^UB&86K*?@IC3XIL?R#SM6#@90R#!?U20SX5#M+@WZ-6%TL@^DQ|W#O$XS=!BER3%EGGR1Z3_S0?SVWVM-=SQQ86N9X6O6!172F|E6EQUGGM&KEH3$WMD1#*J9BG5NHC*#KAQMQ|WJ7|DGZ750O3*YD#6_#";
    private static final byte[] STOP_RECEIVER = "-J8@3V3J^DM1BH2&T3$O5S+&Y#Y&+*_1FBXC7U#MF=&-T3X5!E?PT1#DS|LAT^$-G||UCZJKK*0&P?IY&J?XYCF1Y9B%3Q*PIHLIQ54#_5SMA6XB7B9L89451T8O7IMQXHV!I|Q^X!0IB+%C9|^+QZZB36JI&EKBM#IT+FG7LX@6=C2#-$TY20NR*$?A*WP=7SG1TZBB!+I6*ZGYK73#QYUGULHW!7WW044Y&DNN|_P8EI8!MT1FYC0U@U*8P-VU8ULZ?C9M&QCU?4#=5UZH!Q-V3P8=@BOQ^_GQB?W$A9*37|D9Q1E18W=W&D&5QC239K0B4879L3E^1UP5RTA@=YEGCE2=4=_SM=4@C4A4NVL028QGMIWGV$$F2DJK*&4YKV#O+O3I!W_CI_FP4Z_BQ$UJF|?J?11%1CR*QV%295Q*RU+D36$?489@HCSBT!J1*PD?V8@|_ZTT?G%F|$BX^8RF*+|6-P5^=TIU9E8SAI6&-^*-5ZZ!CQM31G+4WH&^XVU=MRI%K0MB|*!OMC1Q#HG!CN|I5JHDV8NLKMMS#S3?IK2|=GHRSBC7BR0%9|5@SV7#CV$DXCRH5&W*7|38ZG=X2!H|M9PQ8JNHB8DO$5%05OEDH4GGQMS&NCPNFR_AXEBYZ6-NQ3T+Y4FJ2&!A^LRP15-@|J3&F5C1J_61$81-BUGFP*%P7MM9U=^_EFT6YSYRC_HN+K#N016L61J*K*^_MLP4R+1RF0=9B&9F9^047+=N+AG__68A_@F6KF-Z@V=LZVQ2SP1SX|+KBPCJNP^@A_6&+=MM1RN=+%XEE_SO%HY-Y#W5B1A&8^G_%7MVKYU3S$!3&T-HF@2O$5BT*3%@+$Z4P=$VXZP8@W@?ZDO66E*!AVUH!M0EE9^C09|97%OC@KL4Z#G69TR1QQEBK_60UW@6620J355*VGC|O7D?^JQM#BKD34ANPB^%QVKJ!?ZHAU-Z62580C^&FEW9|2C7N6T8CZ3N!9|GM74J62?*X$K&4OF^70^NXWX$$39-2?IEF5=9%S!P!@6T+%DVXXK!I@^Q#AGQX?V4&^O7A5#JHK#FJX?TDQG_Q_Y420Q-QPT2F|L2CQKEAJW^DBKXD53H&LHE57!L3D?*?4^#MO1|+PCPMT?G#MNHD|C-BO^Q-5L4_#5N&R1FG?E0WD+*#Z|X45+F^Y*OQ^@2-&XB7WA780*B_!0?GJ?K^2KQVQ&K-1KWPPOE=H0TV9K?XU31SDP_T&@@^88|0DA0Z6|07S*NWB+9#0T118S3=E25_8O2TB@Q*GQ-=4YU4@_8&U+MZWB91W+KV^T8%5NH?*J4JHYA_&6T$+4TLZB0YV85NU_ITT$&AF^=E761NY&2IVY?GU@*Q0HK|%#J6-7!S6CX5K#23OL*H+H?#?BX14IIYK0KP3B^WP6$0F?OI&J9B$DQ18VWHC^D#8VV-L-ZUJ-WV@ZNVB^NH62+&4M19RVSDWNQDC1%FG7BP6O*YH@2VB5O&*WT7|_TH+22E69X&J+!F|4COZ-WYSNMAWV|EDRFJNOC3-CDRN#CH2D21^_BFBDT3&*M#6HPQQXWYFB&46=80FKHF*!Y4V!RED0!0!HKU!7-X^G&6M0@RKT96G?8?NJ!?37?*T|$G6#YPMD5+3%C?94FRZ564=SOFH+WMPXM%DXR_S!?#R?V!=&O=IY6FO-1M@E55V08BE!3Q^Q01!H*W=_HAB4P$I#0-+B@H%43PNW778J4+JVAMX$DNJ2AF^EER6X7VE0*O|BY96XKW1C3S=5DE6Y38J0SC3@_HQ+DIJI5SB+Y*+&36+^?*UGSWYV_@V+Q_Y75575OFR$ZC3DOL!*BWZO76X?$R@X2D8C53PG@S4OTA@Y8ATY9E36V*8S=1O*5$CK0GO#+CK!E##@O0F-P0W@8R7EMEI9C4O=5Y*KHP2|9+^?P&QMM8N-K9!8-IMO8#RPPCOXO6B5P7V!VX@VCRRSY_6XBF|QL*LO^Y@RORH9$LX6GS-_TFBI8@&X4_UXT3FTP-XIWE5%WD*JBN*#K3XOFA1%?-&D5?8D%73^$".getBytes();
    private static final byte[] CONNECTION_END = "#FE1_93GJ!|6$_OG2DORFAHQRO6#CCZ%@JQDOI5%VNB!?U10Q69|@+IC#HRVZA9$QEW6!*D2&VNOI5=51Q0_PY!@%^U&KQ=PGXPA*-PWGBY?NV3-^56+2481%3U08Y*^^%OSAZ_B@8O9T!=&92MLMN7VFUFE-MF@7R3!X1^^7^XHSKB8B&GFAB4^TR+TX|G|KS1^-I7E9DF521I!H%W1IC-4ES2$@YKJ%4U6B@_SW2&23$%L0HMZBT?6LE?%=PDGC5SR%L01IK#1V0&|$UG|8PW-0$@^?L|_V9LT5T@RO2YXP27_5%&!+?YPDQWZQ$6G6%B@3^3*^MKF|!T-WE%5^+JW05S8K2%K1H5#$+Y6I3&?@FEBR%KG*Y^BGA|#20$5JM?FXO#2U8@P5O2Z7^L+08P#^#|PT6Z%&_6G-O7$OZ|@^TV@DF4IX9VOHR5PU2?%P#WF^N?-DO1AHZ!^S&_J@*B*HKN43#ZOS%+6S9YQL7M@#GSJ&JR#MFGP1G--FD6SOTZJLRLURN%T6LWGRC7$P^ZJD-+7G#JZGQ#TSIS^&*50OZ|Q&J*^*-&KR*$P3-5!T+@2V0U0SI3|#!BYIRUJ+-I81ALO0II61C=|8B*KEE1#F#%1BT4S_K@0!R842CJA49?=IU!QD650!BBBGYHV0?@NSEUZA_4YZ%4=!=JG5Y5VKN4*%=RYDM=OD!^|UPO*18DUT-N922FF%CJZ-V&U&GQ&WFIC7O#J2#IEU3W^I5T|QWC-?Y8PIJCR^?K%ZS0&1X2%ACNL5W9S=1HO909O^?$B&?_FED+&TN8C-_W6TBA9MFP_R@^2-45K+FM?7=$PW3IG8|&-HKK6@S0=XHW|GWEYAM2D|4?WVWC^WM74=YYLUBS8*?7_6DO^^M^O|A^1_&LLIQN+&Z0VOQYLN1I@JR+%B8GWQ=SD8ZSFEKV33E$IPRB0TGK!#MN*5HH3%A5-F|AY@W8S4LDG+!*Z9XLE4ZI_18$LY-%?HDD=+3GQ$!R*FI!GWX3M6I18TJ%-L$@PT-U=U==W3=+@L0*%%VTRB9UW?37V3^?J37IC|CFW@ODQGW#OLZE42YG^A&T2KTV452ZQ71XP811$^LI-HQ5%MFZX@|DD4-UT=D2@93XYCGA*WI*FX+#RD#74WDB%*R9C84HP&V6*F$!3B8S4IEJUI@$B!#CYU!^&RNLA9UX7TN%_#VOL|6$O=_1G6C=%093K265QMTH4%--^_V$4993F=9FDY|^M--X!VZ-^_$_8DH%9*#%UA1=F#XD!OZ_L@I?UX&-I7$0A?HLSIGKA|-TE2M-DA6D-O@LOYMH8S|VY00^J6+QM34*9W54#L*SVXWGG?|=QN=ST@8S&MK%_UW&XJ-YQP7BQ^MOMH^OW@=X$SRDE%VI38%V8+*AKU_T5H#*&GF5I+!9P6*C?$7-G312DX_XT&#+|3X_UH*P8HO9I=%B?UBWZ6WH5N+R+^C0OC33?8|?^S&U=B@3^B98GST_$G5O*3EB_GV!2L?!4WXOQ^PH1SUIDIUS!!+8005U5YYXFZMP22VST-UQ#63-Z?1VC!KX2QNVM@5C|+P789Q_1PFQ@&QVHM&$U#E05MM!Z+G=Q_%X#MR4=GAR%1JWZHE@G57Q*K&W28_U|0?U7BC^%H7Y0E@@@52?E2UX|X+?T0DMLUD&2@9#=OIWIEZZUL1I#-T&EJ7UHYX-81W8F1C!$|9=@K57KCX2?!9$J83^U^GA3N3A&%A69W442N!!QY9$$QT41-E@@U238!|3WCHX%|_14X-6BIBFF6SBOSO69=ZK$P0WQ710C5F9FABR0%*QQ0K*#V%17L#-U-CCZCUO%2JLX!M^VVNE-94YDZA?BV5U_AT^5ZQZL%|_1$@C|U!!MUCI&Q_WQZW!AS=KY4?-PIKQNICQ&D@A^Q99U9=|FU^FAC@B3F?D3N4CN0@+N=VI%$#ES7?@J2EZKU4C=0HOVCRL-1G%^GPNX*5$3G_?7EAY|LJQ-*&|SOWU^1$=HBOI!&=M7ZNS_H!95-S5LMA#2BP&YX0K9Q^5AUX8?O&M8OY^2".getBytes();
    
    // Objects used during communication
    private DatagramSocket clientSocket;
    private InetAddress IPAddress;
    
    // Variables
    public boolean isConnected = true;
    private boolean currentlyKeepingAlive = false;
    private boolean useAutomaticMessageReceiver = false;
    private boolean useManualMessageReceiver = false;
    
    private Bindings customMessageBindings = null;
    
    
    // Keeps the current process alive and in position
    // Stop using StopKeepAlive()
    // Note: Only one at a time possible (Useful for main class)
    public void KeepAlive() {
    	currentlyKeepingAlive = true;
    	while (currentlyKeepingAlive) {
    		try {
    			Thread.sleep(1000);
    		} catch(Exception e) {}
    	}
    }
    
    // Stops currently KeepAlive waiting points
    public void StopKeepAlive() {
    	currentlyKeepingAlive = false;
    }
    
    
    // Connects to the Golang messaging server
    public void ConnectToGolangServer() throws Exception {
    	// Create connection
        clientSocket = new DatagramSocket();
        IPAddress = InetAddress.getByName(IP);
        
        // Send initial "hello"
        SendMessage(CONNECTION_HELLO);
    }
    
    // Connects to the Golang messaging server AND sets the messageHandler
    public void ConnectToGolangServer(Bindings messageHandler) throws Exception {
    	// Create connection
        clientSocket = new DatagramSocket();
        IPAddress = InetAddress.getByName(IP);
        
        // Set customMessageBindings
        customMessageBindings = messageHandler;
        
        // Send initial "hello"
        SendMessage(CONNECTION_HELLO);
    }
    
    // Sets a custom Bindings interface
    public void SetMessageBindings(Bindings messageHandler) {
    	customMessageBindings = messageHandler;
    }
    
    // Closes any current connection to Golang
    // Also sends a signal
    public void CloseConnection() throws IOException {
    	// Send the closing signal
        clientSocket.send(new DatagramPacket(CONNECTION_END, CONNECTION_END.length, IPAddress, PORT));
    	
    	// Actually close the socket
    	clientSocket.close();
    	isConnected = false;
    }
    
    // SendMessage sends the given string to Golang
    public void SendMessage(String msg) throws IOException {
    
    	// Buffer
        byte[] sendData = new byte[2048];
    	
    	// Fill up Message with null bytes
    	for (int i = msg.length(); i < 2048; i++) {
    		msg += '\0';
    	}
    	
    	// Set Message Data
    	sendData = msg.getBytes();
                
        // Get Message data
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PORT);
        
        // Send msg
        clientSocket.send(sendPacket);
    }
    
    
    // ReceiveMessage returns the next incoming message
    // Stop by using StopMessageReceiver
    public String ReceiveNextMessage() throws Exception {
    	// Make sure the automatic receiver is not running
    	if (useManualMessageReceiver)
    		throw new Exception("ReceiveNextMessage is already running. Only one is possible! You can close one by using StopMessageReceiver");
    	else if (useAutomaticMessageReceiver)
    		throw new Exception("StartMessageReceiver is already running. Only one is possible! You can close one by using StopMessageReceiver");
    	
    	// Security
    	useManualMessageReceiver = true;
    	
    	// Receive packet
		byte[] receiveData = new byte[2048];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);

		// Security
		useManualMessageReceiver = false;
		
		// Return
		return new String(receivePacket.getData());
    }
      
    // StartMessageReceiver passes incoming messages to the handler
    // Stop by using StopMessageReceiver
    public void StartMessageReceiver() throws Exception {
    	// Make sure the automatic receiver is not running
    	if (useManualMessageReceiver)
    		throw new Exception("ReceiveNextMessage is already running. Only one is possible! You can close one by using StopMessageReceiver");
    	else if (useAutomaticMessageReceiver)
    		throw new Exception("StartMessageReceiver is already running. Only one is possible! You can close one by using StopMessageReceiver");
    	else if (customMessageBindings == null) {
    		throw new Exception("There are no Bindings to be called. Make sure to set them up before using StartMessageReceiver!");
    	}
    	
    	 // Start Message Handler
        new Thread() {
          	@Override
          	public void run() {
          		try {
          			while (isConnected && useAutomaticMessageReceiver) {
          	    		
          	    		// Buffer
          	    		byte[] receiveData = new byte[2048];
          	    		
          	    		// Create DatagramPacket
          	    		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
          	    	
          	    		// Receive Packet
          	    		try {
          	    			clientSocket.receive(receivePacket);
          	    		} catch (IOException e) {
          	    			if (isConnected) {
          	    				System.out.println("Error receiving message");
          	    			}
          	    		}
          	        
          	    		// Take message and pass it on to the Message Handler
          	    		String message = new String(receivePacket.getData());
          	    		customMessageBindings.OnMessageReceived(message);
          	    		
          	    	}
				} catch (Exception e) {
					e.printStackTrace();
				}
          	}
         }.start();
    	
    }
    
    // StopMessageReceiver stops any currently running message receiver
    public void StopMessageReceiver() throws IOException {
    	// Send stop code to client itself
        DatagramPacket sendPacket = new DatagramPacket(STOP_RECEIVER, STOP_RECEIVER.length, clientSocket.getLocalAddress(), clientSocket.getLocalPort());
        clientSocket.send(sendPacket);
        
        // Free the blockades
        useAutomaticMessageReceiver = false;
    	useManualMessageReceiver = false;
    	
    }
}