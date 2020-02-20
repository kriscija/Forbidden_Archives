package GuiElements;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import service.EventPerpetratorsService;
import service.EventService;
import service.EventVictimsService;
import service.HierarchyService;
import service.InvolvesService;
import service.MainViewService;
import service.OrgoService;
import service.POIService;
import service.OrganizationVictimsView;
import service.TheoryService;
import service.TruthSeekerService;
import service.UserService;
import service.VictimsService;

public class MainGui {
	private JFrame fullframe = new JFrame("Forbidden Archives");
	private DatabaseConnectionService con = new DatabaseConnectionService("golem.csse.rose-hulman.edu",
			"ForbiddenArchives");
	public OrgoService orgoservice = new OrgoService(con);
	public UserService userservice = new UserService(con);
	public TheoryService theoryservice = new TheoryService(con);
	public EventService eventservice = new EventService(con);
	public MainViewService mainviewservice = new MainViewService(con);
	public EventPerpView eventperpview = new EventPerpView(con);
	public OrganizationVictimsView orgovicviewservice = new OrganizationVictimsView(con);
	public EventPerpetratorsService eventperpservice = new EventPerpetratorsService(con);
	public HierarchyService heirservice = new HierarchyService(con);
	public POIService poiservice = new POIService(con);
	public InvolvesService involvesservice = new InvolvesService(con);
	public VictimsService victimsservice = new VictimsService(con);
	public EventVictimsService eventvictimsservice = new EventVictimsService(con);
	public TruthSeekerService truthseekerservice = new TruthSeekerService(con);
	
	//
	final CardLayout layout = new CardLayout();
	final JPanel mainframe = new JPanel(layout);
	
	JFileChooser chooser;
	
	public MainGui() {

		con.connect("ForbiddenArchives20", "KillPoliticians69");
		fullframe.setPreferredSize(new Dimension(1000, 800));
		
		GridBagLayout mainmenu = new GridBagLayout();
		GridBagLayout orgovicview = new GridBagLayout();
		GridBagLayout modifymenu = new GridBagLayout();
		JPanel mainviewer;
		JPanel theoryviewer;
		JPanel viewer;
		JPanel orgoviewer;
		JPanel mainmenupanel = new JPanel(mainmenu);
		JPanel eventperppanel;
		JPanel eventviewer;
		JPanel orgovicpanel;
		JPanel eventvictimpanel;
		JPanel hierpanel;

		
		mainframe.setPreferredSize(new Dimension(1000, 800));
		
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


		
		JButton importbutton = new JButton("Select Directory with files for import:");
		ActionListener importAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("Select import directory: ");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);   
			    
			    String directory;
			    if (chooser.showOpenDialog(mainmenupanel) == JFileChooser.APPROVE_OPTION) { 
			    	directory = chooser.getSelectedFile().getPath();
			    	System.out.println(directory);
			      
			    }
			    else {
			      System.out.println("No Selection ");
			      return;
			    }
			    
			    orgoservice.importCSV(directory+"/Organizations.csv");
			    theoryservice.importCSV(directory+"/Theories.csv");
			    eventservice.importCSV(directory+"/Event.csv");
			    poiservice.importCSV(directory+"/POI.csv");
			    eventperpservice.importCSV(directory+"/EventPerpetrators.csv");
			    heirservice.importCSV(directory+"/Hierarchy.csv");
			    involvesservice.importCSV(directory+"/Involves.csv");
			    victimsservice.importCSV(directory+"/Victims.csv");
			    eventvictimsservice.importCSV(directory+"/EventVictims.csv");
			    truthseekerservice.importCSV(directory+"/TruthSeeker.csv");
			    

			    
			}
		};
		
		importbutton.addActionListener(importAL);
		mainmenupanel.add(importbutton);
		

		// make viewer screen
		ViewerGui vg = new ViewerGui(layout, mainframe);
		viewer = vg.getViewerGui();

		//make orgvic table
		OrgVicGui ovg = new OrgVicGui(layout, gbc, mainframe, fullframe, orgovicviewservice);
		orgovicpanel = ovg.getOrgVicGui();
		
		//make heir table
		HierarchyGui hg = new HierarchyGui(layout, gbc, mainframe, fullframe, heirservice);
		hierpanel = hg.getOrgVicGui();
		
		// make orgo display screen
		OrgoGui og = new OrgoGui(layout, gbc, mainframe, fullframe, hg, ovg, orgoservice);
		orgoviewer = og.getOrgoGui();

		//make main view display screen
		MainViewGui mvg = new MainViewGui(layout, gbc, mainframe, fullframe, mainviewservice);
		mainviewer = mvg.getMainViewGui();
		
		//make theory view screen
		TheoryViewerGui tvg = new TheoryViewerGui(layout, gbc, mainframe, fullframe, mvg, theoryservice);
		theoryviewer = tvg.getTheoryViewGui();

		
		// make eventperp table
		EventPerpGui epg = new EventPerpGui(layout, gbc, mainframe, fullframe, eventperpview);
		eventperppanel = epg.getEventPerpGui();

		//make eventvictim table
		EventVictimGui evg = new EventVictimGui(layout, gbc, mainframe, fullframe, eventvictimsservice);
		eventvictimpanel = evg.getEventVictimGui();
		
		//make event table
		EventGui eg = new EventGui(layout, gbc, mainframe, fullframe, evg, epg, orgoservice, eventservice);
		eventviewer = eg.getEventGui();
		

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
		mainframe.add(eventviewer);
		layout.addLayoutComponent(eventviewer, "eventviewer");
		mainframe.add(eventperppanel);
		layout.addLayoutComponent(eventperppanel, "eventperppanel");
		mainframe.add(eventvictimpanel);
		layout.addLayoutComponent(eventvictimpanel, "eventvictimspanel");
		mainframe.add(orgovicpanel);
		layout.addLayoutComponent(orgovicpanel, "orgovicpanel");
		mainframe.add(hierpanel);
		layout.addLayoutComponent(hierpanel, "hierpanel");
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
