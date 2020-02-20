package GuiElements;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.util.Date;

import service.OrgoService;
import service.TheoryService;

public class TheoryModifyGui {
	JFrame dataEntryWindow;
	
	String[] data;

	//constructor for adding
	public TheoryModifyGui() {
		
        JTextField Title = new JTextField("");        
        JTextField Summary = new JTextField("");
        Summary.setPreferredSize(new Dimension(300, 100));
        Title.setPreferredSize(new Dimension(300, 40));
        
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        panel.add(new JLabel("Title:"));
        panel.add(Title);
        panel.add(new JLabel("Summary:"));
        panel.add(Summary);
        
        this.data = new String[2];
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	if(!Title.getText().equals("")) {
	            this.data[0] = Title.getText();
	            this.data[1] = Summary.getText();
        	}
        } else {
            System.out.println("Data input Failed");
        }
        
	}
	
    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "dd/MM/yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
}
