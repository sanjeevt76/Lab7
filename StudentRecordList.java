import javax.swing.JOptionPane;


public class StudentRecordList {

	private StudentRecord list [];
	private int maxSize;
	private int size;

	public StudentRecordList() {
		this.maxSize = 10;
		this.size = 0;
		this.list = new StudentRecord[maxSize];
	}

	/*
	 * Method to insert a record to the list
	 * checks if there is space (size is below maxSize)
	 * increases size
	 * adds record to the location below the highest element
	 * returns true if successful
	 */
	public boolean insert (StudentRecord record){
		if (size < maxSize){
			size++;
			list [size-1] = record;
			return true;
		}
		return false;
	}

	// method to get the list
	private StudentRecord [] getList (){
		return this.list;
	}

	// method to get size
	private int getSize(){
		return this.size;
	}

	/*
	 * finds the record to delete
	 * then moves the last record to the one to be 
	 * deleted
	 * shortens the list (decreases the size of the list)
	 * returns true if deleted
	 */
	public boolean delete(StudentRecord record){
		for (int where = 0; where < size; where++){
			if (list[where].getStudentName().equalsIgnoreCase(record.getStudentName())){
				list[where] = list[size-1]; // puts last one in my spot
				size--;   // decrease size of list
				return true;
			}
		}
		return false;
	}


	/**
	 * @param args
	 */

	/*
	 * Bubble sort method
	 */
	public void bubbleSort(){
		for (int pass = 1; pass < size; pass++){
			for (int element = 0; element < size -1; element ++){
				if(list[element].getStudentName()
						.compareToIgnoreCase(list[element+1]
								.getStudentName())>0){

					StudentRecord hold = list[element];
					list [element] = list[element+1];
					list [element+1] = hold;

				}// end if

			} // end for element

		}// end for pass

	} // end bubble sort 

	/*
	 * Binary search - search for a name
	 */

	public int binarySearch (StudentRecord rec, String searchKey){
		int low = 0;
		int hight = size -1;
		int middle;

		while (low <= high){
			middle = (high + low)/2;
			if(searchKey.equalsIgnoreCase(list[middle].getStudentName())){
				return middle;  // element was found 
			}
			else if(searchKey.comparedToIgnoreCase(list[middle].getStudentName())<0){
				high = middle -1;	
			}
			else { 
				low = middle +1;

			}
		} return -1; // element was not found
	}

	public static void main(String[] args) {

		StudentRecordList studentList = new StudentRecordList();

		while(true){
			char command;
			command = JOptionPane.showInputDialog(null,
					"i - insert a student\n" + 
							"p - print the list\n" +
							"d - deletes a student\n" +
							"s- sort by name\n"+
					"f - find a person \n").charAt(0);
			switch (command){
			case 'i':{ 
				String record = JOptionPane.showInputDialog(null, 
						"Enter <name>,<ID>,<address>,<average>",
						"Sanjeev,500295,45 Daviselm,100");
				StudentRecord sInfo = new StudentRecord();
				sInfo.processRecord(record);
				if (!studentList.insert(sInfo)){
					JOptionPane.showMessageDialog(null, "not added");
				}
				else{
					JOptionPane.showMessageDialog(null, "success");
				}
				break;
			}
			case 'd':{
				String record = JOptionPane.showInputDialog(null, 
						"Enter record to delete",
						"Tony Campos,P123456,45 Daviselm,100");
				StudentRecord sInfo = new StudentRecord();
				sInfo.processRecord(record);
				if (!studentList.delete(sInfo)){
					JOptionPane.showMessageDialog(null, "record not found");
				}
				else{
					JOptionPane.showMessageDialog(null, "record deleted");
				}
				break;
			}
			case 'p':{
				StudentRecord [] myRecords = studentList.getList();
				for (int i = 0; i < studentList.getSize(); i++){
					System.out.println(myRecords[i]);
				}	
				break;
			}
			case 's':{
				studentList.bubbleSort();
				break;
			}
			case 'f':{
				String record = JOptionPane.showInputDialog(null, 
						"Enter name to find",
						"Sanjeev,500295,45 Daviselm,100");
				StudentRecord sInfo = new StudentRecord();
				sInfo.processRecord(record);	

				int loc = studentList.binarySearch(sInfo.getStudentName());

				if (loc < 0){
					JOptionPane.showMessageDialog(null,"Record not found");	
				}
				else {
					JOptionPane.showMessageDialog(null,"found!");

				}
				break;
			}
			}
		}

	}

}
