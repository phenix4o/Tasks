package das;

import java.util.Scanner;

public class Staqwecy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println("The number is odd: " +das.Staqwecy.isOdd(n));
	}
	static boolean isOdd(int n){
		if(n%2==0){
			return false;
		}
		else
			return true;
	}

}
