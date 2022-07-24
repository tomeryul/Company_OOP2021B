package Model;

import java.io.Serializable;
import java.util.Vector;

import Model.workingHours.epreferenceOfWorkHours;

//1- we need to import the array of role in here
// 2- we need to make boolean that ask if the department
// require synchronized work of all their employees
// if yes - we need to change each person preference

public class Department implements Syncronizeable, ChangeWorkingable, Calculate,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean isSynchornized;
	protected Vector<Role> allRoles;
	protected String nameOfDepartment; // main
	protected double profit = 0;

	// constructor
	public Department(String nameOfDepartment, boolean isSynchronized) {
		if (isSynchronized)
			synchronize();
		else
			changeable();
		this.nameOfDepartment = nameOfDepartment;
		allRoles = new Vector<Role>();
	}

	public Vector<Role> getAllRoles() {
		return allRoles;
	}

	public boolean isSynchronizedWork() {
		return isSynchornized;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Department)) {
			return false;
		}
		Department d = (Department) other;
		return nameOfDepartment.equals(d.nameOfDepartment);
	}

	@Override
	public void changeable() {
		isSynchornized = false;
	}

	@Override
	public void synchronize() {
		isSynchornized = true;

	}

	@Override
	public void calculateProfit() {
		double tempProfit = 0;
		for (int i = 0; i < allRoles.size(); i++) {
			allRoles.get(i).calculateProfit();
			tempProfit += allRoles.get(i).getProfit();
		}
		profit = tempProfit;
	}

	public double getProfit() {
		return profit;
	}

	public String getNameOfDepartment() {
		return nameOfDepartment;
	} 

	public String toString() {
		StringBuilder sb = new StringBuilder("Department name:" + nameOfDepartment + "\n");
		for (int i = 0; i < allRoles.size(); i++) {
			sb.append(allRoles.get(i) + "\n");
		}
		return sb.toString();
	}

	public void addRole(Role role) {
		allRoles.add(role);
		
	}

	public boolean changeWorkHours(epreferenceOfWorkHours pref, int numOfHours) {
		if(isSynchornized) {
			for (int i = 0; i < allRoles.size(); i++) {
				if(allRoles.get(i).getAllEmployees().size()!=0)
				if(allRoles.get(i).isSynchronized())
					return false;
			}
		}
		
		for (int i = 0; i < allRoles.size(); i++) {
			if(!allRoles.get(i).isSynchronized())
				allRoles.get(i).changeWorkHours(pref,numOfHours);
		}
		calculateProfit();
	return true;
	}

	public Role getRole(String roleName) {
		for (int i = 0; i < allRoles.size(); i++) {
			if(allRoles.get(i).getNameOfRole().equalsIgnoreCase(roleName))
				return allRoles.get(i);
		}
		
		return null;
	}

	public boolean addEmployee(Employee employee) {
		for (int i = 0; i <allRoles.size(); i++) {
			if(allRoles.get(i).getNameOfRole().equalsIgnoreCase(employee.getNameOfRole())) {
				allRoles.get(i).addEmployee(employee);
				return true;
			}
		}
		
		return false; //error
	}


}
