import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AccountWriterReader 
{	
	public ArrayList<Account> Read()
	{
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		try
		{	
			FileInputStream fi = new FileInputStream(new File("Accounts.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			//Read accounts from file
			int size = oi.readInt();
			
			for (int i = 0; i < size; i++)
			{
				Account a = (Account) oi.readObject();
				accounts.add(a);
			}
			
			oi.close();
			fi.close();
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		catch(IOException e)
		{
			//System.out.println("Error Initialising Stream");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return accounts;
	}
	
	public ArrayList<Account> Write(ArrayList<Account> accounts)
	{
		try
		{
			FileOutputStream f = new FileOutputStream(new File("Accounts.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			//Write accounts to file
			o.writeInt(accounts.size());
			
			for (Account account: accounts)
			{
				o.writeObject(account);
			}
			
			o.close();
			f.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		catch(IOException e)
		{
			//System.out.println("Error Initialising Stream");
		}
		return accounts;
	}
}
