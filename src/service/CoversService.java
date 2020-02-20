package service;

import db.DatabaseConnectionService;

public class CoversService extends AbstractService{
	public CoversService(DatabaseConnectionService dbService) {
		super(dbService);
		this.cols = new String[] { "OrganizationID", "TheoryID"};
		this.addStorProc = "dbo.add_Covers";
	}

}
