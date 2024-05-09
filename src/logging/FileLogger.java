package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements ILogger {
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

    public void write(String s){
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
}
