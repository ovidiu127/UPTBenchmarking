package bench.cpu;

import bench.IBenchmark;

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
//                computePiMagically();
                break;
            }

            case 1:
            {
//                computePiByGuessing();

                break;
            }

            case 2:
            {
//                computePiUsingMaths();

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
