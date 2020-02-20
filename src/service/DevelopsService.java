package service;

import db.DatabaseConnectionService;

public class DevelopsService extends AbstractService{
	public DevelopsService(DatabaseConnectionService dbService) {
		super(dbService);
		this.cols = new String[] { "TheoryID", "TruthSeekerID"};
		this.addStorProc = "dbo.add_Develops";
	}
}
