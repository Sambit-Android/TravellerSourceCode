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

@SuppressWarnings("serial")
@Entity
@Table(name = "CORE_DESIGNATION",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries
({
	@NamedQuery(name = "Designation.findAllDesignation",query="SELECT d.dnId,d.dnName,d.dnDescription,d.designationStatus,dept.deptName,d.deptId from Designation d INNER JOIN d.departmentEntity dept ORDER BY d.dnId DESC"),
	@NamedQuery(name = "Designation.getDesignationNameBasedOnId",query="SELECT d.dnName FROM Designation d WHERE d.dnId =:dnId"),
	@NamedQuery(name = "Designation.findAllDnNames",query="SELECT d.dnId,d.dnName FROM Designation d WHERE d.designationStatus='Active' ORDER BY d.dnId DESC"),
	@NamedQuery(name = "Designation.UpdateStatus",query="UPDATE Designation d SET  d.designationStatus = :status WHERE d.dnId = :dnId"),
	@NamedQuery(name = "Designation.getAllActiveDesignations",query="SELECT dn.dnId,dn.dnName,dn.deptId from Designation dn WHERE dn.designationStatus = 'Active'"),
	@NamedQuery(name = "Designation.readDesignationNames",query="SELECT d.dnName FROM Designation d"),
	@NamedQuery(name = "Designation.getDnNameBasedOnId",query="SELECT dn.dnName FROM Designation dn WHERE dn.dnId = :dnId"),
	@NamedQuery(name = "Designation.getAllActiveDesignationsForRegistration",query="SELECT dn from Designation dn WHERE dn.designationStatus = 'Active'"),
	@NamedQuery(name = "Designation.getUniqueDesignationNames",query="SELECT dn.dnName from Designation dn WHERE dn.deptId = :deptId"),
	@NamedQuery(name = "Designation.ActiveUsers",query="SELECT ur.urId FROM Users ur WHERE ur.dnId=:dnId AND ur.status=1"),

})
public class Designation extends BaseEntity implements java.io.Serializable 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private int dnId;	
	
	@Column(name = "DN_DESCRIPTION")
	private String dnDescription;	
	
	@Column(name = "DESIGNATION_STATUS", length = 500)
	private String designationStatus;
	
	@Column(name = "DN_NAME")
	private String dnName;	
	
	@Column(name = "DEPT_ID")
	private int deptId;
	
	@OneToOne
	@JoinColumn(name="DEPT_ID",insertable=false,updatable=false)
	private Department departmentEntity;
			
	public int getDnId() {
		return dnId;
	}
	public void setDnId(int dnId) {
		this.dnId = dnId;
	}	
	public String getDnDescription() {
		return this.dnDescription;
	}
	public void setDnDescription(String dnDescription) {
		this.dnDescription = dnDescription;
	}
	public String getDnName() {
		return this.dnName;
	}
	public void setDnName(String dnName) {
		this.dnName = dnName;
	}
	
	public String getDesignationStatus() {
		return designationStatus;
	}
	public void setDesignationStatus(String designationStatus) {
		this.designationStatus = designationStatus;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public Department getDepartmentEntity() {
		return departmentEntity;
	}
	public void setDepartmentEntity(Department departmentEntity) {
		this.departmentEntity = departmentEntity;
	}	
}