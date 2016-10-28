package com.bcits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="LOCATION",schema="BSMART")
@NamedQueries({
	           @NamedQuery(name="LocationOracleEntity.getAllLocations",query="SELECT l FROM LocationOracleEntity l "),
	           @NamedQuery(name="LocationOracleEntity.getLocationBySdocode",query="SELECT l FROM LocationOracleEntity l where l.sitecode=:sitecode")

})

public class LocationOracleEntity {
	
	@Id
	@Column(name="SITECODE")
	private int sitecode;
	
	@Column(name="COMPANY")
	private String company;
	
	@Column(name="ZONE")
	private String zone;
	
	@Column(name="CIRCLE")
	private String circle;
	
	@Column(name="DIVISION")
	private String division;
	
	@Column(name="SUBDIVISION")
	private String subdivision;
	
	@Column(name="SECTION")
	private String section;
	
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

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getSitecode() {
		return sitecode;
	}

	public void setSitecode(int sitecode) {
		this.sitecode = sitecode;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public int getNewsitecode() {
		return newsitecode;
	}

	public void setNewsitecode(int newsitecode) {
		this.newsitecode = newsitecode;
	}

	@Column(name="DBUSER")
	private String dbuser;
	
	@Column(name="NEWSITECODE")
	private int newsitecode;
	
}

