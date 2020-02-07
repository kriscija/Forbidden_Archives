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
//	public EventService eventservice = new EventService(con);
//	public PersonService personservice = new PersonService(con);
//	public MainViewService mainviewservice = new MainViewService(con);
//	public EventPerpView eventperpview = new EventPerpView(con);
//	public OrganizationVictimsView orgovicview = new OrganizationVictimsView(con);
//	
	private String[] orgodisplays = { "Organization name", "Description", "Attributes", "Date of Establishment" };
	private String[][] orgodata;
	private JTable todisporgo = new JTable(orgodata, orgodisplays);
	private String[] maindisplays = { "Theory Name", "Theory Summary", "Covered Organization", "Organization Description","Conspiracy Event", "Event Description", "Event Type", "Event Date" };
	private String[][] maindata;
	private JTable todispmain = new JTable(maindata, maindisplays);
	private String[] eperpdisplays = { "Event name", "Description", "First Name", "Last Name" };
	private String[][] eperpdata;
	private JTable todispeperp = new JTable(eperpdata, eperpdisplays);
	private String[] orvicdisplays = { "Organization name", "victim firstname", "victim lastname" };
	private String[][] orvicdata;
	private JTable todisporvic = new JTable(orvicdata, orvicdisplays);
	final CardLayout layout = new CardLayout();
	final JPanel mainframe = new JPanel(layout);

	public void reinitializeData() {
		ArrayList<String> orgnam = this.orgoservice.getOrganizationName();
		ArrayList<String> orgdesc = this.orgoservice.getOrganizationDesc();
		ArrayList<String> orgdoe = this.orgoservice.getOrganizationDoe();
		ArrayList<String> orgatt = this.orgoservice.getOrganizationatt();
		

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
		mainframe.add(viewmain);
		layout.addLayoutComponent(viewmain, "viewmain");
		mainframe.add(viewer);
		layout.addLayoutComponent(viewer, "viewer");
		mainframe.add(orgoviwer);
		layout.addLayoutComponent(orgoviwer, "orgoviwer");
		mainframe.add(mainmenupanel);
		layout.addLayoutComponent(mainmenupanel, "mainmenupanel");
		mainframe.add(eventperppanel);
		layout.addLayoutComponent(eventperppanel, "eventperppanel");
		mainframe.add(orgovicpanel);
		layout.addLayoutComponent(orgovicpanel, "orgovicpanel");
		mainframe.add(modifypanel);
		layout.addLayoutComponent(modifypanel, "modifypanel");
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
		

//		JScrollPane todisporgo = new JScrollPane(todisp);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 4;
//		gbc.gridy = 3;
//		orgomain.add(todisporgo, gbc);
		//make back button
		JButton backButton = new JButton("Back");

		ActionListener backbutton = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "MainMenu");
			}
		};
		backButton.addActionListener(backbutton);


//		mainframe.add(orgomain);
//		layout.addLayoutComponent(orgomain, "orgoview");

		// Contribute organizatodifyion panel construction
		
		//Main contributor table
		
		JPanel modifier = new JPanel(new GridBagLayout());

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
		modifypanel.add(backButton, gbc);
		
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
				todisp.revalidate();
				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();
				todisp.repaint();

			}

		
		};

		ActionListener delorgob = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(orgo1f.getText());
				orgoservice.delOrgo(id);
				reinitializeData();
				todisp.revalidate();
				todisp.repaint();
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
				todisp.revalidate();
				todisp.repaint();
				mainframe.revalidate();
				mainframe.repaint();
				fullframe.revalidate();
				fullframe.repaint();

			}
		};

		JButton delorgo = new JButton("Delete");

		JButton uporgo = new JButton("Update");
		JButton addorgo = new JButton("Add");
		delorgo.addActionListener(delorgob);
		uporgo.addActionListener(uporgob);
		addorgo.addActionListener(addorgob);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		editorgo.add(delorgo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 3;
		editorgo.add(uporgo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 3;
		editorgo.add(addorgo, gbc);
		
		

		fullframe.add(mainframe);
		fullframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fullframe.setLocationByPlatform(true);
		fullframe.pack();
		fullframe.setVisible(true);

	}

}
