import javax.swing.JOptionPane;

public class VehicleRecordList {
	private VehicleRecord list[];
	private int maxSize;
	private int size;

	// constructor
	public VehicleRecordList() {
		this.maxSize = 10;
		this.size = 0;
		this.list = new VehicleRecord[maxSize];
	}

	// method that inserts a new vehicle to the list
	public boolean insert (VehicleRecord record){
		if (size < maxSize){
			size++;
			list [size-1] = record;
			return true;
		}
		return false;
	}

	// method that changes the vehicle info
	public boolean change (VehicleRecord record, int index){
		if (index < maxSize){
			list[index] = record;
			return true;
		}
		return false;
	}

	// method that deletes the vehicle record
	public boolean delete(VehicleRecord record){
		for (int where = 0; where < size; where++){
			if (list[where].getMake().equalsIgnoreCase(record.getMake())){
				list[where] = list[size-1]; // puts last one in my spot
				size--;   // decrease size of list

				return true;
			}
		}
		return false;
	}

	// method to get list
	private VehicleRecord[] getList(){
		return this.list;
	}

	// method to get a vehicle based on index number
	private VehicleRecord getVehicle(int index){
		return this.list[index];
	}


	// method to get size
	private int getSize(){
		return this.size;
	}
	/*
	 * Bubble sort method for names in alpha order
	 */
	public void bubbleSort(){
		for (int pass = 1; pass < size; pass++){
			for (int element = 0; element < size -1; element++){
				if (list[element].getMake()
						.compareToIgnoreCase(list[element+1]
								.getMake())>0){

					VehicleRecord hold = list[element];
					list[element] = list[element+1];
					list[element+1] = hold;

				} // end if
			}// end for element
		}// end for pass
	}  // end bubblesort

	/*
	 * Binary Search - search for a name
	 */
	public int binarySearch (String searchKey){
		int low = 0;
		int high = size -1;
		int middle;

		while (low <= high){
			middle = (high + low)/2;
			if (searchKey.equalsIgnoreCase(list[middle].getMake())){
				return middle;  // element was found
			}
			else if(searchKey.compareToIgnoreCase(list[middle].getMake())<0){
				high = middle -1;
			}
			else {
				low = middle + 1;
			}
		}
		return -1;  // element was not found
	}
	public static void main(String[] args) {
		VehicleRecordList vehicleList = new VehicleRecordList();

		while(true){
			char command = JOptionPane.showInputDialog(null,
					"i - insert a vehicle\n" + 
							"c - change a vehicle info\n" + 
							"d - delete a vehicle\n" + 
							"p - print the list of vehicles\n" +
							"s - sort by vehcile name\n"+
							"f - find a vehicle\n",
					"Enter your choice").charAt(0);



			switch (command){
			case 'i':{ 
				String record = JOptionPane.showInputDialog(null, 
						"Enter <make>,<model>,<year>,<type>\nExample: Toyota,Sienna,2016,v",
						"Toyota,Sienna,2016,v");
				VehicleRecord vInfo = new VehicleRecord();
				vInfo.processVehicle(record);

				if (!vehicleList.insert(vInfo)){
					JOptionPane.showMessageDialog(null, "not added");
				}
				else{
					JOptionPane.showMessageDialog(null, "success");
				}

				break;
			}


			case 'c':{
				// get the index of the vehicle that is being changed
				int recordNum = Integer.parseInt(JOptionPane.showInputDialog(null, 
						"Enter vehicle number you want to change:",
						"Enter number"));
				VehicleRecord vInfo = new VehicleRecord();
				vInfo = vehicleList.getVehicle(recordNum-1);  // get vehicle from the list

				if (vInfo != null){
					// get new info of that vehicle
					String record = JOptionPane.showInputDialog(null, 
							"Vehicle Info: " + vInfo + 
							"\n\nEnter your changes.\nExample: Toyota,Sienna,2016,v",
							vInfo.toString("original"));
					vInfo.processVehicle(record);

					if (!vehicleList.change(vInfo, recordNum - 1)){
						JOptionPane.showMessageDialog(null, "not changed");
					}
					else{
						JOptionPane.showMessageDialog(null, "success");
					}
				}

				break;
			}

			case 'd':{
				String record = JOptionPane.showInputDialog(null, 
						"Enter record to delete\nExample: Toyota,Sienna,2016,v",
						"Toyota,Sienna,2016,v");
				VehicleRecord vInfo = new VehicleRecord();
				vInfo.processVehicle(record);

				if (!vehicleList.delete(vInfo)){
					JOptionPane.showMessageDialog(null, "record not found");
				}
				else{
					JOptionPane.showMessageDialog(null, "record deleted");
				}

				break;
			}

			case 'p':{
				VehicleRecord[] myRecords = vehicleList.getList();
				String output = "";

				for (int i = 0; i < vehicleList.getSize(); i++){
					output += myRecords[i] + "\n";
				}

				if (output == "")
					JOptionPane.showMessageDialog(null, "List is empty");
				else
					JOptionPane.showMessageDialog(null, output);

				break;
			}

			default:
				continue;

			case 's':{
				vehicleList.bubbleSort();
				break;
			}
			case 'f':{
				String record = JOptionPane.showInputDialog(null, 
						"Enter vehicle to find",
						"Toyota,Sienna,2016,v");
				VehicleRecord vInfo = new VehicleRecord();
				vInfo.processVehicle(record);

				int loc = 
						vehicleList.binarySearch(vInfo.getMake());

				if (loc < 0){
					JOptionPane.showMessageDialog(null, "Record not found");
				}
				else {
					JOptionPane.showMessageDialog(null, "found!");
				}
				break;
			}

			}

		}
	}
}


