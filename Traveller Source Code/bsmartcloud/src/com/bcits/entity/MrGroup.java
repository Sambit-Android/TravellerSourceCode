package com.bcits.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="MRGROUP",schema="VCLOUDENGINE")
@NamedQueries({
	@NamedQuery(name="MrGroup.MaxId",query="SELECT MAX(m.id) FROM MrGroup m"),
	@NamedQuery(name="MrGroup.DeleteData",query="DELETE FROM MrGroup g WHERE g.mrcode=:mrcode"),
	@NamedQuery(name="MrGroup.updateGroups",query="UPDATE MrGroup g SET g.grpName=:newGrpName WHERE g.grpName=:grpName")	
})
public class MrGroup implements Serializable{
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;	
	
	@Column(name="MRCODE" )
	private String mrcode;	
		
	/*@Column(name="SDOCODE")
	private int sdocode;*/
	
	@Column(name="GROUPNAME")
	private String grpName;
	
	/*@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name = "MRCODE",referencedColumnName="MRCODE", nullable = false,insertable=false,updatable=false),	
		@JoinColumn(name = "SDOCODE",referencedColumnName="SDOCODE", nullable = false,insertable=false,updatable=false)	
	})
	private MRMasterEntity groups;*/

	

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	
	/*public MRMasterEntity getGroups() {
		return groups;
	}

	public void setGroups(MRMasterEntity groups) {
		this.groups = groups;
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMrcode() {
		return mrcode;
	}

	public void setMrcode(String mrcode) {
		this.mrcode = mrcode;
	}

	/*public int getSdocode() {
		return sdocode;
	}

	public void setSdocode(int sdocode) {
		this.sdocode = sdocode;
	}*/

	
	
}

