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

import service.MainViewService;
import service.OrgoService;
import service.TheoryService;

public class MainViewGui {	
	CardLayout layout;
	GridBagConstraints gbc;
	JPanel mainframe;
	JFrame fullframe;
	MainViewService mainviewservice;
	JTable mainTable;
	String theory_name;

	public MainViewGui(CardLayout layout, GridBagConstraints gbc, JPanel mainframe, JFrame fullframe, MainViewService mainviewservice) {
		// make main viewer
		this.layout = layout;
		this.gbc = gbc;
		this.mainframe = mainframe;
		this.fullframe = fullframe;
		this.mainviewservice = mainviewservice;
		
		Vector<Vector<Object>> data = this.mainviewservice.getValues(null);
		try {
			this.mainTable = new JTable(this.mainviewservice.buildTableModel(data, this.mainviewservice.jtableCols));
			this.mainTable.removeColumn(this.mainTable.getColumnModel().getColumn(0));
			this.mainTable.removeColumn(this.mainTable.getColumnModel().getColumn(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	
	public void loadTable(String title) {
		Vector<Vector<Object>> data = this.mainviewservice.getValues(title);
		DefaultTableModel model = (DefaultTableModel) this.mainTable.getModel();
		try {
			model = mainviewservice.buildTableModel(data, this.mainviewservice.jtableCols);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // for example
		this.mainTable.setModel(model);
		this.mainTable.removeColumn(this.mainTable.getColumnModel().getColumn(0));
		this.mainTable.removeColumn(this.mainTable.getColumnModel().getColumn(0));
		model.fireTableDataChanged();
	}
	
	public JPanel getMainViewGui() {		
		GridBagLayout mainview = new GridBagLayout();
		JButton backViewer = new JButton("Back");
		JScrollPane todispmainviewer = new JScrollPane(this.mainTable);
		JPanel mainviewer  = new JPanel(mainview);
		
	
		ActionListener backToView = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "theoryviewer");
			}
		};
		backViewer.addActionListener(backToView);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainviewer.add(backViewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 3;
		mainviewer.add(todispmainviewer, gbc);
		
		return mainviewer;		
	}

}
