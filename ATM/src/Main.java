import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main 
{
	static AccountWriterReader awr = new AccountWriterReader();
	
	public static void main(String[] args) 
	{	
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		displayOpeningScreen(accounts);
	}
	
	public static void displayOpeningScreen(ArrayList<Account> accounts)
	{
		cls();
		
		int response;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("*******************\n");
		System.out.print("\033[34m");
		System.out.print("Bank Of Ireland\n");
		System.out.print("\033[37m");
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
				displayCreateAccount(accounts);
				break;
				
			case 3:
				cls();
				displayAccounts(accounts);
				break;
			
			case 4:
				cls();
				exit();
			
			default:
				System.out.print("\033[31mInvalid Selection");
				System.out.println("\033[37m");
				try 
				{
					Thread.sleep(3000);
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cls();
				displayOpeningScreen(accounts);
			}
		}
		catch(InputMismatchException e)
		{
			System.out.print("\033[31m");
			System.out.print("Invalid Selection");
			System.out.println("\033[37m");
			try 
			{
				Thread.sleep(3000);
			} 
			catch (InterruptedException ee) 
			{
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			cls();
			displayOpeningScreen(accounts);
		}
		finally
		{
			scanner.close();
		}

	}
	
	//Screen to log in 
	public static void logIn(ArrayList<Account> accounts)
	{
		cls();
		
		int response;
		boolean checkResponse = false;
		Scanner scanner = new Scanner(System.in);
		accounts = awr.Read();
		
		System.out.print("*******************\n");
		System.out.print("\033[34m");
		System.out.print("Bank Of Ireland\n");
		System.out.print("\033[37m");
		System.out.print("*******************\n\n");
		System.out.println("Log In (Enter 0 to return to main menu)\n");
		System.out.print("Enter Account Number: ");
		
		try
		{
			response = scanner.nextInt();
			
			if(response == 0)
			{
				displayOpeningScreen(accounts);
			}
			
			//Iterate over arraylist
			for(int i = 0; i < accounts.size(); i++)
			{
				//Check if accountID exists in arraylist
				if(accounts.get(i).getAccountID() == response)
				{
					checkResponse = true;
					break;
				}
				else
				{
					continue;
				}
			}
			
			//If accountID exists in arraylist
			if(checkResponse)
			{
				//Get PIN for account
				int PIN = accounts.get(response-1).getPIN();
				
				System.out.print("Enter PIN: ");
				try
				{
					
					int PINresponse = scanner.nextInt();
					
					//Check if PIN matches user input
					if(PINresponse == PIN)
					{
						cls();
						System.out.print("\033[32m");
						System.out.println("Login Successful");
						System.out.print("\033[37m");
						loading();
						mainOptions(accounts);
					}
					else
					{
						System.out.print("\033[31m");
						System.out.println("PIN Incorrect");
						System.out.print("\033[37m");
						try 
						{
							Thread.sleep(3000);
						} 
						catch (InterruptedException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						logIn(accounts);
					}
				}
				catch(InputMismatchException e)
				{
					System.out.print("\033[31m");
					System.out.println("Invalid Input");
					System.out.print("\033[37m");

					System.out.println("\nPress Enter to Continue:");
					scanner.nextLine();
					scanner.nextLine();
					
					logIn(accounts);
				}
			}
			else
			{
				System.out.print("\033[31m");
				System.out.println("Account No. " + response + " Doesn't Exist");
				System.out.print("\033[37m");
				try 
				{
					Thread.sleep(3000);
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logIn(accounts);
			}
			
		}
		catch(InputMismatchException e)
		{
			System.out.print("\033[31m");
			System.out.println("Invalid Input");
			System.out.print("\033[37m");
			
			System.out.println("\nPress Enter to Continue:");
			scanner.nextLine();
			scanner.nextLine();

			logIn(accounts);
		}
		finally
		{
			scanner.close();
		}
	}
	
	public static void displayCreateAccount(ArrayList<Account> accounts)
	{
		boolean confirmation = false;
		String custName;
		int PIN;
		double balance;
			
		do
		{	
			cls();
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("*******************\n");
			System.out.print("\033[34m");
			System.out.print("Bank Of Ireland\n");
			System.out.print("\033[37m");
			System.out.print("*******************\n\n");
			System.out.println("Create Account");
			
			try
			{	
				System.out.print("\nEnter Customer Name: ");
				custName = scanner.nextLine();
				
				System.out.print("Create PIN: ");	
				PIN = scanner.nextInt();
				
				System.out.print("Enter $ Balance: ");	
				balance = scanner.nextDouble();
				scanner.nextLine();
				
				cls();
				loading();
				
				String response;
				
				do
				{
					cls();
					
					System.out.print("\033[33m");
					System.out.println("Confirm Details");
					System.out.print("\033[37m");
					System.out.println("\nCustomer Name: " + custName);
					System.out.println("PIN: " + PIN);
					System.out.println("Balance: $" + balance);
					System.out.print("\033[33m");
					System.out.print("Y/N (Enter 0 to return to main menu): ");
					System.out.print("\033[37m");
					
					response = scanner.nextLine();
					
					if(response.equals("Y") || response.equals("y"))
					{
						confirmation = true;
						int accountID = accounts.size()+1;
						accounts.add(new Account(custName, PIN, balance, accountID));
						accounts = awr.Write(accounts);
						cls();
						System.out.print("\033[32m");
						System.out.println("New Account Created");
						System.out.print("\033[36m");
						System.out.println("\nUser Login Details:");
						System.out.println("\033[33mAccount Number: \033[37m" + accounts.get(accounts.size()-1).getAccountID());
						System.out.println("\033[33mPIN: \033[37m" + accounts.get(accounts.size()-1).getPIN());
						
						System.out.println("\nPress Enter to Continue:");
						scanner.nextLine();
						
						displayOpeningScreen(accounts);
						
					}
					else if(response.equals("N") || response.equals("n"))
					{
						confirmation = false;
						cls();
						loading();
					}
					else if(response.equals("0"))
					{
						displayOpeningScreen(accounts);
					}
					else
					{
						confirmation = false;
						System.out.print("\033[31m");
						System.out.println("Invalid Input");
						System.out.print("\033[37m");
	
						System.out.println("\nPress Enter to Continue:");
						scanner.nextLine();
					}
				} while(!response.equals("Y") && !response.equals("y") && !response.equals("N") && !response.equals("n") && !response.equals("0"));
			}
			catch(InputMismatchException e)
			{
				System.out.print("\033[31m");
				System.out.println("Invalid Input");
				System.out.print("\033[37m");
				
				System.out.println("\nPress Enter to Continue:");
				scanner.nextLine();
				scanner.nextLine();
				
				displayCreateAccount(accounts);
			}
		} while(confirmation == false);
	}
	
	public static void mainOptions(ArrayList<Account> accounts)
	{
		cls();
		Scanner scanner = new Scanner(System.in);
		int response;
		
		System.out.print("*******************\n");
		System.out.print("\033[34m");
		System.out.print("Bank Of Ireland\n");
		System.out.print("\033[37m");
		System.out.print("*******************\n\n");
		System.out.println("1) Check Balance & History");
		System.out.println("2) Withdraw Cash");
		System.out.println("3) Deposit Cash");
		System.out.println("4) Change PIN");
		System.out.println("5) Delete Account");
		System.out.println("6) Exit");
		System.out.print("\nPlease Select An Option: ");	
		
		try
		{
			response = scanner.nextInt();
			
			switch(response)
			{
			case 1:
				break;
				
			case 2:
				break;
				
			case 3:
				break;
			
			case 4:
				break;
				
			case 5:
				break;
				
			case 6:
				cls();
				loading();
				displayOpeningScreen(accounts);
			
			default:
				System.out.print("\033[31mInvalid Selection");
				System.out.println("\033[37m");
				try 
				{
					Thread.sleep(3000);
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cls();
				mainOptions(accounts);
			}
		}
		catch(InputMismatchException e)
		{
			System.out.print("\033[31m");
			System.out.print("Invalid Selection");
			System.out.println("\033[37m");
			try 
			{
				Thread.sleep(3000);
			} 
			catch (InterruptedException ee) 
			{
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			cls();
			mainOptions(accounts);
		}
		finally
		{
			scanner.close();
		}
	}
	
	public static void displayAccounts(ArrayList<Account> accounts)
	{
		cls();
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		accounts = awr.Read();
		
		for(int i = 0; i < accounts.size(); i++)
		{
			if(accounts.get(i) == null)
			{
				continue;
			}
			System.out.println(accounts.get(i));
			System.out.println();
		}
		
		
		System.out.println("\nNumber of Accounts: " + accounts.size());
		
		System.out.println("\nPress Enter to Continue:");
		scanner.nextLine();
		
		displayOpeningScreen(accounts);
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
		System.out.print("Loading...");
		try 
		{
			Thread.sleep(3000);
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
