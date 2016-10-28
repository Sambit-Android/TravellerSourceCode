package com.bcits.service;

import java.util.List;

import com.bcits.entity.ComplaintEntity;

public interface ComplaintService extends GenericService<ComplaintEntity>
{
       List<ComplaintEntity> findAll();
}
