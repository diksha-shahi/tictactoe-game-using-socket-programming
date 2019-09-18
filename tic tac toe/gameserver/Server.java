package gameserver;

import java.net.*;
import java.util.*;

class Server implements Runnable
{
    boolean flag;
    Thread st, rt;
    DatagramSocket dRSkt, dSSkt;
    LinkedList <DatagramPacket> datagramQ;
 
    Server(int pno) throws Exception
    {
        //open the port using DatagramSocket that binds to specific port no
        dRSkt = new DatagramSocket(pno);
        //open the port using DatagramSocket that binds to any available port no
        dSSkt = new DatagramSocket();

        //threads for datagram i/o
        flag = true;
        st = new Thread(this);
        rt = new Thread(this);

        st.start();
        rt.start();

        //data structures
        datagramQ = new LinkedList<DatagramPacket>();
    }

    public void run()
    {
        if(Thread.currentThread().equals(st))
            sendDatagrams();
        else 
            receiveDatagrams();
    }

    void sendDatagrams()
    {
        DatagramPacket dPkt;
        GameManager gMgr = new GameManager();
        while(flag)
        {
            if(datagramQ.size() > 0)
            {
                try
                {
                    dPkt = datagramQ.remove(0); 
                    //read and process the dPkt
                    byte arr[] =  dPkt.getData();
                    int len = dPkt.getLength();

                    String req = new String(arr, 0, len);
                    String res = gMgr.Process(req);

                    //compose a new datagram
                    DatagramPacket rPkt = new DatagramPacket(res.getBytes(), res.length(), dPkt.getAddress(), dPkt.getPort()); 
                    dSSkt.send(rPkt);
                }
                catch(Exception ex)
                {
                    System.out.println("Err in sendDatagrams()," + ex);
                }
            }
            else
            {
                try
                {
                 Thread.sleep(1000000);
                }
                catch(InterruptedException ex)
                {}
            }
        }//while

        try
        {
          dSSkt.close();
        }
        catch(Exception ex)
        {}
    }

    void receiveDatagrams()
    {
        DatagramPacket dPkt; 
        while(flag)
        {
            try
            {
                //create a datagram packet
                dPkt = new DatagramPacket(new byte[512], 512);   

                //receive data in the datagram packet
                System.out.println("Server waiting to receive a datagram packet"); 
                dRSkt.receive(dPkt);
                datagramQ.add(dPkt);
                st.interrupt();//bring st out of sleep, if in. 
                System.out.println("Server got a datagram packet");
            }
            catch(Exception ex)
            {
                System.out.println("Err in receiveDatagrams()," + ex);
            }
        }
        try
        {
            dRSkt.close();
        }
        catch(Exception ex)
        {}

    }//receiveDatagrams
 
}

