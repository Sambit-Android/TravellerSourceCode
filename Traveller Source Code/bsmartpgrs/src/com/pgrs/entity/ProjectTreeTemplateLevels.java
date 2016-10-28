package com.pgrs.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name="CORE_PROJECT_HEIRARCHY_LEVELS",schema=PgrsSchemaName.cescPgrsSchema)
public class ProjectTreeTemplateLevels extends
 BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int phlId;
	
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="TREE_LEVEL")
	private int level;
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch= FetchType.EAGER)
	@JoinColumn(name="PROJECT_ID",insertable =false,updatable=false)
	private Project project;
	
	@Column(name="PROJECT_ID")
	private int projectId;
	
	

	public int getPhlId() {
		return phlId;
	}

	public void setPhlId(int phlId) {
		this.phlId = phlId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	
	



	

	
}
