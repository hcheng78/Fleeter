package agencyrentalprogram;

import java.util.*;

public class ManagerUI implements UserInterface 
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
             
            if(selection == 7) // quit program
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
            // View / Update Rates
            case 1: int rateOpt = getRatesOption(input);
            {
                switch(rateOpt)
                {
                    // display rental rates   
                    case 1: veh_type = getVehicleType(input);
                    {
                        switch (veh_type) 
                        {
                            case 1: display_lines = SystemInterface.getCarRates(); break;
                            case 2: display_lines = SystemInterface.getSuvRates(); break;
                            case 3: display_lines = SystemInterface.getTruckRates(); break;
                        }
                        displayResults(display_lines);
                        break;
                    }
                    // update rental rates
                    case 2: veh_type = getVehicleType(input);
                    {
                        RatesDetails rDetails = getNewRates(input);
                        switch (veh_type) 
                        {
                            case 1: display_lines = SystemInterface.updateCarRates(rDetails); break;
                            case 2: display_lines = SystemInterface.updateSuvRates(rDetails); break;
                            case 3: display_lines = SystemInterface.updateTruckRates(rDetails); break;
                        }
                        displayResults(display_lines);
                        break;
                    }
                }
            }
            
            // display all vehicles   
            case 2: display_lines = SystemInterface.getAllVehicles();
            {
                displayResults(display_lines); 
                break; 
            }
            
            // Add / Update Account  
            case 3: int acctOpt = getAcctOption(input); 
            {
                String acctNum = getAcctNumber(input);
                AccountDetails acctInfo = getAcctDetails(input);
                switch(acctOpt)
                {
                    case 1: display_lines = SystemInterface.addAccount(acctInfo); break;
                    case 2: display_lines = SystemInterface.updateAccount(acctNum, acctInfo); break;
                }
                displayResults(display_lines); 
                break; 
            }
            
            // display all reservations
            case 4: display_lines = SystemInterface.getAllReservations();  
            {
                displayResults(display_lines); 
                break;      
            }
            
            // display all accounts  
            case 5: display_lines = SystemInterface.getAllAccounts();
            {
                displayResults(display_lines); 
                break; 
            }
  
            // view transactions  
            case 6: acct_num = getAcctNumber(input);     
            {
                display_lines = SystemInterface.getAccountTransactions(acct_num);
                
                displayResults(display_lines);     
                break; 
            }
        } 
    }
    
    // ------- private methods 
    private void displayMenu()
    {
        System.out.println("1. – View/Update Rates ");
        System.out.println("2. – View All Vehicles ");
        System.out.println("3. – Add/Update Account ");
        System.out.println("4. – View All Reservations ");
        System.out.println("5. – View All Accounts ");
        System.out.println("6. – View Transactions ");
        System.out.println("7. - Quit ");
    }
    private int getSelection(Scanner input)
    {
        return input.nextInt();
    }
    private int getRatesOption(Scanner input)
    {
        System.out.println("1. View Rates ");
        System.out.println("2. Update Rates ");
        
        return input.nextInt();
    }
    private RatesDetails getNewRates(Scanner input)
    {
        double daily;
        double weekly;
        double monthly;
        double mileage;
        double insur;
        
        System.out.println("Enter new daily rate: ");
        daily = input.nextDouble();
        System.out.println("Enter new weekly rate: ");
        weekly = input.nextDouble();
        System.out.println("Enter new monthly rate: ");
        monthly = input.nextDouble();
        System.out.println("Enter new mileage rate: ");
        mileage = input.nextDouble();
        System.out.println("Enter new insurance rate: ");
        insur = input.nextDouble();
        
        return new RatesDetails(daily, weekly, monthly, mileage, insur);
    }
    private int getAcctOption(Scanner input)
    {
        System.out.println("1. Add Account ");
        System.out.println("2. Update Account ");
        
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
    private AccountDetails getAcctDetails(Scanner input)
    {
        String acctNum;
        String companyName;
        String primeCustomer;
        boolean prime = false;
        
        System.out.println("(New)");
        acctNum = getAcctNumber(input);
        System.out.println("Enter company name: ");
        companyName = input.nextLine();
        System.out.println("Prime Customer (Y/N): ");
        primeCustomer = input.nextLine();
        
        if (primeCustomer.toUpperCase().equals("Y")) 
        {
            prime = true;
        }
        
        return new AccountDetails(acctNum, companyName, prime);
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
    

 