package agencyrentalprogram;
public class Rates 
{
    //instance variable  
    private VehicleRates[] rates = new VehicleRates[3]; // array of size 3 to store rates for three types of vehicles 
    
    // constructor – passed CarRates, SUVRates and TruckRates objects to assign in vehicleRates array
    public Rates(CarRates car, SuvRates suv, TruckRates truck)
    {
        rates[0] = car;
        rates[1] = suv;
        rates[2] = truck;
    }
    
    // getter method for array
    public VehicleRates getCarRates() 
    {
        return rates[0];
    }
    public VehicleRates getSuvRates()
    {
        return rates[1];
    }
    public VehicleRates getTruckRates() 
    {
        return rates[2];
    }
    
    //setter methods
    public void setCarRates(CarRates c)
    {
        rates[0] = c;
    }
    public void setSuvRates(SuvRates s)
    {
        rates[1] = s;
    }
    public void setTruckRates(TruckRates t)
    {
        rates[2] = t;
    }
    
    // calculates estimated cost of rental 
    // called to calculate a cost quote for a customer given an estimated number of days will use and estimated number of miles will drive, whether the daily insurance wanted, and whether a prime customer or not
    // uses the CURRENT standard rates for the vehicle type (car, SUV or truck) 
    // passed encoded estimated rental period e.g., D4 (four days), and W2, (two weeks), M1 (one month). 
    public double calcEstimatedCost(int vehicleType, String estimatedRentalPeriod, int estimatedNumMiles, boolean dailyInsur, boolean primeCustomer)
    {
        VehicleRates vehRates = null; // sets for carRates, suvRates or truckRates
        double estimate = 0; // estimated cost 
        String timePeriod;
        int timeLength;
        
        // adjust miles for prime customers
        if(primeCustomer)
        {
            if(estimatedNumMiles > 100)
            {
                estimatedNumMiles = estimatedNumMiles - 100;
            }
            else
            {
                estimatedNumMiles = 0;
            }
        }
        
        switch(vehicleType) // pick which type of vehicle rates
        {
            case 1: vehRates = getCarRates(); break;
            case 2: vehRates = getSuvRates(); break;
            case 3: vehRates = getTruckRates(); break;
        }
        
        timePeriod = estimatedRentalPeriod.substring(0, 1); // get D, W, or M
        timeLength = Integer.valueOf(estimatedRentalPeriod.substring(1)); // convert number from string to int
        
        if(timePeriod.toUpperCase().equals("D"))
        {
            estimate = vehRates.cloneAsCostType().getDailyRate() * timeLength;
            estimate = estimate + vehRates.cloneAsCostType().getMileageCharge() * estimatedNumMiles;
        }
        else if(timePeriod.toUpperCase().equals("W"))
        {
            estimate = vehRates.cloneAsCostType().getWeeklyRate() * timeLength;
            estimate = estimate + vehRates.cloneAsCostType().getMileageCharge() * estimatedNumMiles;
        }
        else if(timePeriod.toUpperCase().equals("M"))
        {
            estimate = vehRates.cloneAsCostType().getMonthlyRate() * timeLength;
            estimate = estimate + vehRates.cloneAsCostType().getMileageCharge() * estimatedNumMiles;
        }
        if(dailyInsur) // insurance rate
        {
            estimate = estimate + vehRates.cloneAsCostType().getDailyInsurRate();
        }
        return estimate;
    }
    
    // called to calculate charge of a returned rented vehicle (for number of days used, num miles driven, whether daily insurance was selected, and whether a prime customer or not). 
    // uses the QUOTED RATE, the rates as they were at the time of the reservation. 
    // if the number of days is less than 7, then charged the daily rate; if number of days is greater than 7 and less than 30, then charged the weekly rate (with remaining days a prorated weekly rate); otherwise, get the monthly rate (with remaining days a prorated monthly rate). 
    public double calcActualCost(Cost rates, int numDaysUsed, int NumMilesDriven, boolean dailyInsur, boolean primeCustomer)
    {
        VehicleRates vehRates;
        double estimate = 0;
     
        if(primeCustomer)
        {
            if(NumMilesDriven > 100)
            {
                NumMilesDriven = NumMilesDriven - 100;
            }
            else
            {
                NumMilesDriven = 0;
            }
        }
        
        if(rates instanceof Car)
        {
            vehRates = getCarRates();
        }
        else if(rates instanceof Suv)
        {
            vehRates = getSuvRates();
        }
        else
        {
            vehRates = getTruckRates();
        }
            
        if(numDaysUsed < 7)
        {
            estimate = vehRates.cloneAsCostType().getDailyRate() * numDaysUsed;
            estimate = estimate + vehRates.cloneAsCostType().getMileageCharge() * NumMilesDriven;
        }
        else if(numDaysUsed < 30)
        {
            double numWeeks = numDaysUsed / 7;
            estimate = vehRates.cloneAsCostType().getWeeklyRate() * numWeeks;
            estimate = estimate + vehRates.cloneAsCostType().getMileageCharge() * NumMilesDriven;
        }
        else
        {
            double numMonths = numDaysUsed / 30;
            estimate = vehRates.cloneAsCostType().getMonthlyRate() * numMonths;
            estimate = estimate + vehRates.cloneAsCostType().getMileageCharge() * NumMilesDriven;
        }
        if(dailyInsur == true)
        {
            estimate = estimate + (vehRates.cloneAsCostType().getDailyInsurRate());
        }
        return estimate;
    }
}
