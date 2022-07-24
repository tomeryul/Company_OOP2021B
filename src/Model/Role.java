package Model;

import java.io.Serializable;
import java.util.Vector;

import Model.workingHours.epreferenceOfWorkHours;

public class Role implements ChangeWorkingable, Syncronizeable, Calculate,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isSynchronized;
	private Vector<Employee> allEmployees;
	private String nameOfRole;
	private double profit = 0;

	// constructor
	public Role(String nameOfRole, boolean isSynchronized) {
		this.nameOfRole = nameOfRole;
		if (isSynchronized)
			synchronize();
		else
			changeable();
		this.allEmployees = new Vector<Employee>();

	}

	public Role(Role role) { // copy constructor
		this.isSynchronized = role.isSynchronized();
		this.nameOfRole = role.getNameOfRole();
		this.allEmployees = new Vector<Employee>();
	}

	public boolean isSynchronized() {
		return isSynchronized;
	}

	public Vector<Employee> getAllEmployees() {
		return allEmployees;
	}

	public String getNameOfRole() {
		return nameOfRole;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Role)) {
			return false;
		}
		Role p = (Role) other;
		return nameOfRole.equalsIgnoreCase(p.nameOfRole);
	}

	@Override
	public void synchronize() {
		isSynchronized = true;

	}

	@Override
	public void changeable() {
		isSynchronized = false;

	}

	@Override
	public void calculateProfit() {
		double tempProfit = 0;
		for (int i = 0; i < allEmployees.size(); i++) {
			allEmployees.get(i).calculateProfit();
			tempProfit += allEmployees.get(i).getProfit();
		}
		this.profit = tempProfit;

	}

	public double getProfit() {
		return profit;
	}

	public String toString() {
		int count = 0;
		StringBuilder sb = new StringBuilder(nameOfRole + ":\n");
		for (int i = 0; i < allEmployees.size(); i++) {
			count = i + 1;
			sb.append(count+") " + allEmployees.get(i) + "\n");
		}
		return sb.toString();
	}

	public void changeWorkHours(epreferenceOfWorkHours pref, int numOfHours) {
		for (int i = 0; i < allEmployees.size(); i++) {
			allEmployees.get(i).getCurrent().hourChange(pref, numOfHours);
			if (pref != epreferenceOfWorkHours.FREE)
				allEmployees.get(i).calculateProfit();
			else
				allEmployees.get(i).setProfit(0.1 * 8);
		}
		calculateProfit();
	}

	public void addEmployee(Employee employee) {
		allEmployees.add(employee);
	}

}
