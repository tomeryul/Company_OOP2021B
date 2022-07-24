package Model;

import java.util.Scanner;

import Model.workingHours.epreferenceOfWorkHours;


public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("enter name of company:\t");
		String companyName = s.next();
		Company company = new Company(companyName);
		
		company.addDepartment(new Department("a", false));
		company.addDepartment(new Department("b", false));
		company.addDepartment(new Department("c", true));
		
		company.addRole(new Role("manager",true));
		company.addRole(new Role("cleaner",false));
		company.addRole(new Role("programmer",false));
		
		company.addEmployee(new EmployeeBaseSalary("tomer", 8,17 , "programmer"), "a");
		company.addEmployee(new EmployeeHourly("ohad", 12, 21, "programmer"), "a");
		company.addEmployee(new EmployeeSalesMan("nadav", 0, 9, "cleaner"), "b");
		company.addEmployee(new EmployeeSalesMan("shany", 21, 6, "cleaner"), "c");
		company.addEmployee(new EmployeeHourly("yonatan", 7, 16, "cleaner"), "b");
		company.addEmployee(new EmployeeBaseSalary("ofir", 9, 18, "manager"), "a");
		boolean end = false;
		int decision;
		do {
			System.out.println("Please select an option:\r\n" + "	1 - Add a employee or role or department \r\n"
					+ "	2 - Show all data \r\n" + "	3 - Change hours of roll  \r\n"
					+ "	4 - Change hours of department \r\n"
					+ "	5 - Show profit of the company departments and employees \r\n"+
					"	6 - Exit \r\n");
 
			decision = s.nextInt();
			switch (decision) {
			case 1:
				create(s, company);
				break;
			case 2:
				System.out.println(company);
				break;
			case 3:
				changeRoleHours(s, company);
				break;
			case 4:
				changeDepartmentHours(s, company);
				break;
			case 5:
				company.calculateProfit();
				System.out.println(company.showAllProfits());
				break;
			case 6:
				System.out.println("Bye ,bye");
				end = true;
				break;

			default:
				System.out.println("error occured,please try again!");
				break;
			}
		} while (!end);
		s.close();
	}

	private static void create(Scanner s, Company company) { // create employee,role,department and add to company
		boolean end = false;
		int decision;
		do {
			System.out.println("Please select an option:\r\n" +	"1 - Add employee  \r\n"+
																"2 - Add role \r\n"+
																"3 - Add department \r\n"+
																"4 - return \r\n");

			decision = s.nextInt();
			switch (decision) {
			case 1:
				createEmployee(s,company);
				break;
			case 2:
				createRole(s,company);
				break;
			case 3:
				createDepartment(s,company);
				break;
			case 4:
				end = true;
				break;
				
			default:
				System.out.println("error occured,please try again!");
				break;
			}
			
		} while (!end);
	}
	
	private static void createEmployee(Scanner s, Company company) {
		Employee employee;
		boolean index=false;
		System.out.println("Enter a name of youre employee");
		String name =s.next();
		System.out.println("when would you like to start your day of work (you work for 9 hours)");
		int start =s.nextInt();
		int end=start+9;
		if(end>=24) {
			index=true;
			end-=24;
		}
		System.out.println("you work from "+start+":00 to "+end+":00");
		if(index) {
			end+=24;
		}
		String roleName = getRoleFromList(s, company);
		String departmentName=getDepartmentFromList(s, company);
		boolean endofloop = false;
		int decision;
		do {
			System.out.println("how do you get your salary:\r\n" +	"1 - Base salary  \r\n"+
																	"2 - Hourly \r\n"+
																	"3 - Base salary and Percentaje of monthly sales \r\n");

			decision = s.nextInt();
			switch (decision) {
			case 1:
				employee = new EmployeeBaseSalary(name, start, end, roleName);
				company.addEmployee(employee,departmentName);
				endofloop=true;
				break;
			case 2:
				employee = new EmployeeHourly(name, start, end, roleName);
				company.addEmployee(employee,departmentName);
				endofloop=true;
				break;
			case 3:
				employee = new EmployeeSalesMan(name, start, end, roleName);
				company.addEmployee(employee,departmentName);
				endofloop=true;
				break;
				
			default:
				System.out.println("error occured,please try again!");
				break;
			}
			
		} while (!endofloop);
		
		
	}

	private static void createRole(Scanner s, Company company) {
		System.out.println("Enter a name for the role");
		String name =s.next();
		String Synchronized = " ";
		while (!Synchronized.equalsIgnoreCase("yes") && !Synchronized.equalsIgnoreCase("no")) {
			System.out.println("Please tell us if youre role is synchronized? (yes/no):");
			Synchronized = s.next();
		}
		boolean isSynchronized;
		if (Synchronized.equalsIgnoreCase("yes"))
			isSynchronized = true;
		else
			isSynchronized = false;
		
		Role role = new Role(name,isSynchronized );
		company.addRole(role);
		
	}

	
	private static void createDepartment(Scanner s, Company company) {
		System.out.println("Enter a name for the department");
		String name =s.next();
		String Synchronized = " ";
		while (!Synchronized.equalsIgnoreCase("yes") && !Synchronized.equalsIgnoreCase("no")) {
			System.out.println("Please tell us if youre department is synchronized? (yes/no):");
			Synchronized = s.next();
		}
		boolean isSynchronized;
		if (Synchronized.equalsIgnoreCase("yes"))
			isSynchronized = true;
		else
			isSynchronized = false;
		
		Department department = new Department(name,isSynchronized );
		company.addDepartment(department);
		
	}

	
	
	private static void changeDepartmentHours(Scanner s, Company company) {
		epreferenceOfWorkHours preference = epreferenceOfWorkHours
				.valueOf(getPreferenceFromList(s, company).toUpperCase());
		int numOfHours = getNumOfHours(s);
		String deparmentName = getDepartmentFromList(s, company);
		company.changeDepartmentHours(preference, numOfHours, deparmentName);
	}

	private static String getDepartmentFromList(Scanner s, Company company) {
		String departmentName;
		do {
			System.out.println(company.showAllDepartments());
			System.out.println("enter Department from list: ");
			departmentName = s.next();
		}while (!company.isDepartmentAlready(departmentName));
		
		return departmentName;
	}
	
	private static String getRoleFromList(Scanner s, Company company) {
		String roleName;
		do {
			System.out.println(company.showAllRoles());
			System.out.println("enter role from list: ");
			roleName = s.next();
		} while (!company.isRoleAlready(roleName));

		return roleName;
	}
	
	private static String getPreferenceFromList(Scanner s, Company company) {
		String preference;
		do {
			System.out.println(company.showAllPreferences());
			System.out.println("enter preference from list: ");
			preference = s.next();
		} while (!company.isPreference(preference));

		return preference;
	}
	

	private static void changeRoleHours(Scanner s, Company company) {
		epreferenceOfWorkHours preference = epreferenceOfWorkHours
				.valueOf(getPreferenceFromList(s, company).toUpperCase());
		int numOfHours = getNumOfHours(s);
		String roleName = getRoleFromList(s, company);
		company.changeRoleHours(preference, numOfHours, roleName);
	}

	private static int getNumOfHours(Scanner s) {
		int numOfHours;
		do {
			System.out.println("enter how many hours do you want to move the work hours: ");
			numOfHours = s.nextInt();
		} while (numOfHours < 0 || numOfHours > 24);
		return 0;
	}

	

	

}
