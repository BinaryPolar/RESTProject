package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class DB
{
	public static Connection get_connection() {
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = "jdbc:sqlite:/Users/katharinaborys/eclipse-workspace/REST_Service/tenants.db";
		Connection conn = null;
		
		try{
			conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		return conn;
		
	}
	
	public static void init_table() {
        Connection conn = get_connection();
        
        System.out.println("connection made..");
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tenants (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	firstname text NOT NULL,\n"
                + "	lastname text NOT NULL,\n"
                + "	adress text NOT NULL,\n"
                + "	city text NOT NULL,\n"
                + "	rent decimal NOT NULL\n"
                + ");";

        
        try {
            Statement stmt = conn.createStatement(); 
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	public static ResultSet getTenants() {
		Connection conn = get_connection();
		
		ResultSet rs = null;
	
        String sql = "SELECT * FROM tenants;";
        try {
            Statement stmt = conn.createStatement(); 
            rs = stmt.executeQuery(sql);
            System.out.println(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
		return rs;
	}
	
	public static ResultSet getTenant(int id) {
		Connection conn = get_connection();
		ResultSet rs = null;
		
		String sql = "SELECT * FROM tenants WHERE id = ?";
		   try {
			   PreparedStatement pstmt = conn.prepareStatement(sql); 
	           pstmt.setInt(1, id);
	           rs = pstmt.executeQuery();
	           
	           //pstmt.execute(sql);
	       } catch (SQLException e) {
	           System.out.println(e.getMessage());
	       }
		 System.out.println(rs);
  		return rs;
		
	}
	
	
	public static void postTenant(Tenant t) {
		Connection conn = get_connection();
		
		String sql = "INSERT INTO tenants(id, firstname,lastname,adress,city,rent) VALUES($next_id, ?,?,?,?,?)";

		   try {
			   
	           PreparedStatement pstmt = conn.prepareStatement(sql); 
	           pstmt.setString(2, t.getFirstName());
	           pstmt.setString(3, t.getLastName());
	           pstmt.setString(4, t.getAddress());
	           pstmt.setString(5, t.getCity());
	           pstmt.setDouble(6, t.getRent());
	           pstmt.executeUpdate();

	       } catch (SQLException e) {
	           System.out.println(e.getMessage());
	       }
		System.out.println("Saved tenant.");
	}
	
	//initializing from TenantEnum.INSTANCE
  	public static void init_dummy_data() {
		Connection conn = get_connection();
		
		String sql = "INSERT INTO tenants(firstname,lastname,adress,city,rent) VALUES(?,?,?,?,?)";
		ArrayList<Tenant> base_tenants = new ArrayList<>(TenantEnum.INSTANCE.getTenants().values());		
		Iterator i = base_tenants.iterator();

		while (i.hasNext()) {
		   Tenant tmp = (Tenant) i.next();
		   try {
	           PreparedStatement pstmt = conn.prepareStatement(sql); 
	           pstmt.setString(1, tmp.getFirstName());
	           pstmt.setString(2, tmp.getLastName());
	           pstmt.setString(3, tmp.getAddress());
	           pstmt.setString(4, tmp.getCity());
	           pstmt.setDouble(5, tmp.getRent());
	           pstmt.executeUpdate();
	       } catch (SQLException e) {
	           System.out.println(e.getMessage());
	       }
		}
  	}
  	
  	public static void deleteTenant(int id) {
  		Connection conn = get_connection();
  		
  		String id_String = String.valueOf(id);
  		String sql = "DELETE FROM tenants WHERE id = ?";
  		
		   try {
	           PreparedStatement pstmt = conn.prepareStatement(sql); 
	           pstmt.setInt(1, id);
	           pstmt.executeUpdate();
	           
	           //pstmt.execute(sql);
	       } catch (SQLException e) {
	           System.out.println(e.getMessage());
	       }
  		
  	}
  	
  	public static void updateTenant(Tenant t) {
  		Connection conn = get_connection();
  		
  		String id_String = String.valueOf(t.getId());
  		String sql = "UPDATE tenants SET lastname = ? WHERE id = ?";
  		
		   try {
	           PreparedStatement pstmt = conn.prepareStatement(sql); 
	           pstmt.setString(1, t.getLastName());
	           pstmt.setString(2, id_String);
	           pstmt.executeUpdate();
	           
	           //pstmt.execute(sql);
	       } catch (SQLException e) {
	           System.out.println(e.getMessage());
	       }
  		
  	}
  
  public static void main(String[] args) throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");
    //init_table();
    //init_dummy_data();
  }

}