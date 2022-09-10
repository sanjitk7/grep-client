package com.grepmp.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientProcessor implements Runnable {

    int port;
    String ip;
    String grepCommand;

    public ClientProcessor(String ip, int port, String grepCommand){
        this.ip = ip;
        this.port = port;
        this.grepCommand = grepCommand;
    }

    public void run() {
        try {

            System.out.println("This is a client process thread with tid: "+ Thread.currentThread().getId());
            Socket s1 = new Socket(this.ip, this.port);
            DataOutputStream dout = new DataOutputStream(s1.getOutputStream());
            dout.writeUTF(grepCommand);
            dout.flush();

            // System.out.println("x0");
            // Thread.sleep(5000);
            // System.out.println("x1");

            DataInputStream dinp = new DataInputStream(s1.getInputStream());
            int byteArrayLength=dinp.readInt();
            byte[] grepCommandResultBytes=new byte[byteArrayLength];
            dinp.readFully(grepCommandResultBytes);
            String grepCommandResult=new String(grepCommandResultBytes,"UTF-8");
            // String grepResult = (String) dinp.readAllBytes();



            System.out.println("The GREP Result received by Client: " + grepCommandResult);
            dout.close();
            s1.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
