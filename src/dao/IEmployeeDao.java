package dao;

public interface IEmployeeDao {
	
//	public void Authenticate(String Name, String Pass);
	public void authenticate(String Name, String Pass);
	public void addEmployee(String fname,String lname,String city,int age,double contact,String role);
	public int editEmployee(String fname,String lname, String city,int age,double contact,int id);
	public void viewEmployee();
	public int removeEmployee(int id);
	public void search(String fname);
	public int getEmployeeId(String fname);

	public boolean checkAccNo(int accNo);
	public void editAccountDetails(int acc_no,String accName);
	public void addNewAccount(int acc_no,String acc_type,String acc_holder_name,int acc_bal);
	public void getAccDetails(int acc_no);
	public int deposite(int amt, int acc_no);
	public int withdraw(int amt, int acc_no);
	public Boolean transfer(int amt, int acc_no_from,int acc_no_to);
	public int checkBal(int acc_no);
	public void getAllAccDetails();
	
	public void checkAllLoanStatus();
	public void giveLoan(int acc_no,int loan_amt,int amt_of_installment,String loan_type,String loan_status);
	public boolean editLoanStatus(String loanStatus,int loanId);
	}
