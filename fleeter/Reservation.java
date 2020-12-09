package agencyrentalprogram;

public class Reservation 
{
    //instance variables  
    private String acctNum; // account number of company account   
    private String rentalPeriod; // e.g., “D4” (four days), “W2” (two weeks), “M1” (one month)  
    private boolean insuranceSelected; // set to true if optional daily insurance wanted    
    
    // constructor
    public Reservation(String acctNum, String rentalPeriod, boolean insuranceSelected)
    {
        this.acctNum = acctNum;
        this.rentalPeriod = rentalPeriod;
        this.insuranceSelected = insuranceSelected;
    }
    
    // getter methods
    public String getAcctNum()
    {
        return acctNum;
    }
    public String getRentalPeriod()
    {
        return rentalPeriod;
    }
    public boolean getInsurance()
    {
        return insuranceSelected;
    }
    
    // Format: Account: #12345, Rental Period: 3 days, Insurance: Daily Insurance Included
    public String toString()
    {
        String insur; 
        String timePeriod;
        String timeLength;
       
        if(getInsurance() == true)
        {
            insur = "Daily Insurance Included";
        }
        else
        {
            insur = "Daily Insurance Declined";
        }
        
        timePeriod = getRentalPeriod().substring(0, 1);
        timeLength = getRentalPeriod().substring(1).toUpperCase();
        
        switch(timePeriod)
        {
            case "D": timePeriod = "days"; break;
            case "W": timePeriod = "weeks"; break;
            case "M": timePeriod = "months"; break;
        }
        
        return "Account: #" + getAcctNum() + ", Rental Period: " + timeLength + " " + timePeriod + ", Insurance: " + insur;
    }
}
