package dao;

public interface IEmployeeDao {
	
	public void Add_Employee(String name,String city,int age,int contact,String role);
	public int Edit_Employee(String name,String city,int age,int contact,String role);
	public void View_Employee();
	public int Remove_Employee(String name);

}
