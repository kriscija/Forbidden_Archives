package service;

import java.util.HashMap;

import db.DatabaseConnectionService;

public class CoversHeirarchyService extends AbstractService{
	
	public CoversHeirarchyService(DatabaseConnectionService dbService) {
		super(dbService);
		this.cols = new String[] {"OrganizationID", "TheoryID"};
		this.addStorProc = "dbo.add_Covers";
	}
}
