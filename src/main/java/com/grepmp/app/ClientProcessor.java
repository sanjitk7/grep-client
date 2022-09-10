package com.grepmp.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientProcessor implements Runnable {

    int port;
    String ip;
    String grepCommand;
    String serverId;
    volatile static int grepResultTotalLineCount;

    public ClientProcessor(String ip, int port, String serverId, String grepCommand){
        this.ip = ip;
        this.port = port;
        this.grepCommand = grepCommand;
        this.serverId = serverId;
    }

    public void run() {
        try {

            Socket s1 = new Socket(this.ip, this.port);
            DataOutputStream dout = new DataOutputStream(s1.getOutputStream());
            dout.writeUTF(grepCommand);
            dout.flush();

            DataInputStream dinp = new DataInputStream(s1.getInputStream());
            int byteArrayLength=dinp.readInt();
            byte[] grepCommandResultBytes=new byte[byteArrayLength];
            dinp.readFully(grepCommandResultBytes);
            String grepCommandResult=new String(grepCommandResultBytes,"UTF-8");

            System.out.println(serverId + " : " + grepCommandResult);
            grepResultTotalLineCount += Integer.parseInt(grepCommandResult);
            dout.close();
            s1.close();
        } catch (Exception e) {
            System.out.println("Machine with serverId: " + serverId + " has failed! - " + e);
        }
    }
}
