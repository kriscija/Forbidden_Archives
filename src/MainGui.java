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
	public OrgoService orgoservice = new OrgoService(con);
	public UserService userservice = new UserService(con);
	public TheoryService theoryservice = new TheoryService(con);
	public EventService eventservice = new EventService(con);
	public PersonService personservice = new PersonService(con);
	
	private String[] orgodisplays = { "Organization name", "Description", "Attributes", "Date of Establishment" };
	private String[][] orgodata;
	private JTable todisp = new JTable(orgodata, orgodisplays);
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
		todisp.setModel(refresh);

	}

	public MainGui() {
		con.connect("ForbiddenArchives20", "KillPoliticians69");

		// main Jpanel constrution
		GridBagLayout orgo = new GridBagLayout();
		JPanel orgomain = new JPanel(orgo);
		GridBagConstraints gbc = new GridBagConstraints();
		JButton switcher = new JButton("Modify Organization");
		ActionListener contributer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "editorgo");
			}
		};

		switcher.addActionListener(contributer);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;

		orgomain.add(switcher, gbc);

		reinitializeData();

		Object[][] maindata = new Object[][] { { "A1", "A2", "A3", "A4" }, { "B1", "B2", "B3", "B4" } };

		JScrollPane todisporgo = new JScrollPane(todisp);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		orgomain.add(todisporgo, gbc);

		mainframe.add(orgomain);
		layout.addLayoutComponent(orgomain, "orgoview");

		// Contribute organization panel construction
		JPanel editorgo = new JPanel(new GridBagLayout());

//		String[] displaysorgoedit = new String[] { "ID","Organization name", "Description", "Attributes", "Date of Establishment" };
		JLabel orgo1 = new JLabel("ID");
		JLabel orgo2 = new JLabel("Organization Name");
		JLabel orgo3 = new JLabel("Description");
		JLabel orgo4 = new JLabel("Attributes");
		JLabel orgo5 = new JLabel("Date Of Establishment");
		JTextField orgo1f = new JTextField();
		JTextField orgo2f = new JTextField();
		JTextField orgo3f = new JTextField();
		JTextField orgo4f = new JTextField();
		JTextField orgo5f = new JTextField();

		JButton organizationviewswitcher = new JButton("view Organizations");

		ActionListener orgoviewjumper = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "orgoview");
			}
		};

		organizationviewswitcher.addActionListener(orgoviewjumper);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		editorgo.add(organizationviewswitcher, gbc);

		JButton evictimviewswitcher = new JButton("view eventvictims");

		ActionListener evictimjumper = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "evictimview");
			}
		};

		evictimviewswitcher.addActionListener(evictimjumper);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		editorgo.add(evictimviewswitcher, gbc);

		JButton theoryviewswitcher = new JButton("view Theories");

		ActionListener theoryjumper = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "theoryview");
			}
		};

		organizationviewswitcher.addActionListener(theoryjumper);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 2;
		editorgo.add(theoryviewswitcher, gbc);

		JButton eventswitcher = new JButton("view Events");

		ActionListener eventjumper = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "eventview");
			}
		};

		eventswitcher.addActionListener(eventjumper);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 2;
		editorgo.add(eventswitcher, gbc);

		mainframe.add(editorgo);
		layout.addLayoutComponent(editorgo, "editorgo");

		// javagraphics bullshit

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		editorgo.add(orgo1, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		editorgo.add(orgo2, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 0;
		editorgo.add(orgo3, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 0;
		editorgo.add(orgo4, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 0;
		editorgo.add(orgo5, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		editorgo.add(orgo1f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		editorgo.add(orgo2f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 1;
		editorgo.add(orgo3f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 1;
		editorgo.add(orgo4f, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 1;
		editorgo.add(orgo5f, gbc);

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

			private void reinitializeDatatest() {
//				ArrayList<String> orgnam = this.orgoservice.getOrganizationName();
//				ArrayList<String> orgdesc = this.orgoservice.getOrganizationDesc();
//				ArrayList<String> orgdoe = this.orgoservice.getOrganizationDoe();
//				ArrayList<String> orgatt = this.orgoservice.getOrganizationatt();

				ArrayList<String> orgnam = new ArrayList<String>();
				orgnam.add("alt");
				orgnam.add("l");
				ArrayList<String> orgdesc = new ArrayList<String>();
				orgdesc.add("er");
				orgdesc.add("m");

				ArrayList<String> orgdoe = new ArrayList<String>();
				orgdoe.add("nat");
				orgdoe.add("a");

				ArrayList<String> orgatt = new ArrayList<String>();
				orgatt.add("test");
				orgatt.add("o");

				ArrayList<String[]> transform = new ArrayList<String[]>();
				for (int k = 0; k < orgnam.size(); k++) {
					String[] toadd = new String[4];
					toadd[0] = orgnam.get(k);
					toadd[1] = orgdesc.get(k);
					toadd[2] = orgdoe.get(k);
					toadd[3] = orgatt.get(k);
					transform.add(toadd);

				}
				String[][] mandata = new String[transform.size()][4];
				orgodata = new String[transform.size()][4];
				for (int i = 0; i < transform.size(); i++) {

					mandata[i] = transform.get(i);

				}
				orgodata = mandata;

				DefaultTableModel refresh = new DefaultTableModel(orgodata, orgodisplays);
//						System.out.println(orgodata[0][0]);
				todisp.setModel(refresh);

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
