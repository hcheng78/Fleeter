package agencyrentalprogram;

public class SystemInterface 
{
    // variables set to aggregation objects  
    private static Rates rates;  
    private static Vehicles vehs;  
    private static Accounts accts;  
    private static Transactions trans; 
    
    // used to set aggregation objects (in place of a constructor)  
    public static void initSystem(Rates r, Vehicles v,  Accounts a, Transactions t) 
    { 
        rates = r;   
        vehs = v;   
        accts = a;   
        trans = t;  
    }   
    
    // Note that methods makeReserv, cancelReservation, and addAccount return information 
    // submitted as a means of confirming the requested action. 
    
    // vehicle rates-related methods 
    // getter methods
    public static String[] getCarRates() 
    {
        String[] array = new String[5];
        
        array[0] = "Daily Rate: $" + rates.getCarRates().getDailyRate();
        array[1] = "Weekly Rate: $" + rates.getCarRates().getWeeklyRate();
        array[2] = "Monthly Rate: $" + rates.getCarRates().getMonthlyRate();
        array[3] = "Mileage Rate: $" + rates.getCarRates().getMileageCharge();
        array[4] = "Insurance Rate: $" + rates.getCarRates().getDailyInsurRate();
        
        return array;
    }
    public static String[] getSuvRates() 
    {
        String[] array = new String[5];
        
        array[0] = "Daily Rate: $" + rates.getSuvRates().getDailyRate();
        array[1] = "Weekly Rate: $" + rates.getSuvRates().getWeeklyRate();
        array[2] = "Monthly Rate: $" + rates.getSuvRates().getMonthlyRate();
        array[3] = "Mileage Rate: $" + rates.getSuvRates().getMileageCharge();
        array[4] = "Insurance Rate: $" + rates.getSuvRates().getDailyInsurRate();
        
        return array;
    }
    public static String[] getTruckRates() 
    {
        String[] array = new String[5];
        
        array[0] = "Daily Rate: $" + rates.getTruckRates().getDailyRate();
        array[1] = "Weekly Rate: $" + rates.getTruckRates().getWeeklyRate();
        array[2] = "Monthly Rate: $" + rates.getTruckRates().getMonthlyRate();
        array[3] = "Mileage Rate: $" + rates.getTruckRates().getMileageCharge();
        array[4] = "Insurance Rate: $" + rates.getTruckRates().getDailyInsurRate();
        
        return array;
    }
    public static String[] updateCarRates(RatesDetails rDetails)
    {
        rates.setCarRates(new CarRates(rDetails.getDailyRate(), rDetails.getWeeklyRate(), rDetails.getMonthlyRate(), rDetails.getMileageCharge(), rDetails.getDailyInsurRate()));
        
        String[] array = new String[5];
        
        array[0] = "Daily Rate: $" + rDetails.getDailyRate();
        array[1] = "Weekly Rate: $" + rDetails.getWeeklyRate();
        array[2] = "Monthly Rate: $" + rDetails.getMonthlyRate();
        array[3] = "Mileage Rate: $" + rDetails.getMileageCharge();
        array[4] = "Insurance Rate: $" + rDetails.getDailyInsurRate();
        
        return array;
    }
    public static String[] updateSuvRates(RatesDetails rDetails)
    {
        rates.setSuvRates(new SuvRates(rDetails.getDailyRate(), rDetails.getWeeklyRate(), rDetails.getMonthlyRate(), rDetails.getMileageCharge(), rDetails.getDailyInsurRate()));
        
        String[] array = new String[5];
        
        array[0] = "Daily Rate: $" + rDetails.getDailyRate();
        array[1] = "Weekly Rate: $" + rDetails.getWeeklyRate();
        array[2] = "Monthly Rate: $" + rDetails.getMonthlyRate();
        array[3] = "Mileage Rate: $" + rDetails.getMileageCharge();
        array[4] = "Insurance Rate: $" + rDetails.getDailyInsurRate();
        
        return array;
    }
    public static String[] updateTruckRates(RatesDetails rDetails)
    {
        rates.setTruckRates(new TruckRates(rDetails.getDailyRate(), rDetails.getWeeklyRate(), rDetails.getMonthlyRate(), rDetails.getMileageCharge(), rDetails.getDailyInsurRate()));
        
        String[] array = new String[5];
        
        array[0] = "Daily Rate: $" + rDetails.getDailyRate();
        array[1] = "Weekly Rate: $" + rDetails.getWeeklyRate();
        array[2] = "Monthly Rate: $" + rDetails.getMonthlyRate();
        array[3] = "Mileage Rate: $" + rDetails.getMileageCharge();
        array[4] = "Insurance Rate: $" + rDetails.getDailyInsurRate();
        
        return array;
    }
    public static String[] estimatedRentalCost(RentalDetails details)   
    {
        double estimate = rates.calcEstimatedCost(details.getVehType(), details.getRentalPeriod(), details.getEstimatedMiles(), details.getInsurance(), details.getPrimeCustomer());
        
        String[] array = new String[1];
        array[0] = "estimate: $" + estimate;
        
        return array;
    }
    public static String[] processReturnedVehicle(String vin, ReturnedVehicleDetails return_details) 
    {
        double actualCost = rates.calcActualCost(vehs.getVehicle(vin).getCost(), return_details.getNumDays(), return_details.getNumMiles(), return_details.getInsurance(), return_details.getPrime());
        
        String[] array = new String[1];
        array[0] = "Total: $" + actualCost;
        
        return array;
    }
    // setter methods
    
    // vehicle-related methods 
    // get avaiable cars that aren't reserved
    public static String[] getAvailCars() 
    {
        int numCars = 0;
        int find = 0;
        String[] availCars;
        
        vehs.reset();
        while(vehs.hasNext())
        {
            if(vehs.getNext() instanceof Car && vehs.getNext().isReserved() == false)
            {
                numCars ++;
            }
        }
        
        availCars = new String[numCars];
        
        vehs.reset();
        while(vehs.hasNext())
        {
            if(vehs.getNext() instanceof Car && vehs.getNext().isReserved() == false)
            {
                availCars[find] = vehs.getNext().toString();
                
                find ++;
            }
        }
        
        return availCars;
    }
    // get avaiable suvs that aren't reserved
    public static String[] getAvailSuvs() 
    {
        int numSuvs = 0;
        int find = 0;
        String[] availSuvs;
        
        vehs.reset();
        while(vehs.hasNext())
        {
            if(vehs.getNext() instanceof Suv && vehs.getNext().isReserved() == false)
            {
                numSuvs ++;
            }
        }
        
        availSuvs = new String[numSuvs];
        
        vehs.reset();
        while(vehs.hasNext())
        {
            if(vehs.getNext() instanceof Suv && vehs.getNext().isReserved() == false)
            {
                availSuvs[find] = vehs.getNext().toString();
                
                find ++;
            }
        }
        
        return availSuvs;
    }
    // get avaiable trucks that aren't reserved
    public static String[] getAvailTrucks() 
    {
        int numTrucks = 0;
        int find = 0;
        String[] availTrucks;
        
        vehs.reset();
        while(vehs.hasNext())
        {
            if(vehs.getNext() instanceof Truck && vehs.getNext().isReserved() == false)
            {
                numTrucks ++;
            }
        }
        
        availTrucks = new String[numTrucks];
        
        vehs.reset();
        while(vehs.hasNext())
        {
            if(vehs.getNext() instanceof Truck && vehs.getNext().isReserved() == false)
            {
                availTrucks[find] = vehs.getNext().toString();
                
                find ++;
            }
        }
        
        return availTrucks;
    }
    public static String[] getAllVehicles()
    {
        int numVehicles = 0;
        int find = 0;
        String[] allVehicles;
        
        vehs.reset();
        while(vehs.hasNext())
        {
            numVehicles ++;
        }
        
        allVehicles = new String[numVehicles];
        
        vehs.reset();
        while(vehs.hasNext())
        {
            allVehicles[find] = vehs.getNext().toString();
            
            find++;
        }
        
        return allVehicles;
    }
    public static String[] makeReservation(ReservationDetails details)
    {
        vehs.getVehicle(details.getVin()).reserve(new Reservation(details.getAcctNum(), details.getRentalPeriod(), details.getDailyInsur()));
        
        String[] array = new String[2];
        array[0] = vehs.getVehicle(details.getVin()).getReservation().toString();
        array[1] = "Successfully reserved";
        
        return array;
    }
    public static String[] cancelReservation(String vin)
    {
        vehs.getVehicle(vin).cancelReservation();
        
        String[] array = new String[2];
        array[0] = vehs.getVehicle(vin).toString();
        array[1] = "Successfully canceled";
        
        return array;
    }
    public static String[] getReservation(String vin) 
    {
        String[] array = new String[1];
        array[0] = vehs.getVehicle(vin).getReservation().toString();
        
        return array;
    }
    public static String[] getAllReservations() 
    {
        int numRes = 0;
        int find = 0;
        String[] array;
        
        vehs.reset();
        while(vehs.hasNext())
        {
            if(vehs.getNext().isReserved() == true)
            {
                numRes ++;
            }
        }
        
        array = new String[numRes];
        
        vehs.reset();
        while(vehs.hasNext())
        {
            if(vehs.getNext().isReserved() == true)
            {
                array[find] = vehs.getNext().getReservation().toString();
                
                find ++;
            }
        }
        
        return array;
    }
    
    // customer accounts-related methods  
    public static String[] addAccount(AccountDetails info)  
    {
        accts.add(new Account(info.getNewAcctNum(), info.getCompanyName(), vehs, info.primeCustomer()));
        
        String[] array = new String[2];
        array[0] = accts.getAccount(info.getNewAcctNum()).toString();
        array[1] = "Account added successfully";
        
        return array;
    }
    public static String[] updateAccount(String acctNum, AccountDetails info)
    {
        accts.getAccount(acctNum).setAcctNum(info.getNewAcctNum());
        accts.getAccount(acctNum).setCompanyName(info.getCompanyName());
        accts.getAccount(acctNum).setPrimeCustomer(info.primeCustomer());
        
        String prime;
        if(info.primeCustomer() == true)
        {
            prime = "Prime Customer";
        }
        else
        {
            prime = "Not Prime Customer";
        }
        
        String[] array = new String[4];
        array[0] = "Account Number: " + info.getNewAcctNum();
        array[1] = "Company Name: " + info.getCompanyName();
        array[2] = "Prime Status" + prime;
        array[3] = "Account Successfully Added/Updated";
        
        return array;
    }
    public static String[] getAccount(String acct_num)
    {
        String[] array = new String[1];
        array[0] = accts.getAccount(acct_num).toString();
        
        return array;
    }
    public static String[] getAllAccounts()
    {
        int numAccounts = 0;
        int find = 0;
        String[] allAccounts;
        
        accts.reset();
        while(accts.hasNext())
        {
            numAccounts ++;
        }
        
        allAccounts = new String[numAccounts];
        
        accts.reset();
        while(accts.hasNext())
        {
            allAccounts[find] = accts.getNext().toString();
            
            find++;
        }
        
        return allAccounts;
    }
    
    // transactions-related methods  
    public static String[] storeTransaction(Transaction trans_info) 
    {
        trans.add(trans_info);
        
        String[] array = new String[2];
        array[0] = trans.getTransaction(trans_info.getAcctNum()).toString();
        array[1] = "Transaction successfully stored";
        
        return array;
    }
    public static String[] getAccountTransactions(String acct_num) 
    {
        String[] array = new String[1];
        array[0] = trans.getTransaction(acct_num).toString();
        
        return array;
    }
}
