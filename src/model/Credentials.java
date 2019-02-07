package model;
import java.util.*;
import model.verifyCredentials;
public class Credentials {
	public static void main(String args[]){
		String user_in;
		String pass;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Credentials for Login!");
		System.out.println("Enter Username");
		user_in = sc.nextLine();
		System.out.println("Enter Password");
		pass = sc.nextLine();
		verifyCredentials ob = new verifyCredentials();
		ob.Authenticate(user_in, pass);
	}
}
