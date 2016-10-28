package com.bcits.cecsmrfinder;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MrGridCustomAdapter extends BaseAdapter {

	String result;
	Context context;
	JSONArray mrdetailstotal;
	
	private static LayoutInflater inflater = null;

	public MrGridCustomAdapter(Context contxt,String mrdetails) throws JSONException {
		// TODO Auto-generated constructor stub
		context = contxt;
		result = mrdetails;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mrdetailstotal = new JSONArray(result);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mrdetailstotal.length();
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
		TextView mr_code;
		TextView mr_name;
		TextView mr_mobile;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;

		rowView = inflater.inflate(R.layout.mrgridsingle, null);
		holder.mr_code = (TextView) rowView.findViewById(R.id.mrcode);
		holder.mr_name = (TextView) rowView.findViewById(R.id.name);
		holder.mr_mobile = (TextView) rowView.findViewById(R.id.mobilenumber);

		try {
			holder.mr_code.setText(mrdetailstotal.getJSONObject(position).getString("mr_code"));
			holder.mr_name.setText(mrdetailstotal.getJSONObject(position).getString("mr_name").length()>18
					?mrdetailstotal.getJSONObject(position).getString("mr_name").substring(0, 18)
							:mrdetailstotal.getJSONObject(position).getString("mr_name"));
			if(mrdetailstotal.getJSONObject(position).getString("mr_mobile") .equals("-")){
				holder.mr_mobile.setText("");
			}else{
				holder.mr_mobile.setText(mrdetailstotal.getJSONObject(position).getString("mr_mobile"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowView;
	}

}
