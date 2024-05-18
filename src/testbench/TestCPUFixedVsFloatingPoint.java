package testbench;

import bench.IBenchmark;
import bench.cpu.CPUDigitsOfPi;
import bench.cpu.CPUFixedVsFloatingPoint;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.NanoTimer;
import timing.TimeUnit;
import utilities.NumberRepresentaion;

public class TestCPUFixedVsFloatingPoint {
    public static void main(String[]args){
        ITimer timer = new NanoTimer();

        ILogger log = new ConsoleLogger();

        IBenchmark bench = new CPUFixedVsFloatingPoint();

        bench.initialize((int)10000);
        bench.warmUP();

        timer.start();

        bench.run(NumberRepresentaion.FIXED);

        long time = timer.stop();

        log.writeTime("Run ", time, TimeUnit.Micro);
    }
}
