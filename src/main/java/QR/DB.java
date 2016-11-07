package QR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DB {
public static Connection connect() throws Exception{
        Connection conn = null;
        String url;
        Statement stmt;
        ResultSet rs;
        try {
            Class.forName("org.sqlite.JDBC");
            url = "jdbc:sqlite:qr.db";
            conn = DriverManager.getConnection(url, "root", "");
      } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Error in database connection. 1");
        }
        return conn;
    }
    
    public void register(String uid, String name) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = connect();
            String query = "insert into members(uid, name) values('"+uid+"', '"+name+"')";
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } finally {
            ps.close();
            conn.close();
        }
    }
    
    static String getName(String uid) throws Exception {
        Connection conn = null;
        String name = "";
        try {
           conn = connect();
           String query = "select name from members where uid="+uid;
           Statement st = conn.createStatement();
           ResultSet res = st.executeQuery(query);
           while(res.next()){
        	   name = res.getString("name");
           }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
        return name;
    }
}
