package com.bcits.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="LOCATIONS" ,schema="VCLOUDENGINE")
@NamedQueries
({
	           @NamedQuery(name="LocationEntity.All",query="SELECT l FROM LocationEntity l WHERE l.level<>0 ORDER BY l.level,l.locationcode,l.sitecode"),
	           @NamedQuery(name="LocationEntity.findLocation",query="SELECT l FROM LocationEntity l WHERE UPPER(l.locationname)LIKE :locationname"),
			   @NamedQuery(name="LocationEntity.topMost",query="SELECT l FROM LocationEntity l WHERE l.level=0"),
			   //@NamedQuery(name="LocationEntity.getChildNodes",query="SELECT DISTINCT(l.locationcategory),l.locationcode FROM LocationEntity l WHERE l.locationcode LIKE :locationcode"),
			  // @NamedQuery(name="LocationEntity.locationNames",query="SELECT l FROM LocationEntity l WHERE l.level=:level AND l.locationcategory LIKE :category ORDER BY l.locationname"),
			   @NamedQuery(name="LocationEntity.findLocationName",query="SELECT l FROM LocationEntity l WHERE l.locationcode =:locationCode"),
			  // @NamedQuery(name="LocationEntity.getRegions",query="SELECT l.locationcode,l.locationname FROM LocationEntity l WHERE l.locationcategory LIKE :locationCategory AND l.level<>0 ORDER BY l.locationname"),
			   @NamedQuery(name="LocationEntity.findLocName",	    query="SELECT e FROM LocationEntity e WHERE UPPER(e.locationname)=:locationName"),
	           @NamedQuery(name="LocationEntity.findByLocationCode",query="SELECT l FROM LocationEntity l WHERE cast(l.locationcode as string)LIKE :locationcode"),
	           //@NamedQuery(name="LocationEntity.findOnlyStates",query="SELECT l FROM LocationEntity l WHERE trim(l.locationcategory)=trim(:locationCategory)AND LENGTH(l.locationcode)=:level ORDER BY l.locationcode"),
	           @NamedQuery(name="LocationEntity.findAllLocations",query="SELECT l FROM LocationEntity l WHERE trim(l.locationcode) LIKE trim(:locationCode) AND trim(l.locationcode) NOT LIKE trim(:locationcode)ORDER BY l.locationcode"),
	           @NamedQuery(name="LocationEntity.locationNames",query="SELECT l FROM LocationEntity l WHERE length(cast(l.locationcode as string))=:locCode AND cast(l.level as string)=:level AND cast(l.locationcode as string) like :digits"),
	           @NamedQuery(name="LocationEntity.findLevel4Location",	    query="SELECT l FROM LocationEntity l WHERE (UPPER(l.locationname)=:locationName AND l.level=:level4)"),
	           @NamedQuery(name="LocationEntity.findLevel3Location",	    query="SELECT l FROM LocationEntity l WHERE (UPPER(l.locationname)=:locationName AND l.level=:level3)"),
	           @NamedQuery(name="LocationEntity.findLevel2Location",	    query="SELECT l FROM LocationEntity l WHERE (UPPER(l.locationname)=:locationName AND l.level=:level2)"),
	           @NamedQuery(name="LocationEntity.findSiteCode",	    query="SELECT l FROM LocationEntity l WHERE l.sitecode=:siteCode AND l.level=:level4"),
	           @NamedQuery(name="LocationEntity.GetAllSdoLevel4",     query="SELECT l FROM LocationEntity l WHERE l.level=4)"),
	           @NamedQuery(name="LocationEntity.findLevelLocation",query="SELECT l FROM LocationEntity l WHERE cast(l.locationcode as string)LIKE :locationCode"),
	           @NamedQuery(name="LocationEntity.findSdoAndSubDiv",query="SELECT l FROM LocationEntity l"),
	           @NamedQuery(name="LocationEntity.findSubDiv",query="SELECT l.locationtype FROM LocationEntity l "),
	           @NamedQuery(name ="LocationEntity.findAllSDOCodes", query = "SELECT l.sitecode from LocationEntity l ORDER BY l.sitecode"),
	           
})
public class LocationEntity 
{
	
	@Column(name="LOCATIONCODE")
	private int locationcode;
	
	@Column(name="LOCATIONTYPE")
	private String locationtype;
	
	@Column(name="LOCATIONNAME")
	private String locationname;
	
	@Column(name="LOGGEDUSER")
	private String username;	
	
	@Column(name="LEVELS")
	private int level;	
	
	@Column(name="ZONE")
	private String zone;
	
	@Column(name="COMPANY")
	private String company;
	
	@Id
	@Column(name="SITECODE")
	private String sitecode;
	
	@Column(name="DATESTAMP")
	private Date datestamp;
	
	@Column(name="CIRCLE")
	private String circle;

	
	public int getLocationcode() {
		return locationcode;
	}


	public void setLocationcode(int locationcode) {
		this.locationcode = locationcode;
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public String getZone() {
		return zone;
	}


	public void setZone(String zone) {
		this.zone = zone;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getSitecode() {
		return sitecode;
	}


	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}


	public Date getDatestamp() {
		return datestamp;
	}


	public void setDatestamp(Date datestamp) {
		this.datestamp = datestamp;
	}


	public String getCircle() {
		return circle;
	}


	public void setCircle(String circle) {
		this.circle = circle;
	}


	public LocationEntity() 
	{
		
	}


	public LocationEntity(int locationcode, String locationtype,
			String locationname, String username, int level, String zone,
			String company, String sitecode, Date datestamp, String circle) {
		super();
		this.locationcode = locationcode;
		this.locationtype = locationtype;
		this.locationname = locationname;
		this.username = username;
		this.level = level;
		this.zone = zone;
		this.company = company;
		this.sitecode = sitecode;
		this.datestamp = datestamp;
		this.circle = circle;
	}


	

}
