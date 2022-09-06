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
        System.out.println("Enter GREP Command: ");
        CommandReader CD = new CommandReader();
        String grep_command = CD.ReadCommand();
        System.out.println("input grep command received by client - "+ grep_command);

        ClientProcessor cp1 = new ClientProcessor("localhost",3001,grep_command);
        ClientProcessor cp2 = new ClientProcessor("localhost",3000,grep_command);
        Thread client_thread1 = new Thread(cp1);
        Thread client_thread2 = new Thread(cp2);
        client_thread1.start();
        client_thread2.start();
        // client_thread.join();
        System.out.println("Threads Started");
        
        
    }
}
