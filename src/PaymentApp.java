import java.sql.*;
import java.util.*;

class Registrations{
	 String name;
	 String email;
	 String pass;
	
	static Connection con = null;
	void registeruser() throws SQLException
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee", "root", "Pass@123");
	        PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?)");
	        
	        Scanner sc = new Scanner(System.in);
	        
	        System.out.println("Name:");
	        name = sc.next();
	       
	        System.out.println("Enter Mail ID:");
	        email = sc.next();
	        
	        System.out.println("Enter the Password:");
	        pass = sc.next();
	        
	        ps.setString(1, name);
	        ps.setString(2, pass);
	        ps.setString(3,email );
	        
	        ps.execute();
	        System.out.println("Hi "+name+" you have registered successfully");
	        
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		
	}
	
	
	boolean userpres()throws SQLException
	{
		boolean flag = false;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee", "root", "Pass@123");
	        
	        Scanner sc = new Scanner(System.in);
	        
	        System.out.println("Enter Mail ID");
	        String emailv = sc.next();
	        
	        System.out.println("Enter the password");
	        String passwordv = sc.next();
	        
	        PreparedStatement ps = con.prepareStatement("select * from user");
	        ResultSet rs = ps.executeQuery();
	        
	        while(rs.next())
	        {
	        	String a = rs.getString(3);
	        	String x = rs.getString(2);
	        	if(a.equals(emailv) && x.equals(passwordv))
	        	{
	        		flag = true;
	        		return flag;
	        		
	        	}		
	        	
	        }
		}
		catch(Exception e){
			System.out.println(e);
		}

		return flag;
		
	}
	
}


class Operations{
	String name;
	String accno;
	String ifsc;
	String bal;
	String phno;
	
	Connection con=null;
	
	void register() throws SQLException
	{
		try {

			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Enter Name: ");
			String name = scanner.next();
			System.out.println("Enter Account Number: ");
			String accno = scanner.next();
			System.out.println("Enter IFSC code: ");
			String ifsc = scanner.next();
			System.out.println("Enter Balance: ");
			String bal = scanner.next();
			System.out.println("Enter Phone Number: ");
			String phno = scanner.next();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee","root","Pass@123");
			PreparedStatement pstmt = con.prepareStatement("insert into bank values(?,?,?,?,?)");
			
		
			pstmt.setString(1, name);
			pstmt.setString(2, accno);
			pstmt.setString(3, ifsc);
			pstmt.setString(4, bal);
			pstmt.setString(5, phno);
			

			pstmt.execute();
			
			System.out.println("Data inserted");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	  }
	
	
	
	 void checkbal() throws SQLException{
		
		
		try {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter either the ACCOUNT NUMBER or PHONE NUMBER:");
			String entry = sc.next();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee","root","Pass@123");
			PreparedStatement pstmt = con.prepareStatement("Select * from bank where accno=? OR phno = ?");
			
			pstmt.setString(1, entry);
			pstmt.setString(2, entry);
			
			ResultSet rs = pstmt.executeQuery();			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <=count; i++) 
			{
				System.out.print(rsmd.getColumnLabel(i)+" \t ");
				
			}
			System.out.println();
			
			while (rs.next()) {
				System.out.println(rs.getString(1)+ " \t " +rs.getString(2)+" \t " +rs.getString(3)+" \t " +rs.getString(4)+" \t " +rs.getString(5));
			}

			System.out.println("Data Displayed");
	} 
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
}
	 
	 
	void transfer() throws SQLException {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "Pass@123");
		
		Scanner s=new Scanner(System.in);
		

		System.out.println("------------CHOOSE OPTION--------------------");
		System.out.println("1.Account");
		System.out.println("2.Phone");
		int x=s.nextInt();
		
		if(x==1) {
			
		PreparedStatement pstmt=con.prepareStatement("update bank set bal = bal + ? where accno=?");
		Scanner s1=new Scanner(System.in);
		
		System.out.println("Enter the receiver account number");
		accno=s1.next();
		
		System.out.println("Enter amount");
		bal=s1.next();
		
		pstmt.setString(1,bal);
		pstmt.setString(2,accno);
		
		pstmt.execute();
		
		PreparedStatement pstmt2 = con.prepareStatement("update bank set bal = bal - ? where name = 'Vish'");
		pstmt2.setString(1, bal);
		
		pstmt2.execute();
	
		}
		
		
		 
		if(x==2)
	        {
	        	PreparedStatement ps = con.prepareStatement("update bank set bal = bal + ? where phno=?");
	        	
	        	Scanner sc = new Scanner(System.in);
	        	
	        	System.out.println("Enter the phone number to which the money is to be transfered");
	        	phno = sc.next();
	        	
	        	System.out.println("Enter the amount to be transfered");
	        	String balt = sc.next();
	        	
	        	ps.setString(1, balt);
	        	ps.setString(2, phno);
	        	
	        	ps.execute();
	        	
	        	PreparedStatement ps2 = con.prepareStatement("update bank set bal = bal - ? where name = 'Vish'");
	        	ps2.setString(1, balt);
	        	ps2.execute();
	        	
	        }
		}
		
		catch(Exception e) {
		System.out.println(e);
		}
		
		}
	}
	


public class PaymentApp {
	public static void main(String[] args) throws SQLException {
		int ch;
		
		Scanner sc = new Scanner(System.in);
		
		Registrations r = new Registrations();
		Operations o = new Operations();
		
		do {
			System.out.println("**************MOBILE APP****************");
			System.out.println("1.Login");
			System.out.println("2.Perform Operations");
			System.out.println("3.Exit");
			System.out.println("*******************************************");
			System.out.println("Enter the Choice");
			ch = sc.nextInt();
			if (ch==1)
			{
				
				r.registeruser();
				
			}
			else if(ch==2)
			{
				if(r.userpres()==true)
				{
					
					do {
						System.out.println("************OPERATIONS*************");
						System.out.println("1.Register");
						System.out.println("2.Check Balance");
						System.out.println("3.Transfer");
						System.out.println("4.Exit");
						System.out.println("--------------------------------");
						System.out.println("Enter your Choice: ");

						ch=sc.nextInt();
						
						if(ch==1)
						{
							o.register();
						}
						else if(ch==2)
						{
							o.checkbal();
						}
						else if(ch==3)
						{
							o.transfer();
						}
						else {
							System.exit(0);
						}
						}while(true);
				}
				
				else
				{
					System.out.println("USER NOT FOUND!!!!!!");
					System.out.println();
				}
				
			}
			else
			{
				System.exit(0);
			}
			
		}while(true);
}
}