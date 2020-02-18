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

import db.DatabaseConnectionService;

//Borrowed most of this from the lab
public class TheoryService extends AbstractService{

	public TheoryService(DatabaseConnectionService dbService) {
		super(dbService);
		this.jtableCols = new String[] {"ID", "Title", "Summary"};
		this.cols = new String[] {"id", "Title", "Summary"};
		this.addStorProc = "dbo.add_Theory";
		this.deleteStorProc = "dbo.delete_Theory";
		this.updateStorProc = "dbo.update_Theory";
		
		this.addErrorTable = new HashMap<Integer, String>();
		
		this.deleteErrorTable = new HashMap<Integer, String>();
		
		this.updateErrorTable = new HashMap<Integer, String>();
	}
	
	public Vector<Vector<Object>> getValues(String title) {
		PreparedStatement stmt = null;
		try {
			String query = "select [ID], [Title], [Summary] from ForbiddenArchives.dbo.Theory";
			if(title != null) {
				query += " where [Title] LIKE ?";
			}
			
			stmt = this.dbService.getConnection().prepareStatement(query);
			int index = 1;
			if(title != null) {
				title = "%" + title + "%";
				stmt.setString(index++, title);
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
