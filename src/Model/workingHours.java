package Model;

import java.io.Serializable;

public class workingHours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nameOfPerson;

	public static enum epreferenceOfWorkHours {
		EARLIER, LATER, DEFAULT, FREE
	};

	private int start;
	private int end;

	private epreferenceOfWorkHours ePref;

	public epreferenceOfWorkHours getePref() {
		return ePref;
	}

	public workingHours() { // default
		this.start = 8;
		this.end = 17;
	}

	public workingHours(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void hourChange(epreferenceOfWorkHours preference, int numOfHours) {
		if (preference == epreferenceOfWorkHours.DEFAULT) {
			this.start = 8;
			this.end = 17;
		}

		else if (preference == epreferenceOfWorkHours.EARLIER) {
			this.start -= numOfHours;
			this.end -= numOfHours;
		} else {
			this.start += numOfHours;
			this.end += numOfHours;
		}
		if (start >= 24) // modify to seperate function
			start -= 24;
		if (end >= 24)
			end -= 24;
		if (start < 0)
			start += 24;
		if (end < 0)
			end += 24;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	public String show() {
		if(end>24)
		return "starts at= "+start+":00 ,end at= "+(end-24)+":00";
		else
			return "starts at= "+start+":00 ,end at= "+end+":00";
	}
}