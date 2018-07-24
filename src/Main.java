import db.DBConnector;
import db.DBType;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {

        DBConnector dbConnector = new DBConnector(DBType.MYSQL);
        Scanner keyboard = new Scanner(System.in);
        String input = "4";
        do {
            System.out.println("(1) Highest Price");
            System.out.println("(2) Lowest Price");
            System.out.println("(3) Total Volume");
            System.out.println("(4) Quit");
            System.out.print("> ");
            input = keyboard.next();

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
        } while(!input.equals("4"));
    }
}
