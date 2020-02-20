package GuiElements;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class EventModifyGui {
	JFrame dataEntryWindow;
	
	String[] data;
	
	String ID;
	String defDesc;
	String defAtt;
	Date defDate;
	String defName;
	
	//constructor for adding
	public EventModifyGui(String[] orgos) {		
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
        
        JTextField type = new JTextField(defAtt);
        JTextField desc = new JTextField(defDesc);
        JComboBox orgo = new JComboBox(orgos);
        
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        panel.add(new JLabel("Type:"));
        panel.add(type);
        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel("Date:"));
        panel.add(datePicker);
        //panel.add(d);
        panel.add(new JLabel("Organization:"));
        panel.add(orgo);
        
        panel.add(new JLabel("Desc:"));
        panel.add(desc);
        
        this.data = new String[5];
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            this.data[0] = type.getText();
            
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            this.data[1] = (String) dateFormat.format(datePicker.getModel().getValue());
            //this.data[1] = "02/05/2020";
            this.data[2] = desc.getText();
            this.data[3] = (String) orgo.getSelectedItem();
            this.data[4] = name.getText();
        } else {
            System.out.println("Data input Failed");
        }
        
	}
	
    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "MM/dd/yyyy";
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
