package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends javax.swing.JFrame implements ActionListener {
    JTable table;
    JTextField pnr;
    JButton show;
    public JourneyDetails(){
        getContentPane().setBackground(Color.white);
        setLayout(null);


        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setBounds(50,50,100,25);
        add(lblpnr);

        pnr = new JTextField();
        pnr.setBounds(160,50,120,25);
        add(pnr);

        show = new JButton("Show Details");
        show.setBackground(Color.black);
        show.setForeground(Color.white);
        show.setBounds(290,50,120,25);
        show.addActionListener(this);
        add(show);

        table = new JTable();

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,100,800,150);
        jsp.setBackground(Color.white);
        add(jsp);

        setSize(800,500);
        setLocation(400,100);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        try {
            Conn conn = new Conn();

            ResultSet rs = conn.s.executeQuery("select * from reservation where PNR = '"+pnr.getText()+"'");

            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(null,"PNR Does Not Exist");
                return;
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception ea){
            ea.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}
