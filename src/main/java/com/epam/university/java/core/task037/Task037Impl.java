package com.epam.university.java.core.task037;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task037Impl implements Task037 {
    /**
     * Implement wall watches using concurrency.
     *
     * @param ticker produces "tick" string
     * @param tacker produces "tack" string
     * @return collection of tick-tack's
     */
    @Override
    public Collection<String> switcher(Callable<String> ticker,
                                       Callable<String> tacker) {
        List<String> res = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            for (int i = 0; i < 5; i++) {
                res.add(executorService.submit(ticker).get());
                res.add(executorService.submit(tacker).get());
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        return res;
    }
}
