package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stocks {

    public static void displayMaxData(ResultSet rs) throws SQLException {
        while (rs.next()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("Stock: " + rs.getString("symbol") + " price: " + rs.getDouble("MAX(price)"));
            System.out.println(buffer.toString());
        }
    }

    public static void displayMinData(ResultSet rs) throws SQLException {
        while (rs.next()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("Stock: " + rs.getString("symbol") + " price: " + rs.getDouble("MIN(price)"));
            System.out.println(buffer.toString());
        }
    }

    public static void displayVolumeData(ResultSet rs) throws SQLException {
        int totalVolume = 0;
        while(rs.next()) {
            totalVolume += rs.getInt("volume");
        }
        System.out.println("Total volume: " + totalVolume);
    }

}
