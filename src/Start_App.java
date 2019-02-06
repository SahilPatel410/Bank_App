import java.util.*;
public class Start_App {
	
	
	public static void main(String args[])
	{
		int choice;
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("1.Admin");
		System.out.println("2.Employee");
		choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			System.out.println("Welcome Admin");
			break;
		case 2:
			System.out.println("Welcome Employee");
			break;
		default:
			System.out.println("Wrong choice");
		
		}
		System.exit(0);
	}

}
