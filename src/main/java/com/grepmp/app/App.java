package com.grepmp.app;
import main.java.com.grepmp.app.CommandReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Enter GREP Command: ");
        CommandReader CD = new CommandReader();
        String grep_command = CD.ReadCommand();
        System.out.println("input grep command received by client - "+ grep_command);

        try {
            Socket s1 = new Socket("localhost", 3000);
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
            System.out.println("The GREP Result received by Client: "+grep_result);
            dout.close();
            s1.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
