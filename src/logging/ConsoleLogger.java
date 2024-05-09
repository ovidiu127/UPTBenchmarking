package logging;

public class ConsoleLogger implements ILogger {
    public void write(long l){
        System.out.println(l);
    }

    public void write(String s){
        System.out.println(s);
    }

    public void write(Object ... values){
        for(Object o : values){
            System.out.print(o.toString());
        }
        System.out.println();
    }

    public void close(){

    }
}
