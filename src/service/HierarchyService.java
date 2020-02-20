package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import db.DatabaseConnectionService;

public class HierarchyService extends AbstractService {
	public HierarchyService(DatabaseConnectionService dbService) {
		super(dbService);
		this.jtableCols = new String[] { "First Name", "Last Name", "Reports to: Firt Name", "Reports to: Last Name",
				"Rank" };
		this.cols = new String[] { "POI", "OrgID", "ReportsTo" };
		this.addStorProc = "dbo.add_OrganizationHierarchy";
	}

	public Vector<Vector<Object>> getValues(String ID) {
		PreparedStatement stmt = null;
		try {
			String query = "select P1.Fname, P1.Lname, P2.Fname, P2.Lname, OrganizationRank from Person P1 join OrganizationHierarchy og on og.PersonOfInterestID=P1.ID join Person P2 on og.ReportsTo = P2.ID ";
			if (ID != null) {
				query += " where OrganizationID = ?";
			}

			stmt = this.dbService.getConnection().prepareStatement(query);
			int index = 1;

			if (ID != null) {
				stmt.setString(index++, ID);
			}

			ResultSet rs = stmt.executeQuery();
			// column names
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
				} catch (SQLException excep) {
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
