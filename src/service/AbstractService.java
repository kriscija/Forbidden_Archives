package service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import db.DatabaseConnectionService;

public abstract class AbstractService {
	public DatabaseConnectionService dbService = null;

	String[] cols;
	String addStorProc;
	String deleteStorProc;
	String updateStorProc;
	
	Vector<Vector<Object>> data;
	public String[] jtableCols;
	
	String[] SQLtableCols;
	
	Map<Integer, String> addErrorTable;
	Map<Integer, String> deleteErrorTable;
	Map<Integer, String> updateErrorTable;
	
	public AbstractService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public boolean add(String[] data) {		
		if(data.length != this.cols.length-1) {
			System.out.println("Add Stor Proc has invalid number of inputs");
		}
		String call = "{ ? = call " + this.addStorProc + "(";
		for(String col : data) {
			call += "?,";
		}
		call = call.substring(0, call.length()-1); //delete last ','
		call += ") }";
		return this.runStorProc(call, data, this.addErrorTable) == 0;		
	}
	
	public boolean delete(int id) {		
		String call = "{ ? = call " + this.deleteStorProc + "(?) }";
		String[] data = new String[]{String.valueOf(id)};

		return this.runStorProc(call, data, this.deleteErrorTable) == 0;
	}

	public boolean update(String[] data) {		
		if(data.length != this.cols.length) {
			System.out.println("Update Stor Proc has invalid number of inputs");
		}
		String call = "{ ? = call " + this.updateStorProc + "(";
		for(String col : data) {
			call += "?,";
		}
		call = call.substring(0, call.length()-1); //delete last ','
		call += ") }";
		return this.runStorProc(call, data, this.updateErrorTable) == 0;		
	}	
	
	public int runStorProc(String storProc, String[] data, Map<Integer, String> errorTable) {		
		int returnval = -5;

		try {
			returnval = this.execute(storProc,data);
		} catch (SQLException e) {
			// TOO Auto-generated catch block
			e.printStackTrace();
		}
		
		String errMessage = errorTable.get(returnval);
		if(errMessage != null) {
			JOptionPane.showMessageDialog(null, "ERROR: " + errMessage);
		} else {
			if(returnval != 0) {
				JOptionPane.showMessageDialog(null, "Your java code has problems, fix it dude!");
			}
		}
		return returnval;
	}
	
	
	public int execute(String call, String[] data) throws SQLException {
		CallableStatement tocall = null;
		tocall = this.dbService.getConnection().prepareCall(call);
		
		tocall.registerOutParameter(1, Types.INTEGER);
		for(int i = 0; i < data.length; i++) {
			tocall.setString(i+2, data[i]);
		}
		
		tocall.execute();
		return tocall.getInt(1);
	}
	
	public boolean importCSV(String csvFilePath) {
        try 
	   {
        	BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
 
            int count = 0;
 
            lineReader.readLine(); // skip header line
 
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");

                boolean add = this.add(data);
                if(!add) {
                	System.err.println("Import failed on line: " + lineText);
                }
                
            }
 
            lineReader.close();
	        System.out.println("Data Successfully Uploaded");
	   }
	   catch (Exception e)
	   {
	           e.printStackTrace();
	   }
		return false;
	}
	
	public DefaultTableModel buildTableModel(Vector<Vector<Object>> data, String[] jtableCols)
	        throws SQLException {
		return new DefaultTableModel(data, new Vector<String>(Arrays.asList(jtableCols)));
	}
	
}
