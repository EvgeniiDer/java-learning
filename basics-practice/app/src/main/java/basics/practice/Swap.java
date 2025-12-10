package basics.practice;


public class Swap
{
    private int iInputDigit;
    public Swap(int number)
    {
        if(isSixDigit(number))
        {
            this.iInputDigit = swapDigits(number);
        }
        System.out.print(iInputDigit);
    }
    private boolean isSixDigit(int number)
    {
        return number >= 100000 && number <= 999999;
    }
    private int swapDigits(int number)
    {
        int[] digits = new int[6];
        int temp = number;

        for(int i = 5; i >= 0; i--)
        {
            digits[i] = temp % 10;
            temp /= 10;
        }

        for(int i = 0; i < digits.length / 2; i++)
        {
            int swap = digits[i];
            digits[i] = digits[digits.length - 1 - i];
            digits[digits.length - 1 - i] = swap;
        }

        int result = 0;
        for(int i = 0; i < 6 ; i++)
        {
            result = result  * 10 + digits[i];
        }
        return result;
    }
    
}