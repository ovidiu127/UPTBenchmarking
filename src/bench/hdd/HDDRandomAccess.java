package bench.hdd;
import timing.ITimer;
import timing.NanoTimer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

import timing.NanoTimer;
import timing.Timer;
import bench.IBenchmark;

public class HDDRandomAccess implements IBenchmark {

    private final static String PATH = "D:\\test.raf";
    private String result;

    @Override
    public void initialize(Object... params) {
        File tempFile = new File(PATH);
        RandomAccessFile rafFile;
        long fileSizeInBytes = (Long) params[0];

        // Create a temporary file with random content to be used for
        // reading and writing
        try {
            rafFile = new RandomAccessFile(tempFile, "rw");
            Random rand = new Random();
            int bufferSize = 4 * 1024; // 4KB
            long toWrite = fileSizeInBytes / bufferSize;
            byte[] buffer = new byte[bufferSize];
            long counter = 0;

            while (counter++ < toWrite) {
                rand.nextBytes(buffer);
                rafFile.write(buffer);
            }
            rafFile.close();
            tempFile.deleteOnExit();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void warmUP() {
        // have a Mountain Dew or Red Bull
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Use run(Object[]) instead");
    }

    @Override
    public void run(Object ...options) {
        // ex. {"r", "fs", 4*1024}
        Object[] param = (Object[]) options;
        // used by the fixed size option
        final int steps = 25000; // number of I/O ops
        // used by the fixed time option
        final int runtime = 5000; // ms

        try {
            // read benchmark
            if (String.valueOf(param[0]).toLowerCase().equals("r")) {
                // buffer size given as parameter
                int bufferSize = Integer.parseInt(String.valueOf(param[2]));

                // read a fixed size and measure time
                if (String.valueOf(param[1]).toLowerCase().equals("fs")) {

                    long timeMs = new RandomAccess().randomReadFixedSize(PATH,
                            bufferSize, steps);
                    result = steps + " random reads in " + timeMs + " ms ["
                            + (steps * bufferSize / 1024 / 1024) + " MB, "
                            + 1.0 * (steps * bufferSize / 1024 / 1024) / timeMs * 1000 + "MB/s]";
                }
                // read for a fixed time amount and measure time
                else if (String.valueOf(param[1]).toLowerCase().equals("ft")) {

                    int ios = new RandomAccess().randomReadFixedTime(PATH,
                            bufferSize, time);
                    result = ios + " I/Os per second ["
                            + (ios * bufferSize / 1024 / 1024) + " MB, "
                            + 1.0 * (ios * bufferSize / 1024 / 1024) / runtime * 1000 + "MB/s]";
                } else
                    throw new UnsupportedOperationException("Read option \""
                            + String.valueOf(param[1])
                            + "\" is not implemented");

            }
            // write benchmark
            else if (String.valueOf(param[0]).toLowerCase().equals("w")) {
                // your code here: implement all cases for param[[0]: fs, ft, other
            } else
                throw new UnsupportedOperationException("Benchmark option \""
                        + String.valueOf(param[0]) + "\" is not implemented");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clean() {
        // remove created file perhaps?
    }

    @Override
    public void cancel() {
        //
    }

    public String getResult() {
        return String.valueOf(result);
    }

    class RandomAccess {
        private Random random;

        RandomAccess() {
            random = new Random();
        }

        /**
         * Reads data from random positions into a fixed size buffer from a
         * given file using RandomAccessFile
         *
         * @param filePath
         *            Full path to file on disk
         * @param bufferSize
         *            Size of byte buffer to read at each step
         * @param toRead
         *            Number of steps to repeat random read
         * @return Amount of time needed to finish given reads in milliseconds
         * @throws IOException
         */
        public long randomReadFixedSize(String filePath, int bufferSize,
                                        int toRead) throws IOException {
            // file to read from
            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            // size of file
            long fileSize = file.getChannel().size();
            // counter for number of reads
            int counter = 0;
            // buffer for reading
            byte[] bytes = new byte[bufferSize];
            // timer
            ITimer timer = new NanoTimer();

            timer.start();

            Random position = new Random();

            while (counter++ < toRead)
            {
                // go to random spot in file

                int randomPos = position.nextInt() % bufferSize;

                file.seek(randomPos);
                // read the bytes into buffer
                file.read(bytes);
            }

            file.close();

            return timer.stop() / 1000000; // ns to ms!
        }

        /**
         * Reads data from random positions into a fixed size buffer from a
         * given file using RandomAccessFile for one second, or any other given
         * time
         *
         * @param filePath
         *            Full path to file on disk
         * @param bufferSize
         *            Size of byte buffer to read at each step
         * @param millis
         *            Total time to read from file
         * @return Number of reads in the given amount of time
         * @throws IOException
         */
        public int randomReadFixedTime(String filePath, int bufferSize,
                                       int millis) throws IOException {
            // file to read from
            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            // size of file
            long fileSize = file.getChannel().size();
            // counter for number of reads
            int counter = 0;
            // buffer for reading
            byte[] bytes = new byte[bufferSize];

            Random position = new Random();

            long now = System.nanoTime();
            // read for a fixed amount of time
            while (/*elapsed time*/ < /*expected millis*/) {
                // go to random spot in file

                int randomPosition = position.nextInt() % bufferSize;

                file.seek(randomPosition);

                // read the bytes into buffer
                file.read(bytes);

                counter++;
            }

            file.close();
            return counter;
        }

        /**
         * Read data from a file at a specific position
         *
         * @param filePath
         *            Path to file
         * @param position
         *            Position in file
         * @param size
         *            Number of bytes to reads from the given position
         * @return Data that was read
         * @throws IOException
         */
        public byte[] readFromFile(String filePath, int position, int size)
                throws IOException {

            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            file.seek(position);
            byte[] bytes = new byte[size];
            file.read(bytes);
            file.close();
            return bytes;
        }

        /**
         * Write data to a file at a specific position
         *
         * @param filePath
         *            Path to file
         * @param data
         *            Data to be written
         * @param position
         *            Start position in file
         * @throws IOException
         */
        public void writeToFile(String filePath, String data, int position)
                throws IOException {

            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            file.seek(position);
            file.write(data.getBytes());
            file.close();
        }
    }

}
