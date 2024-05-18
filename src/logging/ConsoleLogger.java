package logging;

import timing.TimeUnit;

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

    @Override
    public void writeTime(long value, TimeUnit unit)
    {
        switch (unit)
        {
            case TimeUnit.Mili:
            {
                System.out.println(value / 1000000);

                break;
            }
            case TimeUnit.Nano:
            {
                System.out.println(value);

                break;
            }

            case TimeUnit.Micro:
            {
                System.out.println(value / 1000);
                break;
            }

            case TimeUnit.Second:
            {
                System.out.println(value / 1000000000);

                break;
            }
        }
    }

    @Override
    public void writeTime(String str, long value, TimeUnit unit)
    {
        System.out.print(str);

        this.writeTime(value, unit);
    }
}
