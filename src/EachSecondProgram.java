import java.util.concurrent.*;

public class EachSecondProgram {
        private static void eachSecondProgram(boolean isFiveSecCounter) {
            int count = 0;
            while (true){
                if (isFiveSecCounter) {
                    if (count % 5 == 0 && count != 0) {
                        System.out.println(count + " sec, " + "5 sec минуло");
                    }
                } else {
                    if (count % 5 != 0) {
                        System.out.println(count + " sec");
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
            }
        }

        public static void timeFromStartProgram() {
            ExecutorService executor = Executors.newFixedThreadPool(2);

            executor.execute(() -> eachSecondProgram(true));
            executor.execute(() -> eachSecondProgram( false));
        }
}
