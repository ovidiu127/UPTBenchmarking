package timing;

public class NanoTimer implements ITimer {
    private long time,elapsed;
    private boolean paused,started;

    NanoTimer(){
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
        elapsed += System.nanoTime() - time;
        started = false;
        return elapsed;
    }

    public void resume(){
        time = System.nanoTime();
        paused = false;
    }

    public long pause(){
        if(paused){
            throw new RuntimeException("Timer was already paused!");
        }

        paused = true;
        return stop();
    }
}
