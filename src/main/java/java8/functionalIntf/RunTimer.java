package java8.functionalIntf;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Util class to calculate the run time of method execution
 */
public class RunTimer {
    private static Map<String,Long> mResultMap = new HashMap<>();

    private static long sStartTime;

    private static long mExecutionTime;

    private static void startTiming(){
        sStartTime = System.nanoTime();
    }

    private static void endTiming(){
        mExecutionTime = (System.nanoTime() - sStartTime) / 1_000_000;
    }

    /**
     * Supplier is the method that we are going to time its execution
     * @param supplier
     * @param testName
     * @param <U>
     * @return
     */
    public static <U> U timeRun(Supplier<U> supplier, String testName){
       startTiming();
       U result = supplier.get();
       endTiming();
        mResultMap.put(testName,mExecutionTime);
        return  result;
    }

    /**
     * Another method that times a thread execution
     */

    public static void timeRun(Runnable runnable,String testName){
        startTiming();
        runnable.run();
        endTiming();
        mResultMap.put(testName,mExecutionTime);
    }

    /**
     *
     * @return
     */
    public static String getTimingResults(){
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Printing ")
                .append(mResultMap.entrySet().size())
                .append(" results from fastest to slowest");


        mResultMap
                .entrySet()
                .stream() //convert entryset into stream
                // converts into a immutable map of time and test, because we want to sort it by time
                .map(entry -> new AbstractMap.SimpleImmutableEntry<>(entry.getValue(),entry.getKey()))
               // .sorted(Comparator.comparing(entry -> entry.getKey()))
                .sorted(Comparator.comparing(AbstractMap.SimpleImmutableEntry::getKey))
                .forEach(entry -> stringBuffer
                        .append("\n")
                        .append(entry.getValue())
                        .append(" executed in ")
                        .append(entry.getKey())
                        .append(" msecs"));
        return stringBuffer.toString();
    }
}
