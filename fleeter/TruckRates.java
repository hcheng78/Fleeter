package agencyrentalprogram;

public class TruckRates extends VehicleRates
{
    //constructor
    public TruckRates(double daily_rate, double weekly_rate, double monthly_rate ,double per_mile_chrg, double daily_insur_rate)
    {
        super(daily_rate, weekly_rate, monthly_rate, per_mile_chrg, daily_insur_rate);
    }
}