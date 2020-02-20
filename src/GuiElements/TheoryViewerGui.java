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
import service.TheoryService;

public class TheoryViewerGui {
	CardLayout layout;
	GridBagConstraints gbc;
	JPanel mainframe;
	JFrame fullframe;
	TheoryService theoryservice;
	MainViewGui mvg;
	JTable theoryTable;

	public TheoryViewerGui(CardLayout layout, GridBagConstraints gbc, JPanel mainframe, JFrame fullframe, MainViewGui mvg, TheoryService theoryservice) {
		// make main viewer
		this.layout = layout;
		this.gbc = gbc;
		this.mainframe = mainframe;
		this.fullframe = fullframe;
		this.mvg = mvg;
		this.theoryservice = theoryservice;
		
		Vector<Vector<Object>> data = this.theoryservice.getValues(null);
		try {
			this.theoryTable = new JTable(this.theoryservice.buildTableModel(data, this.theoryservice.jtableCols));
			this.theoryTable.removeColumn(this.theoryTable.getColumnModel().getColumn(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	public void loadTable(String title) {
		Vector<Vector<Object>> data = this.theoryservice.getValues(title);
		DefaultTableModel model = (DefaultTableModel) this.theoryTable.getModel();
		try {
			model = theoryservice.buildTableModel(data, this.theoryservice.jtableCols);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // for example
		this.theoryTable.setModel(model);
		this.theoryTable.removeColumn(this.theoryTable.getColumnModel().getColumn(0));
		model.fireTableDataChanged();
	}
	
	public JPanel getTheoryViewGui() {
		GridBagLayout theoryview = new GridBagLayout();
		JButton backViewer = new JButton("Back");
		JButton searchTheoryButton = new JButton("Search Theory");
		
		JButton theoryDeetsButton = new JButton("See Details");
		JButton theoryAddButton = new JButton("Add Theory");
		JLabel theorySearchLabel = new JLabel("Theory name:");
		JTextField theorySearchTxt = new JTextField();
		JScrollPane theoryscrollpane = new JScrollPane(this.theoryTable);
		JPanel theoryviewer  = new JPanel(theoryview);
		
	
		ActionListener backToView = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "viewer");
			}
		};
		backViewer.addActionListener(backToView);
		
		ActionListener searchTheory = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = theorySearchTxt.getText();
				if(title.equals("")) {
					title = null;
				}
				loadTable(title);
			}
		};
		searchTheoryButton.addActionListener(searchTheory);
		
		ActionListener seeTheory = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = theoryTable.getSelectedRow();
				if(row == -1) {
					return;
				}
				String theory_name = (String) theoryTable.getValueAt(row, 0);
				mvg.loadTable(theory_name);
				layout.show(mainframe, "mainviewer");
			}
		};
		theoryDeetsButton.addActionListener(seeTheory);
		
		ActionListener addTheory = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TheoryModifyGui tmg = new TheoryModifyGui();
				if((tmg.data[0] != null) && (!tmg.data[0].equals(""))) {
					theoryservice.add(tmg.data);
				}
				loadTable(null);
			}
		};
		theoryAddButton.addActionListener(addTheory);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		theoryviewer.add(backViewer, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 3;
		theoryviewer.add(theoryscrollpane, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 0;
		theoryviewer.add(theorySearchLabel, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 0;
		theoryviewer.add(theorySearchTxt, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
		gbc.gridy = 1;
		theoryviewer.add(searchTheoryButton, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 4;
		theoryviewer.add(theoryDeetsButton, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 5;
		theoryviewer.add(theoryAddButton, gbc);
		
		return theoryviewer;		
	}
}
