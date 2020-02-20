package GuiElements;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

import service.EventService;
import service.OrgoService;


public class EventGui {
	CardLayout layout;
	GridBagConstraints gbc;
	JPanel mainframe;
	JFrame fullframe;
	OrgoService orgoservice;
	EventService eventservice;
	
	EventPerpGui epg;
	JScrollPane todispeventpane;
	EventVictimGui evg;
	
	JTable eventTable;

	public EventGui(CardLayout layout, GridBagConstraints gbc, JPanel mainframe, JFrame fullframe, EventVictimGui evg, EventPerpGui epg, OrgoService orgoservice, EventService eventservice) {
		this.layout = layout;
		this.gbc = gbc;
		this.mainframe = mainframe;
		this.fullframe = fullframe;
		this.evg = evg;
		this.epg = epg;
		
		this.orgoservice = orgoservice;
		this.eventservice = eventservice;
		
		Vector<Vector<Object>> data = this.eventservice.getValues(null, null);
		try {
			this.eventTable = new JTable(this.eventservice.buildTableModel(data, this.eventservice.jtableCols));
			this.eventTable.removeColumn(this.eventTable.getColumnModel().getColumn(0));
			this.OrganizationNameify();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadTable(String dateFrom, String dateTo) {
		Vector<Vector<Object>> data = this.eventservice.getValues(dateFrom, dateTo);
		DefaultTableModel model = (DefaultTableModel) this.eventTable.getModel();
		try {
			model = eventservice.buildTableModel(data, this.eventservice.jtableCols);
		} catch (SQLException e) {
			// TODO Auto-generated catch blocky33
			e.printStackTrace();
		} // for example
		this.eventTable.setModel(model);
		this.eventTable.removeColumn(this.eventTable.getColumnModel().getColumn(0));
		this.OrganizationNameify();
		model.fireTableDataChanged();
	}
	
	public void OrganizationNameify() {
		for(int row = 0; row < this.eventTable.getRowCount(); row++) {		
			String orgoName = this.orgoservice.getOrganizationName(String.valueOf(this.eventTable.getValueAt(row, 3)));
			this.eventTable.setValueAt(orgoName, row, 3);
		}
	}
	
	public JPanel getEventGui() {
		this.loadTable(null, null);
		JLabel datel = new JLabel("Est. >=");
		JLabel date2l = new JLabel("Est. < ");
		JButton backViewer = new JButton("Back");
		GridBagLayout event = new GridBagLayout();
		JPanel eventviewer = new JPanel(event);
		JButton searchevent = new JButton("Filter");
		JTextField date1 = new JTextField();
		JTextField date2 = new JTextField();
		this.todispeventpane = new JScrollPane(eventTable);
		
		
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

		searchevent.addActionListener(filterorgAL);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 0;
		eventviewer.add(date1, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 1;
		eventviewer.add(date2, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 2;
		eventviewer.add(searchevent, gbc);
		
		
		//Create, and Delete
		JButton addOrgo = new JButton("Add");
		JButton deleteOrgo = new JButton("Delete");
		
		ActionListener backToView = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "viewer");
			}
		};
		backViewer.addActionListener(backToView);
		
		ActionListener addEvAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> orgoList = orgoservice.getOrganizationName();
				String[] orgos = orgoList.toArray(new String[0]);
				EventModifyGui emg = new EventModifyGui(orgos);
				
				if((emg.data[3] != null) && (!emg.data[3].equals(""))) {
					System.out.println(emg.data[0]);
					emg.data[3] = orgoservice.getOrganizationID(emg.data[3]);
					System.out.println(emg.data[0]);

					eventservice.add(emg.data);
				}
				loadTable(null, null);
			}
		};
		addOrgo.addActionListener(addEvAL);
		
		ActionListener deleteEvAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = eventTable.getSelectedRow();
				if(row == -1) {
					return;
				}
				int ID = (int) eventTable.getModel().getValueAt(row, 0);
				eventservice.delete(ID);
				loadTable(null, null);
			}
		};
		deleteOrgo.addActionListener(deleteEvAL);
		
		JButton eventPerpetrators = new JButton("See Perpetrators");
		ActionListener seeEventPerps = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = eventTable.getSelectedRow();
				if(row == -1) {
					return;
				}
				String ID = String.valueOf(eventTable.getModel().getValueAt(row, 0));
				System.out.println(ID);
				epg.loadTable(ID);
				layout.show(mainframe, "eventperppanel");
			}
		};
		eventPerpetrators.addActionListener(seeEventPerps);
		
		JButton eventVictims = new JButton("See Victims");
		ActionListener seeEventVics = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = eventTable.getSelectedRow();
				if(row == -1) {
					return;
				}
				String ID = String.valueOf(eventTable.getModel().getValueAt(row, 0));
				evg.loadTable(ID);
				layout.show(mainframe, "eventvictimspanel");
			}
		};
		eventVictims.addActionListener(seeEventVics);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 3;
		eventviewer.add(todispeventpane, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		eventviewer.add(backViewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 0;
		eventviewer.add(datel, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 1;
		eventviewer.add(date2l, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 4;
		eventviewer.add(addOrgo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 5;
		eventviewer.add(deleteOrgo, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 3;
		eventviewer.add(eventPerpetrators, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 3;
		eventviewer.add(eventVictims, gbc);
			

		return eventviewer;
	}
}
