package com.bcits.service;

import java.util.List;

import com.bcits.entity.AppActivityEntity;

public interface AppActivityService extends GenericService<AppActivityEntity>
{
	List<AppActivityEntity> findAll();	
}
