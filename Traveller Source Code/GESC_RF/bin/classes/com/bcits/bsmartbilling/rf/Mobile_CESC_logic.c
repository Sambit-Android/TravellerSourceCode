Following are the status
/*--------------------------------*/

// 1 - NORMAL
// 2 - Door Lock  - 
// 3 - MNR
// 4 - Direct Connection
// 5 - Diss Connection
// 6 - IDLE
// 7 - MB- MeterSeal Broken
// 8 - MS - 
// 9 - NV -Not Visible


///////////////// for MNR and DC.. present status would be same as prev status
if (Ldl_or_mnr_prev_month[0] == '3' || Ldl_or_mnr_prev_month[0] == '4'  )
{
	key_pressed = Ldl_or_mnr_prev_month[0];

}



/////////////// expect normal and Diss connections for all other status.. present_reading= prevsious_reading

if (Lstatus_id_c == '2' || Lstatus_id_c == '3' || Lstatus_id_c == '4' || Lstatus_id_c == '6'   || Lstatus_id_c == '7' || Lstatus_id_c == '8' || Lstatus_id_c == '9'  ) 
{
	LPresent_Reading_float = LPrevious_Reading_float;
}

////////////////////////

Add Logic for Meter rotation Case

////////////////////////////////

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

///////////////////




if (Lstatus_id_c == '3' || Lstatus_id_c == '4' || Lstatus_id_c == '2'  || Lstatus_id_c == '7' || Lstatus_id_c == '8' || Lstatus_id_c == '9'  )
 {
 Laverage_consumption_long = atol (&Laverage_consumption[0]);
 Lreading_value_float += Laverage_consumption_long;
 }


//////////////////////// check no -negative consumption
 if(Lreading_value_float <0 )
{
	Lreading_value_float=0;
}

///////////////////////////////




/**********************************************  DONE  ******************************************************************/








/***************************************  DONE  *****************************************/
 //CR6) LT1 and LT4 cases AVG consu should be 0  --> **NO READING ***
 if(Ltariff_code_i == 1 ||  Ltariff_code_i == 2 || Ltariff_code_i == 111 || Ltariff_code_i == 112 || Ltariff_code_i == 113 || Ltariff_code_i == 114  )
 {
	 Lreading_value_float =0;
 } 	   

/////////////////////////////////////


/***************************************  DONE END  *****************************************/





//CR8) DL->IDLE (ADJ amount should be effected)-DONE  
if (( Lstatus_id_c == '1' ||   Lstatus_id_c == '6' ) &&  Ldl_or_mnr_prev_month[0] == '2')
{
 	dl_adj_amount = Ldl_adj_float;
	if (Lstatus_id_c == '6')
	{
		Lstatus_id_c == '1';	
	}
}
else
{
	 dl_adj_amount=0;
} 
///////////////////////////////




//CR7) for DL case also DL_ADJ_GIVEN is getting deducted-DONE-for DL case dl_adj_amount should be zero
if(Lstatus_id_c == '2')
{
	 dl_adj_amount=0;
}

///////////////////////////////////// Calculation for Trivetor meters   *********PENDING ************/

 
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

	/////////////////////////////////////////////////////////

	Ltax_float = 0.06;
if (Ltariff_code_i == 56 || Ltariff_code_i == 59 || Ltariff_code_i == 64 || Ltariff_code_i == 66 ||
 Ltariff_code_i == 105 || Ltariff_code_i == 106 || Ltariff_code_i == 111 || Ltariff_code_i == 112|| Ltariff_code_i == 113 || Ltariff_code_i == 114 || Ltariff_code_i == 115 || Ltariff_code_i == 116  )
{
	Ltax_float = 0.00;
}













////////////////// For BJ/KJ   ******IMP***********/

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
	/*********************************************************************/
	// CR1) For DISs cases -FR should be asked(CONS=FR-IR) -> DONE-> Average calculation is done for 2-DL,3-MNR,4-DC,7-MB,8-MS,9-NV
   if (Lstatus_id_c == '2' )
   {
 	 energy_charges = 0;
   }
    	/* *********************************************************************/
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














//////////////////////////////////////Abnormal  checking//////////////////////////////////////

if(Ltax_float+energy_charges+fixed_charges+Lothers_float+Linterest_float-Lcredit_or_rebate_float >= 5 00 000 && (Ltariff_code_i == 5 || Ltariff_code_i == 52|| Ltariff_code_i == 54|| Ltariff_code_i == 56|| Ltariff_code_i == 58 || Ltariff_code_i == 51 || Ltariff_code_i == 204 || Ltariff_code_i == 50 || Ltariff_code_i == 57 || Ltariff_code_i == 59 || Ltariff_code_i == 53 || Ltariff_code_i == 6 || Ltariff_code_i == 62|| Ltariff_code_i == 64 || Ltariff_code_i == 69 || Ltariff_code_i == 63 || Ltariff_code_i == 65 ))
{					
					 	lcd_clear_display();
						lcd_display_line("Abnormal bill Not Allowed",2,0);
						key_pressed = get_key ();
						return;
} 
 
if(Ltax_float+energy_charges+fixed_charges+Lothers_float+Linterest_float-Lcredit_or_rebate_float >= 50 00 000 )
{					
					 	lcd_clear_display();
						lcd_display_line("Abnormal bill Not Allowed",2,0);
						key_pressed = get_key ();
						return;
} 

//////////////////////////////// 
