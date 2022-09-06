package main.java.com.grepmp.app;
import java.util.Scanner;

public class CommandReader {

    String Command;

    public String ReadCommand(){
        Scanner sc = new Scanner(System.in);
        Command = sc.nextLine();
        sc.close();
        return Command;
    }
}
