package com.bcits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="LOCATIONS", schema="vcloudengine")
public class CircleDetails
{
  
  
  @Column(name="company")
  private String company;
  
  @Column(name="zone ")
  private String zone;
  
  @Column(name="locationtype ")
  private String locationtype;
  
  @Column(name="locationname ")
  private String locationname;
  @Id
  @Column(name="sitecode ")
  private String sitecode;
  
  @Column(name="circle ")
  private String circle;

public String getCompany() {
	return company;
}

public void setCompany(String company) {
	this.company = company;
}

public String getZone() {
	return zone;
}

public void setZone(String zone) {
	this.zone = zone;
}

public String getLocationtype() {
	return locationtype;
}

public void setLocationtype(String locationtype) {
	this.locationtype = locationtype;
}

public String getLocationname() {
	return locationname;
}

public void setLocationname(String locationname) {
	this.locationname = locationname;
}

public String getSitecode() {
	return sitecode;
}

public void setSitecode(String sitecode) {
	this.sitecode = sitecode;
}

public String getCircle() {
	return circle;
}

public void setCircle(String circle) {
	this.circle = circle;
}
  
  

}
