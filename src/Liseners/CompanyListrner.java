package Liseners;

import java.util.Vector;

import Model.Department;
import Model.Employee;
import Model.Role;
import Model.workingHours.epreferenceOfWorkHours;

public interface CompanyListrner {

	public void calculateProfit();

	public double getProfit();

	public String toString();

	public String showAllProfits();

	public String showAllRoles();

	public boolean isRoleAlready(String roleName);

	public boolean isDepartmentAlready(String departmentName);

	public boolean isPreference(String preference);

	public String showAllPreferences();

	public void changeDepartmentHours(epreferenceOfWorkHours pref, int numOfHours, String departmentName);

	public void updateRolesProfits();

	public String showAllDepartments();

	public boolean addEmployee(Employee employee, String departmentName);

	public boolean isEmployeeExist(Employee employee);

	public void addDepartment(Department department);

	public void addRole(Role role);

	public boolean alreadyExitRole(Role role);

	public boolean changeRoleHours(epreferenceOfWorkHours pref, int numOfHours, String roleName);

	public Role getRoleByname(String roleName);

	public Vector<Department> getAllDepartments();

	public Vector<Role> getAllRoles();

	public Vector<Employee> getAllEmployees();

	public String getNameOfRole(int index);

	public String getNameOfDepartment(int index);

	public epreferenceOfWorkHours getePref(String pref);
}
