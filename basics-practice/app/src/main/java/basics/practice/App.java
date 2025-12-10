

package basics.practice;
import java.util.Scanner;

public class App{
    public static void main(String [] argc)
    {
        System.out.println(getSteveJobsQuote());
        System.out.println(calculatePercentage());
        Swap swap = new Swap(123456);
    }
    public static String getSteveJobsQuote()
    {
        return "\t\"Your time is limited,\n\t\tso don't waste it\n\t\t\tliving someone else's life\"\n\t\t\t\tSteve Jobs";
    }
    public static double calculatePercentage()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        double number = scanner.nextDouble();
        System.out.print("Enter percent: ");
        int percent = scanner.nextInt();
        if(percent > 100 || percent < 0 )
            {
                throw new IllegalArgumentException("Percent must be between 0 and 100");
            }
                return (number * percent) / 100;
    }
    public static int combineDigits()
    {
        Scanner scanner = new Scanner(System.in); 
        System.out.print("Enter the number of digits: ");
        int count = scanner.nextInt();    
        int result = 0;
        for(int i = 0; i < count; i++)
            {
                System.out.print("Digit: " + (i + 1) + ": ");
                int digit = scanner.nextInt();
                
                if (digit < 0 || digit > 9) {
                    throw new IllegalArgumentException("Digits mast be from 0 to 9");
                }
                
                result = result * 10 + digit; 
            }
        return result;
    }
}