package agencyrentalprogram;

public class RentalDetails 
{
    // instance variables
    private int vehicleType; // selects which vehicle type 
    private String rentalPeriod; // rental period
    private int miles; // # of miles driven
    private boolean insurance; // return true if insurance selected
    private boolean primeCustomer; // return true if prime customer
    
    //constructor
    public RentalDetails(int vehicleType, String rentalPeriod, int miles, boolean insurance, boolean primeCustomer)
    {
        this.vehicleType = vehicleType;
        this.rentalPeriod = rentalPeriod;
        this.miles = miles;
        this.insurance = insurance; 
        this.primeCustomer = primeCustomer;
    }
    
    //getter methods
    public int getVehType()
    {
        return vehicleType;
    }
    public String getRentalPeriod()
    {
        return rentalPeriod;
    }
    public int getEstimatedMiles()
    {
        return miles;
    }
    public boolean getInsurance()
    {
        return insurance;
    }
    public boolean getPrimeCustomer()
    {
        return primeCustomer;
    }
}
