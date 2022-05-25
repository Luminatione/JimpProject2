package JimpProject2.utility;

import java.util.function.Consumer;

public class Logger
{
    private static Consumer<String> output = null;
    public static void setOutput(Consumer<String> consoleAppend)
    {
        output = consoleAppend;
    }
    public static void log(String message)
    {
        output.accept(message);
    }
}
