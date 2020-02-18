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
public class EventPerpView extends AbstractService{
	
	public EventPerpView(DatabaseConnectionService dbService) {
		super(dbService);
	}


	public ArrayList<String> getEventID(boolean s, String eventname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select EventID from ForbiddenArchives.dbo.EventPerpetratorsView";
		if(s == true) {
			query = query + " where [name] = '" + eventname + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("EventID");
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
	public ArrayList<String> getPersonID(boolean s, String eventname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select PersonID from ForbiddenArchives.dbo.EventPerpetratorsView";
		if(s == true) {
			query = query + " where [name] = '" + eventname + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("PersonID");
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
	public ArrayList<String> getFname(boolean s, String eventname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select Fname from ForbiddenArchives.dbo.EventPerpetratorsView";
		if(s == true) {
			query = query + " where [name] = '" + eventname + "'";
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
	public ArrayList<String> getLname(boolean s, String eventname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select Lname from ForbiddenArchives.dbo.EventPerpetratorsView";
		if(s == true) {
			query = query + " where [name] = '" + eventname + "'";
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
	public ArrayList<String> getDesc(boolean s, String eventname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select Description from ForbiddenArchives.dbo.EventPerpetratorsView";
		if(s == true) {
			query = query + " where [name] = '" + eventname + "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Description");
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
	public ArrayList<String> getName(boolean s, String eventname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [name] from ForbiddenArchives.dbo.EventPerpetratorsView";
		if(s == true) {
			query = query + " where [name] = '" + eventname + "'";
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

	
}
