package GuiElements;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import db.DatabaseConnectionService;
import service.EventPerpView;
import service.EventService;
import service.MainViewService;
import service.OrgoService;
import service.OrganizationVictimsView;
import service.TheoryService;
import service.UserService;

public class MainGui {
	private JFrame fullframe = new JFrame("Forbidden Archives");
	private DatabaseConnectionService con = new DatabaseConnectionService("golem.csse.rose-hulman.edu",
			"ForbiddenArchives");
	public OrgoService orgoservice = new OrgoService(con);
	public UserService userservice = new UserService(con);
	public TheoryService theoryservice = new TheoryService(con);
	public EventService eventservice = new EventService(con);
	// public PersonService personservice = new PersonService(con);
	public MainViewService mainviewservice = new MainViewService(con);
	public EventPerpView eventperpview = new EventPerpView(con);
	public OrganizationVictimsView orgovicview = new OrganizationVictimsView(con);
	//
	private JTable todisporgo;
	private String[] maindisplays = { "Theory Name", "Theory Summary", "Covered Organization",
			"Organization Description", "Conspiracy Event", "Event Description", "Event Type", "Event Date" };
	private String[][] maindata = { { "A1", "A2", "A3", "A4", "A5", "a6", "a7", "a8" },
			{ "B1", "B2", "B3", "B4", "b5", "b6", "b7", "b8" } };
	
	private String[] eperpdisplays = { "Event name", "Description", "First Name", "Last Name" };
	private String[][] eperpdata = { { "A1", "A2", "A3", "A4" }, { "B1", "B2", "B3", "B4" } };
	private JTable todispeperp = new JTable(eperpdata, eperpdisplays);
	private String[] orvicdisplays = { "Organization name", "victim firstname", "victim lastname" };
	private String[][] orvicdata = { { "A1", "A2", "A3" }, { "B1", "B2", "B3" } };
	private JTable todisporvic = new JTable(orvicdata, orvicdisplays);
	final CardLayout layout = new CardLayout();
	final JPanel mainframe = new JPanel(layout);

	final FileDialog fileDialog = new FileDialog(fullframe, "Select file for import");

	public MainGui() {

		con.connect("ForbiddenArchives20", "KillPoliticians69");

		
		GridBagLayout mainmenu = new GridBagLayout();
		GridBagLayout orgovicview = new GridBagLayout();
		GridBagLayout modifymenu = new GridBagLayout();
		JPanel mainviewer;
		JPanel theoryviewer;
		JPanel viewer;
		JPanel orgoviewer;
		JPanel mainmenupanel = new JPanel(mainmenu);
		JPanel eventperppanel;
		JPanel orgovicpanel = new JPanel(orgovicview);
		JPanel modifypanel = new JPanel(modifymenu);

		GridBagConstraints gbc = new GridBagConstraints();

		JButton viewbutton = new JButton("Down The Rabbit Hole");
		ActionListener viewAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "viewer");
			}
		};
		viewbutton.addActionListener(viewAL);
		viewbutton.setPreferredSize(new Dimension(500,500));
		viewbutton.setFont(new Font("Ariel", Font.BOLD, 40));
		mainmenupanel.add(viewbutton);
		

		// make viewer screen
		ViewerGui vg = new ViewerGui(layout, mainframe);
		viewer = vg.getViewerGui();

		// make orgo display screen
		OrgoGui og = new OrgoGui(layout, gbc, mainframe, fullframe, orgoservice);
		orgoviewer = og.getOrgoGui();
		
		//make theory view screen
		TheoryViewerGui tvg = new TheoryViewerGui(layout, gbc, mainframe, fullframe, mainviewservice, theoryservice);
		theoryviewer = tvg.getTheoryViewGui();		
		
		//make main view display screen
		MainViewGui mvg = new MainViewGui(layout, gbc, mainframe, fullframe, mainviewservice);
		mainviewer = mvg.getMainViewGui();
	
		// make eventperp table
//		EventPerpGui epg = new EventPerpGui(layout, gbc, fullframe, mainframe, eventperpview);
//		eventperppanel = epg.getEventPerpGui();

//		
//		
//
//		// make orvic tablfm
//		JButton orgosearch = new JButton("Search Organization");
//		JLabel orgolabel = new JLabel("Organization name:");
//		JTextField orgose = new JTextField();
//		JScrollPane orgovicviewer = new JScrollPane(todisporvic);
//		ActionListener orgocisearch = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String orgname = orgose.getText();
//
//				searchOrgVic(orgname);
//				// todisp.revalidate();
//				// todisp.repaint();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//
//			}
//		};
//		orgosearch.addActionListener(orgocisearch);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 4;
//		gbc.gridy = 3;
//		orgovicpanel.add(orgovicviewer, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		orgovicpanel.add(backViewer3, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 5;
//		gbc.gridy = 0;
//		orgovicpanel.add(orgolabel, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 6;
//		gbc.gridy = 0;
//		orgovicpanel.add(orgose, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 6;
//		gbc.gridy = 1;
//		orgovicpanel.add(orgosearch, gbc);
//
//		// mainframe.add(orgomain);
//		// layout.addLayoutComponent(orgomain, "orgoview");
//
//		// Contribute organizatodifyion panel construction
//
//		// Main contributor table
//
//		// JPanel modifier = new JPanel(new GridBagLayout());
//
//		// String[] displaysorgoedit = new String[] { "ID","Organization name",
//		// "Description", "Attributes", "Date of Establishment" };
//		JLabel orgo0 = new JLabel("Organization");
//		JLabel orgo1 = new JLabel("ID");
//		JLabel orgo2 = new JLabel("Organization Name");
//		JLabel orgo3 = new JLabel("Description");
//		JLabel orgo4 = new JLabel("Attributes");
//		JLabel orgo5 = new JLabel("Date Of Establishment");
//		JLabel the0 = new JLabel("Theory");
//		JLabel the1 = new JLabel("ID");
//		JLabel the2 = new JLabel("Theory Title");
//		JLabel the3 = new JLabel("Theory Summary");
//		JLabel ev0 = new JLabel("Event");
//		JLabel ev1 = new JLabel("ID");
//		JLabel ev2 = new JLabel("Event Type");
//		JLabel ev3 = new JLabel("Date of Occurence");
//		JLabel ev4 = new JLabel("Description");
//		JLabel ev5 = new JLabel("PerpID");
//		JLabel ev6 = new JLabel("Name");
//		JLabel per0 = new JLabel("Person");
//		JLabel per1 = new JLabel("ID");
//		JLabel per2 = new JLabel("Date Of Birth");
//		JLabel per3 = new JLabel("First Name");
//		JLabel per4 = new JLabel("Last Name");
//
//		JTextField orgo1f = new JTextField();
//		JTextField orgo2f = new JTextField();
//		JTextField orgo3f = new JTextField();
//		JTextField orgo4f = new JTextField();
//		JTextField orgo5f = new JTextField();
//		JTextField the1f = new JTextField();
//		JTextField the2f = new JTextField();
//		JTextField the3f = new JTextField();
//		JTextField ev1f = new JTextField();
//		JTextField ev2f = new JTextField();
//		JTextField ev3f = new JTextField();
//		JTextField ev4f = new JTextField();
//		JTextField ev5f = new JTextField();
//		JTextField ev6f = new JTextField();
//		JTextField per1f = new JTextField();
//		JTextField per2f = new JTextField();
//		JTextField per3f = new JTextField();
//		JTextField per4f = new JTextField();
//
//		// mainframe.add(editorgo);
//		// layout.addLayoutComponent(editorgo, "editorgo");
//
//		// javagraphics bullshit
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		modifypanel.add(backMain3, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//		modifypanel.add(orgo0, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 1;
//		modifypanel.add(orgo1, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 1;
//		modifypanel.add(orgo2, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 3;
//		gbc.gridy = 1;
//		modifypanel.add(orgo3, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 4;
//		gbc.gridy = 1;
//		modifypanel.add(orgo4, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 5;
//		gbc.gridy = 1;
//		modifypanel.add(orgo5, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 2;
//		modifypanel.add(orgo1f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 2;
//		modifypanel.add(orgo2f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 3;
//		gbc.gridy = 2;
//		modifypanel.add(orgo3f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 4;
//		gbc.gridy = 2;
//		modifypanel.add(orgo4f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 5;
//		gbc.gridy = 2;
//		modifypanel.add(orgo5f, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 0;
//		gbc.gridy = 3;
//		modifypanel.add(the0, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 3;
//		modifypanel.add(the1, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 3;
//		modifypanel.add(the2, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 3;
//		gbc.gridy = 3;
//		modifypanel.add(the3, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 4;
//		modifypanel.add(the1f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 4;
//		modifypanel.add(the2f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 3;
//		gbc.gridy = 4;
//		modifypanel.add(the3f, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 0;
//		gbc.gridy = 5;
//		modifypanel.add(ev0, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 5;
//		modifypanel.add(ev1, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 5;
//		modifypanel.add(ev2, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 3;
//		gbc.gridy = 5;
//		modifypanel.add(ev3, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 4;
//		gbc.gridy = 5;
//		modifypanel.add(ev4, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 5;
//		gbc.gridy = 5;
//		modifypanel.add(ev5, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 6;
//		gbc.gridy = 5;
//		modifypanel.add(ev6, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 6;
//		modifypanel.add(ev1f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 6;
//		modifypanel.add(ev2f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 3;
//		gbc.gridy = 6;
//		modifypanel.add(ev3f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 4;
//		gbc.gridy = 6;
//		modifypanel.add(ev4f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 5;
//		gbc.gridy = 6;
//		modifypanel.add(ev5f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 6;
//		gbc.gridy = 6;
//		modifypanel.add(ev6f, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 0;
//		gbc.gridy = 7;
//		modifypanel.add(per0, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 7;
//		modifypanel.add(per1, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 7;
//		modifypanel.add(per2, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 3;
//		gbc.gridy = 7;
//		modifypanel.add(per3, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 4;
//		gbc.gridy = 7;
//		modifypanel.add(per4, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 8;
//		modifypanel.add(per1f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 8;
//		modifypanel.add(per2f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 3;
//		gbc.gridy = 8;
//		modifypanel.add(per3f, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 4;
//		gbc.gridy = 8;
//		modifypanel.add(per4f, gbc);
//
//		System.out.println("reached1");
//
//		ActionListener addorgob = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				List<String> data = new ArrayList<String>();
//				data.add(orgo3f.getText());
//				data.add(orgo4f.getText());
//				data.add(orgo5f.getText());
//				data.add(orgo2f.getText());
//				orgoservice.add(data.toArray(new String[0]));
//				reinitializeData();
//				// System.out.println("reached");
//				// System.out.println(todisp.getModel().getValueAt(0, 0));
//				// DefaultTableModel torefresh = (DefaultTableModel)todisp.getModel();
//				// torefresh.fireTableDataChanged();
//				// todisp.revalidate();
//				// todisp.repaint();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//				// todisp.repaint();
//
//			}
//
//		};
//
//		ActionListener delorgob = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int id = Integer.parseInt(orgo1f.getText());
//				orgoservice.delete(id);
//				reinitializeData();
//				// todisp.revalidate();
//				// todisp.repaint();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//
//			}
//		};
//		ActionListener uporgob = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				List<String> data = new ArrayList<String>();
//				data.add(orgo1f.getText());
//				data.add(orgo3f.getText());
//				data.add(orgo4f.getText());
//				data.add(orgo5f.getText());
//				data.add(orgo2f.getText());
//				orgoservice.update(data.toArray(new String[0]));
//				reinitializeData();
//				// todisp.revalidate();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//
//			}
//		};
//
//		ActionListener importorgob = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				fileDialog.setVisible(true);
//				String fileName = fileDialog.getDirectory() + fileDialog.getFile();
//				if (fileName.contentEquals("nullnull")) {
//					return;
//				}
//				System.out.println(fileName);
//
//				orgoservice.importCSV(fileName);
//				reinitializeData();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//			}
//		};
//
//		ActionListener addtheb = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				List<String> data = new ArrayList<String>();
//				data.add(the2f.getText());
//				data.add(the3f.getText());
//
//				theoryservice.add(data.toArray(new String[0]));
//				reinitializeData();
//				// System.out.println("reached");
//				// System.out.println(todisp.getModel().getValueAt(0, 0));
//				// DefaultTableModel torefresh = (DefaultTableModel)todisp.getModel();
//				// torefresh.fireTableDataChanged();
//				// todisp.revalidate();
//				// todisp.repaint();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//				// todisp.repaint();
//
//			}
//
//		};
//
//		ActionListener deltheb = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int id = Integer.parseInt(the1f.getText());
//				theoryservice.delete(id);
//				reinitializeData();
//				// todisp.revalidate();
//				// todisp.repaint();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//
//			}
//		};
//		ActionListener uptheb = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				List<String> data = new ArrayList<String>();
//				data.add(the1f.getText());
//				data.add(the2f.getText());
//				data.add(the3f.getText());
//
//				theoryservice.update(data.toArray(new String[0]));
//				reinitializeData();
//				// todisp.revalidate();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//
//			}
//		};
//
//		ActionListener importtheb = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				fileDialog.setVisible(true);
//				String fileName = fileDialog.getDirectory() + fileDialog.getFile();
//				if (fileName.contentEquals("nullnull")) {
//					return;
//				}
//				System.out.println(fileName);
//
//				theoryservice.importCSV(fileName);
//				reinitializeData();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//			}
//		};
//
//		ActionListener addevb = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				List<String> data = new ArrayList<String>();
//				data.add(ev2f.getText());
//				data.add(ev3f.getText());
//				data.add(ev4f.getText());
//				data.add(ev5f.getText());
//				data.add(ev6f.getText());
//
//				eventservice.add(data.toArray(new String[0]));
//				reinitializeData();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//				// todisp.repaint();
//
//			}
//
//		};
//
//		ActionListener delevb = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int id = Integer.parseInt(ev1f.getText());
//				eventservice.delete(id);
//				reinitializeData();
//				// todisp.revalidate();
//				// todisp.repaint();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//
//			}
//		};
//		ActionListener upevb = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				List<String> data = new ArrayList<String>();
//				data.add(ev1f.getText());
//				data.add(ev2f.getText());
//				data.add(ev3f.getText());
//				data.add(ev4f.getText());
//				data.add(ev5f.getText());
//
//				eventservice.update(data.toArray(new String[0]));
//				reinitializeData();
//				// todisp.revalidate();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//
//			}
//		};
//		ActionListener importevb = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				fileDialog.setVisible(true);
//				String fileName = fileDialog.getDirectory() + fileDialog.getFile();
//				if (fileName.contentEquals("nullnull")) {
//					return;
//				}
//				System.out.println(fileName);
//
//				eventservice.importCSV(fileName);
//				reinitializeData();
//				mainframe.revalidate();
//				mainframe.repaint();
//				fullframe.revalidate();
//				fullframe.repaint();
//
//			}
//		};
//
//		JButton delorgo = new JButton("Delete Organization");
//		JButton uporgo = new JButton("Update Organization");
//		JButton addorgo = new JButton("Add Organization");
//		JButton importorgo = new JButton("Import Organization");
//
//		JButton delthe = new JButton("Delete Theory");
//		JButton upthe = new JButton("Update Theory");
//		JButton addthe = new JButton("Add Theory");
//		JButton importthe = new JButton("Import Theory");
//
//		JButton delper = new JButton("Delete Person");
//		JButton upper = new JButton("Update Person");
//		JButton addper = new JButton("Add Person");
//		JButton importper = new JButton("Import Person");
//
//		JButton delev = new JButton("Delete Event");
//		JButton upev = new JButton("Update Event");
//		JButton addev = new JButton("Add Event");
//		JButton importev = new JButton("Import Event");
//
//		delorgo.addActionListener(delorgob);
//		uporgo.addActionListener(uporgob);
//		addorgo.addActionListener(addorgob);
//		importorgo.addActionListener(importorgob);
//
//		delthe.addActionListener(deltheb);
//		addthe.addActionListener(addtheb);
//		upthe.addActionListener(uptheb);
//		importthe.addActionListener(importtheb);
//
//		delev.addActionListener(delevb);
//		addev.addActionListener(addevb);
//		upev.addActionListener(upevb);
//		importev.addActionListener(importevb);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 0;
//		gbc.gridy = 9;
//		modifypanel.add(delorgo, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 9;
//		modifypanel.add(uporgo, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 9;
//		modifypanel.add(addorgo, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 3;
//		gbc.gridy = 9;
//		modifypanel.add(importorgo, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 0;
//		gbc.gridy = 10;
//		modifypanel.add(delthe, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 10;
//		modifypanel.add(upthe, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 10;
//		modifypanel.add(addthe, gbc);
//		gbc.gridx = 3;
//		gbc.gridy = 10;
//		modifypanel.add(importthe, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 0;
//		gbc.gridy = 11;
//		modifypanel.add(delper, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 11;
//		modifypanel.add(upper, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 11;
//		modifypanel.add(addper, gbc);
//		gbc.gridx = 3;
//		gbc.gridy = 11;
//		modifypanel.add(importper, gbc);
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 0;
//		gbc.gridy = 12;
//		modifypanel.add(delev, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 1;
//		gbc.gridy = 12;
//		modifypanel.add(upev, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 2;
//		gbc.gridy = 12;
//		modifypanel.add(addev, gbc);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridx = 3;
//		gbc.gridy = 12;
//		modifypanel.add(importev, gbc);
//
		layout.addLayoutComponent(mainmenupanel, "mainmenupanel");
		mainframe.add(mainmenupanel);
		mainframe.add(mainviewer);
		layout.addLayoutComponent(mainviewer, "mainviewer");
		mainframe.add(viewer);
		layout.addLayoutComponent(viewer, "viewer");
		mainframe.add(orgoviewer);
		layout.addLayoutComponent(orgoviewer, "orgoviewer");
		mainframe.add(theoryviewer);
		layout.addLayoutComponent(theoryviewer, "theoryviewer");
//		mainframe.add(eventperppanel);
//		layout.addLayoutComponent(eventperppanel, "eventperppanel");
//		mainframe.add(orgovicpanel);
//		layout.addLayoutComponent(orgovicpanel, "orgovicpanel");
//		mainframe.add(modifypanel);
//		layout.addLayoutComponent(modifypanel, "modifypanel");

		System.out.println("reached2");

		fullframe.add(mainframe);
		fullframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fullframe.setLocationByPlatform(true);
		fullframe.pack();
		fullframe.setVisible(true);

		System.out.println("reached3");

	}

}
