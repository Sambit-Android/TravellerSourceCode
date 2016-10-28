package com.pgrs.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name = "CORE_ROLE",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries
({
	@NamedQuery(name="Role.findAllRoles",query="SELECT r FROM Role r ORDER BY r.id DESC"),
	@NamedQuery(name="Role.readRoleNames",query="SELECT r.rlName FROM Role r WHERE r.dnId=:dnId"),
	@NamedQuery(name = "Role.findAll", query = "SELECT r.id,r.rlName,r.rlDescription,r.roleStatus,r.dnId,dn.dnName FROM Role r INNER JOIN r.designationEntity dn ORDER BY r.id DESC"),
	@NamedQuery(name = "Role.UpdateStatus",query="UPDATE Role r SET  r.roleStatus = :rlStatus WHERE r.id = :rlId"),
	@NamedQuery(name="Role.executeGetRolesBasedOnOfficeId",query="SELECT r FROM Role r WHERE r.officeId = :officeId"),
	/*@NamedQuery(name="Role.getRoleNameBasedOnId",query="SELECT r.rlName FROM Role r ")*/
	@NamedQuery(name="Role.getRoleNameBasedOnId",query="SELECT r.rlName FROM Role r WHERE r.id = :roleId"),
	@NamedQuery(name="Role.getRoleIdBasedOnName",query="SELECT r.id FROM Role r WHERE r.rlName = :roleName"),
	@NamedQuery(name="Role.getActiveUserBasedOnUserId",query="SELECT ur.urId FROM Users ur WHERE ur.urId = :urId AND ur.status=1"),
})
public class Role extends BaseEntity implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RL_ID", unique = true, nullable = false, precision = 10, scale = 0)
	private int id;
	
	@Column(name = "RL_DESCRIPTION")
	private String rlDescription;
	
	@Column(name = "RL_NAME")
	private String rlName;
	
	@Column(name = "OFFICE_ID")
	private int officeId;
	
	@Column(name = "ROLE_STATUS", length = 500)
	private String roleStatus;
	
	@Column(name = "DN_ID")
	private int dnId;
	
	@OneToOne
	@JoinColumn(name="DN_ID",insertable=false,updatable=false)
	private Designation designationEntity;

	public Role(){}	
		
	public Role(int id,String rlDescription, String rlName) 
	{
		this.id = id;
		this.rlDescription = rlDescription;
		this.rlName = rlName;
	}
	public Role(int rlId){
		this.id = rlId;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int rlId) {
		this.id = rlId;
	}
	public String getRlDescription() {
		return this.rlDescription;
	}
	public void setRlDescription(String rlDescription) {
		this.rlDescription = rlDescription;
	}	
	public String getRlName() {
		return this.rlName;
	}
	public void setRlName(String rlName) {
		this.rlName = rlName;
	}
	public String getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public int getOfficeId() {
		return officeId;
	}

	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}

	public int getDnId() {
		return dnId;
	}

	public void setDnId(int dnId) {
		this.dnId = dnId;
	}

	public Designation getDesignationEntity() {
		return designationEntity;
	}

	public void setDesignationEntity(Designation designationEntity) {
		this.designationEntity = designationEntity;
	}
	
}