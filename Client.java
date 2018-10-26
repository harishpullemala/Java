/*
 * 
 *  UI Class
 * 
 */

package com.cg.bank.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.cg.bank.bean.DemandDraft;
import com.cg.bank.dao.DemandDraftDAO;
import com.cg.bank.service.DemandDraftService;


public class Client {

	static Logger log = Logger.getLogger(DemandDraftDAO.class.getName());
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("1)	Enter Demand Draft Details");
		System.out.println("2)	Exit");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = Integer.parseInt(br.readLine());
		
		String name = "";
		String mobile = "";
		String forName = "";
		float ddAmount = 0;
		String remarks = "";
		
		switch(option){
			case 1:
				try{
					DemandDraft dd1 = new DemandDraft();
					
					// DemandDraft Input
					System.out.println("Enter the name of the customer : ");
					name = br.readLine();
					dd1.setName(name);
					
					System.out.println("Enter customer phone number: ");
					mobile = br.readLine();
					dd1.setMobile(mobile);
					
					System.out.println("In favor of: ");
					forName = br.readLine();
					dd1.setForName(forName);
					
					System.out.println("Enter Demand Draft amount (in Rs): ");
					ddAmount = Float.parseFloat(br.readLine());
					dd1.setDdAmount(ddAmount);
					
					System.out.println("Enter Remarks: ");
					remarks= br.readLine();
					dd1.setRemarks(remarks);
					
					// UI LAYER TO SERVICE LAYER
					DemandDraftService newService = new DemandDraftService();
					int transaction_id = newService.addDemandDraftDetails(dd1);
					
					if(transaction_id == 0){
						System.out.println("Service is unavailable. Please try again later");
						log.error("Couldn't connect with the database");
						System.exit(0);
					}
					
					// OUTPUT
					System.out.println("Your Demand Draft request has been successfully "
										+ "registered along with the "+transaction_id);
					
				}catch(NumberFormatException e){
					log.error("Input is not a valid number");
					System.out.println("Input is not a valid number");
				}
				break;
			case 2:
				break;
			default:
				log.error("Invalid option selected");
				System.out.println("Invalid option");
		}
		System.exit(0);
	}

}
