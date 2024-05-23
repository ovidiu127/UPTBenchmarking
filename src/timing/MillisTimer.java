package timing;

public class MillisTimer implements ITimer {
    private long time,elapsed;
    private boolean paused,started;

    MillisTimer(){
        paused = false;
        started = false;
    }

    public void start(){
        if(started){
            throw new RuntimeException("Timer was already started!");
        }
        started = true;

        elapsed = 0;
        time = System.nanoTime();
    }

    public long stop(){
        elapsed += System.currentTimeMillis() - time;
        started = false;
        return elapsed;
    }

    public void resume(){
        time = System.currentTimeMillis();
        paused = false;
    }

    public long pause(){
        if(paused){
            throw new RuntimeException("Timer was already paused!");
        }

        paused = true;
        return stop();
    }

    @Override
    public long getTime()
    {
        return 0;
    }

}
