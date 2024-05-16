package testbench;

import bench.DemoBenchmark;
import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.NanoTimer;
import timing.TimeUnit;

public class TestCPUDigitsOfPi
{
    public static void main(String[] args)
    {
        ITimer timer = new NanoTimer();

        ILogger log = new ConsoleLogger();

        IBenchmark bench = new DemoBenchmark();

        final int workload = 10000;

        bench.initialize(workload);

        for(int i = 0; i < 12; ++ i)
        {
            timer.start();

            bench.run();

            long time = timer.stop();

            log.writeTime("Run ", time, TimeUnit.Nano);
        }

        log.write("Finished in: ", timer.stop());

        log.close();

        bench.clean();
    }
}
