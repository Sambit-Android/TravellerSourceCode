package com.bcits.disconreconn;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class MRUtilMaster extends Application {

	private static  String   mrrno	;                                              
	private static  String   mlistno	;                                              
	private static  String   mlistdate	;                                              
	private static  String   mtariff	;                                               
	private static  String   msocode	;                                               
	private static  String   mdramt	;                                               
	private static  String   mrdngdate	;                                              
	private static  String   mmtrcode	;                                              
	private static  String   marrears	;                                              
	private static  String   musername	;                                              
	private static  String   mdatestamp	;                                               
	private static  String   mageing	;                                               
	private static  String   mstatus	;                                               
	private static  String   mid	;                                               
	private static  String   mcollectionid	;                                              
	private static  String   moldrrno	;                                               
	private static  String   mconsumer_name	;                                               
	private static  String   maddress	;                                               
	private static  String   mfdrcode	;                                               
	private static  String   mtccode	;                                               
	private static  String   mpoleno	;                                               
	private static  String   mbalance	;     
	

	private static  String   maverage	;   




	private static  String   momdateofrelease ;                                               
	private static  String   momserialno ;                                              
	private static  String   mommake ;                                              
	private static  String   momcapacity ;                                              
	private static  String   momcover ;                                              
	private static  String   momposition ;                                               
	private static  String   momtype ;                                              
	private static  String   momfr ;                                              
	private static  String   momimage ;                                              
	private static  String   momlongitude ;                                               
	private static  String   momlattitude ;                                              


	private static  String   mnmdateofinstall ;                                              
	private static  String   mnmserialno ;                                              
	private static  String   mnmmake ;                                              
	private static  String   mnmcapacity ;                                             
	private static  String   mnmcover ;                                               
	private static  String   mnmposition ;                                               
	private static  String   mnmtype ;                                              
	private static  String   mnmir ;                                              
	private static  String   mnmimage ;                                              
	private static  String   mnmlongitude ;                                               
	private static  String   mnmlattitude ;              
	private static  String   mnmconstant ;


	private static  String   mmeterreplacedate ;                                              
	private static  String   mdeviceid ;                                              
	private static  String   msyncstatus ;                                           
	private static  String   mmeterreplacestatus ;                                 
	private static  String   mmr ;                                           
	private static  String   mservertomobiledate	;                                             
	private static  String   mdevicefirmwareversion	;  
	private static  String   mmeterflag	;  
	
	

	private static  String   mextra1	;                                             
	private static  String   mextra2	;                                       
	private static  String   mextra3	;                                          
	private static  String   mextra4	;                                           
	private static  String   mextra5	;                                     
	private static  String   mextra6	;   

	
	private static  String   mextra7	;     
	private static  String   mextra8	;                                           
	private static  String   mextra9	;                                     
	private static  String   mextra10	;   

	

	public static String getMmeterflag() {
		return mmeterflag;
	}
	public static void setMmeterflag(String mmeterflag) {
		MRUtilMaster.mmeterflag = mmeterflag;
	}
	
	public static String getMaverage() {
		return maverage;
	}
	public static void setMaverage(String maverage) {
		MRUtilMaster.maverage = maverage;
	}
	
	
	public static String getMnmconstant() {
		return mnmconstant;
	}
	public static void setMnmconstant(String mnmconstant) {
		MRUtilMaster.mnmconstant = mnmconstant;
	}

	public static String getMrrno() {
		return mrrno;
	}
	public static void setMrrno(String mrrno) {
		MRUtilMaster.mrrno = mrrno;
	}
	public static String getMlistno() {
		return mlistno;
	}
	public static void setMlistno(String mlistno) {
		MRUtilMaster.mlistno = mlistno;
	}
	public static String getMlistdate() {
		return mlistdate;
	}
	public static void setMlistdate(String mlistdate) {
		MRUtilMaster.mlistdate = mlistdate;
	}
	public static String getMtariff() {
		return mtariff;
	}
	public static void setMtariff(String mtariff) {
		MRUtilMaster.mtariff = mtariff;
	}
	public static String getMsocode() {
		return msocode;
	}
	public static void setMsocode(String msocode) {
		MRUtilMaster.msocode = msocode;
	}
	public static String getMdramt() {
		return mdramt;
	}
	public static void setMdramt(String mdramt) {
		MRUtilMaster.mdramt = mdramt;
	}
	public static String getMrdngdate() {
		return mrdngdate;
	}
	public static void setMrdngdate(String mrdngdate) {
		MRUtilMaster.mrdngdate = mrdngdate;
	}
	public static String getMmtrcode() {
		return mmtrcode;
	}
	public static void setMmtrcode(String mmtrcode) {
		MRUtilMaster.mmtrcode = mmtrcode;
	}
	public static String getMarrears() {
		return marrears;
	}
	public static void setMarrears(String marrears) {
		MRUtilMaster.marrears = marrears;
	}
	public static String getMusername() {
		return musername;
	}
	public static void setMusername(String musername) {
		MRUtilMaster.musername = musername;
	}
	public static String getMdatestamp() {
		return mdatestamp;
	}
	public static void setMdatestamp(String mdatestamp) {
		MRUtilMaster.mdatestamp = mdatestamp;
	}
	public static String getMageing() {
		return mageing;
	}
	public static void setMageing(String mageing) {
		MRUtilMaster.mageing = mageing;
	}
	public static String getMstatus() {
		return mstatus;
	}
	public static void setMstatus(String mstatus) {
		MRUtilMaster.mstatus = mstatus;
	}
	public static String getMid() {
		return mid;
	}
	public static void setMid(String mid) {
		MRUtilMaster.mid = mid;
	}
	public static String getMcollectionid() {
		return mcollectionid;
	}
	public static void setMcollectionid(String mcollectionid) {
		MRUtilMaster.mcollectionid = mcollectionid;
	}
	public static String getMoldrrno() {
		return moldrrno;
	}
	public static void setMoldrrno(String moldrrno) {
		MRUtilMaster.moldrrno = moldrrno;
	}
	public static String getMconsumer_name() {
		return mconsumer_name;
	}
	public static void setMconsumer_name(String mconsumer_name) {
		MRUtilMaster.mconsumer_name = mconsumer_name;
	}
	public static String getMaddress() {
		return maddress;
	}
	public static void setMaddress(String maddress) {
		MRUtilMaster.maddress = maddress;
	}
	public static String getMfdrcode() {
		return mfdrcode;
	}
	public static void setMfdrcode(String mfdrcode) {
		MRUtilMaster.mfdrcode = mfdrcode;
	}
	public static String getMtccode() {
		return mtccode;
	}
	public static void setMtccode(String mtccode) {
		MRUtilMaster.mtccode = mtccode;
	}
	public static String getMpoleno() {
		return mpoleno;
	}
	public static void setMpoleno(String mpoleno) {
		MRUtilMaster.mpoleno = mpoleno;
	}
	public static String getMbalance() {
		return mbalance;
	}
	public static void setMbalance(String mbalance) {
		MRUtilMaster.mbalance = mbalance;
	}
	public static String getMomdateofrelease() {
		return momdateofrelease;
	}
	public static void setMomdateofrelease(String momdateofrelease) {
		MRUtilMaster.momdateofrelease = momdateofrelease;
	}
	public static String getMomserialno() {
		return momserialno;
	}
	public static void setMomserialno(String momserialno) {
		MRUtilMaster.momserialno = momserialno;
	}
	public static String getMommake() {
		return mommake;
	}
	public static void setMommake(String mommake) {
		MRUtilMaster.mommake = mommake;
	}
	public static String getMomcapacity() {
		return momcapacity;
	}
	public static void setMomcapacity(String momcapacity) {
		MRUtilMaster.momcapacity = momcapacity;
	}
	public static String getMomcover() {
		return momcover;
	}
	public static void setMomcover(String momcover) {
		MRUtilMaster.momcover = momcover;
	}
	public static String getMomposition() {
		return momposition;
	}
	public static void setMomposition(String momposition) {
		MRUtilMaster.momposition = momposition;
	}
	public static String getMomtype() {
		return momtype;
	}
	public static void setMomtype(String momtype) {
		MRUtilMaster.momtype = momtype;
	}
	public static String getMomfr() {
		return momfr;
	}
	public static void setMomfr(String momfr) {
		MRUtilMaster.momfr = momfr;
	}
	public static String getMomimage() {
		return momimage;
	}
	public static void setMomimage(String momimage) {
		MRUtilMaster.momimage = momimage;
	}
	public static String getMomlongitude() {
		return momlongitude;
	}
	public static void setMomlongitude(String momlongitude) {
		MRUtilMaster.momlongitude = momlongitude;
	}
	public static String getMomlattitude() {
		return momlattitude;
	}
	public static void setMomlattitude(String momlattitude) {
		MRUtilMaster.momlattitude = momlattitude;
	}
	public static String getMnmdateofinstall() {
		return mnmdateofinstall;
	}
	public static void setMnmdateofinstall(String mnmdateofinstall) {
		MRUtilMaster.mnmdateofinstall = mnmdateofinstall;
	}
	public static String getMnmserialno() {
		return mnmserialno;
	}
	public static void setMnmserialno(String mnmserialno) {
		MRUtilMaster.mnmserialno = mnmserialno;
	}
	public static String getMnmmake() {
		return mnmmake;
	}
	public static void setMnmmake(String mnmmake) {
		MRUtilMaster.mnmmake = mnmmake;
	}
	public static String getMnmcapacity() {
		return mnmcapacity;
	}
	public static void setMnmcapacity(String mnmcapacity) {
		MRUtilMaster.mnmcapacity = mnmcapacity;
	}
	public static String getMnmcover() {
		return mnmcover;
	}
	public static void setMnmcover(String mnmcover) {
		MRUtilMaster.mnmcover = mnmcover;
	}
	public static String getMnmposition() {
		return mnmposition;
	}
	public static void setMnmposition(String mnmposition) {
		MRUtilMaster.mnmposition = mnmposition;
	}
	public static String getMnmtype() {
		return mnmtype;
	}
	public static void setMnmtype(String mnmtype) {
		MRUtilMaster.mnmtype = mnmtype;
	}
	public static String getMnmir() {
		return mnmir;
	}
	public static void setMnmir(String mnmir) {
		MRUtilMaster.mnmir = mnmir;
	}
	public static String getMnmimage() {
		return mnmimage;
	}
	public static void setMnmimage(String mnmimage) {
		MRUtilMaster.mnmimage = mnmimage;
	}
	public static String getMnmlongitude() {
		return mnmlongitude;
	}
	public static void setMnmlongitude(String mnmlongitude) {
		MRUtilMaster.mnmlongitude = mnmlongitude;
	}
	public static String getMnmlattitude() {
		return mnmlattitude;
	}
	public static void setMnmlattitude(String mnmlattitude) {
		MRUtilMaster.mnmlattitude = mnmlattitude;
	}
	public static String getMmeterreplacedate() {
		return mmeterreplacedate;
	}
	public static void setMmeterreplacedate(String mmeterreplacedate) {
		MRUtilMaster.mmeterreplacedate = mmeterreplacedate;
	}
	public static String getMdeviceid() {
		return mdeviceid;
	}
	public static void setMdeviceid(String mdeviceid) {
		MRUtilMaster.mdeviceid = mdeviceid;
	}
	public static String getMsyncstatus() {
		return msyncstatus;
	}
	public static void setMsyncstatus(String msyncstatus) {
		MRUtilMaster.msyncstatus = msyncstatus;
	}
	public static String getMmeterreplacestatus() {
		return mmeterreplacestatus;
	}
	public static void setMmeterreplacestatus(String mmeterreplacestatus) {
		MRUtilMaster.mmeterreplacestatus = mmeterreplacestatus;
	}
	public static String getMmr() {
		return mmr;
	}
	public static void setMmr(String mmr) {
		MRUtilMaster.mmr = mmr;
	}
	public static String getMservertomobiledate() {
		return mservertomobiledate;
	}
	public static void setMservertomobiledate(String mservertomobiledate) {
		MRUtilMaster.mservertomobiledate = mservertomobiledate;
	}
	public static String getMdevicefirmwareversion() {
		return mdevicefirmwareversion;
	}
	public static void setMdevicefirmwareversion(String mdevicefirmwareversion) {
		MRUtilMaster.mdevicefirmwareversion = mdevicefirmwareversion;
	}
	public static String getMextra1() {
		return mextra1;
	}
	public static void setMextra1(String mextra1) {
		MRUtilMaster.mextra1 = mextra1;
	}
	public static String getMextra2() {
		return mextra2;
	}
	public static void setMextra2(String mextra2) {
		MRUtilMaster.mextra2 = mextra2;
	}
	public static String getMextra3() {
		return mextra3;
	}
	public static void setMextra3(String mextra3) {
		MRUtilMaster.mextra3 = mextra3;
	}
	public static String getMextra4() {
		return mextra4;
	}
	public static void setMextra4(String mextra4) {
		MRUtilMaster.mextra4 = mextra4;
	}
	public static String getMextra5() {
		return mextra5;
	}
	public static void setMextra5(String mextra5) {
		MRUtilMaster.mextra5 = mextra5;
	}
	public static String getMextra6() {
		return mextra6;
	}
	public static void setMextra6(String mextra6) {
		MRUtilMaster.mextra6 = mextra6;
	}
	
	public static String getMextra7() {
		return mextra7;
	}
	public static void setMextra7(String mextra7) {
		MRUtilMaster.mextra7 = mextra7;
	}
	public static String getMextra8() {
		return mextra8;
	}
	public static void setMextra8(String mextra8) {
		MRUtilMaster.mextra8 = mextra8;
	}
	public static String getMextra9() {
		return mextra9;
	}
	public static void setMextra9(String mextra9) {
		MRUtilMaster.mextra9 = mextra9;
	}
	public static String getMextra10() {
		return mextra10;
	}
	public static void setMextra10(String mextra10) {
		MRUtilMaster.mextra10 = mextra10;
	}

	
	
	public static Toast showToast(Context context , int msg){

		return	Toast.makeText(context,msg,Toast.LENGTH_LONG);

	}
	public static Toast showToast(Context context , String msg){

		return	Toast.makeText(context,msg,Toast.LENGTH_LONG);

	}

	public static AlertDialog.Builder show(Context context , String title,int icon,String msg, String pvtstrg , OnClickListener pvt_listener , String ngtstrg , OnClickListener ngt_listener ) {
		return	new AlertDialog.Builder(context).setTitle(title).setIcon(icon).setMessage(msg).setPositiveButton(pvtstrg, pvt_listener).setNegativeButton(ngtstrg, ngt_listener);

	}

	public static AlertDialog.Builder show(Context context , String title,int icon,String msg, String pvtstrg , OnClickListener pvt_listener  ) {
		return	new AlertDialog.Builder(context).setTitle(title).setIcon(icon).setMessage(msg).setPositiveButton(pvtstrg, pvt_listener);

	}

}
