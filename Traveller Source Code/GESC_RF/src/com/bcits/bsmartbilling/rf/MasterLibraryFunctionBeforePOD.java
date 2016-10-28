package com.bcits.bsmartbilling.rf;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Utils.DefaultClass;
import Utils.FilePropertyManager;
import Utils.Logger;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.utils.DBTariffHandler;
import com.utils.DatabaseHandler;

public class MasterLibraryFunctionBeforePOD extends Application {

	public Context context;
	public static Logger masterLogger;

	MasterLibraryFunctionBeforePOD() {

	}

	MasterLibraryFunctionBeforePOD(Context context) {
		this.context = context;
	}

	MasterLibraryFunctionBeforePOD(Context context, String tCode) {
		this.context = context;
		DBTariffHandler tariffHandler = new DBTariffHandler(context);
		tariffHandler.openToRead();
		Cursor cursor_next = tariffHandler.getTariffDetails(tCode);
		tariffHandler.close();
		if (cursor_next != null && cursor_next.moveToFirst()) {
			UtilMaster.Lfcslab1 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_FCSLAB1));
			UtilMaster.Lfcslab2 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_FCSLAB2));
			UtilMaster.Lfcslab3 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_FCSLAB3));
			UtilMaster.Lfcslab4 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_FCSLAB4));

			UtilMaster.Lfcrate1 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_FCRATE1));
			UtilMaster.Lfcrate2 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_FCRATE2));
			UtilMaster.Lfcrate3 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_FCRATE3));
			UtilMaster.Lfcrate4 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_FCRATE4));

			UtilMaster.Lecslab1 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB1));
			UtilMaster.Lecslab2 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB2));
			UtilMaster.Lecslab3 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB3));
			UtilMaster.Lecslab4 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB4));
			UtilMaster.Lecslab5 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB5));
			UtilMaster.Lecslab6 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB6));

			UtilMaster.Lecrate1 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE1));
			UtilMaster.Lecrate2 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE2));
			UtilMaster.Lecrate3 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE3));
			UtilMaster.Lecrate4 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE4));
			UtilMaster.Lecrate5 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE5));
			UtilMaster.Lecrate6 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE6));
			UtilMaster.Ltax_rate = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_TAX));

			UtilMaster.LFcReduction = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_FCREDUCTION));
			UtilMaster.LEcReduction = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECREDUCTION));
			UtilMaster.LFcMax = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_FCMAX));
			UtilMaster.LEcMax = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECMAX));

			UtilMaster.mtariffdesc = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_TARIFFDESC));

		} else {
			System.out.println("else block...");
		}
		cursor_next.close();

	}

	/* SETTING TARIFF RATES FOR EC CALCULATION DATED:25 March 2015 BY GURURAJ */

	public boolean setECTariffRates(Context mobContext, int quarter,
			String tCode) {
		boolean status = false;
		DBTariffHandler tariffHandler = new DBTariffHandler(mobContext);
		tariffHandler.openToRead();
		Cursor cursor_next = tariffHandler.getTariffDetails(tCode);
		if (cursor_next != null && cursor_next.moveToPosition(quarter)) {
			UtilMaster.Lecslab1 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB1));
			UtilMaster.Lecslab2 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB2));
			UtilMaster.Lecslab3 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB3));
			UtilMaster.Lecslab4 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB4));
			UtilMaster.Lecslab5 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB5));
			UtilMaster.Lecslab6 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECSLAB6));

			UtilMaster.Lecrate1 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE1));
			UtilMaster.Lecrate2 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE2));
			UtilMaster.Lecrate3 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE3));
			UtilMaster.Lecrate4 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE4));
			UtilMaster.Lecrate5 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE5));
			UtilMaster.Lecrate6 = cursor_next.getString(cursor_next
					.getColumnIndex(DBTariffHandler.KEY_ECRATE6));
			status = true;
		} else {
			System.out.println("else block...");
			status = false;
		}
		cursor_next.close();
		tariffHandler.close();
		return status;
	}

	
	
	/**
	 * @category Master calculation method
	 * @return Calculation completed status
	 * @throws Exception
	 */
	public int calculate() throws Exception {
		int returnVal = 0;
		try {
			float ec = 0;
			float fc = 0;
			// float fppca = 0 ;
			float tax = 0;
			float units = 0;
			float mtrRent = 0;
			float bm = Float.parseFloat(UtilMaster.mno_of_months_issued);
			float arrears = Float.parseFloat(UtilMaster.marrears.trim());
			float others = Float.parseFloat(UtilMaster.mothers.trim());
			float dlAdj = 0;
			float total = 0;
			float credite_rebate = 0;
			float masterInterest = Float
					.parseFloat(UtilMaster.minterest.trim());
			float masterFc = Float.parseFloat(UtilMaster.mfixed_ges.trim());
			float interestondeposit = Float
					.parseFloat(UtilMaster.minterestondeposit.trim());
			float PFPenalty = 0;
			float excessLoadPenalty = 0;
			UtilMaster.setMprevious_billeddate(getPrvDate(-(int) (bm * 30),
					new SimpleDateFormat("MM/dd/yyyy")
							.parse(UtilMaster.mreading_date)));
			float masterPF = Float.parseFloat(UtilMaster.mmaster_Pf_reading
					.trim());
			float sanctionedLoadKW = 0;
			try {
				sanctionedLoadKW = Float.parseFloat(UtilMaster.mconnected_load
						.trim());
			} catch (Exception e) {
				sanctionedLoadKW = 0;
			}

			UtilMaster.mdivision = getDivisionName(Integer
					.parseInt(UtilMaster.mGLogin_SiteCode.trim()));

			if (UtilMaster.mpresentmeterstatus.equalsIgnoreCase("1")) {
				dlAdj = Float.parseFloat(UtilMaster.mdl_adj.trim());
			}
			units = Float.parseFloat(UtilMaster.mconsumption.trim());
			// ec = (float)doubRound (calEC(units, bm) , 2);
			try {
				ec = (float) doubRound(
						getECForAllQuarters(this.context, units, bm,
								UtilMaster.mtariff.trim()), 2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			fc = (float) doubRound(calcFC(units, bm, masterFc), 2);

			if (UtilMaster.mtariff.trim().equalsIgnoreCase("21")) {

				fc = calcFCForLT7TariffOnly(dateDiff(
						"d",
						getPreviousReadDateFromPresent(
								UtilMaster.mreading_date, bm),
						getDateFromString(UtilMaster.mreading_date)));

			}

			// This is WRT the FPPCA dated April 2015 to June 2015
			// FPPCA 0 WRT the JAN2016

			if (bm <= 3) {
				/* others = (float) (units * 0.13); */
				/* others = (float) 0; */

				others = (float) (units * 0.04);

			} else {
				/* others = (float) (((units/bm) * 3)* 0.13); */
				/* others = (float) 0; */

				others = (float) (((units / bm) * 3) * 0.04);

			}

			// tax = (float)doubRound
			// (Float.parseFloat(UtilMaster.Ltax_rate.trim()) * units,2);
			tax = (float) doubRound(
					calTax(ec, (float) 0.06,
							Integer.parseInt(UtilMaster.mtariff.trim()), units,
							bm, others), 2);

			// by shivanand

			if ((UtilMaster.mtariff.trim().equalsIgnoreCase("2")) || (UtilMaster.mextra3.trim().equals("Y"))) {
				tax = 0.0f;
			}

			credite_rebate = (float) doubRound(
					getCredite_rebate(ec, fc, tax,
							Integer.parseInt(UtilMaster.mtariff.trim()), units,
							bm), 2);

			mtrRent = (float) doubRound(Float.parseFloat(UtilMaster.mmeterrent)
					* bm, 2);

			/* BY SHIVANAND */

			if (UtilMaster.mtrivector.trim().equalsIgnoreCase("1")
					|| (!UtilMaster.mpf_reading.trim().equalsIgnoreCase("0.0"))) {
				PFPenalty = (float) ltPFPenalty(UtilMaster.mpf_reading, units,
						masterPF);
				excessLoadPenalty = excessLoadPenalty(UtilMaster.mbmd_reading,
						sanctionedLoadKW, Float.parseFloat(UtilMaster
								.getMmeter_constant().trim()),
						UtilMaster.mtariff.trim());
			}

			// This code will not disturb the tax and other calculations for the
			// LT7

			// By shivanand for the LT 7 billing considering whichever is
			// heigher

			if (UtilMaster.mtariff.trim().equalsIgnoreCase("21")) {

				if (ec > fc) {

					fc = 0f;
				} else if (fc > ec) {

					ec = 0f;

				} else {

					fc = 0f;

				}

			}

		
			float d_and_r_fee = Float
					.parseFloat(UtilMaster.md_and_r_fee.trim());
			float backbillarrears = Float.parseFloat(UtilMaster.mbackbillarr
					.trim());
			float audit_arrears = Float.parseFloat(UtilMaster.maudit_arrears
					.trim());
			float othersOriginal =Float.parseFloat(UtilMaster.mothers.trim());
			
			
			
			System.out.println("d_and_r_fee :"+ d_and_r_fee);
			System.out.println("backbillarrears :"+ backbillarrears);
			System.out.println("audit_arrears :"+ audit_arrears);
			System.out.println("othersOriginal :"+ othersOriginal);
			
			
			
			 /*IMP
			  * 
			  * This position must be at last as 
			adding  
			drfee, backbillarr, auditarresr, others values 
			will not affect the tax and other calculations*/
			
			
			others = others + d_and_r_fee + backbillarrears + audit_arrears + othersOriginal;
			
			
			System.out.println("Total others including the drfee, backbillarr, auditarresr, others, fppca :"+ others);
			
			
			

			total = ec + fc + tax + mtrRent + arrears + others + masterInterest
					+ interestondeposit - (credite_rebate + dlAdj) + PFPenalty
					+ excessLoadPenalty;

			total = (float) doubRound(total, 2);

			UtilMaster.mcredit_or_rebate = "" + credite_rebate;
			UtilMaster.mec = String.valueOf(ec);
			UtilMaster.mfc = String.valueOf(fc);
			UtilMaster.mtax = String.valueOf(tax);
			UtilMaster.mmeterrent = String.valueOf(mtrRent);
			UtilMaster.mtotal = String.valueOf(total);
			UtilMaster.mdl_adj = String.valueOf(dlAdj);
			UtilMaster.mpf_penality = String.valueOf(PFPenalty);
			UtilMaster.mbmd_penality = String.valueOf(excessLoadPenalty);
			UtilMaster.mothers = String.valueOf(others);

			// to resolve duplicate bill fppca printing issue
			UtilMaster.mfppca = UtilMaster.mothers;

			returnVal = 10;
		} finally {
			// returnVal = 0 ;
		}

		return returnVal;
	}

	/******** RATES FOR EC AND FC CALCULATION DATED:17-Mar-2015 BY GURURAJ ************/
	public float getECForAllQuarters(Context mobContext, float nou, float bm,
			String tariffCode) {
		float totalEc = 0;
		String[] tdates = null;
		Date mbDate = null;
		float difBM = 0;
		String pvdate1 = null;
		float nou12 = nou / bm;
		float chBM = 0;
		try {

			DBTariffHandler handler = new DBTariffHandler(mobContext);
			handler.openToRead();
			Cursor c = handler.getTdateforECFC(tariffCode);
			int i = 0;
			tdates = new String[c.getCount()];
			/************ Get TDATE For all quarters *************************/
			while (c != null && c.moveToPosition(i)) {
				tdates[i] = c.getString(0);
				i++;
			}
			c.close();
			handler.close();
			/************* Count the quarter ********************/

			mbDate = getMobileDate(new SimpleDateFormat("MM/dd/yyyy")
					.parse(UtilMaster.mreading_date));

			int datCountValue = 0;
			
			
			
			
			
			
			
			
			
			for (int cnt = 0; cnt < tdates.length; cnt++) {
				
				
				System.out.println("cnt value :::"+cnt);
				System.out.println("tdates.length :::"+tdates.length);
				
				
				
				difBM = dateDiff("m",
						new SimpleDateFormat("yyyy-MM-dd").parse(tdates[cnt]
								.trim()), getMobileDate(new SimpleDateFormat(
								"MM/dd/yyyy").parse(UtilMaster.mreading_date)));
				
				
				
				System.out.println("difBM :::"+difBM);
				System.out.println("bm :::"+bm);
				
				System.out.println("perMOnthBM :::"+nou12);
				
				
				
				
				if (difBM >= bm) {
					if (setECTariffRates(mobContext, cnt, tariffCode)) {
						pvdate1 = UtilMaster.mprevious_billeddate;
						
						System.out.println("pvdate1 is ::::"+pvdate1);
						
						if (cnt > 0) {
							
							
							System.out.println("executing  cnt > 0 :cnt value is :::"+cnt);
							
							Date test11 = new SimpleDateFormat("yyyy-MM-dd")
									.parse(tdates[cnt - 1].trim());
							
							System.out.println("test11 :::"+test11);
							System.out.println("mbDate :::"+mbDate);
							
							
							if (mbDate.equals(test11)) {
								Calendar calendartest = Calendar.getInstance();
								calendartest.setTime(mbDate);
								calendartest.add(Calendar.DATE, (-1));
								mbDate = new SimpleDateFormat("dd/MM/yyyy")
										.parse(new SimpleDateFormat(
												"dd/MM/yyyy")
												.format(calendartest.getTime()));
							}
						}
						/*chBM = round1(
								dateDiff("m",
										new SimpleDateFormat("dd/MM/yyyy")
												.parse(pvdate1), mbDate), 2);*/
						
						// new change
						chBM = 
								dateDiff("m",
										new SimpleDateFormat("dd/MM/yyyy")
												.parse(pvdate1), mbDate);
						
						float nou11 = (nou12 * chBM);
						totalEc = totalEc + calEC(nou11, chBM);
					}
					break;
				} else {
					if ((cnt + 1) < tdates.length) {
						
						
						System.out.println("excuting (cnt + 1) < tdates.length): cnt value is :::"+cnt+"::: pvdate1 :::"+pvdate1);
						
						
						pvdate1 = new SimpleDateFormat("dd/MM/yyyy")
								.format(new SimpleDateFormat("yyyy-MM-dd")
										.parse(tdates[cnt].trim()));
						/***** dda ******/
					} else {
						pvdate1 = UtilMaster.mprevious_billeddate;
						
						
						
						
						System.out.println("excuting else:::::"+pvdate1);
						
						
						
						
						
					}

					if (setECTariffRates(mobContext, cnt, tariffCode)) {
						/*chBM = round1(
								dateDiff("m",
										new SimpleDateFormat("dd/MM/yyyy")
												.parse(pvdate1), mbDate), 2);*/
						
						// new change
						
						
						
					
						
						chBM = 
								dateDiff("m",
										new SimpleDateFormat("dd/MM/yyyy")
												.parse(pvdate1), mbDate);
						
						
						System.out.println("Executing hererer :::: chBM :::"+chBM);
						
						
						
						float nou11 = (nou12 * chBM);
						totalEc = totalEc + calEC(nou11, chBM);
					}
					mbDate = new SimpleDateFormat("yyyy-MM-dd")
							.parse(tdates[cnt].trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalEc;
	}

	/**
	 * @category Fixed charges slab wise calc
	 * @param consumption
	 * @param bm
	 * @return Fixed charges
	 */
	public float calcFCSlabWise(float consumption, float bm) {
		float fc = 0;

		if (UtilMaster.mtariff.trim().equalsIgnoreCase("2")
				|| UtilMaster.mtariff.trim().equalsIgnoreCase("1")) {
			// ////////////UNITS PER MONTH LESS THEN 18 UNITS///////////////
			if ((consumption / bm) <= 18) {
				fc = 0;
			} else {
				fc = 15;
			}
		} else {

			/*********** Slabs ***********/
			float fcSlabOne = 0;

			fcSlabOne = Float.parseFloat(UtilMaster.Lfcslab1);

			float fcSlabTwo = 0;

			fcSlabTwo = Float.parseFloat(UtilMaster.Lfcslab2);

			float fcSlabThree = 0;

			fcSlabThree = Float.parseFloat(UtilMaster.Lfcslab3);

			float fcSlabFour = 0;

			fcSlabFour = Float.parseFloat(UtilMaster.Lfcslab4);

			/*********** Slabs Rates ***********/

			float fcRateOne = 0;

			fcRateOne = Float.parseFloat(UtilMaster.Lfcrate1);

			float fcRateTwo = 0;

			fcRateTwo = Float.parseFloat(UtilMaster.Lfcrate2);

			float fcRateThree = 0;

			fcRateThree = Float.parseFloat(UtilMaster.Lfcrate3);

			float fcRateFour = 0;

			fcRateFour = Float.parseFloat(UtilMaster.Lfcrate4);

			System.out.println("BILL Month in fc " + bm);

			/*********** Slabs Division ***********/

			float fcSlab1 = 0;

			fcSlab1 = fcSlabOne * bm;

			float fcSlab2 = 0;

			fcSlab2 = fcSlabTwo * bm;

			float fcSlab3 = 0;

			fcSlab3 = fcSlabThree * bm;

			float fcSlab4 = 0;

			fcSlab4 = fcSlabFour * bm;

			/*********** Slabs Calculation ***********/

			if ((consumption <= fcSlab1) || (fcSlab1 == 0))
				fc = fcRateOne;

			else if ((consumption > fcSlab1 && consumption <= (fcSlab1 + fcSlab2))
					|| (fcSlab2 == 0))
				fc = fcRateTwo;

			else if ((consumption > (fcSlab1 + fcSlab2) && consumption <= fcSlab1
					+ fcSlab2 + fcSlab3)
					|| (fcSlab3 == 0))
				fc = fcRateThree;

			else if ((consumption > (fcSlab1 + fcSlab2 + fcSlab3) && consumption <= fcSlab1
					+ fcSlab2 + fcSlab3 + fcSlab4)
					|| (fcSlab4 == 0))
				fc = fcRateFour;

			else if (consumption > (fcSlab1 + fcSlab2 + fcSlab3 + fcSlab4))
				fc = fcRateFour;

			else
				System.out.println("Out of Slab");

			System.out.println(" FC before BM : " + fc);
			fc = (fc * bm);

		}
		return fc;
	}

	/**
	 * @category Returns Master FC
	 * @param consumption
	 * @param bm
	 * @param masterFC
	 * @return FC
	 */
	public float calcFC(float consumption, float bm, float masterFC) {
		float fc = 0;
		if (UtilMaster.mtariff.trim().equalsIgnoreCase("2")
				|| UtilMaster.mtariff.trim().equalsIgnoreCase("1")) {
			// ////////////UNITS PER MONTH LESS THEN 18 UNITS///////////////
			if ((consumption / bm) <= 18) {
				fc = 0;
			} else {
				fc = 15;
			}
		} else
			fc = masterFC;

		return fc;
	}

	/**
	 * @category Returns FC for LT7 tariff
	 * @param days
	 * @return FC
	 * @author shivanand
	 */
	public float calcFCForLT7TariffOnly(float days) {
		float fc = 0;

		/* float ratePerDay = ((masterFC / 4) / 7); */
		float ratePerDay = 0;

		try {

			ratePerDay = (Float.valueOf(UtilMaster.mconnected_load.trim()) * 160) / 7;

		} catch (Exception e) {
			// TODO: handle exception

			ratePerDay = ((Float.parseFloat(UtilMaster.mfixed_ges.trim()) / 4) / 7);

		}

		fc = days * ratePerDay;

		System.out.println("FC is for LT7 is :" + fc);

		return fc;
	}

	/**
	 * @category EC Calculation for all Tariffs
	 * @param consumption
	 * @param bm
	 * @return EC
	 */
	public float calEC(float consumption, float bm) {
		float ec = 0;

		// ----------------For BJ/KJ ------------/
		if ((UtilMaster.mtariff.trim().equalsIgnoreCase("2") || UtilMaster.mtariff
				.trim().equalsIgnoreCase("1"))) {

			// ------------UNITS PER MONTH LESS THEN 18 UNITS----------/
			if ((consumption / bm) <= 18) {
				/*
				 * ec = (float) (consumption * (float) 5.11);
				 * 
				 * if (ec < 30) ec = 30 * bm;
				 */

				UtilMaster.isBJKJLessThan18Units = true;

				if (UtilMaster.getMpresentmeterstatus().trim().equals("2")) {
					ec = 0;
				} else {

					float untiPerMonth = consumption / bm;
					ec = ((float) (untiPerMonth * (float) 5.34) < (float) 30) ? (30)
							: ((float) (untiPerMonth * (float) 5.34 * bm));

				}

			} else {
				// ---------- UNITS PER MONTH GREATER THEN 18---------------//
				float untiPerMonth = consumption / bm;
				ec = 78;

				if (untiPerMonth > 18 && untiPerMonth <= 30) {
					ec = (float) (untiPerMonth * 2.60);
				} else if (untiPerMonth > 30 && untiPerMonth <= 100) {
					ec = (float) (78 + (untiPerMonth - 30) * 3.70);
				} else if (untiPerMonth > 100 && untiPerMonth <= 200) {
					ec = (float) (78 + 259 + (untiPerMonth - 100) * 5.10);
				} else if (untiPerMonth > 200) {
					ec = (float) (78 + 259 + 510 + (untiPerMonth - 200) * 5.90);
				}
				ec = ec * bm;

				System.out.println("Execting the ec is  >>>>>>>>>>>>>> :" + ec);

			}

			/*
			 * if (UtilMaster.getMpresentmeterstatus().trim().equals("2") &&
			 * (UtilMaster.mtariff.trim().equalsIgnoreCase("1")||
			 * UtilMaster.mtariff.trim().equalsIgnoreCase("2"))) ec = 0;
			 */

		} else {
			/*********** Slabs ***********/

			float ecSlabOne = 0;

			ecSlabOne = Float.parseFloat(UtilMaster.Lecslab1);
			System.out.println("ecSlabOne " + ecSlabOne);

			float ecSlabTwo = 0;

			ecSlabTwo = Float.parseFloat(UtilMaster.Lecslab2);

			System.out.println("ecSlabTwo " + ecSlabTwo);

			float ecSlabThree = 0;

			ecSlabThree = Float.parseFloat(UtilMaster.Lecslab3);

			System.out.println("ecSlabThree " + ecSlabThree);

			float ecSlabFour = 0;

			ecSlabFour = Float.parseFloat(UtilMaster.Lecslab4);

			System.out.println("ecSlabFour " + ecSlabFour);

			float ecSlabFive = 0;

			ecSlabFive = Float.parseFloat(UtilMaster.Lecslab5);

			System.out.println("ecSlabFive " + ecSlabFive);

			float ecSlabSix = 0;

			ecSlabSix = Float.parseFloat(UtilMaster.Lecslab6);

			System.out.println("ecSlabSix " + ecSlabSix);

			/*********** Slabs Rates ***********/

			float ecRateOne = 0;

			ecRateOne = Float.parseFloat(UtilMaster.Lecrate1);
			System.out.println("ecRateOne  " + ecRateOne);

			float ecRateTwo = 0;

			ecRateTwo = Float.parseFloat(UtilMaster.Lecrate2);

			System.out.println("ecRateTwo  " + ecRateTwo);

			float ecRateThree = 0;

			ecRateThree = Float.parseFloat(UtilMaster.Lecrate3);

			System.out.println("ecRateThree  " + ecRateThree);

			float ecRateFour = 0;

			ecRateFour = Float.parseFloat(UtilMaster.Lecrate4);

			System.out.println("ecRateFour  " + ecRateFour);

			float ecRateFive = 0;

			ecRateFive = Float.parseFloat(UtilMaster.Lecrate5);

			System.out.println("ecRateFive  " + ecRateFive);

			float ecRateSix = 0;

			ecRateSix = Float.parseFloat(UtilMaster.Lecrate6);

			System.out.println("ecRateSix  " + ecRateSix);

			/*********** Slabs Division ***********/

			System.out
					.println("ecSlab1 = ecSlabOne   * bm    , respectively upto ecSlab6");
			System.out.println("BM is :" + bm);

			float ecSlab1 = 0;

			ecSlab1 = ecSlabOne * bm;

			System.out.println("ecSlab1  " + ecSlab1);

			float ecSlab2 = 0;

			ecSlab2 = ecSlabTwo * bm;

			System.out.println("ecSlab2  " + ecSlab2);

			float ecSlab3 = 0;

			ecSlab3 = ecSlabThree * bm;

			System.out.println("ecSlab3  " + ecSlab3);

			float ecSlab4 = 0;

			ecSlab4 = ecSlabFour * bm;

			System.out.println("ecSlab4  " + ecSlab4);

			float ecSlab5 = 0;

			ecSlab5 = ecSlabFive * bm;

			System.out.println("ecSlab5  " + ecSlab5);

			float ecSlab6 = 0;

			ecSlab6 = ecSlabSix * bm;

			System.out.println("ecSlab6  " + ecSlab6);

			System.out.println("BILL Month in ec " + bm);

			/*********** Slabs Calculation ***********/
			System.out.println(" consumption : " + consumption);

			if ((consumption <= ecSlab1) || (ecSlab1 == 0)) {
				ec = consumption * ecRateOne;

				
				
				
				System.out.println("Executing : ec = consumption * ecRateOne;"+ec);
				System.out.println("Consumtion :"+consumption);
				System.out.println("ecRate :"+ecRateOne);
				
				

			} else if ((consumption > ecSlab1 && consumption <= (ecSlab1 + ecSlab2))
					|| (ecSlab2 == 0)) {
				ec = (ecSlab1 * ecRateOne)
						+ ((consumption - ecSlab1) * ecRateTwo);

				System.out
						.println("Executing : ec = (ecSlab1 * ecRateOne) + ((consumption - ecSlab1) * ecRateTwo);");

			} else if ((consumption > (ecSlab1 + ecSlab2) && consumption <= ecSlab1
					+ ecSlab2 + ecSlab3)
					|| (ecSlab3 == 0)) {
				ec = (ecSlab1 * ecRateOne) + (ecSlab2 * ecRateTwo)
						+ ((consumption - ecSlab1 - ecSlab2) * ecRateThree);

				System.out
						.println("Executing : ec = (ecSlab1 * ecRateOne) + (ecSlab2 * ecRateTwo) + ((consumption - ecSlab1-ecSlab2) * ecRateThree);");

			} else if ((consumption > (ecSlab1 + ecSlab2 + ecSlab3) && consumption <= ecSlab1
					+ ecSlab2 + ecSlab3 + ecSlab4)
					|| (ecSlab4 == 0)) {
				ec = (ecSlab1 * ecRateOne)
						+ (ecSlab2 * ecRateTwo)
						+ (ecSlab3 * ecRateThree)
						+ ((consumption - ecSlab1 - ecSlab2 - ecSlab3) * ecRateFour);

				System.out
						.println("Executing : ec = (ecSlab1 * ecRateOne) + (ecSlab2* ecRateTwo) +(ecSlab3 * ecRateThree)+ ((consumption - ecSlab1-ecSlab2-ecSlab3) * ecRateFour);");

			} else if ((consumption > (ecSlab1 + ecSlab2 + ecSlab3 + ecSlab4) && consumption <= ecSlab1
					+ ecSlab2 + ecSlab3 + ecSlab4 + ecSlab5)
					|| (ecSlab5 == 0)) {
				ec = (ecSlab1 * ecRateOne)
						+ (ecSlab2 * ecRateTwo)
						+ (ecSlab3 * ecRateThree)
						+ (ecSlab4 * ecRateFour)
						+ ((consumption - ecSlab1 - ecSlab2 - ecSlab3 - ecSlab4) * ecRateFour);

				System.out
						.println("Executing : ec = (ecSlab1 * ecRateOne) + (ecSlab2 * ecRateTwo) + (ecSlab3 * ecRateThree)+(ecSlab4 * ecRateFour)+((consumption -ecSlab1-ecSlab2-ecSlab3-ecSlab4 ) * ecRateFour);");

			} else if ((consumption > (ecSlab1 + ecSlab2 + ecSlab3 + ecSlab4 + ecSlab5) && consumption <= ecSlab1
					+ ecSlab2 + ecSlab3 + ecSlab4 + ecSlab5 + ecSlab6)
					|| (ecSlab6 == 0)) {
				ec = (ecSlab1 * ecRateOne)
						+ (ecSlab2 * ecRateTwo)
						+ (ecSlab3 * ecRateThree)
						+ (ecSlab4 * ecRateFour)
						+ (ecSlab5 * ecRateFive)
						+ ((consumption - ecSlab1 - ecSlab2 - ecSlab3 - ecSlab4 - ecSlab5) * ecRateSix);

				System.out
						.println("Executing : ec = (ecSlab1 * ecRateOne) + (ecSlab2 * ecRateTwo) + (ecSlab3 * ecRateThree)+(ecSlab4 * ecRateFour)+(ecSlab5 * ecRateFive)+((consumption -ecSlab1-ecSlab2-ecSlab3-ecSlab4-ecSlab5) * ecRateSix);");

			} else if (consumption > (ecSlab1 + ecSlab2 + ecSlab3 + ecSlab4
					+ ecSlab5 + ecSlab5 + ecSlab6)) {
				ec = (ecSlab1 * ecRateOne)
						+ (ecSlab2 * ecRateTwo)
						+ (ecSlab3 * ecRateThree)
						+ (ecSlab4 * ecRateFour)
						+ (ecSlab5 * ecRateFive)
						+ (ecSlab6 * ecRateSix)
						+ ((consumption - ecSlab1 - ecSlab2 - ecSlab3 - ecSlab4
								- ecSlab5 - ecSlab6) * ecRateSix);

				System.out
						.println("Executing : ec = (ecSlab1 * ecRateOne) + (ecSlab2 * ecRateTwo) + (ecSlab3 * ecRateThree)+(ecSlab4 * ecRateFour)+(ecSlab5 * ecRateFive)+(ecSlab6 * ecRateSix)+((consumption -ecSlab1-ecSlab2-ecSlab3-ecSlab4-ecSlab5-ecSlab6) * ecRateSix);");

			}

			else {
				System.out.println(" Out of Slab ");
			}

		}

		// by SHIVANAND

		if (UtilMaster.getMpresentmeterstatus().equals("6")) {
			ec = 0;
		}

		return ec;
	}

	/**
	 * @category Rebate Calculation For All Rebate related tariffs
	 * @param ec
	 * @param fc
	 * @param tax
	 * @param Tariff_code
	 * @param consumption
	 * @param bm
	 * @return Total Rebate
	 */
	public float getCredite_rebate(float ec, float fc, float tax,
			int Ltariff_code_i, float consumption, float bm) {
		float credite_rebate = 0;
		float localtempEC = 0;

		/************************** BJ-KJ REBATE *********************/
		if (Ltariff_code_i == 2 || Ltariff_code_i == 1) {
			/*
			 * if ((consumption / bm) <= 18) credite_rebate = ec;
			 */

		} else if (Ltariff_code_i == 54 || Ltariff_code_i == 544
				|| Ltariff_code_i == 504 || Ltariff_code_i == 204) {
			// 1st case free Lighting FL case for LT2A I : TARIF CODE :
			// Ltariff_code_i == 54 || Ltariff_code_i == 544 || Ltariff_code_i
			// == 504
			// 2nd case free Lighting FL case for LT2A II : TARIF CODE :
			// Ltariff_code_i == 204
			// 3rd case free Lighting FL case for LT3A II : TARIF CODE :
			// Ltariff_code_i == 104
			if ((consumption / bm) < 200)
				credite_rebate = ec;
			else {
				consumption /= bm;
				if (consumption < 280)
					localtempEC += ((consumption - 200) * 0.1);

				else if (consumption < 400)
					localtempEC += ((consumption - 280) * 1.82) + (80 * 0.1);

				else
					localtempEC += ((consumption - 400) * 4.6) + (120 * 1.82)
							+ (80 * 0.1);

				credite_rebate = ec - (localtempEC * bm);
			}
			credite_rebate += fc;
		} else if (Ltariff_code_i == 53 || Ltariff_code_i == 58
				|| Ltariff_code_i == 63 || Ltariff_code_i == 69) {

			credite_rebate = solarRebate(ec, fc, Ltariff_code_i, consumption,
					bm);

		} else if (Ltariff_code_i == 104 || Ltariff_code_i == 208) // OL
			credite_rebate = (fc + ec + tax);

		else if (Ltariff_code_i == 59) { // ************ 4th
											// case**************************

			if (consumption < 100)
				credite_rebate = (float) (consumption * 0.35);
			else
				credite_rebate = 35;

			// Demo changes By shivanand

		} else if (Ltariff_code_i == 179 || Ltariff_code_i == 178) { // ************
																		// 5th
																		// case**************************

			credite_rebate = (float) (ec - (consumption * 1.25));

		} else if (Ltariff_code_i == 101) { // ********************* 6th case
											// *******************

			credite_rebate = (float) (ec * 0.20);

		} else {

			credite_rebate = 0;
		}

		return credite_rebate;

	}

	/**
	 * @category Rebate Calculation
	 * @param ec
	 * @param fc
	 * @param tariff_code
	 * @param consumption
	 * @param bm
	 * @return Solar Rebate for Tariff code 53,58,63,69
	 */
	public float solarRebate(float ec, float fc, int Ltariff_code_i,
			float consumption, float bm) {
		float solar = 0;
		// float fcReduction = 0;
		float ecReduction = 0;
		// float fcMax = 0;
		float ecMax = 0;

		try {
			// fcReduction = Float.parseFloat(UtilMaster.LFcReduction);
			ecReduction = Float.parseFloat(UtilMaster.LEcReduction);
			// fcMax = Float.parseFloat(UtilMaster.LFcMax);
			ecMax = (Float.parseFloat(UtilMaster.LEcMax) * bm);

			if (ecReduction > 0) {
				if (Ltariff_code_i == 53 || Ltariff_code_i == 58
						|| Ltariff_code_i == 63 || Ltariff_code_i == 69)
					solar = consumption * ecReduction;

				if (solar > ecMax)
					solar = ecMax;

			} else
				solar = 0;

		} catch (Exception e) {
			FilePropertyManager.appendLog("CALCULATION ERROR : "
					+ Log.getStackTraceString(e));
			solar = 0;
		}
		return solar;
	}

	/**
	 * @category TAX
	 * @param ec
	 * @param rateInDPoint
	 * @param Ltariff_code_i
	 * @param consumption
	 * @param bm
	 * @return Tax
	 */
	public float calTax(float ec, float rateInDPoint, int Ltariff_code_i,
			float consumption, float bm, float fppca) {
		float duty = 0;
		if (Ltariff_code_i == 2 || Ltariff_code_i == 1) {
			if ((consumption / bm) <= (float) 18)
				duty = 0;
			else
				duty = (ec + fppca) * rateInDPoint;

		} else {
			if (Ltariff_code_i == 56 || Ltariff_code_i == 59
					|| Ltariff_code_i == 64 || Ltariff_code_i == 66
					|| Ltariff_code_i == 105 || Ltariff_code_i == 106
					|| Ltariff_code_i == 111 || Ltariff_code_i == 112
					|| Ltariff_code_i == 113 || Ltariff_code_i == 114
					|| Ltariff_code_i == 115 || Ltariff_code_i == 116) {
				duty = 0;
			} else
				duty = (ec + fppca) * rateInDPoint;

		}
		return duty;
	}

	public double ltPFPenalty(String recPF, float consumption, float masterPF) {

		float recordedPF = Float.parseFloat(recPF);
		double ltPFPenalty = 0;
		float pf = 0;
		double penaltyOne = 0;
		double penaltyTwo = 0;

		if (recordedPF == (float) 0)
			pf = masterPF;
		else
			pf = recordedPF;

		UtilMaster.mpf_reading = String.valueOf(pf);
		if (pf > 0 && pf < 0.85) {
			penaltyOne = 0.85 - pf;
			penaltyTwo = doubRound((penaltyOne * 2), 2);
			if (penaltyTwo <= 0.30)
				ltPFPenalty = doubRound((consumption * penaltyTwo), 2);
			else
				ltPFPenalty = doubRound((consumption * 0.3), 2);
		}

		masterLogger.debug("penaltyOne--------------" + penaltyOne);
		masterLogger.debug("penaltyTwo--------------" + penaltyTwo);
		masterLogger.debug("ltPFPenalty-------------" + ltPFPenalty);

		return ltPFPenalty;
	}

	/*--------- FUNCTION TO CALCULATE EXCESS LOAD PENALTY  ---------------------------**/
	public float excessLoadPenalty(String recBMD, float sanctionedLoadKW,
			float meterConstant, String tariffCode) {
		float recordedBMD = Float.parseFloat(recBMD);
		float excessLoadPenalty = 0;

		double sanctionedLoadHP = 0;
		double excessLoad = 0;

		excessLoad = (recordedBMD * meterConstant) - sanctionedLoadKW;

		if (excessLoad > 0) {
			sanctionedLoadHP = doubRound((excessLoad / 0.746), 4);
			masterLogger.debug("sanctionedLoadHP---------------"
					+ sanctionedLoadHP);
			masterLogger.debug("excessLoadPenalty--------------"
					+ excessLoadPenalty);
			excessLoadPenalty = (float) doubRound(
					Math.round(sanctionedLoadHP * 35 * 2), 2);
			if ("173".equalsIgnoreCase(tariffCode)
					|| "174".equalsIgnoreCase(tariffCode)
					|| "175".equalsIgnoreCase(tariffCode)
					|| "176".equalsIgnoreCase(tariffCode)) {
				double fc = 0;
				double fcslab1 = 0, fcslab2 = 0, fcslab3 = 0;
				fcslab1 = Double.parseDouble(UtilMaster.Lfcslab1);
				fcslab2 = Double.parseDouble(UtilMaster.Lfcslab2);
				fcslab3 = Double.parseDouble(UtilMaster.Lfcslab3);

				double sLoadinKW = (excessLoad);
				if (sLoadinKW > fcslab1 && sLoadinKW < fcslab2)
					fc = Double.parseDouble(UtilMaster.Lfcrate1);
				else if (sLoadinKW >= fcslab2 && sLoadinKW < fcslab3)
					fc = Double.parseDouble(UtilMaster.Lfcrate2);
				else if (sLoadinKW >= fcslab3)
					fc = Double.parseDouble(UtilMaster.Lfcrate3);

				masterLogger.debug("sLoadinKW--------------" + sLoadinKW);
				masterLogger.debug("excessLoad-------------" + excessLoad);
				excessLoadPenalty = (float) doubRound(
						Math.round(excessLoad * fc * 2), 2);
			}
		} else {
			excessLoadPenalty = 0;
		}
		return excessLoadPenalty;
	}

	/**
	 * @category Decimal point round
	 * @param val
	 * @param places
	 * @return Rounded Value to given place
	 */
	public static double doubRound(double val, int places) {
		long factor = (long) Math.pow(10, places);
		val = val * factor;
		long tmp = Math.round(val);
		return (double) tmp / factor;
	}

	/**
	 * @category DAY Diff Calculation
	 * @param token
	 * @param date1
	 * @param date2
	 * @return diff
	 */
	public float dateDiff(String token, Date date1, Date date2) {
		float diff = 0;
		// different date might have different offset
		
		
		System.out.println("Tariff data :"+date1);
		System.out.println("reading date :"+date2);
		
		
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET)
				+ cal1.get(Calendar.DST_OFFSET);

		if (date2 == null)
			cal2.setTime(new Date());
		else
			cal2.setTime(date2);
		long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET)
				+ cal2.get(Calendar.DST_OFFSET);

		// Use integer calculation, truncate the decimals
		int hr1 = (int) (ldate1 / 3600000); // 60*60*1000
		int hr2 = (int) (ldate2 / 3600000);

		int days1 = hr1 / 24;
		int days2 = hr2 / 24;
		int dateDiff = days2 - days1;

		// System.out.println("monthDiff 1" +monthDiff);
		System.out.println(" days: ::: " + dateDiff);
		float fbm = dateDiff;
		// fbm = (float) LibraryFunction.doubRound(fbm /365 *12,2) ;
	//	fbm = (float) MasterLibraryFunction.doubRound(fbm / 30, 2);
		
		//new change
		fbm = fbm / 30;
		

		if (token.equals("d")) {
			diff = dateDiff;
			System.out.println(" days:  ;;;;  " + dateDiff);
		} else if (token.equals("m")) {
			diff = fbm;
			System.out.println(" diff ---------->:  ;;;;  " + fbm);
			System.out.println(" month:  ;;;;  " + diff);
		}

		/******* day wise diff *******/
		return diff;
	}

	
	
	
	
	
	
	/**
	 * @category Consumption Calculation Fun
	 * @param presReading
	 * @param prevReading
	 * @param meterConstatnt
	 * @return Consumption
	 */
	public long consumption(String presReading, String prevReading,
			float meterConstatnt) {

		long difference = 0;
		long presentReading = Long.parseLong(presReading);
		long previousReading = Long.parseLong(prevReading);
		String len = "1";

		for (int g = 0; g < prevReading.length(); g++)
			len = len + "0";

		long finalvalue = Long.parseLong(len);

		if (presentReading >= previousReading)
			difference = presentReading - previousReading;
		else
			difference = (finalvalue - previousReading) + presentReading;

		System.out.println(" di : " + difference);
		return (long) (difference * meterConstatnt);
	}

	public String toString1(Object value) {
		String result = String.valueOf(value);
		return result;
	}

	public int toInt(String value) {
		int result = Integer.parseInt(value);
		return result;
	}

	/**
	 * @category Returns Application Version
	 * @param context
	 * @return version
	 */
	public static String getVersion(Context context) {
		String version = null;
		try {
			version = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e1) {
			e1.printStackTrace();
		}
		return version;
	}

	/**
	 * @category Returns SBM No
	 * @param er_context
	 * @return imeino
	 */
	public String getSBMNo(Context er_context) {
		String imeino = "";

		try {
			TelephonyManager tm = (TelephonyManager) getSystemService(er_context.TELEPHONY_SERVICE);
			imeino = tm.getDeviceId();
		} catch (Exception e) {
			getlogger(er_context, "IMEI_NO_ERROR").error(e);
			imeino = "-";
		}
		return imeino;
	}

	/**
	 * @category Check for Null OR Empty (if so returns false)
	 * @param stringVar
	 * @return Status
	 */
	public static boolean IsNullOrEmpty(String stringVar) {
		// boolean b = true;
		if (stringVar == null || stringVar.length() == 0
				|| stringVar.equals(" ") || stringVar.equals("")) {
			return false;
		} else {
			if (stringVar.equals("null")) {
				return false;
			}
			return true;
		}
	}

	/* METHOD TO WRITE LOG FILE */
	public static boolean SdcardExists() {
		File externalStorage = Environment.getExternalStorageDirectory();
		if (externalStorage.canWrite() || externalStorage.canRead())
			return true;
		return false;
	}

	public static boolean createfolder_if_not_exist_sdcard(String foldername) {
		if (SdcardExists()) {
			File myfile = new File(Environment.getExternalStorageDirectory(),
					foldername);
			if (myfile.exists()) {
				System.out.println(foldername + " folder exists in sd card");
			} else {
				System.out
						.println(foldername
								+ " folder doesnt exists in sd card. So creating the folder.. Please Wait");
				myfile.mkdir();
			}
			return true;
		}
		return false;
	}

	public static void collectLogs1(String data) {
		// SimpleDateFormat sdf = new
		// SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		// String timestamp = sdf.format(new Date());
		if (DefaultClass.logfile == null) {
			DefaultClass.logfile = Environment.getExternalStorageDirectory()
					+ "/" + DefaultClass.mainDir + "/"
					+ DefaultClass.currentdate + "_LogFile.log";
		}
		File logFile = new File(DefaultClass.logfile);
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @category MOBILE DATE IN FORMATE dd//MM/yyyy
	 * @return Date(dd//MM/yyyy)
	 */
	public static Date getMobileDate(Date date) {
		// Date date = null ;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		// Calendar calendar = Calendar.getInstance();

		try {
			// calendar.setTime(date);
			date = df.parse(df.format(date));
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	/**
	 * @category returns add/sub date
	 * @param days
	 */
	public void setBill_Due_date(int days, Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// gettin current date
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String bill_date = dateFormat.format(cal.getTime());
		// Consumer.setlBilldate(bill_date);

		// current date
		UtilMaster.setMbilledatetimestamp(new SimpleDateFormat(
				"dd/MM/yyyyHH:mm:ss").format(cal.getTime()));

		int curr_date = cal.get(Calendar.DAY_OF_MONTH);
		// Consumer.setLPR(Integer.toString(curr_date));

		// getting time
		int hour = cal.get(Calendar.HOUR);
		int minu = cal.get(Calendar.MINUTE);
		String str_hour = Integer.toString(hour);
		String str_min = Integer.toString(minu);

		if (str_min.length() == 1) {
			str_min = "0" + str_min;
		}
		String AM_PM = null;
		if (cal.get(Calendar.AM_PM) == 0) {
			AM_PM = "AM";
		} else {
			AM_PM = "PM";
		}
		String time = str_hour + ":" + str_min + AM_PM;

		// Consumer.setLTime(time);
		// Consumer.setLBilldate_Time(time + "" + bill_date);
		// getting due date
		try {
			cal.setTime(dateFormat.parse(bill_date));
			cal.add(Calendar.DATE, days);
			String due_date = dateFormat.format(cal.getTime());
			Calendar chgdate = Calendar.getInstance();
			chgdate.setTime(dateFormat.parse(due_date));
			// String dayName = new
			// DateFormatSymbols().getWeekdays()[chgdate.get(Calendar.DAY_OF_WEEK)];
			// int weekOfMonthFr = chgdate.get(Calendar.WEEK_OF_MONTH);

			/*
			 * if ( dayName.toUpperCase().equals("SUNDAY") ||
			 * dayName.toUpperCase().equals("SUN") ) { setBill_Due_date(days +
			 * 1); } else { UtilMaster.setMduedate(due_date); }
			 */

			UtilMaster.setMduedate(due_date);
			// System.out.println("due date" +
			// dateFormat.format(cal.getTime()));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getPrvDate(int days, Date date) {
		String string = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		try {
			String bill_date = dateFormat.format(cal.getTime());
			cal.setTime(dateFormat.parse(bill_date));
			cal.add(Calendar.DATE, days);
			string = dateFormat.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * @category VALIDAT DATE RANGE
	 * @return Boolean
	 */
	public boolean isdataValid() {
		boolean bool = false;
		try {
			DatabaseHandler db = new DatabaseHandler(context);
			db.openToRead();
			String serverDate = db.getServerToSBMDate();
			db.close();
			UtilMaster.serverDatesync_key = false;

			System.out.println("Coming hereeee isDateValid::::1");

			mobileDateValidation(3, serverDate);

			if (UtilMaster.serverDatesync_key) {
				bool = true;
			} else {
				bool = false;
			}
		} catch (Exception e) {
			bool = true;
			FilePropertyManager.appendLog(Log.getStackTraceString(e));
		}
		return bool;
	}

	public void mobileDateValidation(int days, String serverDate1) {

		if (serverDate1.equals("-")) {

			serverDate1 = "10/02/2016";

		}

		System.out.println("Coming hereeee mobileDateValidation::::2");

		System.out.println("serverDate1 is :" + serverDate1);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Calendar cal = Calendar.getInstance(); // gettin current date
		String mobileDate = dateFormat.format(cal.getTime());
		Date moblDate = null;
		Date serverDate = null;
		Date lstdt = null;

		// int hour = cal.get(Calendar.HOUR); // current date IN HOUR
		int minu = cal.get(Calendar.MINUTE); // current date IN MINUTE
		// String str_hour = Integer.toString(hour);
		String str_min = Integer.toString(minu);
		if (str_min.length() == 1) {
			str_min = "0" + str_min;
		}

		try {
			moblDate = dateFormat.parse(mobileDate);
			serverDate = dateFormat.parse(serverDate1.trim());
			System.out.println("serverDate *********************" + serverDate);
			cal.setTime(dateFormat.parse(serverDate1));
			cal.add(Calendar.DATE, days);
			String lastDate = dateFormat.format(cal.getTime());
			lstdt = dateFormat.parse(lastDate);

			Calendar chgdate = Calendar.getInstance();
			chgdate.setTime(dateFormat.parse(lastDate));
			String dayName = new DateFormatSymbols().getWeekdays()[chgdate
					.get(Calendar.DAY_OF_WEEK)];
			// int weekOfMonthFr = chgdate.get(Calendar.WEEK_OF_MONTH);

			if ((dayName.toUpperCase().equals("SUNDAY"))
					|| (dayName.toUpperCase().equals("SUN")))
				mobileDateValidation(days + 1, serverDate1);
			else {

				System.out.println("");

				if (((moblDate.after(serverDate)) || (moblDate
						.equals(serverDate)))
						&& ((moblDate.equals(lstdt)) || (moblDate.before(lstdt)))

				) {

					UtilMaster.serverDatesync_key = true;
				}

				else {
					UtilMaster.serverDatesync_key = false;

				}

			}
			System.out.println("due date" + dateFormat.format(cal.getTime()));
		} catch (ParseException e) {

			e.printStackTrace();
			System.out.println("catch block****************");
			UtilMaster.serverDatesync_key = true;
		}
	}

	public static double round2(double val, int places) {
		long factor = (long) Math.pow(10, places);
		val = val * factor;
		long tmp = Math.round(val);
		return (double) tmp / factor;
	}

	public static float round1(float val, int places) {
		return (float) round2((double) val, places);
	}

	/**
	 * @param sitecode
	 * @return Division Name
	 */
	public static String getDivisionName(int sitecode) {
		String divionName = "TEST";
		// final int sitecodei = sitecode ;
		switch (sitecode) {

		case 5121: {
			divionName = "SANDUR";
			break;
		}
		case 5111: {
			divionName = "KURUGODU";
			break;
		}
		case 5112: {
			divionName = "RSD_BELLARY";
			break;
		}
		case 5122: {
			divionName = "TORANGAL";
			break;
		}
		case 5131: {
			divionName = "SIRUGUPPA";
			break;
		}
		case 5132: {
			divionName = "TEKKALKOTA";
			break;
		}
		case 5241: {
			divionName = "HADAGALI";
			break;
		}
		case 5251: {
			divionName = "HAGARI_BOMMANA_HALLI";
			break;
		}
		case 5261: {
			divionName = "KOTTUR";
			break;
		}
		case 5262: {
			divionName = "KUDLIGI";
			break;
		}
		case 5271: {
			divionName = "KAMLAPUR";
			break;
		}
		case 5272: {
			divionName = "KAMPLI";
			break;
		}
		case 5273: {
			divionName = "RSD_HOSPET";
			break;
		}
		case 6111: {
			divionName = "GANGAVATHI";
			break;
		}
		case 6122: {
			divionName = "KARATIGI";
			break;
		}
		case 6131: {
			divionName = "HANUMA_SAGARA";
			break;
		}
		case 6132: {
			divionName = "KUSTIGI";
			break;
		}
		case 6241: {
			divionName = "KOPPAL";
			break;
		}
		case 6251: {
			divionName = "MUNIRABAD";
			break;
		}
		case 6261: {
			divionName = "KUKNOOR";
			break;
		}
		case 6262: {
			divionName = "YELBURGA";
			break;
		}
		case 7111: {
			divionName = "DEODURGA";
			break;
		}
		case 7122: {
			divionName = "MANVI";
			break;
		}
		case 7131: {
			divionName = "RSD_RAICHUR";
			break;
		}
		case 7132: {
			divionName = "SHAKTHINAGAR";
			break;
		}
		case 7133: {
			divionName = "YERAGERA";
			break;
		}
		case 7141: {
			divionName = "KAVITAL";
			break;
		}
		case 7142: {
			divionName = "SIRWAR";
			break;
		}
		case 7251: {
			divionName = "HATTI";
			break;
		}
		case 7252: {
			divionName = "LINGASUGUR";
			break;
		}
		case 7253: {
			divionName = "MUDGAL";
			break;
		}
		case 7261: {
			divionName = "MASKI";
			break;
		}
		case 7271: {
			divionName = "SINDHANOOR";
			break;
		}

		}
		return divionName;

	}

	/**
	 * @category Logger Instance
	 * @param Application
	 *            Context
	 * @param Activity
	 *            Name
	 * @return Logger Instance
	 */
	public static Logger getlogger(Context logContext, String TAG) {
		String logFilename = "log_cesc_"
				+ new SimpleDateFormat("yyyy_MM_dd").format(new Date())
				+ ".log";

		Logger.Level log_level = Logger.Level.DEBUG;
		// Logger.Level log_level = Logger.Level.ERROR ;
		// Logger.Level log_level = Logger.Level.INFO ;

		try {
			if (masterLogger == null) {
				masterLogger = Logger.getInstance(logContext);
				masterLogger.LoggerSetup(TAG, logFilename, log_level);
			}
			masterLogger.setTag(TAG);
		} catch (Exception e) {
			FilePropertyManager.appendLog("LOGGER FILE ERROE: "
					+ Log.getStackTraceString(e));
			e.printStackTrace();
		}
		return masterLogger;
	}

	public String getTariffDesc(Context m_context, String tCode_) {
		String tDesc = "-";
		try {
			DBTariffHandler tariffHandler = new DBTariffHandler(m_context);
			tariffHandler.openToRead();
			tDesc = tariffHandler.getTariffDesc(tCode_);
			tariffHandler.close();

		} catch (Exception e) {
			e.printStackTrace();
			return tDesc;
		}
		return tDesc;
	}

	public static Date getPreviousReadDateFromPresent(String ddMMyyyy, float bm) {

		int bm1 = (int) bm;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			cal.setTime(sdf.parse(ddMMyyyy));
			/* cal.add(Calendar.MONTH, -1); */

			cal.add(Calendar.MONTH, -bm1);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cal.getTime();

	}

	public static Date getDateFromString(String ddMMyyyy) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			cal.setTime(sdf.parse(ddMMyyyy));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cal.getTime();

	}

}
