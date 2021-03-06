import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Account implements Serializable
{
	static DecimalFormat formatter = new DecimalFormat("#,##0.00");
	private static final long serialVersionUID = 1L;
	private int accountID;
	private String custName;
	private int PIN;
	private double balance = 0;
	private ArrayList<String> history = new ArrayList<String>();
	
	Account(String custName, int PIN, int accountID)
	{
		this.custName = custName;
		this.PIN = PIN;
		this.balance = 0.00;
		this.accountID = accountID;
		this.setHistory(history);
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
	
	public void setHistory(ArrayList<String> history)
	{
		this.history = history;
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
	
	public ArrayList<String> getHistory()
	{
		return this.history;
	}
	
	public String toString()
	{
		return "\033[33mAccount No. " + accountID + "\033[37m\nCustomer Name: "
				+ custName + "\nPIN: " + PIN + "\nBalance: $" + formatter.format(balance);
	}
	
}
