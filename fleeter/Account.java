package agencyrentalprogram;

public class Account 
{
    //instance variables 
    private String acct_num; // five-digit acct number 
    private String company_name; // company name
    private Vehicles reserved_vehs; // reserved vehicles
    private boolean prime_customer; // prime customer
    
    //constructor
    public Account(String acct_num, String company_name, Vehicles reserved_vehs, boolean prime_customer)
    {
        this.acct_num = acct_num;
        this.company_name = company_name;
        this.reserved_vehs = reserved_vehs;
        this.prime_customer = prime_customer;
    }
    
    // getter methods 
    public String getAcctNum() 
    {
        return acct_num;
    }
    public String getCompanyName()
    {
        return company_name;
    }
    public boolean primeCustomer()
    {
        return prime_customer;
    }
    
    // setter methods
    public void setAcctNum(String acctNum)
    {
        acct_num = acctNum;
    }
    public void setCompanyName(String companyName)
    {
        company_name = companyName;
    }
    public void setPrimeCustomer(boolean prime)
    {
        prime_customer = prime;
    }
    
    // String array of reserved vehicles
    public String[] getVehiclesReserved() 
    {
        int count = 0;
        int index = 0;
        String[] array;
        
        reserved_vehs.reset();
        while(reserved_vehs.hasNext())
        {
            if(reserved_vehs.getNext().getReservation().getAcctNum().equals(getAcctNum()))
            {
                count ++;
            }
        }
        
        array = new String[count];
        
        reserved_vehs.reset();
        while(reserved_vehs.hasNext())
        {
            if(reserved_vehs.getNext().getReservation().getAcctNum().equals(getAcctNum()))
            {
                array[index] = reserved_vehs.getNext().toString();
                index ++;
            }
        }
        
        return array;
    }
    
    // Format: Company A (acct# 12345) Prime Customer
    public String toString() 
    {
        String prime = "Not Prime Customer";
        
        // if true sets as prime customer
        if(primeCustomer() == true)
        {
            prime = "Prime Customer";
        }
        
        return getCompanyName() + " (acct# " + getAcctNum() + "), Prime Status: " + prime;
    }
}
