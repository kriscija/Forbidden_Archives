package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;

import db.DatabaseConnectionService;

//Borrowed most of this from the lab
public class MainViewService extends AbstractService{

	public String theory_name;

	public MainViewService(DatabaseConnectionService dbService) {
		super(dbService);
		this.jtableCols = new String[]{ "Theory Name", "Theory Summary", "Covered Organization", "Organziation Description", "Conspiracy Event", "Event Description", "Event Type", "Event Date" };
		
		//this.SQLtableCols = new String[]{ "Theory Name", "Theory Summary", "Description", "Attribute", "DOE" };
	}
	
	public Vector<Vector<Object>> getValues() {
		PreparedStatement stmt = null;
		try {
			String query = "select [Theory Name], [Theory Summary], [Covered Organization], [Organization Description], [Conspiracy Event], [Event Description], [Event Type], [Event Date] from ForbiddenArchives.dbo.Main_view";		
			if(theory_name != null) {
				query += " where [Theory Name] = ?";
			}
			
			stmt = this.dbService.getConnection().prepareStatement(query);
			int index = 1;
			if(theory_name != null) {
				stmt.setString(index++, theory_name);
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

//	public ArrayList<String> getTheoryName(boolean s, String theoryname) {
//
//		ArrayList<String> thename = new ArrayList<String>();
//
//		Statement stmt = null;
//		String query = "select [Theory Name] from ForbiddenArchives.dbo.Main_view";
//		if(s == true) {
//			query = query + " where [Theory Name] = '" + theoryname +  "'";
//		}
//		System.out.println(query);
//		try {
//			Connection con = this.dbService.getConnection();
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				String nam = rs.getString("Theory Name");
//				thename.add(nam);
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return thename;
//	}
//	public ArrayList<String> getTheorySummary(boolean s, String theoryname) {
//
//		ArrayList<String> thename = new ArrayList<String>();
//
//		Statement stmt = null;
//		String query = "select [Theory Summary] from ForbiddenArchives.dbo.Main_view";
//		if(s == true) {
//			query = query + " where [Theory Name] = '" + theoryname +  "'";
//		}
//		try {
//			Connection con = this.dbService.getConnection();
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				String nam = rs.getString("Theory Summary");
//				thename.add(nam);
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return thename;
//	}
//	public ArrayList<String> getCoveredOrganization(boolean s, String theoryname) {
//
//		ArrayList<String> thename = new ArrayList<String>();
//
//		Statement stmt = null;
//		String query = "select [Covered Organization] from ForbiddenArchives.dbo.Main_view";
//		if(s == true) {
//			query = query + " where [Theory Name] = '" + theoryname +  "'";
//		}
//		try {
//			Connection con = this.dbService.getConnection();
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				String nam = rs.getString("Covered Organization");
//				thename.add(nam);
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return thename;
//	}
//	public ArrayList<String> getOrgDesc(boolean s, String theoryname) {
//
//		ArrayList<String> thename = new ArrayList<String>();
//
//		Statement stmt = null;
//		String query = "select [Organization Description] from ForbiddenArchives.dbo.Main_view";
//		if(s == true) {
//			query = query + " where [Theory Name] = '" + theoryname +  "'";
//		}
//		try {
//			Connection con = this.dbService.getConnection();
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				String nam = rs.getString("Organization Description");
//				thename.add(nam);
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return thename;
//	}
//	public ArrayList<String> getConspEvent(boolean s, String theoryname) {
//
//		ArrayList<String> thename = new ArrayList<String>();
//
//		Statement stmt = null;
//		String query = "select [Conspiracy Event] from ForbiddenArchives.dbo.Main_view";
//		if(s == true) {
//			query = query + " where [Theory Name] = '" + theoryname +  "'";
//		}
//		try {
//			Connection con = this.dbService.getConnection();
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				String nam = rs.getString("Conspiracy Event");
//				thename.add(nam);
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return thename;
//	}
//	public ArrayList<String> getEventDescription(boolean s, String theoryname) {
//
//		ArrayList<String> thename = new ArrayList<String>();
//
//		Statement stmt = null;
//		String query = "select [Event Description] from ForbiddenArchives.dbo.Main_view";
//		if(s == true) {
//			query = query + " where [Theory Name] = '" + theoryname +  "'";
//		}
//		try {
//			Connection con = this.dbService.getConnection();
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				String nam = rs.getString("Event Description");
//				thename.add(nam);
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return thename;
//	}
//	public ArrayList<String> getEventType(boolean s, String theoryname) {
//
//		ArrayList<String> thename = new ArrayList<String>();
//
//		Statement stmt = null;
//		String query = "select [Event Type] from ForbiddenArchives.dbo.Main_view";
//		if(s == true) {
//			query = query + " where [Theory Name] = '" + theoryname +  "'";
//		}
//		try {
//			Connection con = this.dbService.getConnection();
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				String nam = rs.getString("Event Type");
//				thename.add(nam);
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return thename;
//	}
//	public ArrayList<String> getEventDate(boolean s, String theoryname) {
//
//		ArrayList<String> thename = new ArrayList<String>();
//
//		Statement stmt = null;
//		String query = "select [Event Date] from ForbiddenArchives.dbo.Main_view";
//		if(s == true) {
//			query = query + " where [Theory Name] = '" + theoryname +  "'";
//		}
//		try {
//			Connection con = this.dbService.getConnection();
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				String nam = rs.getString("Event Date");
//				thename.add(nam);
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return thename;
//	}
}
