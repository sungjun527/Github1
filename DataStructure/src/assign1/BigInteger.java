package assign1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class BigInteger {
	public static final String QUIT_COMMAND = "quit";
	public static final String MSG_INVALID_INPUT = "입력이 잘못되었습니다.";
	public static final Pattern OPERATOR = Pattern.compile("[*+-]");
	public static final Pattern NUMBER = Pattern.compile("[0-9]");
	int[] num;
	boolean plus = true;

	static public BigInteger minusNumber(BigInteger bi) {
		bi.plus = false;
		return bi;
	}

	
	public BigInteger(int i) {
		num = new int[] { i };
		plus = i > 0;
	}

	public BigInteger(int[] num) {
		this.num = num;
		this.plus = num[num.length - 1] >= 0;
	}

	public BigInteger(String inputString) {
		int size = (inputString.length()-1) / 8 + 1;
		int[] ints = new int[size];
		for (int i = 0; i < ints.length; i++) {
			if (inputString.length() - 8 > 0) {
				ints[i] = Integer.parseInt(inputString.substring(inputString.length() - 8));
				inputString = inputString.substring(0, inputString.length() - 8);
			} else {
				ints[i] = Integer.parseInt(inputString);
			}
		}
		num = ints;
	}
	

	public BigInteger add(BigInteger big) {
		//계산을 위한 배열 생성
		int length = (big.num.length < this.num.length)?this.num.length:big.num.length;
		int[] intArray1 = new int[length];
		int[] intArray2 = new int[length];
		int[] resultArray = new int[length+1];
		for (int i = 0; i < big.num.length; i++) 
			intArray1[i]=big.num[i];
		for (int i = 0; i < this.num.length; i++) 
			intArray2[i]=this.num[i];
		
		//계산
		int up = 0;
		int tmp = 0;
		for (int i = 0; i < length; i++) {
			tmp = intArray1[i] + intArray2[i] + up;
			up = tmp / 100000000;
			resultArray[i] =  tmp % 100000000;
		}
		if (up != 0) {
			resultArray[length] = up;
		} 
		
		//리턴
		return new BigInteger(resultArray);
	}

	
	public BigInteger subtract(BigInteger big) {
		//연산에 필요한 변수 설정
		boolean plusResult = true;
		int length = (big.num.length < this.num.length)?this.num.length:big.num.length;
		int[] bigNumArray = new int[length];
		int[] smallNumArray = new int[length];
		int[] resultArray = new int[length];
		
		//대소비교
		if (big.num.length > this.num.length) {
			plusResult=false;
		} else if (big.num.length == this.num.length){
			for (int k = 0; k < num.length; k++) {
				if(big.num[big.num.length-k-1]>this.num[this.num.length-k-1]){
					plusResult=false;
					break;
				} else if(big.num[big.num.length-k-1]<this.num[this.num.length-k-1]){
					break;
				}
			}
		}
		
		//배열 초기화
		if(plusResult){
			for (int i = 0; i < big.num.length; i++) 
				bigNumArray[i]=this.num[i];
			for (int i = 0; i < this.num.length; i++) 
				smallNumArray[i]=big.num[i];
		} else {
			for (int i = 0; i < big.num.length; i++) 
				bigNumArray[i]=big.num[i];
			for (int i = 0; i < this.num.length; i++) 
				smallNumArray[i]=this.num[i];
		}

		//연산
		int up = 0;
		for (int i = 0; i <length; i++) {
			int tmp = bigNumArray[i]-smallNumArray[i]+up;
			if(tmp<0){
				resultArray[i]=100000000+tmp;
				up=-1;
			} else {
				resultArray[i]=tmp;
				up=0;
			}
		}
		
		//리턴
		BigInteger answer = new BigInteger(resultArray);
		answer.plus=plusResult;
		return answer;
	}

	
	public BigInteger multiply(BigInteger big) {
		//계산을 위한 배열 생성
		int[] tmp = new int[this.num.length+big.num.length];
		
		//계산
		for (int i = 0; i < this.num.length; i++) {
			for (int j = 0; j < big.num.length; j++) {
				tmp[i+j]+=(this.num[i]%10000)*(big.num[j]%10000);
				tmp[i+j]+=((this.num[i]/10000)*(big.num[j]%10000)+(this.num[i]%10000)*(big.num[j]/10000));
				if(tmp[i+j]>=10000){
						
					tmp[i+j+1]+=tmp[i+j]/10000;
					tmp[i+j]%=10000;
				}
				tmp[i+j+1]+=(this.num[i]/10000)*(big.num[j]/10000);
			}
		}
		
		//리턴
		return new BigInteger(tmp);
		
		//쪼개지는 핸드폰이 있었으면 좋겠다.
//		
//		BigInteger answer = new BigInteger(0);
//		BigInteger counter = new BigInteger(0);
//		while(!big.toString().equals(counter.toString())){
//			answer=answer.add(this);
//			counter=counter.add(new BigInteger(1));
//		}
//		return answer;
	}
	
	@Override
	public String toString() {
		String buf = "";
		for (int i = 0; i < num.length; i++) {
			String tmp = "" + num[i];
			while (tmp.length() != 8)
				tmp = "0" + tmp;
			buf = tmp + buf;
		}
		
		//앞의 0 제거
		while(buf.startsWith("0")){
			buf=buf.substring(1);
		}
		if("".equals(buf)) return "0";//0 처리
		if(!plus) buf = "-" + buf;//음수처리
		return buf; //리턴

	}

	static BigInteger evaluate(String input) throws IllegalArgumentException {
		// formating
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		String operator = null;
		CharSequence ch = null;
		String type = "o";
		for (int i = 0; i < input.length(); i++) {
			ch = input.subSequence(i, i + 1);
			if (NUMBER.matcher(ch).matches()) {
				if (operator == null)
					sb1.append(ch);
				else
					sb2.append(ch);
			} else if (OPERATOR.matcher(ch).matches()) {
				if("*".equals(ch.toString())){
					operator = ch.toString();
				} else {
					if ("".equals(sb1.toString())){
						if("-".equals(ch.toString())){
							type = "-o";
						}
					} else if (operator == null) {
						operator = "+";
						if ("-".equals(ch.toString())) {
							type = type + "-";
						}
					} else if ("-".equals(ch.toString())) {
						type = type + "-";
					}

				}
			}
		}
		if (operator == null)
			type = "o-";
		
		BigInteger num1 = new BigInteger(sb1.toString());
		BigInteger num2 = new BigInteger(sb2.toString());

		// 연산
		if ("o".equals(type)) {// 숫자+연산자+숫자 형태
			if ("+".equals(operator)) {
				return num1.add(num2);
			} else if ("*".equals(operator)) {
				return num1.multiply(num2);
			}
		} else if ("-o".equals(type)) {// (-숫자)+연산자+숫자 형태
			if ("+".equals(operator)) {
				return num2.subtract(num1);
			} else if ("*".equals(operator)) {
				return minusNumber(num1.multiply(num2));
			}
		} else if ("o-".equals(type)) {// 숫자+연산자+(-숫자) 형태
			if ("+".equals(operator)) {
				return num1.subtract(num2);
			} else if ("*".equals(operator)) {
				return minusNumber(num1.multiply(num2));
			}
		} else if ("-o-".equals(type)) {// (-숫자)+연산자+(-숫자) 형태
			if ("+".equals(operator)) {
				return minusNumber(num1.add(num2));
			} else if ("*".equals(operator)) {
				return num1.multiply(num2);
			}
		}
		return new BigInteger(2714);//잘못된 포맷일 경우 2714를 반환(의미 없음)
	}

	public static void main(String[] args) throws Exception {
		try (InputStreamReader isr = new InputStreamReader(System.in)) {
			try (BufferedReader reader = new BufferedReader(isr)) {
				boolean done = false;
				while (!done) {
					String input = reader.readLine();

					try {
						done = processInput(input);
					} catch (IllegalArgumentException e) {
						System.err.println(MSG_INVALID_INPUT);
					}
				}
			}
		}
	}

	static boolean processInput(String input) throws IllegalArgumentException {
		boolean quit = isQuitCmd(input);

		if (quit) {
			return true;
		} else {
			BigInteger result = evaluate(input);
			System.out.println(result.toString());

			return false;
		}
	}

	static boolean isQuitCmd(String input) {
		return input.equalsIgnoreCase(QUIT_COMMAND);
	}
}