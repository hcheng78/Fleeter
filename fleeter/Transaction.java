package agencyrentalprogram;

public class Transaction 
{
    //instance variables 
    private String acct_num; // five-digit acct number 
    private String company_name; // company name
    private String vehicle_type; // car, SUV or Truck 
    private String rental_period; // days, week, months 
    private String rental_cost; // cost
    
    //constructor
    public Transaction(String acct_num, String company_name, String vehicle_type, String rental_period, String rental_cost)
    {
        this.acct_num = acct_num;
        this.company_name = company_name;
        this.vehicle_type = vehicle_type;
        this.rental_period = rental_period;
        this.rental_cost = rental_cost;
    }
    
    //getter methods
    public String getAcctNum()
    {
        return acct_num;
    }
    public String getCompanyName()
    {
        return company_name;
    }
    public String getVehicleType()
    {
        return vehicle_type;
    }
    public String getRentalPeriod()
    {
        return rental_period;
    }
    public String getRentalCost()
    {
        return rental_cost;
    }
    
    // Format: Baltimore Environmental Company (acct# 10245) Car, 3 day rental, $120.54
    public String toString()
    {
        String timePeriod = getRentalPeriod().substring(0, 1);
        String timeLength = getRentalPeriod().substring(1).toUpperCase();
        
        switch(timePeriod)
        {
            case "D": timePeriod = "day";
            case "W": timePeriod = "week";
            case "M": timePeriod = "month";
        }
        
        return getCompanyName() + " (acct# " + getAcctNum() + ")" + getVehicleType() + ", " + timeLength + " " + timePeriod + " rental, $" + getRentalCost(); 
    }
}
 