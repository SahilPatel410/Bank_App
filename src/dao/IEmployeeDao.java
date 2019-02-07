package dao;

public interface IEmployeeDao {
	
	public void Add_Employee(String fname,String lname,String city,int age,int contact,String role);
	public int Edit_Employee(String fname,String lname, String city,int age,int contact,String role,int id);
	public void View_Employee();
	public int Remove_Employee(int id);
	public void search(String fname);
	
	public void Add_New_Account(int acc_no,String acc_type,String acc_holder_name,int acc_bal);
	public void Get_Acc_Details(int acc_no);
	public int Deposite(int amt, int acc_no);
	public int Withdraw(int amt, int acc_no);
	public Boolean Transfer(int amt, int acc_no_from,int acc_no_to);
	public int Check_bal(int acc_no);
	public void Get_All_Acc_Details();
	
	public void Check_all_loan_status();
	public void Give_Loan(int acc_no,int loan_amt,int amt_of_installment,String loan_type,String loan_status);
	

}
