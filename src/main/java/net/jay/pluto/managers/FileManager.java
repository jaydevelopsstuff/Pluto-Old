package net.jay.pluto.managers;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class FileManager implements Manager {
    private final Executor executor;

    public FileManager() {
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void write(String content, String file) {
        write(content, new File(file));
    }

    public void write(String content, File file) {
        executor.execute(() -> {
            try {
                FileWriter writer = new FileWriter(file, true);
                writer.write(content);
                writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void overwrite(String content, String file) {
        overwrite(content, new File(file));
    }

    public void overwrite(String content, File file) {
        executor.execute(() -> {
            try {
                FileWriter writer = new FileWriter(file, false);
                writer.write(content);
                writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void clear(String file) {
        clear(new File(file));
    }

    public void clear(File file) {
        executor.execute(() -> {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(file);
            } catch(FileNotFoundException e) {
                e.printStackTrace();
            }
            if(writer == null) return;
            writer.print("");
            writer.close();
        });
    }

    public void delete(String file) {
        delete(new File(file));
    }

    public void delete(File file) {
        executor.execute(() -> {
            file.delete();
        });
    }

    public void write(String content, String file, Consumer<Boolean> callback) {
        write(content, new File(file), callback);
    }

    public void write(String content, File file, Consumer<Boolean> callback) {
        executor.execute(() -> {
            try {
                FileWriter writer = new FileWriter(file, true);
                writer.write(content);
                writer.close();
                callback.accept(true);
            } catch(IOException e) {
                e.printStackTrace();
                callback.accept(false);
            }
        });
    }

    public void overwrite(String content, String file, Consumer<Boolean> callback) {
        overwrite(content, new File(file), callback);
    }

    public void overwrite(String content, File file, Consumer<Boolean> callback) {
        executor.execute(() -> {
            try {
                FileWriter writer = new FileWriter(file, false);
                writer.write(content);
                writer.close();
                callback.accept(true);
            } catch(IOException e) {
                e.printStackTrace();
                callback.accept(false);
            }
        });
    }

    public void clear(String file, Consumer<Boolean> callback) {
        clear(new File(file), callback);
    }

    public void clear(File file, Consumer<Boolean> callback) {
        executor.execute(() -> {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(file);
            } catch(FileNotFoundException e) {
                e.printStackTrace();
            }
            if(writer == null) {
                callback.accept(false);
                return;
            }
            writer.print("");
            writer.close();
            callback.accept(true);
        });
    }

    public void delete(String file, Consumer<Boolean> callback) {
        delete(new File(file), callback);
    }

    public void delete(File file, Consumer<Boolean> callback) {
        executor.execute(() -> {
            callback.accept(file.delete());
        });
    }

    @Override
    public void shutdown() {

    }
}
