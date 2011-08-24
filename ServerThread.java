
/**
 * Listens for one connection to the selected port, then stops listening and starts the MainThread.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.net.*;
public class ServerThread extends Thread
{
    public Socket mainSocket;
    public ServerSocket sock;
    public ServerThreadListener listener;
    public String password;
    public ServerThread(int port, String password){
	this.password=password;
        try{
            sock=new ServerSocket(port);
        }
        catch(Exception e){
		System.out.println("port creation" + e);
        }
    }
    public void run(){
	System.out.println("ServerThread start");
        try{
            mainSocket=sock.accept();
            sock.close();
            if(listener!=null){
                listener.serverConnection();
            }
            new MainThread(mainSocket, password).start();

        }
        catch(Exception e){
            System.err.println("Listening for connection fail!" + e);
        }
    }
}
