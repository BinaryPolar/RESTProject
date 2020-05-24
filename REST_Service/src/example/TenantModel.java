package example;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static example.TenantEnum.INSTANCE;

public class TenantModel {


    public static Tenant getTenant(int id){
   	 ResultSet rs = DB.getTenant(id);
   	 Tenant currentTenant = null;

 	try {
 		
		while (rs.next()) {
 	    	int id_new = rs.getInt("id");
 			String firstName = rs.getString("firstname");
        	String lastName = rs.getString("lastname");
        	String address = rs.getString("adress");
        	String city = rs.getString("city");
        	double rent = rs.getDouble("rent");
          
        	currentTenant = new Tenant(id_new, firstName, lastName, address, city, rent); 
        }
          return currentTenant;

		} catch (SQLException e) {
			e.printStackTrace();

		}
	return null;

    }

    public static ArrayList<Tenant> getTenants(){
   	 ArrayList<Tenant> tenants = new ArrayList<Tenant>();
	 ResultSet rs = DB.getTenants();

    	try {
			while(rs.next()) {
				Tenant tmp = new Tenant(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("adress"), rs.getString("city"), rs.getDouble("rent"));
				tenants.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return tenants;
    }


    public static void deleteTenant(int id){
    	DB.deleteTenant(id);
    }

    public static void postTenant(Tenant t) {
        DB.postTenant(t);
    }
    
    public static void updateTenant(Tenant t) {
    	DB.updateTenant(t);
    }

}
