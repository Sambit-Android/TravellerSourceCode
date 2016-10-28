package com.bcits.bsmartbilling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.utils.DBTariffHandler;
import com.utils.DatabaseHandler;
import com.utils.helper.UnbilledViewPojo;

public class ViewUnBilledList extends Activity {
	ImageView headerIcon ;
	Button btnlogout  ; 
	private LinearLayout mLinearScroll;
	private ListView mListView;
	private List<UnbilledViewPojo> mArrayListFruit;
	private List<UnbilledViewPojo> mArrayListFruitTemp;
	private HashMap<String, String > tarif_map ;

	// change row size according to your need, how many row you needed per page
	int rowSize = 6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.pagination);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
		TextView txtTitle=(TextView)findViewById(R.id.title);
		txtTitle.setText(getString(R.string.report));
		headerIcon = (ImageView)findViewById(R.id.header);
		headerIcon.setImageDrawable(getResources().getDrawable(R.drawable.report_view));
		btnlogout = (Button) findViewById(R.id.titleBack);
		
		mLinearScroll = (LinearLayout) findViewById(R.id.linear_scroll);
		mListView = (ListView) findViewById(R.id.listview1);
		
		/**
		 * add item into arraylist
		 */
		mArrayListFruit = new ArrayList<UnbilledViewPojo>();
		mArrayListFruitTemp = new ArrayList<UnbilledViewPojo>();
		DBTariffHandler tariffHandler = new DBTariffHandler(getApplicationContext());
		tariffHandler.openToRead();
		tarif_map = tariffHandler.getAllTariffCodeWithDesc();
		tariffHandler.close();
		
	}
	
	
	

	@Override
	protected void onStart() {
		super.onStart();
		
		
		try {
			
		
		
		
		if( (UtilMaster.mGLogin_MRCode.equalsIgnoreCase("")) ||  UtilMaster.mGLogin_SiteCode.equalsIgnoreCase("") ){
			new AlertDialog.Builder(ViewUnBilledList.this)
							.setTitle("Sorry..!")
							.setMessage("Session timeout ..! Please Login again")
					   .setPositiveButton("OK", new DialogInterface.OnClickListener()
					   {
						   
						   public void onClick(DialogInterface dialog,int which) 
						   {
							 
						startActivity(new Intent(ViewUnBilledList.this,Login.class));
							//   return;

								
						   }
					   }).show();
		}
		//startAdding() ;
		
		
		 DatabaseHandler databasehandlerHandler = new DatabaseHandler(getApplicationContext());
		 databasehandlerHandler.openToRead();
		 mArrayListFruit = databasehandlerHandler.getUnBilled();
		 databasehandlerHandler.close();
		 
		 if(tarif_map.size() > 0){
		 if(mArrayListFruit.size() > 0){
		 
		/**
		 * create dynamic button according the size of array
		 */
		int rem = mArrayListFruit.size() % rowSize;
		if (rem > 0) {
			for (int i = 0; i < rowSize - rem; i++) {
				mArrayListFruit.add(new UnbilledViewPojo((11111+i), "test", "test", "test"));
			}
		}
		
		/**
		 * add arraylist item into list on page load
		 */
		addItem(0);
		int size = mArrayListFruit.size() / rowSize;

		for (int j = 0; j < size; j++) {
			final int k;
			k = j;
			final Button btnPage = new Button(ViewUnBilledList.this);
			LayoutParams lp = new LinearLayout.LayoutParams(80,
					LayoutParams.WRAP_CONTENT);
			lp.setMargins(3, 2, 2, 2);
			btnPage.setTextColor(Color.WHITE);
			btnPage.setTextSize(20.0f);
			btnPage.setId(j);
			btnPage.setText(String.valueOf(j + 1));
			mLinearScroll.addView(btnPage, lp);

			btnPage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					/**
					 * add arraylist item into list
					 */
					addItem(k);
				}
			});
		}
		
		 }else{
			 new AlertDialog.Builder(ViewUnBilledList.this)
				.setTitle("Sorry..!")
				.setMessage("No reports found")
			   .setPositiveButton("OK", new DialogInterface.OnClickListener()
			   {
				   public void onClick(DialogInterface dialog,int which) 
				   {
					   startActivity(new Intent(ViewUnBilledList.this,ReportActionClass.class));
				   }
			   }).show();
		 }
		 
		 }else{
			 new AlertDialog.Builder(ViewUnBilledList.this)
				.setTitle("Sorry..!")
				.setMessage("Update Tariff table")
			   .setPositiveButton("OK", new DialogInterface.OnClickListener()
			   {
				   public void onClick(DialogInterface dialog,int which) 
				   {
					   startActivity(new Intent(ViewUnBilledList.this,ReportActionClass.class));
				   }
			   }).show();
		 }
			btnlogout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent inte_back = new Intent(ViewUnBilledList.this,ReportActionClass.class);
				startActivity(inte_back);
			}
		}); 
			
		} catch (Exception e) {
			e.printStackTrace();
			new AlertDialog.Builder(ViewUnBilledList.this)
			.setTitle("Sorry..!")
			.setMessage("Error..! Please try agin later")
		   .setPositiveButton("OK", new DialogInterface.OnClickListener()
		   {
			   public void onClick(DialogInterface dialog,int which) 
			   {
				   startActivity(new Intent(ViewUnBilledList.this,ReportActionClass.class));
			   }
		   }).show();
		}	
	}


private void startAdding(){
	DatabaseHandler handler = new DatabaseHandler(getApplicationContext());
	handler.openToRead();
	mArrayListFruit = handler.getUnBilled();
	handler.close();
	if(mArrayListFruit.size() > 0){
		/**
		 * create dynamic button according the size of array
		 */
		int rem = mArrayListFruit.size() % rowSize;
		if (rem > 0) {
			for (int i = 0; i < rowSize - rem; i++) {
				mArrayListFruit.add(new UnbilledViewPojo((11111+i), "test", "test", "test"));
			}
		}
		
		/**
		 * add arraylist item into list on page load
		 */
		addItem(0);
		int size = mArrayListFruit.size() / rowSize;

		for (int j = 0; j < size; j++) {
			final int k;
			k = j;
			final Button btnPage = new Button(ViewUnBilledList.this);
			LayoutParams lp = new LinearLayout.LayoutParams(80,
					LayoutParams.WRAP_CONTENT);
			lp.setMargins(3, 2, 2, 2);
			btnPage.setTextColor(Color.WHITE);
			btnPage.setTextSize(20.0f);
			btnPage.setId(j);
			btnPage.setText(String.valueOf(j + 1));
			mLinearScroll.addView(btnPage, lp);

			btnPage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					/**
					 * add arraylist item into list
					 */
					addItem(k);
				}
			});
		}
		
	 }else{
		 new AlertDialog.Builder(ViewUnBilledList.this)
			.setTitle("Sorry..!")
			.setMessage("No reports found")
		   .setPositiveButton("OK", new DialogInterface.OnClickListener()
		   {
			   public void onClick(DialogInterface dialog,int which) 
			   {
				   startActivity(new Intent(ViewUnBilledList.this,ReportActionClass.class));
			   }
		   }).show();
	 }
	
}

	private void addItem(int i) {
		mArrayListFruitTemp.clear();
		i = i * rowSize;

		/**
		 * fill temp array list to set on page change
		 */
		for (int j = 0; j < rowSize; j++) {
			mArrayListFruitTemp.add(j, mArrayListFruit.get(i));
			i = i + 1;
		}

		// set view
		setView();
	}

	// set view method
	private void setView() {
		
		//mArrayListFruitTemp 
		String[] from = null;
		int[] to = null;
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
		from = new String[] { "id_number_unbilled" ,"txt_acc_no_unbilled" ,"txt_tariff_desc_unbilled" , "txt_name_unbilled_view"};
		to = new int[] { R.id.id_number_unbilled , R.id.txt_acc_no_unbilled , R.id.txt_tariff_desc_unbilled, R.id.txt_name_unbilled_view};
		for (UnbilledViewPojo pojo : mArrayListFruitTemp) {
			if( !"test".equalsIgnoreCase(pojo.getConsumer_No()) && !"test".equalsIgnoreCase(pojo.getTariff()))
			{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id_number_unbilled" ,pojo.getSl_no()+". ");
			map.put("txt_acc_no_unbilled" ,pojo.getConsumer_No());
			map.put("txt_tariff_desc_unbilled" , tarif_map.get(pojo.getTariff().trim()));
			map.put("txt_name_unbilled_view" ,pojo.getCon_name());
			fillMaps.add(map);
			}
			
		}	
		SimpleAdapter adapter = new SimpleAdapter(
				ViewUnBilledList.this,
				fillMaps,
				R.layout.view_unbilled_text,
				from, to);
		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ViewUnBilledList.this, android.R.layout.simple_list_item_1,
				mArrayListFruitTemp);*/
		mListView.setAdapter(adapter);
		

		/**
		 * On item click listner
		 */
		/*mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "Selected Row:"+position+". "+mArrayListFruitTemp.get(position).getConsumer_No(), Toast.LENGTH_LONG).show();
				System.out.println(mArrayListFruitTemp.get(position));
			}

		});*/
	}
	
	@Override
	public void onBackPressed() {
		Intent inte_back = new Intent(	ViewUnBilledList.this,	ReportActionClass.class);
		inte_back.putExtra("status", "add");
		startActivity(inte_back);
	}

}
