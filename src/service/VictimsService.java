package service;

import db.DatabaseConnectionService;

public class VictimsService extends AbstractService{
	public VictimsService(DatabaseConnectionService dbService) {
		super(dbService);
		this.cols = new String[] {"DOB", "First Name", "Last Name"};
		this.addStorProc = "dbo.add_Victim";
	}
}
