package com.grepmp.app;

import com.grepmp.app.CommandReader;
import com.grepmp.app.ClientProcessor;
import com.grepmp.app.CommandReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        System.out.println("Enter GREP Command: ");
        CommandReader CD = new CommandReader();
        String grepCommand = CD.ReadCommand();
        System.out.println("input grep command received by client - " + grepCommand);

        try {
            // Getting Server Config data from properties file.
            // System.out.println("Hi");
            InputStream propertiesInputStream = new FileInputStream(
                    "./src/networkConfig.properties");
            Properties networkProperties = new Properties();
            networkProperties.load(propertiesInputStream);


            String[] hostnamesArray = networkProperties.getProperty("hostnames").split(",");
            String[] portsArray = networkProperties.getProperty("ports").split(",");
            // System.out.println("The ports from prop:" + Arrays.toString(portsArray));

            // Initalising number of servers and command string
            int N;
            if (hostnamesArray.length == portsArray.length){
                System.out.println("Number of IPs = Number of Ports = "+ hostnamesArray.length);
                N = hostnamesArray.length;
            } else{
                System.out.println("Number of IPs != Number of Ports!!\n Setting N=0");
                N = 0;
            }

            ClientProcessor[] cp = new ClientProcessor[N];
            Thread[] clientThread = new Thread[N];

            for (int i = 0; i < N; i++) {
                cp[i] = new ClientProcessor(hostnamesArray[i], Integer.parseInt(portsArray[i]), grepCommand);
                clientThread[i] = new Thread(cp[i]);
                clientThread[i].start();
                System.out.println("Client Process Thread for i=" + i);
            }

            System.out.println("Threads Started");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
