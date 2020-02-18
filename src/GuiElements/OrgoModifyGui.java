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
import java.util.Date;

import service.OrgoService;

public class OrgoModifyGui {
	JFrame dataEntryWindow;
	
	String[] data;
	
	String ID;
	String defDesc;
	String defAtt;
	Date defDate;
	String defName;
	
	//constructor for adding
	public OrgoModifyGui() {
		this("", "", null, "");
	}
	
	//constructor for modifying
	public OrgoModifyGui(String orgdesc, String orgatt, Date orgdate, String orgname) {
		this.defDesc = orgdesc;
		this.defAtt = orgatt;
		this.defDate = orgdate;
		this.defName = orgname;
		
        JTextField name = new JTextField(defName);
        
        
        UtilDateModel model = new UtilDateModel();
        if (this.defDate != null) {
            model.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(this.defDate)),
                          Integer.parseInt((new SimpleDateFormat("MM")).format(this.defDate))-1,
                          Integer.parseInt((new SimpleDateFormat("dd")).format(this.defDate)));
            model.setSelected(true);
        }
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        
        JTextField att = new JTextField(defAtt);
        JTextField desc = new JTextField(defDesc);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel("Date of Establishment:"));
        panel.add(datePicker);
        //panel.add(d);
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
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            this.data[2] = (String) dateFormat.format(datePicker.getModel().getValue());

            //this.data[2] = date.getText();
            this.data[3] = name.getText();
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
