package service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import db.DatabaseConnectionService;

//Borrowed most of this from the lab
public class TheoryService extends AbstractService{

	private DatabaseConnectionService dbService = null;

	public TheoryService(DatabaseConnectionService dbService) {
		super(dbService);
		this.cols = new String[] {"id", "Title", "Summary"};
		this.addStorProc = "dbo.add_Theory";
		this.deleteStorProc = "dbo.delete_Theory";
		this.updateStorProc = "dbo.update_Theory";
		
		this.addErrorTable = new HashMap<Integer, String>();
		
		this.deleteErrorTable = new HashMap<Integer, String>();
		
		this.updateErrorTable = new HashMap<Integer, String>();
	}
}
