
/**
 * Write a description of class CompRemote here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.Robot;
import java.net.*;
import org.apache.commons.codec.digest.DigestUtils;
public class CompRemote extends JFrame implements ServerThreadListener, ActionListener
{
    private JPasswordField passwordField=new JPasswordField(8);
    private JButton startButton=new JButton("Start");
    private JLabel ipField=new JLabel();
    private JLabel statusField=new JLabel("CompRemote 2.0: Enter password");
    private ServerThread serverThread;
    public CompRemote(){
        super("CompRemote");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(),1));
        add(statusField);
        add(passwordField);
        add(startButton);
        startButton.addActionListener(this);
        pack();
        validate();
    }
    public void serverConnection(){
        System.out.println("connect!");
        statusField.setText("Connected!");
    }
    public void actionPerformed(ActionEvent e){
        System.out.println(e);
        if(e.getSource()==startButton){
            startListening();
        }
    
    }
    public void startListening(){
        passwordField.setVisible(false);
        startButton.setVisible(false);
        serverThread=new ServerThread(24125,hashPassword(passwordField.getText()));
        System.out.println("listening on port 24125");
        serverThread.listener=this;
        serverThread.start();
        String ipHint="<html>This computer's IP address(es) is/are: <br>";
        for(String str: IPAddressFinder.get()){
            ipHint=ipHint+str+"<br>";
        }
        ipHint=ipHint+"</html>";
        statusField.setText(ipHint);
    }
    private String hashPassword(String str){
        return DigestUtils.shaHex(DigestUtils.shaHex(str)+"*&#~"); //I know this is a FAIL
    }
        
    public static void main(String[] args){
	try{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}
	catch(Exception e){
		System.out.println("Look and feel fail:" + e.toString());
	}
        CompRemote comp=new CompRemote();
        comp.setVisible(true);
    }
}
