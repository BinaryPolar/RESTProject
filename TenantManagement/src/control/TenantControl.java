package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Tenant;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.UniformInterfaceException;


public class TenantControl {

	String BASE_URL = "http://localhost:8080/REST_Service/initial/";
	Client client = Client.create(new DefaultClientConfig());
	WebResource resource = client.resource(BASE_URL).path("");
	ClientResponse response = null;	
	
	ObjectMapper mapper = new ObjectMapper();

	
    //Fetching tenants 
    public ArrayList<Tenant> getTenantList() 
    {
    	
		response = resource.path("")
				.accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		
		String output = response.getEntity(String.class);
		output = output.substring(10,output.length()-1);
		int statusCode = response.getStatus();
		
		try {
			ArrayList<Tenant> listTenant = mapper.readValue(output, new TypeReference<ArrayList<Tenant>>(){});
			//9,1 List<Tenant> myObjects = Arrays.asList(mapper.readValue(response.getEntity(String.class), Tenant[].class));
			return listTenant;
	    	
		
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
    }
	
	public ClientResponse deleteTenant(Tenant t) {
		String s = "" + t.getId();
		return resource.path("" + s).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
    }
	
	public ClientResponse postTenant(Tenant t) {
		try {
			String json = mapper.writeValueAsString(t);
			ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;	
	}


	public ClientResponse updateTenant(Tenant t) {
		try {
			String json = mapper.writeValueAsString(t);
			ClientResponse response = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	} 

}
    
