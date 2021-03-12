package com.navratil.app;

/**
 * PackageWriter class.
 *
 * @author navratil
 */
public class PackageWriter extends Thread {
    private static final long WAIT_TIME = 60L * 1000L;
    private DeliveryOffice deliveryOffice;
    private boolean isStopped;

    public PackageWriter(DeliveryOffice deliveryOffice) {
        this.deliveryOffice = deliveryOffice;
    }

    public synchronized void doQuit() {
        this.isStopped = true;
    }

    private synchronized boolean isRunning() {
        return !this.isStopped;
    }

    @Override
    public void run() {
        while (isRunning()) {
            try {
                Thread.sleep(WAIT_TIME);
                synchronized (deliveryOffice) {
                    System.out.println(deliveryOffice.listPackages());
                }
            } catch (InterruptedException e) { }
        }
    }
}
