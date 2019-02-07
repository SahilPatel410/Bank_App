package model;
import dao_imp.*;
import java.io.*;
import java.util.*;
public class Admin {
	
	String acc_name;

	public String get_acc_name()
	
	{
		return acc_name;
	}
	public void set_acc_name(String acc_name)
	{
		acc_name= acc_name;
	}
	public static void main(String args[])
	{
	EmployeeDao obj =new EmployeeDao();
//		//obj.Add_Employee("Yesha","Dave","Bhuj",20,123456789,"Admin");
//		//int a=obj.Remove_Employee("Yesha","Dave");
//obj.search("Ram");
	obj.Check_all_loan_status();
//		//obj.View_Employee();
////		int a=obj.Edit_Employee("Ram","Dave", "Ahmedabd", 20, 7898789, "Admin");
//		
//		obj.Add_New_Account(20190701, "Saving", "Priya Sharma",2000);
		
//		Admin obj = new Admin();
//		Scanner sc = new Scanner(System.in);
//		String name=sc.nextLine();
//		
	
//	int a=obj.Check_bal(20190701);
//	System.out.print(a);
//	Boolean t_status=obj.Transfer(1000,20190701,2019003);
//	if(t_status==true)
//	{
//		System.out.print("Transcation Completed..!!");
//	}
	obj.Give_Loan(2019002,60000,8000,"Personal","Pending");
}

}
