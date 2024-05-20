package testbench;

import bench.IBenchmark;
import bench.hdd.HDDWriteSpeed;
import logging.ConsoleLogger;
import timing.ITimer;
import timing.NanoTimer;
import timing.TimeUnit;

public class TestHDDWriteSpeed {
    public static void main(String[] args) {
        IBenchmark bench = new HDDWriteSpeed();

        ConsoleLogger log = new ConsoleLogger();

        bench.run("fb", true);
    }
}
