package ui;

import db.DBConnector;
import db.DBType;

import java.util.Scanner;

public class Display {

    private DBConnector dbConnector = new DBConnector(DBType.MYSQL);
    private Scanner keyboard = new Scanner(System.in);
    private String input;

    public void start() {
        do {
            prompt();
            input = keyboard.next();
            inputHandler();
        } while(!input.equals("4"));
    }

    public void prompt() {
        System.out.println("(1) Highest Price");
        System.out.println("(2) Lowest Price");
        System.out.println("(3) Total Volume");
        System.out.println("(4) Quit");
        System.out.print("> ");
    }

    public void inputHandler() {
            switch(input) {
                case "1":
                    System.out.print("Enter Date: ");
                    input = keyboard.next();
                    dbConnector.getDailyHigh(input);
                    break;
                case "2":
                    System.out.print("Enter Date: ");
                    input = keyboard.next();
                    dbConnector.getDailyLow(input);
                    break;
                case "3":
                    System.out.print("Enter Date: ");
                    input = keyboard.next();
                    dbConnector.getTotalVolume(input);
                    break;
                default:
                    break;
            }

    }

}
