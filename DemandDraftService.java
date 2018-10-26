/*
 * 
 * SERVICE CLASS
 * 
 */

package com.cg.bank.service;

import com.cg.bank.bean.DemandDraft;
import com.cg.bank.dao.DemandDraftDAO;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

///////////////////////////////////////////
///////// USER EXCEPTION CLASSES //////////
///////////////////////////////////////////
class IllegalValueException extends Exception{
	private static final long serialVersionUID = 1L;
	
	IllegalValueException(String s){
		super(s);
	}
}

class DDLimitReachedException extends Exception{
	private static final long serialVersionUID = 1L;
	
	DDLimitReachedException(String s){
		super(s);
	}
}

////////////////////////////////////////////
////// END OF USER EXCEPTION CLASSES ///////
////////////////////////////////////////////


public class DemandDraftService implements IDemandDraftService {
	
	static Logger log = Logger.getLogger(DemandDraftDAO.class.getName());
	
	
	public int addDemandDraftDetails (DemandDraft demandDraft){
		
		// Input validation Logic         
		try{
			if(!(Pattern.matches("[A-Z a-z]+",demandDraft.getName()))){
				throw new IllegalValueException("Name contains invalid characters");
			}
			if(!(Pattern.matches("[0-9]{10}+",demandDraft.getMobile()))){
				throw new IllegalValueException("Mobile Number has invalid characters/invalid length");
			}
			if(demandDraft.getDdAmount()<=0){
				throw new IllegalValueException("Invalid amount"); 
			}
		}catch(IllegalValueException e){
			System.err.println(e.getMessage());
			log.error(e.getMessage());
			System.exit(0);
		}
		// End of Input validation Logic       
		
		
		// Commission calculation
		int commission = 0;
		try{
			float am = demandDraft.getDdAmount();
			commission = 0;
			if(am<=5000){
				commission = 10;
			}else if(am>5000 && am<=10000){
				commission = 41;
			}else if(am>10000 && am<=100000){
				commission = 51;
			}else if(am>100000 && am<=500000){
				commission = 306;
			}else{
				throw new DDLimitReachedException("DD Amount should be less than Rs.5,00,000");
			}
		}catch(DDLimitReachedException e){
			System.err.println(e.getMessage());
			log.error(e.getMessage());
			System.exit(0);
		}
		// End of Commission calculation
		
		demandDraft.setCommission(commission);
		
		
		// SERVICE LAYER TO DAO LAYER
		DemandDraftDAO dao = new DemandDraftDAO();
		int transaction_id = dao.addDemandDraftDetails(demandDraft);
		
		return transaction_id;
		
	}
	public DemandDraft getDemandDraftDetails (int transactionId){
		
		
		return null; 
		
	}
}

