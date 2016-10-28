package com.bcits.cecsmrfinder;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomHistorySearchAdapter extends BaseAdapter {

	//String []result;
	Context context;
	JSONArray arr ;
	private static LayoutInflater inflater = null;
	/*JSONObject map;*/
	

	public CustomHistorySearchAdapter(Context contxt) throws JSONException {
		// TODO Auto-generated constructor stub
		
		context = contxt;
		arr = new JSONArray(ConsumerHistorySearch.responsefromserver);
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

	public class Holder {
		TextView rowno;
		TextView rrnotext;
		TextView tarifftext;
		TextView name;
		TextView address;
		TextView village;
		ImageView search;
		LinearLayout ll;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Holder holder = new Holder();
		View rowView;

		rowView = inflater.inflate(R.layout.consumer_history_rrnolist_singleitem, null);
		
		
			try {
				final JSONObject j  = arr.getJSONObject(position);
				holder.rrnotext = (TextView) rowView.findViewById(R.id.rrnotext);
				holder.tarifftext = (TextView) rowView.findViewById(R.id.tarifftext);
				holder.name = (TextView) rowView.findViewById(R.id.nametext);
				//holder.address = (TextView) rowView.findViewById(R.id.address);
				//holder.village = (TextView) rowView.findViewById(R.id.village);
				holder.search = (ImageView) rowView.findViewById(R.id.mapview);
				holder.ll = (LinearLayout) rowView.findViewById(R.id.linearlayout01);
				//holder.rrnotext.setText("RR No - "+j.getString("RRNO"));
				//TextView textView = (TextView) view.findViewById(R.id.textview);
				SpannableString content = new SpannableString("RR No - "+j.getString("RRNO"));
				content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
				holder.rrnotext.setText(content);
				holder.tarifftext.setText("Tariff - "+j.getString("TARIFFDCB"));
				holder.name.setText("NAME : "+j.getString("CONSUMERNAME")+"\nSTATUS : "+j.getString("INSTSTATUS")+"\nVILLAGE : "+j.getString("VILLAGENAME"));
				holder.rowno = (TextView) rowView.findViewById(R.id.rowno);
				holder.rowno.setText(""+(position+1));
				
				holder.search.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try {
							if(j.getString("LATITUDE").equals("null")||j.getString("LATITUDE").equals(null)||j.getString("LATITUDE").equals("0")||j.getString("LONGTITUTED").equals("null")||j.getString("LONGTITUTED").equals("null")||j.getString("LONGTITUTED").equals("null")){
								Intent in = new Intent(context,ConsumerHistoryMapActivity.class);
								
								in.putExtra("latitude", "12.2958");
								in.putExtra("longitude", "76.6394");
								in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								context.startActivity(in);
							}else{
							Intent in = new Intent(context,ConsumerHistoryMapActivity.class);
							
							in.putExtra("latitude", j.getString("LATITUDE"));
							in.putExtra("longitude", j.getString("LONGTITUTED"));
							in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(in);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
				holder.rrnotext.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ConsumerHistorySearch.rrno = holder.rrnotext.getText().toString().substring(8);
						Intent intent = new Intent(
								context,
								ConsumerHistoryAllDetails.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					}
				});
				if(position%2==0){
					holder.ll.setBackgroundColor(rowView.getResources().getColor(R.color.detailed_mr_card_row_dark));
				}else{
					holder.ll.setBackgroundColor(rowView.getResources().getColor(R.color.detailed_mr_card_row_light));
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			   
	
		
		
		

		
		
		return rowView;
	}

}
