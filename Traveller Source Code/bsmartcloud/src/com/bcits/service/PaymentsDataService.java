package com.bcits.service;

import java.util.List;

import com.bcits.entity.PaymentsDataEntity;

public interface PaymentsDataService extends GenericService<PaymentsDataEntity>
{
  List<PaymentsDataEntity> getPaymentsData(String searchData,String colvalue,String sdoCode,String paymentMode);
}
