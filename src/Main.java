import java.math.BigInteger;
import java.util.Random;

public class Main {

	private static int countFalseResults32 = 0;
	private static int countResults512 = 0;
    	static  BigInteger smalles32BitNumer = new BigInteger("2147483648");
    	static  BigInteger smalles512BitNumer = new BigInteger((int)Math.pow(2, 511) + "");
    	static int counter = 0;
	private static boolean found32BitResult = false;
	private static BigInteger falsePrimeWith32Bit;
	private static boolean found512BitResult = false;
	private static BigInteger pseudoPrimeWith512Bit;

	public static void main(String [ ] args) {
		primeWithinOneMillion();
        RabinMiller rabinMiller = new RabinMiller();
		System.out.println();		
		while(!found32BitResult ){
			Random rnd = new Random();
			falsePrimeWith32Bit = findNewBigEnoughRandomNumberWith32Bit(rnd);
			found32BitResult = find32BitFalseResult(rabinMiller, falsePrimeWith32Bit);
		}
		System.out.println("False Prime Number with 32 Bit is: " + falsePrimeWith32Bit + ".");
		System.out.println("We tested " + countFalseResults32 + " different numbers.");
		countFalseResults32 = 0;
		
		while(!found512BitResult){
			Random rnd = new Random();
			pseudoPrimeWith512Bit = findNewBigEnoughRandomNumberWith512Bit(rnd);
			found512BitResult = find512BitPseudoPrime(rabinMiller);
		}
		System.out.println();
		System.out.println("Our randomly generated 512 Bit pseudo prime number is " + pseudoPrimeWith512Bit + ".");
		System.out.println("We tested " + countResults512 + " different numbers.");
		countResults512 = 0;
		}
	
	public static boolean find32BitFalseResult(RabinMiller rabinMiller, BigInteger falsePrimeWith32Bit){
		countFalseResults32++;
		if(!(rabinMiller.getRabinMillerPrime(falsePrimeWith32Bit,1) == true && rabinMiller.getRabinMillerPrime(falsePrimeWith32Bit,10) == false)){
			return false;
		}
		return true;
	}

	private static BigInteger findNewBigEnoughRandomNumberWith32Bit(Random rnd) {
		BigInteger with32Bit;
		do {
			with32Bit = new BigInteger(32, rnd);
		} while (with32Bit.compareTo(smalles32BitNumer) <= 0);
		return with32Bit;
	}
	
	private static BigInteger findNewBigEnoughRandomNumberWith512Bit(Random rnd) {
		BigInteger with512Bit;
		do {
			with512Bit = new BigInteger(512, rnd);
		} while (with512Bit.compareTo(smalles512BitNumer) <= 0);
		return with512Bit;
	}

	public static void primeWithinOneMillion(){
		int primeWithinOneMillion = 0;
        RabinMiller rabinMiller = new RabinMiller();
		for(int k = 1; k < 6; k++){
			for(int i = 0; i < 1000000; i++){
                BigInteger iAsBigInteger = new BigInteger(i + "");
				if(rabinMiller.getRabinMillerPrime(iAsBigInteger, k))
				    primeWithinOneMillion ++;
			}
			System.out.println("k = " + k + " finds " + primeWithinOneMillion + " prime numbers");
			primeWithinOneMillion = 0;
		}
	}
	
	public static boolean find512BitPseudoPrime(RabinMiller rabinMiller){
		countResults512++;
		if((rabinMiller.getRabinMillerPrime(pseudoPrimeWith512Bit,5) == true)){
			return true;
		}
		return false;
	}
}
