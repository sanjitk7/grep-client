package com.grepmp.app;
import main.java.com.grepmp.app.CommandReader;
import main.java.com.grepmp.app.ClientProcessor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
        int N = 2;
        System.out.println("Enter GREP Command: ");
        CommandReader CD = new CommandReader();
        String grepCommand = CD.ReadCommand();
        System.out.println("input grep command received by client - "+ grepCommand);

        int[] portsList = new int[]{3000,3001};
        ClientProcessor[] cp = new ClientProcessor[N];
        Thread[] clientThread = new Thread[N];

        for (int i=0; i<N;i++){
            cp[i] = new ClientProcessor("localhost",portsList[i],grepCommand);
            clientThread[i] = new Thread(cp[i]);
            clientThread[i].start();
            System.out.println("Client Process Thread for i="+i);
        }
        
        System.out.println("Threads Started");
        
        
    }
}
