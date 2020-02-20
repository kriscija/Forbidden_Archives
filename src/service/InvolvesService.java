package service;

import db.DatabaseConnectionService;

public class InvolvesService extends AbstractService{
	public InvolvesService(DatabaseConnectionService dbService) {
		super(dbService);
		this.cols = new String[] {"EventID", "TheoryID"};
		this.addStorProc = "dbo.add_TheoryInvolves";
	}
}
