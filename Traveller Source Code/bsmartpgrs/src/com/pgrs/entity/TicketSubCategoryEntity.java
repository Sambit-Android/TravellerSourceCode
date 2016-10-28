package com.pgrs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name = "PGRS_SUBCATEGORY",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries({
	@NamedQuery(name="TicketSubCategoryEntity.getAllActiveSubCategories",query="SELECT tsc.subCategoryId,tsc.subCategoryName FROM TicketSubCategoryEntity tsc WHERE tsc.status = 1 AND tsc.categoryId=:categoryId",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="TicketSubCategoryEntity.getAllSubDivisions",query = "SELECT DISTINCT(ph.siteCode),ph.text,ph.id FROM ProjectHeirarchyEntity ph WHERE ph.parentId =:divisionId AND ph.type='Subdivision' ORDER BY ph.text ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="TicketSubCategoryEntity.getAllDivisions",query = "SELECT DISTINCT(ph.id),ph.text FROM ProjectHeirarchyEntity ph WHERE ph.parentId =:circleId AND ph.type='Division'",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="TicketSubCategoryEntity.findAllSubCategories",query="SELECT tsc.subCategoryId,tsc.subCategoryName,tsc.status,tsc.noLevels,tsc.categoryId,tc.categoryName FROM TicketSubCategoryEntity tsc INNER JOIN tsc.ticketCategoryEntity tc ORDER BY tsc.subCategoryId DESC"),
	@NamedQuery(name="TicketSubCategoryEntity.getAllSubCategoryNames",query="SELECT DISTINCT(tsc.subCategoryName) FROM TicketSubCategoryEntity tsc"),
	@NamedQuery(name="TicketSubCategoryEntity.setSubCategoryStatus",query="UPDATE TicketSubCategoryEntity tsc SET tsc.status=:status WHERE tsc.subCategoryId=:subCategoryId"),
	@NamedQuery(name="TicketSubCategoryEntity.getAssignedSubCategoryIds",query="SELECT ht.subCategoryId FROM HelpDeskTicketEntity ht WHERE ht.subCategoryId=:subCategoryId AND ht.invalidFlag='No'"),
	@NamedQuery(name="TicketSubCategoryEntity.getAllSection",query = "SELECT DISTINCT(ph.siteCode),ph.text,ph.id FROM ProjectHeirarchyEntity ph WHERE ph.id IN(:subDivisionIds) AND ph.type='Section'",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="TicketSubCategoryEntity.getAllSubDivisions1",query = "SELECT DISTINCT(ph.siteCode),ph.text,ph.id FROM ProjectHeirarchyEntity ph WHERE ph.id IN(:divisionIds) AND ph.type='Subdivision'",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="TicketSubCategoryEntity.getAllSubDivisionsForAndroidPart",query="SELECT p.parent.parent.id,p.text,p.siteCode,p.id FROM ProjectHeirarchyEntity p WHERE p.type='Section'",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="TicketSubCategoryEntity.getAllSections",query = "SELECT DISTINCT(ph.siteCode),ph.text,ph.id FROM ProjectHeirarchyEntity ph WHERE ph.parentId =:subDivisionId AND ph.type='Section' ORDER BY ph.text ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
})
public class TicketSubCategoryEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private int subCategoryId;
	
	@Column(name="SUBCATEGORY_NAME")
	private String subCategoryName;
	
	@Column(name="CATEGORY_ID")
	private int categoryId;
	
	@OneToOne
	@JoinColumn(name="CATEGORY_ID",insertable=false,updatable=false)
	private TicketCategoryEntity ticketCategoryEntity;
	
	@Column(name="no_levels_esc")
	private int noLevels;

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getNoLevels() {
		return noLevels;
	}

	public void setNoLevels(int noLevels) {
		this.noLevels = noLevels;
	}

	public TicketCategoryEntity getTicketCategoryEntity() {
		return ticketCategoryEntity;
	}

	public void setTicketCategoryEntity(TicketCategoryEntity ticketCategoryEntity) {
		this.ticketCategoryEntity = ticketCategoryEntity;
	}
	
}
