
public class Account 
{
	int accountID = 0;
	String custName;
	
	Account(String custName)
	{
		this.accountID = accountID++;
		this.custName = custName;
	}
}
