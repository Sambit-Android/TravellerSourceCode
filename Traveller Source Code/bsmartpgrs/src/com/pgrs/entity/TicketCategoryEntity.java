package com.pgrs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import com.pgrs.util.PgrsSchemaName;

@Entity
@Table(name = "PGRS_CATEGORY",schema=PgrsSchemaName.cescPgrsSchema)
@NamedQueries({
	@NamedQuery(name="TicketCategoryEntity.getAllActiveCategories",query="SELECT tc.categoryId,tc.categoryName FROM TicketCategoryEntity tc WHERE tc.status = 1 ORDER BY tc.categoryName ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="TicketCategoryEntity.getAllDivisions",query="SELECT ph.id,ph.text FROM ProjectHeirarchyEntity ph WHERE ph.project.id = :projectId AND ph.type='Division' ORDER BY ph.text ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="TicketCategoryEntity.findAllCategories",query="SELECT tc.categoryId,tc.categoryName,tc.status FROM TicketCategoryEntity tc ORDER BY tc.categoryId DESC"),
	@NamedQuery(name="TicketCategoryEntity.getAllCategoryNames",query="SELECT DISTINCT(tc.categoryName) FROM TicketCategoryEntity tc"),
	@NamedQuery(name="TicketCategoryEntity.setCategoryStatus",query="UPDATE TicketCategoryEntity tc SET tc.status=:status WHERE tc.categoryId=:categoryId"),
	@NamedQuery(name="TicketCategoryEntity.getAssignedCategoryIds",query="SELECT ht.categoryId FROM HelpDeskTicketEntity ht WHERE ht.categoryId=:categoryId AND ht.invalidFlag='No'"),
	@NamedQuery(name="TicketCategoryEntity.getLastCategory",query="SELECT tc.categoryName FROM TicketCategoryEntity tc ORDER BY tc.categoryId DESC"),
	@NamedQuery(name="TicketCategoryEntity.getAllCircles",query="SELECT ph.id,ph.text FROM ProjectHeirarchyEntity ph WHERE ph.project.id = :projectId AND ph.type='Circle' ORDER BY ph.text ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
	@NamedQuery(name="TicketCategoryEntity.getAllSubDivisions",query="SELECT ph.id,ph.text FROM ProjectHeirarchyEntity ph WHERE ph.project.id = :projectId AND ph.type='Subdivision' ORDER BY ph.text ASC",hints={@QueryHint(name="org.hibernate.cacheable",value="true")}),
})
public class TicketCategoryEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private int categoryId;
	
	@Column(name="CATEGORY_NAME")
	private String categoryName;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
