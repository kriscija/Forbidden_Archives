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

public class EventPerpGui {
	CardLayout layout;
	GridBagConstraints gbc;
	JPanel mainframe;
	JTable todispeperp;
	public EventPerpGui(CardLayout layout, GridBagConstraints gbc, JPanel mainframe, JTable todispeperp) {
		this.layout = layout;
		this.gbc = gbc;
		this.mainframe = mainframe;
		this.todispeperp = todispeperp;
	}
	
	public JPanel getEventPerpGui() {
		JScrollPane eperpviewer = new JScrollPane(todispeperp);
		JLabel eventlabel = new JLabel("Event name:");
		JButton backViewer = new JButton("Back");
		

		GridBagLayout eventperp = new GridBagLayout();
		JPanel eventperppanel = new JPanel(eventperp);
		
		ActionListener backToView = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "viewer");
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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 0;
		eventperppanel.add(eventlabel, gbc);


		
		return eventperppanel;
	}
}