import java.io.*;
import java.sql.*;
import java.util.*;

public class JdbcDemo 
{
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

  static final String DB_URL = "jdbc:mysql://localhost/mayank2?useSSL=false";

  static final String USER = "mayank";
  static final String PASS = "Mayank@917"; 

  static Scanner sc = new Scanner(System.in);

  static void add_employee(Statement stmt) 
  {
    String sql;
    
    int zero = 0;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Employee ID: ");
    int id = sc.nextInt();

    System.out.print("First Name: ");
    String firstName = sc.next();

    System.out.print("Last Name: ");
    String lastName = sc.next();

    System.out.print("Email: ");
    String email = sc.next();

    System.out.print("Job ID(Set -1 for assigning NULL): ");
    int jid = sc.nextInt();

    System.out.print("Phone Number: ");
    String phoneNumber = sc.next();

    System.out.print("Salary: ");
    int salary = sc.nextInt();

    System.out.print("Department ID(Set -1 for assigning NULL): ");
    int department_id = sc.nextInt();

    try 
    {
      if (jid != -1 && department_id != -1) sql ="INSERT into Employees VALUES (" +id +", '" +firstName +"', '" +lastName +"', '" +email +"', " +jid +", '" +phoneNumber +"', " +salary + ", " + department_id + ");";
      else sql ="INSERT into Employees(employee_id,first_name,last_name,email,phone_number,salary) VALUES (" +id +", '" +firstName +"', '" +lastName +"', '" +email +"', '" +phoneNumber + "', " + zero +");";
      
      stmt.executeUpdate(sql);
      System.out.println("");

      System.out.println("Employee Added Successfully to the Table");
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void remove_employee(Statement stmt) 
  {
    String sql;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Employee ID who you want to remove: ");
    int id = sc.nextInt();

    try 
    {
      sql = "Delete from Employees where employee_id = " + id;

      stmt.executeUpdate(sql);
      System.out.println("");

      System.out.println("Employee Removed");
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void remove_dept(Statement stmt) 
  {
    String sql;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Department ID you want to remove: ");
    int id = sc.nextInt();

    try 
    {
      sql = "Delete from Departments where department_id = " + id;

      stmt.executeUpdate(sql);
      System.out.println("");

      System.out.println("Department Removed");
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void remove_job(Statement stmt) 
  {
    String sql;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Job ID who you want to remove: ");
    int id = sc.nextInt();

    try 
    {
      sql = "Delete from Jobs where job_id = " + id;

      stmt.executeUpdate(sql);
      System.out.println("");

      System.out.println("Job Removed");
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void list_of_employees(Statement stmt) 
  {
    String sql;

    sql ="SELECT employee_id \"ID\", CONCAT(first_name," +" \" \" " +",last_name) as Name, email \"Email ID\", phone_number \"Phone Number\", salary \"Salary\" from Employees;";

    try 
    {
      ResultSet rs = stmt.executeQuery(sql);
      // Integer count = 0;

      System.out.println("Printing out the list of Employees");
      System.out.println("");

      System.out.println("+--------------------------------------------------------------------------------------+");
			System.out.printf("| %3s | %-24s | %-20s | %15s | %-10s |\n", "ID", "Name", "Email id", "Phone Number", "Salary");
			System.out.println("+--------------------------------------------------------------------------------------+");

			while(rs.next())
			{
				System.out.printf("| %3d |", rs.getInt("ID"));
				System.out.printf(" %-24s | %-20s |", rs.getString("Name"), rs.getString("Email ID"));
				System.out.printf(" %15s |", rs.getString("Phone Number"));
				System.out.printf(" %10s |\n", rs.getString("Salary"));
			}

			System.out.println("+--------------------------------------------------------------------------------------+");

      System.out.println("");
      rs.close();
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void list_of_departments(Statement stmt) 
  {
    String sql;

    sql ="SELECT department_id \"ID\", department_name as Name from Departments;";

    try 
    {
      ResultSet rs = stmt.executeQuery(sql);
      // Integer count = 0;

      System.out.println("Printing out the list of Departments");
      System.out.println("");

      System.out.println("+--------------------------------+");
			System.out.printf("| %3s | %-24s |\n", "ID", "Name");
			System.out.println("+--------------------------------+");

			while(rs.next())
			{
				System.out.printf("| %3d |", rs.getInt("ID"));
				System.out.printf(" %-24s |\n", rs.getString("Name"));
			}

			System.out.println("+--------------------------------+");
      
      System.out.println("");
      rs.close();
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void list_of_jobs(Statement stmt) 
  {
    String sql;

    sql ="SELECT job_id \"ID\", job_title as Title from Jobs;";

    try 
    {
      ResultSet rs = stmt.executeQuery(sql);

      System.out.println("Printing out the list of Jobs");
      System.out.println("");

      System.out.println("+--------------------------------+");
			System.out.printf("| %3s | %-24s |\n", "ID", "Title");
			System.out.println("+--------------------------------+");

			while(rs.next())
			{
				System.out.printf("| %3d |", rs.getInt("ID"));
				System.out.printf(" %-24s |\n", rs.getString("Title"));
			}

			System.out.println("+--------------------------------+");

      System.out.println("");
      rs.close();
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void list_particular_dependents(Statement stmt) 
  {
    String sql;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Employee ID: ");
    int id = sc.nextInt();

    sql ="SELECT d.dependent_id as ID, CONCAT(d.first_name," +" \" \" " +",d.last_name) as Name from Dependents d inner join Employees e on e.employee_id = d.employee_id and e.employee_id = "+id+";";

    try 
    {
      ResultSet rs = stmt.executeQuery(sql);

      System.out.println("Printing out the list of Dependent of the Selected Employee");
      System.out.println("");

      System.out.println("+--------------------------------+");
			System.out.printf("| %3s | %-24s |\n", "ID", "Name");
			System.out.println("+--------------------------------+");

			while(rs.next())
			{
				System.out.printf("| %3d |", rs.getInt("ID"));
				System.out.printf(" %-24s |\n", rs.getString("Name"));
			}

			System.out.println("+--------------------------------+");

      System.out.println("");
      rs.close();
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void nalla_employees(Statement stmt) 
  {
    String sql;

    sql ="SELECT employee_id \"ID\", CONCAT(first_name," +" \" \" " +",last_name) as Name from Employees where job_id is NULL";

    try 
    {
      ResultSet rs = stmt.executeQuery(sql);

      System.out.println("Printing out the list of Nalla Employees");
      System.out.println("");

      System.out.println("+--------------------------------+");
			System.out.printf("| %3s | %-24s |\n", "ID", "Name");
			System.out.println("+--------------------------------+");

			while(rs.next())
			{
				System.out.printf("| %3d |", rs.getInt("ID"));
				System.out.printf(" %-24s |\n", rs.getString("Name"));
			}

			System.out.println("+--------------------------------+");

      rs.close();
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void avg_employees(Statement stmt) 
  {
    String sql;

    sql ="SELECT employee_id \"ID\", CONCAT(first_name," +" \" \" " +",last_name) as Name from Employees where salary >= (select avg(salary) from Employees);";
    
    try 
    {
      ResultSet rs = stmt.executeQuery(sql);

      System.out.println("Printing out the list of Employees above average");
      System.out.println("");

      System.out.println("+--------------------------------+");
			System.out.printf("| %3s | %-24s |\n", "ID", "Name");
			System.out.println("+--------------------------------+");

			while(rs.next())
			{
				System.out.printf("| %3d |", rs.getInt("ID"));
				System.out.printf(" %-24s |\n", rs.getString("Name"));
			}

			System.out.println("+--------------------------------+");

      rs.close();
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }
    
    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void vacancy(Statement stmt) 
  {
    String sql;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Job ID: ");
    int id = sc.nextInt();

    System.out.print("Job Title: ");
    String job_title = sc.next();

    System.out.print("Min Salary(Set -1 for assigning NULL): ");
    int min = sc.nextInt();

    System.out.print("Max Salary(Set -1 for assigning NULL): ");
    int max = sc.nextInt();

    try 
    {
      if (min != -1 && max != -1) sql ="INSERT INTO Jobs Values (" +id +",'" +job_title +"'," +min +"," +max +");"; 
      else if (min == -1 && max != -1) sql ="INSERT INTO Jobs( job_id,job_title,max_salary) Values (" +id +",'" +job_title +"'," +max +");"; 
      else if (max == -1 && min != -1) sql ="INSERT INTO Jobs( job_id,job_title,min_salary) Values (" +id +",'" +job_title +"'," +min +");"; 
      else sql ="INSERT INTO Jobs( job_id,job_title) Values (" +id +",'" +job_title +"');";

      stmt.executeUpdate(sql);
      System.out.println("");

      System.out.println("Job Added Successfully to the Table");
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void add_department(Statement stmt) 
  {
    String sql;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Department ID: ");
    int id = sc.nextInt();

    System.out.print("Department Name: ");
    String departmentName = sc.next();

    System.out.print("Location Id: ");
    int location_id = sc.nextInt();

    try 
    {
      sql ="INSERT into Departments VALUES (" +id +", '" +departmentName +"', " +location_id +");";

      stmt.executeUpdate(sql);
      System.out.println("");

      System.out.println("Department Added Successfully to the Table");
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void add_dependents(Statement stmt) 
  {
    String sql;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Dependent ID: ");
    int id = sc.nextInt();

    System.out.print("Dependent first Name: ");
    String dependentfName = sc.next();

    System.out.print("Dependent last Name: ");
    String dependentlName = sc.next();

    System.out.print("Relationship: ");
    String relationship = sc.next();

    System.out.print("Employee Id: ");
    int employee_id = sc.nextInt();

    try 
    {
      sql ="INSERT into Dependents VALUES (" +id +", '" +dependentfName +"', '" +dependentlName +"', '" +relationship +"', " +employee_id +");";

      stmt.executeUpdate(sql);
      System.out.println("");

      System.out.println("Dependent Added Successfully to the Table");
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void delete_from_department(Statement stmt) 
  {
    String sql;

    int zero = 0;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Employee ID who you want to remove from department: ");
    int id = sc.nextInt();

    try 
    {
      sql ="Update Employees set department_id = NULL, salary= " +zero +", job_id = NULL where employee_id = " + id+";";

      stmt.executeUpdate(sql);
      System.out.println("");

      System.out.println("Employee Status Updated");
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void give_job(Statement stmt) {
    String sql;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Employee ID who you want to update: ");
    int id = sc.nextInt();

    System.out.print("Job id which you want to update to: ");
    int jid = sc.nextInt();

    int salary = 0;

    String sql2 ="SELECT (min_salary + max_salary) /2 as Salary from Jobs where job_id =" +jid +";";

    try 
    {
      ResultSet rs = stmt.executeQuery(sql2);

      while (rs.next()) 
        salary = rs.getInt("Salary");

      rs.close();
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    try 
    {
      sql ="Update Employees set job_id =" +jid +",salary = " +salary +" where employee_id = " +id +";";

      stmt.executeUpdate(sql);
      System.out.println("");

      System.out.println("Employee Status Updated");
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void fired(Statement stmt) 
  {
    String sql;

    System.out.println("Please Enter the Values Required -");

    System.out.print("Employee ID who you want to fire: ");
    int id = sc.nextInt();

    try 
    {
      sql ="Update Employees set job_id = NULL, salary = 0 where employee_id = " +id +";";

      stmt.executeUpdate(sql);
      System.out.println("");

      System.out.println("Employee Status Updated");
    } 

    catch (SQLException e) 
    {
      e.printStackTrace();
    }

    System.out.println("");
    System.out.println("Press Enter to go back to menu");
    sc.nextLine();
    sc.nextLine();
  }

  static void menu(Statement stmt) 
  {
      // while (true)
      // { 
      //     System.out.print("\033[H\033[2J");
      //     System.out.flush();

      //     System.out.println("+=================================================+");
      //     System.out.println("|              SUPER USER PORTAL                  |");
      //     System.out.println("|                                                 |");
      //     System.out.println("+=================================================+");
      //     System.out.println("|1|-> Register as a Super User                    |");
      //     System.out.println("|2|-> Login as an Existing Super User             |");
      //     System.out.println("|0|-> Exit                                        |");
      //     System.out.println("+=================================================+");

      //     System.out.print("Enter your choice: ");
      //     int input = sc.nextInt();

      //     if (input == 1)
      //     {
      //       System.out.print("Enter Your Name: ");
      //       String name = sc.next();

      //       System.out.print("Enter the corresponding password: ");
      //       String pass = sc.next();

      //       try 
      //       {
      //         BufferedWriter out = new BufferedWriter(new FileWriter("admins.txt",true));

      //         out.write("\n" + pass);

      //         out.close();
      //       } 

      //       catch (IOException e) 
      //       {
      //         e.printStackTrace();
      //       }
      //     }

      //     else if (input == 2)
      //     {
      //         System.out.print("Enter Your Name: ");
      //         String name = sc.next();
      //         System.out.print("Enter the corresponding password: ");
      //         String pass = sc.next();

      //         int flag = 0;

      //         File myFile = new File("admins.txt");

      //         try 
      //         {
      //           Scanner sc1 = new Scanner(myFile);

      //           while(sc1.hasNextLine())
      //           {
      //             String line = sc1.nextLine();

      //             if(pass.equals(line))
      //             {
      //                 flag = 1;
      //                 break;
      //             }
      //           }

      //           sc1.close();
      //       } 

      //       catch (FileNotFoundException e) 
      //       {
      //           e.printStackTrace();
      //       }

      //       if(flag == 1)
      //         break;
      //     }

      //     else if (input == 0)
      //       System.exit(0);

      //     else
      //     {
      //       System.out.println("Please provide a Valid Input");
      //       continue;
      //     }
      // }   

      while (true) 
      {
      System.out.print("\033[H\033[2J");
      System.out.flush();

      // System.out.println("+=================================================+");
      // System.out.println("|===============LOGIN WAS SUCCESSFUL==============|");
      System.out.println("+=================================================+");
      System.out.println("|              MENU DRIVEN INTERFACE              |");
      System.out.println("|                                                 |");
      System.out.println("|                      -MAYANK CHADHA (IMT2020045)|");
      System.out.println("+=================================================+");
      System.out.println("|1|-> Add Employee                                |");
      System.out.println("|2|-> Add Dependent                               |");
      System.out.println("|3|-> Add a Job Vacancy                           |");
      System.out.println("|4|-> Add a Department                            |");
      System.out.println("+=================================================+");
      System.out.println("|5|-> Give/Update Job                             |");
      System.out.println("|6|-> Fire                                        |");
      System.out.println("|7|-> Delete Employee from Department             |");
      System.out.println("+=================================================+");
      System.out.println("|8|-> Remove Employee                             |");
      System.out.println("|9|-> Remove Department                           |");
      System.out.println("|10|-> Remove Job                                 |");
      System.out.println("+=================================================+");
      System.out.println("|11|-> More than average Employees                |");
      System.out.println("|12|-> Nalla Employees                            |");
      System.out.println("|13|-> List of Employees                          |");
      System.out.println("|14|-> List of Departments                        |");
      System.out.println("|15|-> List of Jobs                               |");
      System.out.println("|16|-> List of Dependents for a Employee          |");
      System.out.println("+=================================================+");
      System.out.println("|0|-> Exit                                        |");
      System.out.println("+=================================================+");
      System.out.println("                                                   ");

      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();

      System.out.print("\033[H\033[2J");
      System.out.flush();

      if (choice > 16 || choice < 0) 
      {
        System.out.println("Invalid choice ! Try Again.");
        continue;
      } 
      else if (choice == 0) break;
      else if (choice == 1) add_employee(stmt); 
      else if (choice == 2) add_dependents(stmt); 
      else if (choice == 3) vacancy(stmt); 
      else if (choice == 4) add_department(stmt); 
      else if (choice == 5) give_job(stmt); 
      else if (choice == 6) fired(stmt); 
      else if (choice == 7) delete_from_department(stmt); 
      else if (choice == 8) remove_employee(stmt); 
      else if (choice == 9) remove_dept(stmt);
      else if (choice == 10) remove_job(stmt);
      else if (choice == 11) avg_employees(stmt);
      else if (choice == 12) nalla_employees(stmt); 
      else if (choice == 13) list_of_employees(stmt); 
      else if (choice == 14) list_of_departments(stmt);
      else if (choice == 15) list_of_jobs(stmt);
      else if (choice == 16) list_particular_dependents(stmt);
   }
   
   System.out.print("\033[H\033[2J");
   System.out.flush();
}

public static void main(String[] args) 
  {
    Connection conn = null;
    Statement stmt = null;

    try 
    {
      Class.forName(JDBC_DRIVER);

      System.out.println("Connecting to database...");

      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      
      menu(stmt);

      stmt.close();
      conn.close();
   } 
   
   catch (SQLException se) 
   { 
      se.printStackTrace();
   } 
   
   catch (Exception e) 
   { 
      e.printStackTrace();
   } 
   
   finally 
   { 
      try 
      {
         if (stmt != null) stmt.close();
      } 
      
      catch (SQLException se2) {}
      
      try 
      {
        if (conn != null) conn.close();
      } 
      
      catch (SQLException se) 
      {
        se.printStackTrace();
      } 
   } 

    System.out.println("Sayonara !");
    System.out.println("End of Code");
   } 
}