/*
 * 
 * DAO Class
 * 
 */

package com.cg.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.cg.bank.bean.DemandDraft;

public class DemandDraftDAO implements IDemandDraftDAO{
	
	static Logger log = Logger.getLogger(DemandDraftDAO.class.getName());
	
	public int addDemandDraftDetails (DemandDraft demandDraft) {
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			log.error("Oracle Driver Missing");
		}
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "hr";
		String pass = "hr";
		try{
			Connection con = DriverManager.getConnection(url,user,pass);
			
			// Getting Transaction ID from the Database Sequence
			Statement st1 = con.createStatement();  
			ResultSet rs = st1.executeQuery("select Transaction_Id_Seq.nextval from dual");
			int transaction_id = 0;
			while(rs.next())
				transaction_id = rs.getInt(1);
			
			// Inserting demandDraft values into the Database
			String sql = "insert into demand_draft values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, transaction_id);
			ps.setString(2, demandDraft.getName());
			ps.setString(3, demandDraft.getForName());
			ps.setString(4, demandDraft.getMobile());
			//////////////
			java.util.Date uDate = new java.util.Date();
			java.sql.Date sDate = new java.sql.Date(uDate.getTime());
			ps.setDate(5, sDate);
			//////////////
			ps.setFloat(6, demandDraft.getDdAmount());
			ps.setInt(7, demandDraft.getCommission());
			ps.setString(8, demandDraft.getRemarks());
			
			ps.executeUpdate();
			
			log.info("Added one row to the database");
	
			con.close();
			
			return transaction_id;  
			
		}catch(SQLException e){
//			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		
		return 0;
	}
	public DemandDraft getDemandDraftDetails (int transactionId){
		return null;
	}
}
