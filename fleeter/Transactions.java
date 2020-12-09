package agencyrentalprogram;

public class Transactions 
{
    //instance variable 
    private Transaction[] trans; // array of Transaction objects 
    private int current; // array iterator
    
    //constructor
    public Transactions()
    {
        trans = new Transaction[25];
        
        current = 0;
    }
    
    // setter methods
    public void add(Transaction t) // add transaction object
    {
        trans[current] = t;
        
        current++;
    }
    public void remove(String acct) // remove selected transaction object
    {
        for(int index = 0; index < current; index ++)
        {
            if(trans[index] != null && trans[index].getAcctNum().equals(acct))
            {
                for(int shift = index; shift < current - 1; shift ++)
                {
                    trans[shift] = trans[shift + 1];
                }
            }
        }
        
        trans[current - 1] = null; // sets last slot to null
        current --; // adjust array size
    }
    public Transaction getTransaction(String acct) throws TransactionNotFoundException // if no transaction found for provided VIN 
    {
        int index = 0;
        boolean found = false;
        
        while(index < trans.length && !found)
        {
            if(trans[index] != null && trans[index].getAcctNum().equals(acct))
            {
                found = true;
            }
            index ++;
        }
        
        // VIN not found if reach end of array
        if(!found)
        {
            throw new TransactionNotFoundException();
        }
        
        // return transaction found
        return trans[index - 1];
    }
    
    //iterator methods 
    public void reset() // resets to first transaction in list  
    {
        current = 0;
    }
    public boolean hasNext() // returns true if more transactions in list to retrieve  
    {
        return current < trans.length && trans[current ++] != null;
    }
    public Transaction getNext() // returns next transaction in list  
    {
        return trans[current - 1];
    }    
}
