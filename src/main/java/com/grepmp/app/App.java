package com.grepmp.app;

import main.java.com.grepmp.app.CommandReader;
import main.java.com.grepmp.app.ClientProcessor;

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

        // Initalising number of servers and command string
        int N = 1;
        System.out.println("Enter GREP Command: ");
        CommandReader CD = new CommandReader();
        String grepCommand = CD.ReadCommand();
        System.out.println("input grep command received by client - " + grepCommand);

        try {
            // Getting Server Config data from properties file.
            // System.out.println("Hi");
            InputStream propertiesInputStream = new FileInputStream(
                    "/Users/sanjitkumar/personal_projects/CollegeIllinois/DistributedSystems/GrepDistributed/grepmp/src/networkConfig.properties");
            Properties networkProperties = new Properties();
            networkProperties.load(propertiesInputStream);


            String[] hostnamesArray = networkProperties.getProperty("hostnames").split(",");
            String[] portsArray = networkProperties.getProperty("ports").split(",");
            // System.out.println("The ports from prop:" + Arrays.toString(portsArray));

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
