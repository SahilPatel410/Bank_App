package model;
import java.util.Scanner;

import dao_imp.EmployeeDao;

public class LoanDetails {
	EmployeeDao obj=new EmployeeDao();
	Scanner sc = new Scanner(System.in);
	int choice=0;
	int id=0;
	public void grantLoan(){
		System.out.println("Enter Account Number : ");
		int accNo=sc.nextInt();
		System.out.println("Enter Loan Amount : ");
		int loanAmt=sc.nextInt();
		System.out.println("Enter Amount Of Installment : ");
		int amtOfInstallment=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Loan Type : ");
		String loanType=sc.nextLine();
		obj.giveLoan(accNo,loanAmt,amtOfInstallment,loanType,"pending");
		System.out.println("Loan Given Successfully");
	}
	public void editLoanStatus(){
		System.out.println("Enter Loan Id : ");
		int loanId=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Loan Status : ");
		String loanStatus=sc.nextLine();
		obj.editLoanStatus(loanStatus,loanId);
	}
}
