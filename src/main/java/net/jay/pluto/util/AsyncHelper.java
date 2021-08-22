package net.jay.pluto.util;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

// There's probably a much better way to do this, may change in the future
public class AsyncHelper<T> {
    public static final AsyncHelper<Byte> byteHelper = new AsyncHelper<>();
    public static final AsyncHelper<Short> shortHelper = new AsyncHelper<>();
    public static final AsyncHelper<Integer> intHelper = new AsyncHelper<>();
    public static final AsyncHelper<Long> longHelper = new AsyncHelper<>();
    public static final AsyncHelper<Float> floatHelper = new AsyncHelper<>();
    public static final AsyncHelper<Double> doubleHelper = new AsyncHelper<>();
    public static final AsyncHelper<Boolean> booleanHelper = new AsyncHelper<>();
    public static final AsyncHelper<String> stringHelper = new AsyncHelper<>();

    public static void runAsync(Runnable task, Runnable callback) {
        CompletableFuture.runAsync(() -> {
           task.run();
           callback.run();
        });
    }

    public void runAsync(Supplier<T> task, Consumer<T> callback) {
        CompletableFuture.runAsync(() -> {
            callback.accept(task.get());
        });
    }
}
