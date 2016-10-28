package com.sqlite.mvs;

import getter.setter.GetterSetterDisconnection;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;

import com.bcits.recondiscon.Login;
import com.bcits.utils.SendRequest;

public class DbDisconnection {

	public static final int DATABASE_VERSION = 26;
	static File dir = new File(Environment.getExternalStorageDirectory() + "");
	private static String DATABASE_NAME = dir+ "/MVS_METER_CONNECTION/DISCONNECTION.db";
	public static final String TABLE_NAME = " disconnectionmaster";
	
	public static final String COL_ID_LOCAL = "id_local";
	public static final String COL_NETWORK_MODE = "network_mode";
	
	public static final String COL_rrno = "rrno";
	public static final String COL_listno = "listno";
	public static final String COL_listdate = "listdate";
	public static final String COL_tariff = "tariff";
	public static final String COL_disdate = "disdate";
	public static final String COL_disfr = "disfr";
	public static final String COL_sdocode = "sdocode";
	public static final String COL_remarks = "remarks";
	public static final String COL_dramt = "dramt";
	public static final String COL_rdngdate = "rdngdate";
	public static final String COL_mtrcode = "mtrcode";
	public static final String COL_arrears = "arrears";
	public static final String COL_username = "username";
	public static final String COL_datestamp = "datestamp";
	public static final String COL_reclistno = "reclistno";
	public static final String COL_redate = "redate";
	public static final String COL_ageing = "ageing";
	public static final String COL_status = "status";
	public static final String COL_id = "id";
	public static final String COL_consumer_name = "consumer_name";
	public static final String COL_address = "address";
	public static final String COL_fdrcode = "fdrcode";
	public static final String COL_tccode = "tccode";
	public static final String COL_poleno = "poleno";
	public static final String COL_longitude_dis = "longitude_dis";
	public static final String COL_latitude_dis = "latitude_dis";
	public static final String COL_image_dis = "image_dis";
	public static final String COL_image_recon = "image_recon";
	
	private static final String COL_payAmount = "pay_amount";
	private static final String COL_payRecDate = "pay_date";
	private static final String COL_payRecNo = "pay_rec_no";
	private static final String COL_payMode = "pay_mode";
	private static final String COL_extra1 = "extra1"; // NOT YET USED
	private DatabaseHelper myDbHelper;
	private SQLiteDatabase myDb;
	private final Context context;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table " + TABLE_NAME + " " + "(" + COL_ID_LOCAL+ " integer primary key, " + COL_rrno + " text, "
		+ COL_listno + " text," 
		+ COL_listdate + " text,"
		+ COL_tariff + " text, " 
		+ COL_disdate + " date, "
		+ COL_disfr + " integer," 
		+ COL_sdocode + " text," 
		+ COL_remarks+ " text," 
		+ COL_dramt+ " text," 
		+ COL_rdngdate+ " text," 
		+ COL_mtrcode+ " text," 
		+ COL_arrears+ " text," 
		+ COL_username+ " text," 
		+ COL_datestamp+ " text," 
		+ COL_reclistno+ " text," 
		+ COL_redate+ " date," 
		+ COL_ageing+ " text," 
		+ COL_status+ " text," 
		+ COL_id+ " text," 
		+ COL_consumer_name+ " text," 
		+ COL_address+ " text," 
		+ COL_fdrcode+ " text," 
		+ COL_tccode+ " text," 
		+ COL_poleno+ " text," 
		+ COL_longitude_dis+ " text," 
		+ COL_latitude_dis+ " text," 
		+ COL_image_dis+ " text," 
		+ COL_image_recon+ " text," 
		+ COL_NETWORK_MODE+ " text," 
		+ COL_payAmount+ " text," 
		+ COL_payRecDate+ " text," 
		+ COL_payRecNo+ " text," 
		+ COL_payMode+ " text," 
		+ COL_extra1+ " text )");
			
			System.out.println("Disconnection table created or updated");
			try {
				db.execSQL("CREATE INDEX if not exists statusIndex ON "+ TABLE_NAME + "(" + COL_status + ")");
				System.out.println("Index created=============");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}

	public DbDisconnection(Context ctx) {
		this.context = ctx;
	}

	public void open() {
		try {
			myDbHelper = new DatabaseHelper(context);
			myDb = myDbHelper.getWritableDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			if (myDbHelper != null) {
				myDbHelper.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void startBooster() {
		try {
			myDb.execSQL("PRAGMA synchronous=OFF");
			myDb.setLockingEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void stopBooster() {
		try {
			myDb.execSQL("PRAGMA synchronous=OFF");
			myDb.setLockingEnabled(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertDisconnection(String rrno, String listno,String listdate, String tariff, String disdate, String disfr,String sdocode, String remarks
			, String dramt,String rdngdate, String mtrcode, String arrears, String username,String datestamp, String reclistno
			, String redate,String ageing, String status, String id, String consumer_name
			, String address,String fdrcode, String tccode, String poleno  , String latdis, String longdis,String payAmount,
			String payRecDate,String payRecNo,String payMode) {

		
		ContentValues contentValues = new ContentValues();
		contentValues.put(COL_sdocode, Login.sdoCode);
		contentValues.put(COL_rrno, rrno);
		contentValues.put(COL_listno, listno);
		contentValues.put(COL_listdate, listdate);
		contentValues.put(COL_tariff, tariff);
		contentValues.put(COL_disdate, disdate);//
		contentValues.put(COL_disfr, disfr);
		contentValues.put(COL_remarks, remarks);
		contentValues.put(COL_dramt, dramt);
		contentValues.put(COL_rdngdate, rdngdate);
		contentValues.put(COL_mtrcode, mtrcode);
		contentValues.put(COL_arrears, arrears);
		contentValues.put(COL_username, username);
		contentValues.put(COL_datestamp, datestamp);
		contentValues.put(COL_reclistno, reclistno);
		contentValues.put(COL_redate, redate);//
		contentValues.put(COL_ageing, ageing);
		contentValues.put(COL_status, status);
		contentValues.put(COL_id, id);
		contentValues.put(COL_consumer_name, consumer_name);
		contentValues.put(COL_address, address);
		contentValues.put(COL_fdrcode, fdrcode);
		contentValues.put(COL_tccode, tccode);
		contentValues.put(COL_poleno, poleno);
		
		contentValues.put(COL_latitude_dis, latdis);
		contentValues.put(COL_longitude_dis, longdis);
		
		contentValues.put(COL_payAmount, payAmount);
		contentValues.put(COL_payRecDate, payRecDate);
		contentValues.put(COL_payRecNo, payRecNo);
		contentValues.put(COL_payMode, payMode);
		
		
		myDb.insert(TABLE_NAME, null, contentValues);
		return true;
	}	
	

	
	public void getDate(){
		Cursor c=myDb.query(TABLE_NAME, null, COL_ID_LOCAL+"=5", null, null, null, null);
		if(c.moveToNext()){
		}
	}
	
	public ArrayList<String> getAccountNumberDiconnect() {
		String[] columns = { COL_rrno };
		Cursor c = myDb.query(TABLE_NAME, columns, COL_status+"='0'", null, null, null,COL_rrno);
		ArrayList<String> accounts = new ArrayList<String>();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			accounts.add(c.getString(c.getColumnIndex(COL_rrno)));
		}

		return accounts;
	}
	
	
	public ArrayList<String> getAccountNumberReconnect() {
		String[] columns = { COL_rrno };
		Cursor c = myDb.query(TABLE_NAME, columns, COL_status + "='2' and trim("+COL_redate+")='null'", null, null, null,COL_rrno);
		ArrayList<String> accounts = new ArrayList<String>();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			accounts.add(c.getString(c.getColumnIndex(COL_rrno)));
		}

		return accounts;
	}
	
	
	public String getTotalConnection() {
		Cursor c = myDb.rawQuery("select "+ COL_ID_LOCAL + " from " + TABLE_NAME, null);
		return c.getCount()+"";
	}

	public String getTotalDisconnected() {
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + "='1'", null);
		return c.getCount() + "";
	}
	
	
	public String getTodalToDisconnect() {
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + " = '0'", null);
		return c.getCount() + "";
	}
	
	public String getTodalDisconnection() {
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + " in('0','1')", null);
		return c.getCount() + "";
	}
	
	public String getTotalNotSynced() {
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+COL_NETWORK_MODE+"='OFFLINE'", null);
		return c.getCount() + "";
	}
	public String getTotalSynced() {
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + "='1'  and "+COL_NETWORK_MODE+"='ONLINE'", null);
		return c.getCount() + "";
	}
	public String getTodalReConnected() {
//		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + "='2' and trim("+COL_redate+")!='null'", null);
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + "='3'", null);

		return c.getCount() + "";
	}
	
	public String getTodalToReconnect() {
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + "='2'  ", null);
		return c.getCount() + "";
	}
	
	
	public String getTodalReconnection() {
//		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + "='2' and trim("+COL_redate+")='null'", null);
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + " in('2','3')", null);
		return c.getCount() + "";
	}
	public String getTotalNotSyncedReConn() {
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + "='3'  and "+COL_NETWORK_MODE+"='OFFLINE'", null);
		return c.getCount() + "";
	}
	public String getTotalSyncedReConn() {
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + "='3'  and "+COL_NETWORK_MODE+"='ONLINE'", null);
		return c.getCount() + "";
	}
	
	
	public String getTotalSyncedMain() {
		Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + " in('1','3')  OR "+ COL_remarks +  "  like '%Will Pay%' OR "+ COL_remarks +  "  like '%Others%'  OR  "+ COL_remarks +  "  like '%Payment%'   and  "+COL_NETWORK_MODE+" not like 'OFFLINE'", null);
		return c.getCount() + "";
	}
	
	public GetterSetterDisconnection getDisconnOneByOne(String localId) {
		SimpleDateFormat formatOld =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
		SimpleDateFormat formatnew =new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
		GetterSetterDisconnection disc =new GetterSetterDisconnection();
		try {
			Cursor	 c=myDb.rawQuery("Select ROWID as demo_no,  "+COL_arrears+", "+COL_sdocode+", "+COL_listno+", "+COL_tariff+", "+COL_id+", "+COL_rrno+", "+COL_consumer_name+", "+COL_latitude_dis+", "+COL_longitude_dis+", "+COL_address+", "+COL_payAmount+", "+COL_payRecDate+"  from "+TABLE_NAME+" where "+COL_status +"='0' order by "+COL_rrno, null);
			if(c.move(Integer.parseInt(localId))){
				disc.setId(c.getString(c.getColumnIndex(COL_id)));
				disc.setArrears(c.getString( c.getColumnIndex(COL_arrears)));
				disc.setRrno(c.getString(c.getColumnIndex(COL_rrno)));
				disc.setTariff(c.getString(c.getColumnIndex(COL_tariff)));
				disc.setListno(c.getString(c.getColumnIndex(COL_listno)));
				disc.setSdocode(c.getString(c.getColumnIndex(COL_sdocode)));
				disc.setAddress(c.getString(c.getColumnIndex(COL_address)));
				disc.setConsumer_name(c.getString(c.getColumnIndex(COL_consumer_name)));
				disc.setLatitude_dis(c.getString(c.getColumnIndex(COL_latitude_dis)));
				disc.setLongitude_dis(c.getString(c.getColumnIndex(COL_longitude_dis)));
				disc.setLastPaidAmount(c.getString(c.getColumnIndex(COL_payAmount)));
			
				String date =c.getString(c.getColumnIndex(COL_payRecDate));
			    try {
					date=formatnew.format(formatOld.parse(date));
				} catch (Exception e) {
					e.printStackTrace();
				}
			    disc.setLastPaidDate(date);
			
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return disc;
	}


	
	public GetterSetterDisconnection getReconnOneByOne(String localId) {
		GetterSetterDisconnection disc =new GetterSetterDisconnection();
		Cursor	 c=myDb.rawQuery("select ROWID as demo_no, "+COL_tariff+", "+COL_sdocode+", "+COL_listno+", "+COL_id+", "+COL_rrno+", "+COL_consumer_name+", "+COL_address+"  from "+TABLE_NAME+" where "+COL_status +"='2'  order by "+COL_rrno, null);
		
		if(c.move(Integer.parseInt(localId))){
			disc.setId(c.getString(c.getColumnIndex(COL_id)));
			disc.setRrno(c.getString(c.getColumnIndex(COL_rrno)));
			disc.setTariff(c.getString(c.getColumnIndex(COL_tariff)));
			disc.setListno(c.getString(c.getColumnIndex(COL_listno)));
			disc.setSdocode(c.getString(c.getColumnIndex(COL_sdocode)));
			disc.setAddress(c.getString(c.getColumnIndex(COL_address)));
			disc.setConsumer_name(c.getString(c.getColumnIndex(COL_consumer_name)));
		}
		return disc;
	}
	
	public boolean checkReconnByAccount(String acNumber) {
		Cursor	 c=myDb.rawQuery("select "+COL_ID_LOCAL+"  from "+TABLE_NAME+" where "+COL_status +"='2' and "+COL_rrno +"='"+acNumber+"' order by "+COL_rrno, null);
	if(c.getCount()<1){
		return true;
	}
		return false;
	}
	
	
	public GetterSetterDisconnection getReconnByAccount(String acNumber) {
		GetterSetterDisconnection disc =new GetterSetterDisconnection();
		Cursor	 c=myDb.rawQuery("select ROWID as demo_no, "+COL_tariff+", "+COL_sdocode+", "+COL_listno+", "+COL_id+", "+COL_rrno+", "+COL_consumer_name+", "+COL_address+"  from "+TABLE_NAME+" where "+COL_status +"='2' and "+COL_rrno +"='"+acNumber+"'  order by "+COL_rrno, null);
		
	if(c.getCount()<1){
		return null;
	}
		
		if(c.moveToFirst()){
			disc.setId(c.getString(c.getColumnIndex(COL_id)));
			disc.setRrno(c.getString(c.getColumnIndex(COL_rrno)));
			disc.setTariff(c.getString(c.getColumnIndex(COL_tariff)));
			disc.setListno(c.getString(c.getColumnIndex(COL_listno)));
			disc.setSdocode(c.getString(c.getColumnIndex(COL_sdocode)));
			disc.setAddress(c.getString(c.getColumnIndex(COL_address)));
			
			disc.setConsumer_name(c.getString(c.getColumnIndex(COL_consumer_name)));
		}
		return disc;
	}
	
	public boolean checkDisconnByAccount(String acNumber) {
		Cursor	 c=myDb.rawQuery("select "+COL_ID_LOCAL+"  from "+TABLE_NAME+" where "+COL_status +" in ('0','1')  and "+COL_rrno +"='"+acNumber+"'", null);
	if(c.getCount()<1){
		return true;
	}
		return false;
	}
	
	
	public GetterSetterDisconnection getDisconnByAccount(String acNumber) {
		GetterSetterDisconnection disc =new GetterSetterDisconnection();
		Cursor	 c=myDb.rawQuery("select ROWID as demo_no, "+COL_tariff+", "+COL_sdocode+", "+COL_listno+", "+COL_id+", "+COL_rrno+", "+COL_consumer_name+", "+COL_address+", "+COL_arrears+", "+COL_latitude_dis+", "+COL_longitude_dis+"  from "+TABLE_NAME+" where "+COL_status +"='0' and "+COL_rrno +"='"+acNumber+"'  order by "+COL_rrno, null);
		
	if(c.getCount()<1){
		return null;
	}
		
		if(c.moveToFirst()){
			disc.setArrears(c.getString(c.getColumnIndex(COL_arrears)));
			disc.setId(c.getString(c.getColumnIndex(COL_id)));
			disc.setRrno(c.getString(c.getColumnIndex(COL_rrno)));
			disc.setTariff(c.getString(c.getColumnIndex(COL_tariff)));
			disc.setListno(c.getString(c.getColumnIndex(COL_listno)));
			disc.setSdocode(c.getString(c.getColumnIndex(COL_sdocode)));
			disc.setAddress(c.getString(c.getColumnIndex(COL_address)));
			
			disc.setLatitude_dis(c.getString(c.getColumnIndex(COL_latitude_dis)));
			disc.setLongitude_dis(c.getString(c.getColumnIndex(COL_longitude_dis)));
			
			disc.setConsumer_name(c.getString(c.getColumnIndex(COL_consumer_name)));
		}
		return disc;
	}
	
	public boolean checkListExists(String listNumber,String sdoCode) {
		
		Cursor c=myDb.query(TABLE_NAME, null, COL_sdocode+"='"+sdoCode+"' and "+COL_listno+"='"+listNumber+"'", null, null, null, null);
		if(c.moveToNext()){
			return true;
		}
		return false;
	}


	public void deleteDisconnectionTable() {
		myDb.delete(TABLE_NAME,null, null);
	}

	public boolean deleteAllNames() {
		int doneDelete = myDb.delete(DATABASE_NAME, null, null);
		return doneDelete > 0;
	}

	public boolean updateDisconnection(String mrReading, String remarks,String serverId, String disDate, String image_disc, String netMode, boolean isDisconnected) {
		ContentValues cv = new ContentValues();
		cv.put(COL_NETWORK_MODE, netMode);
		cv.put(COL_disfr, mrReading);
		cv.put(COL_remarks, remarks);
		cv.put(COL_disdate, disDate);
		cv.put(COL_image_dis, image_disc);
		if(isDisconnected) { // IF ONLY DISCONNECTED change to 1
			cv.put(COL_status, "1");//Status changed to disconnected(0 to 1)
		}
		
		if(myDb.update(TABLE_NAME, cv, COL_id + "='" + serverId + "'", null)>0) {
			return true;
		}
		return false;
	}
	
	public boolean updateReConnection( String remarks,String serverId, String reDate,  String image_rec, String netMode) {
		ContentValues cv = new ContentValues();
		cv.put(COL_NETWORK_MODE, netMode);
		cv.put(COL_remarks, remarks);
		cv.put(COL_redate, reDate);
		cv.put(COL_image_recon, image_rec);
		cv.put(COL_status, "3");//Status changed to disconnected(0 to 1)
		if(myDb.update(TABLE_NAME, cv, COL_id + "='" + serverId + "'", null)>0)
		{
			return true;
		}
		return false;
	}
	
	public void updateOfflineToOnline(String serverId) {
		open();
		ContentValues cv = new ContentValues();
		cv.put(COL_NETWORK_MODE, "ONLINE");
		System.out.println("Server Id="+serverId+" updated to Loacl db as ONLINE ======");
		myDb.update(TABLE_NAME, cv, COL_id + "='" + serverId + "'", null);
		close();
	}
	
	public JSONArray uploadData(){ //TODO
		open();
		String[] columns = new String[] {COL_sdocode,COL_id, COL_remarks, COL_disdate,COL_disfr,COL_image_dis,COL_image_recon,COL_status,COL_redate};
		Cursor cursor = myDb.query(TABLE_NAME, columns, COL_NETWORK_MODE+"='OFFLINE'", null, null, null, null);
		JSONArray json = new JSONArray();
		
		if (cursor == null) {
		} else {
			int k = 0;
			while (cursor != null && cursor.moveToPosition(k)) {
				k++;
				JSONObject jsonOb = new JSONObject();
				try {
					
					jsonOb.put("SDOCODE", cursor.getString(cursor	.getColumnIndex(COL_sdocode)));
					jsonOb.put("REMARKS", cursor.getString(cursor.getColumnIndex(COL_remarks)));
					jsonOb.put("SERVERID", cursor.getString(cursor.getColumnIndex(COL_id)));
					jsonOb.put("STATUS", cursor.getString(cursor.getColumnIndex(COL_status)));
					jsonOb.put("DISFR", cursor.getString(cursor	.getColumnIndex(COL_disfr))); // METER READING WHILE DISCONNECTION
					
					String status=cursor.getString(cursor.getColumnIndex(COL_status)).trim();
					if(status.equals("1") || status.equals("0") ) { // DISCONNECTION
						jsonOb.put("IMAGE", cursor.getString(cursor.getColumnIndex(COL_image_dis)));
						jsonOb.put("ACTION_DATE", cursor.getString(cursor.getColumnIndex(COL_disdate)));
						jsonOb.put("TYPE",  "DISCONNECTION");
					}else {
						jsonOb.put("IMAGE", cursor.getString(cursor.getColumnIndex(COL_image_recon)));
						jsonOb.put("ACTION_DATE",  cursor.getString(cursor.getColumnIndex(COL_redate)));
						jsonOb.put("TYPE",  "RECONNECTION");
					}
					
					json.put(jsonOb);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		close();
		return json;
	}
	
	
	/*public Cursor uploadDataReConn(){
		String[] columns = new String[] {COL_sdocode,COL_id, COL_remarks, COL_redate,COL_image_recon};
		Cursor cursor = myDb.query(TABLE_NAME, columns, COL_NETWORK_MODE+"='OFFLINE' and "+COL_status+"='3'", null, null, null, null);
		return cursor;
	}	*/
	public boolean getAllOfflineData(){
		String[] columns = new String[] {COL_ID_LOCAL};
		Cursor cursor= myDb.query(TABLE_NAME, columns, COL_NETWORK_MODE+"='OFFLINE'", null, null, null, null);
		if(cursor.getCount()==0){
			return false;
		}
	return true;
	}
	
	public boolean getOfflineDataMain(){
		String[] columns = new String[] {COL_ID_LOCAL};
		Cursor cursor= myDb.query(TABLE_NAME, columns, COL_NETWORK_MODE+"='OFFLINE' ", null, null, null, null);
		if(cursor.getCount()==0){
			return false;
		}
	return true;
	}
	
	
	public void runSyncDisconnection() {
		open();
		if(getOfflineDataMain()){
			new SyncDisconnctionAsync().execute();
			
		}else{
			Log.e("Complaint Sync", "No data to sync");
		}
	
	}

/*	private class SyncDisconnctionAsync extends AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		JSONArray json = new JSONArray();
		protected Void doInBackground(Void... params) {
			json= uploadData();
			SendRequest req = new SendRequest();
			responsefromserver = req.sendRequest("UpdateDisReConnectionMobileUser", json);
			return null;
		}

		protected void onPostExecute(Void unused) {
			if (responsefromserver == null) {

				}
			else	if(responsefromserver.equals("[]")){
			}
			 else { 
				// Toast.makeText(context, "Disconnection [Uploading is started.]", Toast.LENGTH_SHORT).show();
					String status = null;
					String serverId = null;
					try {
						 JSONArray jsonarr = new JSONArray(responsefromserver);
					     
						    if(jsonarr != null)
						    {
						     for(int i=0;i<jsonarr.length();i++)
						     {
						      JSONObject obje = jsonarr.getJSONObject(i);
						      status=obje.getString("status");
						      serverId=obje.getString("serverid");
						      if (status != null && status.equals("updated")) {
									updateOfflineToOnline(serverId);
								} else {
								}
						     }
						    }
						   // Toast.makeText(context, "Disconnection [Uploading is Completed.]", Toast.LENGTH_SHORT).show();

					} catch (Exception e) {
						e.printStackTrace();
					}
			}

		}
	}*/

	
	private class SyncDisconnctionAsync extends AsyncTask<Void, Void, Void> {
		String responsefromserver = null;
		JSONArray json = new JSONArray();

		protected Void doInBackground(Void... params) {
			try {
				json = uploadData();
				SendRequest req = new SendRequest();
				String url1 = "UpdateDisReConnectionMobileUser";
				responsefromserver = req.sendRequest(url1, json);
				
				System.out.println("DISCONNECTION RESPONSE EEEEEEEEEEEEEEEEEEEEEE  "+responsefromserver);
			} catch (Exception e) {
				e.printStackTrace();
			}
 
			return null;
		}

		protected void onPostExecute(Void unused) {
			if (responsefromserver == null) {} 
			else if (responsefromserver.equals("[]")) {}
			else {
				String status = null;
				String serverId = null;
				try {
					JSONArray jsonarr = new JSONArray(responsefromserver);

					if (jsonarr != null) {
						for (int i = 0; i < jsonarr.length(); i++) {
							JSONObject obje = jsonarr.getJSONObject(i);
							status = obje.getString("status");
							serverId = obje.getString("serverid");
							if (status != null && status.equals("updated")) {
								updateOfflineToOnline(serverId);
							} 
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	
	
	
public String getListNo() {
String sdocode = Login.sdoCode,listno = "";
		Cursor c=		myDb.rawQuery("select  "+COL_listno+","+COL_sdocode+" from "+TABLE_NAME+" limit 1", null);
if(c.moveToNext()){
	sdocode=c.getString(c.getColumnIndex(COL_sdocode));
	listno=c.getString(c.getColumnIndex(COL_listno));
}
		return sdocode+"@@@"+listno;
	}

public JSONObject getLatLong(String accountNo) {
	
	JSONObject obj = new JSONObject();
	try {
		Cursor	 c=myDb.rawQuery("select  "+COL_longitude_dis+", "+COL_latitude_dis+"  from "+TABLE_NAME+" where "+COL_rrno +"='"+accountNo+"'", null);
		if(c.getCount()<1){
			obj.put("lat", "0");
			obj.put("long", "0");
			return obj;
		}
			
			if(c.moveToFirst()){
				obj.put("lat", c.getString(c.getColumnIndex(COL_latitude_dis)));
				obj.put("long", c.getString(c.getColumnIndex(COL_longitude_dis)));
				
			}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return obj;
}


public JSONArray getLatLongArray(String type) {
	
	JSONArray arr = new JSONArray();
	try {
		
		Cursor	 c=myDb.rawQuery("select  "+COL_longitude_dis+", "+COL_latitude_dis+"  from "+TABLE_NAME+" where "+COL_latitude_dis +"!='0'", null);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			JSONObject obj = new JSONObject();
			obj.put("lat", c.getString(c.getColumnIndex(COL_latitude_dis)));
			obj.put("long", c.getString(c.getColumnIndex(COL_longitude_dis)));			
			arr.put(obj);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arr;
}

public ArrayList<String> getAllDates() {
	ArrayList<String> dates = new ArrayList<String>();
	try {
		
		open();
		String[] columns = {COL_disdate,COL_redate };
		Cursor c = myDb.query(TABLE_NAME, columns, COL_status + " in('1','3')  OR "+ COL_remarks +  "  like '%Will Pay%' OR "+ COL_remarks +  "  like '%Others%'  OR  "+ COL_remarks +  "  like '%Payment%'   and  "+COL_NETWORK_MODE+" not like 'OFFLINE'", null, null, null,null);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			if(!c.getString(c.getColumnIndex(COL_disdate)).trim().isEmpty())
			{
				String date=c.getString(c.getColumnIndex(COL_disdate));
				if(date.contains(" ")) {
					date=date.split(" ")[0];
				}
				dates.add(date);
			}
			 if(!c.getString(c.getColumnIndex(COL_redate)).trim().isEmpty())
			{
					String date=c.getString(c.getColumnIndex(COL_redate));
					if(date.contains(" ")) {
						date=date.split(" ")[0];
					}
					dates.add(date);
			}
			
		}

		close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	dates = new ArrayList<String>(new LinkedHashSet<String>(dates));
	return dates;
}

public ArrayList<String> getAllDataWithDate(/*String date*/) {
	ArrayList<String> dates = new ArrayList<String>();
	try {
		SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		SimpleDateFormat format2= new SimpleDateFormat("MMM-dd",Locale.getDefault());
		open();
		Cursor c = myDb.query(TABLE_NAME, null, COL_status + " in('1','3')  OR "+ COL_remarks +  "  like '%Will Pay%' OR "+ COL_remarks +  "  like '%Others%'  OR  "+ COL_remarks +  "  like '%Payment%'  ", null, null, null,null);
	
		int count =0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

			String date= null;
			String status=c.getString(c.getColumnIndex(COL_status)).trim();
			if(status.equals("1")) {
				 status="Disconnected";
				 date= c.getString(c.getColumnIndex(COL_disdate)).split(" ")[0].trim();
				 date=format2.format(format1.parse(date));
			}
			else if(status.equals("3")) {
				 date= c.getString(c.getColumnIndex(COL_redate)).split(" ")[0].trim();
				 date=format2.format(format1.parse(date));
				 status="Reconnected";
			}else {
				System.out.println("ELSE PART OF REPORTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
				status=c.getString(c.getColumnIndex(COL_remarks)).trim();
				date= c.getString(c.getColumnIndex(COL_disdate)).split(" ")[0].trim();
				date=format2.format(format1.parse(date));
				if(status.contains("-")) {
					status=status.split("-")[0].trim();
				}
			}
			
			String conusmerName=c.getString(c.getColumnIndex(COL_consumer_name));
			
			if(conusmerName.length()>8) {
				conusmerName=conusmerName.substring(0, 7)+"..";
			}
			
			String consumerNo=c.getString(c.getColumnIndex(COL_rrno));
			dates.add((++count)+"  "+date+"  "+consumerNo+"  "+conusmerName+"  "+status);
			
		}

		close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	dates = new ArrayList<String>(new LinkedHashSet<String>(dates));
	return dates;
}



public ArrayList<String> getAllDataForPrint() {
	ArrayList<String> main = new ArrayList<String>();
	try {
		
		open();
		Cursor c1 = myDb.query(TABLE_NAME, null, COL_status + " in('1')", null, null, null,null);
		
		ArrayList<String> disconnected = new ArrayList<String>();
		
		if(c1.getCount()>0){
			disconnected.add(" ");
			disconnected.add("Disconnected List");
			disconnected.add("-----------------");
		}
		int count =1;
		for (c1.moveToFirst(); !c1.isAfterLast(); c1.moveToNext()) {
			String conusmerName=c1.getString(c1.getColumnIndex(COL_consumer_name));
			
			if(conusmerName.length()>10) {
				conusmerName=conusmerName.substring(0, 10)+".";
			}
			
			String consumerNo=c1.getString(c1.getColumnIndex(COL_rrno));
			String fr=c1.getString(c1.getColumnIndex(COL_disfr));
			String date= c1.getString(c1.getColumnIndex(COL_disdate)).split(" ")[0].trim();
			
			SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
			SimpleDateFormat format2= new SimpleDateFormat("MMM-dd",Locale.getDefault());
			date=format2.format(format1.parse(date));
			
				disconnected.add((count++) +" "+date+"  "+consumerNo+"  "+conusmerName+"  "+fr);
		   }
		c1.close();
	Cursor c2 = myDb.query(TABLE_NAME, null, COL_status + " in('0') and "+ COL_remarks +  "  like '%Will Pay%' OR "+ COL_remarks +  "  like '%Others%'  OR  "+ COL_remarks +  "  like '%Payment%' ", null, null, null,null);
		
		ArrayList<String> notDisconnected = new ArrayList<String>();
		
		if(c2.getCount()>0){
			notDisconnected.add(" ");
			notDisconnected.add("Not Disconnected List");
			notDisconnected.add("---------------------");
		}
		count =1;
		for (c2.moveToFirst(); !c2.isAfterLast(); c2.moveToNext()) {
			String conusmerName=c2.getString(c2.getColumnIndex(COL_consumer_name));
			
			if(conusmerName.length()>10) {
				conusmerName=conusmerName.substring(0, 10)+".";
			}
			
			String consumerNo=c2.getString(c2.getColumnIndex(COL_rrno));
			String remark=c2.getString(c2.getColumnIndex(COL_remarks));
			if(remark.contains("-")) {
				remark =remark.split("-")[0].trim();
			}
			String date= c2.getString(c2.getColumnIndex(COL_disdate)).split(" ")[0].trim();
			SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
			SimpleDateFormat format2= new SimpleDateFormat("MMM-dd",Locale.getDefault());
			date=format2.format(format1.parse(date));
			
			notDisconnected.add((count++) +" "+date+"  "+consumerNo+"  "+conusmerName+"  "+remark);
		   }
		
		c2.close();
		Cursor c3 = myDb.query(TABLE_NAME, null, COL_status + " in('3')", null, null, null,null);
		
		ArrayList<String> reConnected = new ArrayList<String>();
		
		if(c3.getCount()>0){
			reConnected.add(" ");
			reConnected.add("Reconnected List");
			reConnected.add("----------------");
		}
		count =1;
		for (c3.moveToFirst(); !c3.isAfterLast(); c3.moveToNext()) {
			String conusmerName=c3.getString(c3.getColumnIndex(COL_consumer_name));
			
			if(conusmerName.length()>10) {
				conusmerName=conusmerName.substring(0, 10)+".";
			}
			
			String consumerNo=c3.getString(c3.getColumnIndex(COL_rrno));
			String date= c3.getString(c3.getColumnIndex(COL_redate)).split(" ")[0].trim();
			
			SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
			SimpleDateFormat format2= new SimpleDateFormat("MMM-dd",Locale.getDefault());
			date=format2.format(format1.parse(date));
			
			reConnected.add((count++) +" "+date+"  "+consumerNo+"  "+conusmerName);
		   }
		c3.close();
		close();
		
		main.addAll(disconnected);
		main.addAll(notDisconnected);
		main.addAll(reConnected);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return main;
}

public String getFailedDisconnection() {
	Cursor c = myDb.rawQuery("Select "+ COL_ID_LOCAL + " from " + TABLE_NAME + " where "+ COL_status + " ='0'  and "+ COL_remarks +  "  like '%Will Pay%' OR "+ COL_remarks +  "  like '%Others%' OR "+ COL_remarks +  "  like '%Payment%'    and  "+COL_NETWORK_MODE+" is not null", null);
	return c.getCount() + "";
}



}

