package assign1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		 Pattern p = Pattern.compile("[-]");
		 Matcher m = p.matcher("-");
		 boolean b = m.matches();
		 System.out.println(b);
		 byte[] ch = "12345 ".getBytes();
		 String[] c1 = "12345 123  ".split(" ");

		 for (String string : c1) {
			System.out.println(string);
		}
		 
		 System.out.println("123456789".substring("123456789".length()-3));
		 System.out.println("123456789".subSequence(8, 9).toString());
	}
}
