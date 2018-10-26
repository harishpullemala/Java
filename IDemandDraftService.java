package com.cg.bank.service;
import com.cg.bank.bean.DemandDraft;

public interface IDemandDraftService {
	int addDemandDraftDetails (DemandDraft demandDraft);
	DemandDraft getDemandDraftDetails (int transactionId);
}

