package testbench;

import bench.IBenchmark;
import bench.cpu.CPUThreadingHasing;
import logging.ConsoleLogger;
import timing.ITimer;
import timing.NanoTimer;
import timing.TimeUnit;

import java.util.Timer;

public class TestCPUThreadedHashing {
    public static void main(String[] args) {
        IBenchmark bench = new CPUThreadingHasing();

        ConsoleLogger log = new ConsoleLogger();

        int maxLength = 10;

        int nThreads = 8;

        int hashCode = 524381996;

        ITimer timer = new NanoTimer();

        timer.start();

        bench.run(maxLength, nThreads, hashCode);

        long time = timer.stop();

        log.writeTime("finished in ", time, TimeUnit.Mili);

        log.write("Result is ", ((CPUThreadingHasing)bench).getResult());
    }
}
