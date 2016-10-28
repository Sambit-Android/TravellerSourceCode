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

public class DepositDetailsLVAdapter extends BaseAdapter {

	//String []result;
	Context context;
	JSONArray arr ;
	private static LayoutInflater inflater = null;
	/*JSONObject map;*/
	

	public DepositDetailsLVAdapter(Context contxt) throws JSONException {
		// TODO Auto-generated constructor stub
		
		context = contxt;
		arr = new JSONArray(ConsumerHistoryAllDetails.responsefromserverdepositdetails);
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
		LinearLayout ll;
		TextView billmonth,billunit,status,netamt;
		
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;

		rowView = inflater.inflate(R.layout.consumer_history_deposit_details_singleitem, null);
		
		
		try {
			holder.ll = (LinearLayout)rowView.findViewById(R.id.linearlayout);
			holder.billmonth = (TextView)rowView.findViewById(R.id.billmonth);
			holder.billunit = (TextView)rowView.findViewById(R.id.billunit);
			holder.status = (TextView)rowView.findViewById(R.id.status);
			holder.netamt = (TextView)rowView.findViewById(R.id.netamt);
			
			
			String udata=arr.getJSONObject(position).getString("RECEIPTDATE");
			 SpannableString content = new SpannableString(udata);
			 content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
			
			
			holder.billmonth.setText(content);
			holder.billunit.setText(arr.getJSONObject(position).getString("RECEIPTNO"));
			holder.status.setText(arr.getJSONObject(position).getString("AMOUNT"));
			holder.netamt.setText(arr.getJSONObject(position).getString("REMARKS"));
			
			
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
