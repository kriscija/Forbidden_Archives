import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.io.*;

//Borrowed most of this from the lab
public class EventService {

	private DatabaseConnectionService dbService = null;

	public EventService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean addEvent(String type, String date, String desc, String perpID, String name) {
		CallableStatement tocall = null;
		int returnval = -5;

		try {
			tocall = this.dbService.getConnection().prepareCall("{ ? = call dbo.add_Event(?,?,?,?,?) }");

			tocall.registerOutParameter(1, Types.INTEGER);
			tocall.setString(2, type);
			tocall.setString(3, date);
			tocall.setString(4, desc);
			tocall.setString(5, perpID);
			tocall.setString(6, name);
			System.out.println(date);
			tocall.execute();
			returnval = tocall.getInt(1);
		} catch (SQLException e) {
			// TOO Auto-generated catch block
			e.printStackTrace();
		}

		if (returnval == 0) {
			return true;
		}
		if (returnval == 2) {
			JOptionPane.showMessageDialog(null, "ERROR: Description too long");
			return false;

		}

		if (returnval == 5) {
			JOptionPane.showMessageDialog(null, "ERROR: Invalid name");
			return false;

		}
		if (returnval == 3) {
			JOptionPane.showMessageDialog(null, "ERROR: Attribute too long");
			return false;

		}
		if (returnval == 6) {
			JOptionPane.showMessageDialog(null, "ERROR: Organization already in database");
			return false;

		}

		else {
			JOptionPane.showMessageDialog(null, "Your java code has problems, fix it dude!");
			return false;
		}
	}
	public boolean delEvent(int id) {
		CallableStatement tocall = null;
		int returnval = -5;

		try {
			tocall = this.dbService.getConnection().prepareCall("{ ? = call dbo.delete_Event(?) }");

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
		if (returnval == 1) {
			JOptionPane.showMessageDialog(null, "ERROR: Invalid ID");
			return false;

		}

		if (returnval == 2) {
			JOptionPane.showMessageDialog(null, "ERROR: ID not found");
			return false;
		}


		else {
			JOptionPane.showMessageDialog(null, "Your java code has problems, fix it dude!");
			return false;
		}
	}
	public boolean upEvent(int id,String type, String date, String desc, String perpID, String name) {
		CallableStatement tocall = null;
		int returnval = -5;

		try {
			tocall = this.dbService.getConnection().prepareCall("{ ? = call dbo.update_Event(?,?,?,?,?,?) }");

			tocall.registerOutParameter(1, Types.INTEGER);
			tocall.setInt(2, id);
			tocall.setString(3, type);
			tocall.setString(4, date);
			tocall.setString(5, desc);
			tocall.setString(6, perpID);
			tocall.setString(7, name);

			tocall.execute();
			returnval = tocall.getInt(1);
		} catch (SQLException e) {
			// TOO Auto-generated catch block
			e.printStackTrace();
		}

		if (returnval == 0) {
			return true;
		}
		if (returnval == 2) {
			JOptionPane.showMessageDialog(null, "ERROR: Description too long");
			return false;

		}
		if (returnval == 1) {
			JOptionPane.showMessageDialog(null, "ERROR: Invalid ID");
			return false;

		}

		if (returnval == 5) {
			JOptionPane.showMessageDialog(null, "ERROR: Invalid name");
			return false;

		}
		if (returnval == 3) {
			JOptionPane.showMessageDialog(null, "ERROR: Attribute too long");
			return false;

		}
		if (returnval == 6) {
			JOptionPane.showMessageDialog(null, "ERROR: ID not present	");
			return false;

		}

		else {
			JOptionPane.showMessageDialog(null, "Your java code has problems, fix it dude!");
			return false;
		}
	}
	
	public boolean importEvent(String csvFilePath) {
        try 
	   {
        	BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
 
            int count = 0;
 
            lineReader.readLine(); // skip header line
 
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String type = data[0];
                String date = data[1];
                String desc = data[2];
                String perpID = data[3];
                String name = data[4];

                boolean add = this.addEvent(type, date, desc, perpID, name);
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

	public ArrayList<String> getEventDesc() {

		ArrayList<String> eventdesc = new ArrayList<String>();

		Statement stmt = null;
		String query = "select Description from ForbiddenArchives.dbo.ConspiracyEvent";
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String desc = rs.getString("Description");
				eventdesc.add(desc);

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

		return eventdesc;
	}

	public ArrayList<String> getEventType() {

		ArrayList<String> eventType = new ArrayList<String>();

		Statement stmt = null;
		String query = "select EventType from ForbiddenArchives.dbo.ConspiracyEvent";
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String att = rs.getString("EventType");
				eventType.add(att);

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

		return eventType;
	}

	public ArrayList<String> getOrganizationDoe() {

		ArrayList<String> eventDOO = new ArrayList<String>();

		Statement stmt = null;
		String query = "select DateOfOccurence from ForbiddenArchives.dbo.ConspiracyEvent";
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String doe = rs.getString("DateOfOccurence");
				eventDOO.add(doe);

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

		return eventDOO;
	}

	public ArrayList<String> getOrganizationName() {

		ArrayList<String> eventName = new ArrayList<String>();

		Statement stmt = null;
		String query = "select [Name] from ForbiddenArchives.dbo.ConspiracyEvent";
		try {
			Connection con = this.dbService.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nam = rs.getString("Name");
				eventName.add(nam);

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

		return eventName;
	}
}
