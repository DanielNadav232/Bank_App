package Model;

import Database.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Saving extends Account
{
    private int saved_money;

    public Saving(int balance,int saved_money) {
        super(balance);
        this.saved_money=saved_money;
        this.updateSaving();
    }

    public int getSaved_money() {
        return saved_money;
    }

    public void setSaved_money(int saved_money) {
        this.saved_money = saved_money;
        this.updateSaving();
    }

    public void saveMoney(int sum){
        this.saved_money+=sum;
        this.updateSaving();
    }

    public void updateSaving(){
    Connection con = ConnectionManager.getConnection();
    try {
        String query = "UPDATE account SET saving = ? WHERE account_id = ?;";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1,this.saved_money);
        preparedStmt.setInt(2,this.getAccountId());
        preparedStmt.executeUpdate();
        preparedStmt.close();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
}
}
