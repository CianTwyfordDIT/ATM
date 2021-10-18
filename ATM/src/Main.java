import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) 
	{
		displayLogIn();
	}
	
	public static void displayLogIn()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("*******************\n");
		System.out.print("Bank Of Ireland\n");
		System.out.print("*******************\n\n");
		System.out.println("1) Log In");
		System.out.println("2) Create Account");
		
		scanner.close();
	}

}
