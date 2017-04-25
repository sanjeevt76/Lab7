
public class StudentRecord {

	private String studentName;
	private String address;
	private String studentID;
	private double average;

	public StudentRecord() {
		// default constructor
		this.studentName = "";
		this.studentID = "";
		this.address = "";
		this.average = 0;

	}


	// access methods 

	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getStudentID() {
		return studentID;
	}


	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}


	public double getAverage() {
		return average;
	}


	public void setAverage(double average) {
		this.average = average;
	}
	
	// method to  split the string into an array of strings
	public void processRecord (String record) {
		String word[];
		word = record.split(",");
		this.studentName = word [0];
		this.studentID = word[1];
		this.address = word[2];
		this.average = Double.parseDouble(word[3]);
				
	}
	
	// method to convert data back to string
	public String toString() {
		
		return(this.studentName + ","+ this.studentID + "," + this.address + "," + Double.toString(this.average));	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String record [] = new String [1];
		
		record [0] = "Chajeesan,123456,123 Sunset Blvd,23";
		
		StudentRecord sInfo = new StudentRecord ();
		
		sInfo.processRecord(record[0]);
		System.out.println(sInfo.toString ());
		System.out.println(sInfo.getStudentName ());
		System.out.println(sInfo.getStudentID ());
		System.out.println(sInfo.getAddress ());
		System.out.println(sInfo.getAverage ());
		
		sInfo.setStudentName("Kuldeep");
		System.out.println(sInfo.getStudentName());
		
		sInfo.setAddress("24 Sussex Drive");
		
		sInfo.setStudentID("555555");
		
		System.out.println(sInfo.getStudentID());
		
		sInfo.setAverage(99.999);
		System.out.println(sInfo.getAverage());
		
	}
}
