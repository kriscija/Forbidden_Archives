package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import db.DatabaseConnectionService;

//Borrowed most of this from the lab
public class OrganService extends AbstractService{

	public OrganService(DatabaseConnectionService dbService) {
		super(dbService);
		this.cols = new String[] {"id", "desc", "att", "date", "name"};
		this.addStorProc = "dbo.add_Organization";
		this.deleteStorProc = "dbo.delete_Organization";
		this.updateStorProc = "dbo.update_Organization";
		
		this.addErrorTable = new HashMap<Integer, String>();
		this.addErrorTable.put(2, "Description too long");
		this.addErrorTable.put(5, "Invalid name");
		this.addErrorTable.put(3, "Attribute too long");
		this.addErrorTable.put(6, "Organization already in database");
		
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

	public ArrayList<String> getOrganizationDesc(boolean s, String date, String date2) {

		ArrayList<String> orgdesc = new ArrayList<String>();

		Statement stmt = null;
		String query = "select Description from ForbiddenArchives.dbo.Organization";
		if(s == true) {
			query = query + " where DOE >= '" + date + "' AND DOE < '" + date2 + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String desc = rs.getString("Description");
				orgdesc.add(desc);

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

		return orgdesc;
	}

	public ArrayList<String> getOrganizationatt(boolean s, String date, String date2) {

		ArrayList<String> orgatt = new ArrayList<String>();

		Statement stmt = null;
		String query = "select Attribute from ForbiddenArchives.dbo.Organization";
		if(s == true) {
			query = query + " where DOE >= '" + date + "' AND DOE < '" + date2 + "'";
		}
		
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String att = rs.getString("Attribute");
				orgatt.add(att);

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

		return orgatt;
	}

	public ArrayList<String> getOrganizationDoe(boolean s, String date, String date2) {

		ArrayList<String> orgdoe = new ArrayList<String>();

		Statement stmt = null;
		String query = "select DOE from ForbiddenArchives.dbo.Organization";
		if(s == true) {
			query = query + " where DOE >= '" + date + "' AND DOE < '" + date2 + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String doe = rs.getString("DOE");
				orgdoe.add(doe);

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

		return orgdoe;
	}

	public ArrayList<String> getOrganizationName(boolean s, String date, String date2) {

		ArrayList<String> orgname = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [name] from ForbiddenArchives.dbo.Organization";
		if(s == true) {
			query = query + " where DOE >= '" + date + "' AND DOE < '" + date2 + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("name");
				orgname.add(nam);

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

		return orgname;
	}
}
