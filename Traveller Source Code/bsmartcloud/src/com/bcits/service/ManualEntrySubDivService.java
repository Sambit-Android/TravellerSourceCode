package com.bcits.service;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcits.entity.ManualSubDivDetails;


public interface ManualEntrySubDivService extends GenericService<ManualSubDivDetails>
{
   List<ManualSubDivDetails> getAllDetails();
   List<ManualSubDivDetails> getSelectedDetails(ManualSubDivDetails manualSubDivDetails);
}
