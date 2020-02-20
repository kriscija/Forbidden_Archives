package service;

import db.DatabaseConnectionService;

public class TruthSeekerService extends AbstractService {
	public TruthSeekerService(DatabaseConnectionService dbService) {
		super(dbService);
		this.cols = new String[] { "DOB", "FName", "LName" };
		this.addStorProc = "dbo.add_TruthSeeker";
	}
}
