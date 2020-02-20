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
		JButton showevents = new JButton("Events");
		// main Jpanel constrution
		GridBagLayout viewmenu = new GridBagLayout();
		JPanel viewer = new JPanel(viewmenu);
		
		ActionListener shomain = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "theoryviewer");
			}
		};
		ActionListener shorgo = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "orgoviewer");
			}
		};
		ActionListener shoevents = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainframe, "eventviewer");
			}
		};

		showmainb.addActionListener(shomain);
		showorgo.addActionListener(shorgo);
		showevents.addActionListener(shoevents);

		viewer.add(showmainb);
		viewer.add(showevents);
		viewer.add(showorgo);
		
		return viewer;
	}
}
