import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) 
	{
		Account[] accounts = new Account[50];
		
		Account account1 = new Account("Cian Twyford", 1234, 2000.00);
		accounts[accounts.length-(accounts.length)] = account1;
		
		displayAccounts(accounts);
		
		//displayLogIn(accounts);
	}
	
	public static void displayLogIn(Account[] accounts)
	{
		int response;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("*******************\n");
		System.out.print("Bank Of Ireland\n");
		System.out.print("*******************\n\n");
		System.out.println("1) Log In");
		System.out.println("2) Create Account");
		System.out.print("\nPlease Select An Option: ");
		
		response = scanner.nextInt();
		
		scanner.close();
	}
	
	public static void displayAccounts(Account[] accounts)
	{
		for(int i = 0; i < accounts.length; i++)
		{
			if(accounts[i] == null)
			{
				continue;
			}
			System.out.println(accounts[i]);
		}
		
	}
}
