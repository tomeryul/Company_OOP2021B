package Model;

import java.io.Serializable;

public abstract class Employee implements Calculate,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int serialId=1000;
	private int id;
	private String employeeName;
	private workingHours current;
	private workingHours preference;
	private double profit=0;
	private String nameOfRole;
	
	public Employee(String employeeName,int start,int end,String nameOfRole) {
		this.employeeName=employeeName;
		this.id=serialId++;
		current=new workingHours();
		preference=new workingHours(start, end); // check valid input
		this.nameOfRole=nameOfRole;
		
		calculateProfit();
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof Employee))
			return false;
		
		return ((Employee) other).getId()==id;
	}
	public void calculateProfit() { 
		int optimalHours=0;
		if(current.getEnd()<current.getStart())
			current.setEnd(current.getEnd()+24);
		if(preference.getEnd()>preference.getStart())
			preference.setEnd(preference.getEnd()+24);
		for (int i =current.getStart(); i <current.getEnd(); i++) {
			for (int j =preference.getStart(); j <preference.getEnd(); j++) {
				if(i>23)
					i-=24;
					if(j>23)
						j-=24;
					
				if(i==j)
					optimalHours++;
				if(current.getEnd()<current.getStart())
					i+=24;
				if(preference.getEnd()>preference.getStart())
					j+=24;
			}
		}
		if(optimalHours>8)
			optimalHours=8;
		profit=(8-optimalHours)*(-0.2)+optimalHours*(0.2);
		if(current.getEnd()>23)
			current.setEnd(current.getEnd()-24);
		if(preference.getEnd()>23)
			preference.setEnd(preference.getEnd()-24);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public workingHours getCurrent() {
		return current;		
	}

	public void setCurrent(workingHours current) {
		this.current = current;
	}

	public workingHours getPreference() {
		return preference;
	}
	
	public void setPreference(workingHours preference) {
		this.preference = preference;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public String getNameOfRole() {
		return nameOfRole;
	}

	public void setNameOfRole(String nameOfRole) {
		this.nameOfRole = nameOfRole;
	}
	
	public String toString() {
		return getClass().getSimpleName()+" [name="+employeeName+" , id="+id+" ,role="+nameOfRole+
				", working hours= "+current.show()+", preferred working hours= "+preference.show()+"]";
	}
	
}
