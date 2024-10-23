package airlinemanagementsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomer extends javax.swing.JFrame implements ActionListener {

    JTextField tfname,tfphone,tfaddress,tfnationality,tfaadhar;
    JRadioButton rbmale,rbfemale;

    public AddCustomer(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Customer Details");
        heading.setBounds(220,20,500,35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 36));
        heading.setForeground(Color.black);
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60,80,150,25);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(220,80,150,25);
        add(tfname);

        JLabel lblnationaliti = new JLabel("Nationality");
        lblnationaliti.setBounds(60,130,150,25);
        lblnationaliti.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblnationaliti);

        tfnationality = new JTextField();
        tfnationality.setBounds(220,130,150,25);
        add(tfnationality);

        JLabel lblaadhar = new JLabel("Aadhar");
        lblaadhar.setBounds(60,180,150,25);
        lblaadhar.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220,180,150,25);
        add(tfaadhar);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60,230,150,25);
        lbladdress.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220,230,150,25);
        add(tfaddress);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60,280,150,25);
        lblgender.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblgender);

        ButtonGroup gendergroup = new ButtonGroup();

        rbmale = new JRadioButton("Male");
        rbmale.setBackground(Color.white);
        rbmale.setBounds(220,280,70,25);
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBackground(Color.white);
        rbfemale.setBounds(300,280,70,25);
        add(rbfemale);

        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);


        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(60,330,150,25);
        lblphone.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(220,330,150,25);
        add(tfphone);

        JButton save = new JButton("Save");
        save.setBackground(Color.black);
        save.setForeground(Color.white);
        save.setBounds(220,380,150,30);
        save.addActionListener(this);
        add(save);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/emp.png"));
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(450,60,280,400);
        add(lblimage);


        setSize(800,500);
        setLocation(300,150);
        setVisible(true);



    }
    public void actionPerformed(ActionEvent ae){
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String phone = tfphone.getText();
        String address = tfaddress.getText();
        String aadhar = tfaadhar.getText();
        String gender = null;
        if(rbmale.isSelected()){
            gender = "Male";

        }else{
            gender = "Female";
        }
        try {
            Conn conn = new Conn();
            String query = "insert into passenger values ('"+name+"','"+nationality+"','"+phone+"','"+address+"','"+aadhar+"','"+gender+"')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Customer Detail Added Successfully");
            setVisible(false);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
