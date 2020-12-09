package agencyrentalprogram;

public class AccountDetails 
{
    //instance variables 
    private String newAcct_num; // five-digit acct number 
    private String company_name; // company name
    private boolean prime_customer; // prime customer
    
    //constructor
    public AccountDetails(String newAcct_num, String company_name, boolean prime_customer)
    {
        this.newAcct_num = newAcct_num;
        this.company_name = company_name;
        this.prime_customer = prime_customer;
    }
    
    // getter methods 
    public String getNewAcctNum() 
    {
        return newAcct_num;
    }
    public String getCompanyName()
    {
        return company_name;
    }
    public boolean primeCustomer()
    {
        return prime_customer;
    }
}
