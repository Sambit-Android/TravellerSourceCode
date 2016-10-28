package com.bcits.bsmartbilling.rf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Utils.Utility;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import com.bcits.bsmartbilling.rf.R;
import com.utils.DBTariffHandler;
import com.utils.DatabaseHandler;
import com.utils.helper.DetailedReportHelper;
import com.utils.helper.MStatusReportHelper;

public class ReportViewHelper extends Activity {
	ImageView headerIcon ;
	Button btnlogout , btn_view_print ; 
	public static  String totalCount = "0" , billedCount= "0" , unBilledCount= "0"  , synced_total= "0" , drepotTotal_rev= "0" ,drepotTotal_Units= "0" ;
	ListView mDrawerList  ;
	public static List<MStatusReportHelper> list ;
	TextView txt_report_heading , txt_total_rep_view , txt__rep_view_billed , txt_rep_view_unbilled ,rep_view_mrname , rep_view_device_id,
	rep_tot_revenue ,rep_tot_units	;
	LinearLayout btn_print_report ;
	TableRow rep_table_row_tot_units , rep_table_row_tot_revenue ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.report_layout_basce);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
		TextView txtTitle=(TextView)findViewById(R.id.title);
		txtTitle.setText(getString(R.string.report));
		headerIcon = (ImageView)findViewById(R.id.header);
		headerIcon.setImageDrawable(getResources().getDrawable(R.drawable.report_view));
		btnlogout = (Button) findViewById(R.id.titleBack);
		 mDrawerList =(ListView)findViewById(R.id.rep_view_listView_new);
		 txt_report_heading = (TextView)findViewById(R.id.txt_report__view_heading);
		 txt_report_heading.setVisibility(View.INVISIBLE);
		 /*R.id.,R.id., R.id. */
		 
		 txt_total_rep_view = (TextView)findViewById(R.id.rep_total);
		 txt__rep_view_billed = (TextView)findViewById(R.id.rep_billed);
		 txt_rep_view_unbilled = (TextView)findViewById(R.id.rep_unbilled);
		 rep_view_mrname= (TextView)findViewById(R.id.rep_view_mrname);
		 rep_view_device_id= (TextView)findViewById(R.id.rep_view_device_id);
		 btn_print_report =(LinearLayout)findViewById(R.id.btn_print_report);
		 rep_table_row_tot_units = (TableRow)findViewById(R.id.rep_table_row_tot_units);
		 rep_table_row_tot_revenue = (TableRow)findViewById(R.id.rep_table_row_tot_revenue);
		 rep_table_row_tot_units.setVisibility(View.INVISIBLE);
		 rep_table_row_tot_revenue.setVisibility(View.INVISIBLE);
		 rep_tot_units= (TextView)findViewById(R.id.rep_tot_units);
		 rep_tot_revenue= (TextView)findViewById(R.id.rep_tot_revenue);
	}
	@Override
	protected void onStart() {
		super.onStart();
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();    
		if(mBluetoothAdapter!=null)
		{
		   if (!mBluetoothAdapter.isEnabled()) {
		    }else{ 
		        mBluetoothAdapter.disable(); 
		    }
		}
		
		if( (UtilMaster.mGLogin_MRCode.equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.equalsIgnoreCase("") ){
			new AlertDialog.Builder(ReportViewHelper.this)
							.setTitle("Sorry..!")
							.setMessage("Session timeout ..! Please Login again")
					   .setPositiveButton("OK", new DialogInterface.OnClickListener()
					   {
						   
						   public void onClick(DialogInterface dialog,int which) 
						   {
							 
						startActivity(new Intent(ReportViewHelper.this,Login.class));
							//   return;

								
						   }
					   }).show();
		}else{
			if (UtilMaster.reportType.equalsIgnoreCase("SUMMARY_REPORT"))
				generateSummeryReport();
			else if(UtilMaster.reportType.equalsIgnoreCase("DETAILED_SUMMARY_REPORT"))
			{	
				//new	performBackgroundTaskupload().execute();
				generateDetailedSummeryReport_new();
			}else if(UtilMaster.reportType.equalsIgnoreCase("ROUTEWISE_SUMMERY_REPORT"))
				generateRouteWiseSummeryReport_new();
			else{
				new AlertDialog.Builder(ReportViewHelper.this)
				.setTitle("Sorry..!")
				.setMessage("No reports found")
			   .setPositiveButton("OK", new DialogInterface.OnClickListener()
			   {
				   public void onClick(DialogInterface dialog,int which) 
				   {
					   startActivity(new Intent(ReportViewHelper.this,ReportActionClass.class));
				   }
			   }).show();
			}
		}
		
		btnlogout.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent inte_back = new Intent(	ReportViewHelper.this,	ReportActionClass.class);
				startActivity(inte_back);
				
			}
		}); 
		btn_print_report.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
			
				startActivity(new Intent(ReportViewHelper.this, BluetoothChat_Reports.class));
				
			}
		}); 
		
		
	}
	
	@Override
	public void onBackPressed() {
		Intent inte_back = new Intent(	ReportViewHelper.this,	ReportActionClass.class);
		inte_back.putExtra("status", "add");
		startActivity(inte_back);
	}

public void generateSummeryReport(){
	 DatabaseHandler databasehandler = new DatabaseHandler(ReportViewHelper.this);
		try {
			MasterLibraryFunction function = new MasterLibraryFunction(getApplicationContext());
			databasehandler.openToRead();
			String totalCount =	databasehandler.getTotalCount() ;
			String billCount =databasehandler.Getbillledcount() ;
			String unbilled = "-" ;
			
			try {
				unbilled = (Integer.parseInt(totalCount) - Integer.parseInt(billCount))+"";
			} catch (NumberFormatException e) {
				unbilled = "0";
			}catch (Exception e) {
				unbilled = "0";
			}
			
			 billedCount =billCount ;
			 unBilledCount =unbilled; 
			 
			 if (UtilMaster.mGlobalList != null)
					UtilMaster.mGlobalList.clear();
				
				if (list!= null)
					list.clear();
			 
			 
			 
			 list = new ArrayList<MStatusReportHelper>();
			List<MStatusReportHelper> list_ =databasehandler.getMStatusWiseReport();
			databasehandler.close();
			for (MStatusReportHelper mStatusReportHelper : list_) {
				mStatusReportHelper.status  = UtilMaster.addPostSpace(getMeterStatus(mStatusReportHelper.status.trim()),14) ;
				list.add(mStatusReportHelper);
				
			}
			UtilMaster.mGlobalList = (list) ;
			if(list != null)
			{
				if(list.size() > 0){
					 rep_table_row_tot_units.setVisibility(View.INVISIBLE);
					 rep_table_row_tot_revenue.setVisibility(View.INVISIBLE);
					txt_report_heading.setVisibility(View.VISIBLE);
					txt_report_heading.setText("SUMMARY REPORT");
					String[] from = null;
					int[] to = null;
					String string="" , string_count="";
					for (MStatusReportHelper mStatusReportHelper : list) {
						string += (mStatusReportHelper.status.trim())+ " \n";
						string_count += (" "+(mStatusReportHelper.count))+ " \n";
					}
					
					List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
					from = new String[] { "rep_status_wise" , "rep_status_wise_counts"};
					to = new int[] { R.id.rep_status_wise , R.id.rep_status_wise_counts};
					HashMap<String, String> map = new HashMap<String, String>();
					/*map.put("rep_total", " "+totalCount);
					map.put("rep_billed", " "+billedCount);
					map.put("rep_unbilled", " "+unBilledCount);*/
					rep_view_mrname.setText(UtilMaster.mGLogin_MRCode);
					this.totalCount = totalCount ;
					txt_total_rep_view.setText(this.totalCount);
					txt__rep_view_billed .setText(billedCount);
					txt_rep_view_unbilled .setText(unBilledCount);
					String imeino ="-";
					try {
						TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
						   imeino = tm.getDeviceId();
					} catch (Exception e) {
						e.printStackTrace();
						imeino ="-";
					}
					rep_view_device_id.setText(imeino);
					
					map.put("rep_status_wise", string);
					map.put("rep_status_wise_counts", string_count);
					fillMaps.add(map);
					SimpleAdapter adapter = new SimpleAdapter(
							getApplicationContext(),
							fillMaps,
							R.layout.view_summary_report,
							from, to);
					mDrawerList.setAdapter(adapter);
				}else{
					new AlertDialog.Builder(ReportViewHelper.this)
					.setTitle("Info")
					.setMessage("Sorry..!\nNot Able to generate report")
					.setPositiveButton(	"Ok",	null)
					.show();
				}
			}else{
				new AlertDialog.Builder(ReportViewHelper.this)
				.setTitle("Info")
				.setMessage("Sorry..!\nNot Able to generate report")
				.setPositiveButton(	"Ok",	null)
				.show();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}

public void generateDetailedSummeryReport_new(){
	 DatabaseHandler databasehandler = new DatabaseHandler(ReportViewHelper.this);
		try {
			databasehandler.openToRead();
			String totalCount =	databasehandler.getTotalCount() ;
			String billCount =databasehandler.Getbillledcount() ;
			String unbilled = "-" ;
			synced_total = databasehandler.GetBilledupdatedcountfromlocal();
			
			try {
				unbilled = (Integer.parseInt(totalCount) - Integer.parseInt(billCount))+"";
			} catch (NumberFormatException e) {
				unbilled = "0";
			}catch (Exception e) {
				unbilled = "0";
			}
			
			ReportViewHelper.totalCount = totalCount ;
			 billedCount =billCount ;
			 unBilledCount =unbilled; 
			 
			if (UtilMaster.detailedReportHelpers != null)
				UtilMaster.detailedReportHelpers.clear();
			
			
			
			 
			// int temp_units = 0 ,  temp_BD = 0;
			List<DetailedReportHelper> list_ =databasehandler.getDetailedMStatusWiseReport();
			
			List<DetailedReportHelper> reportHelpers   = new ArrayList<DetailedReportHelper>();
			MasterLibraryFunction	libraryFunction = new MasterLibraryFunction(ReportViewHelper.this);
				
			
			drepotTotal_rev = "0" ;
			drepotTotal_Units = "0";
			int unit_01 = 0 , rev_01 = 0 ;
			for (DetailedReportHelper helper : list_) {
				helper.traifDesc  = libraryFunction.getTariffDesc(ReportViewHelper.this, helper.tariffCode);
				rev_01 += helper.report_totalRev ;
				unit_01 += helper.report_Totalunits;
				reportHelpers.add(helper);
			}
			drepotTotal_rev = rev_01+"" ;
			drepotTotal_Units = unit_01+"";
			
			UtilMaster.detailedReportHelpers = (reportHelpers) ;
			if(reportHelpers != null)
			{
				if (reportHelpers.size() > 0) {
					
					
					
					UtilMaster.	reportType  ="DETAILED_SUMMARY_REPORT" ;
					/*startActivity(new Intent(ReportViewHelper.this,	BluetoothChat_Reports.class));*/
					String[] from = null;
					int[] to = null;
					txt_report_heading.setVisibility(View.VISIBLE);
					txt_report_heading.setText("DETAILED REPORT");
					rep_view_mrname.setText(UtilMaster.mGLogin_MRCode);
					ReportViewHelper.totalCount = totalCount ;
					txt_total_rep_view.setText(ReportViewHelper.totalCount);
					txt__rep_view_billed .setText(billedCount);
					txt_rep_view_unbilled .setText(unBilledCount);
					String imeino ="-";
					try {
						TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
						   imeino = tm.getDeviceId();
					} catch (Exception e) {
						e.printStackTrace();
						imeino ="-";
					}
					rep_view_device_id.setText(imeino);
					 rep_table_row_tot_units.setVisibility(View.VISIBLE);
					 rep_table_row_tot_revenue.setVisibility(View.VISIBLE);
					 rep_tot_units.setText(drepotTotal_Units);
					 rep_tot_revenue.setText(drepotTotal_rev);
					 
					 String string="" , string_count="";
						/*for (DetailedReportHelper detailedReportHelper : reportHelpers) {
							string += (mStatusReportHelper.status.trim())+ " \n";
							string_count += (" "+(mStatusReportHelper.count))+ " \n";
						}*/
						
						List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
						from = new String[] { "id_number" , "txt_tariff_desc" , "txt_no_of_conn" , "txt_count_column1" , "txt_count_column2", "txt_units", "txt_revenue",
								"txt_no_of_billed_conn"};
						to = new int[] { R.id.id_number , R.id.txt_tariff_desc , R.id.txt_no_of_conn, R.id.txt_count_column1, R.id.txt_count_column2, R.id.txt_units,
								R.id.txt_revenue , R.id.txt_no_of_billed_conn};
						int intex_cout= 0 ;
						/*for (DetailedReportHelper detailedReportHelper : reportHelpers) {*/
						for (int i= 0 ; i < list_.size() ; i++) {
							DetailedReportHelper detailedReportHelper = reportHelpers.get(i) ;
							
							string =" : "+detailedReportHelper.mtrStatusNrm+"\n"+
									" : "+detailedReportHelper.mtrStatusMNR+"\n"+
									" : "+detailedReportHelper.mtrStatusDISSCONN+"\n"+
									" : "+detailedReportHelper.mtrStatusMB+"\n"+
									" : "+detailedReportHelper.mtrStatusNV;
									
									
						string_count = " : "+detailedReportHelper.mtrStatusDL+"\n"+
								" : "+detailedReportHelper.mtrStatusDC+"\n"+
								" : "+detailedReportHelper.mtrStatusIDLE+"\n"+
								" : "+detailedReportHelper.mtrStatusMS;
								
									
						HashMap<String, String> map = new HashMap<String, String>();
						intex_cout++;
						map.put("id_number" ,intex_cout+".");
						map.put("txt_tariff_desc" ,detailedReportHelper.traifDesc.trim());
						map.put("txt_no_of_conn" ,detailedReportHelper.report_totalConn+"");
						map.put("txt_count_column1" ,string);
						map.put("txt_count_column2" ,string_count);
						map.put("txt_units" ,detailedReportHelper.report_Totalunits+"");
						map.put("txt_revenue", detailedReportHelper.report_totalRev+"");
						map.put("txt_no_of_billed_conn", detailedReportHelper.report_Billed+"");
						fillMaps.add(map);
						
						}
						
						
						SimpleAdapter adapter = new SimpleAdapter(
								ReportViewHelper.this,
								fillMaps,
								R.layout.view_detailed_report,
								from, to);
						//status = true ;
						mDrawerList.setAdapter(adapter);
						Utility.setListViewHeightBasedOnChildren(mDrawerList);
					
					
					
				} else {
					new AlertDialog.Builder(ReportViewHelper.this)
					.setTitle("Info")
					.setMessage("Sorry..!\nNot Able to generate report")
					.setPositiveButton(	"Ok",	null)
					.show();
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		databasehandler.close();
}
public void generateRouteWiseSummeryReport_new(){
	 DatabaseHandler databasehandler = new DatabaseHandler(ReportViewHelper.this);
		try {
			databasehandler.openToRead();
			String totalCount =	databasehandler.getTotalCount() ;
			String billCount =databasehandler.Getbillledcount() ;
			String unbilled = "-" ;
			synced_total = databasehandler.GetBilledupdatedcountfromlocal();
			
			try {
				unbilled = (Integer.parseInt(totalCount) - Integer.parseInt(billCount))+"";
			} catch (NumberFormatException e) {
				unbilled = "0";
			}catch (Exception e) {
				unbilled = "0";
			}
			
			ReportViewHelper.totalCount = totalCount ;
			 billedCount =billCount ;
			 unBilledCount =unbilled; 
			 
			if (UtilMaster.detailedReportHelpers != null)
				UtilMaster.detailedReportHelpers.clear();
			
			
			
			 
			// int temp_units = 0 ,  temp_BD = 0;
			List<DetailedReportHelper> list_ =databasehandler.getDetailedRouteWiseReport();
			
			List<DetailedReportHelper> reportHelpers   = new ArrayList<DetailedReportHelper>();
			MasterLibraryFunction	libraryFunction = new MasterLibraryFunction(ReportViewHelper.this);
				
			
			drepotTotal_rev = "0" ;
			drepotTotal_Units = "0";
			int unit_01 = 0 , rev_01 = 0 ;
			for (DetailedReportHelper helper : list_) {
				//helper.traifDesc  = libraryFunction.getTariffDesc(ReportViewHelper.this, helper.tariffCode);
				rev_01 += helper.report_totalRev ;
				unit_01 += helper.report_Totalunits;
				reportHelpers.add(helper);
			}
			drepotTotal_rev = rev_01+"" ;
			drepotTotal_Units = unit_01+"";
			
			UtilMaster.detailedReportHelpers = (reportHelpers) ;
			if(reportHelpers != null)
			{
				if (reportHelpers.size() > 0) {
					
					
					
					UtilMaster.	reportType  ="ROUTEWISE_SUMMERY_REPORT" ;
					/*startActivity(new Intent(ReportViewHelper.this,	BluetoothChat_Reports.class));*/
					String[] from = null;
					int[] to = null;
					txt_report_heading.setVisibility(View.VISIBLE);
					txt_report_heading.setText("READING DATE WISE");
					rep_view_mrname.setText(UtilMaster.mGLogin_MRCode);
					ReportViewHelper.totalCount = totalCount ;
					txt_total_rep_view.setText(ReportViewHelper.totalCount);
					txt__rep_view_billed .setText(billedCount);
					txt_rep_view_unbilled .setText(unBilledCount);
					String imeino ="-";
					try {
						TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
						   imeino = tm.getDeviceId();
					} catch (Exception e) {
						e.printStackTrace();
						imeino ="-";
					}
					rep_view_device_id.setText(imeino);
					 rep_table_row_tot_units.setVisibility(View.VISIBLE);
					 rep_table_row_tot_revenue.setVisibility(View.VISIBLE);
					 rep_tot_units.setText(drepotTotal_Units);
					 rep_tot_revenue.setText(drepotTotal_rev);
					 
					 String string="" , string_count="";
						/*for (DetailedReportHelper detailedReportHelper : reportHelpers) {
							string += (mStatusReportHelper.status.trim())+ " \n";
							string_count += (" "+(mStatusReportHelper.count))+ " \n";
						}*/
						
						List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
						from = new String[] { "id_number" , "txt_tariff_desc" , "txt_no_of_conn" , "txt_count_column1" , "txt_count_column2", "txt_units", "txt_revenue",
								"txt_no_of_billed_conn", "txt_tariff_desc_lable"};
						to = new int[] { R.id.id_number , R.id.txt_tariff_desc , R.id.txt_no_of_conn, R.id.txt_count_column1, R.id.txt_count_column2, R.id.txt_units,
								R.id.txt_revenue , R.id.txt_no_of_billed_conn , R.id.txt_tariff_desc_lable};
						int intex_cout= 0 ;
						/*for (DetailedReportHelper detailedReportHelper : reportHelpers) {*/
						for (int i= 0 ; i < list_.size() ; i++) {
							DetailedReportHelper detailedReportHelper = reportHelpers.get(i) ;
							
							string =" : "+detailedReportHelper.mtrStatusNrm+"\n"+
									" : "+detailedReportHelper.mtrStatusMNR+"\n"+
									" : "+detailedReportHelper.mtrStatusDISSCONN+"\n"+
									" : "+detailedReportHelper.mtrStatusMB+"\n"+
									" : "+detailedReportHelper.mtrStatusNV;
									
									
						string_count = " : "+detailedReportHelper.mtrStatusDL+"\n"+
								" : "+detailedReportHelper.mtrStatusDC+"\n"+
								" : "+detailedReportHelper.mtrStatusIDLE+"\n"+
								" : "+detailedReportHelper.mtrStatusMS;
								
									
						HashMap<String, String> map = new HashMap<String, String>();
						intex_cout++;
						map.put("txt_tariff_desc_lable", "RngDate ");
						map.put("id_number" ,intex_cout+".");
						map.put("txt_tariff_desc" ,detailedReportHelper.tariffCode.trim());
						map.put("txt_no_of_conn" ,detailedReportHelper.report_totalConn+"");
						map.put("txt_count_column1" ,string);
						map.put("txt_count_column2" ,string_count);
						map.put("txt_units" ,detailedReportHelper.report_Totalunits+"");
						map.put("txt_revenue", detailedReportHelper.report_totalRev+"");
						map.put("txt_no_of_billed_conn", detailedReportHelper.report_Billed+"");
						fillMaps.add(map);
						
						}
						
						
						SimpleAdapter adapter = new SimpleAdapter(
								ReportViewHelper.this,
								fillMaps,
								R.layout.view_detailed_report,
								from, to);
						//status = true ;
						mDrawerList.setAdapter(adapter);
						Utility.setListViewHeightBasedOnChildren(mDrawerList);
					
					
					
				} else {
					new AlertDialog.Builder(ReportViewHelper.this)
					.setTitle("Info")
					.setMessage("Sorry..!\nNot Able to generate report")
					.setPositiveButton(	"Ok",	null)
					.show();
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		databasehandler.close();
}

/**
 * @author Guru
 * @date 05-Nov-2014
 * @category Updating billed data from local to server 
 */
public class performBackgroundTaskupload_new extends AsyncTask<Void, Void, String> 
{
	int count_update = 0 ;
	private ProgressDialog Dialog ;
	String err_msg_show = "NO";
	boolean b = false;
	protected void onPreExecute() 
	{
		Dialog = new ProgressDialog(	ReportViewHelper.this);
		Dialog.setMessage("Please wait...");
		Dialog.setCancelable(false);
		Dialog.show();
	}

		protected String doInBackground(Void... params) {

			try {
				
				runOnUiThread(new Runnable() {  
                    @Override
                    public void run() {
                    	System.out.println("***************");
        				  generateDetailedSummeryReport_new();
                    }
                });
				
				
				
				if (b)
					err_msg_show = "OK";

				
			} catch (Exception e) {
				err_msg_show = "NO";
				e.printStackTrace();
			}

			return err_msg_show;
		}

	protected void onPostExecute(String unused) {

		
			Dialog.dismiss();
			
			if(unused.equalsIgnoreCase("OK")){
				
				
			 }else{
				  new AlertDialog.Builder(ReportViewHelper.this)
				   .setTitle("Info")
				   .setMessage("Sorry..!\nNot Able to generate report")
				   .setCancelable(false)
				   .setPositiveButton("OK",new DialogInterface.OnClickListener()
				   {
					   public void onClick(DialogInterface dialog,int which) 
					   {
						  startActivity(new Intent(ReportViewHelper.this, ReportActionClass.class));
					   }
				   }).show();
			 }
			
}
}


public String getMeterStatus(String value) {
	String mtrStatus = "-";
	if (value.equals("1")) {
		mtrStatus = getString(R.string.a);
	} else if (value.equals("2")) {
		mtrStatus = getString(R.string.b);
	} else if (value.equals("3")) {

		mtrStatus = getString(R.string.c);
	} else if (value.equals("4")) {
		mtrStatus = getString(R.string.d);
	} else if (value.equals("5")) {
		mtrStatus = getString(R.string.e);
	} else if (value.equals("6")) {
		mtrStatus = getString(R.string.f);
	} else if (value.equals("7")) {
		mtrStatus = getString(R.string.g);
	} else if (value.equals("8")) {
		mtrStatus = getString(R.string.h);
	} else if (value.equals("9")) {
		mtrStatus = getString(R.string.i);
	} else {
		mtrStatus = ("-");
	}

	return mtrStatus ;
}
public  String getTariffDesc(Context m_context , String tCode_){
	String tDesc = "-";
	try {
	DBTariffHandler tariffHandler = new DBTariffHandler(m_context);
	tariffHandler.openToRead();
	tDesc = tariffHandler.getTariffDesc(tCode_);
	tariffHandler.close() ;
	
	} catch (Exception e) {
		e.printStackTrace();
		return tDesc ;
	}
	return tDesc ;
}
}
