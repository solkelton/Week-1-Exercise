package db;

import data.DataContainer;

import java.sql.*;

public class DBConnector {

    private DBType dbType = null;

    public DBConnector(DBType dbType) {
        this.dbType = dbType;
    }

    public void connect() {
        try (
                Connection conn = DBUtil.getConnection(this.dbType);
        ){

//            System.out.println("Connected!");
        } catch(SQLException e) {
            DBUtil.processException(e);
        }
    }

    public void insert(DataContainer dataContainer) {
        String insertString = "INSERT INTO stocks (symbol, price, volume, date) " + "VALUES(?, ?, ?, ?)";

        try (
                Connection conn = DBUtil.getConnection(this.dbType);
                PreparedStatement insertStatement = conn.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS);
                ){
            insertStatement.setString(1, dataContainer.getSymbol());
            insertStatement.setDouble(2, dataContainer.getPrice());
            insertStatement.setInt(3, dataContainer.getVolume());
            insertStatement.setDate(4, dataContainer.getDate());

            insertStatement.executeUpdate();

//            System.out.println("Insert Called!");
        } catch(SQLException e) {
            DBUtil.processException(e);
        }
    }

    public void getDailyHigh(String date) {
//        System.out.println("Getting daily high...");
        String highString = "SELECT symbol, MAX(price), volume " +
                "FROM stocks " +
                "WHERE date = DATE(?)";
        try (
                Connection conn = DBUtil.getConnection(this.dbType);
                PreparedStatement highStatement = conn.prepareStatement(highString, Statement.RETURN_GENERATED_KEYS);
        ){
            highStatement.setString(1, date);
            ResultSet rs = highStatement.executeQuery();
//            System.out.println("Displaying results...");
            Stocks.displayMaxData(rs);

        } catch(SQLException e) {
            DBUtil.processException(e);
        }
    }

    public void getDailyLow(String date) {
//        System.out.println("Getting daily low...");
        String lowString = "SELECT symbol, MIN(price), volume " +
                "FROM stocks " +
                "WHERE date = DATE(?)";
        try (
                Connection conn = DBUtil.getConnection(this.dbType);
                PreparedStatement lowStatement = conn.prepareStatement(lowString, Statement.RETURN_GENERATED_KEYS);
        ){
            lowStatement.setString(1, date);
            ResultSet rs = lowStatement.executeQuery();
//            System.out.println("Displaying results...");
            Stocks.displayMinData(rs);

        } catch(SQLException e) {
            DBUtil.processException(e);
        }
    }

    public void getTotalVolume(String date) {
        String totalVolume = "SELECT volume " +
                             "FROM stocks " +
                             "WHERE date = DATE(?)";
        try (
                Connection conn = DBUtil.getConnection(this.dbType);
                PreparedStatement volumeStatement = conn.prepareStatement(totalVolume, Statement.RETURN_GENERATED_KEYS);
        ){
            volumeStatement.setString(1, date);
            ResultSet rs = volumeStatement.executeQuery();
//            System.out.println("Displaying results...");
            Stocks.displayVolumeData(rs);

        } catch(SQLException e) {
            DBUtil.processException(e);
        }
    }


}
