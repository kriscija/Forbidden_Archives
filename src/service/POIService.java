package service;

import db.DatabaseConnectionService;

public class POIService extends AbstractService{
	public POIService(DatabaseConnectionService dbService) {
		super(dbService);
		this.cols = new String[] {"DOB", "FName", "LName"};
		this.addStorProc = "dbo.add_PersonOfInterest";
	}
}
