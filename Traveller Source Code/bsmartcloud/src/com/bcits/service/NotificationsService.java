package com.bcits.service;

import java.util.List;

import com.bcits.entity.NotificationsEntity;


public interface NotificationsService extends GenericService<NotificationsEntity>
{
	  List<NotificationsEntity> findAll();
}
