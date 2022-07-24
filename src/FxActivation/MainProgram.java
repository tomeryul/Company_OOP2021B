package id_316041789_id_314674649;

import Model.Company;
import Model.Department;
import Model.EmployeeBaseSalary;
import Model.EmployeeHourly;
import Model.EmployeeSalesMan;
import Model.Role;
import View.AbstractCompanyView;
import View.companyView;
import controller.CompanyController;


import javafx.application.Application;
import javafx.stage.Stage;



public class MainProgram extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		String companyName = " ";
		Company theModel = new Company(companyName);
		HardCode(theModel);
		AbstractCompanyView View = new companyView(primaryStage);
		@SuppressWarnings("unused")
		CompanyController controller = new CompanyController(theModel, View);

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void HardCode(Company company) {
		company.addDepartment(new Department("a", false));
		company.addDepartment(new Department("b", false));
		company.addDepartment(new Department("c", true));
		

		company.addRole(new Role("manager", true));
		company.addRole(new Role("cleaner", false));
		company.addRole(new Role("programmer", false));

		company.addEmployee(new EmployeeBaseSalary("tomer", 8, 17, "programmer"), "a");
		company.addEmployee(new EmployeeHourly("ohad", 12, 21, "programmer"), "a");
		company.addEmployee(new EmployeeSalesMan("nadav", 0, 9, "cleaner"), "b");
		company.addEmployee(new EmployeeSalesMan("shany", 21, 6, "cleaner"), "c");
		company.addEmployee(new EmployeeHourly("yonatan", 7, 16, "cleaner"), "b");
		company.addEmployee(new EmployeeBaseSalary("ofir", 9, 18, "manager"), "a");
	}
}
