package casim.utils;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractWorker implements Runnable {
    
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean stopped = new AtomicBoolean(false);
    private Thread thread;
    
    
    public void start() {
        thread = new Thread(this);
        this.running.set(true);
        thread.start();
    }

    public void stop() {
        this.running.set(false);
    }

    public boolean isRunning() {
        return this.running.get();
    }

    public boolean isStopped() {
        return this.stopped.get();
    }

    @Override
    public abstract void run();
    
    protected void setStopped(final boolean value) {
        this.stopped.set(value);
    }
}
