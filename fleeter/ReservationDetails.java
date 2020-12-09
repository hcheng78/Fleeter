package agencyrentalprogram;

public class ReservationDetails 
{
    private String vin; // vin number
    private String acctNum; // 5 digit account number 
    private String rentalPeriod; // e.g. D3 W3 M1
    private boolean dailyInsur; // true if insurance is wanted
    
    //constructor
    public ReservationDetails(String vin, String acctNum, String rentalPeriod, boolean dailyInsur)
    {
        this.vin = vin;
        this.acctNum = acctNum;
        this.rentalPeriod = rentalPeriod;
        this.dailyInsur = dailyInsur;
    }
    
    //getter methods
    public String getVin()
    {
        return vin;
    }
    public String getAcctNum()
    {
        return acctNum;
    }
    public String getRentalPeriod()
    {
        return rentalPeriod;
    }
    public boolean getDailyInsur()
    {
        return dailyInsur;
    }
}
