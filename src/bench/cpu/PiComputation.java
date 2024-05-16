package bench.cpu;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

import static java.math.BigDecimal.*;

public class PiComputation
{
    private static boolean isInsideCircle(double x, double y)
    {
        return (x * x + y * y <= 1);
    }

    public static BigDecimal MonteCarlo(Object ... params)
    {
        long n = (long) params[0];

        long insidePointsCounter = 0;

        Random random = new Random();

        double rangeMin = -1;

        double rangeMax = 1;

        for (long i = 0; i < n; ++i)
        {
            double pointX = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
            double pointY = rangeMin + (rangeMax - rangeMin) * random.nextDouble();

            if (isInsideCircle(pointX, pointY))
            {
                insidePointsCounter++;
            }
        }

        BigDecimal insidePoints = valueOf(insidePointsCounter);

        insidePoints.setScale(10, RoundingMode.HALF_UP);

        BigDecimal totalPoints = valueOf(n);

        // Set the precision and rounding mode for better accuracy
        MathContext mc = new MathContext(100, RoundingMode.HALF_UP);

        BigDecimal piEstimate = insidePoints.multiply(valueOf(4), mc).divide(totalPoints, mc);

        return piEstimate;
    }

    public static BigDecimal GregoryLiebniz(Object ... params)
    {
        long k = (long) params[0];

        double sum = 0;

        for(int i = 0; i < k; ++ i)
        {
            if(i % 2 == 0)
            {
                sum += ((double) 1 / (2 * i + 1));
            }
            else
            {
                sum -= ((double) 1 / (2 * i + 1));
            }
        }

        BigDecimal bigSmokeMirosystems = valueOf(sum);

        bigSmokeMirosystems.setScale(10, RoundingMode.HALF_UP);

        MathContext mc = new MathContext(100, RoundingMode.HALF_UP);

        BigDecimal piEstimate = bigSmokeMirosystems.multiply(valueOf(4), mc);

        return piEstimate;
    }

    public static BigDecimal GaussLegandar(Object ... params)
    {
        BigDecimal a = BigDecimal.ONE;

        BigDecimal b = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(Math.sqrt(2)), MathContext.DECIMAL128);

        BigDecimal t = BigDecimal.valueOf(0.25);

        BigDecimal p = BigDecimal.ONE;

        // Ensure params[0] is treated as a double and then converted to BigDecimal
        double accuracyDouble = (double) params[0];
        BigDecimal accuracy = BigDecimal.valueOf(accuracyDouble);

        BigDecimal two = BigDecimal.valueOf(2);

        BigDecimal four = BigDecimal.valueOf(4);

        BigDecimal epsilon = accuracy; // Precision threshold

        BigDecimal aNext;

        BigDecimal bNext;

        BigDecimal tNext;

        do
        {
            aNext = a.add(b).divide(two, MathContext.DECIMAL128);

            bNext = BigDecimal.valueOf(Math.sqrt(a.multiply(b).doubleValue()));

            tNext = t.subtract(p.multiply(a.subtract(aNext).pow(2)));

            a = aNext;

            b = bNext;

            t = tNext;

            p = p.multiply(two);
        }
        while (a.subtract(b).abs().compareTo(epsilon) > 0);

        BigDecimal piEstimate = a.add(b).pow(2).divide(t.multiply(four), 50, RoundingMode.HALF_UP);

        return piEstimate;
    }

}
