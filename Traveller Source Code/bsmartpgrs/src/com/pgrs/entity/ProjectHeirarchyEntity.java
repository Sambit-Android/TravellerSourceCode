package com.pgrs.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
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
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name="CORE_PROJECT_HIERARCHY",schema=PgrsSchemaName.cescPgrsSchema)
@BatchSize(size = 10)
@Cacheable(true)
@NamedQueries({
	@NamedQuery(name = "ProjectHeirarchyEntity.getListById", query = "SELECT a FROM ProjectHeirarchyEntity a WHERE a.parentId = :parentId ORDER BY a.text",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "ProjectHeirarchyEntity.readTree", query = "Select o.id,o.text from ProjectHeirarchyEntity o WHERE o.project.company.id=:companyId AND o.project.id=:projectId AND o.parentId=:id",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "ProjectHeirarchyEntity.getObbject", query = "SELECT a FROM ProjectHeirarchyEntity a WHERE a.project.id = :projectId AND a.project.company.id=:companyId AND a.parentId = :parentId ORDER BY a.text",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name = "ProjectHeirarchyEntity.getProjectHeirarchyByOfficeId", query = "SELECT a FROM ProjectHeirarchyEntity a where a.id=:id",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="ProjectHeirarchyEntity.getOfficeNameBasedOnId",query="SELECT a.text FROM ProjectHeirarchyEntity a WHERE a.id = :officeId"),
	@NamedQuery(name="ProjectHeirarchyEntity.getParentId",query="SELECT a.parentId FROM ProjectHeirarchyEntity a WHERE a.id = :id"),
	@NamedQuery(name = "InvoiceCheque.getAllOffice", query = "SELECT i.id,i.text,i.parentId,i.project.id FROM ProjectHeirarchyEntity i ORDER BY i.id ASC"),	
	@NamedQuery(name = "ProjectHeirarchyEntity.getCountBasedOnLevelAndProjectId", query = "SELECT phe.siteCode From ProjectHeirarchyEntity phe INNER JOIN phe.projectTreeTemplateLevels ptl WHERE ptl.phlId = :level AND phe.project.id=:projectId ORDER BY phe.id DESC"),
	@NamedQuery(name = "ProjectHeirarchyEntity.getCountBasedOnLevelAndProjectIdAndSiteCode", query = "SELECT phe.siteCode From ProjectHeirarchyEntity phe INNER JOIN phe.projectTreeTemplateLevels ptl WHERE ptl.phlId = :level AND phe.project.id=:projectId AND phe.siteCode=:siteCode ORDER BY phe.id DESC"),
	@NamedQuery(name = "ProjectHeirarchyEntity.getDivisionSiteCode", query = "SELECT phe.siteCode From ProjectHeirarchyEntity phe INNER JOIN phe.projectTreeTemplateLevels ptl WHERE ptl.phlId = :level AND phe.project.id=:projectId AND phe.parentId=:parentId ORDER BY phe.id DESC"),
	//@NamedQuery(name="ProjectHeirarchyEntity.getAllCircles",query="SELECT ph.id,ph.text,ph.siteCode FROM ProjectHeirarchyEntity ph WHERE ph.project.id = :projectId AND ph.type='Circle' and ph.siteCode in (select distinct substr(cast(sitecode as string),0,2) from  Application)"),
	@NamedQuery(name="ProjectHeirarchyEntity.getAllDivisions",query = "SELECT DISTINCT(ph.siteCode),ph.text,ph.id FROM ProjectHeirarchyEntity ph WHERE ph.id IN(:circleIds) AND ph.type='Division'"),
	
	@NamedQuery(name="ProjectHeirarchyEntity.getSiteCode",query = "SELECT ph.siteCode FROM ProjectHeirarchyEntity ph WHERE ph.id=:projectId"),
	@NamedQuery(name="ProjectHeirarchyEntity.getProjectHirarchybyId",query = "SELECT ph FROM ProjectHeirarchyEntity ph WHERE ph.id=:id"),
	@NamedQuery(name="ProjectHeirarchyEntity.getTextBasedOnSiteCode",query = "SELECT ph.text FROM ProjectHeirarchyEntity ph WHERE ph.siteCode=:siteCode"),
	@NamedQuery(name="ProjectHeirarchyEntity.getProjectHirarchybySiteCode",query = "SELECT ph.treeHierarchy,ph.text FROM ProjectHeirarchyEntity ph WHERE ph.siteCode LIKE :siteCode AND ph.type='Section'",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="ProjectHeirarchyEntity.getProjectTypeBySitecode",query = "SELECT ph.type FROM ProjectHeirarchyEntity ph WHERE ph.siteCode=:siteCode"),
})
public class ProjectHeirarchyEntity extends BaseEntity{    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@JsonIgnore
    public ProjectHeirarchyEntity getParent() {
        return parent;
    }

    @JsonIgnore
    public void setParent(ProjectHeirarchyEntity parent) {
        this.parent = parent;
    }
    
    @ManyToOne()
    @JoinColumn(name="PARENT_ID", insertable=false, updatable=false)
    private ProjectHeirarchyEntity parent;
 
   
    @JsonIgnore    
    public Set<ProjectHeirarchyEntity> getChilds() {
        return childs;
    }
    
    @JsonIgnore
    public void setChilds(Set<ProjectHeirarchyEntity> childs) {
        this.childs = childs;
    }
    
    @OneToMany(mappedBy="parent", fetch=FetchType.EAGER)
    @JsonIgnore
    private Set<ProjectHeirarchyEntity> childs = new HashSet<ProjectHeirarchyEntity>();

    @Transient
    public Boolean getHasChilds() {
        return !getChilds().isEmpty();
    }  
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    
    @Column(name="TEXT")
    private String text;
    
    @Column(name="PARENT_ID")
    private Integer parentId;
    
    @Column(name="TREE_HIERARCHY")
    private String treeHierarchy;
    
    @Column(name="TYPE")
    private String type;
    	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_ID")
	private Project project;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PTL_ID")
	private ProjectTreeTemplateLevels projectTreeTemplateLevels;

	@Column(name="SITE_CODE")
	private String siteCode;
	
	@Column(name="CLASSIFICATION_TYPE")
	private String classificationType;

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

	public String getTreeHierarchy() {
		return treeHierarchy;
	}

	public void setTreeHierarchy(String treeHierarchy) {
		this.treeHierarchy = treeHierarchy;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonIgnore
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project; 
	}

	@JsonIgnore
	public ProjectTreeTemplateLevels getProjectTreeTemplateLevels() {
		return projectTreeTemplateLevels;
	}

	public void setProjectTreeTemplateLevels(
			ProjectTreeTemplateLevels projectTreeTemplateLevels) {
		this.projectTreeTemplateLevels = projectTreeTemplateLevels;
	}

	
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getClassificationType() {
		return classificationType;
	}

	public void setClassificationType(String classificationType) {
		this.classificationType = classificationType;
	}

	/*public Integer getIndividualCatCode() {
		return individualCatCode;
	}

	public void setIndividualCatCode(Integer individualCatCode) {
		this.individualCatCode = individualCatCode;
	}*/
	
	
	

}