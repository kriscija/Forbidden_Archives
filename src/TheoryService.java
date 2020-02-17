import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//Borrowed most of this from the lab
public class TheoryService {

	private DatabaseConnectionService dbService = null;

	public TheoryService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean addTheory(String title,String summary) {
		CallableStatement tocall = null;
		int returnval = -5;

		try {
			tocall = this.dbService.getConnection().prepareCall("{ ? = call dbo.add_Theory(?,?) }");

			tocall.registerOutParameter(1, Types.INTEGER);
			tocall.setString(2, title);
			tocall.setString(3, summary);
			
			tocall.execute();
			returnval = tocall.getInt(1);
		} catch (SQLException e) {
			// TOO Auto-generated catch block
			System.out.println("Add Theory Failed");
			e.printStackTrace();
		}

		if (returnval == 0) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "ERROR: Invalid Data");
			return false;
		}
	}
	public boolean delTheory(int id) {
		CallableStatement tocall = null;
		int returnval = -5;

		try {
			tocall = this.dbService.getConnection().prepareCall("{ ? = call dbo.delete_Theory(?) }");

			tocall.registerOutParameter(1, Types.INTEGER);
			tocall.setInt(2, id);
			

			tocall.execute();
			returnval = tocall.getInt(1);
		} catch (SQLException e) {
			// TOO Auto-generated catch block
			e.printStackTrace();
		}

		if (returnval == 0) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "ERROR: Invalid Data");
			return false;

		}

	}
	public boolean upTheory(int id, String title,String summary) {
		CallableStatement tocall = null;
		int returnval = -5;

		try {
			tocall = this.dbService.getConnection().prepareCall("{ ? = call dbo.update_Theory(?,?,?) }");

			tocall.registerOutParameter(1, Types.INTEGER);
			tocall.setInt(2, id);
			tocall.setString(3, title);
			tocall.setString(4, summary);
			

			tocall.execute();
			returnval = tocall.getInt(1);
		} catch (SQLException e) {
			// TOO Auto-generated catch block
			e.printStackTrace();
		}

		if (returnval == 0) {
			return true;
		}

		else {
			JOptionPane.showMessageDialog(null, "ERROR: Invalid Data");
			return false;
		}
	}

	public boolean importTheory(String csvFilePath) {
        try 
	   {
        	BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
 
            int count = 0;
 
            lineReader.readLine(); // skip header line
 
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String title = data[0];
                String summary = data[1];

                boolean add = this.addTheory(title, summary);
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

	
}
