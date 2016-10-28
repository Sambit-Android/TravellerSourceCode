package com.bcits.utils;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcits.cecsmrfinder.R;

public class TransactionDOAdapter extends ArrayAdapter<MrWiseBillingProgressGetterSetter> implements Filterable{

		private Context context;
		private List<MrWiseBillingProgressGetterSetter> items;
		private Filter planetFilter;
		private List<MrWiseBillingProgressGetterSetter> origAllCashList;
		public TransactionDOAdapter(Context context, int textViewResourceId,
				ArrayList<MrWiseBillingProgressGetterSetter> items) {
			super(context, textViewResourceId, items);
			this.context = context;
			this.items = items;
			this.origAllCashList = items;
		}
		
		public int getCount() {
			return items.size();
		}

		public MrWiseBillingProgressGetterSetter getItem(int position) {
			return items.get(position);
		}

		public long getItemId(int position) {
			return items.get(position).hashCode();
		}


		@SuppressLint("InflateParams")
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			TransactionHolder holder =  new TransactionHolder();;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.custom_allcash_list3, null);
				
				//holder.serialNo = (TextView) view.findViewById(R.id.serialno);
				holder.totalmonth = (TextView) view
						.findViewById(R.id.title2);
				holder.monthpercentage = (TextView) view
						.findViewById(R.id.billingpercentage);
				holder.mrcodetitle = (TextView) view
						.findViewById(R.id.mrcodetitle);
				holder.mrname = (TextView) view
						.findViewById(R.id.mrname);
				holder.mrmobile = (TextView) view
						.findViewById(R.id.mrmobile);
				holder.linearOffline = (LinearLayout) view
						.findViewById(R.id.linearlayout);
			
				view.setTag(holder);

				

			} else {
				holder = (TransactionHolder) view.getTag();
			}
			 MrWiseBillingProgressGetterSetter item = items.get(position);
			// Planet p = planetList.get(position);
			if (item != null) {

				if (holder.mrcodetitle != null) {

					// final String id = String.format(item.getId());
					final String mrcode = item.getMrcode();
					final String tobebilled = item.getTobebilled();
					final String billed = item.getBilled();
					final String unbilled = item.getUnbilled();
					final String tobebilledday = item.getTobebilledday();
					final String billedday = item.getBilledday();
					final String unbilledday = item.getUnbilledday();
					final String mrname = item.getMrname();
					final String mrmobile = item.getMrmobilenumber();
					
					float billing_percentage = (Float.parseFloat(billed)/Float.parseFloat(tobebilled))*100;
					double newKB = Math.round(billing_percentage*100.0)/100.0;

					float billing_percentage_day = (Float.parseFloat(billedday)/Float.parseFloat(tobebilledday))*100;
					double newKB2 = Math.round(billing_percentage_day*100.0)/100.0;
					
					if (Double.isNaN(billing_percentage_day)) {
						newKB2 = 0;
					}
					if (Double.isNaN(billing_percentage)) {
						newKB = 0;
					}
					
					
					//holder.serialNo.setText("Sl No "+String.valueOf(position + 1));
					holder.totalmonth.setText(tobebilled+"/"+billed);
					holder.monthpercentage.setText(newKB+"%");
					holder.mrcodetitle.setText("MR : "+mrcode);
					holder.mrname.setText(mrname);
					holder.mrmobile.setText(mrmobile);
					holder.mrmobile.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if(!mrmobile.equalsIgnoreCase("NA")&&(!mrmobile.equalsIgnoreCase(""))){
								Intent intent = new Intent(Intent.ACTION_CALL);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.setData(Uri.parse("tel:" + mrmobile));
								context.startActivity(intent);
							}
							
						}
					});
					
					if (position % 2 == 0) {
						holder.linearOffline.setBackgroundColor(view.getResources().getColor(
								R.color.detailed_mr_card_row_dark));
					} else {
						holder.linearOffline.setBackgroundColor(view.getResources().getColor(
								R.color.detailed_mr_card_row_light));
					}
				}
			}
			return view;
		}

		public void resetData() {
			items = origAllCashList;
		}
		
		private static class TransactionHolder {
			LinearLayout linearOffline;
			//TextView serialNo;
			TextView totalmonth;
			TextView monthpercentage;
			TextView mrcodetitle;
			TextView mrname;
			TextView mrmobile;
		}
		
		 @Override
			public Filter getFilter() {
				if (planetFilter == null)
					planetFilter = new PlanetFilter();
				
				return planetFilter;
			}
		private class PlanetFilter extends Filter {

			
			
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults results = new FilterResults();
				// We implement here the filter logic
				if (constraint == null || constraint.length() == 0) {
					// No filter implemented we return all the list
					results.values = origAllCashList;
					results.count = origAllCashList.size();
				}
				else {
					// We perform filtering operation
					List<MrWiseBillingProgressGetterSetter> nPlanetList = new ArrayList<MrWiseBillingProgressGetterSetter>();
					
					for (MrWiseBillingProgressGetterSetter p : items) {
						if (p.getMrcode().toUpperCase().startsWith(constraint.toString().toUpperCase()) || p.getTobebilled().toUpperCase().startsWith(constraint.toString().toUpperCase()) )
							nPlanetList.add(p);
						
					}
					
					results.values = nPlanetList;
					results.count = nPlanetList.size();
					

				}
				return results;
			}

			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				
				// Now we have to inform the adapter about the new list filtered
				if (results.count == 0)
					notifyDataSetInvalidated();
				else {
					items = (List<MrWiseBillingProgressGetterSetter>) results.values;
					notifyDataSetChanged();
				}
				
			}
			
		} 
	
	}
