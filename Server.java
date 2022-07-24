import java.io.IOException;
import java.net.*;
import java.net.SocketAddress;
import java.util.StringTokenizer;

public class Server {
    public static void main(String[] args) {
        
        DatagramSocket sSrv;

        //PACKETS IN E OUT
        DatagramPacket fromClient;
        DatagramPacket toClient;
        byte [] buf;
        String str="Y";

        try {
            sSrv = new DatagramSocket(0);
            System.out.println("PORTA " + sSrv.getLocalPort());

            while (true){

                //BUFFER TO READ
                buf = new byte[128];
                
                //PACKET TO RECEIVE FROM CLIENT
                fromClient = new DatagramPacket(buf, buf.length);
                
                //RECEIVE FROM CLIENT
                sSrv.receive(fromClient);

                //ADDRESS OF CLIENT
                SocketAddress isa = fromClient.getSocketAddress();

                //STRING RECEIVED
                String s = new String(fromClient.getData(), 0, fromClient.getLength());
                System.out.println("STRING RECEIVEED: " + s);

                //CREATE DATAGRAMPACKET FOR CLIENT
                toClient = new DatagramPacket(str.getBytes(), str.getBytes().length);
                //SET THE ADDRESS FROM THE SEND
                
                toClient.setSocketAddress(isa);
                
                //SEND
                sSrv.send(toClient);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
