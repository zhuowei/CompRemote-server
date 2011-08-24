import java.net.*;
import java.util.*;
public class IPAddressFinder
{
    public static ArrayList<String> get(){
        ArrayList<String> list=new ArrayList<String>();
        try{
            Enumeration<NetworkInterface> eth=NetworkInterface.getNetworkInterfaces();
            while(eth.hasMoreElements()){
                NetworkInterface e=eth.nextElement();
                //System.out.println(e);
                if(!(e.isLoopback()||e.isVirtual())&&e.isUp()){
                    Enumeration<InetAddress> address=e.getInetAddresses();
                    while(address.hasMoreElements()){
                        InetAddress add=address.nextElement();
                        if(add instanceof Inet4Address){
                            list.add(add.getHostAddress());
                        }
                    }
                }
            }
        }
        catch(Exception e){
            System.err.println(e);
        }
        return list;
    }
}
