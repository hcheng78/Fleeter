package agencyrentalprogram;

import java.util.*;

public class AgencyRentalProgram 
{ 
    public static void main(String[] args) 
    { 
        // Create (and Populate) Agency Data  
        Rates agency_rates = assignRates(); // supporting private static method 
 
        Vehicles agency_vehicles = new Vehicles();  
        populate(agency_vehicles); // supporting private static method 
        
        Accounts accounts = new Accounts();  
        Transactions transactions = new Transactions(); 
        
        // Establish User Interface  
        Scanner input = new Scanner(System.in);  
        UserInterface ui;  
        boolean quit = false; 
        
        // Create Requested UI and Execute Program  
        while(!quit) 
        {   
            ui = getUI(input);
            
            if(ui == null)    
            {
                quit = true;
            }   
            else 
            {    
                // Init System Interface with Agency Data (if not already initialized)    
                if(initialized(agency_rates, agency_vehicles) == true)          
                {
                    SystemInterface.initSystem(agency_rates, agency_vehicles, accounts, transactions);
                } 
                
                // Start User Interface    
                ui.start();   
            }  
        } 
    } 
 
    public static UserInterface getUI(Scanner input) 
    {  
        UserInterface ui = null;
        boolean valid_selection = false; 
        
        while(!valid_selection) 
        {   
            System.out.print("1 – Employee, 2 – Manager, 3 – quit: "); 
            int selection = input.nextInt(); 
            
            if(selection == 1) 
            {    
                ui = new EmployeeUI();    
                valid_selection = true;   
            }   
            else if(selection == 2) 
            {    
                ui = new ManagerUI();    
                valid_selection = true;   
            }   
            else if(selection == 3) 
            {    
                ui = null;    
                valid_selection = true;   
            }   
            else    
            {
                System.out.println("Invalid selection – please reenter");  
            }
        }  
        return ui; 
    } 
    private static Rates assignRates() 
    {
        CarRates carR = new CarRates(24.95, 149.95, 514.95, 0.15, 14.95);
        SuvRates suvR = new SuvRates(29.95, 189.95, 679.95, 0.15, 14.95);
        TruckRates truckR = new TruckRates(34.95, 224.95, 797.95, 0.26, 22.95);

        return new Rates(carR, suvR, truckR);
    }
    private static void populate(Vehicles vehs)
    {
        vehs.add(new Car("Chevrolet Camaro", 30, "KH4GM4564GD", "2"));
        vehs.add(new Car("Ford Fusion", 34, "AB4FG5689GM", "4"));
        vehs.add(new Car("Ford Fusion Hybrid", 32, "KU4EG3245RW", "4"));
        vehs.add(new Car("Chevrolet Impala", 30, "RK3BM4256YH", "4"));

        vehs.add(new Suv("Honda Odyssey", 28, "VM9RE2645TD", "7", "6"));
        vehs.add(new Suv("Dodge Caravan", 25, "QK3FL4273ME", "5", "4"));
        vehs.add(new Suv("Ford Expedition", 20, "JK2RT9364HY", "5", "3"));

        vehs.add(new Truck("Ten-Foot", 12, "EJ5KU2435BC", "2810"));
        vehs.add(new Truck("Seventeen-Foot", 10, "KG4DM5472RK", "5930"));
        vehs.add(new Truck("Twenty-Four-Foot", 8, "EB2WR3082QB", "6500"));
        vehs.add(new Truck("Twenty-Four-Foot", 8, "TY3GH4290EK", "6500"));
    }
    private static boolean initialized(Rates r, Vehicles v)
    {
        return r != null || v != null;
    }
} 