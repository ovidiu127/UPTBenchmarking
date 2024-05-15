package bench;

import java.util.Random;

public class DemoBenchmark implements IBenchmark
{
    private int array[];

    private int n;

    private boolean running;

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(this.n);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        for(int i = 0; i < this.n - 1 && running; ++ i)
        {
            for(int j = i + 1; j < this.n && running; ++ j)
            {
                if(this.array[i] > this.array[j])
                {
                    utilities.Utility.swap(this.array, i, j);
                }
            }
        }
    }

    @Override
    public void run(Object ... params)
    {
        System.out.println("luati aminte!");

        System.out.println("spor la cantari!");

        System.out.println("ALL MY NIGGAS HATE JAVA");
    }

    @Override
    public void initialize(Object... params)
    {
        this.n = (int) params[0];

        this.array = new int[n];

        Random random = new Random();

        for(int i = 0; i < this.n; ++ i)
        {
            this.array[i] = random.nextInt(1000);
        }

        this.running = true;
    }

    @Override
    public void clean()
    {
        for(int i = 0; i < this.n; ++ i)
        {
            System.out.printf("%d ", this.array[i]);
        }
        this.array = null;
    }

    @Override
    public void cancel()
    {
        this.running = false;

        System.out.println("You got canceled");
    }
}
