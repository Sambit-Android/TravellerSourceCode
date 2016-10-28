package com.bcits.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.bcits.entity.GroupEntity;

public interface GroupService extends GenericService<GroupEntity>
{
	List<GroupEntity> findAll();
	
	void updateGroupData(HttpServletRequest request ,ModelMap model,GroupEntity grp);
}