package service;

import db.DatabaseConnectionService;

public class EventPerpetratorsService extends AbstractService{
	public EventPerpetratorsService(DatabaseConnectionService dbService) {
		super(dbService);
		this.cols = new String[] {"EventID", "PersonOfInterestID", "Description"};
		this.addStorProc = "dbo.add_EventPerpetrators";
	}
}
