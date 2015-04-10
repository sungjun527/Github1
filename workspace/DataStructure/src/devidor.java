import java.util.Scanner;


public class devidor {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("소인수 분해 할 숫자를 입력하세요 : ");
		try {
			int num = s.nextInt();
			for (int i = 1; i <= num; i++) {
				if(num%i==0){
					System.out.print(i+" ");
				}
			}
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("숫자입력요망");
		} finally {
			s.close();
		}
		
	}
}
