public class VehicleRecord {
  private String make;
  private String model;
  private int year;
  private char type;
  
  public VehicleRecord() {
    // TODO Auto-generated constructor stub
  }
  
  // getter and setters
  public String getMake() {
    return make;
  }
  
  public void setMake(String make) {
    this.make = make;
  }
  
  public String getModel() {
    return model;
  }
  
  public void setModel(String model) {
    this.model = model;
  }
  
  public int getYear() {
    return year;
  }
  
  public void setYear(int year) {
    this.year = year;
  }
  
  public char getType() {
    return type;
  }
  
  public void setType(char type) {
    this.type = type;
  }
  
  // method that assigns the string values to field variables
  public void processVehicle (String vehicle) {
    String word[];
    word = vehicle.split(",");
    this.make= word [0];
    this.model = word[1];
    this.year = Integer.parseInt(word[2]);
    this.type = word[3].charAt(0);
  }
  
  // method that returns the vehicle name based on its type
  public String getVehicleName(char vType) {
    String vName = "";
    
    switch(Character.toLowerCase(vType)){
      case 'p':
        vName = "Passenger";
        break;
      case 'v':
        vName = "Van";
        break;
      case 's':
        vName = "SUV";
        break;
      case 't':
        vName = "Truck";
        break;
    }
    
    return vName;
  }
  
  // method to convert data back to string
  public String toString() {
    return(this.make + " " + this.model + " " + this.year + " " + getVehicleName(this.type));  
  }
  
  // method that returns the data in its original format
  public String toString(String format) {
    return(this.make + "," + this.model + "," + this.year + "," + this.type);
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    String vehicle = "Toyota,Sienna,2016,v";
    
    VehicleRecord vInfo = new VehicleRecord();
    vInfo.processVehicle(vehicle);
    
    System.out.println(vInfo.toString ());
    System.out.println(vInfo.getMake());
    System.out.println(vInfo.getModel());
    System.out.println(vInfo.getYear());
    System.out.println(vInfo.getType());
  }
}
