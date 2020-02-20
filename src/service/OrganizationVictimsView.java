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
public class OrganizationVictimsView extends AbstractService{
	
	public OrganizationVictimsView(DatabaseConnectionService dbService) {
		super(dbService);
		this.jtableCols = new String[] {"ID", "Event", "First Name", "Last Name"};
	}

	public Vector<Vector<Object>> getValues(String ID) {
		PreparedStatement stmt = null;
		try {
			String query = "select ID, EventName, Fname, Lname from ForbiddenArchives.dbo.OrganizationVictims";
			if(ID != null){
			query += " where ID = ?";
		}
		
			stmt = this.dbService.getConnection().prepareStatement(query);
			int index = 1;
			
			if(ID != null) {
				stmt.setString(index++, ID);
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
