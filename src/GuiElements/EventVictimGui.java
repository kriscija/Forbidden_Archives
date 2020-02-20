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
import service.EventVictimsService;

public class EventVictimGui {
	CardLayout layout;
	GridBagConstraints gbc;
	JPanel mainframe;
	JTable eventVictimTable;
	EventVictimsService evemtvictimsservice;
	
	public EventVictimGui(CardLayout layout, GridBagConstraints gbc, JPanel mainframe, JFrame fullframe, EventVictimsService eventviewservice) {
		this.layout = layout;
		this.gbc = gbc;
		this.mainframe = mainframe;
		this.evemtvictimsservice = eventviewservice;
		
		Vector<Vector<Object>> data = this.evemtvictimsservice.getValues(null);
		try {
			this.eventVictimTable = new JTable(this.evemtvictimsservice.buildTableModel(data, this.evemtvictimsservice.jtableCols));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadTable(String ID) {
		Vector<Vector<Object>> data = this.evemtvictimsservice.getValues(ID);
		DefaultTableModel model = (DefaultTableModel) this.eventVictimTable.getModel();
		try {
			model = evemtvictimsservice.buildTableModel(data, this.evemtvictimsservice.jtableCols);
		} catch (SQLException e) {
			// TODO Auto-generated catch blocky33
			e.printStackTrace();
		} // for example
		this.eventVictimTable.setModel(model);
		model.fireTableDataChanged();
		
		
	}
	
	public JPanel getEventVictimGui() {
		JScrollPane evictimviewer = new JScrollPane(eventVictimTable);
		JButton backViewer = new JButton("Back");

		GridBagLayout eventperp = new GridBagLayout();
		JPanel eventvictimpanel = new JPanel(eventperp);
		
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
		eventvictimpanel.add(evictimviewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		eventvictimpanel.add(backViewer, gbc);

		
		return eventvictimpanel;
	}
}