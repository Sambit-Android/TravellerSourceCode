package com.pgrs.entity;
// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name = "CORE_PROJECT",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries({
	@NamedQuery(name="Project.getProjectNameBasedOnId",query="SELECT p.name FROM Project p WHERE p.id = :projectId"),
	@NamedQuery(name="Project.getProjectName",query="SELECT p FROM Project p "),
	@NamedQuery(name="Project.projectStatusUpdate",query="UPDATE Project p SET p.status=:status WHERE p.id=:projectId"),
	@NamedQuery(name="Project.deleteProjectHeirarchyBasedOnProjectId",query="DELETE ProjectHeirarchyEntity ph WHERE ph.project.id=:projectId"),
	@NamedQuery(name="Project.getActiveUsersBasedOnProjectId",query="SELECT ur.urId FROM Users ur WHERE ur.projectId=:projectId"),
})
public class Project extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, precision = 6, scale = 0)
	private int id;

	@Column(name = "NAME", length = 45)
	private String name;
	
	@Column(name = "SALES_LEDGER", length = 45)
	private String salesLedger;
	
	@Column(name = "COMPANY_ID")
	private int companyId;

	@Column(name="ENCLOSURES_DETAILS")
	private String enclosuresDetails;
	
	@Column(name="METRIC")
	private String metric;
	
	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch= FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID",insertable =false,updatable=false)
	private Company company;

	@OneToMany(mappedBy="project",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<ProjectTreeTemplateLevels> projectTreeTemplateLevels = new HashSet<ProjectTreeTemplateLevels>();
	
	public Integer getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSalesLedger() {
		return salesLedger;
	}

	public void setSalesLedger(String salesLedger) {
		this.salesLedger = salesLedger;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	
	
	public String getEnclosuresDetails() {
		return enclosuresDetails;
	}

	public void setEnclosuresDetails(String enclosuresDetails) {
		this.enclosuresDetails = enclosuresDetails;
	}

	@JsonIgnore
	public Set<ProjectTreeTemplateLevels> getProjectTreeTemplateLevels() {
		return projectTreeTemplateLevels;
	}

	public void setProjectTreeTemplateLevels(
			Set<ProjectTreeTemplateLevels> projectTreeTemplateLevels) {
		this.projectTreeTemplateLevels = projectTreeTemplateLevels;
	}


	

	
	

}