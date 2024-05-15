package logging;

import timing.TimeUnit;

public interface ILogger {
    void write(long l);

    void write(String s);

    void write(Object ... values);

    void close();

    void writeTime(long value, TimeUnit unit);

    void writeTime(String str, long value, TimeUnit unit);
}
