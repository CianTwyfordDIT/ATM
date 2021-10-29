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
				cls();
				displayLogIn();
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
				System.exit(0);
			
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
		
		scanner.close();
	}
	
	public static void displayLogIn()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("*******************\n");
		System.out.print("Bank Of Ireland\n");
		System.out.print("*******************\n\n");
		System.out.println("Log In");
	}
	
	public static void displayCreateAccount()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("*******************\n");
		System.out.print("Bank Of Ireland\n");
		System.out.print("*******************\n\n");
		System.out.println("Create Account");
	}
	
	public static void displayAccounts(ArrayList<Account> accounts)
	{
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
}
