package com.bcits.cecsmrfinder;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapterArrearsRange extends BaseAdapter {

	String []result;
	Context context;
	
	private static LayoutInflater inflater = null;

	public CustomAdapterArrearsRange(Context contxt,String []mrdetails) throws JSONException {
		// TODO Auto-generated constructor stub
		context = contxt;
		result = mrdetails;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.length;
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
		TextView rr_range;
		
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;

		rowView = inflater.inflate(R.layout.single_list_item_arrears_selectrange, null);
		holder.rr_range = (TextView) rowView.findViewById(R.id.textView1);
		

		try {
			if(position%2==0){
				holder.rr_range.setBackgroundColor(Color.parseColor("#d6eaf8"));
			}else{
				holder.rr_range.setBackgroundColor(Color.parseColor("#aed6f1"));
			}
			holder.rr_range.setText(result[position]);
			holder.rr_range.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowView;
	}

}
