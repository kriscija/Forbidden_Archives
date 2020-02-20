package GuiElements;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import service.OrganizationVictimsView;
import service.OrgoService;

public class OrgoGui {
	CardLayout layout;
	GridBagConstraints gbc;
	JPanel mainframe;
	JFrame fullframe;
	//OrganizationVictimsView orgovicview;
	OrgoService orgoservice;
	JScrollPane todisporgopane;
	OrgVicGui ovg;
	HierarchyGui hg;
	
	JTable orgoTable;

	public OrgoGui(CardLayout layout, GridBagConstraints gbc, JPanel mainframe, JFrame fullframe, HierarchyGui hg, OrgVicGui ovg, OrgoService orgoservice) {
		this.layout = layout;
		this.gbc = gbc;
		this.mainframe = mainframe;
		this.fullframe = fullframe;
		this.hg = hg;
		this.ovg = ovg;
		this.orgoservice = orgoservice;
		
		Vector<Vector<Object>> data = this.orgoservice.getValues(null, null);
		try {
			this.orgoTable = new JTable(this.orgoservice.buildTableModel(data, this.orgoservice.jtableCols));
			this.orgoTable.removeColumn(this.orgoTable.getColumnModel().getColumn(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadTable(String dateFrom, String dateTo) {
		Vector<Vector<Object>> data = this.orgoservice.getValues(dateFrom, dateTo);
		DefaultTableModel model = (DefaultTableModel) this.orgoTable.getModel();
		try {
			model = orgoservice.buildTableModel(data, this.orgoservice.jtableCols);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // for example
		this.orgoTable.setModel(model);
		this.orgoTable.removeColumn(this.orgoTable.getColumnModel().getColumn(0));
		model.fireTableDataChanged();
	}
	
	public JPanel getOrgoGui() {
		this.loadTable(null, null);
		JLabel datel = new JLabel("Est. >=");
		JLabel date2l = new JLabel("Est. < ");
		JButton backViewer = new JButton("Back");
		JButton seeVictims = new JButton("See Victims");
		JButton seeHierarchy = new JButton("See Hierarchy");
		GridBagLayout orgo = new GridBagLayout();
		JPanel orgoviewer = new JPanel(orgo);
		JButton searchorg = new JButton("Filter");
		JTextField date1 = new JTextField();
		JTextField date2 = new JTextField();
		this.todisporgopane = new JScrollPane(orgoTable);
		
		
		// org search
		ActionListener filterorgAL = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String date1r = date1.getText();
				String date2r = date2.getText();
				if(date1r.equals("")) {
					date1r = null;
				}
				if(date2r.equals("")) {
					date2r = null;
				}
				loadTable(date1r, date2r);
			}
		};
		
		ActionListener seeHier = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = orgoTable.getSelectedRow();
				if(row == -1) {
					return;
				}
				String ID = String.valueOf(orgoTable.getModel().getValueAt(row, 0));
				System.out.println(ID);
				hg.loadTable(ID);
				layout.show(mainframe, "hierpanel");
			}
		};
		seeHierarchy.addActionListener(seeHier);

		searchorg.addActionListener(filterorgAL);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 0;
		orgoviewer.add(date1, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 1;
		orgoviewer.add(date2, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 2;
		orgoviewer.add(searchorg, gbc);
		
		
		//Create, Update, and Delete
		JButton addOrgo = new JButton("Add");
		JButton deleteOrgo = new JButton("Delete");
		JButton modifyOrgo = new JButton("Modify");
		
		ActionListener backToView = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "viewer");
			}
		};
		backViewer.addActionListener(backToView);
		
		ActionListener addOrgoAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrgoModifyGui omg = new OrgoModifyGui();
				if((omg.data[0] != null) && (!omg.data[0].equals(""))) {
					orgoservice.add(omg.data);
				}
				loadTable(null, null);
			}
		};
		addOrgo.addActionListener(addOrgoAL);
		
		ActionListener deleteOrgoAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = orgoTable.getSelectedRow();
				if(row == -1) {
					return;
				}
				int ID = (int) orgoTable.getModel().getValueAt(row, 0);
				orgoservice.delete(ID);
				loadTable(null, null);
			}
		};
		deleteOrgo.addActionListener(deleteOrgoAL);

		ActionListener modifyOrgoAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = orgoTable.getSelectedRow();
				if(row == -1) {
					return;
				}
				String ID = String.valueOf(orgoTable.getModel().getValueAt(row, 0));
				String desc = (String) orgoTable.getModel().getValueAt(row, 2);		//desc
				String att = (String) orgoTable.getModel().getValueAt(row, 3);		//att
				Date date = (Date) orgoTable.getModel().getValueAt(row, 4);	//date
				String name = (String) orgoTable.getModel().getValueAt(row, 1);		//name
				OrgoModifyGui omg = new OrgoModifyGui(desc, att, date, name);
				String[] data = new String[] {ID, omg.data[0], omg.data[1], omg.data[2], omg.data[3]};
				orgoservice.update(data);
				loadTable(null, null);
			}
		};
		modifyOrgo.addActionListener(modifyOrgoAL);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 3;
		orgoviewer.add(todisporgopane, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		orgoviewer.add(backViewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 0;
		orgoviewer.add(datel, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 1;
		orgoviewer.add(date2l, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 4;
		orgoviewer.add(addOrgo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 5;
		orgoviewer.add(deleteOrgo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 6;
		orgoviewer.add(modifyOrgo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 3;
		orgoviewer.add(seeVictims, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 3;
		orgoviewer.add(seeHierarchy, gbc);
			

		return orgoviewer;
	}
}
