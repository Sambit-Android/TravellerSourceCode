package com.pgrs.service;

import com.pgrs.entity.HelpDeskTicketEntity;

public interface ReceiveSmsService {
  public void receiveSmsInfo(HelpDeskTicketEntity ticketEntity,String mobile,String message) throws InterruptedException;


}
