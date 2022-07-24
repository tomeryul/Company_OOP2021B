package Model;

import java.io.Serializable;
import java.util.Vector;

import Liseners.CompanyListrner;
import Model.workingHours.epreferenceOfWorkHours;

public class Company implements Calculate, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<CompanyListrner> allListeners; // maor's
	private Vector<Department> allDepartments;
	private Vector<Role> allRoles;
	private Vector<Employee> allEmployees;
	private double profit = 0;
	private String nameOfCompany;

	public Company(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
		this.allDepartments = new Vector<Department>();
		this.allRoles = new Vector<Role>();
		this.allEmployees = new Vector<Employee>();
		allListeners = new Vector<CompanyListrner>();
	}

	public void registerListener(CompanyListrner listener) {
		allListeners.add(listener);
	}

	public void calculateProfit() {
		double tempProfit = 0;
		for (int i = 0; i < allDepartments.size(); i++) {
			allDepartments.get(i).calculateProfit();
			tempProfit += allDepartments.get(i).getProfit();
		}
		this.profit = tempProfit;

		for (int i = 0; i < allRoles.size(); i++) {
			allRoles.get(i).calculateProfit();
		}
	}

	public double getProfit() {
		return profit;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Company name is:" + nameOfCompany + "\n");
		for (int i = 0; i < allDepartments.size(); i++) {
			sb.append(allDepartments.get(i) + "\n");
		}
		return sb.toString();
	}

	public String showAllProfits() {
		calculateProfit();
		StringBuilder sb = new StringBuilder();
		sb.append("company profit is:" + profit + "\n");
		sb.append("showing departments' profits:\n");
		for (int i = 0; i < allDepartments.size(); i++) {
			sb.append(allDepartments.get(i).getNameOfDepartment() + " : " + allDepartments.get(i).getProfit() + "\n");
		}
		sb.append("showing employees' profits:\n");
		for (int i = 0; i < allEmployees.size(); i++) {
			sb.append(allEmployees.get(i).getId() + " - " + allEmployees.get(i).getEmployeeName() + " : "
					+ allEmployees.get(i).getProfit() + "\n");
		}
		return sb.toString();
	}

	public Vector<Department> getAllDepartments() {
		return allDepartments;
	}

	public Vector<Role> getAllRoles() {
		return allRoles;
	}

	public Vector<Employee> getAllEmployees() {
		return allEmployees;
	}

	public String showAllRoles() {
		StringBuilder sb = new StringBuilder("List of roles:\n");
		for (int i = 0; i < allRoles.size(); i++) {
			sb.append(allRoles.get(i).getNameOfRole() + "\n");
		}
		return sb.toString();
	}

	public boolean isRoleAlready(String roleName) {
		for (int i = 0; i < allRoles.size(); i++) {
			if (allRoles.get(i).getNameOfRole().equalsIgnoreCase(roleName))
				return true;
		}
		return false;
	}

	public boolean isDepartmentAlready(String departmentName) {
		boolean index = false;
		for (int i = 0; i < allDepartments.size(); i++) {
			if (allDepartments.get(i).getNameOfDepartment().equalsIgnoreCase(departmentName)) {
				index = true;
			}

		}
		return index;
	}

	public boolean isPreference(String preference) {
		boolean index = false;
		epreferenceOfWorkHours[] allPreferences = epreferenceOfWorkHours.values();
		for (int i = 0; i < allPreferences.length; i++) {
			if (allPreferences[i].name().equalsIgnoreCase(preference))
				;
			index = true;
		}
		return index;
	}

	public String showAllPreferences() {
		epreferenceOfWorkHours[] allPreferences = epreferenceOfWorkHours.values();
		StringBuilder sb = new StringBuilder("showing all preferences:\n");
		for (int i = 0; i < allPreferences.length; i++) {
			sb.append(allPreferences[i].name() + "\n");
		}
		return sb.toString();
	}

	public void changeDepartmentHours(epreferenceOfWorkHours pref, int numOfHours, String departmentName) {
		for (int i = 0; i < allDepartments.size(); i++) {
			if (allDepartments.get(i).getNameOfDepartment().equalsIgnoreCase(departmentName)) {
				allDepartments.get(i).changeWorkHours(pref, numOfHours);
				calculateProfit();
				updateRolesProfits();
				return;
			}
		}
	}

	public void updateRolesProfits() {
		for (int i = 0; i < allRoles.size(); i++) {
			allRoles.get(i).calculateProfit();
		}

	}

	public String showAllDepartments() {
		StringBuilder sb = new StringBuilder("List of Departments:\n");
		for (int i = 0; i < allDepartments.size(); i++) {
			sb.append(allDepartments.get(i).getNameOfDepartment() + "\n");
		}
		return sb.toString();
	}

	public boolean addEmployee(Employee employee, String departmentName) {
		if (isEmployeeExist(employee)) {
			System.out.println("Employee allredy existe");
			return false;
		}
		allEmployees.add(employee); // add to employees

		for (int i = 0; i < allRoles.size(); i++) { // add to role
			if (employee.getNameOfRole().equalsIgnoreCase(allRoles.get(i).getNameOfRole()))
				allRoles.get(i).addEmployee(employee);

		}

		for (int j = 0; j < allDepartments.size(); j++) { // add to department
			if (allDepartments.get(j).getNameOfDepartment().equalsIgnoreCase(departmentName))
				allDepartments.get(j).addEmployee(employee);
		}

		return true;
	}

	public boolean isEmployeeExist(Employee employee) {
		for (int i = 0; i < allEmployees.size(); i++) {
			if (allEmployees.get(i).equals(employee))
				return true;
		}
		return false;
	}

	public void addDepartment(Department department) {
		for (int i = 0; i < allRoles.size(); i++) {
			department.addRole(new Role(allRoles.get(i)));
		}
		allDepartments.add(department);
	}

	public void addRole(Role role) {
		if (alreadyExitRole(role))
			return;
		allRoles.add(role);
		for (int i = 0; i < allDepartments.size(); i++) {
			allDepartments.get(i).addRole(new Role(role));
		}
	}

	public boolean alreadyExitRole(Role role) {
		for (int i = 0; i < allRoles.size(); i++) {
			if (allRoles.get(i).equals(role))
				return true;
		}
		return false;
	}

	public boolean changeRoleHours(epreferenceOfWorkHours pref, int numOfHours, String roleName) {
		Role role = getRoleByname(roleName);// role name is valid!
		if (role.isSynchronized()) {
			for (int i = 0; i < allDepartments.size(); i++) {
				if (allDepartments.get(i).getRole(roleName).getAllEmployees().size() != 0)
					if (allDepartments.get(i).isSynchornized)
						return false; // collapse , synchronized role and department!
			}
		}
		for (int i = 0; i < allDepartments.size(); i++) {
			if (!allDepartments.get(i).isSynchronizedWork()) {
				allDepartments.get(i).getRole(roleName).changeWorkHours(pref, numOfHours);
				allDepartments.get(i).calculateProfit();
			}
		}
		calculateProfit();
		updateRolesProfits();
		return true;
	}

	public Role getRoleByname(String roleName) {
		for (int i = 0; i < allRoles.size(); i++) {
			if (allRoles.get(i).getNameOfRole().equalsIgnoreCase(roleName))
				return allRoles.get(i);
		}
		return null; // cant happen unless we made a mistake
	}

//maybe need to change to size-1
	public String getNameOfRole(int index) {
		if (index > allRoles.size() || index < 0) {
			return null;
		} else
			return allRoles.get(index).getNameOfRole();

	}// cant happen unless we made a mistake

	// maybe need to change to size-1
	public String getNameOfDepartment(int index) {
		if (index > allDepartments.size() || index < 0) {
			return null;
		} else
			return allDepartments.get(index).getNameOfDepartment();

	}// cant happen unless we made a mistake

	public epreferenceOfWorkHours getePref(String pref) {
		epreferenceOfWorkHours[] allPreferences = epreferenceOfWorkHours.values();
		for (int i = 0; i < allPreferences.length; i++) {
			if (allPreferences[i].name().equals(pref)) {
				return allPreferences[i];
			}
		}
		return null;
	}

}