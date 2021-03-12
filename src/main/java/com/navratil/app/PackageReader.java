package com.navratil.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * PackageReader class.
 *
 * @author navratil
 */
public class PackageReader extends Thread {
    private DeliveryOffice deliveryOffice;

    public PackageReader(DeliveryOffice deliveryOffice) {
        this.deliveryOffice = deliveryOffice;
    }

    /**
     * run thread.
     */
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                String line = br.readLine();
                if ("quit".equals(line)) {
                    break;
                }

                try {
                    synchronized (deliveryOffice) {
                        deliveryOffice.addPackage(line);
                    }
                } catch (PackageParseException | NumberFormatException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
