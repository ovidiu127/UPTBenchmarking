package testbench;

import bench.IBenchmark;

import bench.hdd.HDDRandomAccess;

public class TestHDDRandomAccess {
    public static void main(String[] args) {
        HDDRandomAccess hddRandomAccess = new HDDRandomAccess();

        hddRandomAccess.run("r", "fs", 4 * 1024);

        hddRandomAccess.run("r", "ft",  512);

        hddRandomAccess.run("w", "fs", 4 * 1024);

        hddRandomAccess.run("w", "ft",  512);
        hddRandomAccess.run("w", "ft", 4 * 1024);
        hddRandomAccess.run("w", "ft", 64 * 1024);
        hddRandomAccess.run("w", "ft", 1 * 1024 * 1024);
    }
}
