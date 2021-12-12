import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main 
{
	static AccountWriterReader awr = new AccountWriterReader();
	private static ArrayList<Account> accounts = new ArrayList<Account>();
	static DecimalFormat formatter = new DecimalFormat("#,###.00");
	
	public static void main(String[] args) 
	{	
		accounts = awr.Read();
		openingScreen();
	}
	
	public static void openingScreen()
	{
		cls();
		
		int response;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("  *******************\n");
		System.out.print("\033[34m");
		System.out.print("    Bank Of Ireland\n");
		System.out.print("\033[37m");
		System.out.print("  *******************\n\n");
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
				logIn();
				break;
				
			case 2:
				createAccount();
				break;
				
			case 3:
				displayAccounts();
				break;
			
			case 4:
				cls();
				exit();
			
			default:
				System.out.print("\033[31mInvalid Selection");
				System.out.println("\033[37m");
				
				try 
				{
					Thread.sleep(2000);
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				cls();
				openingScreen();
			}
		}
		catch(InputMismatchException e)
		{
			System.out.print("\033[31m");
			System.out.print("Invalid Selection");
			System.out.println("\033[37m");
			
			try 
			{
				Thread.sleep(2000);
			} 
			catch (InterruptedException ee) 
			{
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			
			cls();
			openingScreen();
		}
		finally
		{
			scanner.close();
		}

	}
	
	//Screen to log in 
	public static void logIn()
	{
		cls();
		
		int response;
		int PIN = 0;
		boolean checkResponse = false;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("  *******************\n");
		System.out.print("\033[34m");
		System.out.print("    Bank Of Ireland\n");
		System.out.print("\033[37m");
		System.out.print("  *******************\n\n");
		System.out.println("Log In (Enter 0 to return to main menu)\n");
		System.out.print("Enter Account Number: ");
		
		try
		{
			response = scanner.nextInt();
			
			if(response == 0)
			{
				openingScreen();
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
				//Iterate over arraylist
				for(int i = 0; i < accounts.size(); i++)
				{
					//Check if accountID exists in arraylist
					if(accounts.get(i).getAccountID() == response)
					{
						PIN = accounts.get(i).getPIN();
						break;
					}
					else
					{
						continue;
					}
				}
				
				System.out.print("Enter PIN: ");
				try
				{
					
					int PINresponse = scanner.nextInt();
					
					//Check if PIN matches user input
					if(PINresponse == PIN)
					{
						cls();
						System.out.print("\033[32m");
						System.out.println("Login Successful\n");
						System.out.print("\033[37m");
						loading();
						mainOptions(response);
					}
					else
					{
						System.out.print("\033[31m");
						System.out.println("PIN Incorrect");
						System.out.print("\033[37m");
						
						try 
						{
							Thread.sleep(2000);
						} 
						catch (InterruptedException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						logIn();
					}
				}
				catch(InputMismatchException e)
				{
					System.out.print("\033[31m");
					System.out.println("Invalid Input");
					System.out.print("\033[37m");

					try 
					{
						Thread.sleep(2000);
					} 
					catch (InterruptedException ee) 
					{
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}
					
					logIn();
				}
			}
			else
			{
				System.out.print("\033[31m");
				System.out.println("Account No. " + response + " Doesn't Exist");
				System.out.print("\033[37m");
				
				try 
				{
					Thread.sleep(2000);
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				logIn();
			}
			
		}
		catch(InputMismatchException e)
		{
			System.out.print("\033[31m");
			System.out.println("Invalid Input");
			System.out.print("\033[37m");
			
			try 
			{
				Thread.sleep(2000);
			} 
			catch (InterruptedException ee) 
			{
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}

			logIn();
		}
		finally
		{
			scanner.close();
		}
	}
	
	public static void createAccount()
	{
		boolean confirmation = false;
		String custName;
		int PIN;
		double balance;
		int accountID;
		String response;
			
		do
		{	
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			try
			{	
				do
				{
					cls();
					
					System.out.print("  *******************\n");
					System.out.print("\033[34m");
					System.out.print("    Bank Of Ireland\n");
					System.out.print("\033[37m");
					System.out.print("  *******************\n\n");
					System.out.println("Create Account");
					
					System.out.print("\nEnter Customer Name: ");
					custName = scanner.nextLine();
					
					System.out.print("Create PIN: ");	
					PIN = scanner.nextInt();
	
					System.out.print("Enter $ Balance: ");	
					balance = scanner.nextDouble();
					scanner.nextLine();
					
					if((int)(Math.log10(PIN) + 1) != 4)
					{
						System.out.print("\033[31m");
						System.out.println("Invalid PIN - Must be 4 digits");
						System.out.print("\033[37m");
	
						try 
						{
							Thread.sleep(2000);
						} 
						catch (InterruptedException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				while((int)(Math.log10(PIN) + 1) != 4);
					
				cls();
				loading();
				
				do
				{
					cls();
					
					System.out.print("\033[33m");
					System.out.println("Confirm Details");
					System.out.print("\033[37m");
					System.out.println("\nCustomer Name: " + custName);
					System.out.println("PIN: " + PIN);
					System.out.println("Balance: $" + formatter.format(balance));
					System.out.print("\033[33m");
					System.out.print("Y/N (Enter 0 to return to main menu): ");
					System.out.print("\033[37m");
					
					response = scanner.nextLine();
					
					switch(response)
					{
					case "Y":
					case "y":
						confirmation = true;
						
						//If arraylist is empty, assign first account to 1
						if(accounts.size() == 0)
						{
							accountID = 1;
						}
						else
						{
							//Assign accountID as the next number after last account in arraylist
							accountID = (accounts.get((accounts.size())-1).getAccountID()+1);
						}
						
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
						
						openingScreen();
						
					case "N":
					case "n":
						confirmation = false;
						cls();
						loading();
						createAccount();
	
					case "0":
						openingScreen();
	
					default:
						confirmation = false;
						System.out.print("\033[31m");
						System.out.println("Invalid Input");
						System.out.print("\033[37m");
	
						try 
						{
							Thread.sleep(2000);
						} 
						catch (InterruptedException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} while(!response.equals("Y") && !response.equals("y") && !response.equals("N") && !response.equals("n") && !response.equals("0"));

			}
			catch(InputMismatchException e)
			{
				System.out.print("\033[31m");
				System.out.println("Invalid Input");
				System.out.print("\033[37m");
				
				try 
				{
					Thread.sleep(2000);
				} 
				catch (InterruptedException ee) 
				{
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
				
				createAccount();
			}
		} while(confirmation == false);
	}
	
	public static void mainOptions(int accountNum)
	{
		cls();
		Scanner scanner = new Scanner(System.in);
		int response;
		int accountID = 0;
		String custName;
		
		for(int i = 0; i < accounts.size(); i++)
		{
			//Check if accountID exists in arraylist
			if(accounts.get(i).getAccountID() == accountNum)
			{
				accountID = i;
				break;
			}
			else
			{
				continue;
			}
		}
		
		custName = accounts.get(accountID).getCustName();
		
		System.out.print("  *******************\n");
		System.out.print("\033[34m");
		System.out.print("    Bank Of Ireland\n");
		System.out.print("\033[37m");
		System.out.print("  *******************\n\n");
		
		System.out.println("\033[32m" + custName + " logged in\033[37m");
		System.out.println();
		System.out.println("1) Check Balance & History");
		System.out.println("2) Withdraw Cash");
		System.out.println("3) Deposit Cash");
		System.out.println("4) Change PIN");
		System.out.println("5) Delete Account");
		System.out.println("6) Exit to Main Menu");
		System.out.print("\nPlease Select An Option: ");	
		
		try
		{
			response = scanner.nextInt();
			
			switch(response)
			{
			case 1:
				showBalance(accountNum, accountID);
				break;
				
			case 2:
				withdrawCash(accountNum);
				break;
				
			case 3:
				depositCash(accountNum);
				break;
			
			case 4:
				break;
				
			case 5:
				deleteAccount(accountNum);
				break;
				
			case 6:
				cls();
				loading();
				openingScreen();
			
			default:
				System.out.print("\033[31mInvalid Selection\033[37m");

				try 
				{
					Thread.sleep(2000);
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				cls();
				mainOptions(accountNum);
			}
		}
		catch(InputMismatchException e)
		{
			System.out.print("\033[31mInvalid Selection\033[37m");
			
			try 
			{
				Thread.sleep(2000);
			} 
			catch (InterruptedException ee) 
			{
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			
			cls();
			mainOptions(accountNum);
		}
		finally
		{
			scanner.close();
		}
	}
	
	public static void displayAccounts()
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
		
		
		System.out.println("\n\033[36mNumber of Accounts: \033[37m" + accounts.size());
		
		System.out.println("\nPress Enter to Continue:");
		scanner.nextLine();
		
		openingScreen();
	}
	
	public static void showBalance(int accountNum, int accountID)
	{
		cls();
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> history = new ArrayList<String>();
		history = accounts.get(accountID).getHistory();
		
		System.out.print("  *******************\n");
		System.out.print("\033[34m");
		System.out.print("    Bank Of Ireland\n");
		System.out.print("\033[37m");
		System.out.print("  *******************\n\n");
		
		System.out.println("\033[33mCustomer Name: \033[37m" +accounts.get(accountID).getCustName());
		System.out.println("\033[33mBalance: \033[37m$" + formatter.format(accounts.get(accountID).getBalance()));
		
		System.out.println("\n\nAccount History");
		
		if(history.size() > 0)
		{
			for(int i = 0; i < history.size(); i++)
			{
				if(history.get(i).charAt(0) == 'W')
				{
					System.out.println("\033[31m" + history.get(i) + "\033[37m");
				}
				else
				{
					System.out.println("\033[32m" + history.get(i) + "\033[37m");
				}
			}
		}
		else
		{
			System.out.println("\nNo Previous Account History");
		}
		
		System.out.println("\nPress Enter to Continue");
		scanner.nextLine();
		
		mainOptions(accountNum);
	}
	
	public static void withdrawCash(int accountNum)
	{
		int accountID = 0;
		int amount;
		String option;
		String response = "";
		Scanner scanner = new Scanner(System.in);
		
		for(int i = 0; i < accounts.size(); i++)
		{
			//Check if accountID exists in arraylist
			if(accounts.get(i).getAccountID() == accountNum)
			{
				accountID = i;
				break;
			}
			else
			{
				continue;
			}
		}
		
		double balance = accounts.get(accountID).getBalance();
		
		try
		{
			cls();
			System.out.print("  *******************\n");
			System.out.print("    \033[34mBank Of Ireland\033[37m\n");
			System.out.print("  *******************\n\n");
			System.out.println("Withdraw Cash\n");
		
			System.out.println("\033[33mCurrent Balance: \033[37m$" + formatter.format(balance));
			
			System.out.println("\n\nA) $200        B) $100");
			System.out.println("\nC) $50         D) $20");
			System.out.println("\nE) $10         F) $5");
			System.out.println("\nG) Other       H) Exit");
			System.out.print("\n\033[33mSelect Option: \033[37m");
		
			option = scanner.nextLine();
			
			switch(option)
			{
			case "A":
			case "a":
				amount = -200;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'W');
				break;
				
			case "B":
			case "b":
				amount = -100;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'W');
				break;
				
			case "C":
			case "c":
				amount = -50;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'W');
				break;
				
			case "D":
			case "d":
				amount = -20;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'W');
				break;
				
			case "E":
			case "e":
				amount = -10;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'W');
				break;
				
			case "F":
			case "f":
				amount = -5;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'W');
				break;
				
			case "G":
			case "g":
				System.out.print("\033[33mEnter Amount to Withdraw: \033[37m$");
				amount = scanner.nextInt();
				
				if(amount % 5 != 0)
				{
					System.out.println("\033[31mAmount must be in notes\033[37m");
					
					try 
					{
						Thread.sleep(2000);
					} 
					catch (InterruptedException ee) 
					{
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}
					
					withdrawCash(accountNum);
				}
				else 
				{
					withdrawDeposit(balance, accountID, accountNum, -amount, 'W');
				}
				mainOptions(accountNum);
				break;
				
			case "H":
			case "h":
				mainOptions(accountNum);
			default:
				System.out.print("\033[31m");
				System.out.println("Invalid Input");
				System.out.print("\033[37m");
				
				try 
				{
					Thread.sleep(2000);
				} 
				catch (InterruptedException ee) 
				{
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
				
				withdrawCash(accountNum);
			}
			
			System.out.println(response);
			try 
			{
				Thread.sleep(2000);
			} 
			catch (InterruptedException ee) 
			{
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			
			mainOptions(accountNum);
				
		}
		catch(InputMismatchException e)
		{
			System.out.print("\033[31m");
			System.out.println("Invalid Input");
			System.out.print("\033[37m");
			
			try 
			{
				Thread.sleep(2000);
			} 
			catch (InterruptedException ee) 
			{
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			
			withdrawCash(accountNum);
		}
	}
	
	public static void depositCash(int accountNum)
	{
		int accountID = 0;
		int amount;
		String option;
		String response = "";
		Scanner scanner = new Scanner(System.in);
		
		for(int i = 0; i < accounts.size(); i++)
		{
			//Check if accountID exists in arraylist
			if(accounts.get(i).getAccountID() == accountNum)
			{
				accountID = i;
				break;
			}
			else
			{
				continue;
			}
		}
		
		double balance = accounts.get(accountID).getBalance();
		
		try
		{
			cls();
			System.out.print("  *******************\n");
			System.out.print("    \033[34mBank Of Ireland\033[37m\n");
			System.out.print("  *******************\n\n");
			System.out.println("Deposit Cash\n");
		
			System.out.println("\033[33mCurrent Balance: \033[37m$" + formatter.format(balance));
			
			System.out.println("\n\nA) $200        B) $100");
			System.out.println("\nC) $50         D) $20");
			System.out.println("\nE) $10         F) $5");
			System.out.println("\nG) Other       H) Exit");
			System.out.print("\n\033[33mSelect Option: \033[37m");
		
			option = scanner.nextLine();
			
			switch(option)
			{
			case "A":
			case "a":
				amount = 200;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'D');
				break;
				
			case "B":
			case "b":
				amount = 100;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'D');
				break;
				
			case "C":
			case "c":
				amount = 50;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'D');
				break;
				
			case "D":
			case "d":
				amount = 20;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'D');
				break;
				
			case "E":
			case "e":
				amount = 10;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'D');
				break;
				
			case "F":
			case "f":
				amount = 5;
				response = withdrawDeposit(balance, accountID, accountNum, amount, 'D');
				break;
				
			case "G":
			case "g":
				System.out.print("\033[33mEnter Amount to Deposit: \033[37m$");
				amount = scanner.nextInt();
				
				if(amount % 5 != 0)
				{
					System.out.println("\033[31mAmount must be in notes\033[37m");
					
					try 
					{
						Thread.sleep(2000);
					} 
					catch (InterruptedException ee) 
					{
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}
					
					depositCash(accountNum);
				}
				else 
				{
					withdrawDeposit(balance, accountID, accountNum, amount, 'D');
				}
				mainOptions(accountNum);
				break;
				
			case "H":
			case "h":
				mainOptions(accountNum);
			default:
				System.out.print("\033[31m");
				System.out.println("Invalid Input");
				System.out.print("\033[37m");
				
				try 
				{
					Thread.sleep(2000);
				} 
				catch (InterruptedException ee) 
				{
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
				
				depositCash(accountNum);
			}
			
			System.out.println(response);
			try 
			{
				Thread.sleep(2000);
			} 
			catch (InterruptedException ee) 
			{
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			
			mainOptions(accountNum);
				
		}
		catch(InputMismatchException e)
		{
			System.out.print("\033[31m");
			System.out.println("Invalid Input");
			System.out.print("\033[37m");
			
			try 
			{
				Thread.sleep(2000);
			} 
			catch (InterruptedException ee) 
			{
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			
			depositCash(accountNum);
		}
	}
	
	//Method to either withdraw from account or deposit to account
	public static String withdrawDeposit(double balance, int accountID, int accountNum, int amount, char operation)
	{
		String response = "";
		ArrayList<String> history = new ArrayList<String>();
		history = accounts.get(accountID).getHistory();
		
		if(balance - amount >= 0)
		{
			accounts.get(accountID).setBalance(balance + amount);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
			String dateTime = formatter.format(date);
			
			if(operation == 'W')
			{
				response = "$" + -amount + " withdrawn";
				String w = "Withdraw: $" + -amount + " - " + dateTime;
				history.add(w);
			}
			if(operation == 'D')
			{
				response = "$" + amount + " deposited";
				String d = "Deposit: $" + amount + " - " + dateTime;
				history.add(d);
			}
			
			accounts.get(accountID).setHistory(history);
			awr.Write(accounts);
		}
		else
		{
			response = "\033[31mNot Enough Funds\033[37m";
		}
		
		return response;
	}
	
	//Delete account from arraylist
	public static void deleteAccount(int accountNum)
	{
		Scanner scanner = new Scanner(System.in);
		String response = "";
		boolean PINconfirm = false;
		int PIN = 0;
		int checkPIN = 0;
		int accountID = 0;
		
		for(int i = 0; i < accounts.size(); i++)
		{
			//Check if accountID exists in arraylist
			if(accounts.get(i).getAccountID() == accountNum)
			{
				accountID = i;
				break;
			}
			else
			{
				continue;
			}
		}
		
		//Show while user has entered wrong input
		do
		{
			cls();
			System.out.print("  *******************\n");
			System.out.print("\033[34m");
			System.out.print("    Bank Of Ireland\n");
			System.out.print("\033[37m");
			System.out.print("  *******************\n\n");
			
			System.out.println("\033[33mDelete Account:");
			System.out.println("\nAccount No: \033[37m"+ accountNum);
			System.out.println("\033[33mCustomer Name: \033[37m" + accounts.get(accountID).getCustName()); 
			System.out.print("\n\033[33mConfirm Y/N: \033[37m");
			
			try
			{
				response = scanner.nextLine();
				
				switch(response)
				{
				case "Y":
				case "y":
					System.out.print("\033[33mConfirm PIN: \033[37m");
					checkPIN = scanner.nextInt();
					
					if(checkPIN == accounts.get(accountID).getPIN())
					{
						cls();
						PINconfirm = true;
						accounts.remove(accountID);
						awr.Write(accounts);
						System.out.println("Account No. " + accountNum + " deleted");
						loading();
					}
					else
					{
						System.out.print("\033[31m");
						System.out.println("PIN Incorrect");
						System.out.print("\033[37m");
						
						try 
						{
							Thread.sleep(2000);
						} 
						catch (InterruptedException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						PINconfirm = false;
					}

				case "N":
				case "n":
					mainOptions(accountNum);

				default:
					System.out.print("\033[31m");
					System.out.println("Invalid Selection");
					System.out.print("\033[37m");
		
					try 
					{
						Thread.sleep(2000);
					} 
					catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			catch(InputMismatchException e)
			{
				System.out.print("\033[31m");
				System.out.print("Invalid Input");
				System.out.println("\033[37m");
				try 
				{
					Thread.sleep(2000);
				} 
				catch (InterruptedException ee) 
				{
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
				cls();
				deleteAccount(accountNum);
			}
		} 
		while(!response.equals("Y") && !response.equals("y") && !response.equals("N") && !response.equals("n") || PINconfirm == false);
		
		openingScreen();
	}
	
	//Clear terminal window
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
	
	//Show loading screen
	public static void loading()
	{
		System.out.print("Loading...");
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Exit program
	public static void exit()
	{
		System.exit(0);
	}
}
