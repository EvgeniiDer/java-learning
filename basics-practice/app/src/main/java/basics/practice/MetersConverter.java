package basics.practice;

import java.util.Scanner;

public class MetersConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter meters: ");
        double meters = scanner.nextDouble();
        
        System.out.println("Convert to:");
        System.out.println("1. Miles");
        System.out.println("2. Inches");
        System.out.println("3. Yards");
        System.out.print("Choose (1-3): ");
        
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                double miles = meters * 0.000621371;
                System.out.printf("%.2f meters = %.5f miles\n", meters, miles);
                break;
            case 2:
                double inches = meters * 39.3701;
                System.out.printf("%.2f meters = %.2f inches\n", meters, inches);
                break;
            case 3:
                double yards = meters * 1.09361;
                System.out.printf("%.2f meters = %.2f yards\n", meters, yards);
                break;
            default:
                System.out.println("Invalid choice! Please select 1, 2 or 3.");
        }
        
        scanner.close();
    }
}