package com.pgrs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name = "LOCATION",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries({
	@NamedQuery(name = "LocationsEntity.getAllSections",query="SELECT DISTINCT(l.newSitecode),l.section FROM LocationsEntity l ORDER BY l.section ASC"),
})
public class LocationsEntity extends BaseEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COMPANY", unique = true, nullable = false, precision = 10, scale = 0)
	private String company;	
	
	@Column(name = "ZONE")
	private String zone;	
	
	@Column(name = "CIRCLE")
	private String circle;
	
	@Column(name = "DIVISION")
	private String division;	
	
	@Column(name = "SUBDIVISION")
	private String subdivision;
	
	@Column(name = "SECTION")
	private String section;	
	
	@Column(name = "SITECODE")
	private String sitecode;
	
	@Column(name = "DBUSER")
	private String dbuser;	
	
	@Column(name = "EMAILIDS")
	private String emailids;
	
	@Column(name = "PHONENOS")
	private String phonenos;	
	
	@Column(name = "NEWSITECODE")
	private String newSitecode;

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

	public String getSitecode() {
		return sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getEmailids() {
		return emailids;
	}

	public void setEmailids(String emailids) {
		this.emailids = emailids;
	}

	public String getPhonenos() {
		return phonenos;
	}

	public void setPhonenos(String phonenos) {
		this.phonenos = phonenos;
	}

	public String getNewSitecode() {
		return newSitecode;
	}

	public void setNewSitecode(String newSitecode) {
		this.newSitecode = newSitecode;
	}
}