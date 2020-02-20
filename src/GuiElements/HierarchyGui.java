package GuiElements;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import service.HierarchyService;
import service.OrganizationVictimsView;
import service.TheoryService;

public class HierarchyGui {
	CardLayout layout;
	GridBagConstraints gbc;
	JPanel mainframe;
	JFrame fullframe;
	JTable orghierTable;
	HierarchyService hierservice;
	
	public HierarchyGui(CardLayout layout, GridBagConstraints gbc, JPanel mainframe, JFrame fullframe, HierarchyService hierservice) {
		// make main viewer
		this.layout = layout;
		this.gbc = gbc;
		this.mainframe = mainframe;
		this.fullframe = fullframe;
		this.hierservice = hierservice; 
		
		Vector<Vector<Object>> data = this.hierservice.getValues(null);
		try {
			this.orghierTable = new JTable(this.hierservice.buildTableModel(data, this.hierservice.jtableCols));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadTable(String ID) {
		Vector<Vector<Object>> data = this.hierservice.getValues(ID);
		DefaultTableModel model = (DefaultTableModel) this.orghierTable.getModel();
		try {
			model = hierservice.buildTableModel(data, this.hierservice.jtableCols);

		} catch (SQLException e) {
			// TODO Auto-generated catch blocky33
			e.printStackTrace();
		} // for example
		this.orghierTable.setModel(model);

		model.fireTableDataChanged();
		
	}
	
	public JPanel getOrgVicGui() {
		// make orvic table

		GridBagLayout orghiergrid = new GridBagLayout();
		JScrollPane orghierviewer = new JScrollPane(orghierTable);
		JButton backViewer = new JButton("Back");
		JPanel orgohierpanel = new JPanel(orghiergrid);

		ActionListener backToView = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "orgoviewer");
			}
		};
		backViewer.addActionListener(backToView);
		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 3;
		orgohierpanel.add(orghierviewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		orgohierpanel.add(backViewer, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		orgohierpanel.add(backViewer, gbc);
		
		return orgohierpanel;		
	}


}
