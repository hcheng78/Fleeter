package agencyrentalprogram;

public class Suv extends Vehicle
{
    private String numSeats; // number of seats
    private String cargoCap; // cargo capacity
    
    //constructor
    public Suv(String description, int mpg, String vin, String numSeats, String cargoCap)
    {
        super(description, mpg, vin); // Vehicle class methods
        
        this.numSeats = numSeats;
        this.cargoCap = cargoCap;
    }
    
    //getter methods
    public String getNumSeats()
    {
        return numSeats;
    }
    public String getCargoCap()
    {
        return cargoCap;
    }
    
    // Format: Honda Odyssey(SUV) MPG: 28 Seating: 7 Storage: 6 cu. ft. VIN: QK3FL4273ME  
    public String toString()
    {
        return super.getDescription() + "(SUV) MPG: " + super.getMpg() + " Seating: " + getNumSeats() + " Storage: " + getCargoCap() + " cu. ft. VIN: " + super.getVin();
    }
}
