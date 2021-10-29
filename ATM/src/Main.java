import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{	
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		Account account1 = new Account("Cian Twyford", 1234, 2000.0);
		accounts.add(account1);
		
		Account account2 = new Account("Gary Twyford", 4321, 4000.0);
		accounts.add(account2);
		
		displayOpeningScreen(accounts);
	}
	
	public static void displayOpeningScreen(ArrayList<Account> accounts)
	{
		int response;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("*******************\n");
		System.out.print("Bank Of Ireland\n");
		System.out.print("*******************\n\n");
		System.out.println("1) Log In");
		System.out.println("2) Create Account");
		System.out.println("3) Admin Options");
		System.out.println("4) Exit");
		System.out.print("\nPlease Select An Option: ");
		
		try
		{
			response = scanner.nextInt();
			
			switch(response)
			{
			case 1:
				logIn(accounts);
				break;
				
			case 2:
				cls();
				displayCreateAccount();
				break;
				
			case 3:
				cls();
				displayAccounts(accounts);
				break;
			
			case 4:
				cls();
				exit();
			
			default:
				cls();
				displayOpeningScreen(accounts);
			}
		}
		catch(InputMismatchException e)
		{
			cls();
			displayOpeningScreen(accounts);
		}
		finally
		{
			scanner.close();
		}

	}
	
	public static void logIn(ArrayList<Account> accounts)
	{
		cls();
		int response;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("*******************\n");
		System.out.print("Bank Of Ireland\n");
		System.out.print("*******************\n\n");
		System.out.println("Log In (Enter 0 to exit)\n");
		System.out.print("Enter Account Number: ");
		
		try
		{
			response = scanner.nextInt();
			
			if(response == 0)
			{
				cls();
				exit();
			}
			
			//Check if accountID exists
			for(int i = 0; i < accounts.size(); i++)
			{
				if(accounts.get(i).getAccountID() == response)
				{
					int PIN = accounts.get(i).getPIN();
					
					System.out.println("Enter PIN: ");
					
					int PINresponse = scanner.nextInt();
					
					if(PINresponse == PIN)
					{
						cls();
						System.out.println("Login Successful");
					}
					else
					{
						cls();
						System.out.println("PIN Incorrect");
					}
					
					loading();
					mainOptions();
				}
			}
		}
		catch(InputMismatchException e)
		{
			cls();
			logIn(accounts);
		}
		finally
		{
			scanner.close();
		}
	}
	
	public static void displayCreateAccount()
	{
		cls();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("*******************\n");
		System.out.print("Bank Of Ireland\n");
		System.out.print("*******************\n\n");
		System.out.println("Create Account");
		
		scanner.close();
	}
	
	public static void mainOptions()
	{
		cls();
		System.out.println("Hi");
	}
	
	public static void displayAccounts(ArrayList<Account> accounts)
	{
		cls();
		
		for(int i = 0; i < accounts.size(); i++)
		{
			if(accounts.get(i) == null)
			{
				continue;
			}
			System.out.println(accounts.get(i));
			System.out.println();
		}
		System.out.println("\nNumber of Accounts: " + Account.numAccounts);
		
	}
	
	public static void cls()
	{
		try
		{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void loading()
	{
		System.out.println("Loading...");
		try 
		{
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void exit()
	{
		System.exit(0);
	}
}
