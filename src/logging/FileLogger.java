package logging;

import timing.TimeUnit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements ILogger
{
    private FileWriter fw;

    public FileLogger(String path){
        try{
            fw = new FileWriter(path);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void write(long l){
        try {
            fw.write(l + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String s)
    {
        try{
            fw.write(s + "\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void write(Object ... values){
        try {
            for(Object o : values) {
                fw.write(o.toString());
            }
            fw.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void writeTime(long value, TimeUnit unit)
    {
        long internationalSystem = value;

        switch (unit)
        {
            case TimeUnit.Mili:
            {
                internationalSystem = (value / 1000000);

                break;
            }

            case TimeUnit.Micro:
            {
                internationalSystem = (value / 1000);
                break;
            }

            case TimeUnit.Second:
            {
                internationalSystem = (value / 1000000000);

                break;
            }
        }

        try
        {
            fw.write(internationalSystem + "");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeTime(String str, long value, TimeUnit unit)
    {
        try
        {
            fw.write(str);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        this.writeTime(value, unit);
    }
}
