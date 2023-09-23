package Server;
import java.net.*;
import java.io.*;
import utility.*;

public class AppServer implements Runnable {
    ServerSocket ss;
    Socket s; 
    Thread t1;
    public AppServer()
    {
        try{
            ss=new ServerSocket(8888);
            System.out.println("server started.....");
            t1=new Thread(this);
            t1.start();
        }
        catch(Exception ex)
        {
            
        }
    }
    public void run(){
        for(;;)
        {
            try
            {
                s=ss.accept();
                Connect c=new Connect(s);
            }
            catch(Exception ex)
            {
            }
            
            }
        }
}
    
class Connect
{
    Connect(Socket s)
    {
        try{
            ObjectInputStream oin=new ObjectInputStream(s.getInputStream());
            ClientData obj=(ClientData)oin.readObject();
            System.out.println("Name:"+obj.name);
            System.out.println("Email:"+obj.email);
            System.out.println("Mobile:"+obj.mobile);
        }
        catch(Exception ex)
        {
        }
        
        }
    

public static void main(String args[])
{
new AppServer();
}
}

