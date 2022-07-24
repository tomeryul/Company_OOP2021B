package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import Liseners.CompanyListrner;
import Liseners.CompanyListrnerToUi;
import Model.Company;
import Model.Department;
import Model.Employee;
import Model.Role;
import Model.workingHours.epreferenceOfWorkHours;
import View.AbstractCompanyView;

public class CompanyController implements CompanyListrner, CompanyListrnerToUi {
	private Company companyModel;
	private AbstractCompanyView companyView;

	public CompanyController(Company model, AbstractCompanyView view) {
		companyModel = model;
		companyView = view;
		companyModel.registerListener(this);
		companyView.registerListener(this);
	}

//to ui

	@Override
	public epreferenceOfWorkHours getePrefToUi(String pref) {
		return companyModel.getePref(pref);
	}

	@Override
	public String getNameOfDepartmentToUi(int index) {
		return companyModel.getNameOfDepartment(index);
	}

	@Override
	public void calculateProfitToUi() {
		companyModel.calculateProfit();
	}

	@Override
	public String getNameOfRoleToUi(int index) {
		return companyModel.getNameOfRole(index);
	}

	@Override
	public Vector<Department> getAllDepartmentsToUi() {
		return companyModel.getAllDepartments();
	}

	@Override
	public Vector<Role> getAllRolesToUi() {
		return companyModel.getAllRoles();
	}

	@Override
	public Vector<Employee> getAllEmployeesToUi() {
		return companyModel.getAllEmployees();
	}

	@Override
	public double getProfitToUi() {

		return companyModel.getProfit();
	}

	@Override
	public String toStringToUi() {

		return companyModel.toString();
	}

	@Override
	public String showAllProfitsToUi() {

		return companyModel.showAllProfits();
	}

	@Override
	public String showAllRolesToUi() {

		return companyModel.showAllRoles();
	}

	@Override
	public boolean isRoleAlreadyToUi(String roleName) {
		return companyModel.isRoleAlready(roleName);
	}

	@Override
	public boolean isDepartmentAlreadyToUi(String departmentName) {

		return companyModel.isDepartmentAlready(departmentName);
	}

	@Override
	public boolean isPreferenceToUi(String preference) {

		return companyModel.isPreference(preference);
	}

	@Override
	public String showAllPreferencesToUi() {

		return companyModel.showAllPreferences();
	}

	@Override
	public void changeDepartmentHoursToUi(epreferenceOfWorkHours pref, int numOfHours, String departmentName) {
		companyModel.changeDepartmentHours(pref, numOfHours, departmentName);

	}

	@Override
	public void updateRolesProfitsToUi() {
		companyModel.updateRolesProfits();

	}

	@Override
	public String showAllDepartmentsToUi() {

		return companyModel.showAllDepartments();
	}

	@Override
	public boolean addEmployeeToUi(Employee employee, String departmentName) {

		return companyModel.addEmployee(employee, departmentName);
	}

	@Override
	public boolean isEmployeeExistToUi(Employee employee) {

		return companyModel.isEmployeeExist(employee);
	}

	@Override
	public void addDepartmentToUi(Department department) {
		companyModel.addDepartment(department);

	}

	@Override
	public void addRoleToUi(Role role) {
		companyModel.addRole(role);

	}

	@Override
	public boolean alreadyExitRoleToUi(Role role) {

		return companyModel.alreadyExitRole(role);
	}

	@Override
	public boolean changeRoleHoursToUi(epreferenceOfWorkHours pref, int numOfHours, String roleName) {

		return companyModel.changeRoleHours(pref, numOfHours, roleName);
	}

	@Override
	public Role getRoleBynameToUi(String roleName) {

		return companyModel.getRoleByname(roleName);
	}

	// to model

	@Override
	public void calculateProfit() {
		companyView.calculateProfitToUi();
	}

	@Override
	public double getProfit() {

		return companyView.getProfitToUi();
	}

	@Override
	public String showAllProfits() {

		return companyView.showAllProfitsToUi();
	}

	@Override
	public String showAllRoles() {

		return companyView.showAllRolesToUi();
	}

	@Override
	public boolean isRoleAlready(String roleName) {

		return companyView.isRoleAlreadyToUi(roleName);
	}

	@Override
	public boolean isDepartmentAlready(String departmentName) {

		return companyView.isDepartmentAlreadyToUi(departmentName);
	}

	@Override
	public boolean isPreference(String preference) {

		return companyView.isPreferenceToUi(preference);
	}

	@Override
	public String showAllPreferences() {

		return companyView.showAllPreferencesToUi();
	}

	@Override
	public void changeDepartmentHours(epreferenceOfWorkHours pref, int numOfHours, String departmentName) {

		companyView.changeDepartmentHoursToUi(pref, numOfHours, departmentName);
	}

	@Override
	public void updateRolesProfits() {

		companyView.updateRolesProfitsToUi();
	}

	@Override
	public String showAllDepartments() {

		return companyView.showAllDepartmentsToUi();
	}

	@Override
	public boolean addEmployee(Employee employee, String departmentName) {

		return companyView.addEmployeeToUi(employee, departmentName);
	}

	@Override
	public boolean isEmployeeExist(Employee employee) {

		return companyView.isEmployeeExistToUi(employee);
	}

	@Override
	public void addDepartment(Department department) {

		companyView.addDepartmentToUi(department);
	}

	@Override
	public void addRole(Role role) {

		companyView.addRoleToUi(role);
	}

	@Override
	public boolean alreadyExitRole(Role role) {

		return companyView.alreadyExitRoleToUi(role);
	}

	@Override
	public boolean changeRoleHours(epreferenceOfWorkHours pref, int numOfHours, String roleName) {

		return companyView.changeRoleHoursToUi(pref, numOfHours, roleName);
	}

	@Override
	public Role getRoleByname(String roleName) {

		return companyView.getRoleBynameToUi(roleName);
	}

	@Override
	public Vector<Department> getAllDepartments() {
		return companyView.getAllDepartmentsToUi();
	}

	@Override
	public Vector<Role> getAllRoles() {
		return companyView.getAllRolesToUi();
	}

	@Override
	public Vector<Employee> getAllEmployees() {
		return companyView.getAllEmployeesToUi();
	}

	@Override
	public String getNameOfRole(int index) {
		return companyView.getNameOfRoleToUi(index);
	}

	@Override
	public String getNameOfDepartment(int index) {
		return companyView.getNameOfDepartmentToUi(index);
	}

	@Override
	public epreferenceOfWorkHours getePref(String pref) {
		return companyView.getePrefToUi(pref);

	}
	
	public static void saveToFile(Company company1)
			throws FileNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("1.dat"));
		o.writeObject(company1);
		o.close();
	}

	public static Company readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream i = new ObjectInputStream(new FileInputStream("1.dat"));
		Company company1 = (Company) i.readObject();
		i.close();
		return company1;
	}
	
}
