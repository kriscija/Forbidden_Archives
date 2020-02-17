
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//Borrowed most of this from the lab
public class MainViewService{

	private DatabaseConnectionService dbService = null;

	public MainViewService(DatabaseConnectionService dbService) {
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

	public ArrayList<String> getTheoryName(boolean s, String theoryname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [Theory Name] from ForbiddenArchives.dbo.Main_view";
		if(s == true) {
			query = query + " where [Theory Name] = '" + theoryname +  "'";
		}
		System.out.println(query);
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Theory Name");
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
	public ArrayList<String> getTheorySummary(boolean s, String theoryname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [Theory Summary] from ForbiddenArchives.dbo.Main_view";
		if(s == true) {
			query = query + " where [Theory Name] = '" + theoryname +  "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Theory Summary");
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
	public ArrayList<String> getCoveredOrganization(boolean s, String theoryname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [Covered Organization] from ForbiddenArchives.dbo.Main_view";
		if(s == true) {
			query = query + " where [Theory Name] = '" + theoryname +  "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Covered Organization");
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
	public ArrayList<String> getOrgDesc(boolean s, String theoryname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [Organization Description] from ForbiddenArchives.dbo.Main_view";
		if(s == true) {
			query = query + " where [Theory Name] = '" + theoryname +  "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Organization Description");
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
	public ArrayList<String> getConspEvent(boolean s, String theoryname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [Conspiracy Event] from ForbiddenArchives.dbo.Main_view";
		if(s == true) {
			query = query + " where [Theory Name] = '" + theoryname +  "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Conspiracy Event");
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
	public ArrayList<String> getEventDescription(boolean s, String theoryname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [Event Description] from ForbiddenArchives.dbo.Main_view";
		if(s == true) {
			query = query + " where [Theory Name] = '" + theoryname +  "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Event Description");
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
	public ArrayList<String> getEventType(boolean s, String theoryname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [Event Type] from ForbiddenArchives.dbo.Main_view";
		if(s == true) {
			query = query + " where [Theory Name] = '" + theoryname +  "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Event Type");
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
	public ArrayList<String> getEventDate(boolean s, String theoryname) {

		ArrayList<String> thename = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [Event Date] from ForbiddenArchives.dbo.Main_view";
		if(s == true) {
			query = query + " where [Theory Name] = '" + theoryname +  "'";
		}
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Event Date");
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
