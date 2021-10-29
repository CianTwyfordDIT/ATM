
public class Account 
{
	static int numAccounts;
	private int accountID;
	private String custName;
	private int PIN;
	private double balance = 0;
	
	Account(String custName, int PIN, double balance)
	{
		this.setAccountID();
		this.setCustName(custName);
		this.setPIN(PIN);
		this.setBalance(balance);
		numAccounts++;
	}
	
	public void setAccountID()
	{
		this.accountID = numAccounts;
	}
	
	public void setCustName(String custName)
	{
		this.custName = custName;
	}
	
	public void setPIN(int PIN)
	{
		this.PIN = PIN;
	}
	
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	
	public int getAccountID()
	{
		return this.accountID;
	}
	
	public String getCustName()
	{
		return this.custName;
	}
	
	public String toString()
	{
		return "Account ID: " + accountID + "\nCustomer Name: "
				+ custName + "\nBalance: " + balance;
	}
	
}
