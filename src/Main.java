import java.math.BigInteger;
import java.util.Random;

/**
 * Created by andreydelany on 24/11/2016.
 */
public class Main {

	private static int countFalseResults32 = 0;
	private static int countResults512 = 0;

	public static void main(String [ ] args) {
		primeWithinOneMillion();
		System.out.println();
		System.out.println("False Prime Number with 32 Bit is: " + find32BitFalseResult() + ".");
		System.out.println("We tested " + countFalseResults32 + " different numbers.");
		countFalseResults32 = 0;
		System.out.println();
		System.out.println("Our randomly generated 512 Bit pseudo prime number is " + find512BitPseudoPrime() + ".");
		System.out.println("We tested " + countResults512 + " different numbers.");
		countResults512 = 0;
		}
	
	public static BigInteger find32BitFalseResult(){
		Random rnd = new Random();
		BigInteger compare32Bit = new BigInteger("2147483648");
		BigInteger falsePrimeWith32Bit = findNewBigEnoughRandomNumber(rnd, compare32Bit);
		countFalseResults32++;
        RabinMiller rabinMiller = new RabinMiller();
		while(!(rabinMiller.getRabinMillerPrime(falsePrimeWith32Bit,1) == true && rabinMiller.getRabinMillerPrime(falsePrimeWith32Bit,10) == false)){
			find32BitFalseResult();
		}
		return falsePrimeWith32Bit;
	}

	private static BigInteger findNewBigEnoughRandomNumber(Random rnd, BigInteger compare32Bit) {
		BigInteger falsePrimeWith32Bit;
		do{
			falsePrimeWith32Bit = new BigInteger(32, rnd);
		}while (falsePrimeWith32Bit.compareTo(compare32Bit) <= 0);
		return falsePrimeWith32Bit;
	}

	public static void primeWithinOneMillion(){
		int primeWithinOneMillion = 0;
        RabinMiller rabinMiller = new RabinMiller();
		for(int k = 1; k < 6; k++){
			for(int i = 0; i < 1000000; i++){
                BigInteger iAsBigInteger = new BigInteger(i + "");
				if(rabinMiller.getRabinMillerPrime(iAsBigInteger, k)) primeWithinOneMillion++;
			}
			System.out.println("k = " + k + " finds " + primeWithinOneMillion + " prime numbers");
			primeWithinOneMillion = 0;
		}
	}
	
	public static BigInteger find512BitPseudoPrime(){
		Random rnd = new Random();
		BigInteger compare512Bit = new BigInteger(Math.pow(2, 511) + "");
		BigInteger falsePrimeWith512Bit;
		do{
			falsePrimeWith512Bit = new BigInteger(512, rnd);
		}while (falsePrimeWith512Bit.compareTo(compare512Bit) <= 0);
		
		countResults512++;
        RabinMiller rabinMiller = new RabinMiller();
		while(!(rabinMiller.getRabinMillerPrime(falsePrimeWith512Bit,5) == false)){
			find32BitFalseResult();
		}
		return falsePrimeWith512Bit;
	}
}
