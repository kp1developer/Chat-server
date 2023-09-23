import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class ChatServer  extends JFrame
implements ActionListener,Runnable{
    JLabel L1,L2;
    JTextField t1;
    JTextArea ta1;
    JButton b1,b2;
    JPanel p1,inputPanel,centerPanel,buttonPanel;
    String name;
    ServerSocket ss;
    Socket  clientSocket;
    BufferedReader br;
    BufferedWriter bw;
    Thread th1;
    
    
    public ChatServer()
    {
       setVisible(true);
       setSize(400,400);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       p1=new JPanel();
       inputPanel=new JPanel();
       centerPanel=new JPanel();
       buttonPanel=new JPanel();
       L1=new JLabel("Name");
       L2=new JLabel();
       t1=new JTextField(20);
       ta1=new JTextArea(5,20);
       JScrollPane js=new JScrollPane(ta1);
       b1=new JButton("Send");
       b2=new JButton("Cancel");
       
       p1.setLayout(new BorderLayout());
       inputPanel.add(L1);
       inputPanel.add(L2);
       p1.add(inputPanel,"North");
       centerPanel.setLayout(new BorderLayout());
       centerPanel.add(js,"Center");
       centerPanel.add(t1,"South");
       p1.add(centerPanel,"Center");
       buttonPanel.add(b1);
       buttonPanel.add(b2);
       p1.add( buttonPanel,"South");
       getContentPane().add(p1);
       p1.setBorder
        (BorderFactory.createTitledBorder("chat server"));
       ta1.setEditable(false);
    b1.addActionListener(this);
    b2.addActionListener(this);
    try{
        name=JOptionPane.showInputDialog(null,"enter name");
        L2.setText(name);
        L2.setFont(new Font("Arial",Font.BOLD,20));
        L2.setForeground(Color.red);
        ss=new ServerSocket(8888);
        clientSocket=ss.accept();
        br=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        bw=new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        bw.write("hello");
        bw.newLine();
        bw.flush();
        th1=new Thread(this);
        th1.start();
    }
    catch(Exception ex)
    {
        
        }
    }
    public void run()
    {
        for(;;)
        {
            try{
                ta1.append(br.readLine()+"\n");
            }
            catch(Exception ex)
            {
            }
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            try{
                String msg=name+"  says-->"+t1.getText();
                bw.write(msg);
                bw.newLine();
                bw.flush();
                ta1.append(msg+"\n");
                t1.setText("");
            }
            catch(Exception ex)
            {
            }
        }
        else{
            System.exit(0);
        }
        }
        
                
            
            
        
    
       
     public static void main(String args[])
    {
       new ChatServer();
        
            
    }
            
    
}
