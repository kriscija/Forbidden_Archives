package GuiElements;

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

import service.OrgoService;

public class OrgoModifyGui {
	OrgoService orgoservice;
	JFrame dataEntryWindow;
	
	String[] data;
	
	
	public OrgoModifyGui(OrgoService orgoservice) {
		this.orgoservice = orgoservice;
        JTextField name = new JTextField("");
        
        //UtilDateModel model = new UtilDateModel();
        //Properties p = new Properties();
        //p.put("text.today", "Today");
        //p.put("text.month", "Month");
        //p.put("text.year", "Year");
        //JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        //JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        JTextField date = new JTextField("");
        
        JTextField att = new JTextField("");
        JTextField desc = new JTextField("");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel("Date:"));
        //panel.add(datePicker);
        panel.add(date);
        panel.add(new JLabel("Attributes:"));
        panel.add(att);
        panel.add(new JLabel("Description"));
        panel.add(desc);
        
        this.data = new String[4];
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            this.data[0] = desc.getText();
            this.data[1] = att.getText();
            
            //DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            //this.data[2] = dateFormat.format(datePicker.getModel().getValue());
            //System.out.println(this.data[2]);
            this.data[2] = date.getText();
            this.data[3] = name.getText();
        } else {
            System.out.println("Data input Failed");
        }
        
	}
	
	public void modify() {
		this.orgoservice.add(data);
	}
	
    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "dd-MM-yyyy";
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
