import java.math.BigInteger;
import java.util.Random;

/**
 * Created by andreydelany on 24/11/2016.
 */
public class RabinMiller {
    BigInteger three = new BigInteger("3");
    BigInteger two = new BigInteger("2");
    BigInteger s;
    BigInteger t;
    Random randomGenerator;
    BigInteger numberForTesting;
    BigInteger numberForTestingMinusOne;
    int amountOfCircles;

    public RabinMiller() {
    }

    public Boolean getRabinMillerPrime(BigInteger currentNumberForTesting, int amountOfCircles) {
        initializeImportantValues(amountOfCircles,currentNumberForTesting);
        if( isCurrentNumberForTestingLegal(numberForTesting)){
            defineSAndT(numberForTesting);
            divideSWithTwoUnitlTwoIsOdd();
            initializeRandom();
            runActualAlgorithmGivenNumberOfTimes(amountOfCircles);
        } else {
            return false;
        }
        return false;
    }

    private boolean isCurrentNumberForTestingLegal(BigInteger input) {
        return isBiggerThenThree(input) && isOdd(input);
    }

    private boolean isBiggerThenThree( BigInteger input) {
        if (input.compareTo(three) == -1)
            return false;
        else
            return true;
    }

    private boolean isBiggerThenTwo( BigInteger input) {
        if (input.compareTo(two) == -1)
            return false;
        else
            return true;
    }

    private boolean isSmallerThenN( BigInteger input) {
        if (input.compareTo(numberForTesting) == 1)
            return false;
        else
            return true;
    }

    private boolean isOdd( BigInteger input) {
        return (input.mod(two) == BigInteger.ONE);
    }

    private void initializeImportantValues(int amountOfCircles, BigInteger currentNumberForTesting) {
        numberForTesting = currentNumberForTesting;
        numberForTestingMinusOne = currentNumberForTesting.subtract(BigInteger.ONE);
        this.amountOfCircles = amountOfCircles;
    }

    private void defineSAndT(BigInteger input) {
        s = input.subtract(BigInteger.ONE);
        t = BigInteger.ZERO;
    }

    private void divideSWithTwoUnitlTwoIsOdd() {
        while (!isOdd(s)) {
            s = s.divide(two);
            t = t.add(BigInteger.ONE);
        }
    }

    private void initializeRandom() {
        randomGenerator = new Random();
    }

    private boolean runActualAlgorithmGivenNumberOfTimes(int amountOfCircles) {
        for(int i = 0; i < amountOfCircles; i++) {
            if (! actualAlgorithm())
                    return false;
        }
        return true;
    }

    private boolean actualAlgorithm() {
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

    private BigInteger createRandomNumber() {
        BigInteger rndm;
        do {
            rndm = generateRandomNumber();
        } while (! isRandomNumberInBorders(rndm));
        
        return rndm;
    }

    private BigInteger generateRandomNumber() {
        return new BigInteger((numberForTesting).bitLength(),randomGenerator);
    }

    private boolean isRandomNumberInBorders(BigInteger input){
        return isBiggerThenTwo(input) && isSmallerThenN(input);
    }
}
