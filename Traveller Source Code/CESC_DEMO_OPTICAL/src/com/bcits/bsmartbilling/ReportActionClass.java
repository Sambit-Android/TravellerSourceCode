package com.bcits.bsmartbilling;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ReportActionClass extends Activity {
	ImageView headerIcon ;
	/*ListView mDrawerList  ;*/
	Button btnlogout , btn_view_report ;
	TextView txt_report_heading ;
	/*public static String totalCount , billedCount , unBilledCount  , synced_total , drepotTotal_rev ,drepotTotal_Units  ;*/ 
	/*public static List<MStatusReportHelper> list ;*/
	
	 RadioGroup radioGroup1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_report_view);
		//setContentView(R.layout.settings_test);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
		TextView txtTitle=(TextView)findViewById(R.id.title);
		txtTitle.setText(getString(R.string.report));
		headerIcon = (ImageView)findViewById(R.id.header);
		
		 headerIcon.setImageDrawable(getResources().getDrawable(R.drawable.report_view));
		 btnlogout = (Button) findViewById(R.id.titleBack);
		/* mDrawerList =(ListView)findViewById(R.id.listView1);*/
		 btn_view_report= (Button) findViewById(R.id.btn_view_report);
		 txt_report_heading = (TextView)findViewById(R.id.txt_report_heading);
		 txt_report_heading.setVisibility(View.INVISIBLE);
		 radioGroup1 = (RadioGroup) findViewById(R.id.root_radio_Group);
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
			new AlertDialog.Builder(ReportActionClass.this)
							.setTitle("Sorry..!")
							.setMessage("Session timeout ..! Please Login again")
					   .setPositiveButton("OK", new DialogInterface.OnClickListener()
					   {
						   
						   public void onClick(DialogInterface dialog,int which) 
						   {
							 
						startActivity(new Intent(ReportActionClass.this,Login.class));
							//   return;

								
						   }
					   }).show();
		}
		
		btnlogout.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent inte_back = new Intent(	ReportActionClass.this,	MainActivity.class);
				inte_back.putExtra("status", "add");
				startActivity(inte_back);
				
			}
		}); 
		btn_view_report.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				UtilMaster.	reportType = getreportType(radioGroup1.getCheckedRadioButtonId());
			//	UtilMaster.	reportType  ="SUMMARY_REPORT" ;
				if("RADIO_UNBILLED_REPORT".equalsIgnoreCase(UtilMaster.reportType))
				startActivity(new Intent(getApplicationContext(), ViewUnBilledList.class));
				else{
					startActivity(new Intent(getApplicationContext(), ReportViewHelper.class));
				}
			
			}
		});
	}

	
	@Override
	public void onBackPressed() {
		Intent inte_back = new Intent(	ReportActionClass.this,	MainActivity.class);
		inte_back.putExtra("status", "add");
		startActivity(inte_back);
	}

	

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}


/*
public void generateSummeryReport(){
	 DatabaseHandler databasehandler = new DatabaseHandler(ReportActionClass.this);
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
			SettingsActivity.totalCount = totalCount ;
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
					txt_report_heading.setVisibility(View.VISIBLE);
					txt_report_heading.setText("SUMMARY REPORT");
					String[] from = null;
					int[] to = null;
					MStatusReportAdapter	adapter = new MStatusReportAdapter(getApplicationContext(),
							list,  totalCount, billedCount, unBilledCount);
					mDrawerList.setAdapter(adapter);
					UtilMaster.	reportType  ="SUMMARY_REPORT" ;
					startActivity(new Intent(ReportActionClass.this, BluetoothChat_Reports.class));
					
					
					String string="" , string_count="";
					for (MStatusReportHelper mStatusReportHelper : list) {
						string += (mStatusReportHelper.status.trim())+ " \n";
						string_count += (" "+(mStatusReportHelper.count))+ " \n";
					}
					List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
					from = new String[] { "rep_total","rep_billed", "rep_unbilled" ,"rep_status_wise" , "rep_status_wise_counts"};
					to = new int[] {R.id.rep_total,R.id.rep_billed, R.id.rep_unbilled , R.id.rep_status_wise , R.id.rep_status_wise_counts};
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("rep_total", " "+totalCount);
					map.put("rep_billed", " "+billedCount);
					map.put("rep_unbilled", " "+unBilledCount);
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
					new AlertDialog.Builder(ReportActionClass.this)
					.setTitle("Info")
					.setMessage("Sorry..!\nNot Able to generate report")
					.setPositiveButton(	"Ok",	null)
					.show();
				}
			}else{
				new AlertDialog.Builder(ReportActionClass.this)
				.setTitle("Info")
				.setMessage("Sorry..!\nNot Able to generate report")
				.setPositiveButton(	"Ok",	null)
				.show();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}*/


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

public String getreportType(int id){
	String mode = "driving" ;
	switch (id) 
    {
    case R.id.radio_summary_report:
    	mode = "SUMMARY_REPORT" ;
     break;
    case R.id.radioi_detailed_report:
    	mode = "DETAILED_SUMMARY_REPORT";
    	
     break;
    case R.id.radio_route_report:
    	mode = "ROUTEWISE_SUMMERY_REPORT";
   
     break;
    case R.id.radio_unbilled_report:
    	mode = "RADIO_UNBILLED_REPORT";
   
     break; 
     
    default:
     break;
    }
	
	return mode;
}

/*
public class CustomImageAdapter extends ArrayAdapter<MStatusReportHelper> {
    Context context;
    int layoutResourceId;
    LinearLayout linearMain;
    ArrayList<MStatusReportHelper> data = new ArrayList<MStatusReportHelper>();

    public CustomImageAdapter(Context context, int layoutResourceId,
                  ArrayList<MStatusReportHelper> data) {
           super(context, layoutResourceId, data);
           this.layoutResourceId = layoutResourceId;
           this.context = context;
           this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
           View row = convertView;

           if (row == null) {
                  LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                  row = inflater.inflate(layoutResourceId, parent, false);

                  linearMain = (LinearLayout) row.findViewById(R.id.lineraMain);

                  Item myImage = data.get(position);
                  for (int j = 0; j < myImage.getName().length; j++) {
                        TextView label = new TextView(context);
                        label.setText(myImage.name[j]);
                        linearMain.addView(label);
                  }
                  ImageView image = new ImageView(context);
                  int outImage = myImage.image;
                  image.setImageResource(outImage);
                  linearMain.addView(image);
           }

           return row;

    }

}*/


	
}
