package QR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DB {
    public Connection connect() throws Exception{
        Connection conn = null;
        String url;
        Statement stmt;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost/acm";
            conn = DriverManager.getConnection(url, "root", "");
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "Error in database connection. 1");
        }
        return conn;
    }
    
    public void insertSample(String aQuery) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = connect();
            String query = "insert into registration values(" + aQuery + ")";
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            ps.close();
            conn.close();
        }
    }
    
    Member getById(int idNum) throws Exception {
        Connection conn = null;
        Member member = new Member();
        try {
           conn = connect();
           String query = "select name, idNum, course, position from members where idNum="+idNum;
           Statement st = conn.createStatement();
           ResultSet res = st.executeQuery(query);
           while(res.next()){
               member.setName(res.getString("name"));
               member.setIdNum(res.getInt("idNum"));
               member.setCourse(res.getString("course"));
               member.setPosition(res.getString("position"));   
               //ch if(member.getIdNum()==201011202) member.setPosition("Im Awesome");
               if(member.getIdNum()==201011266) member.setPosition("Im Awesome");
           }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return member;
    }
}
