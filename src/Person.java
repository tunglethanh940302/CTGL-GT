
public class Person {

	private String ID;
	private String name;
	private String birthplace;
	private String dob;
	
	public Person(String iD, String name, String birthplace, String dob) {
		this.ID = iD;
		this.name = name;
		this.birthplace = birthplace;
		this.dob = dob;
	}
	
	public Person() {
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	 @Override

	public String toString() {

	   return String.format("%-2s %-10s %-10s %-10s", ID, name, dob, birthplace);
	 }	

	 @Override

	 public boolean equals(Object obj) {
		 
		 return false;
	 }

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
