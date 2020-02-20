package service;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;

import db.DatabaseConnectionService;

import java.io.*;

//Borrowed most of this from the lab
public class EventService extends AbstractService{

	public EventService(DatabaseConnectionService dbService) {
		super(dbService);
		this.jtableCols = new String[] {"ID", "Type", "Name", "Description", "Organization", "Date"};
		this.cols = new String[] {"id", "type", "date", "desc", "perpID", "name"};
		this.addStorProc = "dbo.add_Event";
		this.deleteStorProc = "dbo.delete_Event";
		this.updateStorProc = "dbo.update_Event";
		
		this.addErrorTable = new HashMap<Integer, String>();
		this.addErrorTable.put(2, "Description too long");
		this.addErrorTable.put(5, "Invalid name");
		this.addErrorTable.put(3, "Attribute too long");
		this.addErrorTable.put(6, "Event already in database");
		
		this.deleteErrorTable = new HashMap<Integer, String>();
		this.deleteErrorTable.put(1, "Invalid ID");
		this.deleteErrorTable.put(2, "ID not found");
		
		this.updateErrorTable = new HashMap<Integer, String>();
		this.updateErrorTable.put(2, "Description too long");
		this.updateErrorTable.put(1, "Invalid ID");
		this.updateErrorTable.put(5, "Invalid name");
		this.updateErrorTable.put(3, "Attribute too long");
		this.updateErrorTable.put(6, "ID already included");
	}

	@Override
	public boolean update(String[] data) {		
		if(data.length != this.cols.length-1) {	// no 'name' column to 1 less expected
			System.out.println("Update Stor Proc for Event has invalid number of inputs");
		}
		String call = "{ ? = call " + this.updateStorProc + "(";
		for(String col : data) {
			call += "?,";
		}
		call = call.substring(0, call.length()-1); //delete last ','
		call += ") }";
		return this.runStorProc(call, data, this.updateErrorTable) == 0;		
	}	

	public Vector<Vector<Object>> getValues(String dateFrom, String dateTo) {
		PreparedStatement stmt = null;
		try {
			String query = "select [ID], [EventType], [Name], [Description], [PerpetratingOrganizationID], [DateOfOccurence] from ForbiddenArchives.dbo.ConspiracyEvent";
			if((dateFrom !=null) || (dateTo !=null)){
			query += " where ";
			query += dateFrom!=null? "[DateOfOccurence] >= ? " : "";
			query += (dateFrom!=null && dateTo!=null)? " AND " : "";
			query += dateTo!=null? "[DateOfOccurence] < ? " : "";
		}
		
			stmt = this.dbService.getConnection().prepareStatement(query);
			int index = 1;
			
			if(dateFrom != null) {
				stmt.setString(index++, dateFrom);
			}
			if(dateTo != null) {
				stmt.setString(index++, dateTo);
			}
			
			ResultSet rs = stmt.executeQuery();
			//column names
		    Vector<String> columnNames = new Vector<String>(Arrays.asList(this.jtableCols));
		    int columnCount = columnNames.size();

		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
		    return data;			
		
		} catch (SQLException ex) {
			
	        if (this.dbService.getConnection() != null) {
	        	 System.err.print("Transaction is being rolled back");
	            try {
	                this.dbService.getConnection().rollback();
	            } catch(SQLException excep) {
	            }
	        }
	       
		} finally {
			if (stmt != null) {
        		try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
			try {
				this.dbService.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
	
