package service;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.DatabaseConnectionService;

//Borrowed most of this from the lab
public class OrganizationVictimsView{

	private DatabaseConnectionService dbService = null;

	public OrganizationVictimsView(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public ArrayList<String> getorgID(boolean s, String orgname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select ID from ForbiddenArchives.dbo.OrganizationVictims";
		if(s == true) {
			query = query + " where [name] = '" + orgname + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("ID");
				thename.add(nam);

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

		return thename;
	}
	public ArrayList<String> getName(boolean s, String orgname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [name] from ForbiddenArchives.dbo.OrganizationVictims";
		if(s == true) {
			query = query + " where [name] = '" + orgname + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("name");
				thename.add(nam);

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

		return thename;
	}
	public ArrayList<String> getVicID(boolean s, String orgname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select VictimID from ForbiddenArchives.dbo.OrganizationVictims";
		if(s == true) {
			query = query + " where [name] = '" + orgname + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("VictimID");
				thename.add(nam);

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

		return thename;
	}
	public ArrayList<String> getFname(boolean s, String orgname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select Fname from ForbiddenArchives.dbo.OrganizationVictims";
		if(s == true) {
			query = query + " where [name] = '" + orgname + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Fname");
				thename.add(nam);

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

		return thename;
	}
	public ArrayList<String> getLname(boolean s, String orgname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select Lname from ForbiddenArchives.dbo.OrganizationVictims";
		if(s == true) {
			query = query + " where [name] = '" + orgname + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Lname");
				thename.add(nam);

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

		return thename;
	}

	
}
