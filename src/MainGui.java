import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MainGui {
	private JFrame fullframe = new JFrame("Forbidden Archives");
	private DatabaseConnectionService con = new DatabaseConnectionService("golem.csse.rose-hulman.edu",
			"ForbiddenArchives");
	public OrganService orgoservice = new OrganService(con);
	public UserService userservice = new UserService(con);
	public TheoryService theoryservice = new TheoryService(con);
	public EventService eventservice = new EventService(con);
//	public PersonService personservice = new PersonService(con);
	public MainViewService mainviewservice = new MainViewService(con);
	public EventPerpView eventperpview = new EventPerpView(con);
	public OrganizationVictimsView orgovicview = new OrganizationVictimsView(con);
//	
	private String[] orgodisplays = { "Organization name", "Description", "Attributes", "Date of Establishment" };
	private String[][] orgodata = { { "A1", "A2", "A3", "A4" }, { "B1", "B2", "B3", "B4" } };
	private JTable todisporgo = new JTable(orgodata, orgodisplays);
	private String[] maindisplays = { "Theory Name", "Theory Summary", "Covered Organization", "Organization Description","Conspiracy Event", "Event Description", "Event Type", "Event Date" };
	private String[][] maindata = { { "A1", "A2", "A3", "A4","A5","a6","a7","a8" }, { "B1", "B2", "B3", "B4","b5","b6","b7","b8" } };
	private JTable todispmain = new JTable(maindata, maindisplays);
	private String[] eperpdisplays = { "Event name", "Description", "First Name", "Last Name" };
	private String[][] eperpdata = { { "A1", "A2", "A3", "A4" }, { "B1", "B2", "B3", "B4" } };
	private JTable todispeperp = new JTable(eperpdata, eperpdisplays);
	private String[] orvicdisplays = { "Organization name", "victim firstname", "victim lastname" };
	private String[][] orvicdata = { { "A1", "A2", "A3" }, { "B1", "B2", "B3" } };
	private JTable todisporvic = new JTable(orvicdata, orvicdisplays);
	final CardLayout layout = new CardLayout();
	final JPanel mainframe = new JPanel(layout);

	public void reinitializeData() {
		
		ArrayList<String> orgnam = this.orgoservice.getOrganizationName(false,"","");
		ArrayList<String> orgdesc = this.orgoservice.getOrganizationDesc(false,"","");
		ArrayList<String> orgdoe = this.orgoservice.getOrganizationDoe(false,"","");
		ArrayList<String> orgatt = this.orgoservice.getOrganizationatt(false,"","");
		

		ArrayList<String[]> transform = new ArrayList<String[]>();
		for (int k = 0; k < orgnam.size(); k++) {
			String[] toadd = new String[4];
			toadd[0] = orgnam.get(k);
			toadd[1] = orgdesc.get(k);
			toadd[2] = orgatt.get(k);
			toadd[3] = orgdoe.get(k);

			transform.add(toadd);

		}
		String[][] mandata = new String[transform.size()][4];
		this.orgodata = new String[transform.size()][4];
		for (int i = 0; i < transform.size(); i++) {

			mandata[i] = transform.get(i);

		}
		this.orgodata = mandata;
		DefaultTableModel refresh = new DefaultTableModel(this.orgodata, this.orgodisplays);
//System.out.println(this.orgodata[0][0]);
		todisporgo.setModel(refresh);
		
		
		//implement once that view is fixed
		
//		ArrayList<String> thnam = this.mainviewservice.getTheoryName();
//		ArrayList<String> ths = this.mainviewservice.getTheorySummary();
//		ArrayList<String> covorg = this.mainviewservice.getCoveredOrganization();
//		ArrayList<String> orgdes = this.mainviewservice.getOrgDesc();
//		ArrayList<String> conspev = this.mainviewservice.getConspEvent();
//		ArrayList<String> evdesc = this.mainviewservice.getEventDescription();
//		ArrayList<String> evtype = this.mainviewservice.getEventType();
//		ArrayList<String> evdate = this.mainviewservice.getEventDate();
//
//
//		ArrayList<String[]> transform1 = new ArrayList<String[]>();
//		for (int k1 = 0; k1 < orgnam.size(); k1++) {
//			String[] toadd1 = new String[8];
//			toadd1[0] = thnam.get(k1);
//			toadd1[1] = ths.get(k1);
//			toadd1[2] = covorg.get(k1);
//			toadd1[3] = orgdes.get(k1);
//			toadd1[4] = conspev.get(k1);
//			toadd1[5] = evdesc.get(k1);
//			toadd1[6] = evtype.get(k1);
//			toadd1[7] = evdate.get(k1);
//
//			transform1.add(toadd1);
//
//		}
//		String[][] mandata1 = new String[transform1.size()][8];
//		this.maindata = new String[transform1.size()][8];
//		for (int i = 0; i < transform1.size(); i++) {
//
//			mandata1[i] = transform1.get(i);
//
//		}
//		this.maindata = mandata1;
//		DefaultTableModel refresh1 = new DefaultTableModel(this.maindata, this.maindisplays);
////System.out.println(this.orgodata[0][0]);
//		todispmain.setModel(refresh1);
		
		
		
		

		ArrayList<String> fnam = this.eventperpview.getFname(false,null);
		ArrayList<String> lnam = this.eventperpview.getLname(false,null);
		ArrayList<String> descc = this.eventperpview.getDesc(false,null);
		ArrayList<String> naam = this.eventperpview.getName(false,null);



	
		


		ArrayList<String[]> transform2 = new ArrayList<String[]>();
		for (int k1 = 0; k1 < naam.size(); k1++) {
			String[] toadd1 = new String[4];
			toadd1[0] = naam.get(k1);
			toadd1[1] = descc.get(k1);
			toadd1[2] = fnam.get(k1);
			toadd1[3] = lnam.get(k1);
		

			transform2.add(toadd1);

		}
		String[][] mandata2 = new String[transform2.size()][4];
		this.eperpdata = new String[transform2.size()][4];
		for (int i = 0; i < transform2.size(); i++) {

			mandata2[i] = transform2.get(i);

		}
		this.eperpdata = mandata2;
		DefaultTableModel refresh2 = new DefaultTableModel(this.eperpdata, this.eperpdisplays);
//System.out.println(this.orgodata[0][0]);
		todispeperp.setModel(refresh2);
		
		
		
		
		
		
		
		
		ArrayList<String> vietname = this.orgovicview.getName(false,null);
		ArrayList<String> fnamm = this.orgovicview.getFname(false,null);
		ArrayList<String> lnamm = this.orgovicview.getLname(false,null);

		
		



	
		


		ArrayList<String[]> transform3 = new ArrayList<String[]>();
		for (int k1 = 0; k1 < naam.size(); k1++) {
			String[] toadd1 = new String[3];
			toadd1[0] = vietname.get(k1);
			toadd1[1] = fnamm.get(k1);
			toadd1[2] = lnamm.get(k1);
			
		

			transform3.add(toadd1);
			System.out.println(toadd1[0]);

		}
		String[][] mandata3 = new String[transform3.size()][3];
		this.orvicdata = new String[transform3.size()][3];
		for (int i = 0; i < transform3.size(); i++) {

			mandata3[i] = transform3.get(i);

		}
		this.orvicdata = mandata3;
		DefaultTableModel refresh3 = new DefaultTableModel(this.orvicdata, this.orvicdisplays);
//System.out.println(this.orgodata[0][0]);
		todisporvic.setModel(refresh3);
		
		
		

	}
public void searchOrg(String date, String date2) {
		
		ArrayList<String> orgnam = this.orgoservice.getOrganizationName(true,date,date2);
		ArrayList<String> orgdesc = this.orgoservice.getOrganizationDesc(true,date,date2);
		ArrayList<String> orgdoe = this.orgoservice.getOrganizationDoe(true,date,date2);
		ArrayList<String> orgatt = this.orgoservice.getOrganizationatt(true,date,date2);
		

		ArrayList<String[]> transform = new ArrayList<String[]>();
		for (int k = 0; k < orgnam.size(); k++) {
			String[] toadd = new String[4];
			toadd[0] = orgnam.get(k);
			toadd[1] = orgdesc.get(k);
			toadd[2] = orgatt.get(k);
			toadd[3] = orgdoe.get(k);

			transform.add(toadd);

		}
		String[][] mandata = new String[transform.size()][4];
		this.orgodata = new String[transform.size()][4];
		for (int i = 0; i < transform.size(); i++) {

			mandata[i] = transform.get(i);

		}
		this.orgodata = mandata;
		DefaultTableModel refresh = new DefaultTableModel(this.orgodata, this.orgodisplays);
//System.out.println(this.orgodata[0][0]);
		todisporgo.setModel(refresh);
		
		
		//implement once that view is fixed
		
//		ArrayList<String> thnam = this.mainviewservice.getTheoryName();
//		ArrayList<String> ths = this.mainviewservice.getTheorySummary();
//		ArrayList<String> covorg = this.mainviewservice.getCoveredOrganization();
//		ArrayList<String> orgdes = this.mainviewservice.getOrgDesc();
//		ArrayList<String> conspev = this.mainviewservice.getConspEvent();
//		ArrayList<String> evdesc = this.mainviewservice.getEventDescription();
//		ArrayList<String> evtype = this.mainviewservice.getEventType();
//		ArrayList<String> evdate = this.mainviewservice.getEventDate();
//
//
//		ArrayList<String[]> transform1 = new ArrayList<String[]>();
//		for (int k1 = 0; k1 < orgnam.size(); k1++) {
//			String[] toadd1 = new String[8];
//			toadd1[0] = thnam.get(k1);
//			toadd1[1] = ths.get(k1);
//			toadd1[2] = covorg.get(k1);
//			toadd1[3] = orgdes.get(k1);
//			toadd1[4] = conspev.get(k1);
//			toadd1[5] = evdesc.get(k1);
//			toadd1[6] = evtype.get(k1);
//			toadd1[7] = evdate.get(k1);
//
//			transform1.add(toadd1);
//
//		}
//		String[][] mandata1 = new String[transform1.size()][8];
//		this.maindata = new String[transform1.size()][8];
//		for (int i = 0; i < transform1.size(); i++) {
//
//			mandata1[i] = transform1.get(i);
//
//		}
//		this.maindata = mandata1;
//		DefaultTableModel refresh1 = new DefaultTableModel(this.maindata, this.maindisplays);
////System.out.println(this.orgodata[0][0]);
//		todispmain.setModel(refresh1);
		
		
		
		

		ArrayList<String> fnam = this.eventperpview.getFname(false,null);
		ArrayList<String> lnam = this.eventperpview.getLname(false,null);
		ArrayList<String> descc = this.eventperpview.getDesc(false,null);
		ArrayList<String> naam = this.eventperpview.getName(false,null);



	
		


		ArrayList<String[]> transform2 = new ArrayList<String[]>();
		for (int k1 = 0; k1 < naam.size(); k1++) {
			String[] toadd1 = new String[4];
			toadd1[0] = naam.get(k1);
			toadd1[1] = descc.get(k1);
			toadd1[2] = fnam.get(k1);
			toadd1[3] = lnam.get(k1);
		

			transform2.add(toadd1);

		}
		String[][] mandata2 = new String[transform2.size()][4];
		this.eperpdata = new String[transform2.size()][4];
		for (int i = 0; i < transform2.size(); i++) {

			mandata2[i] = transform2.get(i);

		}
		this.eperpdata = mandata2;
		DefaultTableModel refresh2 = new DefaultTableModel(this.eperpdata, this.eperpdisplays);
//System.out.println(this.orgodata[0][0]);
		todispeperp.setModel(refresh2);
		
		
		
		
		
		
		
		
		ArrayList<String> vietname = this.orgovicview.getName(false,null);
		ArrayList<String> fnamm = this.orgovicview.getFname(false,null);
		ArrayList<String> lnamm = this.orgovicview.getLname(false,null);

		
		



	
		


		ArrayList<String[]> transform3 = new ArrayList<String[]>();
		for (int k1 = 0; k1 < naam.size(); k1++) {
			String[] toadd1 = new String[3];
			toadd1[0] = vietname.get(k1);
			toadd1[1] = fnamm.get(k1);
			toadd1[2] = lnamm.get(k1);
			
		

			transform3.add(toadd1);
			System.out.println(toadd1[0]);

		}
		String[][] mandata3 = new String[transform3.size()][3];
		this.orvicdata = new String[transform3.size()][3];
		for (int i = 0; i < transform3.size(); i++) {

			mandata3[i] = transform3.get(i);

		}
		this.orvicdata = mandata3;
		DefaultTableModel refresh3 = new DefaultTableModel(this.orvicdata, this.orvicdisplays);
//System.out.println(this.orgodata[0][0]);
		todisporvic.setModel(refresh3);
		
		
		

	}
public void eventPerpUpdate(String eventname) {
	
	ArrayList<String> orgnam = this.orgoservice.getOrganizationName(false,"","");
	ArrayList<String> orgdesc = this.orgoservice.getOrganizationDesc(false,"","");
	ArrayList<String> orgdoe = this.orgoservice.getOrganizationDoe(false,"","");
	ArrayList<String> orgatt = this.orgoservice.getOrganizationatt(false,"","");
	

	ArrayList<String[]> transform = new ArrayList<String[]>();
	for (int k = 0; k < orgnam.size(); k++) {
		String[] toadd = new String[4];
		toadd[0] = orgnam.get(k);
		toadd[1] = orgdesc.get(k);
		toadd[2] = orgatt.get(k);
		toadd[3] = orgdoe.get(k);

		transform.add(toadd);

	}
	String[][] mandata = new String[transform.size()][4];
	this.orgodata = new String[transform.size()][4];
	for (int i = 0; i < transform.size(); i++) {

		mandata[i] = transform.get(i);

	}
	this.orgodata = mandata;
	DefaultTableModel refresh = new DefaultTableModel(this.orgodata, this.orgodisplays);
//System.out.println(this.orgodata[0][0]);
	todisporgo.setModel(refresh);
	
	
	//implement once that view is fixed
	
//	ArrayList<String> thnam = this.mainviewservice.getTheoryName();
//	ArrayList<String> ths = this.mainviewservice.getTheorySummary();
//	ArrayList<String> covorg = this.mainviewservice.getCoveredOrganization();
//	ArrayList<String> orgdes = this.mainviewservice.getOrgDesc();
//	ArrayList<String> conspev = this.mainviewservice.getConspEvent();
//	ArrayList<String> evdesc = this.mainviewservice.getEventDescription();
//	ArrayList<String> evtype = this.mainviewservice.getEventType();
//	ArrayList<String> evdate = this.mainviewservice.getEventDate();
//
//
//	ArrayList<String[]> transform1 = new ArrayList<String[]>();
//	for (int k1 = 0; k1 < orgnam.size(); k1++) {
//		String[] toadd1 = new String[8];
//		toadd1[0] = thnam.get(k1);
//		toadd1[1] = ths.get(k1);
//		toadd1[2] = covorg.get(k1);
//		toadd1[3] = orgdes.get(k1);
//		toadd1[4] = conspev.get(k1);
//		toadd1[5] = evdesc.get(k1);
//		toadd1[6] = evtype.get(k1);
//		toadd1[7] = evdate.get(k1);
//
//		transform1.add(toadd1);
//
//	}
//	String[][] mandata1 = new String[transform1.size()][8];
//	this.maindata = new String[transform1.size()][8];
//	for (int i = 0; i < transform1.size(); i++) {
//
//		mandata1[i] = transform1.get(i);
//
//	}
//	this.maindata = mandata1;
//	DefaultTableModel refresh1 = new DefaultTableModel(this.maindata, this.maindisplays);
////System.out.println(this.orgodata[0][0]);
//	todispmain.setModel(refresh1);
	
	
	
	

	ArrayList<String> fnam = this.eventperpview.getFname(true, eventname);
	ArrayList<String> lnam = this.eventperpview.getLname(true, eventname);
	ArrayList<String> descc = this.eventperpview.getDesc(true, eventname);
	ArrayList<String> naam = this.eventperpview.getName(true, eventname);




	


	ArrayList<String[]> transform2 = new ArrayList<String[]>();
	for (int k1 = 0; k1 < naam.size(); k1++) {
		String[] toadd1 = new String[4];
		toadd1[0] = naam.get(k1);
		toadd1[1] = descc.get(k1);
		toadd1[2] = fnam.get(k1);
		toadd1[3] = lnam.get(k1);
	

		transform2.add(toadd1);

	}
	String[][] mandata2 = new String[transform2.size()][4];
	this.eperpdata = new String[transform2.size()][4];
	for (int i = 0; i < transform2.size(); i++) {

		mandata2[i] = transform2.get(i);

	}
	this.eperpdata = mandata2;
	DefaultTableModel refresh2 = new DefaultTableModel(this.eperpdata, this.eperpdisplays);
//System.out.println(this.orgodata[0][0]);
	todispeperp.setModel(refresh2);
	
	
	
	
	
	
	
	
	ArrayList<String> vietname = this.orgovicview.getName(false,null);
	ArrayList<String> fnamm = this.orgovicview.getFname(false,null);
	ArrayList<String> lnamm = this.orgovicview.getLname(false,null);

	
	




	


	ArrayList<String[]> transform3 = new ArrayList<String[]>();
	for (int k1 = 0; k1 < naam.size(); k1++) {
		String[] toadd1 = new String[3];
		toadd1[0] = vietname.get(k1);
		toadd1[1] = fnamm.get(k1);
		toadd1[2] = lnamm.get(k1);
		
	

		transform3.add(toadd1);
		System.out.println(toadd1[0]);

	}
	String[][] mandata3 = new String[transform3.size()][3];
	this.orvicdata = new String[transform3.size()][3];
	for (int i = 0; i < transform3.size(); i++) {

		mandata3[i] = transform3.get(i);

	}
	this.orvicdata = mandata3;
	DefaultTableModel refresh3 = new DefaultTableModel(this.orvicdata, this.orvicdisplays);
//System.out.println(this.orgodata[0][0]);
	todisporvic.setModel(refresh3);
	
	
	

}
public void searchOrgVic(String orgname) {
	
	ArrayList<String> orgnam = this.orgoservice.getOrganizationName(false,"","");
	ArrayList<String> orgdesc = this.orgoservice.getOrganizationDesc(false,"","");
	ArrayList<String> orgdoe = this.orgoservice.getOrganizationDoe(false,"","");
	ArrayList<String> orgatt = this.orgoservice.getOrganizationatt(false,"","");
	

	ArrayList<String[]> transform = new ArrayList<String[]>();
	for (int k = 0; k < orgnam.size(); k++) {
		String[] toadd = new String[4];
		toadd[0] = orgnam.get(k);
		toadd[1] = orgdesc.get(k);
		toadd[2] = orgatt.get(k);
		toadd[3] = orgdoe.get(k);

		transform.add(toadd);

	}
	String[][] mandata = new String[transform.size()][4];
	this.orgodata = new String[transform.size()][4];
	for (int i = 0; i < transform.size(); i++) {

		mandata[i] = transform.get(i);

	}
	this.orgodata = mandata;
	DefaultTableModel refresh = new DefaultTableModel(this.orgodata, this.orgodisplays);
//System.out.println(this.orgodata[0][0]);
	todisporgo.setModel(refresh);
	
	
	//implement once that view is fixed
	
//	ArrayList<String> thnam = this.mainviewservice.getTheoryName();
//	ArrayList<String> ths = this.mainviewservice.getTheorySummary();
//	ArrayList<String> covorg = this.mainviewservice.getCoveredOrganization();
//	ArrayList<String> orgdes = this.mainviewservice.getOrgDesc();
//	ArrayList<String> conspev = this.mainviewservice.getConspEvent();
//	ArrayList<String> evdesc = this.mainviewservice.getEventDescription();
//	ArrayList<String> evtype = this.mainviewservice.getEventType();
//	ArrayList<String> evdate = this.mainviewservice.getEventDate();
//
//
//	ArrayList<String[]> transform1 = new ArrayList<String[]>();
//	for (int k1 = 0; k1 < orgnam.size(); k1++) {
//		String[] toadd1 = new String[8];
//		toadd1[0] = thnam.get(k1);
//		toadd1[1] = ths.get(k1);
//		toadd1[2] = covorg.get(k1);
//		toadd1[3] = orgdes.get(k1);
//		toadd1[4] = conspev.get(k1);
//		toadd1[5] = evdesc.get(k1);
//		toadd1[6] = evtype.get(k1);
//		toadd1[7] = evdate.get(k1);
//
//		transform1.add(toadd1);
//
//	}
//	String[][] mandata1 = new String[transform1.size()][8];
//	this.maindata = new String[transform1.size()][8];
//	for (int i = 0; i < transform1.size(); i++) {
//
//		mandata1[i] = transform1.get(i);
//
//	}
//	this.maindata = mandata1;
//	DefaultTableModel refresh1 = new DefaultTableModel(this.maindata, this.maindisplays);
////System.out.println(this.orgodata[0][0]);
//	todispmain.setModel(refresh1);
	
	
	
	

	ArrayList<String> fnam = this.eventperpview.getFname(false,null);
	ArrayList<String> lnam = this.eventperpview.getLname(false,null);
	ArrayList<String> descc = this.eventperpview.getDesc(false,null);
	ArrayList<String> naam = this.eventperpview.getName(false,null);




	


	ArrayList<String[]> transform2 = new ArrayList<String[]>();
	for (int k1 = 0; k1 < naam.size(); k1++) {
		String[] toadd1 = new String[4];
		toadd1[0] = naam.get(k1);
		toadd1[1] = descc.get(k1);
		toadd1[2] = fnam.get(k1);
		toadd1[3] = lnam.get(k1);
	

		transform2.add(toadd1);

	}
	String[][] mandata2 = new String[transform2.size()][4];
	this.eperpdata = new String[transform2.size()][4];
	for (int i = 0; i < transform2.size(); i++) {

		mandata2[i] = transform2.get(i);

	}
	this.eperpdata = mandata2;
	DefaultTableModel refresh2 = new DefaultTableModel(this.eperpdata, this.eperpdisplays);
//System.out.println(this.orgodata[0][0]);
	todispeperp.setModel(refresh2);
	
	
	
	
	
	
	
	
	ArrayList<String> vietname = this.orgovicview.getName(true,orgname);
	ArrayList<String> fnamm = this.orgovicview.getFname(true,orgname);
	ArrayList<String> lnamm = this.orgovicview.getLname(true,orgname);

	
	

if(vietname.size() == 0) {
	this.orvicdata = new String[0][0];
	DefaultTableModel refresh3 = new DefaultTableModel(this.orvicdata, this.orvicdisplays);
//System.out.println(this.orgodata[0][0]);
	todisporvic.setModel(refresh3);
	return;
	
}


	


	ArrayList<String[]> transform3 = new ArrayList<String[]>();
	for (int k1 = 0; k1 < naam.size(); k1++) {
		String[] toadd1 = new String[3];
		toadd1[0] = vietname.get(k1);
		toadd1[1] = fnamm.get(k1);
		toadd1[2] = lnamm.get(k1);
		
	

		transform3.add(toadd1);
//		System.out.println(toadd1[0]);

	}
	String[][] mandata3 = new String[transform3.size()][3];
	this.orvicdata = new String[transform3.size()][3];
	for (int i = 0; i < transform3.size(); i++) {

		mandata3[i] = transform3.get(i);

	}
	this.orvicdata = mandata3;
	DefaultTableModel refresh3 = new DefaultTableModel(this.orvicdata, this.orvicdisplays);
//System.out.println(this.orgodata[0][0]);
	todisporvic.setModel(refresh3);
	
	
	

}

	public MainGui() {
		con.connect("ForbiddenArchives20", "KillPoliticians69");

		// main Jpanel constrution
		GridBagLayout viewmenu = new GridBagLayout();
		GridBagLayout mainview = new GridBagLayout();
		GridBagLayout orgo = new GridBagLayout();
		GridBagLayout mainmenu = new GridBagLayout();
		GridBagLayout eventperp = new GridBagLayout();
		GridBagLayout orgovicview = new GridBagLayout();
		GridBagLayout modifymenu = new GridBagLayout();
		JPanel viewmain = new JPanel(mainview);
		JPanel viewer = new JPanel(viewmenu);
		JPanel orgoviwer = new JPanel(orgo);
		JPanel mainmenupanel = new JPanel(mainmenu);
		JPanel eventperppanel = new JPanel(eventperp);
		JPanel orgovicpanel = new JPanel(orgovicview);
		JPanel modifypanel = new JPanel(modifymenu);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		// main menu stuff
		
		JButton modifyviewbutton = new JButton("Modify menu");
		ActionListener contributer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "modifypanel");
			}
		};

		modifyviewbutton.addActionListener(contributer);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 3;

		mainmenupanel.add(modifyviewbutton, gbc);

		reinitializeData();
		JButton viewbutton = new JButton("View Menu");
		ActionListener mainviewer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "viewer");
			}
		};
		viewbutton.addActionListener(mainviewer);
		mainmenupanel.add(viewbutton);
		

//		JScrollPane todisporgo = new JScrollPane(todisp);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 4;
//		gbc.gridy = 3;
//		orgomain.add(todisporgo, gbc);
		//make back button
		JButton backButton = new JButton("Back");
		JButton backButton1 = new JButton("Back");
		JButton backButton2 = new JButton("Back");
		JButton backButton3 = new JButton("Back");
		JButton backButton4 = new JButton("Back");
		JButton backButton5 = new JButton("Back");
		JButton backButton6= new JButton("Back");
		JButton backButton7 = new JButton("Back");
		

		ActionListener backbutton = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "mainmenupanel");
			}
		};
		backButton.addActionListener(backbutton);
		backButton1.addActionListener(backbutton);
		backButton2.addActionListener(backbutton);
		backButton3.addActionListener(backbutton);
		backButton4.addActionListener(backbutton);
		backButton5.addActionListener(backbutton);
		backButton6.addActionListener(backbutton);
		backButton7.addActionListener(backbutton);
		
		
		//make viewer screen
		
		JButton showmainb = new JButton("Theory view");
		JButton showorgo = new JButton("Organization view");
		JButton showeperps = new JButton("Event Perpetrator view");
		JButton showevictims = new JButton("Show Event Victims view");
		ActionListener shomain = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "viewmain");
			}
		};
		ActionListener shorgo = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "orgoviwer");
			}
		};
		ActionListener shoeperps = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "eventperppanel");
			}
		};
		ActionListener shoevictims = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "orgovicpanel");
			}
		};
		showmainb.addActionListener(shomain);
		showorgo.addActionListener(shorgo);
		showeperps.addActionListener(shoeperps);
		showevictims.addActionListener(shoevictims);
		viewer.add(backButton7);
		viewer.add(showmainb);
		viewer.add(showeperps);
		viewer.add(showorgo);
		viewer.add(showevictims);
		
		
		
	
		
		// make orgo display screen
		
		JScrollPane todisporgopane = new JScrollPane(todisporgo);
		JButton searchorg = new JButton("Filter");
		JTextField date1 = new JTextField();
		JTextField date2 = new JTextField();
		JLabel datel = new JLabel("Est. >=");
		JLabel date2l = new  JLabel("Est. < ");
		
		
		ActionListener searchor = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String date1r = date1.getText();
				String date2r = date2.getText();
				
				searchOrg(date1r,date2r);
//				todisp.revalidate();
//				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();

			}
		};
		searchorg.addActionListener(searchor);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 3;
		orgoviwer.add(todisporgopane, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		orgoviwer.add(backButton5, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 0;
		orgoviwer.add(datel, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 0;
		orgoviwer.add(date1, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 1;
		orgoviwer.add(date2l, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 1;
		orgoviwer.add(date2, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 2;
		orgoviwer.add(searchorg, gbc);
		
		// make main viewer
		JButton mainsearch = new JButton("Search Theory");
		JLabel mainlabel = new JLabel("Theory name:");
		JTextField mainse = new JTextField();
		JScrollPane todispmainviewer = new JScrollPane(todispmain);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		viewmain.add(backButton1, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 3;
		viewmain.add(todispmainviewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 0;
		viewmain.add(mainlabel, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 0;
		viewmain.add(mainse, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 1;
		viewmain.add(mainsearch, gbc);
		
		
		//make eventperp table
		
		JScrollPane eperpviewer = new JScrollPane(todispeperp);
		JButton eventsearch = new JButton("Search Event");
		JLabel eventlabel = new JLabel("Event name:");
		JTextField eventse = new JTextField();
		ActionListener searcheperp = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String eventname = eventse.getText();
				
				eventPerpUpdate(eventname);
//				todisp.revalidate();
//				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();

			}
		};
		eventsearch.addActionListener(searcheperp);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 3;
		eventperppanel.add(eperpviewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		eventperppanel.add(backButton2, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 0;
		eventperppanel.add(eventlabel, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 0;
		eventperppanel.add(eventse, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 1;
		eventperppanel.add(eventsearch, gbc);
		
		
		//make orvic tablfm
		JButton orgosearch = new JButton("Search Organization");
		JLabel orgolabel = new JLabel("Organization name:");
		JTextField orgose = new JTextField();
		JScrollPane orgovicviewer = new JScrollPane(todisporvic);
		ActionListener orgocisearch = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String orgname = orgose.getText();
				
				searchOrgVic(orgname);
//				todisp.revalidate();
//				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();

			}
		};
		orgosearch.addActionListener(orgocisearch);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 3;
		orgovicpanel.add(orgovicviewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		orgovicpanel.add(backButton3, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 0;
		orgovicpanel.add(orgolabel, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 0;
		orgovicpanel.add(orgose, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 1;
		orgovicpanel.add(orgosearch, gbc);
		
		
		
		


//		mainframe.add(orgomain);
//		layout.addLayoutComponent(orgomain, "orgoview");

		// Contribute organizatodifyion panel construction
		
		//Main contributor table
		
//		JPanel modifier = new JPanel(new GridBagLayout());

//		String[] displaysorgoedit = new String[] { "ID","Organization name", "Description", "Attributes", "Date of Establishment" };
		JLabel orgo0 = new JLabel("Organization");
		JLabel orgo1 = new JLabel("ID");
		JLabel orgo2 = new JLabel("Organization Name");
		JLabel orgo3 = new JLabel("Description");
		JLabel orgo4 = new JLabel("Attributes");
		JLabel orgo5 = new JLabel("Date Of Establishment");
		JLabel the0 = new JLabel("Theory");
		JLabel the1 = new JLabel("ID");
		JLabel the2 = new JLabel("Theory Title");
		JLabel the3 = new JLabel("Theory Summary");
		JLabel ev0 = new JLabel("Event");
		JLabel ev1 = new JLabel("ID");
		JLabel ev2 = new JLabel("Event Type");
		JLabel ev3 = new JLabel("Date of Occurence");
		JLabel ev4 = new JLabel("Description");
		JLabel ev5 = new JLabel("PerpID");
		JLabel ev6 = new JLabel("Name");
		JLabel per0 = new JLabel("Person");
		JLabel per1 = new JLabel("ID");
		JLabel per2 = new JLabel("Date Of Birth");
		JLabel per3 = new JLabel("First Name");
		JLabel per4 = new JLabel("Last Name");
		


		
		JTextField orgo1f = new JTextField();
		JTextField orgo2f = new JTextField();
		JTextField orgo3f = new JTextField();
		JTextField orgo4f = new JTextField();
		JTextField orgo5f = new JTextField();
		JTextField the1f = new JTextField();
		JTextField the2f = new JTextField();
		JTextField the3f = new JTextField();
		JTextField ev1f = new JTextField();
		JTextField ev2f = new JTextField();
		JTextField ev3f = new JTextField();
		JTextField ev4f = new JTextField();
		JTextField ev5f = new JTextField();
		JTextField ev6f = new JTextField();
		JTextField per1f = new JTextField();
		JTextField per2f = new JTextField();
		JTextField per3f = new JTextField();
		JTextField per4f = new JTextField();
		
		
		
		
		
		


		
	


//		mainframe.add(editorgo);
//		layout.addLayoutComponent(editorgo, "editorgo");

		// javagraphics bullshit

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		modifypanel.add(backButton4, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		modifypanel.add(orgo0, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		modifypanel.add(orgo1, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 1;
		modifypanel.add(orgo2, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 1;
		modifypanel.add(orgo3, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 1;
		modifypanel.add(orgo4, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 1;
		modifypanel.add(orgo5, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		modifypanel.add(orgo1f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 2;
		modifypanel.add(orgo2f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 2;
		modifypanel.add(orgo3f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 2;
		modifypanel.add(orgo4f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 2;
		modifypanel.add(orgo5f, gbc);
		
		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		modifypanel.add(the0, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 3;
		modifypanel.add(the1, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 3;
		modifypanel.add(the2, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 3;
		modifypanel.add(the3, gbc);
		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 4;
		modifypanel.add(the1f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 4;
		modifypanel.add(the2f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 4;
		modifypanel.add(the3f, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 5;
		modifypanel.add(ev0, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 5;
		modifypanel.add(ev1, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 5;
		modifypanel.add(ev2, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 5;
		modifypanel.add(ev3, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 5;
		modifypanel.add(ev4, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 5;
		modifypanel.add(ev5, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 5;
		modifypanel.add(ev6, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 6;
		modifypanel.add(ev1f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 6;
		modifypanel.add(ev2f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 6;
		modifypanel.add(ev3f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 6;
		modifypanel.add(ev4f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 6;
		modifypanel.add(ev5f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 6;
		modifypanel.add(ev6f, gbc);

		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 7;
		modifypanel.add(per0, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 7;
		modifypanel.add(per1, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 7;
		modifypanel.add(per2, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 7;
		modifypanel.add(per3, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 7;
		modifypanel.add(per4, gbc);
		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 8;
		modifypanel.add(per1f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 8;
		modifypanel.add(per2f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 8;
		modifypanel.add(per3f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 8;
		modifypanel.add(per4f, gbc);


System.out.println("reached1");



		
		
		
		ActionListener addorgob = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String desc = orgo3f.getText();
				String att = orgo4f.getText();
				String date = orgo5f.getText();
				String name = orgo2f.getText();
				orgoservice.addOrgo(desc, att, date, name);
				reinitializeData();
//				System.out.println("reached");
//				System.out.println(todisp.getModel().getValueAt(0, 0));
//				DefaultTableModel torefresh = (DefaultTableModel)todisp.getModel();
//				torefresh.fireTableDataChanged();
//				todisp.revalidate();
//				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();
//				todisp.repaint();

			}

		
		};

		ActionListener delorgob = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(orgo1f.getText());
				orgoservice.delOrgo(id);
				reinitializeData();
//				todisp.revalidate();
//				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();

			}
		};
		ActionListener uporgob = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(orgo1f.getText());
				String desc = orgo3f.getText();
				String att = orgo4f.getText();
				String date = orgo5f.getText();
				String name = orgo2f.getText();
				orgoservice.upOrgo(id, desc, att, date, name);
				reinitializeData();
//				todisp.revalidate();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();

			}
		};
		
		
		ActionListener addtheb = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String title = the2f.getText();
				String summary = the3f.getText();
				
				theoryservice.addTheory(title, summary);
				reinitializeData();
//				System.out.println("reached");
//				System.out.println(todisp.getModel().getValueAt(0, 0));
//				DefaultTableModel torefresh = (DefaultTableModel)todisp.getModel();
//				torefresh.fireTableDataChanged();
//				todisp.revalidate();
//				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();
//				todisp.repaint();

			}

		
		};

		ActionListener deltheb = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(the1f.getText());
				theoryservice.delTheory(id);
				reinitializeData();
//				todisp.revalidate();
//				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();

			}
		};
		ActionListener uptheb = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(the1f.getText());
				String title = the2f.getSelectedText();
				String summary = the3f.getSelectedText();
				theoryservice.upTheory(id, title, summary);
				reinitializeData();
//				todisp.revalidate();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();

			}
		};

		ActionListener addevb = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String type = ev2f.getText();
				String doo = ev3f.getText();
				String desc = ev4f.getText();
				String perpid = ev5f.getText();
				String name = ev6f.getText();
				
				eventservice.addEvent(type, doo, desc, perpid, name);
				reinitializeData();
//				System.out.println("reached");
//				System.out.println(todisp.getModel().getValueAt(0, 0));
//				DefaultTableModel torefresh = (DefaultTableModel)todisp.getModel();
//				torefresh.fireTableDataChanged();
//				todisp.revalidate();
//				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();
//				todisp.repaint();

			}

		
		};

		ActionListener delevb = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(ev1f.getText());
				eventservice.delEvent(id);
				reinitializeData();
//				todisp.revalidate();
//				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();

			}
		};
		ActionListener upevb = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(the1f.getText());
				String type = ev2f.getText();
				String doo = ev3f.getText();
				String desc = ev4f.getText();
				String perpid = ev5f.getText();
				String name = ev6f.getText();
				String title = the2f.getSelectedText();
				String summary = the3f.getSelectedText();
				eventservice.upEvent(id, type, doo, desc, perpid, name);
				reinitializeData();
//				todisp.revalidate();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();

			}
		};
		
		
		
		JButton delorgo = new JButton("Delete Organization");
		JButton uporgo = new JButton("Update Organization");
		JButton addorgo = new JButton("Add Organization");
		JButton delthe = new JButton("Delete Theory");
		JButton upthe = new JButton("Update Theory");
		JButton addthe = new JButton("Add Theory");
		JButton delper = new JButton("Delete Person");
		JButton upper = new JButton("Update Person");
		JButton addper = new JButton("Add Person");
		JButton delev = new JButton("Delete Event");
		JButton upev = new JButton("Update Event");
		JButton addev = new JButton("Add Event");
		
		
		
		
		
		
		delorgo.addActionListener(delorgob);
		uporgo.addActionListener(uporgob);
		addorgo.addActionListener(addorgob);
		delthe.addActionListener(deltheb);
		addthe.addActionListener(addtheb);
		upthe.addActionListener(uptheb); 
		delev.addActionListener(delevb);
		addev.addActionListener(addevb);
		upev.addActionListener(upevb);

		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 9;
		modifypanel.add(delorgo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 9;
		modifypanel.add(uporgo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 9;
		modifypanel.add(addorgo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 10;
		modifypanel.add(delthe, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 10;
		modifypanel.add(upthe, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 10;
		modifypanel.add(addthe, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 11;
		modifypanel.add(delper, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 11;
		modifypanel.add(upper, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 11;
		modifypanel.add(addper, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 12;
		modifypanel.add(delev, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 12;
		modifypanel.add(upev, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 12;
		modifypanel.add(addev, gbc);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		layout.addLayoutComponent(mainmenupanel, "mainmenupanel");
		mainframe.add(mainmenupanel);
		mainframe.add(viewmain);
		layout.addLayoutComponent(viewmain, "viewmain");
		mainframe.add(viewer);
		layout.addLayoutComponent(viewer, "viewer");
		mainframe.add(orgoviwer);
		layout.addLayoutComponent(orgoviwer, "orgoviwer");
		mainframe.add(eventperppanel);
		layout.addLayoutComponent(eventperppanel, "eventperppanel");
		mainframe.add(orgovicpanel);
		layout.addLayoutComponent(orgovicpanel, "orgovicpanel");
		mainframe.add(modifypanel);
		layout.addLayoutComponent(modifypanel, "modifypanel");
		
		System.out.println("reached2");

		fullframe.add(mainframe);
		fullframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fullframe.setLocationByPlatform(true);
		fullframe.pack();
		fullframe.setVisible(true);
		
		System.out.println("reached3");

	}

}
