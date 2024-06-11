package revisionipt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RevisionIPT extends JFrame implements ActionListener, ItemListener{

    JLabel lblTitle, lblName, lblID, lblEmail, lblPLvl, lblPName, lblPSession, lblOutput, lblFee;
    JTextField txtName, txtID, txtEmail, txtFee;
    JComboBox <String> cbPLvl, cbPName, cbPSession;
    JButton btnSubmit, btnView, btnReset, btnCalculate;
    JTextArea txtDisplay;
    Integer inewfee;
    
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/revisionipt";
    String uname = "root";
    String pass = "";
    
   RevisionIPT(){
       setTitle("STUDENT ENROLLMENT SYSTEM SUNWAY UNIVERSITY");
       
       //lbl
       lblTitle = new JLabel ("ENROLLMENT DATA");
        
       lblName = new JLabel ("Name :");
       txtName = new JTextField ();
       
       lblID = new JLabel ("Student ID :");
       txtID = new JTextField ();
       
       lblEmail = new JLabel ("Email :");
       txtEmail = new JTextField();
       
       lblPLvl= new JLabel ("Program Level :");
       cbPLvl = new JComboBox<>(new String[]{"Please Select Your Program", "DIPLOMA", "DEGREE"});
       lblPName = new JLabel ("Program Name :");
       cbPName= new JComboBox<>(new String[]{"Please Select Program Name"});
       lblPSession = new JLabel("Program Session :");
       cbPSession = new JComboBox<>(new String[]{"Please Select Your Session", "2023", "2024", "2025"});
       
       lblFee = new JLabel ("Total Fee :");
       txtFee = new JTextField();
       
       btnSubmit = new JButton("SUBMIT");
       btnCalculate = new JButton("CALCULATE");
       btnView = new JButton("VIEW");
       btnReset = new JButton("RESET");
       
       lblOutput = new JLabel("Output");
       
       txtDisplay = new JTextArea();
       
       //add
       add(lblTitle);
       add(lblName);
       add(txtName);
       add(lblID);
       add(txtID);
       add(lblEmail);
       add(txtEmail);
       
       add(lblFee);
       add(txtFee);
       txtFee.setEditable(false);
       
       add(lblPLvl);
       add(cbPLvl);
       add(lblPName);
       add(cbPName);
       add(lblPSession);
       add(cbPSession);
       
       add(btnSubmit);
       btnSubmit.addActionListener(this);
       add(btnCalculate);
       btnCalculate.addActionListener(this);
       add(btnView);
       btnView.addActionListener(this);
       add(btnReset);
       btnReset.addActionListener(this);
       
       add(lblOutput);
       add(txtDisplay);
       JScrollPane scrollPane = new JScrollPane(txtDisplay);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       scrollPane.setBounds(100, 380, 400, 200);
       add(scrollPane);
       
       //setbounds
        lblTitle.setBounds(280, 30, 250, 10);
        lblName.setBounds(100, 60, 250, 10);
        txtName.setBounds(250, 60, 250, 20);
        lblID.setBounds(100, 90, 250, 10);
        txtID.setBounds(250, 90, 250, 20);
        lblEmail.setBounds(100, 120, 250, 10);
        txtEmail.setBounds(250, 120, 250, 20);
        txtFee.setBounds(250, 180, 250, 20);
        lblPLvl.setBounds(100, 160, 250, 20);
        cbPLvl.setBounds(250, 160, 250, 20);
        lblPName.setBounds(100, 200, 250, 20);
        cbPName.setBounds(250, 200, 250, 20);
        lblPSession.setBounds(100, 240, 250, 20);
        cbPSession.setBounds(250, 240, 250, 20);
        lblFee.setBounds(100, 280, 250, 10);
        txtFee.setBounds(250, 280, 250, 20);
        btnSubmit.setBounds(100, 310, 80, 30);
        btnCalculate.setBounds(200, 310, 110, 30);
        btnView.setBounds(330, 310, 80, 30);
        btnReset.setBounds(430, 310, 80, 30);
        lblOutput.setBounds(100, 350, 250, 30);
        txtDisplay.setBounds(100, 420, 400, 200);

       
               
       setLayout(null);
       setSize(900,900);
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    cbPLvl.addItemListener(new ItemListener() {
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectedProgram = (String) e.getItem();

            cbPName.removeAllItems();

            if (selectedProgram.equals("DIPLOMA")) {
                
                cbPName.addItem("Diploma Program 1");
                cbPName.addItem("Diploma Program 2");
                cbPName.addItem("Diploma Program 3");
                inewfee = 200;
                
            } else if (selectedProgram.equals("DEGREE")) {
                
                cbPName.addItem("Degree Program 1");
                cbPName.addItem("Degree Program 2");
                cbPName.addItem("Degree Program 3");
                inewfee = 1000;
            }
        }
    }
});
   }
    
    public static void main(String[] args) {
       RevisionIPT ipt = new RevisionIPT();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCalculate) {
            
            Integer total = inewfee;
            txtFee.setText(String.valueOf(total));
            
        } else if (e.getSource() == btnReset) {
            
            txtName.setText("");
            txtID.setText("");
            txtEmail.setText("");
            txtFee.setText("");
            txtDisplay.setText("");
            
            cbPLvl.setSelectedIndex(0);
            cbPName.removeAllItems();
            cbPName.addItem("Please Select Your Program");
            cbPSession.setSelectedIndex(0);
            
        }else if(e.getSource() == btnSubmit){
            
        String name = txtName.getText();
        String student = txtID.getText();
        String email = txtEmail.getText();
        String PLvl = (String) cbPLvl.getSelectedItem();
        String PName = (String) cbPName.getSelectedItem();
        String PSession = (String) cbPSession.getSelectedItem();
        String Fee = txtFee.getText();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, uname, pass);
            Statement stat = conn.createStatement();
            
            String sql = "INSERT INTO ipt (Name, StudID, Email, PLvl, PName, PSession, Fee) " +
                         "VALUES ('"+name+"', '"+student+"', '"+email+"', '"+PLvl+"', '"+PName+"', '"+PSession+"', '"+Fee+"')";
            stat.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data successfully added");
            conn.close();
            
        //Project_IPT pipt = new Project_IPT();
        //pipt.setVisible(true);
        //this.dispose();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
        }
            
        }else if(e.getSource() == btnView){
            
            String Name = txtName.getText();
            String ID = txtID.getText();
            String Email = txtEmail.getText();
            String PLvl = (String) cbPLvl.getSelectedItem();
            String PName = (String) cbPName.getSelectedItem();
            String PSession = (String) cbPSession.getSelectedItem();
            String Fee = txtFee.getText();


        txtDisplay.append("Name : "+ Name + "\n");
        txtDisplay.append("ID : "+ ID + "\n");
        txtDisplay.append("Email : "+ Email + "\n");
        txtDisplay.append("Program Level : "+ PLvl + "\n");
        txtDisplay.append("program Name : "+ PName + "\n");
        txtDisplay.append("program Session : "+ PSession + "\n");
        txtDisplay.append("Fee : "+ Fee + "\n");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
