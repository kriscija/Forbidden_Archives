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
import java.util.HashMap;

import javax.swing.JOptionPane;

import db.DatabaseConnectionService;

import java.io.*;

//Borrowed most of this from the lab
public class EventService extends AbstractService{

	public EventService(DatabaseConnectionService dbService) {
		super(dbService);
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
	
	public ArrayList<String> getEventDesc() {

		ArrayList<String> eventdesc = new ArrayList<String>();

		Statement stmt = null;
		String query = "select Description from ForbiddenArchives.dbo.ConspiracyEvent";
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String desc = rs.getString("Description");
				eventdesc.add(desc);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

		}

		return eventdesc;
	}

	public ArrayList<String> getEventType() {

		ArrayList<String> eventType = new ArrayList<String>();

		Statement stmt = null;
		String query = "select EventType from ForbiddenArchives.dbo.ConspiracyEvent";
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String att = rs.getString("EventType");
				eventType.add(att);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

		}

		return eventType;
	}

	public ArrayList<String> getOrganizationDoe() {

		ArrayList<String> eventDOO = new ArrayList<String>();

		Statement stmt = null;
		String query = "select DateOfOccurence from ForbiddenArchives.dbo.ConspiracyEvent";
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String doe = rs.getString("DateOfOccurence");
				eventDOO.add(doe);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

		}

		return eventDOO;
	}

	public ArrayList<String> getOrganizationName() {

		ArrayList<String> eventName = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [Name] from ForbiddenArchives.dbo.ConspiracyEvent";
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Name");
				eventName.add(nam);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

		}

		return eventName;
	}
}
