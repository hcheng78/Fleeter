package agencyrentalprogram;

import java.util.*;

public class EmployeeUI implements UserInterface 
{    
    // no constructor needed 
    // starts a “command loop” that repeatedly: 
    // (a) displays a menu of options 
    // (b) gets the selected command from the user
    // (c) executes the command
    public void start() 
    { 
        int selection; 
        Scanner input = new Scanner(System.in);  
        boolean quit = false; 
    
        // command loop  
        while(!quit) 
        {   
            displayMenu();
            selection = getSelection(input);   
            execute(selection, input);  
             
            if(selection == 8) // quit program
            {
                quit = true;
            }
        }       
    }         
    private void execute(int selection, Scanner input) 
    { 
        int veh_type;  
        String[] display_lines = null; 
        String acct_num;
        String vin;
    
        switch(selection) 
        { 
            // display rental rates   
            case 1: veh_type = getVehicleType(input);     
            {
                switch(veh_type)
                {         
                    case 1: display_lines = SystemInterface.getCarRates(); break;         
                    case 2: display_lines = SystemInterface.getSuvRates(); break;               
                    case 3: display_lines = SystemInterface.getTruckRates(); break;     
                }     
                displayResults(display_lines); 
                break; 
            }
            
            // display available vehicles   
            case 2: veh_type = getVehicleType(input);  
            {
                switch(veh_type)
                {         
                    case 1: display_lines = SystemInterface.getAvailCars(); break;         
                    case 2: display_lines = SystemInterface.getAvailSuvs(); break;               
                    case 3: display_lines = SystemInterface.getAvailTrucks(); break;     
                }     
                displayResults(display_lines); 
                break; 
            }
            
            // display estimated rental cost   
            case 3: RentalDetails rental_details = getRentalDetails(input);     
            {
                display_lines = SystemInterface.estimatedRentalCost(rental_details);
                
                displayResults(display_lines); 
                break; 
            }
            
            // make a reservation   
            case 4: ReservationDetails reserv_details = getReservationDetails(input);     
            {
                display_lines = SystemInterface.makeReservation(reserv_details);
                
                displayResults(display_lines); 
                break;      
            }
            
            // cancel a reservation   
            case 5: acct_num = getAcctNumber(input);     
            {
                vin = getVIN(input);
                display_lines = SystemInterface.cancelReservation(vin);     
                
                displayResults(display_lines); 
                break; 
            }
  
            // view corporate account (and company reservations)   
            case 6: acct_num = getAcctNumber(input);     
            {
                display_lines = SystemInterface.getAccount(acct_num);
                
                displayResults(display_lines);     
                break; 
            }
   
            // process returned vehicle   
            case 7: ReturnedVehicleDetails return_details = getReturnedVehicleDetails(input);    
            {
                vin = getVIN(input);
                display_lines = SystemInterface.processReturnedVehicle(vin, return_details);     
                
                displayResults(display_lines);     
                break; 
            }
        } 
    }
    
    // ------- private methods 
    private void displayMenu()
    {
        System.out.println("1. – View Current Rates ");
        System.out.println("2. – View Available Vehicles ");
        System.out.println("3. – Calculate Estimated Rental Cost ");
        System.out.println("4. – Make a Reservation ");
        System.out.println("5. - Cancel Reservation ");
        System.out.println("6. – View Corporate Account ");
        System.out.println("7. – Process Returned Vehicle ");
        System.out.println("8. - Quit ");
    }
    private int getSelection(Scanner input)
    {
        return input.nextInt();
    }
    private String getAcctNumber(Scanner input) // generates and returns 5 digit account number (continues to prompt user if invalid input read) 
    {
        String acct = null;
        int acctNum = -1;
        boolean validInput = false;

        while (!validInput) 
        {
            try 
            {
                System.out.println("Enter Account Number: ");
                acctNum = input.nextInt();
                acct = Integer.toString(acctNum);

                if (acct.length() == 5) 
                {
                    validInput = true;
                } 
                else 
                {
                    System.out.println("Error: Reenter: ");
                }
            } 
            catch (InputMismatchException ex) 
            {
                input.next();
            }
        }
        
        return acct;
    }
    private String getVIN(Scanner input) // prompts user to enter VIN for a given vehicle (does not do any error checking on the input) 
    {
        System.out.println("Enter VIN for vehicle: ");
        
        return input.nextLine();
    }
    private int getVehicleType(Scanner input) // prompts user to enter 1, 2, or 3, and returns (continues to prompt user if invalid input read) 
    {
        int vehicle = -1;
        boolean validInput = false;

        while (!validInput) 
        {
            try 
            {
                System.out.println("Enter Vehicle Type: ");
                System.out.println("1. Car ");
                System.out.println("2. Suv ");
                System.out.println("3. Truck ");
                vehicle = input.nextInt();

                if (vehicle <= 3 && vehicle >= 1) 
                {
                    validInput = true;
                } 
                else 
                {
                    System.out.println("Error: Reenter: ");
                    input.nextInt();
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.out.println("Error: Reenter: ");
                input.next();
            }
        }
        
        return vehicle;
    }
    private RentalDetails getRentalDetails(Scanner input)  
    // prompts user to enter required information for an estimated rental cost 
    // (vehicle type, estimated number of miles expected to be driven, rental period (number of days, weeks or months) insurance option
    // returning the result packaged in a RentalDetails object (to be passed in method calls to the SystemInterface) 
    {
        int vehType;
        int estMiles;
        String rentalPeriod;
        String insur;
        String primeCust;
        boolean insurChoice = false;
        boolean prime = false;

        vehType = getVehicleType(input);
        System.out.println("Enter Estimated Miles: ");
        estMiles = input.nextInt();
        System.out.println("Enter Rental Period: ");
        rentalPeriod = input.nextLine();
        System.out.println("Insurance (Y/N): ");
        insur = input.nextLine();
        System.out.println("Prime Customer (Y/N): ");
        primeCust = input.nextLine();
        
        if (insur.toUpperCase().equals("Y")) 
        {
            insurChoice = true;
        }
        if (primeCust.toUpperCase().equals("Y")) 
        {
            prime = true;
        }

        return new RentalDetails(vehType, rentalPeriod, estMiles, insurChoice, prime);
    }
    private ReservationDetails getReservationDetails(Scanner input)  
    // prompts user to enter required information for making a reservation 
    // (VIN of vehicle to reserve, acct number, rental period, and insurance option)
    //returning the result packaged in a ReservationDetails object (to be passed in method calls to the SystemInterface) 
    {
        String vin;
        String acctNum;
        String rentalPeriod;
        String insur;
        boolean insurChoice = false;

        System.out.println("Enter VIN for vehicle: ");
        vin = input.nextLine();
        acctNum = getAcctNumber(input);
        System.out.println("Enter Rental Period: ");
        rentalPeriod = input.nextLine();
        System.out.println("Insurance (Y/N): ");
        insur = input.nextLine();
        
        if (insur.toUpperCase().equals("Y")) 
        {
            insurChoice = true;
        }

        return new ReservationDetails(vin, acctNum, rentalPeriod, insurChoice);
    }
    public ReturnedVehicleDetails getReturnedVehicleDetails(Scanner input)
    {
        int days;
        int miles;
        String dailyInsurance;
        String primeCustomer;
        boolean insur = false;
        boolean prime = false;
        
        System.out.println("Enter days rented: ");
        days = input.nextInt();
        System.out.println("Enter miles driven: ");
        miles = input.nextInt();
        System.out.println("Daily Insurance (Y/N): ");
        dailyInsurance = input.nextLine();
        System.out.println("Prime Customer (Y/N): ");
        primeCustomer = input.nextLine();
        
        if (dailyInsurance.toUpperCase().equals("Y")) 
        {
            insur = true;
        }
        if (primeCustomer.toUpperCase().equals("Y")) 
        {
            prime = true;
        }
        
        return new ReturnedVehicleDetails(days, miles, insur, prime);
    }
    private void displayResults(String[] lines) // displays the array of strings passed, one string per screen line 
    {
        for (int i = 0; i < lines.length; i++) 
        {
            System.out.println(lines[i]);
        }
    }
}
    

 