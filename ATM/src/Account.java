import java.io.Serializable;
import java.text.DecimalFormat;

public class Account implements Serializable
{
	static DecimalFormat formatter = new DecimalFormat("#,###.00");
	private static final long serialVersionUID = 1L;
	protected static int numAccounts;
	private int accountID;
	private String custName;
	private int PIN;
	private double balance = 0;
	
	Account(String custName, int PIN, double balance, int accountID)
	{
		this.setCustName(custName);
		this.setPIN(PIN);
		this.setBalance(balance);
		this.setAccountID(accountID);
	}
	
	public void setAccountID(int accountID)
	{
		this.accountID = accountID;;
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
	
	public int getPIN()
	{
		return this.PIN;
	}
	
	public double getBalance()
	{
		return this.balance;
	}
	
	public String toString()
	{
		return "\033[33mAccount No. " + accountID + "\033[37m\nCustomer Name: "
				+ custName + "\nPIN: " + PIN + "\nBalance: $" + formatter.format(balance);
	}
	
}
