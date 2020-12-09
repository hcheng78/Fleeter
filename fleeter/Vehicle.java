package agencyrentalprogram;

public abstract class Vehicle 
{
    //instance variables  
    private String description; // make-model for cars/suvs, length for trucks  
    private int mpg; // miles per gallon  
    private String vin; // unique vehicle identification number  
    private Reservation resv; // if null, then vehicle not reserved  
    private Cost rates; // only assigned when vehicle reserved 
    
    //constructor  
    public Vehicle(String description, int mpg, String vin) 
    {   
        this.description = description;
        this.mpg = mpg;   
        this.vin = vin;   
        
        resv = null; // init as “no reservation”   
        rates = null; // used to hold rates at the time of the reservation  
    }    
    
    // getter methods
    public String getDescription() 
    {
        return description;
    }
    public int getMpg()
    {
        return mpg;
    }
    public String getVin() 
    {
        return vin;
    }
    public Reservation getReservation() 
    {
        return resv;
    }
    public Cost getCost() 
    {
        return rates;
    }
    
    public boolean isReserved() // returns true if reserved
    {
        return resv != null;
    }
    
    // setter methods
    public void reserve(Reservation r) throws ReservedVehicleException // if vehicle is reserved  
    {
        if(resv != null)
        {
            throw new ReservedVehicleException();
        }
        
        resv = r;
    }
    public void cancelReservation() throws UnreservedVehicleException // if reservation doesn’t exist 
    {
        if(resv == null)
        {
            throw new UnreservedVehicleException();
        }
        
        resv = null;
    }
    public void setCost(Cost cost) // sets cost 
    {
        rates = cost;
    }
    
    public abstract String toString(); // ABSTRACT METHOD – implemented in each subclass                                                      
    // e.g.,Honda Odyssey (SUV) MPG: 28 Seats: 7 Storage: 6 cu. ft. VIN: QK3FL4273ME   
}
