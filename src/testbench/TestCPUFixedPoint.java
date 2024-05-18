package testbench;

import bench.IBenchmark;
import bench.cpu.CPUFixedPoint;
import bench.cpu.CPUFixedVsFloatingPoint;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.NanoTimer;
import timing.TimeUnit;
import utilities.NumberRepresentaion;

public class TestCPUFixedPoint {
    public static void main(String[] args) {
        ITimer timer = new NanoTimer();

        ILogger log = new ConsoleLogger();

        IBenchmark bench = new CPUFixedPoint();

        int size = 100000;

        bench.initialize((int)size);
        bench.warmUP();

        timer.start();

        bench.run();

        long time = timer.stop();

        log.writeTime("Run ", time, TimeUnit.Micro);
        log.write("MOPS: " + (29 + 11 + 6) * size / (time / 1000));
    }
}
