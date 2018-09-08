package softwarepulse.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteTest {
    private static Connection con;
    private static Boolean hasData=false;
    public ResultSet displayUsers() throws SQLException, ClassNotFoundException{
        if(con==null){
            getConnection();
        }
        
        Statement state=con.createStatement();
        ResultSet rs=state.executeQuery("select fname, lname from user");
        return rs;
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.jdbc");
        con=DriverManager.getConnection("jdbc:sqlite:SQLiteTest1.db");
        initialise();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
