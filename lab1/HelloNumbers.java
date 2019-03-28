public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int sum = x;
        while (x < 10) {
        	sum += x;
        	System.out.print(sum + " ");
        	x = x+1;
        }
    }
}
