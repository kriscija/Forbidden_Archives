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
public class EventPerpView{

	private DatabaseConnectionService dbService = null;

	public EventPerpView(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

//	public boolean addOrgo(String desc, String att, String date, String name) {
//		CallableStatement tocall = null;
//		int returnval = -5;
//
//		try {
//			tocall = this.dbService.getConnection().prepareCall("{ ? = call dbo.add_Organization(?,?,?,?) }");
//
//			tocall.registerOutParameter(1, Types.INTEGER);
//			tocall.setString(2, desc);
//			tocall.setString(3, att);
//			tocall.setString(4, date);
//			tocall.setString(5, name);
//			System.out.println(date);
//			tocall.execute();
//			returnval = tocall.getInt(1);
//		} catch (SQLException e) {
//			// TOO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		if (returnval == 0) {
//			return true;
//		}
//		if (returnval == 2) {
//			JOptionPane.showMessageDialog(null, "ERROR: Description too long");
//			return false;
//
//		}
//
//		if (returnval == 5) {
//			JOptionPane.showMessageDialog(null, "ERROR: Invalid name");
//			return false;
//
//		}
//		if (returnval == 3) {
//			JOptionPane.showMessageDialog(null, "ERROR: Attribute too long");
//			return false;
//
//		}
//		if (returnval == 6) {
//			JOptionPane.showMessageDialog(null, "ERROR: Organization already in database");
//			return false;
//
//		}
//
//		else {
//			JOptionPane.showMessageDialog(null, "Your java code has problems, fix it dude!");
//			return false;
//		}
//	}
//	public boolean delOrgo(int id) {
//		CallableStatement tocall = null;
//		int returnval = -5;
//
//		try {
//			tocall = this.dbService.getConnection().prepareCall("{ ? = call dbo.delete_Organization(?) }");
//
//			tocall.registerOutParameter(1, Types.INTEGER);
//			tocall.setInt(2, id);
//			
//
//			tocall.execute();
//			returnval = tocall.getInt(1);
//		} catch (SQLException e) {
//			// TOO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		if (returnval == 0) {
//			return true;
//		}
//		if (returnval == 1) {
//			JOptionPane.showMessageDialog(null, "ERROR: Invalid ID");
//			return false;
//
//		}
//
//		if (returnval == 2) {
//			JOptionPane.showMessageDialog(null, "ERROR: ID not found");
//			return false;
//		}
//
//
//		else {
//			JOptionPane.showMessageDialog(null, "Your java code has problems, fix it dude!");
//			return false;
//		}
//	}
//	public boolean upOrgo(int id,String desc, String att, String date, String name) {
//		CallableStatement tocall = null;
//		int returnval = -5;
//
//		try {
//			tocall = this.dbService.getConnection().prepareCall("{ ? = call dbo.update_Organization(?,?,?,?,?) }");
//
//			tocall.registerOutParameter(1, Types.INTEGER);
//			tocall.setInt(2, id);
//			tocall.setString(3, desc);
//			tocall.setString(4, att);
//			tocall.setString(5, date);
//			tocall.setString(6, name);
//
//			tocall.execute();
//			returnval = tocall.getInt(1);
//		} catch (SQLException e) {
//			// TOO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		if (returnval == 0) {
//			return true;
//		}
//		if (returnval == 2) {
//			JOptionPane.showMessageDialog(null, "ERROR: Description too long");
//			return false;
//
//		}
//		if (returnval == 1) {
//			JOptionPane.showMessageDialog(null, "ERROR: Invalid ID");
//			return false;
//
//		}
//
//		if (returnval == 5) {
//			JOptionPane.showMessageDialog(null, "ERROR: Invalid name");
//			return false;
//
//		}
//		if (returnval == 3) {
//			JOptionPane.showMessageDialog(null, "ERROR: Attribute too long");
//			return false;
//
//		}
//		if (returnval == 6) {
//			JOptionPane.showMessageDialog(null, "ERROR: ID not present	");
//			return false;
//
//		}
//
//		else {
//			JOptionPane.showMessageDialog(null, "Your java code has problems, fix it dude!");
//			return false;
//		}
//	}

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
