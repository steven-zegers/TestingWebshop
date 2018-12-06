package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String password;
	private String firstName;
	private String lastName;
	private ShoppingCart shoppingCart;
	private String userid;
	private String streetName;
	private int streetNumber;
	private String city;

	public Person() {

	}
	public Person(String userId, String password, String firstName, String lastName, String streetName, int streetNumber, String city) {
		setUserid(userId);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setStreetName(streetName);
		setStreetNumber(streetNumber);
		setCity(city);
	}

	public void setHashedPassword(String password) {
		this.password = password;
	}

	public String hashPassword(String password) {
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-512");
			crypt.reset();
			byte[] 	passwordBytes = password.getBytes("UTF-8");
			crypt.update(passwordBytes);
			byte[] digest = crypt.digest();
			BigInteger digestAsBigInteger = new BigInteger(1, digest);
			return digestAsBigInteger.toString(16);
		} catch(NoSuchAlgorithmException e) {
			throw new DomainException("Encryptie wordt niet ondersteund");
		} catch(UnsupportedEncodingException e) {
			throw new DomainException("Encryptie wordt niet ondersteund");
		}

	}

	public String getPassword() {
		return password;
	}

	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		System.out.println("Eerste passwoord: " + getPassword());
		System.out.println("Tweede passwoord: " + hashPassword(password));
		return getPassword().equals(hashPassword(password));
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = hashPassword(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public ShoppingCart getShoppingCart() {
		return this.shoppingCart;
	}

	@Override
	public String toString(){
		return getFirstName() + " " + getLastName();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userId) {
		this.userid = userId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		if (streetNumber <= 0) {
			throw  new IllegalArgumentException("Your street number cannot be smaller or equal than 0.");
		}
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
