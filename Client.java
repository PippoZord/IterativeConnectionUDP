import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.StringTokenizer;

public class Client {
    public static void main(String[] args) {
        
        //SCOKET DATAGRAM
        DatagramSocket sClient;
        
        //ADDRESS OF SERVER
        InetSocketAddress isa;

        //PACKETS IN E OUT
        DatagramPacket fromSrv;
        DatagramPacket toSrv;

        //SUPPORT VARIABLE
        String msg;
        String s = "Y";

        try {
            sClient = new DatagramSocket();
            
            while (true){ 
                //CONNECT TO SERVER IP AND PORT
                int port = Integer.parseInt(args[0]);
                isa = new InetSocketAddress("localhost", port);

                //READ FRONM SYSTEM.IN
                msg = read();
                
                if(msg.equals("exit"))
                    break;

                //CREATE A DATAGRAM PACKET FOR THE SERVER
                toSrv = new DatagramPacket(msg.getBytes(), msg.getBytes().length);
                toSrv.setSocketAddress(isa);

                //SEND THE PACKET TO SERVER
                sClient.send(toSrv);

                //READER BUFFER FROM SERVER
                byte [] buf = new byte[1];

                //CREATE DATAGRAM PACKET TO RECEIVE FROM SERVER
                fromSrv = new DatagramPacket(buf, buf.length);
                
                //RECEIVE FROM SERVER
                sClient.receive(fromSrv);
                
                //GIVE TO s THE VALUE
                s = new String(fromSrv.getData(), 0,fromSrv.getLength());

            };

            //CLOSE THE SOCKET
            sClient.close();
            System.out.println("SOCKET CLOSED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return THE STRING READ
     */
    private static String read() throws IOException {
        BufferedReader bu = new BufferedReader(new InputStreamReader(System.in));
        return bu.readLine();
    }
}
