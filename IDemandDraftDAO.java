package com.cg.bank.dao;

import com.cg.bank.bean.DemandDraft;

public interface IDemandDraftDAO {
	int addDemandDraftDetails (DemandDraft demandDraft);
	DemandDraft getDemandDraftDetails (int transactionId);
}
