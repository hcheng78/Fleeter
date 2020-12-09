package agencyrentalprogram;

public class Vehicles 
{
    //instance variable 
    private Vehicle[] vehs; // array of Vehicle objects 
    private int current; // array iterator
    
    //constructor
    public Vehicles()
    {
        vehs = new Vehicle[25];
        
        current = 0;
    }
    
    // setter methods
    public void add(Vehicle v) // add vehicle object
    {
        vehs[current] = v;
        
        current ++;
    }
    public void remove(String vin) // remove selected vehicle object
    {
        for(int index = 0; index < current; index ++)
        {
            if(vehs[index] != null && vehs[index].getVin().equals(vin))
            {
                for(int shift = index; shift < current - 1; shift ++)
                {
                    vehs[shift] = vehs[shift + 1];
                }
            }
        }
        
        vehs[current - 1] = null; // sets last slot to null
        current --; // adjust array size
    }
    public Vehicle getVehicle(String vin) throws VINNotFoundException // if no vehicle found for provided VIN 
    {
        int index = 0;
        boolean found = false;
        
        while(index < vehs.length && !found)
        {
            if(vehs[index] != null && vehs[index].getVin().equals(vin))
            {
                found = true;
            }
            index ++;
        }
        
        // VIN not found if reach end of array
        if(!found)
        {
            throw new VINNotFoundException();
        }
        
        // return vehicle found
        return vehs[index - 1];
    }
    
    //iterator methods 
    public void reset() // resets to first vehicle in list  
    {
        current = 0;
    }
    public boolean hasNext() // returns true if more vehicles in list to retrieve  
    {
        return current < vehs.length && vehs[current ++] != null;
    }
    public Vehicle getNext() // returns next vehicle in list  
    { 
        return vehs[current - 1];
    }
}
