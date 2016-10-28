package com.bcits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name ="MODULEMASTER", schema ="VCLOUDENGINE")
@NamedQueries({
		@NamedQuery(name = "ModuleMasterEntity.findAll", query = "SELECT mmse FROM ModuleMasterEntity mmse ORDER BY mmse.id"),
		@NamedQuery(name = "ModuleMasterEntity.updateStatus", query = "UPDATE ModuleMasterEntity mmse SET mmse.status=:status WHERE mmse.id LIKE :id"),
		@NamedQuery(name = "ModuleMasterEntity.findAllActivatedModuleMasters", query = "SELECT mmse FROM ModuleMasterEntity mmse WHERE mmse.status='Active' ORDER BY mmse.id"),
		@NamedQuery(name = "ModuleMasterEntity.updateStatusAllModules", query = "UPDATE ModuleMasterEntity mmse SET mmse.status=:status "),
		@NamedQuery(name = "ModuleMasterEntity.ActivateSelectedModules", query = "UPDATE ModuleMasterEntity mmse SET mmse.status=:status WHERE mmse.id IN (:allModules)"),
		@NamedQuery(name = "ModuleMasterEntity.DeActivateNonSelectedModules", query = "UPDATE ModuleMasterEntity mmse SET mmse.status=:status WHERE mmse.id NOT IN (:allModules)")
		
		
})
public class ModuleMasterEntity {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "MODULENAME")
	private String moduleName;

	@Column(name = "STATUS")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName, String status) {
		this.moduleName = moduleName;
		this.status = status;
	}

	public ModuleMasterEntity() {

	}

	public ModuleMasterEntity(String id, String moduleName) {
		this.id = id;
		this.moduleName = moduleName;

	}

}
