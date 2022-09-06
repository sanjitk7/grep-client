package main.java.com.grepmp.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientProcessor implements Runnable {

    int port;
    String ip;
    String grep_command;

    public ClientProcessor(String ip, int port, String grep_command){
        this.ip = ip;
        this.port = port;
        this.grep_command = grep_command;
    }

    public void run() {
        try {

            System.out.println("This is a client process thread with tid: "+ Thread.currentThread().getId());
            Socket s1 = new Socket(this.ip, this.port);
            DataOutputStream dout = new DataOutputStream(s1.getOutputStream());
            dout.writeUTF(grep_command);
            dout.flush();

            // System.out.println("x0");
            // Thread.sleep(5000);
            // System.out.println("x1");

            DataInputStream dinp = new DataInputStream(s1.getInputStream());
            // System.out.println("x2");
            String grep_result = (String) dinp.readUTF();
            // System.out.println("x3");
            System.out.println("The GREP Result received by Client: " + grep_result);
            dout.close();
            s1.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
