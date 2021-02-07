package StudMan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConClass {
    private Connection con;
    public Connection getConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentmanager",
                    "root",
                    "RamB#@2711"
            );
            return con;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void closeConnect() throws SQLException {
        con.close();
    }

}
