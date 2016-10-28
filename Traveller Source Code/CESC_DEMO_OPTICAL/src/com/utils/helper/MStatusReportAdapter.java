package com.utils.helper;


import java.util.ArrayList;
import java.util.List;

import com.bcits.bsmartbilling.R;
import com.bcits.bsmartbilling.SettingsActivity;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MStatusReportAdapter extends BaseAdapter {

	private Context context;
	private List<MStatusReportHelper> navDrawerItems;
	public String total  ;
	public String billed  ;
	public String unbilled ;
	
	
	
	
	public MStatusReportAdapter(Context context,
			List<MStatusReportHelper> navDrawerItems, String total,
			String billed, String unbilled) {
		super();
		this.context = context;
		this.navDrawerItems = navDrawerItems;
		this.total = total;
		this.billed = billed;
		this.unbilled = unbilled;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.view_summary_report, null);
		}

		
		TextView rep_total = (TextView) convertView.findViewById(R.id.rep_total);
		TextView rep_billed = (TextView) convertView.findViewById(R.id.rep_billed);
		TextView rep_unbilled = (TextView) convertView.findViewById(R.id.rep_unbilled);
		TextView rep_status_wise = (TextView) convertView.findViewById(R.id.rep_status_wise);
		rep_total.setText(total) ;
		rep_billed.setText(billed) ;
		rep_unbilled.setText(unbilled) ;
		String string = "";
		for (MStatusReportHelper mStatusReportHelper : navDrawerItems) {
			
			/*mmOutStream.write(mStatusReportHelper.status.getBytes());
			mmOutStream.write((" "+mStatusReportHelper.count).getBytes());*/
			string+=mStatusReportHelper.status.getBytes()+" "+mStatusReportHelper.count+"\n";
		
		}
		rep_status_wise.setText(string) ;
		/*total =   SettingsActivity.totalCount.getBytes() ;
		billed = SettingsActivity.billedCount .getBytes() ;
		unbilled = SettingsActivity.unBilledCount.getBytes();*/
		
		return convertView;
	}

}
