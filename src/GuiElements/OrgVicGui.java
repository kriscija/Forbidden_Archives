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

import service.OrganizationVictimsView;
import service.TheoryService;

public class OrgVicGui {
	CardLayout layout;
	GridBagConstraints gbc;
	JPanel mainframe;
	JFrame fullframe;
	OrganizationVictimsView orgvicview;
	JTable orgvicTable;
	
	public OrgVicGui(CardLayout layout, GridBagConstraints gbc, JPanel mainframe, JFrame fullframe, OrganizationVictimsView orgvicview) {
		// make main viewer
		this.layout = layout;
		this.gbc = gbc;
		this.mainframe = mainframe;
		this.fullframe = fullframe;
		this.orgvicview = orgvicview;
		
		Vector<Vector<Object>> data = this.orgvicview.getValues(null);
		try {
			this.orgvicTable = new JTable(this.orgvicview.buildTableModel(data, this.orgvicview.jtableCols));
			this.orgvicTable.removeColumn(this.orgvicTable.getColumnModel().getColumn(0));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadTable(String ID) {
		Vector<Vector<Object>> data = this.orgvicview.getValues(ID);
		DefaultTableModel model = (DefaultTableModel) this.orgvicTable.getModel();
		try {
			model = orgvicview.buildTableModel(data, this.orgvicview.jtableCols);

		} catch (SQLException e) {
			// TODO Auto-generated catch blocky33
			e.printStackTrace();
		} // for example
		this.orgvicTable.setModel(model);
		this.orgvicTable.removeColumn(this.orgvicTable.getColumnModel().getColumn(0));

		model.fireTableDataChanged();
		
	}
	
	public JPanel getOrgVicGui() {
		// make orvic table

		GridBagLayout orgperpgrid = new GridBagLayout();
		JScrollPane orgovicviewer = new JScrollPane(orgvicTable);
		JButton backViewer = new JButton("Back");
		JPanel orgovicpanel = new JPanel(orgperpgrid);

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
		orgovicpanel.add(orgovicviewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		orgovicpanel.add(backViewer, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		orgovicpanel.add(backViewer, gbc);
		
		return orgovicpanel;		
	}


}
