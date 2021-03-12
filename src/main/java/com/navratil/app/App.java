package com.navratil.app;

/**
 * App class.
 *
 * @author navratil
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        DeliveryOffice deliveryOffice = new DeliveryOffice();

        Thread packageReader = new PackageReader(deliveryOffice);
        Thread packageWriter = new PackageWriter(deliveryOffice);

        packageReader.start();
        packageWriter.start();

        try {
            packageReader.join();
        } catch (InterruptedException e) { }

        ((PackageWriter) packageWriter).doQuit();

        try {
            packageWriter.join();
        } catch (InterruptedException e) { }
    }
}
