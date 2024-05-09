import logging.ConsoleLogger;
import logging.FileLogger;
import logging.ILogger;

public class Main {
    public static void main(String[] args) {
        ILogger log = new FileLogger("logs.txt");
        log.write("Test");
        log.write(11);
        log.write("Ovidiu " + 11 + ":" + 27);
        log.close();

        ILogger log1 = new ConsoleLogger();
        log1.write("Hello");
        log1.write("Ovidiu " + 11 + ":" + 27);
    }
}