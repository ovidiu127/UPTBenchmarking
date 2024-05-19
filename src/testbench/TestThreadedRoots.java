package testbench;

import bench.IBenchmark;
import bench.cpu.CPUThreadedRoots;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.NanoTimer;
import timing.TimeUnit;

import java.awt.image.ConvolveOp;

public class TestThreadedRoots {
    public static void main(String[] args) {
        ITimer timer = new NanoTimer();

        ILogger log = new ConsoleLogger();

        TimeUnit timeUnit = TimeUnit.Mili;

        IBenchmark bench = new CPUThreadedRoots();

        int workload = 100000000;

        bench.initialize(workload);

        bench.warmUP();

        for(int i = 1; i <= 20000; i *= 2)
        {
            timer.start();

            bench.run(i);

            long time = timer.stop();

            log.writeTime("[t = " + i + "] Finished in ", time, timeUnit);

            double Score = (double) workload / (time * i);

            System.out.println("Score: " + Score + " for threads: " + i);
        }

        bench.clean();

        log.close();
    }
}
