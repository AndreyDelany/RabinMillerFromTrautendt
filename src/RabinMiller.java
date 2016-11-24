import java.math.BigInteger;
import java.util.Random;

/**
 * Created by andreydelany on 24/11/2016.
 */
public class RabinMiller {
    static BigInteger three = new BigInteger("3");
    static BigInteger two = new BigInteger("2");
    static BigInteger s;
    static BigInteger t;
    static Random randomGenerator;
    static BigInteger numberForTesting;
    static BigInteger numberForTestingMinusOne;

    public static Boolean getRabinMillerPrime(int amountOfCircles, BigInteger currentNumberForTesting) {

        if( isCurrentNumberForTestingLegal(currentNumberForTesting)){
            numberForTesting = currentNumberForTesting;
            numberForTestingMinusOne = currentNumberForTesting.subtract(BigInteger.ONE);
            defineSAndT(currentNumberForTesting);
            divideSWithTwoUnitlTwoIsOdd();
            initializeRandom();
            runActualAlgorithmGivenNumberOfTimes(amountOfCircles);
        } else {
            return false;
        }
        return false;
    }

    private static boolean isCurrentNumberForTestingLegal(BigInteger input) {
        return isBiggerThenThree(input) && isOdd(input);
    }

    private static boolean isBiggerThenThree( BigInteger input) {
        if (input.compareTo(three) == -1)
            return false;
        else
            return true;
    }

    private static boolean isBiggerThenTwo( BigInteger input) {
        if (input.compareTo(two) == -1)
            return false;
        else
            return true;
    }

    private static boolean isSmallerThenN( BigInteger input) {
        if (input.compareTo(numberForTesting) == 1)
            return false;
        else
            return true;
    }

    private static boolean isOdd( BigInteger input) {
        return (input.mod(two) == BigInteger.ONE);
    }

    private static void defineSAndT(BigInteger input) {
        s = input.subtract(BigInteger.ONE);
        t = BigInteger.ZERO;
    }

    private static void divideSWithTwoUnitlTwoIsOdd() {
        while (!isOdd(s)) {
            s = s.divide(two);
            t = t.add(BigInteger.ONE);
        }
    }

    private static void initializeRandom() {
        randomGenerator = new Random();
    }

    private static boolean runActualAlgorithmGivenNumberOfTimes(int amountOfCircles) {
        for(int i = 0; i < amountOfCircles; i++) {
            if (! actualAlgorithm())
                    return false;
        }
        return true;
    }

    private static boolean actualAlgorithm() {
        BigInteger a = createRandomNumber();
        BigInteger v = a.modPow(s,numberForTesting);
        if (v.compareTo(BigInteger.ONE) == 0)
            return true;
        else {
            BigInteger i = BigInteger.ZERO;
            while (v.compareTo(numberForTestingMinusOne) != 0) {
                if ((i.compareTo(t.subtract(BigInteger.ONE))) == 0)
                    return false;
                else {
                    v = v.modPow(two, numberForTesting);
                    i = i.add(BigInteger.ONE);
                }
            }
        }
        return true;
    }

    private static BigInteger createRandomNumber() {
        BigInteger rndm = generateRandomNumber();
        while (! isRandomNumberInBorders(rndm)) {
            rndm = generateRandomNumber();
        }
        return rndm;
    }

    private static BigInteger generateRandomNumber() {
        return new BigInteger((numberForTesting).bitLength(),randomGenerator);
    }

    private static boolean isRandomNumberInBorders(BigInteger input){
        return isBiggerThenTwo(input) && isSmallerThenN(input);
    }
}
