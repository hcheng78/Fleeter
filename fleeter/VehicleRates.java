package agencyrentalprogram;

public abstract class VehicleRates 
{
    // instance variables
    private double daily_rate;
    private double weekly_rate;
    private double monthly_rate;
    private double per_mile_chrg;
    private double daily_insur_rate;

    // constructor
    public VehicleRates(double daily_rate, double weekly_rate, double monthly_rate ,double per_mile_chrg, double daily_insur_rate)
    {
        this.daily_rate = daily_rate;
        this.weekly_rate = weekly_rate;
        this.monthly_rate = monthly_rate;
        this.per_mile_chrg = per_mile_chrg;
        this.daily_insur_rate = daily_insur_rate;
    }
    
    // getter methods
    public double getDailyRate() 
    {
        return daily_rate;
    }
    public double getWeeklyRate() 
    {
        return weekly_rate;
    }
    public double getMonthlyRate() 
    {
        return monthly_rate;
    }
    public double getMileageCharge() 
    {
        return per_mile_chrg;
    }
    public double getDailyInsurRate() 
    {
        return daily_insur_rate;
    }
    
    // private class
    private class PrivateCost implements Cost
    {
        // instance variables
        private double daily_rate;
        private double weekly_rate;
        private double monthly_rate;
        private double per_mile_chrg;
        private double daily_insur_rate;
        
        // constructor
        public PrivateCost(double daily_rate, double weekly_rate, double monthly_rate ,double per_mile_chrg, double daily_insur_rate)
        {
            this.daily_rate = daily_rate;
            this.weekly_rate = weekly_rate;
            this.monthly_rate = monthly_rate;
            this.per_mile_chrg = per_mile_chrg;
            this.daily_insur_rate = daily_insur_rate;
        }
        
        // getter methods
        public double getDailyRate()  
        {
            return daily_rate;
        }
        public double getWeeklyRate() 
        {
            return weekly_rate;
        }
        public double getMonthlyRate()  
        {
            return monthly_rate;
        }
        public double getMileageCharge()    
        {
            return per_mile_chrg;
        }
        public double getDailyInsurRate() 
        {
            return daily_insur_rate;
        }
    }
    
    // creates object of type PrivateCost but returns as more general type Cost
    public Cost cloneAsCostType() 
    {
        return new PrivateCost(daily_rate, weekly_rate, monthly_rate, per_mile_chrg, daily_insur_rate);
    }   
}
