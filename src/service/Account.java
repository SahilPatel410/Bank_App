package service;

import java.util.Scanner;
import dao_imp.EmployeeDao;
import model.AdminProfile;
import model.EmployeeProfile;
import model.AccountDetails;
import service.UserServices;
public class Account {
	EmployeeDao objEmp = new EmployeeDao();
	AccountDetails objAcc = new AccountDetails();
	public Account(){
		String choice=null;
		Scanner sc=new Scanner(System.in);
		do{
			System.out.println("------------------------------------");	
			System.out.println("Enter 1 to Create Account");
			System.out.println("Enter 2 for Check Balance");
			System.out.println("Enter 3 for Deposite Money");
			System.out.println("Enter 4 for Withdraw Money");
			System.out.println("Enter 5 for Transfer Money");
			System.out.println("Enter 6 for View Account Details");
			System.out.println("Enter 7 for Edit Account Details");
			System.out.println("Enter 8 for view Accounts");
			System.out.println("Enter 9 for Back to main Menu");
			System.out.println("------------------------------------");	
			choice = sc.nextLine();
			switch(choice){
			case "1":
				objAcc.addAccount();
				break;
			case "2":
				int bal=objAcc.checkBalance();
				System.out.println("Your Balance is Rs "+bal);
				break;
			case "3":
				int amt=objAcc.deposite();
				System.out.println("Total Balance is "+amt);
				break;
			case "4":
				int amt1=objAcc.withdraw();
				System.out.println("Total Balance is "+amt1);
				break;
			case "5":
				boolean tStatus=objAcc.transfer();
				if(tStatus){System.out.println("Transaction Completed Successfully..!!");}
				else{System.out.println("Transaction Failed..!!");}
				break;
			case "6":
				objAcc.accountdetails();
				break;
			case "7":
				objAcc.editAccDetails();
				break;
			case "8":
				objEmp.getAllAccDetails();
				break;
			case "9":
				UserServices obj=new UserServices(3);
				break;
			default:
				System.out.println("Enter right choice");
				break;
			}
		}while(!choice.equals("9"));
	}
}
