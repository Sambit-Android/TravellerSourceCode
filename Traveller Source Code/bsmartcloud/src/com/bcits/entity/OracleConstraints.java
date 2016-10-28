package com.bcits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name="All_constraints")
@NamedQueries({@NamedQuery(name="constraint.getChecks",query="SELECT a.searchCondition from OracleConstraints a WHERE tableName = :tableName AND constraintName = :constraintName")
	
})
public class OracleConstraints 
{
	
	@Column(name="SEARCH_CONDITION")
	private String searchCondition;
	@Id
	@Column(name="table_name")
	private String tableName;
	
	@Column(name="constraint_name")
	private String constraintName;

	public String getSearchCondition() 
	{
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) 
	{
		this.searchCondition = searchCondition;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getConstraintName() {
		return constraintName;
	}

	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}

	

}
