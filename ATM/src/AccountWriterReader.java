import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AccountWriterReader 
{
	public static void main(String[] args)
	{
		Account account1 = new Account("Cian Twyford", 1234, 2000.0);
		//accounts.add(account1);
		
		try
		{
			FileOutputStream f = new FileOutputStream(new File("Accounts.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			//Write objects to file
			o.writeObject(account1);
			
			o.close();
			f.close();
			
			FileInputStream fi = new FileInputStream(new File("Accounts.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Account acc1 = (Account) oi.readObject();
			
			System.out.println(acc1.toString());
			
			oi.close();
			fi.close();
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		catch(IOException e)
		{
			System.out.println("Error Initialising Stream");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
