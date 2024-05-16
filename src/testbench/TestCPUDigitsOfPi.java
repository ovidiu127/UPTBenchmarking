package testbench;

import bench.IBenchmark;
import bench.cpu.CPUDigitsOfPi;
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

        IBenchmark bench = new CPUDigitsOfPi();

        bench.initialize();

        timer.start();

        bench.run(2);

        long time = timer.stop();

        log.writeTime("Run ", time, TimeUnit.Nano);

        log.write("Finished in: ", timer.stop());

        log.close();

        bench.clean();
    }
}
