package agencyrentalprogram;

public class ReturnedVehicleDetails 
{
    // instance variables
    private int numDays;
    private int numMiles;
    private boolean dailyInsur;
    private boolean primeCustomer;
    
    // constructor
    public ReturnedVehicleDetails(int days, int miles, boolean insur, boolean prime)
    {
        numDays = days;
        numMiles = miles;
        dailyInsur = insur;
        primeCustomer = prime;
    }
    
    // getter methods
    public int getNumDays()
    {
        return numDays;
    }
    public int getNumMiles()
    {
        return numMiles;
    }
    public boolean getInsurance()
    {
        return dailyInsur;
    }
    public boolean getPrime()
    {
        return primeCustomer;
    }
}
