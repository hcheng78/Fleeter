package agencyrentalprogram;

public class Truck extends Vehicle
{
    private String loadCap; // loading capacity
    
    //constructor
    public Truck(String description, int mpg, String vin, String loadCap)
    {
        super(description, mpg, vin); // Vehicle class methods
        
        this.loadCap = loadCap;
    }
    
    //getter method
    public String getLoadCap()
    {
        return loadCap;
    }
    
    // Format: Seventeen-Foot (Truck) MPG: 12 Load Capacity: 5390 lbs. VIN: EJ5KU2435BC   
    public String toString()
    {
        return super.getDescription() + "(Truck) MPG: " + super.getMpg() + " Load Capacity: " + getLoadCap() + " lbs. VIN:" + super.getVin();
    }
}
