package com.bcits.cecsmrfinder;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RRnumber_custom_listAdapter extends BaseAdapter {

	//String []result;
	Context context;
	JSONArray arr ;
	private static LayoutInflater inflater = null;
	JSONObject map;
	

	public RRnumber_custom_listAdapter(Context contxt) throws JSONException {
		// TODO Auto-generated constructor stub
		
		context = contxt;
		arr = DetailedMRCardActivity.rrjson;
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
		TextView rr_no;
		TextView tariff;
		TextView name;
		ImageView map;
		//TextView address;
		TextView rownumber;
		//Button showonmap;
		
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;

		rowView = inflater.inflate(R.layout.rr_no_custom_listitem, null);
		
		
			try {
				JSONObject j  = arr.getJSONObject(position);
				holder.rr_no = (TextView) rowView.findViewById(R.id.rrnotext);
				holder.tariff = (TextView) rowView.findViewById(R.id.tarifftext);
				holder.name = (TextView) rowView.findViewById(R.id.nametext);
				holder.map = (ImageView) rowView.findViewById(R.id.mapview);
				holder.rownumber = (TextView) rowView.findViewById(R.id.rowno);
				
				holder.rr_no.setText("RR NO - "+j.getString("rr_no"));
				holder.tariff.setText("TARIFF - "+j.getString("tariffdesc"));
				holder.name.setText(j.getString("name")+"\n"+j.getString("address1")+" "+j.getString("address2"));
				//holder.address.setText(j.getString("address1")+" "+j.getString("address2"));
				holder.rownumber.setText(""+(position+1));
				map = new JSONObject();
				map.put("lat", j.getString("latitude").equals("0")||j.getString("latitude").contains("E")||j.getString("latitude").equals("")||j.getString("latitude").equals("null")||j.getString("latitude").equals(null)?"12.2958":j.getString("latitude"));
				map.put("long", j.getString("longitude").equals("0")||j.getString("longitude").contains("E")||j.getString("longitude").equals("")||j.getString("longitude").equals("null")||j.getString("longitude").equals(null)?"76.6394":j.getString("longitude"));
				map.put("rr_no", j.getString("rr_no"));
				map.put("tariff", j.getString("tariffdesc"));
				map.put("mrcode", DetailedMRCardActivity.mrcod);
				map.put("billno", j.getString("billno"));
				map.put("rdngdate", j.getString("rdngdate"));
				
				map.put("name", j.getString("name"));
				map.put("mobile", j.getString("mobile")==null?"-":j.getString("mobile"));
				map.put("meterstatus", j.getString("meterstatus"));
				map.put("billdate", j.getString("billdate"));
				map.put("netamtdue", j.getString("netamtdue"));
				//holder.rr_no.setTag(map);
				
				holder.map.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent in = new Intent(context,RRnoMapActivity.class);
						in.putExtra("map_obj", map.toString());
						v.getContext().startActivity(in);
					}
				});
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			   
	
		
		
		

		
		
		return rowView;
	}

}
