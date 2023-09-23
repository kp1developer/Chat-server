package Client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import utility.*;

public class ClientMachine extends JFrame implements ActionListener{
    JLabel L1,L2,L3;
    JButton b1;
    JPanel p1;
    JTextField t1,t2,t3;
    public ClientMachine()
    {
        setVisible(true);
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        L1=new JLabel("Name");
        L2=new JLabel("Email");
        L3=new JLabel("Mobile");
        t1=new JTextField(20);
        t2=new JTextField(20);
        t3=new JTextField(20);
        b1=new JButton("submit to server");
        p1=new JPanel();
        p1.add(L1);
        p1.add(t1);
        p1.add(L2);
        p1.add(t2);
        p1.add(L3);
        p1.add(t3);
        p1.add(b1);
        getContentPane().add(p1);
        b1.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent e)
    {
        try{
            ClientData obj=new ClientData();
            obj.Name=t1.getText();
            obj.Email=t2.getText();
            obj.Mobile=t3.getText();
            Socket s=new Socket("122.168.127.81",8888);
            ObjectOutputStream ois=new ObjectOutputStream(s.getOutputStream());
            ois.writeObject(obj);
            ois.close();
            s.close();
        }
        catch(Exception ex)
        {
        }
    }
    public static void main(String args[])
    {
        new ClientMachine();
    }
        }
    
    

