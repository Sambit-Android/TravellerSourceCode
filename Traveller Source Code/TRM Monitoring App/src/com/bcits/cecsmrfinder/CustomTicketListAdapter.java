package com.bcits.cecsmrfinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bcits.cecsmrfinder.CustomDeviceStatusSearchAdapter.Holder;
import com.sqlite.cescip.DBMrWiseBilling;

public class CustomTicketListAdapter extends BaseAdapter{

	// String []result;
	Context context;
	JSONArray arr;
	String flag = "";
	private static LayoutInflater inflater = null;
	TextView officermobile, docketsummary, email, section , name, categoryname, subcategoryname, 
	docketsource, docketupdatedate, estresolvetime, assignedperson , designation, docketno, 
	docketstatus, docketcreateddate, resolveddate;
	Spinner statuschange;


	/* JSONObject map; */

	public CustomTicketListAdapter(Context contxt,String response)
			throws JSONException {
		// TODO Auto-generated constructor stub

		this.context = contxt;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		arr = new JSONArray(response);
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
		ImageView tv4;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Holder holder = new Holder();
		View rowView;
		
		
		rowView = inflater.inflate(R.layout.ticket_status_list_single_item, null);

		holder.ll = (LinearLayout) rowView.findViewById(R.id.linearlayout);
		holder.tv1 = (TextView) rowView.findViewById(R.id.tv1);
		holder.tv2 = (TextView) rowView.findViewById(R.id.tv2);
		holder.tv3 = (TextView) rowView.findViewById(R.id.tv3);
		holder.tv4 = (ImageView) rowView.findViewById(R.id.buttonticket);
		try {

			if (position % 2 == 0) {
				holder.ll.setBackgroundColor(rowView.getResources().getColor(
						R.color.detailed_mr_card_row_dark));
			} else {
				holder.ll.setBackgroundColor(rowView.getResources().getColor(
						R.color.detailed_mr_card_row_light));
			}


			holder.tv1.setText(arr.getJSONObject(position).getString("docketNumber"));
			holder.tv2.setText(arr.getJSONObject(position).getString("docketSummary"));
			holder.tv3.setText(arr.getJSONObject(position).getString("docketStatus"));
			holder.tv4.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog = new Dialog(context);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setCancelable(true);
					dialog.setContentView(R.layout.custom_ticket_dialog);
					officermobile = (TextView) dialog.findViewById(R.id.officermobile);
					docketsummary = (TextView) dialog.findViewById(R.id.docketsummary);
					email = (TextView) dialog.findViewById(R.id.officeremail);
					section = (TextView) dialog.findViewById(R.id.sectiondialog);
					name = (TextView) dialog.findViewById(R.id.officernamedialog);
					categoryname = (TextView) dialog.findViewById(R.id.categoryname);
					subcategoryname = (TextView) dialog.findViewById(R.id.subcategoryname);
					docketsource = (TextView) dialog.findViewById(R.id.docketsource);
					docketupdatedate = (TextView) dialog.findViewById(R.id.docketupdatedate);
					estresolvetime = (TextView) dialog.findViewById(R.id.estresolvetime);
					assignedperson = (TextView) dialog.findViewById(R.id.assignedpersonname);
					designation= (TextView) dialog.findViewById(R.id.designation);
					docketno = (TextView) dialog.findViewById(R.id.docketno);
					docketstatus = (TextView) dialog.findViewById(R.id.docketstatus);
					docketcreateddate = (TextView) dialog.findViewById(R.id.docketcreateddate);
					resolveddate = (TextView) dialog.findViewById(R.id.resolveddate);
					
					
					try {
						officermobile.setText(arr.getJSONObject(position).getString("consumerMobileNo").equals("")? "  " :arr.getJSONObject(position).getString("consumerMobileNo"));
						docketsummary.setText(arr.getJSONObject(position).getString("docketSummary"));
						email.setText(arr.getJSONObject(position).getString("consumerEmailId"));
						section.setText(arr.getJSONObject(position).getString("section"));
						name.setText(arr.getJSONObject(position).getString("userName"));
						categoryname.setText(arr.getJSONObject(position).getString("categoryName"));
						subcategoryname.setText(arr.getJSONObject(position).getString("subCategoryName"));
						docketsource.setText(arr.getJSONObject(position).getString("docketSource"));
						docketupdatedate.setText(arr.getJSONObject(position).getString("docketUpdatedDt"));
						estresolvetime.setText(arr.getJSONObject(position).getString("estResolveTime"));
						assignedperson.setText(arr.getJSONObject(position).getString("urName"));
						designation.setText(arr.getJSONObject(position).getString("designation"));
						docketno.setText(arr.getJSONObject(position).getString("docketNumber"));
						docketstatus.setText(arr.getJSONObject(position).getString("docketStatus"));
						docketcreateddate.setText(arr.getJSONObject(position).getString("docketCreatedDt"));
						resolveddate.setText(arr.getJSONObject(position).getString("resolvedDate"));

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					dialog.show();
					

				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowView;
	}

}
