package agencyrentalprogram;

public class Car extends Vehicle 
{
    private String numSeats; // number of seats
    
    // constructor methods
    public Car(String description, int mpg, String vin, String numSeats)
    {
        super(description, mpg, vin); // Vehicle class methods
        
        this.numSeats = numSeats;
    }
    
    //getter methods
    public String getNumSeats()
    {
        return numSeats;
    }
    
    // Format: Ford Fusion(Car) MPG: 34 Seating: 4 VIN: AB4FG5689GM      
    public String toString()
    {
        return super.getDescription() + "(Car) MPG: " + super.getMpg() + " Seating: " + getNumSeats() + " VIN: " + super.getVin();
    }
}
