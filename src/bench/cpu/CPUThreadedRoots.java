package bench.cpu;

import bench.IBenchmark;

import javax.swing.plaf.TableHeaderUI;

public class CPUThreadedRoots implements IBenchmark
{
    private double result;
    private int size;
    private boolean running;

    private int nThreads;

    @Override
    public void initialize(Object... params)
    {
        // save size from params array

        this.size = (int) params[0];
    }

    @Override
    public void warmUP()
    {
        // call run method: call run() once
        // detect number of cores: Runtime.....availableProcessors();
        run(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Objects...) instead");
    }

    @Override
    public void run(Object... options)
    {
        // options[0] -> number of threads
        // ...

        this.nThreads = (int) options[0];

        Thread[] threads = new Thread[nThreads];

        // e.g. 1 to 10,000 on 4 threads = 2500 jobs per thread
        final int jobPerThread = this.size / this.nThreads;

        running = true; // flag used to stop all started threads
        // create a thread for each runnable (SquareRootTask) and start it
        for (int i = 0; i < nThreads; ++i)
        {
            threads[i] = new Thread(new SquareRootTask(i * jobPerThread, (i + 1) * jobPerThread));

            threads[i].start();
        }

        // join threads
        for (int i = 0; i < nThreads; ++i)
        {
            try
            {
                threads[i].join();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }

//        System.out.println("The final result: " + this.result);
    }

    @Override
    public void clean() {
        // only implement if needed
    }

    public void cancel()
    {
        this.running = false;
    }

//    @Override
    public String getResult() {
        return String.valueOf(result);
    }

    private synchronized void addResults(double partials)
    {
        this.result += partials;
    }

    class SquareRootTask implements Runnable {

        private int from, to;
        private final double precision = 1e-4; // fixed
        private double result = 0.0;

        public SquareRootTask(int from, int to) {
            // save params to class members
            this.from = from;

            this.to = to;
        }

        @Override
        public void run() {
            // compute Newtonian square root on each number from i = 'from' to 'to', and also check 'running'
            // save (+=) each computed square root in the local 'result' variable
            // extra: send 'result' back to main thread and sum up with all results
            for(int i = from; i  <= to; ++ i)
            {
                this.result += getNewtonian(i);
            }

            addResults(this.result);
        }

        private double getNewtonian(double x) {
            // ... implement the algorithm for Newton's square root(x) here
            double s = x;

            while(Math.abs(s * s - x) > precision)
            {
                s = (x / s + s) / 2;
            }

            return s;
        }

        // extra: compute sum, pass it back to wrapper class. Use synchronized
    }
}
