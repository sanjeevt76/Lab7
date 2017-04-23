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
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    VehicleRecordList vehicleList = new VehicleRecordList();
    
    while(true){
      Character command = JOptionPane.showInputDialog(null,
                                                 "i - insert a vehicle\n" + 
                                                 "c - change a vehicle info\n" + 
                                                 "d - delete a vehicle\n" + 
                                                 "p - print the list of vehicles",
                                                 "Enter your choice").charAt(0);
      
      if (command == null)
        break;
      
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
      }
    }
  }
}
