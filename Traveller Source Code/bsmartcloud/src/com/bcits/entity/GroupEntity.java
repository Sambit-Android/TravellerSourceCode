package com.bcits.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="GROUP",schema="VCLOUDENGINE")
@NamedQueries({
	@NamedQuery(name="GroupEntity.Findll",query=" SELECT g FROM GroupEntity g"),
	@NamedQuery(name="GroupEntity.CheckGrpExist",query=" SELECT COUNT(*) FROM GroupEntity g WHERE g.id<>:id AND g.grpName LIKE :name"),
	
})
public class GroupEntity  implements Serializable
{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME")
	private String grpName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	
	
}
