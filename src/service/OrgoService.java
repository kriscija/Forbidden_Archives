package service;

import java.io.BufferedReader;
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
import javax.swing.JTable;

import db.DatabaseConnectionService;

//Borrowed most of this from the lab
public class OrgoService extends AbstractService{
	
	public OrgoService(DatabaseConnectionService dbService) {
		super(dbService);
		this.jtableCols = new String[]{ "ID", "Organization name", "Description", "Attributes", "Date of Establishment" };
		
		this.SQLtableCols = new String[]{ "ID", "name", "Description", "Attribute", "DOE" };
		
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
		this.dbService = dbService;
	}
	
	public String getOrganizationName(String ID) {
		PreparedStatement stmt = null;
		String query = "select TOP 1 name from Organization where ID = ?";
	    try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			int index = 1;
			stmt.setString(index++, ID);
	
			ResultSet rs = stmt.executeQuery();
			//column names
		    Vector<String> columnNames = new Vector<String>(Arrays.asList(this.jtableCols));
		    int columnCount = columnNames.size();

		    rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "";
	}
	
	public String getOrganizationID(String name) {
		PreparedStatement stmt = null;
		String query = "select TOP 1 ID from Organization where name = ?";
	    try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			int index = 1;
			stmt.setString(index++, name);
	
			ResultSet rs = stmt.executeQuery();
			//column names
		    Vector<String> columnNames = new Vector<String>(Arrays.asList(this.jtableCols));
		    int columnCount = columnNames.size();

		    rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "";
	}
	
	public Vector<Vector<Object>> getValues(String dateFrom, String dateTo) {
		PreparedStatement stmt = null;
		try {
			String query = "select ID, name, Description, Attribute, DOE from ForbiddenArchives.dbo.Organization";
			if((dateFrom !=null) || (dateTo !=null)){
			query += " where ";
			query += dateFrom!=null? "DOE >= ? " : "";
			query += (dateFrom!=null && dateTo!=null)? " AND " : "";
			query += dateTo!=null? "DOE < ? " : "";
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
	
	public ArrayList<String> getOrganizationName() {

		ArrayList<String> orgname = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [name] from ForbiddenArchives.dbo.Organization";
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
