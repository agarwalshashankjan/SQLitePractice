package softwarepulse.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        Class.forName("org.sqlite.JDBC");
        con=DriverManager.getConnection("jdbc:sqlite:SQLiteTest1.db");
        
        System.out.println("c4");
        initialise();
    }

    private void initialise() throws SQLException {
        
        System.out.println("c5");
        if(!hasData){
            
        System.out.println("c6");
            hasData=true;
            Statement state=con.createStatement();
            ResultSet rs=state.executeQuery("select name from sqlite_master where type='table' and name='user'");
            System.out.println("c9");
            if(!rs.next()){
                System.out.println("c7");
                System.out.println("Building the user table from populated values.");
                Statement state2=con.createStatement();
                System.out.println("c8");
                state2.execute("create table user(id integer,"
                +"fname varchar(60),"+"lname varchar(60),"
                +"primary key(id));");
                
        System.out.println("c3");
                PreparedStatement prep=con.prepareStatement("insert into user values(?,?,?);");
                prep.setString(2, "Shahsank");
                prep.setString(3, "Agarwal");
                prep.execute();
                
                PreparedStatement prep2=con.prepareStatement("insert into user values(?,?,?);");
                prep.setString(2, "Shailesh");
                prep.setString(3, "Singh");
                prep2.execute();
            }
        } 
    }
    
    public void addUser(String firstname, String lastname) throws ClassNotFoundException, SQLException {
        if(con==null){
            getConnection();
        }
        PreparedStatement prep=con.prepareStatement("insert into user values(?,?,?);");
                prep.setString(2, firstname);
                prep.setString(3, lastname);
                prep.execute();
    }
}
