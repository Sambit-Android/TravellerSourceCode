//unsigned char glob_bill_num[10];
unsigned char flag_consumer_load_manuall, manual_send_bill_flag;

extern struct Log_date_time *Log_date_time_ptr;
extern struct status_time_date_log *_status_time_date_log_ptr;

#define blank_print_bfr2 blank_print_bfr
extern char ch_chek;
#define PRINTER EPSON
// #define PRINTERF628

unsigned char *flag_batch_created;
unsigned char *batch_created_cnt;

#include "QP4627Avare.h"
//added by husain on 28.05.2014
#include "tariff_file_version2r_cesc_20_05_2014_may2014.c"
//end of addition
char compute_tarrif ();
void blank_display_line (char c1);
extern unsigned char ip_address[];
extern const char QP462732C_LIBRARIES_VER[] ;
void fill_space_for_unused_parameters();
void copy_spaces(unsigned char *bfr, int size1);
float near_qrt(float curnx,int type);
float rnd(float amount);
//****************************************************************
//******************tariff calulation with respect to each type***
//****************************************************************
// 54   LT2(a)-SW 	see software code		 
// On 3/5/07 LbackBillArrear hase been removed from Lclosing Balance and Lothers from Ltax
// calculateFC calculateEC
// if 1 take from backend
// if 2 calculate as per tariff table
// if 3 take from backend, and compare fixedcharges with enrgy charges and take whichever is low
// if 4 caclulate as per tariff table, and compare fixedcharges with enrgy charges and take whichever is low
//       for example if 33 is used, both parameters are taken from backend and whcihever is low is taken and other is made zero34 FC taken from backend, EC is calculated and whichever is low is taken44 FC & EC are calculated and whichever is low is taken43 FC calulate and EC taken from back end , and whichever is low is taken
// if 5 take from backend, and compare fixedcharges with enrgy charges and take whichever is high
// if 6 caclulate as per tariff table, and compare fixedcharges with enrgy charges and take whichever is high
// if 88 both FC and EC are calculated and total of FC and EC are compared with backend. FC. If the total of FC and EC is less than backend FC, then Backend FC is taken for charges and energy charges are made to zero.
// old/ if 99 both FC and EC are calculated and added and compared with backend FC+6.65, 
// old/       and if less backend FC is taken and EC made zer.If greater, 
// old/       caclulated  FC and calculated EC are takenIf backend FC is Rs 30, 
// old/       and calculated FC is 20, and EC is 9units * 1.85 = 16.65+20 = 36.65 backend FC 30 is taken

             
//		modified on 09.11.2005 to suit new tariff of Oct 2005
// new/ if 99 both FC and EC are calculated and added and compared with backend FC, 
// new/       and, if less, backend FC is taken and EC made zero.
// new/       If greater, caclulated  FC and calculated EC are taken
// new        

// valid  combinations are 11,12,21,22,33,34,43,44,55,56,65,66,8899only . 
// Other combinations will result in errors. 


// meter installed 
// if meter is installed present meter reading is not asked, and goes to print bill


// billstatus 0xfd - bill printing stared
// billstatus 0xfc - bill printed OK
// billstatus 0x7c - bill uploaded Ok


// 1 N   - NORMAL
// 2 SB  - SEAL BROKEN
// 3 NR  - NOT READEABLE
// 4 WT  - WRONG TARIFF
// 5 BY  - METER BYPASSED
// 6 PD  - PERMANENT DL
// 7 GB  - GLASS BROKEN
// 8 NU  - NOT IN USE

// if door lock take average
// if 		char doorlockavg[1];  == 'Y' then average is taken and billed
// if 		char doorlockavg[1];  == 'N' then zero units is biiled



unsigned long temp_long = 0,count = 0;

const char *menu6[] = { 	
						"1 SUMMARY REPORT    ",
						"2 SUMMERYWITHDETAILS",
						"3 INST NOT READ     ",
						"                    ",
						
					};	


unsigned long dflash_tariff;
extern unsigned long dflash_miscCESC; 
unsigned long dflash_misc; 

struct tariffdatavalue *eflash_tariffdatavalue_ptr;
unsigned char validbillprinted;
extern unsigned long Long_receipt_no;

extern char *Duty_closed;
char count_bill=0;
char ch_chek;
char Ldl_or_mnr_prev_month [1+1]; 	// '1' = 
									// '2' = DL 
									// '3' = MNR
									// '4' = DC
									// '5' = DISCONN
extern unsigned char DEBUG_FLAG;
float sc1u,sc2u,sc3u,sc4u,sc5u,sc6u,sc1r,sc2r,sc3r,sc4r,sc5r,sc6r,ecred,ecmax;
float LClosing_Balance_float_ba=0;
float net_round_diff=0;

unsigned char month_table[] = {31,28,31,30,31,30,31,31,30,31,30,31};

int len;
long sitecodei;
int newrollflag=0;
char LConsumer_SC_No[15+1];             
char Lmeter_constant [7+1]; 
char Lsitecode [4+1]; 
///char Lbillmonth[6+1];commented by manjunath.
char Lbillmonth[10];//added by manjunath.
//char Lconsumer_name [20+1];   
char LTariff [7+1];
//char Lledger_no [10+1];
//char Lfolio_no [10+1];
char LConnected_Load [7+1];
//char Lextra_present_reading [7+1];
char Ld_and_r_fee [7+1];
char Larrears [7+1]; 
char Linterest [7+1];
char Lothers [7+1]; 
char Lbackbillarr [7+1]; 
char Laverage_consumption [7+1]; 
//char Lprev_no_of_dls [7+1];   
//char Ldate_of_service[10+1]; 
char LPrevious_Reading[10+1]; 
char Lpower_factor[7+1]; 
char Lreading_date[10+1];
char Lmeter_change_units_consumed[7+1];
char Lno_of_months_the_bill_has_to_be_issued[5+1];
char Lcredit_or_rebate[7+1]; 
char Lfixed_charges[7+1];
char Laudit_arrears[7+1];
char Lold_interest[7+1];
char LADDITIONAL_DEP[10+1];
char Ldl_adj[10];

char LPresent_Reading[10+1];//7+1
char LMobileNumber[10+1];//7+1
char LltPFPenalty[10+1];
char LexcessLoadPenalty[10+1];
char Lunits[10+1];
char Lec[10+1];
char Lnetamountdue[10+1];
char Lpf_penality[10+1];
char LexcessLoadPenalty[10+1];
char LTAX [10+1];
char Lcredit[10+1];
char Lgrandtotal[10+1];
char Lout_no_of_months_the_bill_has_to_be_issued[2+1];
char Lmeterreadingtype[1+1];
char LReceipt_No[10+1];
char LCheque_No[10+1];
char LCheque_Date[10+1];
char LBank[35+1];
char Lbill_no[10+1];
char Lbill_status[1+1];
char Lrecord_separator[2+1];
char Lckwhlkwh[7+1];
char Lbmd[7+1];
char LPresent_PF[10];
float LPresent_PF_float=0;
char LRecorded_BMD[10];
float recordedBMD=0;
float Ldl_adj_float;
float dl_adj_amount;
float fueladjcharges;
float fueladjunits;
float slab1;
float slab2;
float slab3;
float slab4;
float slab5;

long LADDITIONAL_DEP_long;
long LClosing_Blalance_long;
float LPresent_Reading_float,LPrevious_Reading_float,max_meter_reading;//..,MobileNumber; //commented by Husain
float LOpening_Balance_float;
float LClosing_Balance_float,Ltax_float,Ltax_float1,Ltax_float2;
unsigned long fixed_charges;
char LConsumer_SC_No_bfr[15];
long Lmeter_change_units_consumed_long;
unsigned char Lstatus_id_c;
unsigned char typeofmeter,meterposition,metercover,mobilno;
char Observe_id_c[1+1];
float Lreading_value_float,Lmeter_constatnt_float,Lmeter_constant_float,Linterest_float,Lold_interest_float,Lothers_float=0,Lcredit_or_rebate_float,Larrears_float,average_long_considered;
float Lno_of_months_the_bill_has_to_be_issued_f;
float Lnofmbtiont;
long Laverage_consumption_long;
float Lbmd_float,Lpf_float,Laudit_arrears_float,Lbackbillarr_float,Ld_and_r_fee_float;
//long sancload;
float sancload;
float pf_penality=0;
float bmd_reading=0;
float pf=0;
float capSURCH = 0;
float penaltyOne=0;
float penaltyTwo=0;
float ltPFPenalty=0;
float excessLoad=0;
float excessLoadPenalty=0;
float load=0;
float sanctionedLoadHP=0;
unsigned int Ltariff_code_i;
char Ldue_date_bfr[11];
char Lttariff_code[7];

//extern unsigned bootload;

//struct all_data *eflash_consumer_ptr;
extern char Lstatus_id[];
extern char LPrevious_Reading[];
extern char LPresent_Reading[];
extern char LConsumer_SC_No[];
extern char LCheque_No[];
extern char LBank[];
extern char Lno_of_months_the_bill_has_to_be_issued[];
extern char Lunits[];
extern char Lreading_date[];
extern char Laverage_consumption [];
extern char Lpower_factor[]; 
extern char Lothers [];
//extern float Ltax_float;

//variable not required to modify
extern float energy_charges, credit;


//extern unsigned long fixed_charges;


struct tariff_table
	{
	char tdate[10];           /// 10
	char tariff_code[7];      //17
	char tariff [10];          //27
	char fec [7]; char tax[7];  //41
	char fc1u[7]; char fc1r[7]; //55 
	char fc2u[7]; char fc2r[7];  //69
	char fc3u[7]; char fc3r[7];  //83
	char fc4u[7]; char fc4r[7];  //87
	char sc1u[7]; char sc1r[7];  //91
	char sc2u[7]; char sc2r[7];	  //105
	char sc3u[7]; char sc3r[7];   //119
	char sc4u[7]; char sc4r[7];   // 133
	char sc5u[7]; char sc5r[7];   //147
	char sc6u[7]; char sc6r[7];   // 161
	char pf_code[7];              // 168
	char fc_red[7]; char ec_red[7]; // 182
	char fc_max[7]; char ec_max[7]; // 196
	char calculateFC;//1             // 197
	char calculateEC;//1             // 198
	char meterinstalled;//1            //199
	//..char crlf[2];                   //221
	} ;
//10,7,10,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,1,1,2  = 221 
//*********************************************************
//void load_parameter (char *, char *, unsigned char);
//unsigned char get_key_entry(unsigned char noch, char bfr[], unsigned char line_no, unsigned char cur_pos);

unsigned char main_menu_flag;
//struct tariff_table *temp_flash_struct;
char *currmetersts[] = {"      ","NORMAL","DOORLK","M N R ","D C   ","D I S "};
unsigned char process_meter_reading (char pr_bill)
{
unsigned char c1 = 0;
unsigned long temp;
char str[3],float_problem[15];
eflash_tariff_ptr = (struct tariff_table *) dflash_tariff;
//eflash_consumer_ptr =  (struct all_data *)temp_long;


data_base_err = 0;
//validbillprinted ='N';

//flash_consumer_ptr = (struct all_data *) dflash_consumer;
if (no_of_consumer_bytes == 0) 
	{
	lcd_display_line("no_of_consumer_byte0",1,0);
	c1 = 'Y';
	}
if (no_of_consumer_records == 0)
	{
	lcd_display_line("no_of_consumer_reco0",2,0);
	c1 = 'Y';
	}
if (LConsumer_record_length == 0)
	{
	lcd_display_line("Consumer_record_len0",3,0);
	c1 = 'Y';
	}
if (no_of_tariff_bytes == 0)
	{
	lcd_display_line("no_of_tariff_bytes0 ",4,0);
	c1 = 'Y';
	}
if (no_of_tariff_records == 0)
	{
	lcd_display_line("no_of_tariff_record0",4,0);
	c1 = 'Y';
	}
if (c1 == 'Y')
	{
	beep_buzzer_n (80);
	get_esc_key ();
	return 0xFF;
	}

Duty_closed = (char *)DUTYFLAG_ADDRESS;
if((unsigned char)*Duty_closed == 'Y'){
	lcd_clear_display();
	lcd_display_line("PLEASE LOAD CONSUMER",1,0);
	lcd_display_line("TO START DUTY",2,0);
	get_key();
	return 0xFF;

}

compute_report ();
lcd_clear_display ();
lcd64128size = '2';
lcd_display_line("RRno:               ",1,0);
//lcd_display_line(&eflash_consumer_ptr->Consumer_SC_No[0],1,0);

//lcd_line_cursor_position (1,5);
//lcd_display_string_noch (&eflash_consumer_ptr->Consumer_SC_No[0],15);
//strcpy(&kbd_input_bfr[0],&eflash_consumer_ptr->Consumer_SC_No[0]);
lcd64128size = '1';
sprintf(&temp_bfr[0], "Biled:%.4d",(int)noof_bills_rised); 
//temp_bfr[10] = ' ';
//lcd_display_line(&temp_bfr[0],7,0);
sprintf (&temp_bfr[10],"TOBil:%.4d",((int)no_of_consumer_records - (int)noof_bills_rised));
lcd_display_line(&temp_bfr[0],8,0);



lcd_display_line(&eflash_consumer_ptr->consumer_name[0],4,0);
lcd_display_line(&eflash_consumer_ptr->ADDRESS[0],5,0);
//lcd_display_line(&eflash_consumer_ptr->ADDRESS1[0],4,0);
strncpy(&temp_bfr[0],"TARIFF   :                     ",21);
strncpy(&temp_bfr[10],&eflash_consumer_ptr->Tariff[0],7);
lcd_display_line(&temp_bfr[0],6,0);
strncpy(&temp_bfr[0],"READ DATE:                     ",21);
strncpy(&temp_bfr[10],&eflash_consumer_ptr->reading_date[0],10);
lcd_display_line(&temp_bfr[0],7,0);

lcd64128size = '2';

//insert_key_next (UP_ARROW_KEY);
//insert_key_next (DOWN_ARROW_KEY);

strcpy(kbd_input_bfr,"               ");

c1 = get_RR_No (15,(char *)&kbd_input_bfr[0],1,5);// coolecting 10 digits, diplaying collected digits at cur pos 10
strncpy (&LConsumer_SC_No_bfr[0],(char *)&kbd_input_bfr[0],15);
temp = atol(&LTariff[0]);


if (c1 == 0xff) return c1;


if (check_data_rr_validity (&kbd_input_bfr[0]) == 'Y')
	{
	load_all_parameters_to_local_buffer ();
	
		
	LPrevious_Reading_float = atof(&LPrevious_Reading[0]); 
	Lreading_value_float = 0;
	Lpf_float = 0;
	Lothers_float = 0;
	c1 = Lunits[0];
	if (compute_tarrif () == 'N') return c1;
	if (pr_bill == 'Y' && c1 == 0xff)
		{
		lcd_clear_display ();	
		lcd_display_line("MeterReadingNotTaken",2,0);
		lcd_display_line("BillCannotBePrinted ",3,0);
		beep_buzzer ();
		get_esc_key ();
		return 1;
		}
	if (pr_bill != 'Y')
		{
		c1 = Lunits[0];
		if (c1 != 0xff)
			{
			lcd_clear_display();
			lcd_display_line("MeterRead.AlrdyTaken",2,0);
			lcd_display_line("To REprintBill   ",3,0);
			lcd_display_line("Press ENTER    or ESC",4,0);
			beep_buzzer ();
			key_pressed = get_key ();
			if (key_pressed == ENTER_KEY )
				{
					Lreading_value_float = atof(&Lunits[0]);
					LPresent_Reading_float = atof(&LPresent_Reading[0]);
					Lstatus_id_c=Lmeterreadingtype[0];
					print_bill (REPRINT);
					return 1;
				}
			else
				{
			//	get_esc_key ();
				return 1;
				}
			}
			
		if (eflash_tariff_ptr->meterinstalled == '1')
		{
	/*		
//		if (*flash_tariff_ptr->sc3r == '1')
			{
			lcd_display_line("1-N  2-SB 3-NR 4-WT",3,0);
            lcd_display_line("5-BY 6-PD 7-GB 8-NU",4,0);
			while (1)
				{
				key_pressed = get_key ();
				if (key_pressed  == ESC_KEY) return;
				if (key_pressed == ENTER_KEY || key_pressed == '1' || key_pressed == '2' || key_pressed == '3' || key_pressed == '4' || key_pressed == '5' || key_pressed == '6' || key_pressed == '7' || key_pressed == '8') break;
				}	 
			if(key_pressed == ENTER_KEY) key_pressed='1';
			*/
			Observe_id_c[0] = 1;
//			lcd_display_line("MeterSts.  1-NORMAL ",2,0);
//			lcd_display_line("2-DoorLck  3-M N R  ",3,0);
//			lcd_display_line("4-D C      5-DIS    ",4,0);
			typeofmeter = '0',meterposition='0',metercover='0';
			lcd_clear_display();
			lcd_display_line("Type of Meter",1,0);
			lcd_display_line("1-Static 2-Electro Mechanical",2,0);
			lcd_display_line("3-High Precision",3,0);
			key_pressed = get_key ();
			typeofmeter = key_pressed;
			//if (typeofmeter  != '1' || key_pressed  != '2' ) return;// 1;
			if (typeofmeter  != '1' && key_pressed  != '2' && key_pressed  != '3') return;// 1; // modified by Husain
			
		    lcd_clear_display();
			lcd_display_line("Where Meter installed?",1,0);
			lcd_display_line("1-Inside Premesis",2,0);
			lcd_display_line("2-Outside Premesis",3,0);
			key_pressed = get_key ();
			meterposition = key_pressed;
			//if (meterposition  != '1' || key_pressed  != '2' ) return 1;
			if (meterposition  != '1' && key_pressed  != '2' ) return;// 1; // modified by Husain
						
			lcd_clear_display();
			lcd_display_line("Meter Has Cover?",1,0);
			lcd_display_line("1-Meter Having Cover",2,0);
			lcd_display_line("2-No Cover",3,0);
			key_pressed = get_key ();
			metercover = key_pressed;
			//if (metercover  != '1' || metercover  != '2' ) return 1;
			if (metercover  != '1' && metercover  != '2' ) return 1; // modified by Husain
			//while (1)
			//{
					lcd_display_line("MobileNumber:       ",3,0);
					key_pressed = get_key_entry (20,'K',10,&LMobileNumber[0],4,11);
					if(key_pressed == 0x00 ){
						if(!strncmp(&LMobileNumber[0],"          ",10))
						{
							strncpy(&LMobileNumber[0],"000000000000",10);
						}
					}
						/*if (get_key_entry (20,'K',10,&LMobileNumber[0],4,11) != 0xff)
						{
	    		        }*/
	    		    else if(key_pressed == 0xFF) return 1;
					// coolecting 10 digits, diplaying collected digits at cur pos 10
					//..MobileNumber = atof (&LMobileNumber[0]);
			//}		

            lcd_clear_display();
			strncpy (&temp_bfr[0],"PREV MTR STS :",20);
			lcd_display_line(&temp_bfr[0],1,0);
			if (Ldl_or_mnr_prev_month[0] < '1' || Ldl_or_mnr_prev_month[0] > '5' ) Ldl_or_mnr_prev_month[0] = '0';
			strncpy (&temp_bfr[0],currmetersts[Ldl_or_mnr_prev_month[0] - '0'],20);
			lcd_display_line(&temp_bfr[0],2,0);
			key_pressed = get_key ();
		    if(key_pressed == ENTER_KEY) key_pressed='1';		
			lcd_clear_display();
			lcd_display_line("Enter Present Status",1,0);
			lcd_display_line("1-Nrml 2-DrLCk 3-MNR",2,0);
			lcd_display_line("4-D C 5-DIS 6 Idle   ",3,0);
			lcd_display_line("7-MB 8- MS 9 NV  ",4,0);
			while (1)
				{
				if (Ldl_or_mnr_prev_month[0] == '3' || Ldl_or_mnr_prev_month[0] == '4'  )
					{
					key_pressed = Ldl_or_mnr_prev_month[0];
					break;
					}
				key_pressed = get_key ();
				if (key_pressed  == ESC_KEY) return 1;
				if (key_pressed == ENTER_KEY || key_pressed == '1' || key_pressed == '2' || key_pressed == '3' || key_pressed == '4' || key_pressed == '5' || key_pressed == '6' || key_pressed == '7' || key_pressed == '8' || key_pressed == '9') break;
				}	 
			lcd_display_line("                    ",4,0);
			if(key_pressed == ENTER_KEY) key_pressed='1';
			Lstatus_id_c = key_pressed;
			if (Lstatus_id_c == '2' || Lstatus_id_c == '3' || Lstatus_id_c == '4' || Lstatus_id_c == '6'   || Lstatus_id_c == '7' || Lstatus_id_c == '8' || Lstatus_id_c == '9'  ) 
				{
				LPresent_Reading_float = LPrevious_Reading_float;
				strncpy (&LPresent_Reading[0],&LPrevious_Reading[0],7);
				}
			else
				{
				if ((unsigned char)*eflash_consumer_ptr->trivector == '1')
					{
					lcd_display_line("CKWH/LKWH :         ",2,0);
					blank_display_line (3);
					if ((unsigned char)get_key_entry (19,'K',6,&Lckwhlkwh[0],2,14) == 0xff) return;

					lcd_display_line("PF . . . .:         ",3,0);
					if ((unsigned char)get_key_entry (19,'K',5,&Lpower_factor[0],3,14) == 0xff) return;

					strncpy (&temp_bfr[0],"SL:      BMD:",13);
					estrncpy (&temp_bfr[3],eflash_consumer_ptr->Connected_Load,6);

					lcd_display_line(&temp_bfr[0],4,0);
					if ((unsigned char)get_key_entry (19,'K',5,&Lbmd[0],4,14) == 0xff) return;
					blank_display_line (4);
					}
				lcd_display_line("PrevMeter:     ",2,0);
				lcd_line_cursor_position (2,11);
				lcd_display_string_noch (&LPrevious_Reading [0],10);
				while (1)
					{
					lcd_display_line("PresMeter:       ",3,0);
						if (get_key_entry (20,'K',10,&LPresent_Reading[0],3,11) != 0xff)
						{
	    		        }
	    		    else return;
					// coolecting 10 digits, diplaying collected digits at cur pos 10
					LPresent_Reading_float = atof (&LPresent_Reading[0]);
					/*
					if(LPresent_Reading_float == 0 )
					{
					 	lcd_clear_display();
						lcd_display_line("Zero Reading Not Allowed",2,0);
						key_pressed = get_key ();
						return;
					} 
					*/
					Laverage_consumption_long = atol (&Laverage_consumption[0]);
					//	Lreading_value_float = LPresent_Reading_float - LPrevious_Reading_float;
					//Shiva 27.12.2009 Three lines are added
					Lreading_value_float = LPresent_Reading_float - LPrevious_Reading_float;
					/*
					if(Lstatus_id_c == '1' && Lreading_value_float == 0 )
					{
					 	lcd_clear_display();
						lcd_display_line("Zero Cons/Not allowed ",2,0);
						lcd_display_line("For Normal Status",3,0);
						key_pressed = get_key ();
						return;
					} 
					*/
				
					sprintf (&float_problem[0],"%8.2f ",Lreading_value_float);
					Lreading_value_float = atof(&float_problem[0]);

//					if(Ldl_or_mnr_prev_month[0]='2')
//						{
//						Lreading_value_float = ((LPresent_Reading_float - LPrevious_Reading_float)-Laverage_consumption_long);
//						}


					if (LPresent_Reading_float < LPrevious_Reading_float || Lreading_value_float > (3*Laverage_consumption_long))
						{
						// meter reading overflow is verified		
						beep_buzzer ();
						if (LPresent_Reading_float < LPrevious_Reading_float)
						{
							lcd_clear_display();
						    lcd_display_line("Meter Rotation.? ENTER/ESC",4,0);
							while ((c1 = get_key ()) != ESC_KEY && c1 != ENTER_KEY);
						    lcd_display_line("                     ",4,0);
							if(c1 == ESC_KEY)
							  return;
						}
						
						
						lcd_clear_display();
						lcd_display_line("ReadingOK.? ENTER/ESC",4,0);
						while ((c1 = get_key ()) != ESC_KEY && c1 != ENTER_KEY);
						lcd_display_line("                     ",4,0);
						if (c1 == ENTER_KEY)
							{
							if (LPresent_Reading_float < LPrevious_Reading_float)		
								{
								max_meter_reading = 1;
								//strncpy (&temp_bfr[0],&LPrevious_Reading[0],6);
								//temp_bfr[6] = '\0';
								i_temp1 = hhtpstrlen (&LPrevious_Reading[0],6);
								while (i_temp1-- > 0) max_meter_reading *= 10;
								Lreading_value_float += max_meter_reading;
								}
							break;
							}
						}
					else break;
					}
				}
			}
		else
			{
			lcd_display_line("                    ",2,0);
			lcd_display_line("1-N  2-SB 3-NR 4-WT ",2,0);
            lcd_display_line("5-BY 6-PD 7-GB 8-NU ",3,0);
			lcd_display_line("                    ",4,0);
			while (1)
				{
				key_pressed = get_key ();
				if (key_pressed  == ESC_KEY) return;
				if (key_pressed == ENTER_KEY || key_pressed == '1'|| key_pressed == '2' || key_pressed == '3' || key_pressed == '4' || key_pressed == '5' || key_pressed == '6' || key_pressed == '7' || key_pressed == '8') break;
				}	 
			if(key_pressed == ENTER_KEY) key_pressed='1';
			Observe_id_c[0] = key_pressed;
	
			Lstatus_id_c = '1';
			LPresent_Reading_float = 0;
			}
		}
	if (eflash_miscCESC_ptr->receipt_printing == 'Y')
		{
		lcd_display_line("Receipt    Enter/Esc",4,0);
		while (1)
			{
			receipt_required = 'N';
			key_pressed = get_key ();
			if (key_pressed == ESC_KEY) break;
			if (key_pressed == ENTER_KEY)
				{
				receipt_required = 'Y';
				break;
				}
			}
		}
	data_uploaded_flag = 'N';
//	if (compute_tarrif () == 'N') return;
	lcd_clear_display ();
	lcd_display_line("PrintBill...ENTER/ESC",1,0);
	//if (get_enter_esc_key () == ENTER_KEY )//commented by manjunath.
	key_pressed = get_key();
	if (key_pressed == ENTER_KEY)
		{
		print_bill ('N');
//		reset_cpu ();
		}
	}
else
	{
	lcd_display_line("Invalid RR No       ",2,0);
	beep_buzzer ();
	get_esc_key ();
	lcd_clear_display ();
	}
//return;
}
//******************************

void print_bill (unsigned char print_bill_flag)
{
unsigned char *ptr, c1;
unsigned long fc1r,fc2r,fc3r,fc4r;
//unsigned long ltempa;
float temp_energycharges;
float temp_fc,temp_ecdrlk,trl;
int barsize;
unsigned char bar_buf[25];
unsigned long tempunits,templong;
eflash_miscCESC_ptr = (struct miscdata_CESC *) dflash_miscCESC;

LClosing_Balance_float = 0;
credit = 0;
Lmeter_change_units_consumed_long =0;
Lmeter_constant_float = atof (&Lmeter_constant[0]);
LADDITIONAL_DEP_long=atol(&LADDITIONAL_DEP[0]);
count_bill++;
 

	

	
		
if (print_bill_flag == 'Y')
{
//if (*eflash_consumer_ptr->trivector == '1')
//Lreading_value_float = Lreading_value_float /Lmeter_constant_float ;
}
else
{
Lmeter_change_units_consumed_long = atol (&Lmeter_change_units_consumed[0]);
//CR4) Handle Meter Change Status-DONE- Status would be retuned as 0, which means meter_changes_units have been considered
if(Lmeter_change_units_consumed_long>0 )
{
	Lstatus_id_c == '0';
}

Lreading_value_float += Lmeter_change_units_consumed_long;
Lreading_value_float *= Lmeter_constant_float;
}
//CR2) For NORMAL CASE CONS=0 Change status to IDLE ->DONE 
// Normal Billed and Consumption =0 , Status will changed to IDLE after confirmation with MR 
if(Lstatus_id_c == '1' && Lreading_value_float==0 && (Ltariff_code_i != 1 || Ltariff_code_i != 111 ||Ltariff_code_i != 113 ) )
{
	
  	                        lcd_clear_display();
						    lcd_display_line("IDLE CASE(CONS=0).? ENTER/ESC",4,0);
							while ((c1 = get_key ()) != ESC_KEY && c1 != ENTER_KEY);
						    lcd_display_line("                     ",4,0);
							if(c1 == ESC_KEY)
							  return;	
                            Lstatus_id_c = '6';	
}

//Lmeter_change_units_consumed_long = atol (&Lmeter_change_units_consumed[0]); on apr17
//Lreading_value_float += Lmeter_change_units_consumed_long;  on apr 17
//Lmeter_constant_float = atof (&Lmeter_constant[0]); on apr 17
//if (Lstatus_id_c == '2' || Lstatus_id_c == '3' || Lstatus_id_c == '4')
correct_dateformat ();
Ltax_float = eatof (eflash_tariff_ptr-> tax);
if (print_bill_flag != 'Y')
if (Lstatus_id_c == '3' || Lstatus_id_c == '4' || Lstatus_id_c == '2'  || Lstatus_id_c == '7' || Lstatus_id_c == '8' || Lstatus_id_c == '9'  )
 {
 Laverage_consumption_long = atol (&Laverage_consumption[0]);
 Lreading_value_float += Laverage_consumption_long;
 }
//Lreading_value_float *= Lmeter_constant_float; on apr17
//if (eflash_tariff_ptr->calculateFC == '9' && eflash_tariff_ptr->calculateEC == '9')
// {
// if (Lreading_value_float > 18)
//  {
//  Lreading_value_float -= 18;
//  ltempa = 18;
//  }
// else ltempa = 0;
// }
sancload = atof (&LConnected_Load[0]);
sprintf (&temp_bfr[0],"%3.3f",sancload);
sancload = atof (&temp_bfr[0]);

if(Lreading_value_float <0 )
{
	Lreading_value_float=0;
}



if (eflash_tariff_ptr->calculateFC == '2' || eflash_tariff_ptr->calculateFC == '4'|| eflash_tariff_ptr->calculateFC == '6' || eflash_tariff_ptr->calculateFC == '8' || eflash_tariff_ptr->calculateFC == '9') calculate_fixed_charges ();
else
 {
 fixed_charges = atof(&Lfixed_charges[0]);
 }
if (eflash_tariff_ptr->calculateEC == '2' || eflash_tariff_ptr->calculateEC == '4'|| eflash_tariff_ptr->calculateEC == '6' || eflash_tariff_ptr->calculateEC == '8' || eflash_tariff_ptr->calculateEC == '9') calculate_energy_charges ();
//else
// {
// energy_charges = atof(&Lenergy_charges[0]);
// }
if ((eflash_tariff_ptr->calculateFC == '3' || eflash_tariff_ptr->calculateFC == '4')&& (eflash_tariff_ptr->calculateEC == '3' || eflash_tariff_ptr->calculateEC == '4'))
 {
 if (fixed_charges < energy_charges ) energy_charges = 0;
 else fixed_charges = 0;
 }
if ((eflash_tariff_ptr->calculateFC == '5' || eflash_tariff_ptr->calculateFC == '6') && (eflash_tariff_ptr->calculateEC == '5' || eflash_tariff_ptr->calculateEC == '6'))
 {
 if (fixed_charges > energy_charges ) energy_charges = 0;
 else fixed_charges = 0;
 }
if (eflash_tariff_ptr->calculateFC == '8' && eflash_tariff_ptr->calculateEC == '8')
 {
 // if calculated EC+FC is less than FC received from BACKN, then backen FC is taken and EC made 0
 // else calculated FC and EC taken
 if ((fixed_charges * atof (&Lno_of_months_the_bill_has_to_be_issued[0])+ energy_charges) < atof(&Lfixed_charges[0]) * atof (&Lno_of_months_the_bill_has_to_be_issued[0]))
  {
  fixed_charges = atof(&Lfixed_charges[0]);
  energy_charges = 0;
  }
  else
  {
         fixed_charges = fixed_charges * atof (&Lno_of_months_the_bill_has_to_be_issued[0]);
   Lothers_float = Lothers_float - (atof(&Lfixed_charges[0])* (atof (&Lno_of_months_the_bill_has_to_be_issued[0])-1));
  }
 }
if(Ltariff_code_i == 1) fixed_charges = 0;

// CR1) For DISs cases -FR should be asked(CONS=FR-IR) -> DONE-> Average calculation is done for 2-DL,3-MNR,4-DC,7-MB,8-MS,9-NV

if (Lstatus_id_c == '2' || Lstatus_id_c == '3' || Lstatus_id_c == '4'   || Lstatus_id_c == '7' || Lstatus_id_c == '8' || Lstatus_id_c == '9' )
 {

  Laverage_consumption_long = atol (&Laverage_consumption[0]);
// if (print_bill_flag != 'Y')
 Lreading_value_float = Laverage_consumption_long/atol(eflash_consumer_ptr->no_of_months_the_bill_has_to_be_issued);
 
 //CR6) LT1 and LT4 cases AVG consu should be 0
 if(Ltariff_code_i == 1 ||  Ltariff_code_i == 2 || Ltariff_code_i == 111 || Ltariff_code_i == 112 || Ltariff_code_i == 113 || Ltariff_code_i == 114  )
 {
	 Lreading_value_float =0;
 } 	   
 average_long_considered = Lreading_value_float;
 strncpy (&Lno_of_months_the_bill_has_to_be_issued[0],"00001",5);
 calculate_energy_charges ();
 estrncpy (&Ldl_or_mnr_prev_month[0],eflash_consumer_ptr->dl_or_mnr_prev_month,1);
 Ldl_or_mnr_prev_month[1] = '\0';
 //after doing this again multyply so that it will not effect other billing
 Lreading_value_float = Lreading_value_float*atol(eflash_consumer_ptr->no_of_months_the_bill_has_to_be_issued);
 }
 
/*
if (Lstatus_id_c == '1' &&  Ldl_or_mnr_prev_month[0] == '2')
 {
 calculate_energy_charges ();
 temp_ecdrlk = energy_charges;
 temp_fc = Lreading_value_float;
 strncpy (&Lno_of_months_the_bill_has_to_be_issued[0],"00001",5);
 Laverage_consumption_long = atol (&Laverage_consumption[0]);
 Lreading_value_float = Laverage_consumption_long;
 calculate_energy_charges ();
 estrncpy (&Lno_of_months_the_bill_has_to_be_issued[0],eflash_consumer_ptr->no_of_months_the_bill_has_to_be_issued,5);
 Ldl_or_mnr_prev_month[1] = '\0';
 dladjamt = energy_charges * (atof (&Lno_of_months_the_bill_has_to_be_issued[0])-1);
 energy_charges = temp_ecdrlk;// - dladjamt;
 
// energy_charges = temp_ecdrlk - energy_charges;
 Lreading_value_float = temp_fc;
 }
 */


fueladjcharges=0;
fueladjunits=0;

if(Lreading_value_float / Lno_of_months_the_bill_has_to_be_issued_f < 0)
{
  	fueladjunits =0;
}
else
{
 fueladjunits =  Lreading_value_float / Lno_of_months_the_bill_has_to_be_issued_f;
}
fueladjcharges = fueladjunits * 0.02;


 
sprintf (&LBank[0],"%7.2f",fueladjcharges);
LBank[7] = '\0'; 
Larrears_float = atof (&Larrears[0]);
Linterest_float = atof(&Linterest[0]);
Lold_interest_float = atof (&Lold_interest[0]);
Lcredit_or_rebate_float = atof (&Lcredit_or_rebate[0]);
Laudit_arrears_float = atof(&Laudit_arrears[0]);
Lbackbillarr_float = atof (&Lbackbillarr[0]);
Ld_and_r_fee_float = atof (&Ld_and_r_fee[0]);
//load_tax ();
Ltax_float = eatof (eflash_tariff_ptr-> tax);
dl_adj_amount =0;
Ldl_adj_float = atof(&Ldl_adj[0]);
//CR8) DL->IDLE,DL->DISS (ADJ amount should be effected)-DONE
if (( Lstatus_id_c == '1' ||   Lstatus_id_c == '5' ||  Lstatus_id_c == '6' ||  Lstatus_id_c == '9' ) &&  Ldl_or_mnr_prev_month[0] == '2')
{
 	dl_adj_amount = Ldl_adj_float;
}
else
{
	 dl_adj_amount=0;
} 
//CR7) for DL case also DL_ADJ_GIVEN is getting deducted-DONE-for DL case dl_adj_amount should be zero
if(Lstatus_id_c == '2')
{
	 dl_adj_amount=0;
}
 
if (*eflash_consumer_ptr->trivector == '1')
 {
 fc1r = eatol (eflash_tariff_ptr-> fc1r);
 fc2r = eatol (eflash_tariff_ptr-> fc2r);
 fc3r = eatol (eflash_tariff_ptr-> fc3r);
 fc4r = eatol (eflash_tariff_ptr-> fc4r);
 
 Lpf_float = atof (&Lpower_factor[0]);
 //if (Lpf_float > 0.3 && Lpf_float < 0.85) Lpf_float = 0.3;
 //else if (Lpf_float < 0.3) Lpf_float = ((0.85 - Lpf_float) * 100 * 0.02);
 if (Lpf_float < 0.85 && Lpf_float >= 0.7) Lpf_float = ((0.85 - Lpf_float) * 100 * 0.02);
 //else if (Lpf_float > 0.3) Lpf_float = 0.3;
 else if (Lpf_float >= 0.85) Lpf_float = 0;
 else if (Lpf_float < 0.7) Lpf_float = 0.3;
 //else Lpf_float = 0;
 Lpf_float *= Lreading_value_float;
 Lbmd_float = (atof (&Lbmd[0]) * Lmeter_constant_float);
 if ((Lbmd_float - (float)sancload)>0)
  {
  tempf = fixed_charges;
  if(Ltariff_code_i == 173 || Ltariff_code_i == 171)
   {
   //tempf = fixed_charges;
   if(sancload >= 29.84 && sancload <50)
    {
    sancload = (float)Lbmd_float - (float)sancload;
    temp_fc = sancload * fc1r;
    //fixed_charges = sancload * fc1r;
    Lbmd_float = temp_fc * 1.5 ;
    }
   else if (sancload >=50)
    {
    sancload = (float)Lbmd_float - (float) sancload;
    temp_fc = sancload * fc2r;
    //fixed_charges = sancload   * fc2r;
    Lbmd_float = temp_fc * 1.5;
    }
   fixed_charges = tempf;
   }
  if(Ltariff_code_i == 5 || Ltariff_code_i == 10)
   {
   sancload = (float)Lbmd_float - (float) sancload;
   temp_fc = sancload * fc2r;
   //fixed_charges = sancload  * fc2r ;//atol (eflash_tariff_ptr-> fc1r);
   Lbmd_float = temp_fc * 1.5;
   //fixed_charges = tempf;
   }
  }
 else Lbmd_float = 0;
 }
else
 {
 Lpf_float = atof (&Lpower_factor[0]);
 Lpf_float *= Lreading_value_float;
 Lbmd_float = 0;
 }

capSURCH = 0;	
ltPFPenalty=0;
excessLoadPenalty=0;

if(Ltariff_code_i != 1 && Ltariff_code_i != 2  && Ltariff_code_i != 111  )
  {
		 pf=0;
		 capSURCH = 0;	
	     ltPFPenalty=0;
		 excessLoadPenalty=0;
	     load = near_qrt(sancload,2);
		 Lpf_float = atof (&Lpower_factor[0]);
		 pf  = Lpf_float;
         if (pf > 0 &&  pf < 0.85 )
		 {
		        penaltyOne = 0.85 -  pf;
			    //penaltyTwo = floorf( (penaltyOne * 2) * 100) / 100; 
				penaltyTwo =  (penaltyOne * 2) * 100 / 100;
			    if(penaltyTwo <= 0.30)
				    ltPFPenalty= rnd(Lreading_value_float * penaltyTwo);
			    else
				    ltPFPenalty  = rnd(Lreading_value_float  *  0.3);   
		       
		 }
       	 if( load >= 40)
		 {
			ltPFPenalty=0;  
            lcd_clear_display();
	        lcd_display_line("ENTER POWER FACTOR:",2,0);
            if (get_key_entry (20,'K',10,&LPresent_PF[0],3,11) != 0xff)
			{
	    	}
	    	else return;
			// coolecting 10 digits, diplaying collected digits at cur pos 10
			LPresent_PF_float = atof (&LPresent_PF[0]); 
			Lpf_float = atof (&Lpower_factor[0]);
            if(LPresent_PF_float == 0  )
			
             pf  = Lpf_float;
			   else
			 pf = LPresent_PF_float; 
		
		    if (pf > 0 &&  pf < 0.85 )
		    {
		        penaltyOne = 0.85 -  pf;
			    //penaltyTwo = floorf( (penaltyOne * 2) * 100) / 100; 
				penaltyTwo =  (penaltyOne * 2) * 100 / 100;
			    if(penaltyTwo <= 0.30)
				    ltPFPenalty= rnd(Lreading_value_float * penaltyTwo);
			    else
				    ltPFPenalty  = rnd(Lreading_value_float  *  0.3);   
		       
			}
			else
			{
				ltPFPenalty=0;
			}
			  
			  
			lcd_clear_display();
	        lcd_display_line("ENTER REC BMD:",2,0);
            if (get_key_entry (20,'K',10,&LRecorded_BMD[0],3,11) != 0xff)
			{
	    	}
	    	else return;
			
			recordedBMD = atof (&LRecorded_BMD[0]); 
			
			//sanctionedLoadKW=keb.sancKW+(keb.sancHP * 0.746);
			excessLoad= (recordedBMD * Lmeter_constant_float )- sancload;
			
			if(excessLoad > 0)
			{
				sanctionedLoadHP=(excessLoad/0.746);  
				
				excessLoadPenalty=  (sanctionedLoadHP * 35 * 2) * 100/100;
			}
			else
			{
				excessLoadPenalty=0;
			}
			excessLoadPenalty = rnd(excessLoadPenalty);
            //clrscr();
            //gotoxy(1,1);
			//printf("PF PEN %4.2f",ltPFPenalty);
			//gotoxy(2,1);
			//printf("EXLoad PEN %4.2f",excessLoadPenalty);
            //get_key();

		  	capSURCH = 	ltPFPenalty+excessLoadPenalty;							   
         }
         lcd_clear_display();
		 strncpy (&temp_bfr[0],"PF Pen: ",10);
		 lcd_display_line (&temp_bfr[0],1,0);
		 sprintf (&LltPFPenalty[0],"%10.2f",ltPFPenalty);
		 lcd_display_line (&LltPFPenalty[0],2,0);
 
		 strncpy (&temp_bfr[0],"BMD PEN: ",10);
		 lcd_display_line (&temp_bfr[0],2,0);
		 sprintf (&LexcessLoadPenalty[0],"%9.2f",excessLoadPenalty);
		 lcd_display_line (&LexcessLoadPenalty[0],2,0);
 
			 
		 key_pressed = get_key ();
		 if (key_pressed == ESC_KEY) return;
		
   	} 
 

Ltax_float = 0.06;
if (Ltariff_code_i == 56 || Ltariff_code_i == 59 || Ltariff_code_i == 64 || Ltariff_code_i == 66 ||
 Ltariff_code_i == 105 || Ltariff_code_i == 106 || Ltariff_code_i == 111 || Ltariff_code_i == 112|| Ltariff_code_i == 113 || Ltariff_code_i == 114 || Ltariff_code_i == 115 || Ltariff_code_i == 116  )
{
	Ltax_float = 0.00;
}

/* 
if (ecred>0) Ltax_float1 *= (Lpf_float+Lbmd_float+fixed_charges+Linterest_float-Lold_interest_float+Ld_and_r_fee_float-credit+ltPFPenalty+excessLoadPenalty);
else Ltax_float1 *=         (Lpf_float+Lbmd_float+fixed_charges+Linterest_float-Lold_interest_float+Ld_and_r_fee_float+ltPFPenalty+excessLoadPenalty); 
*/

Ltax_float *=         energy_charges+fueladjcharges;



// free Lighting FL case for LT2A I
if (Ltariff_code_i == 54 || Ltariff_code_i == 544 || Ltariff_code_i == 504  )
 {
 if ((Lreading_value_float/Lno_of_months_the_bill_has_to_be_issued_f)< 200)
  {
  credit = energy_charges;
  }
 else
  {
  calculate_energy_charges ();
  temp_energycharges = energy_charges;
  //Ltax_float = eatof (eflash_tariff_ptr-> tax);
  //Ltax_float *= (energy_charges+fixed_charges+Linterest_float-Lold_interest_float+Lpf_float);
  Ltax_float = 0.06;
  Ltax_float *= energy_charges;
  energy_charges = 0;
  tempunits = Lreading_value_float;
  Lreading_value_float /= Lno_of_months_the_bill_has_to_be_issued_f;  
  if (Lreading_value_float < 280) energy_charges += ((Lreading_value_float - 200)  * 0.1);
  else if (Lreading_value_float < 400) energy_charges += ((Lreading_value_float - 280)  * 1.82) + (80 * 0.1);
  else energy_charges += ((Lreading_value_float - 400)  * 4.6) + (120 * 1.82) + (80 * 0.1);
  energy_charges *= Lno_of_months_the_bill_has_to_be_issued_f;
  credit = temp_energycharges - energy_charges;
  energy_charges = temp_energycharges;
  Lreading_value_float = tempunits;
  }
 credit += fixed_charges;
 }
// free Lighting FL case for LT2A II
if (Ltariff_code_i == 204 )
 {
 if ((Lreading_value_float/Lno_of_months_the_bill_has_to_be_issued_f)< 200)
  {
  credit = energy_charges;
  }
 else
  {
  calculate_energy_charges ();
  temp_energycharges = energy_charges;
  //Ltax_float = eatof (eflash_tariff_ptr-> tax);
  //Ltax_float *= (energy_charges+fixed_charges+Linterest_float-Lold_interest_float+Lpf_float);
  
  Ltax_float = 0.06;
  Ltax_float *= energy_charges;
  
  energy_charges = 0;
  tempunits = Lreading_value_float;
  Lreading_value_float /= Lno_of_months_the_bill_has_to_be_issued_f;  
  if (Lreading_value_float < 280) energy_charges += ((Lreading_value_float - 200)  * 0.1);
  else if (Lreading_value_float < 400) energy_charges += ((Lreading_value_float - 280)  * 1.82) + (80 * 0.1);
  else energy_charges += ((Lreading_value_float - 400)  * 4.6) + (120 * 1.82) + (80 * 0.1);
  energy_charges *= Lno_of_months_the_bill_has_to_be_issued_f;
  credit = temp_energycharges - energy_charges;
  energy_charges = temp_energycharges;
  Lreading_value_float = tempunits;
  }
 credit += fixed_charges;
 }
 

 // free Lighting FL case for LT3A II
if (Ltariff_code_i == 104 )
 {
 if ((Lreading_value_float/Lno_of_months_the_bill_has_to_be_issued_f)< 200)
  {
  credit = energy_charges;
  }
 else
  {
  calculate_energy_charges ();
  temp_energycharges = energy_charges;
  //Ltax_float = eatof (eflash_tariff_ptr-> tax);
  //Ltax_float *= (energy_charges+fixed_charges+Linterest_float-Lold_interest_float+Lpf_float);
  
  Ltax_float = 0.06;
  Ltax_float *= energy_charges;
  
  energy_charges = 0;
  tempunits = Lreading_value_float;
  Lreading_value_float /= Lno_of_months_the_bill_has_to_be_issued_f;  
  if (Lreading_value_float < 280) energy_charges += ((Lreading_value_float - 200)  * 0.1);
  else if (Lreading_value_float < 400) energy_charges += ((Lreading_value_float - 280)  * 1.82) + (80 * 0.1);
  else energy_charges += ((Lreading_value_float - 400)  * 4.6) + (120 * 1.82) + (80 * 0.1);
  energy_charges *= Lno_of_months_the_bill_has_to_be_issued_f;
  credit = temp_energycharges - energy_charges;
  energy_charges = temp_energycharges;
  Lreading_value_float = tempunits;
  }
 credit += fixed_charges;
 }
if (Ltariff_code_i == 59)
{
  if (Lreading_value_float < 100)
   credit = Lreading_value_float * 0.35;
  else
   credit = 35;
}
 
 
 
if (Ltariff_code_i == 179)
 {
 calculate_energy_charges ();
 temp_energycharges = energy_charges;
// load_tax ();
 //Ltax_float = eatof (eflash_tariff_ptr-> tax);
 //Ltax_float *= (fixed_charges+energy_charges+Linterest_float-Lold_interest_float+Lpf_float);
  Ltax_float = 0.06;
  Ltax_float *= energy_charges;
  
 energy_charges = 0;
 tempunits = Lreading_value_float;
 Lreading_value_float /= Lno_of_months_the_bill_has_to_be_issued_f;  
 energy_charges = Lreading_value_float * 1.25;
 energy_charges *= Lno_of_months_the_bill_has_to_be_issued_f;
 //credit = fixed_charges+temp_energycharges - energy_charges;
 credit = temp_energycharges - energy_charges;
 energy_charges = temp_energycharges;
 Lreading_value_float = tempunits;
// fixed_charges = 0;
 }
 
 
if (Ltariff_code_i == 101 )
{
	
  //calculate_energy_charges ();
  temp_energycharges = energy_charges;	
  credit = temp_energycharges * 0.20;
  Ltax_float = 0.06;
  Ltax_float *= energy_charges;	
	 
}

if (Ltariff_code_i == 2 || Ltariff_code_i == 1 )
 {
 //temp_energycharges = energy_charges ;
     energy_charges = 0;

if ( (Lreading_value_float / Lno_of_months_the_bill_has_to_be_issued_f) <= 18 )
 {
    //energy_charges = Lreading_value_float * 3.55;//3.48;krishna told to change 3.48 into 3.55    .....15/04/09
    //energy_charges = Lreading_value_float * 4.18;//3.55 to 4.18 tariff change   .....01/12/09
    //energy_charges = Lreading_value_float * 4.38;//4.18 to 4.38 tariff change   .....07/01/11
    //energy_charges = Lreading_value_float * 4.64;//4.18 to 4.38 tariff change   .....07/01/11
	//energy_charges = Lreading_value_float * 4.86;//4.64 to 4.86 tariff change   .....may2013
	energy_charges = Lreading_value_float * 5.11;//4.64 to 4.86 tariff change   .....may2014
    fixed_charges = 0;

	Lcredit_or_rebate_float = energy_charges;
	 Ltax_float = 0;
    if ( energy_charges < 30)
    {
      energy_charges = 30 * Lno_of_months_the_bill_has_to_be_issued_f;
      Lcredit_or_rebate_float=energy_charges;
      Ltax_float = 0;
    }
	
	// CR1) For DISs cases -FR should be asked(CONS=FR-IR) -> DONE-> Average calculation is done for 2-DL,3-MNR,4-DC,7-MB,8-MS,9-NV
   if (Lstatus_id_c == '2' )
   {
 	 energy_charges = 0;
   }
    	
 }
  else
  {
   //new 22/04/2009
   
   
  fixed_charges = 15;
  trl = Lreading_value_float;
  Lreading_value_float = Lreading_value_float / Lno_of_months_the_bill_has_to_be_issued_f;
     energy_charges =78;
   
  if ( Lreading_value_float > 18 && Lreading_value_float <= 30 )
        {
            //energy_charges = Lreading_value_float1*2;
            //energy_charges = Lreading_value_float1*2.10;
            energy_charges = Lreading_value_float*2.60;
 
        }
        else if ( Lreading_value_float > 30 && Lreading_value_float <= 100 )
           {
            //energy_charges =  60 +(Lreading_value_float1-30) * 3;
            //energy_charges =  63 +(Lreading_value_float1-30) * 3.10;
            energy_charges =  78 +(Lreading_value_float-30) * 3.70;
        }
        else if ( Lreading_value_float > 100 && Lreading_value_float <= 200 )
        {
            //energy_charges = 270 + (Lreading_value_float1 - 100) * 4;
            //energy_charges = 280 + (Lreading_value_float1 - 100) * 4.20;
            energy_charges = 78+ 259 + (Lreading_value_float - 100) * 4.95;
        }
        else if ( Lreading_value_float > 200 )
        {
            //energy_charges = 270 + 400 + (Lreading_value_float1 - 200) * 4.5;
            energy_charges = 78 + 259 + 495 + (Lreading_value_float - 200) * 5.75;
        } 
  energy_charges = energy_charges * Lno_of_months_the_bill_has_to_be_issued_f;
  Lcredit_or_rebate_float=0;
  Lreading_value_float = Lreading_value_float * Lno_of_months_the_bill_has_to_be_issued_f;
  Lreading_value_float = trl;
  
  if (Lstatus_id_c == '2'  )
  {
 	 energy_charges = 0;
  }
  
  }


  /*{//old one
    trl = Lreading_value_float;
    Lreading_value_float = Lreading_value_float / Lno_of_months_the_bill_has_to_be_issued_f;
    energy_charges =62.40;
    Lreading_value_float= Lreading_value_float - 18;
    if ( Lreading_value_float <= 30 )
     {
  energy_charges = 62.40 + Lreading_value_float * 1.65;
  }
       if ( Lreading_value_float > 30 && Lreading_value_float <= 100 )
    {
  energy_charges =  (Lreading_value_float) * 2.75;
  }
        if ( Lreading_value_float > 100 && Lreading_value_float <= 200 )
    {
  energy_charges = 62.40 + 275.0 + (Lreading_value_float - 100) * 3.50;
  }
        if ( Lreading_value_float > 200 && Lreading_value_float <= 300 )
    {
  energy_charges = 62.40 + 625.0 + (Lreading_value_float - 200) * 4.00;
  }
  if ( Lreading_value_float > 300 && Lreading_value_float <= 400 )
    {
  energy_charges = 62.40 + 1025.0 + (Lreading_value_float - 300) * 4.25;
  }
        if ( Lreading_value_float > 400 )
    {
  energy_charges = 62.40 + 1450.0 + (Lreading_value_float - 400) * 4.50;
  }
        energy_charges = energy_charges * Lno_of_months_the_bill_has_to_be_issued_f;
  credit=0;
  Lreading_value_float = Lreading_value_float * Lno_of_months_the_bill_has_to_be_issued_f;
  Lreading_value_float = trl;
 
      }*/
   }
 
   if(Ltariff_code_i == 1)
   {
   credit = 0;
   }

/* 
if (Ltariff_code_i == 21)
{
 Ltax_float = 0.05;
 energy_charges=0;
    if ((float)sancload/0.746 < 67)
 {
   fixed_charges = 160 * (float)sancload * atof (&Lno_of_months_the_bill_has_to_be_issued[0]);
      //energy_charges = (Lreading_value_float * 6.00);
      //energy_charges = (Lreading_value_float * 6.75); // Tariff changed from 01.12.2009
   energy_charges = (Lreading_value_float * 8.20);
   if ( energy_charges < fixed_charges)
    {
     energy_charges = 0;
  //fixed_charges = 0 ;
  Ltax_float *=         (Lpf_float+Lbmd_float+energy_charges+fixed_charges+Linterest_float-Lold_interest_float+Ld_and_r_fee_float);
  }
   else
    {
      fixed_charges = 0;
   Ltax_float *=         (Lpf_float+Lbmd_float+energy_charges+fixed_charges+Linterest_float-Lold_interest_float+Ld_and_r_fee_float);     
       }
     }
    else
     {
     fixed_charges = 50 * (float)sancload ;
   //energy_charges = Lreading_value_float * 6.0 ;
  //energy_charges = Lreading_value_float * 7.0 ; //Tariff changed from 01.12.2009
  energy_charges = Lreading_value_float * 8.2 ; //Tariff changed from 01.12.2009
  Ltax_float *=         (Lpf_float+Lbmd_float+energy_charges+fixed_charges+Linterest_float-Lold_interest_float+Ld_and_r_fee_float);
     }
}
 
*/ 
 

/*if (Ltariff_code_i == 2)
 {
 energy_charges=0;
 energy_charges = (Lreading_value_float * 3.55);
 if (energy_charges <= (30 * Lno_of_months_the_bill_has_to_be_issued_f))
  {
  energy_charges = 30 * Lno_of_months_the_bill_has_to_be_issued_f;
  credit = 30 * Lno_of_months_the_bill_has_to_be_issued_f;
  Ltax_float = 0;
  fixed_charges = 0;
  }
 else if (energy_charges > (30 * Lno_of_months_the_bill_has_to_be_issued_f) && energy_charges < (18 * 3.55) * Lno_of_months_the_bill_has_to_be_issued_f)
  {
  credit = energy_charges;
  Ltax_float = 0;
  fixed_charges = 0;
  }
 }
 
*/
 
if (Ltariff_code_i == 178)
 {
	 
 calculate_energy_charges ();
 temp_energycharges = energy_charges;
// load_tax ();
 //Ltax_float = eatof (eflash_tariff_ptr-> tax);
 //Ltax_float *= (fixed_charges+energy_charges+Linterest_float-Lold_interest_float+Lpf_float);
  Ltax_float = 0.06;
  Ltax_float *= energy_charges;
  
 energy_charges = 0;
 tempunits = Lreading_value_float;
 Lreading_value_float /= Lno_of_months_the_bill_has_to_be_issued_f;  
 energy_charges = Lreading_value_float * 1.25;
 energy_charges *= Lno_of_months_the_bill_has_to_be_issued_f;
 //credit = fixed_charges+temp_energycharges - energy_charges;
 credit = temp_energycharges - energy_charges;
 energy_charges = temp_energycharges;
 Lreading_value_float = tempunits;
// fixed_charges = 0;
 }


LClosing_Balance_float = Ltax_float+energy_charges+fixed_charges+fueladjcharges+Lothers_float+Linterest_float-Lcredit_or_rebate_float+Larrears_float+Lpf_float+Lbmd_float+Laudit_arrears_float+Ld_and_r_fee_float+Lbackbillarr_float-dl_adj_amount+ltPFPenalty+excessLoadPenalty;

//LClosing_Balance_float = Ltax_float+energy_charges+fixed_charges+Lothers_float+Linterest_float-Lcredit_or_rebate_float+Larrears_float+Lpf_float+Lbmd_float+Laudit_arrears_float+Ld_and_r_fee_float+Lbackbillarr_float;

 

 
LClosing_Balance_float = LClosing_Balance_float-credit;

if(Ltax_float+energy_charges+fixed_charges+Lothers_float+Linterest_float-Lcredit_or_rebate_float >= 500000 && (Ltariff_code_i == 5 || Ltariff_code_i == 52|| Ltariff_code_i == 54|| Ltariff_code_i == 56|| Ltariff_code_i == 58 || Ltariff_code_i == 51 || Ltariff_code_i == 204 || Ltariff_code_i == 50 || Ltariff_code_i == 57 || Ltariff_code_i == 59 || Ltariff_code_i == 53 || Ltariff_code_i == 6 || Ltariff_code_i == 62|| Ltariff_code_i == 64 || Ltariff_code_i == 69 || Ltariff_code_i == 63 || Ltariff_code_i == 65 ))
{					
					 	lcd_clear_display();
						lcd_display_line("Abnormal bill Not Allowed",2,0);
						key_pressed = get_key ();
						return;
} 
 
if(Ltax_float+energy_charges+fixed_charges+Lothers_float+Linterest_float-Lcredit_or_rebate_float >= 5000000 )
{					
					 	lcd_clear_display();
						lcd_display_line("Abnormal bill Not Allowed",2,0);
						key_pressed = get_key ();
						return;
} 

//0.50P adjustment
LClosing_Balance_float_ba=LClosing_Balance_float;
if (LClosing_Balance_float > 0) LClosing_Balance_float += 0.5;
else LClosing_Balance_float -= 0.5;
LClosing_Blalance_long = LClosing_Balance_float;
LClosing_Balance_float = LClosing_Blalance_long ;

net_round_diff=0;
//CR5) Round Off to be added in one more column
net_round_diff = LClosing_Balance_float_ba-LClosing_Blalance_long; 
 
sprintf (&LCheque_No[0],"%7.2f",net_round_diff);
LCheque_No[10] = '\0';

/*
if(net_round_diff > 0 )
{
	
	sprintf (&LCheque_No[0],"%7.2f",net_round_diff);
    LCheque_No[10] = '\0';
  
} else
{
	estrncpy (&LCheque_No[0],0,10);
    LCheque_No[10] = '\0';

} 
*/


///////////// end or CR5 
if (print_bill_flag != 'D')
 {
 lcd_clear_display();
 strncpy (&temp_bfr[0],"PresRdng: ",10);
 sprintf (&LPresent_Reading[0],"%10.2f",LPresent_Reading_float);
 strncpy(&temp_bfr[10],&LPresent_Reading[0],10);//&eflash_consumer_ptr->Present_Reading[0],10);
 lcd_display_line (&temp_bfr[0],1,0);
 
 strncpy (&temp_bfr[0],"EnerChrg: ",10);
 sprintf (&Lec[0],"%9.2f",energy_charges);
 strncpy(&temp_bfr[10],&Lec[0],10);//&eflash_consumer_ptr->ec[0],10);
 lcd_display_line (&temp_bfr[0],2,0);
 

 sprintf (&Lunits[0],"%10.2f",Lreading_value_float);
 strncpy (&temp_bfr[0],"Units   : ",10);
 strncpy(&temp_bfr[10],&Lunits[0],10);//&eflash_consumer_ptr->units[0],10);
 lcd_display_line (&temp_bfr[0],3,0);
 
 sprintf (&Lnetamountdue[0],"%9.0f",LClosing_Balance_float);
 strncpy (&temp_bfr[0],"Amount  : ",10);
 strncpy(&temp_bfr[10],&Lnetamountdue[0],10);//&eflash_consumer_ptr->netamountdue[0],10);
 lcd_display_line (&temp_bfr[0],4,0);
 key_pressed = get_key ();
 if (key_pressed == ESC_KEY) return;
 }
 
//if(count_bill==2)
//print_lf (1);
process_due_date ();
Lbill_status[0] = 0xfd;  // bill print status 0xfd for just started printing
if (print_bill_flag == 'N' && !data_base_err)
 {
 write_to_external_flash_n (eflash_consumer_ptr->bill_status,(char *)&Lbill_status[0],1);
 store_all_parameters ();
// Long_bill_no++;
 }
 
//else
// {
// lcd_display_line("Store data Y/N      ",4,0);
// if (get_enter_esc_key () == ENTER_KEY)
//  {
//  store_all_parameters ();
//  Long_bill_no++;
//  }
// }
blank_print_bfr ();
 
Lprinted_stationery = 'Y';
 
if (print_heading == 'Y')
 {
 blank_print_bfr ();
 estrncpy(&print_line_bfr[0],eflash_miscCESC_ptr->heading1,24);
 epsondh = 'Y';
 print_line_double_width (); //printing dowuble heigh & width
 epsondh = 'N';
 blank_print_bfr ();
 estrncpy(&print_line_bfr[0],eflash_miscCESC_ptr->heading2,24);
 print_line_double_height (0);
 blank_print_bfr ();
 estrncpy(&print_line_bfr[0],eflash_miscCESC_ptr->heading3,24);
 print_line (0);
 print_lf(1);
 }


newrollflag = 1;
//  OLD - BILL
if(newrollflag == 0 )
{
	
					if(Lprinted_stationery == 'Y')
					{
					print_lf (4);
					}
					if (Lprinted_stationery != 'Y') print_dotted_line ();
					if (Llogo_y_i != 0)
					 {
					// print_logo ();
					 print_lf(2);
					 print_dotted_line ();
					 }
					//print_dotted_line ();
					//print_lf(1);
					blank_print_bfr ();
					estrncpy(&print_line_bfr[4],eflash_miscCESC_ptr->Division_code,24);
					print_line_double_width ();
					if(Lprinted_stationery == 'Y')
					{
					print_lf (2);
					}
	
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"R R NO   :",10);
					strncpy (&print_line_bfr[27],&LConsumer_SC_No[0],15);
					print_line_double_height ();
 
					//blank_print_bfr ();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"LEDGER NO: ",10);
					//estrncpy (&print_line_bfr[15],eflash_consumer_ptr->ledger_no,5);
					//print_line_bfr[20] = '/';
					//estrncpy (&print_line_bfr[21],eflash_consumer_ptr->folio_no,5);
					//print_line (0);
 
					//print_lf(2);
 
					blank_print_bfr ();
					blank_print_bfr2();
					estrncpy (&print_line_bfr[27],eflash_consumer_ptr->CONSUMERCODE,14);
					print_line (0);
 
					print_lf(1);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"METREADER:",10);
					//strncpy (&print_line_bfr[27],eflash_consumer_ptr->Receipt_No,10);
					estrncpy (&print_line_bfr[27],(char *)eflash_other_ptr->UNIT_slno,8);
					print_line (0);

 
					//print_lf(1);
 

					blank_print_bfr ();
					blank_print_bfr2();
					//strncpy(&print_line_bfr[0],"Name:",5);
					//strncpy (&print_line_bfr[0],&Lconsumer_name[0],20);
					print_lf (2);
					estrncpy (&print_line_bfr[2],eflash_consumer_ptr->consumer_name,20);
					print_line (0);
 
					//print_line (0);
 
					blank_print_bfr ();
					blank_print_bfr2();
 
					estrncpy (&print_line_bfr[2],eflash_consumer_ptr->ADDRESS,24);
					print_line (0);
 
					blank_print_bfr ();
					blank_print_bfr2();
					estrncpy (&print_line_bfr[2],eflash_consumer_ptr->ADDRESS1,24);
					print_line (0);

					print_lf (1); 

					//print_lf(2);
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"TARIFF   :",10);
					estrncpy(&print_line_bfr[27],eflash_tariff_ptr->tariff,10);
					print_line (0);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"SANC LOAD:",10);
					strncpy (&print_line_bfr[27],&LConnected_Load[0],7);
					print_line (0);
 
					print_lf (2);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//sprintf (&print_line_bfr[0],"BILL PERIOD: %2.2f Months",(float)Lno_of_months_the_bill_has_to_be_issued_f);
					//sprintf (&print_line_bfr[27],"%2.2f Months",(float)Lno_of_months_the_bill_has_to_be_issued_f);
					estrncpy(&print_line_bfr[27],eflash_consumer_ptr->no_of_months_the_bill_has_to_be_issued,5);
					print_line (0);
 
 
 
					blank_print_bfr ();
					blank_print_bfr2();
 
					//rtc_from_pc[03]; // month
					//rtc_from_pc[8];  // year
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[24],"RDNGDATE:",9);
 
					//process_due_date ();
 
					print_line_bfr[27] = Lreading_date[3]; //rtc_from_pc[0]; // day
					print_line_bfr[28] = Lreading_date[4];//rtc_from_pc[1]; // day
					print_line_bfr[29] = '/';
					print_line_bfr[30] = Lreading_date[0];//rtc_from_pc[3]; // month
					print_line_bfr[31] = Lreading_date[1];//rtc_from_pc[4]; // month
					print_line_bfr[32] = '/';
					print_line_bfr[33] = Lreading_date[6];//rtc_from_pc[8]; // year
					print_line_bfr[34] = Lreading_date[7];//rtc_from_pc[9]; // year
					print_line_bfr[35] = Lreading_date[8];
					print_line_bfr[36] = Lreading_date[9];
					print_line (0);
 
					//read_rtc_data ();
					//blank_print_bfr ();
					//convert_BCD_to_ascii (rtc_local_bfr[2],&print_line_bfr[8]);
					//print_line_bfr[10] = ':';
					//convert_BCD_to_ascii (rtc_local_bfr[1],&print_line_bfr[11]);
 
					//print_line (0);
 
					blank_print_bfr ();
					blank_print_bfr2();
					sprintf(&print_line_bfr[27],"%.9ld",Long_bill_no); 
					print_line (0);

 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"METREADER:",10);
					strncpy (&print_line_bfr[27],"-------",10);
					print_line (0);


 
					print_lf (2);
 
 
 
 
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"PRESREADING :",13);
					templong=LPresent_Reading_float;
					if(templong == LPresent_Reading_float)
					sprintf (&print_line_bfr[27],"%ld",(long)templong);
					else sprintf (&print_line_bfr[27],"%10.2f",LPresent_Reading_float);
					print_line (0);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"PREVREADING :",13);
					templong=LPrevious_Reading_float;
					if(templong == LPrevious_Reading_float)
					sprintf (&print_line_bfr[27],"%ld",(long)templong);
					else sprintf (&print_line_bfr[27],"%10.2f",LPrevious_Reading_float);
					print_line (0);
 

					blank_print_bfr ();
					blank_print_bfr2();
					if (Lprinted_stationery != 'Y') strncpy(&print_line_bfr[0],"METER CONST :",13);
					sprintf (&print_line_bfr[25],"%6.2f",Lmeter_constant_float);
					print_line (0);
 

					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"UNITSCNSUMED:",13);
					templong=Lreading_value_float;
					if(templong == Lreading_value_float)
					sprintf (&print_line_bfr[27],"%ld",(long)templong);
					else
					sprintf (&print_line_bfr[27],"%10.2f",Lreading_value_float);
					print_line (0);
 
					blank_print_bfr ();
					blank_print_bfr2();
					if (Lprinted_stationery != 'Y')
					strncpy(&print_line_bfr[0],"AVG CONS:",10);
					strncpy (&print_line_bfr[27],&Laverage_consumption[0],8);
					print_line (0);
 

					blank_print_bfr ();
					blank_print_bfr2();
					//strncpy(&print_line_bfr[0],"BMD:     PEN:",13);
					strncpy(&print_line_bfr[20],&Lbmd[0],5);
					//sprintf (&print_line_bfr[15],"%9.2f",Lbmd_float);
					print_line (0);
 

					blank_print_bfr ();
					blank_print_bfr2();
					//strncpy(&print_line_bfr[0],"PF:      PEN:",13);
					strncpy(&print_line_bfr[25],&Lpower_factor[0],7);
					//sprintf (&print_line_bfr[15],"%9.2f",Lpf_float);
					print_line (0);
 
					print_lf(5);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y') strncpy(&print_line_bfr[0],"FIXEDCHARGES:",13);
					sprintf (&print_line_bfr[20],"%9.2f",(float)fixed_charges);
					print_line (0);
 
					print_lf(4);
					//print_lf (2);
 
					blank_print_bfr ();
					blank_print_bfr2();
					if (Lprinted_stationery != 'Y') strncpy(&print_line_bfr[0],"ENRGYCHARGES:",13);
					sprintf (&print_line_bfr[20],"%9.2f",energy_charges);
					print_line (0);


					strncpy((char *)&bar_buf[0],eflash_consumer_ptr->Consumer_SC_No,15);
					bar_buf[15] = '-';
					sprintf((char *)&bar_buf[16], "%7.0f",LClosing_Balance_float);
					bar_buf[23] = '\0';
					print_barcode ((unsigned char *)&bar_buf[0],CODE128,'Y',sizeof(bar_buf)); 



 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y') strncpy(&print_line_bfr[0],"REBT   :",13);
					//sprintf(&print_line_bfr[15],"%9.2f",Lcredit_or_rebate_float);
					//sprintf(&print_line_bfr[15],"%9.2f",credit);
					sprintf(&print_line_bfr[25],"%9.2f",(Lcredit_or_rebate_float));
					print_line (0);
 
 
 
					// PF Penalty
					blank_print_bfr ();
					blank_print_bfr2();
					sprintf (&print_line_bfr[25],"%9.2f",Lpf_float);
					print_line (0);
 
 
 
					// Ex Load/MD Penalty
					blank_print_bfr ();
					blank_print_bfr2();
					sprintf (&print_line_bfr[25],"%9.2f",Lbmd_float);
					print_line (0);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y') strncpy(&print_line_bfr[0],"INTEREST    :",13);
					sprintf(&print_line_bfr[25],"%9.2f",Linterest_float-Lold_interest_float);
					print_line (0);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y') strncpy(&print_line_bfr[0],"OTHERS      :",13);
					sprintf (&print_line_bfr[25],"+FAC %3.2f",Lothers_float +Ld_and_r_fee_float+fueladjcharges);
					print_line (0);
 

					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y') strncpy(&print_line_bfr[0],"TAX         :",13);
					sprintf(&print_line_bfr[25],"%9.2f",Ltax_float);
					print_line (0);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y') strncpy(&print_line_bfr[0],"SUB TOTAL   :",13);
					sprintf(&print_line_bfr[25],"%9.2f",(LClosing_Balance_float -(Larrears_float+Lold_interest_float+Ltax_float+dl_adj_amount)));
					//sprintf(&print_line_bfr[25],"%9.2f",(LClosing_Balance_float -(Larrears_float+Lold_interest_float+Ltax_float)));
					print_line (0);
 
					print_lf(1);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y') strncpy(&print_line_bfr[0],"ARREARS     :",13);
					sprintf (&print_line_bfr[25],"%9.2f",Larrears_float+Lold_interest_float);
					print_line (0);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y') strncpy(&print_line_bfr[0],"Credit&Adj   :",13);
					//sprintf(&print_line_bfr[15],"%9.2f",Lcredit_or_rebate_float);
					//sprintf(&print_line_bfr[15],"%9.2f",credit);
					sprintf(&print_line_bfr[15],"(-)DL ADJ %9.2f",dl_adj_amount);
					print_line (0);
					//credit =  dl_adj_amount;
					// For GOK subsidy
					print_lf(1);
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"NETAMOUNTDUE:",13);
					sprintf (&print_line_bfr[25],"%9.0f",LClosing_Balance_float);
					print_line_double_height ();
 
					blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"DUEDATE :",9);
					strncpy(&print_line_bfr[25],&Ldue_date_bfr[0],10);
					print_line (0);
 

					//if (*eflash_consumer_ptr->trivector != '1') print_lf(2);
 
					//if (*eflash_consumer_ptr->trivector == '1')
					// {
					// strncpy(&print_line_bfr[0],"PF:      PEN:",13);
					// strncpy(&print_line_bfr[3],&Lpower_factor[0],5);
					// sprintf (&print_line_bfr[15],"%9.2f",Lpf_float);
					// print_line (0);
 
					// strncpy(&print_line_bfr[0],"BMD:     PEN:",13);
					// strncpy(&print_line_bfr[3],&Lbmd[0],5);
					// sprintf (&print_line_bfr[15],"%9.2f",Lbmd_float);
					// print_line (0);
					// }
					//else print_lf(2);
 
					//else if (Lpf_float > 0 )//&& *eflash_consumer_ptr->trivector != '1')
					// {
					// sprintf (&print_line_bfr[0],"POWER FACT  :  %9.2f",Lpf_float);
					// print_line (0);
					// }
					//else if (Lprinted_stationery != 'Y') print_lf(3);
					//else print_lf(3);
					//if(*eflash_consumer_ptr->trivector != '1' && Lpf_float == 0 )
					//print_lf(3);
 
					//if (Lprinted_stationery != 'Y')
					//{
					//print_lf_vb(1);
					//print_dotted_line ();
					//print_lf_vb(1);
					//}
 


 
					if(LADDITIONAL_DEP_long >0 )
					{
					  blank_print_bfr (); 
					  strncpy(&print_line_bfr[0],"ADD DEP:",9);
					  sprintf (&print_line_bfr[24],"%9.2f",(float)LADDITIONAL_DEP_long);
					  print_line (0);
 
					  blank_print_bfr ();
					  blank_print_bfr2();
					  sprintf (&print_line_bfr[24],"PAYABLE AMOUNT:%9.2f",(float)LADDITIONAL_DEP_long+LClosing_Balance_float);
					  print_line (0);
					  print_lf(1);
					}
					else
					{
					print_lf(4);
 
					} 
 
					print_lf(1);
}
else
{	
	
					//-------------------- line 1
	                //blank_print_bfr ();
					//blank_print_bfr2();
					//sitecodei = atoi (&Lsitecode[0]);
					sprintf (&print_line_bfr[25],"%ld",sitecodei);
					//print_line (0);
					
					
					sitecodei = atoi (&Lsitecode[0]);
				    
					blank_print_bfr ();
					blank_print_bfr2();
					strncpy (&print_line_bfr[25],eflash_consumer_ptr->sitecode,4);
					if(sitecodei == 3210 )
					{
						strncpy (&print_line_bfr[25],"MADDUR-1",15);
						 
					}
					else if(sitecodei == 3213)
					{
						strncpy (&print_line_bfr[25],"BHARTINAGARA",15);
					}
					else if(sitecodei == 3220)
					{
						strncpy (&print_line_bfr[25],"MADDUR-2",15);
					}
					
					else if(sitecodei == 3230)
					{
						strncpy (&print_line_bfr[25],"MALAVALLI-1",15);
					}
					
					else if(sitecodei == 3240)
					{
						strncpy (&print_line_bfr[25],"MALAVALLI-2",15);
					}
					else if(sitecodei == 3243)
					{
						strncpy (&print_line_bfr[25],"HALAGUR",15);
					}
					else if(sitecodei == 3242)
					{
						strncpy (&print_line_bfr[25],"KIRUGAVALU",15);
					}
					else if(sitecodei == 2140)
					{
						strncpy (&print_line_bfr[25],"SANTHEMARAHALLI",15);
					}
					else if(sitecodei == 2133)
					{
						strncpy (&print_line_bfr[25],"HARADANAHALLI",15);
					}
					else if(sitecodei == 2130)
					{
						strncpy (&print_line_bfr[25],"GUNDULUPET",15);
					}
					else if(sitecodei == 2140)
					{
						strncpy (&print_line_bfr[25],"BEGUR",15);
					}
					else if(sitecodei == 2120)
					{
						strncpy (&print_line_bfr[25],"HUNSUR",15);
					}
					else if(sitecodei == 2250)
					{
						strncpy (&print_line_bfr[25],"BILIKERE",15);
					}
					else if(sitecodei == 2220)
					{
						strncpy (&print_line_bfr[25],"HD- KOTE",15);
					}
					else if(sitecodei == 2230)
					{
						strncpy (&print_line_bfr[25],"KR-NAGAR",15);
					}
					else if(sitecodei == 2240)
					{
						strncpy (&print_line_bfr[25],"PERIYAPATTANA",15);
					}
					else if(sitecodei == 2251)
					{
						strncpy (&print_line_bfr[25],"BETTADAPURA",15);
					}
					else if(sitecodei == 2260)
					{
						strncpy (&print_line_bfr[25],"SARAGURU",15);
					}
					else if(sitecodei == 2432)
					{
						strncpy (&print_line_bfr[25],"SUNTIKOPPA",15);
					}
					else if(sitecodei == 2433)
					{
						strncpy (&print_line_bfr[25],"SOMWARPET",15);
					}
					else if(sitecodei == 2412)
					{
						strncpy (&print_line_bfr[25],"MURNAD",15);
					}
					else if(sitecodei == 2431)
					{
						strncpy (&print_line_bfr[25],"KUSHALNAGAR",15);
					}
					else if(sitecodei == 2434)
					{
						strncpy (&print_line_bfr[25],"SHANIVARSANTHE",15);
					}
					else if(sitecodei == 2411)
					{
						strncpy (&print_line_bfr[25],"MADIKERI",15);
					}
					else if(sitecodei == 2422)
					{
						strncpy (&print_line_bfr[25],"VIRAJPET",15);
					}
					else if(sitecodei == 2421)
					{
						strncpy (&print_line_bfr[25],"GONNIKOPAL",15);
					}
					else if(sitecodei == 3123)
					{
						strncpy (&print_line_bfr[25],"KOTHATHI1",15);
					}
					else if(sitecodei == 3134)
					{
						strncpy (&print_line_bfr[25],"KERAGODU",15);
					}
					else if(sitecodei == 3411)
					{
						strncpy (&print_line_bfr[25],"NAGAMANGALA",15);
					}
					else if(sitecodei == 1115)
					{
						strncpy (&print_line_bfr[25],"VARUNA",15);
					}
					else if(sitecodei == 1123)
					{
						strncpy (&print_line_bfr[25],"KADAKOLA",15);
					}
					else if(sitecodei == 1136)
					{
						strncpy (&print_line_bfr[25],"SIDDALINGAPURA",15);
					}
					else if(sitecodei == 1223)
					{
						strncpy (&print_line_bfr[25],"YELWALA",15);
					}
					else if(sitecodei == 1243)
					{
						strncpy (&print_line_bfr[25],"JAYAPURA",15);
					}
					else if(sitecodei == 1310)
					{
						strncpy (&print_line_bfr[25],"NANJUNGUD-URBAN",15);
					}
					else if(sitecodei == 1320)
					{
						strncpy (&print_line_bfr[25],"NANJUNGUD-RURAL",15);
					}
					else if(sitecodei == 1330)
					{
						strncpy (&print_line_bfr[25],"T-NARASHIPURA",15);
					}
					else if(sitecodei == 1340)
					{
						strncpy (&print_line_bfr[25],"BANNUR",15);
					}
					else if(sitecodei == 2110)
					{
						strncpy (&print_line_bfr[25],"CHNAGARA-URBAN",15);
					}
					else if(sitecodei == 2310)
					{
						strncpy (&print_line_bfr[25],"KOLLEGALA",15);
					}
					else if(sitecodei == 2320)
					{
						strncpy (&print_line_bfr[25],"HANUR",15);
					}
					else if(sitecodei == 2330)
					{
						strncpy (&print_line_bfr[25],"YALANDUR",15);
					}
					else if(sitecodei == 3310)
					{
						strncpy (&print_line_bfr[25],"PANDAVAPURA",15);
					}
					else if(sitecodei == 3315)
					{
						strncpy (&print_line_bfr[25],"MELUKOTE",15);
					}
					else if(sitecodei == 3320)
					{
						strncpy (&print_line_bfr[25],"KR PET-1",15);
					}
					else if(sitecodei == 3340)
					{
						strncpy (&print_line_bfr[25],"KR PET-2",15);
					}
					else if(sitecodei == 3344)
					{
						strncpy (&print_line_bfr[25],"KIKKERI",15);
					}
					else if(sitecodei == 3330)
					{
						strncpy (&print_line_bfr[25],"SR PATNA",15);
					}
					else if(sitecodei == 3334)
					{
						strncpy (&print_line_bfr[25],"ARAKERE",15);
					}
					else if(sitecodei == 4120)
					{
						strncpy (&print_line_bfr[25],"HASAN RURAL-1",15);
					}
					else if(sitecodei == 4160)
					{
						strncpy (&print_line_bfr[25],"HASAN RURAL-2",15);
					}
					else if(sitecodei == 4130)
					{
						strncpy (&print_line_bfr[25],"BELUR",15);
					}
					else if(sitecodei == 4132)
					{
						strncpy (&print_line_bfr[25],"HALEBEEDU",15);
					}
					else if(sitecodei == 4133)
					{
						strncpy (&print_line_bfr[25],"AREHALLY",15);
					}
					else if(sitecodei == 4140)
					{
						strncpy (&print_line_bfr[25],"ALUR",15);
					}
					else if(sitecodei == 4150)
					{
						strncpy (&print_line_bfr[25],"SAKALESHPURA",15);
					}
					else if(sitecodei == 4214)
					{
						strncpy (&print_line_bfr[25],"S.BELAGOLA",15);
					}
					else if(sitecodei == 4210)
					{
						strncpy (&print_line_bfr[25],"CR PATANA",15);
					}
					else if(sitecodei == 4220)
					{
						strncpy (&print_line_bfr[25],"NUGGEHALLI",15);
					}
					else if(sitecodei == 4223)
					{
						strncpy (&print_line_bfr[25],"HIRISAVE",15);
					}
					else if(sitecodei == 4310)
					{
						strncpy (&print_line_bfr[25],"HN PURA",15);
					}
					else if(sitecodei == 4320)
					{
						strncpy (&print_line_bfr[25],"ARAKALUGUDU",15);
					}
					else if(sitecodei == 4330)
					{
						strncpy (&print_line_bfr[25],"SALIGRAMA",15);
					}
					else if(sitecodei == 4340)
					{
						strncpy (&print_line_bfr[25],"HANGARAHALLI",15);
					}
					else if(sitecodei == 4350)
					{
						strncpy (&print_line_bfr[25],"RAMANANTHAPURA",15);
					}
					else if(sitecodei == 4351)
					{
						strncpy (&print_line_bfr[25],"KONANUR",15);
					}
					else if(sitecodei == 4410)
					{
						strncpy (&print_line_bfr[25],"ARASIKERE",15);
					}
					else if(sitecodei == 4420)
					{
						strncpy (&print_line_bfr[25],"BANAVARA",15);
					}
					else if(sitecodei == 4423)
					{
						strncpy (&print_line_bfr[25],"DM KURKE",15);
					}
					else if(sitecodei == 4430)
					{
						strncpy (&print_line_bfr[25],"GANDASI",15);
					}
					
					
					
					
					//else if(sitecodei == 2411)
				//	{
						
				//	}
					 
					print_line (0);
					print_lf(1);
                    print_lf (1);					
					
							
					print_lf (3);
					
					
					
					// ------------------ line 2
					blank_print_bfr ();
					blank_print_bfr2();
				    estrncpy (&print_line_bfr[2],eflash_consumer_ptr->sitecode,4);
					strncpy (&print_line_bfr[27],&LConsumer_SC_No[0],15);
					print_line_double_height ();
					
					
					
					
					// ---------------------line3
					blank_print_bfr ();
					blank_print_bfr2();
					estrncpy (&print_line_bfr[18],eflash_consumer_ptr->consumer_name,15);
					print_line (0);
					//print_lf(1);
				
					//----------------------- line 4
					blank_print_bfr ();
					blank_print_bfr2();
 					estrncpy (&print_line_bfr[2],eflash_consumer_ptr->ADDRESS,24);
					print_line (0);
					blank_print_bfr ();
					blank_print_bfr2();
					estrncpy (&print_line_bfr[2],eflash_consumer_ptr->ADDRESS1,24);
					print_line (0);

					print_lf(1);
					print_lf(1);
					print_lf(1);
					
					// ----------------------- line 5
					blank_print_bfr ();
					blank_print_bfr2();
				    estrncpy (&print_line_bfr[2],eflash_consumer_ptr->ledger_no,5);
					print_line_bfr[5] = '/';
					estrncpy (&print_line_bfr[6],eflash_consumer_ptr->folio_no,5);
					estrncpy (&print_line_bfr[18],(char *)eflash_other_ptr->UNIT_slno,8);
					estrncpy (&print_line_bfr[34],eflash_consumer_ptr->mrcode,5);
					print_line (0);
					print_lf(1);
					print_lf(1);
					
					//----------------------- line 6				
					blank_print_bfr ();
					blank_print_bfr2();
				    sprintf(&print_line_bfr[2],"%.9ld",Long_bill_no);
					print_line_bfr[18] = Lreading_date[3]; //rtc_from_pc[0]; // day
					print_line_bfr[19] = Lreading_date[4];//rtc_from_pc[1]; // day
					print_line_bfr[20] = '/';
					print_line_bfr[21] = Lreading_date[0];//rtc_from_pc[3]; // month
					print_line_bfr[22] = Lreading_date[1];//rtc_from_pc[4]; // month
					print_line_bfr[23] = '/';
					print_line_bfr[24] = Lreading_date[6];//rtc_from_pc[8]; // year
					print_line_bfr[25] = Lreading_date[7];//rtc_from_pc[9]; // year
					print_line_bfr[26] = Lreading_date[8];
					print_line_bfr[27] = Lreading_date[9];
					
					strncpy(&print_line_bfr[34],&Ldue_date_bfr[0],10);
					print_line (0);
					print_lf(1);
					print_lf(1);
					print_lf(1);
					
					///////// line 7 --------------------------
					blank_print_bfr ();
					blank_print_bfr2();
				    estrncpy(&print_line_bfr[2],eflash_tariff_ptr->tariff,10);
					strncpy (&print_line_bfr[18],&LConnected_Load[0],7);
					estrncpy (&print_line_bfr[34],eflash_consumer_ptr->meterno,10);
					print_line (0);
					
					print_lf(1);
					print_lf(1);
					print_lf(1);
					
					///////// line 8 --------------------------
					blank_print_bfr ();
					blank_print_bfr2();
				    templong=LPresent_Reading_float;
					if(templong == LPresent_Reading_float)
					sprintf (&print_line_bfr[2],"%ld",(long)templong);
					else sprintf (&print_line_bfr[2],"%10.2f",LPresent_Reading_float);
					
					templong=LPrevious_Reading_float;
					if(templong == LPrevious_Reading_float)
					sprintf (&print_line_bfr[18],"%ld",(long)templong);
					else sprintf (&print_line_bfr[18],"%10.2f",LPrevious_Reading_float);
					if (Ldl_or_mnr_prev_month[0] == '1' )
					{
					strncpy (&print_line_bfr[26],"(NRM)",5);
					} else
					if ( Ldl_or_mnr_prev_month[0] == '2' )
					{
					strncpy (&print_line_bfr[26],"(D L)",5);
					} else
					if ( Ldl_or_mnr_prev_month[0] == '3' )
					{
					strncpy (&print_line_bfr[26],"(MNR)",5);
					} else
					if ( Ldl_or_mnr_prev_month[0] == '4' )
					{
					strncpy (&print_line_bfr[26],"(D C)",5);
					} else
					if ( Ldl_or_mnr_prev_month[0] == '5' )
					{
					strncpy (&print_line_bfr[26],"(DIS)",5);
					} else
					if ( Ldl_or_mnr_prev_month[0] == '6' )
					{
					strncpy (&print_line_bfr[26],"(IDL)",5);
					} else
					if ( Ldl_or_mnr_prev_month[0] == '7' )
					{
					strncpy (&print_line_bfr[26],"(MB) ",5);
					} else
					if ( Ldl_or_mnr_prev_month[0] == '8' )
					{
					strncpy (&print_line_bfr[26],"(MS) ",5);
					} else
					if ( Ldl_or_mnr_prev_month[0] == '9' )
					{
					strncpy (&print_line_bfr[26],"(NV) ",5);
					} 
					
					
					templong=Lreading_value_float;
					if(templong == Lreading_value_float)
					sprintf (&print_line_bfr[34],"%ld",(long)templong);
					else
					sprintf (&print_line_bfr[34],"%10.2f",Lreading_value_float);
					print_line (0);
					print_lf(1);
					print_lf(1);
					print_lf(1);
					
					//////---------- line 9
					
					blank_print_bfr ();
					blank_print_bfr2();
				    sprintf (&print_line_bfr[2],"%6.2f",Lmeter_constant_float);
					strncpy(&print_line_bfr[18],&Lbmd[0],5);
					strncpy(&print_line_bfr[34],&Lpower_factor[0],7);
					print_line (0);
					print_lf(1);
					print_lf(1);
					
					//////---------- line 10
					blank_print_bfr ();
					blank_print_bfr2();
					if (Lstatus_id_c == '2' || Lstatus_id_c == '3' || Lstatus_id_c == '4' || Lstatus_id_c == '5'  || Lstatus_id_c == '7' || Lstatus_id_c == '8' || Lstatus_id_c == '9' )
					{
				    	sprintf (&print_line_bfr[2],"%10.2f",average_long_considered);
					}
					else
					{
						strncpy (&print_line_bfr[2],&Laverage_consumption[0],8);
					}
						
					//// printing meter status
					if (Lstatus_id_c == '1')
					{
					strncpy (&print_line_bfr[18]," NORMAL",8);
					} else
					if (Lstatus_id_c == '2')
					{
					strncpy (&print_line_bfr[18],"DOORLOCK",8);
					} else
					if (Lstatus_id_c == '3')
					{
					strncpy (&print_line_bfr[18],"  M N R",8);
					} else
					if (Lstatus_id_c == '4')
					{
					strncpy (&print_line_bfr[18],"    D C",8);
					} else
					if (Lstatus_id_c == '5')
					{
					strncpy (&print_line_bfr[18],"    DIS",8);
					} else
					if (Lstatus_id_c == '6')
					{
					strncpy (&print_line_bfr[18],"    IDLE",8);
					} else
					if (Lstatus_id_c == '7')
					{
					strncpy (&print_line_bfr[18],"MTR BROKEN",10);
					} else
					if (Lstatus_id_c == '8')
					{
					strncpy (&print_line_bfr[18],"MTR STICKY",10);
					} else
					if (Lstatus_id_c == '9')
					{
					strncpy (&print_line_bfr[18],"NOT VISIBLE",11);
					} 
					
					estrncpy(&print_line_bfr[34],eflash_consumer_ptr->no_of_months_the_bill_has_to_be_issued,5);
					estrncpy(&print_line_bfr[36],"months(s)",9);
					
					print_line (0);
					print_lf(1);
					print_lf(1);
					print_lf(1);
					
					/////////////// -line 11
					blank_print_bfr ();
					blank_print_bfr2();
				    sprintf (&print_line_bfr[2],"%9.2f",(float)fixed_charges);
					sprintf (&print_line_bfr[18],"%9.2f",energy_charges);
					sprintf (&print_line_bfr[34],"%9.2f",ltPFPenalty);
					print_line (0);
					print_lf(1);
					print_lf(1);
					print_lf(1);
					
									
					////////////  line 12
					blank_print_bfr ();
					blank_print_bfr2();
				    sprintf (&print_line_bfr[2],"%9.2f",excessLoadPenalty);
					sprintf(&print_line_bfr[18],"%9.2f",(Lcredit_or_rebate_float));
					sprintf (&print_line_bfr[34],"%9.2f",Lothers_float +Ld_and_r_fee_float+fueladjcharges);
					print_line (0);
					print_lf(1);
					print_lf(1);
					
					////////////  line 13
					blank_print_bfr ();
					blank_print_bfr2();
					sprintf (&print_line_bfr[2],"%9.2f",Larrears_float+Lold_interest_float);
					sprintf(&print_line_bfr[18],"%9.2f",Linterest_float-Lold_interest_float);
					sprintf(&print_line_bfr[34],"%9.2f",Ltax_float);
					print_line (0);
					print_lf(1);
					print_lf(1);
					
					////////////  line 14
					blank_print_bfr ();
					blank_print_bfr2();
				    //sprintf(&print_line_bfr[2],"%9.2f",(Lcredit_or_rebate_float));
					sprintf(&print_line_bfr[2],"DL AD%7.2f",(dl_adj_amount));
					strncpy(&print_line_bfr[18],"NETAMOUNTDUE:",13);
					sprintf(&print_line_bfr[34],"%9.2f",credit);
					print_line (0);
					//print_lf(1);
					//print_lf(1);
					
				    blank_print_bfr ();
					blank_print_bfr2();
					//if (Lprinted_stationery != 'Y')
					//strncpy(&print_line_bfr[0],"NETAMOUNTDUE:",13);
					sprintf (&print_line_bfr[18],"%9.0f",LClosing_Balance_float);
					print_line_double_height ();
				
					
					strncpy((char *)&bar_buf[0],eflash_consumer_ptr->Consumer_SC_No,15);
					bar_buf[15] = '-';
					sprintf((char *)&bar_buf[16], "%7.0f",LClosing_Balance_float);
					bar_buf[23] = '\0';
					print_barcode ((unsigned char *)&bar_buf[0],CODE128,'Y',sizeof(bar_buf)); 
					
					print_lf(1);
					//print_lf(1);

	
}					
 
 
//print_lf (4);
//if(!(noof_bills_rised%2))
//if((noof_bills_rised%3))
//if((noof_bills_rised%4))
//print_lf (1);
//if((noof_bills_rised%2)||(noof_bills_rised%3))
 
//if((count_bill==2) ||(count_bill==3)||(count_bill==4)||(count_bill==6)||(count_bill==8)||(count_bill==13) )//||(count_bill==5)
if((count_bill!=3) &&(count_bill!=5)&&(count_bill!=8))
{
//print_lf (1);
 
}
//if((count_bill==4)||(count_bill==8)||(count_bill==12))
//print_lf (1);
//print_lf (1);
//if(noof_bills_rised!=0)
if((!(noof_bills_rised%4))||(!(noof_bills_rised%5)))
print_lf (1);
//if(noof_bills_rised==4)
//if(noof_bills_rised!=0)
print_lf (1);
 

 

//if (print_bill_flag == 'D') return;
if (print_bill_flag == 'N' && !data_base_err)
 {
// store_all_parameters ();
 Long_bill_no++;
 flash_overwrite = 'Y';
 Lbill_status[0] = 0xfc;  // bill print status 0xfd for just started printing
 write_to_external_flash_n (eflash_consumer_ptr->bill_status,(char *)&Lbill_status[0],1);
 validbillprinted = 'Y';
 }
fill_space_for_unused_parameters();
//else
// {
// lcd_display_line("Store data Y/N      ",4,0);
// if (get_enter_esc_key () == ENTER_KEY)
//  {
//  store_all_parameters ();
//  Long_bill_no++;
//  }
// }
print_lf (1); 
if (motor_err) eflash_consumer_ptr--;
//if (PRINTERTYPE == f638 || PRINTERTYPE == f628) initialise_f638_f628 ();
 
print_lf(1);
print_lf(1);
print_lf(1);

 

} 

void fill_space_for_unused_parameters()
{
	int size ;
	
	
	size = sizeof(eflash_consumer_ptr->billed_date);
	if((unsigned char)*eflash_consumer_ptr->billed_date == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->billed_date,size);
	}
	
	size = sizeof(eflash_consumer_ptr->time);
	if((unsigned char)*eflash_consumer_ptr->time == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->time,size);
	}
	
	size = sizeof(eflash_consumer_ptr->Present_Reading);
	if((unsigned char)*eflash_consumer_ptr->Present_Reading == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->Present_Reading,size);
	}
	size = sizeof(eflash_consumer_ptr->ckwhlkwh);
	if((unsigned char)*eflash_consumer_ptr->ckwhlkwh == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->ckwhlkwh,size);
	}
	size = sizeof(eflash_consumer_ptr->units);
	if((unsigned char)*eflash_consumer_ptr->units == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->units,size);
	}
	size = sizeof(eflash_consumer_ptr->ec);
	if((unsigned char)*eflash_consumer_ptr->ec == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->ec,size);
	}
	size = sizeof(eflash_consumer_ptr->pf_reading);
	if((unsigned char)*eflash_consumer_ptr->pf_reading == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->pf_reading,size);
	}
	size = sizeof(eflash_consumer_ptr->bmd_reading);
	if((unsigned char)*eflash_consumer_ptr->bmd_reading == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->bmd_reading,size);
	}
	size = sizeof(eflash_consumer_ptr->pf_penality);
	if((unsigned char)*eflash_consumer_ptr->pf_penality == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->pf_penality,size);
	}
	size = sizeof(eflash_consumer_ptr->bmd_penality);
	if((unsigned char)*eflash_consumer_ptr->bmd_penality == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->bmd_penality,size);
	}
	size = sizeof(eflash_consumer_ptr->netamountdue);
	if((unsigned char)*eflash_consumer_ptr->netamountdue == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->netamountdue,size);
	}
	size = sizeof(eflash_consumer_ptr->TAX);
	if((unsigned char)*eflash_consumer_ptr->TAX == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->TAX,size);
	}
	size = sizeof(eflash_consumer_ptr->credit);
	if((unsigned char)*eflash_consumer_ptr->credit == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->credit,size);
	}
	size = sizeof(eflash_consumer_ptr->grandtotal);
	if((unsigned char)*eflash_consumer_ptr->grandtotal == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->grandtotal,size);
	}
	size = sizeof(eflash_consumer_ptr->out_no_of_months_the_bill_has_to_be_issued);
	if((unsigned char)*eflash_consumer_ptr->out_no_of_months_the_bill_has_to_be_issued == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->out_no_of_months_the_bill_has_to_be_issued,size);
	}
	size = sizeof(eflash_consumer_ptr->meterreadingtype);
	if((unsigned char)*eflash_consumer_ptr->meterreadingtype == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->meterreadingtype,size);
	}
	size = sizeof(eflash_consumer_ptr->Receipt_No);
	if((unsigned char)*eflash_consumer_ptr->Receipt_No == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->Receipt_No,size);
	}
	size = sizeof(eflash_consumer_ptr->Cheque_No);
	if((unsigned char)*eflash_consumer_ptr->Cheque_No == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->Cheque_No,size);
	}
	size = sizeof(eflash_consumer_ptr->Cheque_Date);
	if((unsigned char)*eflash_consumer_ptr->Cheque_Date == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->Cheque_Date,size);
	}
	size = sizeof(eflash_consumer_ptr->Bank);
	if((unsigned char)*eflash_consumer_ptr->Bank == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->Bank,size);
	}
	size = sizeof(eflash_consumer_ptr->bill_no);
	if((unsigned char)*eflash_consumer_ptr->bill_no == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->bill_no,size);
	}
	size = sizeof(eflash_consumer_ptr->bill_status);
	if((unsigned char)*eflash_consumer_ptr->bill_status == 0xFF){
		copy_spaces((unsigned char *)eflash_consumer_ptr->bill_status,size);
	}

}
//*****************************************************************
void copy_spaces(unsigned char *bfr, int size1)
{
int index;
unsigned char *tem_buf = bfr;
	for(index=0; index<size1; index++){
		write_to_external_flash_n ( (char *)tem_buf," ",1);
		tem_buf++;
	}
}
//*****************************************************************
load_all_parameters_to_local_buffer ()
{
estrncpy (&LConsumer_SC_No[0],eflash_consumer_ptr->Consumer_SC_No,15);
LConsumer_SC_No[15] = '\0';
estrncpy (&Lmeter_constant[0],eflash_consumer_ptr->meter_constant,7); 
Lmeter_constant[7] = '\0';
estrncpy (&Lsitecode[0],eflash_consumer_ptr->sitecode,4); 
Lsitecode[4] = '\0';

estrncpy (&Lbillmonth[0],eflash_consumer_ptr->billmonth,6); 
Lbillmonth[6] = '\0';

//strncpy (&Lconsumer_name[0],eflash_consumer_ptr->consumer_name,20);  
//Lconsumer_name[20] = '\0';
estrncpy (&LTariff[0],eflash_consumer_ptr->Tariff,7) ;
LTariff[7] = '\0';
//strncpy (&Lledger_no[0],eflash_consumer_ptr->ledger_no,10) ;
//Lledger_no[10] = '\0';
//strncpy (&Lfolio_no[0],eflash_consumer_ptr->folio_no,10) ;
//Lfolio_no[10] = '\0';
estrncpy (&LConnected_Load[0],eflash_consumer_ptr->Connected_Load,7);
LConnected_Load[7] = '\0';
// not used
//strncpy (&Lextra_present_reading[0],eflash_consumer_ptr->extra_present_reading,7);
//Lextra_present_reading[7] = '\0';
estrncpy (&Ld_and_r_fee[0],eflash_consumer_ptr->d_and_r_fee,7);
Ld_and_r_fee[7] = '\0';
estrncpy (&Larrears[0],eflash_consumer_ptr->arrears,7); 
Larrears[7] = '\0';
estrncpy (&Linterest[0],eflash_consumer_ptr->interest,7);
Linterest[7] = '\0';
estrncpy (&Lothers[0],eflash_consumer_ptr->others,7); 
Lothers[7] = '\0';
estrncpy (&Lbackbillarr[0],eflash_consumer_ptr->backbillarr,7); 
Lbackbillarr[7] = '\0';
estrncpy (&Laverage_consumption[0],eflash_consumer_ptr->average_consumption,7); 
Laverage_consumption[7] = '\0';
estrncpy (&Ldl_or_mnr_prev_month[0],eflash_consumer_ptr->dl_or_mnr_prev_month,1); 
Ldl_or_mnr_prev_month[1] = '\0';
// not used
//strncpy (&Lprev_no_of_dls[0],eflash_consumer_ptr->prev_no_of_dls,7) ;   
//Lprev_no_of_dls[7] = '\0';
// not used 
//strncpy (&Ldate_of_service[0],eflash_consumer_ptr->date_of_service,10); 
//Ldate_of_service[10] = '\0';
estrncpy (&LPrevious_Reading[0],eflash_consumer_ptr->Previous_Reading,10); 
LPrevious_Reading[10] = '\0';
estrncpy (&Lpower_factor[0],eflash_consumer_ptr->power_factor,7); 
Lpower_factor[7] = '\0';
estrncpy (&Lreading_date[0],eflash_consumer_ptr->reading_date,10);
Lreading_date[10] = '\0';
estrncpy (&Lmeter_change_units_consumed[0],eflash_consumer_ptr->meter_change_units_consumed,7);
Lmeter_change_units_consumed[7] = '\0';
estrncpy (&Lno_of_months_the_bill_has_to_be_issued[0],eflash_consumer_ptr->no_of_months_the_bill_has_to_be_issued,5);
Lno_of_months_the_bill_has_to_be_issued[5] = '\0';
estrncpy (&Lcredit_or_rebate[0],eflash_consumer_ptr->credit_or_rebate,7); 
Lcredit_or_rebate[7] = '\0';
estrncpy (&Lfixed_charges[0],eflash_consumer_ptr->fixed_charges,7);
Lfixed_charges[7] = '\0';
estrncpy (&Laudit_arrears[0],eflash_consumer_ptr->audit_arrears,7);
Laudit_arrears[7] = '\0';
estrncpy (&Lold_interest[0],eflash_consumer_ptr->old_interest,7);
Lold_interest[7] = '\0';
estrncpy (&Ldl_adj[0],eflash_consumer_ptr->dl_adj,10);
Ldl_adj[10] = '\0';

estrncpy (&LADDITIONAL_DEP[0],eflash_consumer_ptr->ADDITIONALDEP,10);
LADDITIONAL_DEP[10] = '\0';

estrncpy (&LPresent_Reading[0],eflash_consumer_ptr->Present_Reading,10);
LPresent_Reading[10] = '\0';
estrncpy (&Lunits[0],eflash_consumer_ptr->units,10);
Lunits[10] = '\0';
estrncpy (&Lec[0],eflash_consumer_ptr->ec,10);
Lec[10] = '\0';
estrncpy (&Lnetamountdue[0],eflash_consumer_ptr->netamountdue,10);
Lnetamountdue[10] = '\0';
estrncpy (&LTAX[0],eflash_consumer_ptr->TAX ,10);
LTAX[10] = '\0';
estrncpy (&Lcredit[0],eflash_consumer_ptr->credit ,10);
Lcredit[10] = '\0';
estrncpy (&Lgrandtotal[0],eflash_consumer_ptr->grandtotal,10);
Lgrandtotal[10] = '\0';
//no reqrd
estrncpy (&Lout_no_of_months_the_bill_has_to_be_issued[0],eflash_consumer_ptr->out_no_of_months_the_bill_has_to_be_issued,2);
Lout_no_of_months_the_bill_has_to_be_issued[0] = '\0';
estrncpy (&Lmeterreadingtype[0],eflash_consumer_ptr->meterreadingtype,1);
Lmeterreadingtype[1] = '\0';
estrncpy (&LReceipt_No[0],eflash_consumer_ptr->Receipt_No,10);
LReceipt_No[10] = '\0';
estrncpy (&LCheque_No[0],eflash_consumer_ptr->Cheque_No,10);
LCheque_No[10] = '\0';
//CR3) Send Reading date in Cheque Date field-DONE
estrncpy (&LCheque_Date[0],eflash_consumer_ptr->reading_date,10);
LCheque_Date[10] = '\0';
estrncpy (&LBank[0],eflash_consumer_ptr->Bank,35);
LBank[35] = '\0';
estrncpy (&Lbill_no[0],eflash_consumer_ptr->bill_no,10);
Lbill_no[10]= '\0';
estrncpy (&Lbill_status[0],eflash_consumer_ptr->bill_status,1);
Lbill_status[1] = '\0'; 
}
//******************************
check_data_rr_validity ()
{
unsigned char c1,c2,c3;//,*ptr;
unsigned int temp = 0;
eflash_consumer_ptr_temp = eflash_consumer_ptr;
eflash_consumer_ptr = (struct all_data *) dflash_consumer;
while (temp < no_of_consumer_records)
	{
	strncpy (&temp_bfr[0],&LConsumer_SC_No_bfr[0],15);
	c3 = hhtpstrlen (&temp_bfr[0],15);
	strncpy (&LConsumer_SC_No_bfr[0],&temp_bfr[0],c3);
	LConsumer_SC_No_bfr[c3] = '\0';
	estrncpy (&temp_bfr[0],eflash_consumer_ptr->Consumer_SC_No,15);
	c2 = hhtpstrlen (&temp_bfr[0],15);
	c1 = 0;
	if (c2 == c3)
		{
		while (c1 < c2)
			{
			if (temp_bfr[c1] != LConsumer_SC_No_bfr[c1]) break;
			c1++;
			}
		}
	if (c1 == c2)
	{
		temp_long = (unsigned long)eflash_consumer_ptr;	
		return ('Y');
	}
	eflash_consumer_ptr++;
	temp++;
	}

eflash_consumer_ptr = eflash_consumer_ptr_temp;
return ('N');
}
//******************************************************
char compute_tarrif ()
{
unsigned int i_temp;
unsigned int noftariffs;
noftariffs = 0;
//temp_flash_struct  = (struct tariff_table *) dflash_tariff;
eflash_tariff_ptr = (struct tariff_table *) dflash_tariff;
strncpy (&temp_bfr[0],&LTariff[0],7);
temp_bfr[6] = '\0';	// only six of 7 charecters are asumed to be used
Ltariff_code_i = atol (&temp_bfr[0]);
//movepointer to tariff area
while (1)
	{
	estrncpy ((char *) &Lttariff_code[0],eflash_tariff_ptr->tariff_code,7);
	strncpy (&temp_bfr[0],&Lttariff_code[0],7);
	temp_bfr[6] = '\0';	// only six of 7 charecters are asumed to be used
	i_temp = atoi (&temp_bfr[0]);
	if (i_temp == Ltariff_code_i) break;
	//..if (noftariffs++ < no_of_tariff_records) eflash_tariff_ptr++;
	if (noftariffs++ < no_of_tariff_records) //added 28.05.2014 by husain
	{
		eflash_tariff_ptr = (struct tariff_table *)((unsigned long)eflash_tariff_ptr+220);
	}
	else
		{
		lcd_clear_display ();
		lcd_display_line("Invalid TariffType   ",1,0);
		lcd_display_line("BillCannotBeProcessed",2,0);
		beep_buzzer ();
		get_esc_key ();
		eflash_consumer_ptr = (struct all_data *)temp_long;
		return('N');
		}
	}
return('Y');	
}
//***************************
float fcred,fcmax;
calculate_fixed_charges ()
{
char c1;
unsigned long fc1u,fc2u,fc3u,fc4u;
unsigned long fc1r,fc2r,fc3r,fc4r;
float fcred,tsanload;
fixed_charges = 0;
fc1u = eatol (eflash_tariff_ptr-> fc1u);
fc2u = eatol (eflash_tariff_ptr-> fc2u);
fc3u = eatol (eflash_tariff_ptr-> fc3u);
fc4u = eatol (eflash_tariff_ptr-> fc4u);
fc1r = eatol (eflash_tariff_ptr-> fc1r);
fc2r = eatol (eflash_tariff_ptr-> fc2r);
fc3r = eatol (eflash_tariff_ptr-> fc3r);
fc4r = eatol (eflash_tariff_ptr-> fc4r);
fcred = eatof (eflash_tariff_ptr-> fc_red);
fcmax = eatof (eflash_tariff_ptr-> fc_max);
slab1 = fc1u * fc1r;
slab2 = (fc2u - fc1u) * fc2r + slab1;
slab3 = (fc3u - fc2u) * fc3r + slab2;
//if (atof (&LConnected_Load[0]) - sancload ) sancload += 1;
tsanload=sancload;
if (sancload > 0 && sancload < 1)
   sancload = 1;
while (1)
	{
	if (sancload > fc3u && fc3u != 0)
		{
		fixed_charges += (sancload - fc3u) *fc4r;	
		fixed_charges += slab3; 
		c1 = 'Y';
		}
	if (c1 == 'Y') break;
	if (sancload > fc2u && fc2u != 0)
		{
		fixed_charges += (sancload - fc2u) *fc3r;	
		fixed_charges += slab2; 
		c1 = 'Y';
		}
	if (c1 == 'Y') break;
	if (sancload > fc1u && fc1u != 0)
		{
		fixed_charges += (sancload - fc1u) *fc2r;	
		fixed_charges += slab1; 
		c1 = 'Y';
		}
	if (c1 == 'Y') break;
	else if (fc1r != 0 && fc1u == 0)
		{
		fixed_charges = fc1r; 
		c1 = 'Y';
		}
	else
		{
		fixed_charges = fc1r * sancload;
		c1 = 'Y';
		}
	if (c1 == 'Y') break;
	}
sancload=tsanload;
if (fcred > 0) 	// fc reduction valid for rural
	{
//	fixed_charges -= fcred;
	credit = fixed_charges - fcred;
	if (credit < 0) credit = 0;
//	(Lreading_value_float * ecred);
	}
}
//**********************
float energy_charges,energy_charges2, credit;
calculate_energy_charges ()
{
float readingdateindays,tariffdateindays;
float diffmonths, Lnofmbti_old_t, Lnofmbti_new_t;
float Lreading_value_temp1,Lreading_value_temp2;
char temp2_bfr[10],diff_prblem[15];

strncpy(&temp_bfr[0],&Lreading_date[3],2);// days
temp_bfr[2] = '\0';
readingdateindays = atof (&temp_bfr[0]);
if( readingdateindays <=0)
{
  	readingdateindays = 0;	
}
strncpy(&temp_bfr[0],&Lreading_date[0],2);// month
temp_bfr[2] = '\0';
readingdateindays += (atof (&temp_bfr[0]) * (float)(365/12));
if( readingdateindays <=0)
{
  	readingdateindays = 0;	
}

strncpy(&temp_bfr[0],&Lreading_date[6],4);//year
temp_bfr[4] = '\0';
readingdateindays += (atof (&temp_bfr[0]) * (float)(365.25));
if( readingdateindays <=0)
{
  	readingdateindays = 1;	
}
estrncpy (&temp2_bfr[0],eflash_tariff_ptr->tdate,10);
strncpy (&temp_bfr[0],&temp2_bfr[0],2);	// date
temp_bfr[2] = '\0';
tariffdateindays = atof (&temp_bfr[0]);
if( readingdateindays <=0)
{
  	readingdateindays = 1;	
}
strncpy (&temp_bfr[0],&temp2_bfr[3],2);
temp_bfr[2] = '\0';
tariffdateindays += (atof (&temp_bfr[0]) * (float)(365/12));
if( readingdateindays <=0)
{
  	readingdateindays = 1;	
}
strncpy(&temp_bfr[0],&temp2_bfr[6],4);
temp_bfr[4] = '\0';
tariffdateindays += (atof (&temp_bfr[0]) * (float)(365.25));
if( readingdateindays <=0)
{
  	readingdateindays = 1;	
}
diffmonths = (readingdateindays - tariffdateindays)/ (float)(365/12);
//Shiva 27.12.2009 Two lines are added
sprintf (&diff_prblem[0],"%2.0f",diffmonths);
diffmonths = atof(&diff_prblem[0]);
if ( diffmonths <= 0)
{
	diffmonths = 1;
}
strncpy (&temp_bfr[0] ,&Lno_of_months_the_bill_has_to_be_issued[0],5);
temp_bfr[5] ='\0';
Lno_of_months_the_bill_has_to_be_issued_f = atof (&temp_bfr[0]);
if (Lno_of_months_the_bill_has_to_be_issued_f <= 0.0)
	{
	Lno_of_months_the_bill_has_to_be_issued_f=1;
	}


//if (eflash_tariff_ptr->calculateFC == '9' && eflash_tariff_ptr->calculateEC == '9') 
//	{
//	if (Lreading_value_float > (18 * Lno_of_months_the_bill_has_to_be_issued_f))
//		{
//		Lreading_value_float -= (18 * Lno_of_months_the_bill_has_to_be_issued_f);
//		Ltemp_UNITS = 18 * Lno_of_months_the_bill_has_to_be_issued_f;
//		}
//	else Ltemp_UNITS = 0;
//	}




if (Lno_of_months_the_bill_has_to_be_issued_f <= diffmonths) Lnofmbti_old_t = 0;
else Lnofmbti_old_t = Lno_of_months_the_bill_has_to_be_issued_f - diffmonths;
Lnofmbti_new_t = Lno_of_months_the_bill_has_to_be_issued_f - Lnofmbti_old_t;
Lnofmbtiont = Lnofmbti_new_t;
Lreading_value_temp1 = Lreading_value_float;
Lreading_value_float = ((float)Lnofmbti_new_t/(float)Lno_of_months_the_bill_has_to_be_issued_f)* Lreading_value_float;
calculate_energy_charges2 ();
energy_charges = energy_charges2;
if (Lnofmbti_old_t > 0)
	{
	Lreading_value_temp2 = Lreading_value_float;
	Lreading_value_float = ((float)Lnofmbti_old_t/(float)Lno_of_months_the_bill_has_to_be_issued_f)* Lreading_value_temp1;
	eflash_tariff_ptr++; 
	//..eflash_tariff_ptr = (struct tariff_table *)((unsigned long)eflash_tariff_ptr+220);
	Lnofmbtiont = Lnofmbti_old_t;
	calculate_energy_charges2 ();
	energy_charges += energy_charges2;
	Lreading_value_float += Lreading_value_temp2;
	}
}
//*************************************
calculate_energy_charges2 ()
{
unsigned char c1 = 'N';
//unsigned long L_reading_adj_value;
float L_reading_adj_value;

energy_charges2 = 0;
sc1u = eatof (eflash_tariff_ptr-> sc1u);
sc2u = eatof (eflash_tariff_ptr-> sc2u);
sc3u = eatof (eflash_tariff_ptr-> sc3u);
sc4u = eatof (eflash_tariff_ptr-> sc4u);
sc5u = eatof (eflash_tariff_ptr-> sc5u);
sc6u = eatof (eflash_tariff_ptr-> sc6u);
sc1r = eatof (eflash_tariff_ptr-> sc1r);
sc2r = eatof (eflash_tariff_ptr-> sc2r);
sc3r = eatof (eflash_tariff_ptr-> sc3r);
sc4r = eatof (eflash_tariff_ptr-> sc4r);
sc5r = eatof (eflash_tariff_ptr-> sc5r);
sc6r = eatof (eflash_tariff_ptr-> sc6r);
ecred = eatof (eflash_tariff_ptr-> ec_red);
ecmax = eatof (eflash_tariff_ptr-> ec_max);

slab1 = sc1u * sc1r;
slab2 = (sc2u - sc1u) * sc2r + slab1;
slab3 = (sc3u - sc2u) * sc3r + slab2;
slab4 = (sc4u - sc3u) * sc4r + slab3;
slab5 = (sc5u - sc4u) * sc5r + slab4;

L_reading_adj_value = Lreading_value_float;
Lreading_value_float /= (float)Lnofmbtiont;

while (1)
	{
	if (Lreading_value_float > sc5u && sc5u != 0)
		{
		energy_charges2 += (Lreading_value_float - sc5u) *sc6r;	
		energy_charges2 += slab5; 
		c1 = 'Y';
		}
	if (c1 == 'Y') break;
	if (Lreading_value_float > sc4u && sc4u != 0)
		{
		energy_charges2 += (Lreading_value_float - sc4u) *sc5r;	
		energy_charges2 += slab4; 
		c1 = 'Y';
		}
	if (c1 == 'Y') break;
	if (Lreading_value_float > sc3u && sc3u != 0)
		{
		energy_charges2 += (Lreading_value_float - sc3u) *sc4r;	
		energy_charges2 += slab3; 
		c1 = 'Y';
		}
	if (c1 == 'Y') break;
	if (Lreading_value_float > sc2u && sc2u != 0)
		{
		energy_charges2 += (Lreading_value_float - sc2u) *sc3r;	
		energy_charges2 += slab2; 
		c1 = 'Y';
		}
	if (c1 == 'Y') break;
	if (Lreading_value_float > sc1u && sc1u != 0)
		{
		energy_charges2 += (Lreading_value_float - sc1u) *sc2r;	
		energy_charges2 += slab1; 
		c1 = 'Y';
		}
	if (c1 == 'Y') break;
	else if (sc1r != 0 && sc1u == 0)
		{
		energy_charges2 = sc1r; 
		c1 = 'Y';
		}
	else
		{
		energy_charges2 = sc1r * Lreading_value_float;
		c1 = 'Y';
		}
	if (c1 == 'Y') break;
	}

// energy charges reduction and max ec red calculated 
if(ecred > 0 && Ltariff_code_i != 1)
	{
	if (Lreading_value_float * ecred  > ecmax  && ecmax != 0)
		{
//		energy_charges2	-= ecmax;
		credit = ecmax * Lnofmbtiont;
		}
	else
		{
//		energy_charges2 -= (Lreading_value_float * ecred);
		credit = (Lreading_value_float * ecred) * Lnofmbtiont;
		}
    }
else if (ecred > 0 && Ltariff_code_i == 1)
	{
	credit+= ecmax;// * Lnofmbtiont;
	}
	
if(Ltariff_code_i != 1)
	{
	Lreading_value_float *= Lnofmbtiont;
	energy_charges2 *= Lnofmbtiont;
	}

//Lreading_value_float *= Lnofmbtiont;
//energy_charges2 *= Lnofmbtiont;
	


Lothers_float = atof (&Lothers[0]);
Lpf_float = atof (&Lpower_factor[0]);
Lpf_float *= Lreading_value_float;
// when the Lreading_value_float is multipled with Lno_of_months_the_bill_has_to_be_issued_f
// a 302/3 will result in 100, and 100 * 3 = 300, here 2 units will be lost
// so this value is rmoved from present meter reading so that 2 units
// get added to next month.
LPresent_Reading_float -= (L_reading_adj_value - Lreading_value_float);
}
//*********************************************************
process_receipt ()
{
unsigned long temp;

lcd_clear_display ();
lcd_display_line("RRno:               ",1,0);
get_key_entry (19,'K',15,(char *)&kbd_input_bfr[0],1,6);// coolecting 10 digits, diplaying collected digits at cur pos 10
strncpy (&LConsumer_SC_No_bfr[0],(char *)&kbd_input_bfr[0],15);
temp = atol(&LTariff[0]);
if (check_data_rr_validity (&kbd_input_bfr[0]) == 'Y')
	{
	load_all_parameters_to_local_buffer ();
	get_receipt_details ();
	print_bill ('R');
//	print_receipt ();
	}
else
	{
	lcd_display_line("Invalid RR No       ",2,0);
	beep_buzzer ();
	get_esc_key ();
	lcd_clear_display ();
	}
}
//*********************************************************
get_receipt_details ()
{
unsigned char c1;
lcd_display_line("ChequeNo.           ",2,0);
get_key_entry (19,'K',10,(char *)&kbd_input_bfr[0],2,10);// coolecting 10 digits, diplaying collected digits at cur pos 10
strncpy (&LCheque_No[0],(char *)&kbd_input_bfr[0],10);

//lcd_display_line("MICR.No             ",3,0);
//get_key_entry (10,&kbd_input_bfr[0],2,10);// coolecting 10 digits, diplaying collected digits at cur pos 10
//strncpy (&LMICR_No[0],&kbd_input_bfr[0],5);

lcd_display_line("BankCode            ",3,0);
get_key_entry (19,'K',10,(char *)&kbd_input_bfr[0],3,10);// coolecting 10 digits, diplaying collected digits at cur pos 10
//for (c1 = 0; c1 < 10; c1++) LBank[c1] = ' ';
strncpy (&LBank[0],(char *)&kbd_input_bfr[0],10);

//lcd_display_line("BranchCode          ",3,0);
//get_key_entry (5,&kbd_input_bfr[0],2,5);// coolecting 10 digits, diplaying collected digits at cur pos 10
//strncpy (&LBranch[0],&kbd_input_bfr[0],6);
}
//*********************************************************
//load_tax ()
//{
//Ltax_float = atof (eflash_tariff_ptr-> tax);
//}
//*********************************************************
print_bill_again()
{
lcd_clear_display ();
if (validbillprinted != 'Y')
	{ 
	lcd_display_line("No valid bill for   ",1,0);
	lcd_display_line("repeat printing !!!!",2,0);
	get_key ();
	return;
	}
lcd_display_line("LastBillPrint Press1",1,0);
lcd_display_line("Rec.BillPrint Press2",2,0);
	while (1)
	{
	key_pressed = get_key ();
	if (key_pressed == '1')
		{
		print_bill (REPRINT);
		}
	if (key_pressed == '2')
		{
		process_meter_reading ('Y');
		}
	if (key_pressed == ESC_KEY) break;
	}
}
//*********************************************************
unsigned char hhtpstrlen(char bfr[],unsigned char noch)
{
unsigned char c1;
//char temp_bfr[noch];
strncpy (&temp_bfr[0], &bfr[0], noch);

for (c1 = noch-1; c1 > 0;)
	{
	if (temp_bfr[c1] == ' ')
		{
		temp_bfr[c1] = '\0';
		c1--;
		}
	else break;
	}

for (c1 = 0; c1 < noch; )
	{
	if (temp_bfr[0] == ' ')
		{
		strncpy (&temp_bfr[0],&temp_bfr[1],noch-1-c1);
		temp_bfr[noch-1-c1] = '\0';
		c1++;
		}
	else break;
	}
c1 = strlen (&temp_bfr[0]);
return (c1);
}
//*********************************************************
load_first_valid_sc_no ()
{
unsigned long temp = 0;
unsigned char c1;
eflash_consumer_ptr = (struct all_data *) dflash_consumer;
while (temp < no_of_consumer_records)
	{
	load_all_parameters_to_local_buffer ();
	c1 = Lunits[0];
	if (c1 == 0xff) break;
	eflash_consumer_ptr++;
	temp++;
	}
//eflash_consumer_ptr--;
}
//*********************************************************
load_current_billno_receiptno ()
{
unsigned long temp = 0;
unsigned char c1;
Long_bill_no = atol (&LLbill_no[1]);
Long_receipt_no = atol (&LLreceipt_no[1]);
eflash_consumer_ptr = (struct all_data *) dflash_consumer;
while (temp < no_of_consumer_records)
	{
	load_all_parameters_to_local_buffer ();
	c1 = Lunits[0];
	if (c1 != 0xff) Long_bill_no++;
	c1 = LCheque_No[0];
	if (c1 != 0xff) Long_receipt_no++;
	eflash_consumer_ptr++;
	temp++;
	}
eflash_consumer_ptr = (struct all_data *) dflash_consumer;
//eflash_consumer_ptr--;
}
//*********************************************************
process_due_date ()
{
// this function first aligns spaces zeros
// Lreading data is in mm/dd/yyy or m/d/yyyy or mm/d/yyyy etc
unsigned char c1;
unsigned int date,month,year,leapyear;
strncpy (&temp_bfr[0],&Lreading_date[0],10);
temp_bfr[5] ='\0';
date  = atoi (&temp_bfr[3]);
temp_bfr[2] = '\0';
month = atoi (&temp_bfr[0]);
temp_bfr[10] = '\0';
year  = atoi (&temp_bfr[6]);
date += 14;
leapyear = year % 4;
//if (leapyear) month_table[1] = 29;
//else month_table[1] = 28;
if (date > month_table[month-1])
	{
	date -= month_table[month-1];
	month += 1;
	if (month > 12) 
		{
		month = 1;
		year++;
		}
	}

sprintf (&Ldue_date_bfr[0],"%.2d",date);
Ldue_date_bfr[2] = '/';
sprintf (&Ldue_date_bfr[3],"%.2d",month);
Ldue_date_bfr[5] = '/';
sprintf (&Ldue_date_bfr[6],"%4d",year);

}
//*********************************************************
correct_dateformat ()
{
unsigned char c1;
hhtpstrlen (&Lreading_date[0],10); 	// day
strncpy (&Lreading_date[0],&temp_bfr[0],10);
if (Lreading_date[1] == '/')
	{
	for (c1 = 0 ; c1 < 9; c1++)
		{
		Lreading_date[9-c1] = Lreading_date[8-c1];	
		}
	Lreading_date[0] = '0';
	}
if (Lreading_date[4] == '/')
	{
	for (c1 = 0 ; c1 < 9-3; c1++)
		{
		Lreading_date[9-c1] = Lreading_date[8-c1];	
		}
	Lreading_date[3] = '0';
	}
}

//*********************************************************

unsigned char get_RR_No(unsigned char nochL, char *bfr, unsigned char line_no, unsigned char cur_pos)
{
unsigned char flag_key=0;
unsigned char totch = nochL;
unsigned char c1,c2;
unsigned char temp_buf[21];
unsigned char t_bfr[21];
int Col = 19;

//eflash_consumer_ptr = (struct all_data *) dflash_consumer;
lcd64128size = '1';


lcd_line_cursor_position (line_no,cur_pos);
lcd_cursor_onoff ('Y');
for (c1 = 0; c1 < nochL; c1++) bfr[c1] = ' ';
while (1)
	{
	if ((unsigned char ) *eflash_consumer_ptr->bill_status == 0xff && (unsigned char ) *eflash_consumer_ptr->Consumer_SC_No != 0xff)
		{
		lcd_display_line("RRno   NOT-BILLED   ",3,0);
		}
	else if ((unsigned char ) *eflash_consumer_ptr->bill_status == 0xfc)
		{
		lcd_display_line("RRno ALREDY-BILLED  ",3,0);
		}
	else if ((unsigned char ) *eflash_consumer_ptr->bill_status == 0xfd)
		{
		lcd_display_line("Bill NOT-PRINTD-FULL",3,0);
		}
	else if ((unsigned char ) *eflash_consumer_ptr->bill_status == 0x7c)
		{
		lcd_display_line("RRno UPLOADED-ALRDY ",3,0);
		}
	
	lcd_display_line(&eflash_consumer_ptr->consumer_name[0],4,0);
	lcd_display_line(&eflash_consumer_ptr->ADDRESS[0],5,0);
	//lcd_display_line(&eflash_consumer_ptr->ADDRESS1[0],4,0);
	strncpy(&temp_bfr[0],"TARIFF   :                     ",21);
	strncpy(&temp_bfr[10],&eflash_consumer_ptr->Tariff[0],7);
	lcd_display_line(&temp_bfr[0],6,0);
	strncpy(&temp_bfr[0],"READ DATE:                     ",21);
	strncpy(&temp_bfr[10],&eflash_consumer_ptr->reading_date[0],10);
	lcd_display_line(&temp_bfr[0],7,0);
	
	
	//lcd_line_cursor_position (line_no,cur_pos);
	//lcd_display_string_noch (&bfr[0],nochL);
	if(flag_key != 1)
	{
		lcd64128size = '2';
		lcd_display_line ("                       ",1,0);
		strncpy(&bfr[0],"RRno:                ",21);
		strncpy(&bfr[5],&eflash_consumer_ptr->Consumer_SC_No[0],15);
		lcd_display_line (&bfr[0],1,0);
		lcd64128size = '1';
	}
	
	
	c1 = get_key ();
	if ((c1 >= '0' && c1 <= '9') || (c1 >= 'A' && c1 <= 'Z'))
		{
		
		if(flag_key != 1)
		{
			lcd64128size = '2';
			strncpy(&bfr[0],"RRno:                ",21);
			strncpy((char *)&t_bfr[0],"RRno:                ",21);
			lcd_display_line (&bfr[0],1,0);
			lcd64128size = '1';
		}
		lcd64128size = '2';
		//lcd_line_cursor_position (1,Col);
		strncpy (&bfr[0],&bfr[1],nochL-1);
		flag_key = 1;
		bfr[nochL-1] = c1;
		
		
		t_bfr[24-Col] = c1;
	//	strncpy(&bfr[5],&t_bfr[0],(20-Col));
		
		
		lcd_display_line ((char *)&t_bfr[0],1,0);
	
		
		
		//lcd_display_char(c1);
		Col -= 1;
		lcd64128size = '1';
		}
	if (c1 == LEFT_ARROW_KEY)
		{
		for (c1 = nochL-1; c1 >0; c1--)
			{
			(bfr[c1] = bfr[c1-1]);
			}
		
		t_bfr[24-(++Col)] = ' ';
		
		lcd64128size = '2';
		lcd_display_line ((char *)&t_bfr[0],1,0);
		lcd64128size = '1';
		
		bfr[0] = ' ';
		}			
	if (c1 == UP_ARROW_KEY )
		{
		
		flag_key = 0;
		eflash_consumer_ptr++;
		
		c2 = *eflash_consumer_ptr->Consumer_SC_No;
		
		while(c2 != 0xFF)
		{
			c2 = *eflash_consumer_ptr->Consumer_SC_No;
			if((unsigned char)*eflash_consumer_ptr->bill_status == 0xFC)
			{
				eflash_consumer_ptr++;
			}
			else break;
		}
		c2 = *eflash_consumer_ptr->Consumer_SC_No;
		if (c2 == 0xff)
			{
			eflash_consumer_ptr--;
			}
		else estrncpy (&bfr[0],eflash_consumer_ptr->Consumer_SC_No,15);
		}
	if (c1 == DOWN_ARROW_KEY )
		{
		
		flag_key = 0;
		eflash_consumer_ptr--;
		c2 = *eflash_consumer_ptr->Consumer_SC_No;
		while(c2 != 0xFF)
		{
			c2 = *eflash_consumer_ptr->Consumer_SC_No;
			if((unsigned char)*eflash_consumer_ptr->bill_status == 0xFC)
			{
				eflash_consumer_ptr--;
			}
			else break;
		}
		c2 = *eflash_consumer_ptr->Consumer_SC_No;
		if (c2 == 0xff)	eflash_consumer_ptr++;
		else estrncpy (&bfr[0],eflash_consumer_ptr->Consumer_SC_No,15);
		}
	if (c1 == ESC_KEY)
		{
		c1 = 0xff;
		break;
		}
	if (c1 == ENTER_KEY)
		{
//		bfr[totch-noch] = 0;
		
	if(19-Col == 4)
	{
		strncpy (&bfr[0]," ",1);
	}
	else if(19-Col == 3)
	{
		strncpy (&bfr[0],"  ",2);
	}
	else if(19-Col == 2)
	{
		strncpy (&bfr[0],"   ",3);
	}
	
		temp_long = (unsigned long)eflash_consumer_ptr;
		c1 = 0;
		if(flag_key != 1){
			estrncpy (&bfr[0],eflash_consumer_ptr->Consumer_SC_No,15);
		}
		break;
		}
	if (c1 == 0xff)
		{
		break;
		}
	}
lcd_cursor_onoff ('N');
lcd64128size = '2';
return (c1);
}
//*********************************************************

store_all_parameters ()
{

eflash_miscCESC_ptr = (struct miscdata_CESC *) dflash_miscCESC;
if (eflash_miscCESC_ptr->ask_before_data_storage == 'Y')
	{
	lcd_clear_display ();
	lcd_display_line("BillPrintOK ESC/ENTE",1,0);
	key_pressed = get_key ();
	if (key_pressed == ESC_KEY) return;
	//if (get_enter_esc_key () == ESC_KEY) return;
	}


//sprintf(&LLbill_no[1],"%.9ld",Long_bill_no);
//LLbill_no[10] = ',';
/*
if((unsigned long)eflash_consumer_ptr != 0x800000)
	eflash_consumer_ptr--;
	
if((unsigned char)*eflash_consumer_ptr->bill_no == 0xFF)
	{
	if((unsigned long)eflash_consumer_ptr != 0x800000)
		eflash_consumer_ptr++;	
	write_to_external_flash_n ( eflash_consumer_ptr->bill_no,(char *)&eflash_miscCESC_ptr->Bill_No[0],10);
	}
else 
	{
	if((unsigned long)eflash_consumer_ptr != 0x800000)
		eflash_consumer_ptr++;
	sprintf(&LLbill_no[0],"%.10ld",Long_bill_no);		
	write_to_external_flash_n ( eflash_consumer_ptr->bill_no,(char *)&LLbill_no[0],10);
	}
*/

sprintf(&LLbill_no[1],"%.9ld",Long_bill_no);
//LLbill_no[10] = ',';
write_to_external_flash_n ( eflash_consumer_ptr->bill_no,(char *)&LLbill_no[0],10);

	
sprintf (&LPresent_Reading[0],"%10.2f",LPresent_Reading_float);
//LPresent_Reading[10] = ',';
write_to_external_flash_n (eflash_consumer_ptr->Present_Reading,(char *)&LPresent_Reading[0],10);
//Lckwhlkwh[7] = ',';
write_to_external_flash_n (eflash_consumer_ptr->ckwhlkwh,(char *)&Lckwhlkwh[0],7);
//Lckwhlkwh[5] = ','; 
write_to_external_flash_n (eflash_consumer_ptr->pf_reading,(char *)&Lckwhlkwh[0],5);
//Lbmd[5] = ',';
write_to_external_flash_n (eflash_consumer_ptr->bmd_reading,(char *)&Lbmd[0],5);
///sprintf (&temp_bfr[0],"%10.2f",Lpf_float);
///write_to_external_flash_n (eflash_consumer_ptr->pf_penality,(char *)&temp_bfr[0],10);
///sprintf (&temp_bfr[0],"%10.2f",Lbmd_float);
//temp_bfr[10] = ',';
///write_to_external_flash_n (eflash_consumer_ptr->bmd_penality,(char *)&temp_bfr[0],10);
//CR9)DL Units .. send only AVG units .. now send BM*AVG Units-DONE
if (Lstatus_id_c == '2' || Lstatus_id_c == '3' || Lstatus_id_c == '4'   || Lstatus_id_c == '7' || Lstatus_id_c == '8' || Lstatus_id_c == '9' )

	sprintf (&Lunits[0],"%10.2f",average_long_considered);
	else
    sprintf (&Lunits[0],"%10.2f",Lreading_value_float);



//Lunits[10] = ',';
write_to_external_flash_n (eflash_consumer_ptr->units,(char *)&Lunits[0],10);

//sprintf (&Ldl_adj[0],"%8.2f",dl_adj_amount);
//Ldl_adj[10] = ',';
//write_to_external_flash_n (eflash_consumer_ptr->dl_adj,(char *)&Ldl_adj[0],10);


sprintf (&Lec[0],"%9.2f",energy_charges);
//Lec[10] = ',';
write_to_external_flash_n (eflash_consumer_ptr->ec,(char *)&Lec[0],10);

sprintf (&Lpf_penality[0],"%8.2f",ltPFPenalty);
//Lnetamountdue[10] = ',';
write_to_external_flash_n (eflash_consumer_ptr->pf_penality,(char *)&Lpf_penality[0],10);

sprintf (&LexcessLoadPenalty[0],"%8.2f",excessLoadPenalty);
//Lnetamountdue[10] = ',';
write_to_external_flash_n (eflash_consumer_ptr->bmd_penality,(char *)&LexcessLoadPenalty[0],10);


sprintf (&Lnetamountdue[0],"%9.0f",LClosing_Balance_float);
//Lnetamountdue[10] = ',';
write_to_external_flash_n (eflash_consumer_ptr->netamountdue,(char *)&Lnetamountdue[0],10);





sprintf(&LTAX[0],"%9.2f",Ltax_float);
//LTAX[10] = ','; 
write_to_external_flash_n (eflash_consumer_ptr->TAX ,(char *)&LTAX[0],10);

sprintf(&temp_bfr[0],"%9.2f",credit);
//temp_bfr[10] = ',';
write_to_external_flash_n (eflash_consumer_ptr->credit ,(char *)&temp_bfr[0],10);


sprintf (&Lgrandtotal[0],"%9.0f",LClosing_Balance_float);
//Lgrandtotal[10] = ',';
write_to_external_flash_n (eflash_consumer_ptr->grandtotal,(char *)&Lgrandtotal[0],10);

Lmeterreadingtype[0] = Lstatus_id_c;
//Lmeterreadingtype[1] = ',';
write_to_external_flash_n (eflash_consumer_ptr->meterreadingtype,(char *)&Lmeterreadingtype[0],1);
//Observe_id_c[2] = ',';
write_to_external_flash_n (eflash_consumer_ptr->out_no_of_months_the_bill_has_to_be_issued,(char *)&Observe_id_c[0],2);

sprintf(&print_line_bfr[14],"%10.0f",(float)Long_receipt_no);
//LReceipt_No[10] = ',';
strncpy(&Lbillmonth[6],"    ",4);
write_to_external_flash_n (eflash_consumer_ptr->Receipt_No,(char *)&Lbillmonth[0],10);
//LCheque_No[10] = ',';
write_to_external_flash_n (eflash_consumer_ptr->Cheque_No,(char *)&LCheque_No[0],10);
//LCheque_Date[10] = ',';
write_to_external_flash_n (eflash_consumer_ptr->Cheque_Date,(char *)&LCheque_Date[0],10);

LBank[6] = ',';
strncpy((char*)&LBank[7],(char*)&software_ver[0],11);
LBank[18] = ',';
sprintf (&LBank[19],"%c",typeofmeter);
LBank[20] = ',';
sprintf (&LBank[21],"%c",meterposition);
LBank[22] = ',';
sprintf (&LBank[23],"%c",metercover);
LBank[24] = ',';
strncpy((char*)&LBank[25],(char*)&LMobileNumber[0],10);
LBank[35] = '\0';
write_to_external_flash_n (eflash_consumer_ptr->Bank,(char *)&LBank[0],35);
//software_ver[20]='\0';
//write_to_external_flash_n (eflash_consumer_ptr->Bank,(char *)&software_ver[0],20);//storing version name updated on 19.06.14

load_time_date_to_print_line_bfr ();

write_to_external_flash_n (eflash_consumer_ptr->billed_date,(char *)&print_line_bfr[4],10);
write_to_external_flash_n (eflash_consumer_ptr->time,(char *)&print_line_bfr[16],8);




//Lbill_status[0] = 0xfc;
//flash_overwrite = 'Y';
//write_to_external_flash_n (eflash_consumer_ptr->bill_status,&Lbill_status[0],1);
}
//**********************************************************
print_dotted_line ()
{
unsigned char c1;
#ifdef PRINTERF628
	{
	print_boarder_t ();
	print_boarder_v (6);
	}
#else
	{
	strncpy (&print_line_bfr[0],"........................",24);
	print_line(0);
	}
#endif
blank_print_bfr ();
}
//**********************************************************

//**********************************************************
print_summary_report ()
{
unsigned int i_temp,temp,noof_consumers;
unsigned char c1;
float tempf;
long templ;
unsigned int meter_reading_type[5];
unsigned char noftariffs;

lcd_clear_display();
lcd_display_line("Printing summaryReport",1,0);
key_pressed = get_key ();

if (key_pressed != ENTER_KEY )
	{
	return;
	}

noftariffs = 0;
meter_reading_type[0] = 0;
meter_reading_type[1] = 0;
meter_reading_type[2] = 0;
meter_reading_type[3] = 0;
meter_reading_type[4] = 0;

temp = 0;
noof_bills_rised = 0;
noof_bills_printed = 0;
noof_bills_notprinted = 0;
noof_bills_uploaded = 0;
total_no_units_demanded = 0;
total_amount_demanded = 0;
eflash_consumer_ptr = (struct all_data *) dflash_consumer;
while (temp < no_of_consumer_records)
	{
	load_all_parameters_to_local_buffer ();
	strncpy (&temp_bfr[0],&Lunits[0],10);
	temp_bfr[10]= '\0';
 	templ = atol (&temp_bfr[0]);
	if ((unsigned char) Lbill_status[0] != 0xff)
		{
		if ((unsigned char )Lbill_status[0] == 0xfc ) noof_bills_printed++;
		if ((unsigned char )Lbill_status[0] == 0xfd ) noof_bills_notprinted++;
		if ((unsigned char )Lbill_status[0] == 0x7c ) noof_bills_uploaded++;
		noof_bills_rised++;
		total_no_units_demanded += templ;
		meter_reading_type[Lmeterreadingtype[0]-'1']++;
		strncpy (&temp_bfr[0],&Lnetamountdue[0],10);
		temp_bfr[10]= '\0';
	 	tempf = atof (&temp_bfr[0]);
		total_amount_demanded += tempf;
		}
	eflash_consumer_ptr++;
	temp++;
	}

strncpy (&print_line_bfr[0],"     SUMMARY REPORT     ",24);
print_line_double_height (0);
print_time_date ();
strncpy(&print_line_bfr[0],"MetReadr ",9);
strncpy (&print_line_bfr[9],&Lmeter_reader_code[0],7);
estrncpy (&print_line_bfr[16],(char *)eflash_other_ptr->UNIT_slno,8);
print_line (0);
print_dotted_line ();
sprintf(&print_line_bfr[0],"TotNo.Cons %10.0f",(float)no_of_consumer_records);
print_line (0);
sprintf(&print_line_bfr[0],"TotRised   %10.0f",(float)noof_bills_rised);
print_line (0);
sprintf(&print_line_bfr[0],"TotNotRisd %10.0f",((float)no_of_consumer_records - (float)noof_bills_rised));
print_line (0);

blank_print_bfr ();
strncpy (&print_line_bfr[0],"Norml  :",8); 
sprintf (&print_line_bfr[10],"%.4d",meter_reading_type[0]);
print_line (0);

strncpy (&print_line_bfr[0],"DoorLk :",8);
sprintf (&print_line_bfr[10],"%.4d",meter_reading_type[1]);
print_line (0);

strncpy (&print_line_bfr[0],"M N R  :",8);
sprintf (&print_line_bfr[10],"%.4d",meter_reading_type[2]);
print_line (0);

strncpy (&print_line_bfr[0],"D C    :",8); 
sprintf (&print_line_bfr[10],"%.4d",meter_reading_type[3]);
print_line (0);

//blank_print_bfr ();
//sprintf (&print_line_bfr[0],"%.4d %.4d %.4d %.4d %.4d",meter_reading_type[0],meter_reading_type[1],meter_reading_type[2],meter_reading_type[3],meter_reading_type[4]);
//print_line(0);
print_dotted_line ();

}
//****************************
//***************************
//****************************
/*
print_cash_receipt ()
{
unsigned char c1;
lcd_clear_display ();
lcd_display_line("ConsmrNo            ",1,0);
c1 = get_RR_No (10,&kbd_input_bfr[0],1,10);// coolecting 10 digits, diplaying collected digits at cur pos 10
strncpy (&LConsumer_SC_No_bfr[0],&kbd_input_bfr[0],10);
//temp = atol(&LTariff[0]);


if (c1 == 0xff) return;
if (check_data_rr_validity (&kbd_input_bfr[0]) == 'Y')
	{
	load_all_parameters_to_local_buffer ();
//	LPrevious_Reading_float = atol(&LPrevious_Reading[0]); 
//	Lreading_value_float = 0;
//	Lpf_float = 0;
//	Lothers_float = 0;
//	c1 = Lunits[0];
//	if (c1 == 0xff)
//		{
//		lcd_clear_display ();	
//		lcd_display_line("MeterReadingNotTaken",2,0);
//		lcd_display_line("BillCannotBePrinted ",3,0);
//		beep_buzzer ();
//		get_esc_key ();
//		return (1);
//		}
	}
else
	{
	beep_buzzer ();
	lcd_display_line("Invalid R.R.No      ",2,0);
	lcd_display_line("New connectn ENT/ESC",3,0);
	if (get_enter_esc_key () == ENTER_KEY)
		{
		lcd_clear_display ();
		strncpy (&temp_bfr[0],"NewRRNo.  "10);
		strncpy (&temp_bfr[10],&kbd_input_bfr[0],10);
		lcd_display_line(&temp_bfr[0],1,0);
		lcd_display_line("ForBill PaymntPres 1",2,0);
		lcd_display_line("ForOtherPaymntPres 2",3,0);
		c1 = 0;
		while (c1 != '1' && c1 != '2')
			{
			c1 = get_key ();
			}
		if (c1 == '2')
			{
			lcd_display_line("PaymntDet           ",2,0);
			c1 = get_RR_No (10,&kbd_input_bfr[0],2,10);// coolecting 10 digits, diplaying collected digits at cur pos 10
			strncpy (&LPaymentDetails[0],&kbd_input_bfr[0],10);
			}			
		else
			{
			strncpy (&LPaymentDetails[0],"Bill      ",10);
			strncpy (&temp_bfr[0],"NetAmtDuem ",10);
			strncpy (&temp_bfr[10],&Lnetamountdue[0],10);
			lcd_display_line(&temp_bfr[0],3,0);
			}
		lcd_display_line("AmtPaid             ",3,0);
		c1 = get_RR_No (10,&kbd_input_bfr[0],1,10);// coolecting 10 digits, diplaying collected digits at cur pos 10
		strncpy (&LAmountPaid[0],&kbd_input_bfr[0],10);
		lcd_display_line("PrintReceipt ENT/ESC",4,0);
		if (get_enter_esc_key () == ENTER_KEY ) print_cash_receipt ();
		else return ();
		}
	}
//***************************************************
*/

//*********************************************************
compute_report ()
{
unsigned int temp;
unsigned char c1;
float tempf;
long templ;
char *temp_ptr;
temp = 0;

//char LLStartReceiptNo[10];
//float LAmountPaid_float;
//float noof_receipts_rised;
//float total_cash_amount;
//float total_dd_amount;
//float total_cancelled_receipts;
//float total_cancelled_amount;
//float grand_total;

temp_ptr = (char *) eflash_consumer_ptr;
noof_bills_rised = 0;
eflash_consumer_ptr = (struct all_data *) dflash_consumer;
while (temp < no_of_consumer_records)
	{
//	load_all_parameters_to_local_buffer ();
	if ((unsigned char) *eflash_consumer_ptr->units != 0xff &&  ((unsigned char) *eflash_consumer_ptr->bill_status == 0xfc))
		{
		noof_bills_rised++;
		}
	eflash_consumer_ptr++;
	temp++;
	}
eflash_consumer_ptr = (struct all_data *)temp_ptr;
}
/*
initialise_system (char flag)
{
unsigned long temp;

rtc_downloaded = 'N';
eflash_other_ptr = (struct otherdata *) dflash_other;
backlightintensity = 225;
eflash_miscCESC_ptr = (struct miscdata1 *) dflash_misc;
eflash_consumerdatavalue_ptr = (struct consumerdatavalue *) (dflash_consumer+consumer_data_length);
no_of_consumer_bytes = eatol (eflash_consumerdatavalue_ptr-> Sno_of_consumer_bytes);
if (no_of_consumer_bytes == 0xffffffff) no_of_consumer_bytes = 0;
no_of_consumer_records = eatol (eflash_consumerdatavalue_ptr->Sno_of_consumer_records);
if (no_of_consumer_records == 0xffffffff || no_of_consumer_records > 10000) no_of_consumer_records = 0;//*-*-*
estrncpy (&temp_bfr[0],eflash_miscCESC_ptr->Consumer_record_lenth,3);
temp_bfr[3] = 0;;
LConsumer_record_length =  atol (&temp_bfr[0]);
eflash_tariffdatavalue_ptr = (struct tariffdatavalue *) (dflash_tariff+tariff_data_length);
no_of_tariff_bytes = eatol(eflash_tariffdatavalue_ptr->Sno_of_tariff_bytes);
if (no_of_tariff_bytes == 0xffffffff) no_of_tariff_bytes = 0;
no_of_tariff_records = eatol(eflash_tariffdatavalue_ptr-> Sno_of_tariff_records);
if (no_of_tariff_records == 0xff) no_of_tariff_records = 0;  //*-*-*
contrast_level = eflash_other_ptr -> contrast;
if (contrast_level > 0x70) contrast_level = 0x70;
//PRINTERTYPE = EPSON190G;
if (*eflash_other_ptr->UNIT_slno == 0xFF)
	{
	lcd_clear_display ();
	lcd_display_line("UnitSlnoNotInitialsd",1,0);
	lcd_display_line("EnterSlNo                ",2,0);
	get_key_entry (19,'K',8,&kbd_input_bfr[0],2,12);
	update_to_external_flash_n ((char *)eflash_other_ptr->UNIT_slno,(char *)&kbd_input_bfr[0],8);
	}
back_light = 0xff;	// backlight OFF
back_light_count = 0;
estrncpy (&Lmeter_reader_code[0],eflash_miscCESC_ptr-> Meterreader_code,10);   
estrncpy (&LLreceipt_no[0],eflash_miscCESC_ptr-> Receipt_NO,9);
estrncpy (&LLbill_no[0],eflash_miscCESC_ptr ->Bill_No,10);
load_current_billno_receiptno ();
print_heading = eflash_miscCESC_ptr->Print_heading[0];
Lprinted_stationery = eflash_miscCESC_ptr ->printed_stationery;	// not printed stationery
Lprintbarcode = eflash_miscCESC_ptr ->printbarcode;
Llogo_info_ptr-=3;// pointing to logo_y
temp_bfr[0] = *Llogo_info_ptr++;
temp_bfr[1] = *Llogo_info_ptr++;
temp_bfr[2] = *Llogo_info_ptr++;
temp_bfr[3] = '\0';
Llogo_y_i = atoi (&temp_bfr[0]);
load_first_valid_sc_no ();
flash_overwrite = 'N';
graphics = 'N';
}
*/
const char *function_key_menu[]  =  { 	
										"A UNIT Sl.No        ",
										"B SOFTWARE VER      ",
										"D DIAGNOSTIC WINDOW ",
										"E.CONSUMER LOAD GPRS",
										"N.DUTY CLOSE        ",
										"P.ERASE CONSUMER    ",
										"R.ACTIVATE GPRS     ",
										"S.SEND BILLS GPRS   ",										
										"W.GPRS OPTIONS      ",
										"Z SYSTEM PARAMETERS ",
									};										
process_function_key ()
{
unsigned char tem_b[1];
unsigned char pass[5];
key_buz_enabled = 'Y';
key_pressed = display_menu_and_select((char *)function_key_menu,10);
//if (key_pressed == '1') concession_ticket ();
if (key_pressed == 'A') 
	{
	lcd_clear_display ();
	clear_temp_bfr ();
	strncpy (&temp_bfr[0],"UNIT No:",8);
	estrncpy (&temp_bfr[10],(char *)eflash_other_ptr->UNIT_slno,8);
	lcd_display_line(&temp_bfr[0],1,0);
	get_esc_key ();
	}
else if (key_pressed == 'B') 
	{
	lcd_clear_display ();
	lcd_display_line(&software_ver[0],1,0);
	lcd_display_line((char *)&QP462732C_LIBRARIES_VER[0], 2, 0);
					
///	lcd_display_line(&QP4632C_LIBRARIES_VER[0],2,0);
////	lcd_display_line((char *)&motfileloader_ver[0], 4, 0);
	get_esc_key ();
	}

else if (key_pressed == 'D')
	{
	lcd_clear_display();
	lcd_display_line("  DIAGONOSTIC MENU:", 1,0);
	lcd_display_line("1. BATCH INFO:", 2,0);
	
	flag_batch_created = (unsigned char *)flag_batch_created_add;
	if((unsigned char)*flag_batch_created == 'Y')
		{
		lcd_display_line("BATCH IS CREATED  ", 3,0);
		lcd_display_line("AVAILABLE CONS = ", 4,0);
		batch_created_cnt = (unsigned char *)batch_created_cnt_addr;
		clear_bfr(temp_bfr, 10);
		strncpy(&temp_bfr[0], (char *)&batch_created_cnt[0], 4);
		lcd_display_line(temp_bfr, 4,18);
		}
	else 
		lcd_display_line("BATCH NOT CREATED!!", 4,0);
		
	get_key();
	}
else if(key_pressed == 'E')
	{
	lcd_clear_display();
	lcd_display_line("PRESS ENTER KEY to", 1, 0);
	lcd_display_line("LOAD CONSUMER GPRS", 2, 0);
	key_pressed = get_key();
	if(key_pressed == ENTER_KEY)
		{
		flag_consumer_load_manuall = 'Y';
		}
	else
		{
			lcd_clear_display();
			lcd_display_line("OPERATION CANCELLED", 2, 0);
			get_key();
			return;
		}
	}

else if(key_pressed == 'S')
	{
	lcd_clear_display();
	lcd_display_line("PRESS ENTER KEY to", 1, 0);
	lcd_display_line("SEND PENDING BILLS", 2, 0);
	key_pressed = get_key();
	if(key_pressed == ENTER_KEY)
		{
		manual_send_bill_flag = 'Y';
		}
	else
		{
			lcd_clear_display();
			lcd_display_line("OPERATION CANCELLED", 2, 0);
			get_key();
			return;
		}
	}	
else if (key_pressed == 'C') 
	{
	lcd_clear_display ();
	clear_temp_bfr ();
	strncpy (&temp_bfr[0],"DEPOTEBTM:",10);
//	strncpy (&temp_bfr[10],(char *)eflash_miscCESC_ptr->DEPOTETMNO,8);
	lcd_display_line(&temp_bfr[0],1,0);
	get_esc_key ();
	}
	else if (key_pressed == 'W'){
		clear_bfr(pass,4);
		lcd_clear_display();
		lcd_display_line("PASSWORD:",1,0);
		key_pressed = get_key_entry (20,'P',5,(char *)&pass[0],2,10);
	
		if(!strncmp((char *)pass,"56789",5)){
			GPRS_function_options_menu();
		}
		else{
			lcd_display_line("PASSWORD INVALID",1,0);
			get_key();
		}
	}
else if (key_pressed == 'R'){
	lcd_clear_display();
	lcd_display_line("ENTER to ENABLE GSM", 1, 0);
	key_pressed = get_key();
	if(key_pressed == ENTER_KEY)
		{
		flash_overwrite = 'Y';
		tem_b[0] = 0x80;
		write_to_external_flash_n(&Log_date_time_ptr->Disble_GSM_flag_flash[0], (char *)&tem_b, 1); 
		lcd_display_line("GSM ACTIVATED      ", 1, 0);
		get_key();
		}
	else return;
	
}	
else if (key_pressed == 'H'){
		if(DEBUG_FLAG == 0x01) DEBUG_FLAG = 0;
		else if(DEBUG_FLAG == 0x00) DEBUG_FLAG = 1;
}
else if (key_pressed == 'P'){
	clear_bfr(pass,4);
	lcd_clear_display();
	lcd_display_line("PASSWORD:",1,0);
	key_pressed = get_key_entry (20,'P',7,(char *)&pass[0],2,10);
	if(!strncmp((char *)pass,"CESC123",7)){
		Erase_consumer_data_manually();
	}
}
else if(key_pressed == 'N'){
	Manual_DutyClosed();
}
//else if (key_pressed == 'D') clean_printer_head ();
/*
else if (key_pressed == 'E') 
		{
		lcd_clear_display ();
//		compute_totals ('A');
//		sprintf (&temp_bfr[0],"CumETMTOT:%10.2f",(float_adult_amount+float_child_amount+float_lug_amount+float_pw_amount+float_journalist_amount)+atof (eflash_miscCESC_ptr->CUMULATIVE_TOTAL));
		lcd_display_line(&temp_bfr[0],1,0);
		get_esc_key ();
		}
else if (key_pressed == 'F') 
		{
		lcd_clear_display ();
		clear_temp_bfr ();
		strncpy (&temp_bfr[0],"RouteID",7);
		lcd_display_line(&temp_bfr[0],1,0);
//		strncpy (&temp_bfr[0],eflash_op_details_ptr->RouteID,20);
		lcd_display_line(&temp_bfr[0],2,0);
		get_esc_key ();
		}
		*/
//else if (key_pressed == 'V') configure_EBTM_private ();
//else if (key_pressed == '4') print_report ();
//else if (key_pressed == 'W') upload_fm_pc ('0');
//else if (key_pressed == 'X') send_data_to_pc ('0');
//else if (key_pressed == 'Y') configure_EBTM ();
else if (key_pressed == 'Z') setup_hhtp  (); 

}

//***************************************************************************************

const char *enter_disp_menu_function[] = {	
								"1 GPRS/ISP ACCESS   ",
								"2 SERVER/IP ACESS   ",
								"3 CHANGE STATUS DLY ",
								"4 CHANGE BILLS SENT ",
								"5 CHANGE DEBUG FLAG ",
						 };



GPRS_function_options_menu() 
{
lcd64128size = '2';
//dflash_misc = 0x0006B0000;
//eflash_miscCESC_ptr = (struct miscdata_CESC *) dflash_miscCESC;
//strcpy(ip_address ,"121,243,174.59");
key_pressed = display_menu_and_select((char *)enter_disp_menu_function, 5) ; 
if		(key_pressed  == '1') 			get_GPRS_ISP_access_point_name() ;
else if	(key_pressed  == '2') 			get_server_ip_and_port() ;
else if	(key_pressed  == '3')		    change_status_delay_in_sec ();
else if	(key_pressed  == '4')			change_nof_bills_oneshot() ;
else if	(key_pressed  == '5')
	{
	if (DEBUG_FLAG == 1)
		{
		lcd_clear_display ();	
		lcd_display_line ("PRESENTLY ENABLED", 1, 1);
		lcd_display_line ("PRES ENTR TO DISABLE", 2, 1);
		key_pressed = get_key ();
		if (key_pressed == ENTER_KEY)
			{
			DEBUG_FLAG = 0;
//			u0brg = 12 ; 			commented on 16-11-2009
			}
		}
	else if (DEBUG_FLAG == 0)
		{
		lcd_clear_display ();	
		lcd_display_line ("PRESENTLY DISABLED ", 1, 1);
		lcd_display_line ("PRES ENTR TO ENABLE", 2, 1);
		key_pressed = get_key ();
		if (key_pressed == ENTER_KEY)
			{
			DEBUG_FLAG = 1;
//NB		u0brg = 12;
			}
		}
	}
}
//*********************************************************
void blank_display_line (char c1)
{
lcd_display_line ("                    ",c1,0);
}
//*********************************************************
summary_report ()
{
key_pressed = display_menu_and_select ((char *)menu6,4);
//key_pressed=get_key();
if(key_pressed=='1') print_summary_report();
else if(key_pressed=='2')print_details_report();
else if(key_pressed=='3') instnotread_report();
//else if(key_pressed =='4') report();
}
//*********************************************************
print_details_report()
{
unsigned int i_temp,temp,noof_consumers;
unsigned char c1;
float tempf;
long templ;
unsigned int meter_reading_type[5];
unsigned int noftariffs;

lcd_clear_display();
lcd_display_line("PRINT DETAIL REPORT",1,0);
key_pressed = get_key ();
if (key_pressed != ENTER_KEY ) return;
//if (get_enter_esc_key () == ENTER_KEY );

noftariffs = 0;
meter_reading_type[0] = 0;
meter_reading_type[1] = 0;
meter_reading_type[2] = 0;
meter_reading_type[3] = 0;
meter_reading_type[4] = 0;

temp = 0;
noof_bills_rised = 0;
noof_bills_printed = 0;
noof_bills_notprinted = 0;
noof_bills_uploaded = 0;
total_no_units_demanded = 0;
total_amount_demanded = 0;
eflash_consumer_ptr = (struct all_data *) dflash_consumer;
while (temp < no_of_consumer_records)
	{
	load_all_parameters_to_local_buffer ();
	strncpy (&temp_bfr[0],&Lunits[0],10);
	temp_bfr[10]= '\0';
 	templ = atol (&temp_bfr[0]);
	if ((unsigned char) Lbill_status[0] != 0xff)
		{
		if ((unsigned char )Lbill_status[0] == 0xfc ) noof_bills_printed++;
		if ((unsigned char )Lbill_status[0] == 0xfd ) noof_bills_notprinted++;
		if ((unsigned char )Lbill_status[0] == 0x7c ) noof_bills_uploaded++;
		noof_bills_rised++;
		total_no_units_demanded += templ;
		meter_reading_type[Lmeterreadingtype[0]-'1']++;
		strncpy (&temp_bfr[0],&Lnetamountdue[0],10);
		temp_bfr[10]= '\0';
	 	tempf = atof (&temp_bfr[0]);
		total_amount_demanded += tempf;
		}
	eflash_consumer_ptr++;
	temp++;
	}
strncpy (&print_line_bfr[0],"     SUMMARY REPORT     ",24);
print_line_double_height (0);
print_time_date ();
strncpy(&print_line_bfr[0],"MetReadr ",9);
strncpy (&print_line_bfr[9],&Lmeter_reader_code[0],7);
estrncpy (&print_line_bfr[16],(char *)eflash_other_ptr->UNIT_slno,8);
print_line (0);
print_dotted_line ();
sprintf(&print_line_bfr[0],"TotNo.Con %10.0f",(float)no_of_consumer_records);
print_line (0);
sprintf(&print_line_bfr[0],"TotRised  %10.0f",(float)noof_bills_rised);
print_line (0);
sprintf(&print_line_bfr[0],"TotNotRisd%10.0f",((float)no_of_consumer_records - (float)noof_bills_rised));
print_line (0);
sprintf(&print_line_bfr[0],"TotUnits  %10.0f",(float)total_no_units_demanded);
print_line (0);
sprintf(&print_line_bfr[0],"TotRevenue%10.0f",(float)total_amount_demanded);
print_line (0);
sprintf(&print_line_bfr[0],"TotPrinted%10.0f",(float)noof_bills_printed);
print_line (0);
sprintf(&print_line_bfr[0],"TotStPrint%10.0f",(float)noof_bills_notprinted);
print_line (0);
sprintf(&print_line_bfr[0],"TotUploded%10.0f",(float)noof_bills_uploaded);
print_line (0);
blank_print_bfr ();
strncpy (&print_line_bfr[0]," Norml DorLK M N R  D C ",24);
print_line (0);
blank_print_bfr ();
sprintf (&print_line_bfr[0],"%.4d %.4d %.4d %.4d %.4d",meter_reading_type[0],meter_reading_type[1],meter_reading_type[2],meter_reading_type[3],meter_reading_type[4]);
print_line(0);
print_dotted_line ();


eflash_consumer_ptr = (struct all_data *) dflash_consumer;
eflash_tariff_ptr = (struct tariff_table *) dflash_tariff;
while (noftariffs < no_of_tariff_records)
	{
	if (key_bfr_count) if (get_key () == ESC_KEY) break; 
	estrncpy (&Lttariff_code[0],eflash_tariff_ptr->tariff_code,7);
	Lttariff_code[6] = '\0';
	i_temp = atoi (&Lttariff_code[0]);
	eflash_consumer_ptr = (struct all_data *) dflash_consumer;
	temp = 0;
	noof_bills_rised = 0;
	total_no_units_demanded = 0;
	total_amount_demanded = 0;
	noof_consumers  = 0;
	meter_reading_type[0] = 0;
	meter_reading_type[1] = 0;
	meter_reading_type[2] = 0;
	meter_reading_type[3] = 0;
	meter_reading_type[4] = 0;
	while (temp < no_of_consumer_records)
		{
		load_all_parameters_to_local_buffer ();	
		Ltariff_code_i = atol (&LTariff[0]);	// from consumer structure
		if (Ltariff_code_i == i_temp)
			{
			strncpy (&temp_bfr[0],&Lunits[0],10);
			temp_bfr[10]= '\0';
		 	templ = atol (&temp_bfr[0]);
			noof_consumers++;
			if ((unsigned char) Lunits[0] != 0xff)
				{
				noof_bills_rised++;
				total_no_units_demanded += templ;
				strncpy (&temp_bfr[0],&Lnetamountdue[0],10);
				temp_bfr[10]= '\0';
			 	tempf = atof (&temp_bfr[0]);
				total_amount_demanded += tempf;
				meter_reading_type[Lmeterreadingtype[0]-'1']++;
				}
			}
		eflash_consumer_ptr++;
		temp++;
		}
	if (noof_consumers)
		{
		if (key_bfr_count) if (get_key () == ESC_KEY) break; 
		strncpy (&temp_bfr[0],"TariffType ",11);
		estrncpy (&temp_bfr[11],eflash_tariff_ptr->tariff,9);		
		lcd_display_line(&temp_bfr[0],2,0);
		blank_print_bfr ();
		estrncpy (&print_line_bfr[0],eflash_tariff_ptr->tariff,9);
		strncpy (&print_line_bfr[10],"NoOfCon.",8);
		sprintf (&print_line_bfr[20],"%.4d",noof_consumers);
		print_line (0);
		blank_print_bfr ();
		strncpy (&print_line_bfr[0],"BilledRised",11);
		sprintf (&print_line_bfr[16],"%.4d",(unsigned int)noof_bills_rised);
		print_line_bfr[20] = ' ';
		print_line (0);
		sprintf(&print_line_bfr[0],"Units         %10.0f",(float)total_no_units_demanded);
		print_line (0);
		sprintf(&print_line_bfr[0],"Revenue       %10.0f",(float)total_amount_demanded);
		print_line (0);
		blank_print_bfr ();
		strncpy (&print_line_bfr[0]," Norml DorLK M N R  D C ",24);
		print_line (0);
		blank_print_bfr ();
		sprintf (&print_line_bfr[0],"  %.4d  %.4d  %.4d  %.4d",meter_reading_type[0],meter_reading_type[1],meter_reading_type[2],meter_reading_type[3]);
		print_line(0);
		print_dotted_line ();
		}
		//..noftariffs+=2;
		//..eflash_tariff_ptr+=2;
		noftariffs+=2;
		//..eflash_tariff_ptr+=1;
		eflash_tariff_ptr = (struct tariff_table *)((unsigned long)eflash_tariff_ptr+440);
	}
print_dotted_line ();
}
//*********************************************************
/*print_details_report()
{
unsigned int i_temp,temp,noof_consumers;
unsigned char c1;
float tempf;
long templ;
unsigned int meter_reading_type[5];
unsigned int noftariffs;

lcd_clear_display();
lcd_display_line("Print Details Report",1,0);
if (get_enter_esc_key () == ENTER_KEY )
noftariffs = 0;
meter_reading_type[0] = 0;
meter_reading_type[1] = 0;
meter_reading_type[2] = 0;
meter_reading_type[3] = 0;
meter_reading_type[4] = 0;

temp = 0;
noof_bills_rised = 0;
noof_bills_printed = 0;
noof_bills_notprinted = 0;
noof_bills_uploaded = 0;
total_no_units_demanded = 0;
total_amount_demanded = 0;
eflash_consumer_ptr = (struct all_data *) dflash_consumer;
while (temp < no_of_consumer_records)
	{
	load_all_parameters_to_local_buffer ();
	strncpy (&temp_bfr[0],&Lunits[0],10);
	temp_bfr[10]= '\0';
 	templ = atol (&temp_bfr[0]);
	if ((unsigned char) Lbill_status[0] != 0xff)
		{
		if ((unsigned char )Lbill_status[0] == 0xfc ) noof_bills_printed++;
		if ((unsigned char )Lbill_status[0] == 0xfd ) noof_bills_notprinted++;
		if ((unsigned char )Lbill_status[0] == 0x7c ) noof_bills_uploaded++;
		noof_bills_rised++;
		total_no_units_demanded += templ;
		meter_reading_type[Lmeterreadingtype[0]-'1']++;
		strncpy (&temp_bfr[0],&Lnetamountdue[0],10);
		temp_bfr[10]= '\0';
	 	tempf = atof (&temp_bfr[0]);
		total_amount_demanded += tempf;
		}
	eflash_consumer_ptr++;
	temp++;
	}
strncpy (&print_line_bfr[0],"     SUMMARY REPORT     ",24);
print_line_double_height (0);
print_time_date ();
strncpy(&print_line_bfr[0],"MetReadr ",9);
strncpy (&print_line_bfr[9],&Lmeter_reader_code[0],7);
estrncpy (&print_line_bfr[16],(char *)eflash_other_ptr->UNIT_slno,8);
print_line (0);
print_dotted_line ();

sprintf(&print_line_bfr[0],"TotNo.Cons %10.0f",(float)no_of_consumer_records);
print_line (0);
blank_print_bfr();
sprintf(&print_line_bfr[0],"TotRised   %10.0f",(float)noof_bills_rised);
print_line (0);
blank_print_bfr();
sprintf(&print_line_bfr[0],"TotNotRisd %10.0f",((float)no_of_consumer_records - (float)noof_bills_rised));
print_line (0);
blank_print_bfr();
sprintf(&print_line_bfr[0],"TotUnits   %10.0f",(float)total_no_units_demanded);
print_line (0);
blank_print_bfr();
sprintf(&print_line_bfr[0],"TotRevenue %10.0f",(float)total_amount_demanded);
print_line (0);
blank_print_bfr();
sprintf(&print_line_bfr[0],"TotPrinted %10.0f",(float)noof_bills_printed);
print_line (0);
blank_print_bfr();
sprintf(&print_line_bfr[0],"TotStpPrint%10.0f",(float)noof_bills_notprinted);
print_line (0);
blank_print_bfr();
sprintf(&print_line_bfr[0],"TotUploaded%10.0f",(float)noof_bills_uploaded);
print_line (0);
blank_print_bfr ();
strncpy (&print_line_bfr[0]," Norml DorLK M N R  D C ",24);
print_line (0);
blank_print_bfr ();
sprintf (&print_line_bfr[0],"%.4d %.4d %.4d %.4d %.4d",meter_reading_type[0],meter_reading_type[1],meter_reading_type[2],meter_reading_type[3],meter_reading_type[4]);
print_line(0);
print_dotted_line ();


eflash_consumer_ptr = (struct all_data *) dflash_consumer;
eflash_tariff_ptr = (struct tariff_table *) dflash_tariff;
while (noftariffs < no_of_tariff_records)
	{
	estrncpy (&Lttariff_code[0],eflash_tariff_ptr->tariff_code,7);
	Lttariff_code[6] = '\0';
	i_temp = atoi (&Lttariff_code[0]);
	eflash_consumer_ptr = (struct all_data *) dflash_consumer;
	temp = 0;
	noof_bills_rised = 0;
	total_no_units_demanded = 0;
	total_amount_demanded = 0;
	noof_consumers  = 0;
	meter_reading_type[0] = 0;
	meter_reading_type[1] = 0;
	meter_reading_type[2] = 0;
	meter_reading_type[3] = 0;
	meter_reading_type[4] = 0;
	while (temp < no_of_consumer_records)
		{
		load_all_parameters_to_local_buffer ();	
		Ltariff_code_i = atol (&LTariff[0]);	// from consumer structure
		if (Ltariff_code_i == i_temp)
			{
			strncpy (&temp_bfr[0],&Lunits[0],10);
			temp_bfr[10]= '\0';
		 	templ = atol (&temp_bfr[0]);
			noof_consumers++;
			if ((unsigned char) Lunits[0] != 0xff)
				{
				noof_bills_rised++;
				total_no_units_demanded += templ;
				strncpy (&temp_bfr[0],&Lnetamountdue[0],10);
				temp_bfr[10]= '\0';
			 	tempf = atof (&temp_bfr[0]);
				total_amount_demanded += tempf;
				meter_reading_type[Lmeterreadingtype[0]-'1']++;
				}
			}
		eflash_consumer_ptr++;
		temp++;
		}
	if (noof_consumers)
		{
		strncpy (&temp_bfr[0],"TariffType ",11);
		estrncpy (&temp_bfr[11],eflash_tariff_ptr->tariff,9);		
		lcd_display_line(&temp_bfr[0],2,0);
		blank_print_bfr ();
		estrncpy (&print_line_bfr[0],eflash_tariff_ptr->tariff,9);
		strncpy (&print_line_bfr[10],"NoOfCon.",8);
		sprintf (&print_line_bfr[20],"%.4d",noof_consumers);
		print_line (0);
		blank_print_bfr ();
		strncpy (&print_line_bfr[0],"BilledRised",11);
		sprintf (&print_line_bfr[16],"%.4d",(unsigned int)noof_bills_rised);
		print_line_bfr[20] = ' ';
		print_line (0);
		sprintf(&print_line_bfr[0],"Units         %10.0f",(float)total_no_units_demanded);
		print_line (0);
		sprintf(&print_line_bfr[0],"Revenue       %10.0f",(float)total_amount_demanded);
		print_line (0);
		blank_print_bfr ();
		strncpy (&print_line_bfr[0]," Norml DorLK M N R  D C ",24);
		print_line (0);
		blank_print_bfr ();
		sprintf (&print_line_bfr[0],"  %.4d  %.4d  %.4d  %.4d",meter_reading_type[0],meter_reading_type[1],meter_reading_type[2],meter_reading_type[3]);
		print_line(0);
		print_dotted_line ();
		}
		noftariffs+=2;
		eflash_tariff_ptr+=2;
	}

print_dotted_line ();
}*/
//*********************************************************
instnotread_report()
{
unsigned int i_temp,temp,noof_consumers;
unsigned char c1;
float tempf;
long templ;
unsigned int meter_reading_type[5];
unsigned int noftariffs;
char *temp_ptr;
temp = 0;

lcd_clear_display();
lcd_display_line("UNBILLED REPORT",1,0);
key_pressed = get_key ();
if (key_pressed != ENTER_KEY )return;
//if (get_enter_esc_key () == ENTER_KEY );

noftariffs = 0;
meter_reading_type[0] = 0;
meter_reading_type[1] = 0;
meter_reading_type[2] = 0;
meter_reading_type[3] = 0;
meter_reading_type[4] = 0;

temp = 0;
noof_bills_rised = 0;
noof_bills_printed = 0;
noof_bills_notprinted = 0;
noof_bills_uploaded = 0;
total_no_units_demanded = 0;
total_amount_demanded = 0;
eflash_consumer_ptr = (struct all_data *) dflash_consumer;
while (temp < no_of_consumer_records)
	{
	load_all_parameters_to_local_buffer ();
	strncpy (&temp_bfr[0],&Lunits[0],10);
	temp_bfr[10]= '\0';
 	templ = atol (&temp_bfr[0]);
	if ((unsigned char) Lbill_status[0] != 0xff)
		{
		if ((unsigned char )Lbill_status[0] == 0xfc ) noof_bills_printed++;
		if ((unsigned char )Lbill_status[0] == 0xfd ) noof_bills_notprinted++;
		if ((unsigned char )Lbill_status[0] == 0x7c ) noof_bills_uploaded++;
		noof_bills_rised++;
		total_no_units_demanded += templ;
		meter_reading_type[Lmeterreadingtype[0]-'1']++;
		strncpy (&temp_bfr[0],&Lnetamountdue[0],10);
		temp_bfr[10]= '\0';
	 	tempf = atof (&temp_bfr[0]);
		total_amount_demanded += tempf;
		}
	eflash_consumer_ptr++;
	temp++;
	}
strncpy (&print_line_bfr[0],"   Billed/Unbilled REPORT     ",24);
print_line_double_height (0);
print_time_date ();
strncpy(&print_line_bfr[0],"MetReadr ",9);
strncpy (&print_line_bfr[9],&Lmeter_reader_code[0],7);
estrncpy (&print_line_bfr[16],(char *)eflash_other_ptr->UNIT_slno,8);
print_line (0);
print_dotted_line ();
sprintf(&print_line_bfr[0],"TotNo.Con %10.0f",(float)no_of_consumer_records);
print_line (0);
sprintf(&print_line_bfr[0],"TotRised  %10.0f",(float)noof_bills_rised);
print_line (0);
sprintf(&print_line_bfr[0],"TotNotRisd%10.0f",((float)no_of_consumer_records - (float)noof_bills_rised));
print_line (0);
print_dotted_line ();


eflash_consumer_ptr = (struct all_data *) dflash_consumer;
while (temp < no_of_consumer_records)
	{
	load_all_parameters_to_local_buffer ();
	
	if ((unsigned char) Lbill_status[0] == 0xff)
		{
			 lcd_clear_display();
			 estrncpy (&temp_bfr[0],eflash_consumer_ptr->Consumer_SC_No,15);
			 lcd_display_line (&temp_bfr[0],1,0);
			 get_key();
	
		}
	eflash_consumer_ptr++;
	temp++;
	}
	
	
	
	temp_ptr = (char *) eflash_consumer_ptr;
	noof_bills_rised = 0;
	eflash_consumer_ptr = (struct all_data *) dflash_consumer;
	while (temp < no_of_consumer_records)
	{
	//	load_all_parameters_to_local_buffer ();
	if ((unsigned char) *eflash_consumer_ptr->units != 0xff &&  ((unsigned char) *eflash_consumer_ptr->bill_status == 0xfc))
		{
		noof_bills_rised++;
		}
		else
		{
			 lcd_clear_display();
			 estrncpy (&temp_bfr[0],eflash_consumer_ptr->Consumer_SC_No,15);
			 lcd_display_line (&temp_bfr[0],1,0);
			 get_key();		
			
		}
	eflash_consumer_ptr++;
	temp++;
	}
	eflash_consumer_ptr = (struct all_data *)temp_ptr;
	
	

}


//*********************************************************
unsigned char get_eflash_consumer_ptr_bill_status(char * local_eflash_ptr)
{
	unsigned char c;
 	eflash_consumer_ptr = (struct all_data *) local_eflash_ptr;
 	c=*eflash_consumer_ptr->bill_status;
 	if(c != 0xfc) return c;
 	return 0xfc;
}
//*********************************************************
void erase_consumer (char falg)
{
	
	
}

initialise_system2 ()
{
unsigned long temp;
//struct *eflash_tarrif_ptr;
struct all_data *ptr;
struct tariff_table *ptr1;
unsigned char *temp_ptr;
no_of_consumer_records = 0;
no_of_consumer_bytes = 0;
no_of_tariff_records = 0;
no_of_tariff_bytes = 0;


eflash_miscCESC_ptr = (struct miscdata_CESC *) dflash_miscCESC;
//eflash_tarrif_ptr = (struct miscdata *) dflash_tariff;
ptr = (struct all_data *) (dflash_consumer);
temp_ptr = (unsigned char *) (dflash_tariff);
while((unsigned char)*ptr->Consumer_SC_No != 0xFF){
	ptr++;
	no_of_consumer_records += 1;
	no_of_consumer_bytes += sizeof(struct all_data);
}
LConsumer_record_length = sizeof(struct all_data);

ptr1 = (struct tariff_table *)dflash_tariff;
if(strncmp(( char *)&ptr1->tdate[0],( char *)"01.05.2014",10))
{
	sector_erase_external_flash (0,(unsigned char *)0x6C0000,'Y');
	write_to_external_flash_n ( ( char *)temp_ptr,( char *)&tariff_inline_code[0],sizeof(tariff_inline_code));
}

ptr1 = (struct tariff_table *)dflash_tariff;
while((unsigned char)*ptr1->tdate != 0xFF){
	ptr1++;
	no_of_tariff_records += 1;
	no_of_tariff_bytes += sizeof(struct tariff_table);
}



}

extern struct all_data  *eflash_consumer_ptr_temp;
send_billed_data()
{
	unsigned long con_cnt;
	int index;
	unsigned char *bill_ptr;
	
	unsigned char c_d[2];
	bill_ptr = (unsigned char *)dflash_consumer;
	
	
	eflash_consumer_ptr_temp = (struct all_data *)dflash_consumer;
	con_cnt = get_consumer_count();
	while(con_cnt--)
	{
		if((unsigned char)*eflash_consumer_ptr_temp->bill_status == 0xFC)
		{
			for(index=0; index<sizeof(struct all_data); index++)
			{
				send_rs232_fifo_URT0((unsigned char)*bill_ptr++);
				//ch_chek += *bill_ptr++;
			}
			bill_ptr -= sizeof(struct all_data);
		}
		bill_ptr += sizeof(struct all_data);
		eflash_consumer_ptr_temp++;
		
	}
	//ch_chek -= 10;
	conv_ascii2hexstring ((unsigned long )ch_chek ,&c_d[1],2);
	send_rs232_fifo_URT0((unsigned char)' ');
	send_rs232_fifo_URT0((unsigned char)c_d[0]);
	send_rs232_fifo_URT0((unsigned char)c_d[1]);
}

check_billed_data()
{
	unsigned long con_cnt;
	int index;
	unsigned char *bill_ptr;
	
	bill_ptr = (unsigned char *)dflash_consumer;
	
	eflash_consumer_ptr_temp = (struct all_data *)dflash_consumer;
	con_cnt = get_consumer_count();
	
	ch_chek = 0;
	while(con_cnt--)
	{
		if((unsigned char)*eflash_consumer_ptr_temp->bill_status == 0xFC)
		{
			for(index=0; index<sizeof(struct all_data); index++)
			{
				ch_chek += *bill_ptr++;
			}
			bill_ptr -= sizeof(struct all_data);
		}
		
		bill_ptr += sizeof(struct all_data);
		eflash_consumer_ptr_temp++;
	
	}
	
}
float near_qrt(float curnx,int type)////king1
  {
   float avg_loadx;

   avg_loadx = 0;

 /*
 	clrscr();
	sprintf(ftemp,"DD%10.2fMF%03d",curnx,keb.MF);
	printf(ftemp,17);
	get_key();   
*/

	if(curnx > 0)
	{
	      if(type == 0)
	      {
	        curnx = ((curnx * Lmeter_constant_float) / 0.746);
	   		avg_loadx=curnx - (unsigned long)curnx;
	       }
	       if(type == 1)
	       {
	        curnx = ((curnx) * 0.746);
	        avg_loadx=curnx - (unsigned long)curnx;
	       }
		   if(type == 2)
	       {
	        //curnx = ((curnx) * keb.MF);
			curnx = ((curnx) * 1 );
	        avg_loadx=curnx - (unsigned long)curnx;
	       }

	   /*gotoxy(2,1);
		sprintf(ftemp,"WA%10.2f",avg_loadx);
		printf(ftemp,12);
			gotoxy(3,1);
	sprintf(ftemp,"QA%10.2f",curnx);
	printf(ftemp,12);
	get_key();  */
	
	        if(avg_loadx>=0.87)
	         avg_loadx=(unsigned long)curnx+1.00;
	        else if(avg_loadx>=0.63)
	         avg_loadx=(unsigned long)curnx+0.75;
	        else if(avg_loadx>=0.37)
	         avg_loadx=(unsigned long)curnx+0.50;
	        else if(avg_loadx>=0.13)
	         avg_loadx=(unsigned long)curnx+0.25;
	        else 
	         avg_loadx=(unsigned long)curnx+0.00;
	        curnx=(float)avg_loadx;


 /*	gotoxy(4,1);
	sprintf(ftemp,"QB%10.2f",curnx);
	printf(ftemp,12);

	get_key();  */

	}
	              return(curnx);
}
float rnd(float amount)
{
             float paise;
			 float loss_gain=0;
			 int flagLG=0;
             unsigned int fill;  // veera 18/05/05 
    fill = 0;
    loss_gain = 0.00;
    if (amount<0)
    {
       amount = -(amount);
       fill=1;
    }
    paise = amount - (unsigned long)amount;
    if(paise >= 0.50)
    {
        amount = amount + (1 - paise);
        loss_gain = 1 - paise;
        flagLG = 0;
    }
    else
    {
        amount = amount - paise;
        loss_gain = paise;
        flagLG = 1;
    }

        if (fill==1)
        {
          amount = -(amount);
        }

    return amount;
}
