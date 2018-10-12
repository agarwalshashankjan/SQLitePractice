/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarepulse.app;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yoshido itsuka
 */
public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQLiteTest test=new SQLiteTest();
        ResultSet rs=test.displayUsers();
        test.addUser("Vikas", "Yadav");
        while(rs.next()){
            
            System.out.println(rs.getString("fname")+" "+rs.getString("lname"));
        }
    }
    
}
