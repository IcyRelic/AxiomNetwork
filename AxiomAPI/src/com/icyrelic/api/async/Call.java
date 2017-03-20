package com.icyrelic.api.async;

import com.icyrelic.api.AxiomAPI;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author IcyRelic
 */
public abstract class Call<T> implements Callable<T> {

    public T getBack(){
        return backCall();
    }

    public T getBackAsync(){
        T object = null;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<T>> list = new ArrayList<>();
        list.add(executor.submit(this));
        for (Future<T> future : list){
            try {
                object = future.get();
            } catch (InterruptedException | ExecutionException e) {
                AxiomAPI.Instance.sendConsoleMessage(ChatColor.RED + "Failed to execute task Async.");
            }
        }
        return object;
    }

    private T backCall(){
        T type = null;
        try {
            type = call();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return type;
    }

}
