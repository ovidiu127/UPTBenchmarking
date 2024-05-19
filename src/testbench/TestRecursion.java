package testbench;

import bench.IBenchmark;
import bench.cpu.CPUFixedPoint;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.NanoTimer;
import timing.TimeUnit;

public class TestRecursion
{
    private static double log2(int n)
    {
        return Math.log(n) / Math.log(2);
    }

    private static double log2(long time)
    {
        return Math.log(time) / Math.log(2);
    }

    private static double log2(double arg)
    {
        return Math.log(arg) / Math.log(2);
    }

    public static void main(String[] args)
    {
        ITimer timer = new NanoTimer();

        ILogger log = new ConsoleLogger();

        IBenchmark bench = new CPUFixedPoint();

        int size = 20000000;

        double Score = 0;

        bench.initialize((int)size);
        bench.warmUP();

        int unroll = 0;

        timer.start();
        bench.run(false);
        long time = timer.stop();

        log.writeTime("Run " + unroll + " unroll ", time, TimeUnit.Micro);
        Score = log2(size/log2(time));
        System.out.println("Score for this run: " + Score);

        unroll = 1;
        timer.start();
        bench.run(true,unroll);
        time = timer.stop();
        log.writeTime("Run " + unroll + " unroll ", time, TimeUnit.Micro);
        Score = log2(size/log2(time));
        System.out.println("Score for this run: " + Score);

        unroll = 5;
        timer.start();
        bench.run(true,unroll);
        time = timer.stop();
        log.writeTime("Run " + unroll + " unroll ", time, TimeUnit.Micro);
        Score = log2(size/log2(time));
        System.out.println("Score for this run: " + Score);

        unroll = 15;
        timer.start();
        bench.run(true,unroll);
        time = timer.stop();
        log.writeTime("Run " + unroll + " unroll ", time, TimeUnit.Micro);
        Score = log2(size/log2(time));
        System.out.println("Score for this run: " + Score);

        unroll = 200;
        timer.start();
        bench.run(true,unroll);
        time = timer.stop();
        log.writeTime("Run " + unroll + " unroll ", time, TimeUnit.Micro);
        Score = log2(size/log2(time));
        System.out.println("Score for this run: " + Score);
    }
}
