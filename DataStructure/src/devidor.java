import java.util.Scanner;


public class devidor {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("���μ� ���� �� ���ڸ� �Է��ϼ��� : ");
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
			System.out.println("�����Է¿��");
		} finally {
			s.close();
		}
		
	}
}
