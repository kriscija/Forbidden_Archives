package GuiElements;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ViewerGui {
	CardLayout layout;
	JPanel mainframe;
	public ViewerGui(CardLayout layout, JPanel mainframe) {
		this.layout = layout;
		this.mainframe = mainframe;
	}
	
	public JPanel getViewerGui() {
		JButton showmainb = new JButton("Theories");
		JButton showorgo = new JButton("Organizations");
		JButton showeperps = new JButton("Event Perpetrators");
		JButton showevictims = new JButton("Event Victims");
		JButton backMain = new JButton("Back");
		// main Jpanel constrution
		GridBagLayout viewmenu = new GridBagLayout();
		JPanel viewer = new JPanel(viewmenu);
		
		ActionListener backToMain = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "mainmenupanel");
			}
		};
		backMain.addActionListener(backToMain);
		
		ActionListener shomain = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "viewmain");
			}
		};
		ActionListener shorgo = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "orgoviwer");
			}
		};
		ActionListener shoeperps = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "eventperppanel");
			}
		};
		ActionListener shoevictims = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "orgovicpanel");
			}
		};
		showmainb.addActionListener(shomain);
		showorgo.addActionListener(shorgo);
		showeperps.addActionListener(shoeperps);
		showevictims.addActionListener(shoevictims);

		
		viewer.add(backMain);
		viewer.add(showmainb);
		viewer.add(showeperps);
		viewer.add(showorgo);
		viewer.add(showevictims);
		
		return viewer;
	}
}
