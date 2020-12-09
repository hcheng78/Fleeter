package agencyrentalprogram;

import java.util.*;

public class Accounts 
{
    // instance variable  
    private Account[] accts; // array of Account objects 
    private int current; // array iterator
    
    // constructor
    public Accounts()
    {
        accts = new Account[25];
        
        current = 0;
    }
    
    // setter methods
    public void add(Account a) // add transaction object
    {
        accts[current] = a;
        
        current++;
    }
    public void remove(String acct) // remove selected transaction object
    {
        for(int index = 0; index < current; index ++)
        {
            if(accts[index] != null && accts[index].getAcctNum().equals(acct))
            {
                for(int shift = index; shift < current - 1; shift ++)
                {
                    accts[shift] = accts[shift + 1];
                }
            }
        }
        
        accts[current - 1] = null; // sets last slot to null
        current --; // adjust array size
    }
    public Account getAccount(String acct) throws InvalidAcctNumException, AccountNumberNotFoundException // returns the account object with acct_num 
    {
        boolean validInput = false;
        int acctNum = 0;
        int index = 0;
        boolean found = false;
        
        // InvalidAcctNumException: if acct number contains non-digits, or is not 5 digits long
        while (validInput == false) 
        {
            try 
            {
                acctNum = Integer.parseInt(acct);

                if (acct.length() == 5) 
                {
                    validInput = true;
                } 
                else 
                {
                    throw new InvalidAcctNumException(); 
                }
            } 
            catch (InputMismatchException ex) 
            {
                throw new InvalidAcctNumException(); 
            }
        }
        
        // AccountNumberNotFoundException: if account number does not exist 
        while(index < accts.length && !found)
        {
            if(accts[index] != null && accts[index].getAcctNum().equals(acct))
            {
                found = true;
            }
            index ++;
        }
        
        if(!found)
        {
            throw new AccountNumberNotFoundException();
        }
        
        // return transaction found
        return accts[index - 1];
    }
    
    //iterator methods 
    public void reset() // resets to first account in list  
    {
        current = 0;
    }
    public boolean hasNext() // returns true if more accounts in list to retrieve  
    {
        return current < accts.length && accts[current ++] != null;
    }
    public Account getNext() // returns next account in list  
    {
        return accts[current - 1];
    }    
}
