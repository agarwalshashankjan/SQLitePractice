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
        
        initialise();
    }

    private void initialise() throws SQLException {
        
        if(!hasData){
            
            hasData=true;
            Statement state=con.createStatement();
            ResultSet rs=state.executeQuery("select name from sqlite_master where type='table' and name='user'");
         
            if(!(rs.next())){
                System.out.println("Building the user table from populated values.");
                Statement state2=con.createStatement();
                state2.execute("create table user(id integer PRIMARY KEY AUTOINCREMENT ,"
                +"fname varchar(60),"+"lname varchar(60));");
                
                PreparedStatement prep=con.prepareStatement("INSERT INTO user (fname,lname) VALUES ('Shashank','Agarwal');");
                
                PreparedStatement prep2=con.prepareStatement("insert into user (fname,lname) values(?,?);");
//                prep2.setString(1, "9");
                prep2.setString(1, "Shailesh");
                prep2.setString(2, "Singh");
                prep2.execute();
            }
        } 
    }
    
    public void addUser(String firstname, String lastname) throws ClassNotFoundException, SQLException {
        if(con==null){
            getConnection();
        }
        PreparedStatement prep=con.prepareStatement("insert into user (fname,lname) values (?,?);");
//                prep.setString(1, "2");
                prep.setString(1, firstname);
                prep.setString(2, lastname);
                prep.execute();
    }
}
