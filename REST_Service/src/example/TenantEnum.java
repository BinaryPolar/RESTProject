package example;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonValue;

public enum TenantEnum {
    INSTANCE;

    private Map<Integer, Tenant> contentProvider = new HashMap<>();

    private TenantEnum() {

    	Tenant f1 = new Tenant(1, "Max", "Mustermann", "Musterstrasse 1","Dortmund", 450.0);
    	Tenant f2 = new Tenant(2, "Maxine", "Musterfrau", "Musterallee 8","Essen", 255.50);
    	Tenant f3 = new Tenant(3, "Peter", "Lustig", "25","Bochum", 679.25);
    	Tenant f4 = new Tenant(4, "Ibims", "EinsUSer", "Lol 4","Düsseldorf", 900.25);
    	Tenant f5 = new Tenant(5, "Farid", "Bang", "JungBrutalGutaussehend 42","Düsseldorf", 335.80);

        contentProvider.put(1,f1);
        contentProvider.put(2,f2);
        contentProvider.put(3,f3);
        contentProvider.put(4,f4);
        contentProvider.put(5,f5);
    }
    
    @JsonValue
    public Map<Integer, Tenant> getTenants(){
        return contentProvider;
    }


    public void addTenant(Tenant t){
        contentProvider.put(t.getId(),t);
    }


    public void removeTenant(int id){
        contentProvider.remove(id);
    }
    
    public void updateTenant(Tenant t) {
    	int index = t.getId();
    	contentProvider.replace(index, t);
    	
    	}
}