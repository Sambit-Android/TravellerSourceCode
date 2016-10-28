package com.bcits.entity;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import oracle.sql.BLOB;
@Entity
@Table(name="disconnectionmaster",schema="MVS")
@NamedQueries({
	@NamedQuery(name="DisconnectionMasterEntity.findAllBySDOAndListNoMobile",query="SELECT d FROM DisconnectionMasterEntity d WHERE d.listno=:listno AND  d.sdocode=:sdoCode "),
    @NamedQuery(name ="DisconnectionMasterEntity.updateDisConnectionListMobile", query = "UPDATE DisconnectionMasterEntity d SET d.disdate =:disdate,d.remarks =:remarks,d.disfr =:disfr,d.status =:status,d.longitude_dis =:longitude,d.latitude_dis =:latitude,d.image_dis =:image  where d.id =:serverid "),
    @NamedQuery(name ="DisconnectionMasterEntity.updateReConnectionListMobile", query = "UPDATE DisconnectionMasterEntity d SET  d.redate =:redate,d.remarks =:remarks,d.longitude_recon =:longitude,d.latitude_recon =:latitude,d.image_recon =:image where d.id =:serverid ")
	
})
public class DisconnectionMasterEntity {

@Id	
@Column(name="id")
private int id;

@Column(name="rrno")
private String rrno;

@Column(name="listno")
private int listno;

@Column(name="listdate")
private Timestamp listdate;

@Column(name="tariff")
private String tariff;

@Column(name="disdate")
private Timestamp disdate;

@Column(name="disfr")
private int disfr;

@Column(name="sdocode")
private String sdocode;

@Column(name="remarks")
private String remarks;

@Column(name="dramt")
private int dramt;

@Column(name="rdngdate")
private Timestamp rdngdate;

@Column(name="mtrcode")
private String mtrcode;

@Column(name="arrears")
private double arrears;

@Column(name="username")
private String username;

@Column(name="datestamp")
private Timestamp datestamp;

@Column(name="reclistno")
private int reclistno;

@Column(name="redate")
private Timestamp redate;

@Column(name="ageing")
private int ageing;

@Column(name="status")
private int status;

@Column(name="collectionid")
private int collectionid;

@Column(name="oldrrno")
private String oldrrno;

@Column(name="consumer_name")
private String consumer_name;

@Column(name="address")
private String address;

@Column(name="fdrcode")
private String fdrcode;

@Column(name="tccode")
private String tccode;

@Column(name="poleno")
private String poleno;

@Column(name="longitude_dis")
private String longitude_dis;

@Column(name="latitude_dis")
private String latitude_dis;

@Column(name="longitude_recon")
private String longitude_recon;

@Column(name="latitude_recon")
private String latitude_recon;

@Column(name="image_dis")
private byte[] image_dis;

@Column(name="image_recon")
private byte[] image_recon;

public String getLongitude_dis() {
	return longitude_dis;
}

public void setLongitude_dis(String longitude_dis) {
	this.longitude_dis = longitude_dis;
}

public String getLatitude_dis() {
	return latitude_dis;
}

public void setLatitude_dis(String latitude_dis) {
	this.latitude_dis = latitude_dis;
}

public String getLongitude_recon() {
	return longitude_recon;
}

public void setLongitude_recon(String longitude_recon) {
	this.longitude_recon = longitude_recon;
}

public String getLatitude_recon() {
	return latitude_recon;
}

public void setLatitude_recon(String latitude_recon) {
	this.latitude_recon = latitude_recon;
}

public byte[] getImage_dis() {
	return image_dis;
}

public void setImage_dis(byte[] image_dis) {
	this.image_dis = image_dis;
}

public byte[] getImage_recon() {
	return image_recon;
}

public void setImage_recon(byte[] image_recon) {
	this.image_recon = image_recon;
}




public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getRrno() {
	return rrno;
}

public void setRrno(String rrno) {
	this.rrno = rrno;
}

public int getListno() {
	return listno;
}

public void setListno(int listno) {
	this.listno = listno;
}

public Timestamp getListdate() {
	return listdate;
}

public void setListdate(Timestamp listdate) {
	this.listdate = listdate;
}

public String getTariff() {
	return tariff;
}

public void setTariff(String tariff) {
	this.tariff = tariff;
}

public Timestamp getDisdate() {
	return disdate;
}

public void setDisdate(Timestamp disdate) {
	this.disdate = disdate;
}

public int getDisfr() {
	return disfr;
}

public void setDisfr(int disfr) {
	this.disfr = disfr;
}

public String getSdocode() {
	return sdocode;
}

public void setSdocode(String sdocode) {
	this.sdocode = sdocode;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

public int getDramt() {
	return dramt;
}

public void setDramt(int dramt) {
	this.dramt = dramt;
}

public Timestamp getRdngdate() {
	return rdngdate;
}

public void setRdngdate(Timestamp rdngdate) {
	this.rdngdate = rdngdate;
}

public String getMtrcode() {
	return mtrcode;
}

public void setMtrcode(String mtrcode) {
	this.mtrcode = mtrcode;
}

public double getArrears() {
	return arrears;
}

public void setArrears(double arrears) {
	this.arrears = arrears;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public Timestamp getDatestamp() {
	return datestamp;
}

public void setDatestamp(Timestamp datestamp) {
	this.datestamp = datestamp;
}

public int getReclistno() {
	return reclistno;
}

public void setReclistno(int reclistno) {
	this.reclistno = reclistno;
}

public Timestamp getRedate() {
	return redate;
}

public void setRedate(Timestamp redate) {
	this.redate = redate;
}

public int getAgeing() {
	return ageing;
}

public void setAgeing(int ageing) {
	this.ageing = ageing;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public int getCollectionid() 
{
	return collectionid;
}

public void setCollectionid(int collectionid) 
{
	this.collectionid = collectionid;
}

public String getOldrrno()
{
	return oldrrno;
}

public void setOldrrno(String oldrrno)
{
	this.oldrrno = oldrrno;
}

public String getConsumer_name() {
	return consumer_name;
}

public void setConsumer_name(String consumer_name) {
	this.consumer_name = consumer_name;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getFdrcode() {
	return fdrcode;
}

public void setFdrcode(String fdrcode) {
	this.fdrcode = fdrcode;
}

public String getTccode() {
	return tccode;
}

public void setTccode(String tccode) {
	this.tccode = tccode;
}

public String getPoleno() {
	return poleno;
}

public void setPoleno(String poleno) {
	this.poleno = poleno;
}

}

