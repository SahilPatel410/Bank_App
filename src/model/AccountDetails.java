package model;

import java.util.Scanner;

import dao_imp.EmployeeDao;

public class AccountDetails {
	EmployeeDao obj=new EmployeeDao();
	Scanner sc = new Scanner(System.in);
	int choice=0;
	int id=0;
	public void addAccount(){
		System.out.println("Enter Following Detais to create new account");
		System.out.println("Enter Account Number:");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Account holder name:");
		String hname = sc.nextLine();
		System.out.println("Enter Account Type:");
		String type = sc.nextLine();
		obj.addNewAccount(number,type,hname,2000);
		System.out.println("Account created Successfully");
	}
	public int checkBalance(){
		System.out.print("Enter Account Number::");
		int accNo=sc.nextInt();
		return obj.checkBal(accNo);
	}
	public int deposite(){
		System.out.print("Enter Account Number::");
		int accNo=sc.nextInt();
		System.out.print("Enter Amount to deposite::");
		int amt=sc.nextInt();
		return obj.deposite(amt,accNo);
	}
	public int withdraw(){
		System.out.print("Enter Account Number::");
		int accNo=sc.nextInt();
		System.out.print("Enter Amount to Withdraw::");
		int amt=sc.nextInt();
		return obj.withdraw(amt,accNo);
	}
	public Boolean transfer(){
		System.out.print("Enter Account Number to transfer from::");
		int accNo1=sc.nextInt();
		System.out.print("Enter Account Number to transfer into::");
		int accNo2=sc.nextInt();
		System.out.print("Enter Amount to transfer::");
		int amt=sc.nextInt();
		return obj.transfer(amt,accNo1,accNo2);
	}
	public void accountdetails(){
		System.out.print("Enter Account Number::");
		int accNo=sc.nextInt();
		obj.getAccDetails(accNo);
	}
	public void editAccDetails(){
		System.out.print("Enter Account Number::");
		int accNo=sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Name to update::");
		String name=sc.nextLine();
		obj.editAccountDetails(accNo,name);
	}
}
