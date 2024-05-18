package testbench;

import bench.IBenchmark;
import bench.cpu.CPUFixedPoint;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.NanoTimer;
import timing.TimeUnit;

public class TestRecursion {
    public static void main(String[] args) {
        ITimer timer = new NanoTimer();

        ILogger log = new ConsoleLogger();

        IBenchmark bench = new CPUFixedPoint();

        int size = 20000000;

        bench.initialize((int)size);
        bench.warmUP();

        int unroll = 0;

        timer.start();
        bench.run(false);
        long time = timer.stop();
        log.writeTime("Run " + unroll + " unroll ", time, TimeUnit.Micro);

        unroll = 1;
        timer.start();
        bench.run(true,unroll);
        time = timer.stop();
        log.writeTime("Run " + unroll + " unroll ", time, TimeUnit.Micro);

        unroll = 5;
        timer.start();
        bench.run(true,unroll);
        time = timer.stop();
        log.writeTime("Run " + unroll + " unroll ", time, TimeUnit.Micro);

        unroll = 15;
        timer.start();
        bench.run(true,unroll);
        time = timer.stop();
        log.writeTime("Run " + unroll + " unroll ", time, TimeUnit.Micro);

        unroll = 200;
        timer.start();
        bench.run(true,unroll);
        time = timer.stop();
        log.writeTime("Run " + unroll + " unroll ", time, TimeUnit.Micro);

    }
}
