package bench.cpu;

import bench.IBenchmark;

import java.text.NumberFormat;

public class CPUDigitsOfPi implements IBenchmark
{
    public void run()
    {

    }

    @Override
    public void run(Object ... params)
    {
        int option = (Integer) params[0];

        switch(option)
        {
            case 0:
            {
                NumberFormat numberFormat = NumberFormat.getInstance();

                numberFormat.setMinimumFractionDigits(10);

                System.out.println("Pi value via Monte Carlo: " +
                        numberFormat.format(PiComputation.MonteCarlo(1000000000L)));

                break;
            }

            case 1:
            {
                NumberFormat numberFormat = NumberFormat.getInstance();

                numberFormat.setMinimumFractionDigits(10);

                System.out.println("Pi value via Gregory Liebniz: " +
                        numberFormat.format(PiComputation.GregoryLiebniz(1000000000L)));

                break;
            }

            case 2:
            {
                NumberFormat numberFormat = NumberFormat.getInstance();

                numberFormat.setMinimumFractionDigits(10);

                System.out.println("Pi value via Gauss Uraganul: " +
                        numberFormat.format(PiComputation.GaussLegandar(0.01)));

                break;
            }

            default:
            {
                throw  new IllegalArgumentException("Invalid option");
            }
        }
    }

    public void initialize(Object... params)
    {

    }

    public  void clean()
    {

    }
    public void cancel()
    {
    }

    public void warmUP()
    {

    }
}
