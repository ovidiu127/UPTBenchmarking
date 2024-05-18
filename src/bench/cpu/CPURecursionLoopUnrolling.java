package bench.cpu;

import java.awt.desktop.PreferencesEvent;

public class CPURecursionLoopUnrolling {
    private int size;

    public void run(){

    }

    public static boolean isPrime(long n) {
        // Handle base cases
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        // Check divisibility from 5 onwards, skipping even numbers
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    private long recursive(long start, long size, int counter){
        if(start >= size){
            return 0;
        }

        long ans = 0;
        if(isPrime(start)) ans = 1;

        long next;
        try{
            next = recursive(start + 1,size,counter + 1);
        }catch (StackOverflowError e) {
            System.out.println("Reached nr " + start + "/" + size + " after " + counter + " calls.");
            return 0;
        }

        return ans + next;
    }

    private long recursiveUnrolled(long start, int unrollLevel, int size, int counter){
        if(start >= size){
            return 0;
        }

        long ans = 0;
        for(int u = 0;u < unrollLevel;++u){
            if(isPrime(start + u)) ++ans;
        }

        long next;
        try{
            next = recursiveUnrolled(start + 1,unrollLevel,size,counter + 1);
        }catch (StackOverflowError e) {
            System.out.println("Reached nr " + start + "/" + size + " after " + counter + " calls.");
            return 0;
        }

        return ans + next;
    }

    public void run(Object ... params){
        boolean mode = (Boolean)params[0];
        int factor;

        if(mode){
            factor = (Integer)params[1];
            recursiveUnrolled(2,factor,this.size,0);
        }
        else{
            recursive(2,this.size,0);
        }
    }

    public void initialize(Object... params){
        this.size = (Integer)params[0];
    }

    public void clean(){

    }

    public void cancel(){

    }

    public void warmUP(){

    }
}
