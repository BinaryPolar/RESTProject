package example;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tenant {
    public Tenant(){

    }

	//Attributes
	private int id;
	private String  firstName, lastName, address, city;
	private double rent;
	
	//Construct
	public Tenant (int id, String firstName, String lastName, String adress, String city, double rent) 
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = adress;
		this.city = city;
		this.rent = rent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}
}
