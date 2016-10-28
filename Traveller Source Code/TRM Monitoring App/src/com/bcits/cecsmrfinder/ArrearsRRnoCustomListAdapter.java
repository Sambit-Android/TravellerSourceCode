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
import android.widget.TextView;
import android.widget.Toast;

public class ArrearsRRnoCustomListAdapter extends BaseAdapter {

	// String []result;
	Context context;
	JSONArray arr;
	private static LayoutInflater inflater = null;
	JSONObject map;

	public ArrearsRRnoCustomListAdapter(Context contxt) throws JSONException {
		// TODO Auto-generated constructor stub

		context = contxt;
		arr = ArrearsRangeGridActivity.jsonresponse;
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
		TextView arrears;
		TextView rownumber;
		TextView mrcode;
		// Button showonmap;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;

		rowView = inflater.inflate(R.layout.arrears_rrno_list_singleitem, null);

		try {

			final JSONObject j = arr.getJSONObject(position);
			holder.rr_no = (TextView) rowView.findViewById(R.id.rrnotext);
			holder.tariff = (TextView) rowView.findViewById(R.id.tarifftext);
			holder.name = (TextView) rowView.findViewById(R.id.nametext);
			holder.map = (ImageView) rowView.findViewById(R.id.mapview);
			holder.rownumber = (TextView) rowView.findViewById(R.id.rowno);
			holder.mrcode = (TextView) rowView.findViewById(R.id.mrcodetxt);
			holder.arrears = (TextView) rowView
					.findViewById(R.id.arrearsamounttext);

			String arrearsTxt = j.getString("arrears");
			Double value = Double.parseDouble(arrearsTxt);

			String udata = "RR NO - " + j.getString("consumer_sc_no");
			SpannableString content = new SpannableString(udata);
			content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);

			holder.rr_no.setText(content);
			holder.tariff.setText("TARIFF - " + j.getString("tariff"));
			holder.name.setText("NAME - " + j.getString("consumer_name")
					+ "\nADDRESS - " + j.getString("address"));
			holder.arrears.setText("Rs. " + Math.round(value));
			holder.rownumber.setText("" + (position + 1));
			holder.mrcode.setText("MR CODE - "+j.getString("mrcode")+" (L)");
			holder.mrcode.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(
							context, MapSurvey.class);
					context.startActivity(intent);
				}
			});
			holder.rr_no.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try {
						ConsumerHistorySearch.rrno = j
								.getString("consumer_sc_no");
						Intent in = new Intent(context,
								ConsumerHistoryAllDetails.class);
						in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(in);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});

			holder.map.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try {
						String arrearsTxt = j.getString("arrears");
						Double value = Double.parseDouble(arrearsTxt);

						if (j.getString("lattitude").equals("null")
								|| j.getString("lattitude").equals(null)
								|| j.getString("lattitude").equals("0.0")||j.getString("lattitude").equals("0")
								|| j.getString("longitude").equals("null")
								|| j.getString("longitude").equals("null")
								|| j.getString("longitude").equals("null")
								|| j.getString("longitude").contains("E")
								|| j.getString("lattitude").contains("E")) {
							Intent in = new Intent(context,
									ArrearsRRnoMapActivity.class);

							in.putExtra("latitude", "12.2958");
							in.putExtra("longitude", "76.6394");
							in.putExtra("arrears",
									String.valueOf(Math.round(value)));
							in.putExtra("consumer_name",
									j.getString("consumer_name"));
							in.putExtra("reading_date",
									j.getString("reading_date"));
							in.putExtra("bill_month", j.getString("bill_month"));
							in.putExtra("consumer_sc_no",
									j.getString("consumer_sc_no"));
							in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(in);
						} else {
							Intent in = new Intent(context,
									ArrearsRRnoMapActivity.class);

							in.putExtra("latitude", j.getString("lattitude"));
							in.putExtra("longitude", j.getString("longitude"));

							in.putExtra("arrears",
									String.valueOf(Math.round(value)));
							in.putExtra("consumer_name",
									j.getString("consumer_name"));
							in.putExtra("reading_date",
									j.getString("reading_date"));
							in.putExtra("bill_month", j.getString("bill_month"));
							in.putExtra("consumer_sc_no",
									j.getString("consumer_sc_no"));
							in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

							context.startActivity(in);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return rowView;
	}

}
