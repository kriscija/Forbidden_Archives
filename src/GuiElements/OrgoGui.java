package GuiElements;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class OrgoGui {
	CardLayout layout;
	GridBagConstraints gbc;
	JPanel mainframe;
	JTable todisporgo;
	
	public OrgoGui(CardLayout layout, GridBagConstraints gbc, JPanel mainframe, JTable todisporgo) {
		this.layout = layout;
		this.gbc = gbc;
		this.mainframe = mainframe;
		this.todisporgo = todisporgo;
	}
	
	public JPanel getOrgoGui() {
		JScrollPane todisporgopane = new JScrollPane(todisporgo);

		JLabel datel = new JLabel("Est. >=");
		JLabel date2l = new JLabel("Est. < ");
		JButton backViewer = new JButton("Back");
		GridBagLayout orgo = new GridBagLayout();
		JPanel orgoviewer = new JPanel(orgo);
		
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
				System.out.println("Insert Organziation Popup");
			}
		};
		addOrgo.addActionListener(addOrgoAL);
		
		ActionListener deleteOrgoAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = todisporgo.getSelectedRow();
				System.out.println(row);
			}
		};
		deleteOrgo.addActionListener(deleteOrgoAL);
		
		ActionListener modifyOrgoAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = todisporgo.getSelectedRow();
				System.out.println(row);
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

			

		return orgoviewer;
	}
}
