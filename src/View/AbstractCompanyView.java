package View;

import java.util.Vector;

import Liseners.CompanyListrnerToUi;
import Model.Department;
import Model.Employee;
import Model.Role;

import Model.workingHours.epreferenceOfWorkHours;

public interface AbstractCompanyView {

	void registerListener(CompanyListrnerToUi listener);

	public void calculateProfitToUi();

	public double getProfitToUi();

	public String toStringToUi();

	public String showAllProfitsToUi();

	public String showAllRolesToUi();

	public boolean isRoleAlreadyToUi(String roleName);

	public boolean isDepartmentAlreadyToUi(String departmentName);

	public boolean isPreferenceToUi(String preference);

	public String showAllPreferencesToUi();

	public void changeDepartmentHoursToUi(epreferenceOfWorkHours pref, int numOfHours, String departmentName);

	public void updateRolesProfitsToUi();

	public String showAllDepartmentsToUi();

	public boolean addEmployeeToUi(Employee employee, String departmentName);

	public boolean isEmployeeExistToUi(Employee employee);

	public void addDepartmentToUi(Department department);

	public void addRoleToUi(Role role);

	public boolean alreadyExitRoleToUi(Role role);

	public boolean changeRoleHoursToUi(epreferenceOfWorkHours pref, int numOfHours, String roleName);

	public Role getRoleBynameToUi(String roleName);

	public Vector<Department> getAllDepartmentsToUi();

	public Vector<Role> getAllRolesToUi();

	public Vector<Employee> getAllEmployeesToUi();

	public String getNameOfRoleToUi(int index);

	public String getNameOfDepartmentToUi(int index);

	public epreferenceOfWorkHours getePrefToUi(String pref);

}
