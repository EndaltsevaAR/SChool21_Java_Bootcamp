package ex00;

public class Program {
    public static void main(String[] args) {
        int number = 479598;
        System.out.println(getSum(number));
    }

    private static int getSum(int number) {
        String strNumber = String.valueOf(number);
        return strNumber.charAt(0) + strNumber.charAt(1) + strNumber.charAt(2) + strNumber.charAt(3) + strNumber.charAt(4) + strNumber.charAt(5) - '0' * 6;
    }
}