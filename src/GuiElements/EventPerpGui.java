package GuiElements;
import java.awt.CardLayout;
import java.awt.Dimension;
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

import service.EventPerpView;

public class EventPerpGui {
	CardLayout layout;
	GridBagConstraints gbc;
	JPanel mainframe;
	JTable eventPerpTable;
	EventPerpView eventperpview;
	
	public EventPerpGui(CardLayout layout, GridBagConstraints gbc, JPanel mainframe, JFrame fullframe, EventPerpView eventperpview) {
		this.layout = layout;
		this.gbc = gbc;
		this.mainframe = mainframe;
		this.eventperpview = eventperpview;
		
		Vector<Vector<Object>> data = this.eventperpview.getValues(null);
		try {
			this.eventPerpTable = new JTable(this.eventperpview.buildTableModel(data, this.eventperpview.jtableCols));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadTable(String ID) {
		Vector<Vector<Object>> data = this.eventperpview.getValues(ID);
		DefaultTableModel model = (DefaultTableModel) this.eventPerpTable.getModel();
		try {
			model = eventperpview.buildTableModel(data, this.eventperpview.jtableCols);
		} catch (SQLException e) {
			// TODO Auto-generated catch blocky33
			e.printStackTrace();
		} // for example
		this.eventPerpTable.setModel(model);
		model.fireTableDataChanged();
		
		
	}
	
	public JPanel getEventPerpGui() {
		JScrollPane eperpviewer = new JScrollPane(eventPerpTable);

		JButton backViewer = new JButton("Back");

		GridBagLayout eventperp = new GridBagLayout();
		JPanel eventperppanel = new JPanel(eventperp);
		
		ActionListener backToView = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "eventviewer");
			}
		};
		backViewer.addActionListener(backToView);
		

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 3;
		eventperppanel.add(eperpviewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		eventperppanel.add(backViewer, gbc);

		
		return eventperppanel;
	}
}