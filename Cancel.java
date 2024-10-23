package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.*;

public class Cancel extends JFrame implements ActionListener {
    JTextField tfpnr;
    JLabel tfname,lblfcode,cancellationno,lbldate;
    JButton fetchButton,cancel;


    Choice source , destination;

    public Cancel(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Random random = new Random();

        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180,20,250,35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 26));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470,150,250,250);
        add(image);
        

        JLabel lblaadhar = new JLabel("PNR Number");
        lblaadhar.setBounds(60,80,150,25);
        lblaadhar.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblaadhar);

        tfpnr = new JTextField();
        tfpnr.setBounds(220,80,150,25);
        add(tfpnr);

        fetchButton = new JButton("Show Details");
        fetchButton.setBackground(Color.black);
        fetchButton.setForeground(Color.white);
        fetchButton.setBounds(380,80,120,25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60,130,150,25);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220,130,150,25);
        add(tfname);

        JLabel lblnationaliti = new JLabel("Cancellation No.");
        lblnationaliti.setBounds(60,180,150,25);
        lblnationaliti.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblnationaliti);

        cancellationno = new JLabel(""+random.nextInt(1000000));
        cancellationno.setBounds(220,180,150,25);
        add(cancellationno);

        JLabel lbladdress = new JLabel("Flight Code");
        lbladdress.setBounds(60,230,150,25);
        lbladdress.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lbladdress);

        lblfcode = new JLabel();
        lblfcode.setBounds(220,230,150,25);
        add(lblfcode);

        JLabel lblgender = new JLabel("Date");
        lblgender.setBounds(60,280,150,25);
        lblgender.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblgender);

        lbldate = new JLabel();
        lbldate.setBounds(220,280,150,25);
        add(lbldate);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(220,330,120,25);
        cancel.addActionListener(this);
        add(cancel);


        setSize(700,400);
        setLocation(400,100);
        setVisible(true);



    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== fetchButton) {
            String pnr = tfpnr.getText();
            try {
                Conn conn = new Conn();
                String query = "select * from reservation where PNR = '"+pnr+"'";
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next()) {
                    tfname.setText(rs.getString("name"));
                    lblfcode.setText(rs.getString("flightcode"));
                    lbldate.setText(rs.getString("ddate"));

                }else {
                    JOptionPane.showMessageDialog(null, "Please Enter Correct PNR");

                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()== cancel) {
            String name = tfname.getText();
            String pnr = tfpnr.getText();
            String cancelno = cancellationno.getText();
            String fcode = lblfcode.getText();
            String date = lbldate.getText();
            try {
                Conn conn = new Conn();
                String query = "insert into cancel values ('"+pnr+"','"+ name +"','"+cancelno+"','"+ fcode +"','"+date+"')";
                conn.s.executeUpdate(query);
                conn.s.executeUpdate("delete from reservation where PNR = '"+pnr+"'");
                JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                setVisible(false);


            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new Cancel();
    }

}
