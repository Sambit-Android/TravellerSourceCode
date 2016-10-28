package com.pgrs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.pgrs.util.PgrsSchemaName;

@SuppressWarnings("serial")
@Entity
@Table(name = "CORE_DEPARTMENT",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries
({
	@NamedQuery(name="Department.getDepartmentNameFilter",query="SELECT d.deptId,d.deptName FROM Department d WHERE d.departmentStatus='Active' ORDER BY d.deptId DESC"),
	@NamedQuery(name="Department.findAll",query="SELECT d.deptId,d.deptName,d.deptDesc,d.departmentStatus FROM Department d ORDER BY d.deptId DESC"),
	@NamedQuery(name="Department.UpdateStatus",query="UPDATE Department d SET  d.departmentStatus = :status WHERE d.deptId = :deptId"),
	@NamedQuery(name="Department.getDeptNameBasedOnId",query="SELECT d.deptName FROM Department d WHERE d.deptId =:deptId"),
	@NamedQuery(name="Department.ActiveUsers",query="SELECT ur.urId FROM Users ur WHERE ur.deptId=:deptId AND ur.status=1"),
})

public class Department extends BaseEntity implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private int deptId;
	
	@Column(name = "DEPT_NAME", length = 45)
	private String deptName;
	
	@Column(name = "DEPT_DESC", length = 100)	
	private String deptDesc;	
	
	@Column(name = "DEPARTMENT_STATUS", length = 500)
	private String departmentStatus;
	
	public Department(){
		
	}
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return this.deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}	
	public String getDeptName() {
		return this.deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDepartmentStatus() {
		return departmentStatus;
	}
	public void setDepartmentStatus(String departmentStatus) {
		this.departmentStatus = departmentStatus;
	}		
}