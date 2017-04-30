import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
    public boolean insert(VehicleRecord record) {
        if (size < maxSize) {
            size++;
            list[size - 1] = record;
            return true;
        }
        return false;
    }

    // method that changes the vehicle info
    public boolean change(VehicleRecord record, int index) {
        if (index < maxSize) {
            list[index] = record;
            insertionSortByVehicleMake();
            return true;
        }

        return false;
    }

    // method that deletes the vehicle record
    public boolean delete(VehicleRecord record) {
        insertionSortByVehicleMake();
        int loc = binarySearch(record.getMake());

        if (loc >= 0) {
            for (int where = loc; where < size; where++) {
                list[where] = list[where + 1]; // puts last one in my spot
                size--;   // decrease size of list

                return true;
            }
        }

        return false;
    }

    // method to get list
    private VehicleRecord[] getList() {
        return this.list;
    }

    // method to get a vehicle based on index number
    private VehicleRecord getVehicle(int index) {
        return this.list[index];
    }


    // method to get size
    private int getSize() {
        return this.size;
    }

    public int linearSearch(String searchKey) {
        for (int i = 0; i < size; i++) {
            if (searchKey.equalsIgnoreCase(list[i].getModel()))
                return i;
        }

        return -1;  // element was not found
    }

    /*
     * Binary Search - search for a name
     */
    public int binarySearch(String searchKey) {
        int low = 0;
        int high = size - 1;
        int middle;

        while (low <= high) {
            middle = (high + low) / 2;
            if (searchKey.equalsIgnoreCase(list[middle].getMake())) {
                return middle;  // element was found
            } else if (searchKey.compareToIgnoreCase(list[middle].getMake()) < 0) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;  // element was not found
    }

    public boolean loadVehiclesFromFile(String filename) throws IOException {

        FileReader rdr = new FileReader(filename);
        BufferedReader input = new BufferedReader(rdr);
        
        for (int i = 0; i < maxSize; i++) {
          while (input.readLine() != null) {
            String record = input.readLine();
            //JOptionPane.showMessageDialog(null, record);
            VehicleRecord vInfo = new VehicleRecord();
            vInfo.processVehicle(record);
            insert(vInfo);
        }
        }

        input.close();
        rdr.close();

        return true;
    }

    public void insertionSortByVehicleMake() {
        for (int top = 1; top < size; top++) {
            VehicleRecord record = list[top];

            int i = top;
            while (i > 0 && record.getMake().compareToIgnoreCase(list[i - 1].getMake()) < 0) {
                list[i] = list[i - 1];
                i--;
            }

            list[i] = record;
        }
    }

    public void selectionSortByVehicleYear() {
        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i+1; j<size; j++) {
                if (list[j].getYear() < list[min].getYear())
                    min = j;
            }

            VehicleRecord record = list[i];
            list[i] = list[min];
            list[min] = record;
        }
    }

    public static void main(String[] args) throws IOException {
        VehicleRecordList vehicleList = new VehicleRecordList();

        while (true) {
            char command = JOptionPane.showInputDialog(null,
                    "i - insert a vehicle\n" +
                            "c - change a vehicle info\n" +
                            "d - delete a vehicle\n" +
                            "p - print the list of vehicles\n" +
                            "b - find a vehicle by make\n" +
                            "l - find a vehicle by model\n" +
                            "f - load vehicles from a file\n" +
                            "s - sort list of vehicles by make\n" +
                            "y - sort list of vehicles by year\n",
                    "Enter your choice").charAt(0);


            switch (command) {
                case 'i': {
                    String record = JOptionPane.showInputDialog(null,
                            "Enter <make>,<model>,<year>,<type>\nExample: Toyota,Sienna,2016,v",
                            "Toyota,Sienna,2016,v");
                    VehicleRecord vInfo = new VehicleRecord();
                    vInfo.processVehicle(record);

                    if (!vehicleList.insert(vInfo)) {
                        JOptionPane.showMessageDialog(null, "not added");
                    } else {
                        JOptionPane.showMessageDialog(null, "success");
                    }

                    break;
                }


                case 'c': {
                    // get the index of the vehicle that is being changed
                    int recordNum = Integer.parseInt(JOptionPane.showInputDialog(null,
                            "Enter vehicle number you want to change:",
                            "Enter number"));
                    VehicleRecord vInfo = new VehicleRecord();
                    vInfo = vehicleList.getVehicle(recordNum - 1);  // get vehicle from the list

                    if (vInfo != null) {
                        // get new info of that vehicle
                        String record = JOptionPane.showInputDialog(null,
                                "Vehicle Info: " + vInfo +
                                        "\n\nEnter your changes.\nExample: Toyota,Sienna,2016,v",
                                vInfo.toString("original"));
                        vInfo.processVehicle(record);

                        if (!vehicleList.change(vInfo, recordNum - 1)) {
                            JOptionPane.showMessageDialog(null, "not changed");
                        } else {
                            JOptionPane.showMessageDialog(null, "success");
                        }
                    }

                    break;
                }

                case 'd': {
                    String record = JOptionPane.showInputDialog(null,
                            "Enter record to delete\nExample: Toyota,Sienna,2016,v",
                            "Toyota,Sienna,2016,v");
                    VehicleRecord vInfo = new VehicleRecord();
                    vInfo.processVehicle(record);

                    if (!vehicleList.delete(vInfo)) {
                        JOptionPane.showMessageDialog(null, "record not found");
                    } else {
                        JOptionPane.showMessageDialog(null, "record deleted");
                    }

                    break;
                }

                case 'p': {
                    VehicleRecord[] myRecords = vehicleList.getList();
                    String output = "";

                    for (int i = 0; i < vehicleList.getSize(); i++) {
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

                case 'b': {
                    String record = JOptionPane.showInputDialog(null,
                            "Enter vehicle to find",
                            "Toyota,Sienna,2016,v");
                    VehicleRecord vInfo = new VehicleRecord();
                    vInfo.processVehicle(record);

                    int loc =
                            vehicleList.binarySearch(vInfo.getMake());

                    if (loc < 0) {
                        JOptionPane.showMessageDialog(null, "Record not found");
                    } else {
                        JOptionPane.showMessageDialog(null, "found!");
                    }
                    break;
                }

                case 'l': {
                    String model = JOptionPane.showInputDialog(null,
                            "Enter vehicle model to find",
                            "Sienna");
                    //VehicleRecord vInfo = new VehicleRecord();
                    //vInfo.processVehicle(record);

                    int loc =
                            vehicleList.linearSearch(model);

                    if (loc < 0) {
                        JOptionPane.showMessageDialog(null, "Record not found");
                    } else {
                        JOptionPane.showMessageDialog(null, "found!");
                    }
                    break;
                }

                case 'f': {
                    String filename = JOptionPane.showInputDialog(null, "Enter the filename you would like to load", "vehicles.txt");

                    try {
                        vehicleList.loadVehiclesFromFile(filename);
                        JOptionPane.showMessageDialog(null, "File loaded");
                    }
                    catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "File not found");
                    }
                    catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "File corrupted");
                    }

                    break;
                }

                case 's': {
                    vehicleList.insertionSortByVehicleMake();
                    JOptionPane.showMessageDialog(null, "sorted!");
                    break;
                }

                case 'y': {
                    vehicleList.selectionSortByVehicleYear();
                    JOptionPane.showMessageDialog(null, "sorted!");
                    break;
                }
            }
        }
    }
}
