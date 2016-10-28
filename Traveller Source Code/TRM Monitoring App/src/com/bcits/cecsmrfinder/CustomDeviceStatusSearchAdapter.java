package com.bcits.cecsmrfinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.sqlite.cescip.DBMrWiseBilling;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomDeviceStatusSearchAdapter extends BaseAdapter {

	// String []result;
	Context context;
	JSONArray arr;
	String flag = "";
	private static LayoutInflater inflater = null;
	

	/* JSONObject map; */

	public CustomDeviceStatusSearchAdapter(Context contxt, JSONArray arrjson)
			throws JSONException {
		// TODO Auto-generated constructor stub

		this.context = contxt;
		arr = arrjson;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.length();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	Dialog dialog ;
	public class Holder {

		LinearLayout ll;
		TextView tv1;
		TextView tv2;
		TextView tv3;
		TextView tv4;
		TextView tv0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Holder holder = new Holder();
		View rowView;

		rowView = inflater.inflate(R.layout.device_status_search_list_singleitem, null);

		holder.ll = (LinearLayout) rowView.findViewById(R.id.linearlayout);
		holder.tv1 = (TextView) rowView.findViewById(R.id.tv1);
		holder.tv2 = (TextView) rowView.findViewById(R.id.tv2);
		holder.tv3 = (TextView) rowView.findViewById(R.id.tv3);
		holder.tv4 = (TextView) rowView.findViewById(R.id.tv4);
		holder.tv0 = (TextView) rowView.findViewById(R.id.tv0);
		try {
			
			String udata=arr.getJSONObject(position).getString("mrcode");
			 SpannableString content = new SpannableString(udata);
			 content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
			 if(arr.getJSONObject(position).getString("mrcode").equals("null")||arr.getJSONObject(position).getString("mrcode").equals(null)){
				 holder.tv1.setText(arr.getJSONObject(position)
							.getString("deviceid").equals("null") ? "" : arr
							.getJSONObject(position).getString("deviceid"));
					holder.tv2.setText("");
					holder.tv3.setText(arr.getJSONObject(position).getString("make")
							.equals("null") ? "" : arr.getJSONObject(position)
							.getString("make"));
					holder.tv4.setText(arr.getJSONObject(position)
							.getString("allocatedflag").equals("null") ? "" : arr
							.getJSONObject(position).getString("allocatedflag"));
					holder.tv0.setText("" + (position + 1));

					if (position % 2 == 0) {
						holder.ll.setBackgroundColor(rowView.getResources().getColor(
								R.color.detailed_mr_card_row_dark));
					} else {
						holder.ll.setBackgroundColor(rowView.getResources().getColor(
								R.color.detailed_mr_card_row_light));
					}
					
					holder.tv2.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if(holder.tv2.getText().toString().length()!=0){
							// TODO Auto-generated method stub
							
					        //for(int i =0;i<arr.length();i++){
					        	try {
									DBMrWiseBilling databasehandler = new DBMrWiseBilling(context);
									databasehandler.open();
									JSONObject mrdata = databasehandler.getMrDetailsDataFromMrCode(holder.tv2.getText().toString().trim());
									databasehandler.close();
									
									 dialog = new Dialog(context);
							        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							        dialog.setCancelable(false);
							        dialog.setContentView(R.layout.custom_dialog);
							        Button mrdetails = (Button) dialog.findViewById(R.id.m);
									TextView mrcode = (TextView) dialog.findViewById(R.id.mrcode);
									mrcode.setText(arr.getJSONObject(position).getString("mrcode"));
									TextView mobile = (TextView) dialog.findViewById(R.id.mobile);
							        mobile.setText(mrdata.getString("mobile").equals(null)||mrdata.getString("mobile").equals("null")||mrdata.getString("mobile")==null?"":mrdata.getString("mobile"));
							        TextView name = (TextView) dialog.findViewById(R.id.name);
							        name.setText(mrdata.getString("name").equals(null)||mrdata.getString("name").equals("null")||mrdata.getString("name")==null?"":mrdata.getString("name"));
							        TextView imei = (TextView) dialog.findViewById(R.id.imei);
							        imei.setText(arr.getJSONObject(position).getString("deviceid"));
							        TextView make = (TextView) dialog.findViewById(R.id.make);
							        make.setText(arr.getJSONObject(position).getString("make"));
							        
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					       // }
					        
					        
						}
						}
					});
			 }else{
			holder.tv1.setText(arr.getJSONObject(position)
					.getString("deviceid").equals("null") ? "" : arr
					.getJSONObject(position).getString("deviceid"));
			holder.tv2.setText(content);
			holder.tv3.setText(arr.getJSONObject(position).getString("make")
					.equals("null") ? "" : arr.getJSONObject(position)
					.getString("make"));
			holder.tv4.setText(arr.getJSONObject(position)
					.getString("allocatedflag").equals("null") ? "" : arr
					.getJSONObject(position).getString("allocatedflag"));
			holder.tv0.setText("" + (position + 1));

			if (position % 2 == 0) {
				holder.ll.setBackgroundColor(rowView.getResources().getColor(
						R.color.detailed_mr_card_row_dark));
			} else {
				holder.ll.setBackgroundColor(rowView.getResources().getColor(
						R.color.detailed_mr_card_row_light));
			}
			
			holder.tv2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(holder.tv2.getText().toString().length()!=0){
					// TODO Auto-generated method stub
					
			        //for(int i =0;i<arr.length();i++){
			        	try {
							DBMrWiseBilling databasehandler = new DBMrWiseBilling(context);
							databasehandler.open();
							JSONObject mrdata = databasehandler.getMrDetailsDataFromMrCode(holder.tv2.getText().toString().trim());
							databasehandler.close();
							
							final Dialog dialog = new Dialog(context);
					        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					        dialog.setCancelable(true);
					        dialog.setContentView(R.layout.custom_dialog);
					        Button mrdetails = (Button) dialog.findViewById(R.id.m);
							TextView mrcode = (TextView) dialog.findViewById(R.id.mrcode);
							mrcode.setText(arr.getJSONObject(position).getString("mrcode"));
							TextView mobile = (TextView) dialog.findViewById(R.id.mobile);
					        mobile.setText(mrdata.getString("mobile").equals(null)||mrdata.getString("mobile").equals("null")||mrdata.getString("mobile")==null?"":mrdata.getString("mobile"));
					        TextView name = (TextView) dialog.findViewById(R.id.name);
					        name.setText(mrdata.getString("name").equals(null)||mrdata.getString("name").equals("null")||mrdata.getString("name")==null?"":mrdata.getString("name"));
					        TextView imei = (TextView) dialog.findViewById(R.id.imei);
					        imei.setText(arr.getJSONObject(position).getString("deviceid"));
					        TextView make = (TextView) dialog.findViewById(R.id.make);
					        make.setText(arr.getJSONObject(position).getString("make"));
					        mrdetails.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									try {
										Intent in = new Intent(context,DetailedMRCardActivity.class);
										in.putExtra("mrcode", arr.getJSONObject(position).getString("mrcode"));
										in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
										context.startActivity(in);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
					        dialog.show();
					        dialog.show();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			       // }
			        
			        
				}
				}
			});
			 }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowView;
	}

}
