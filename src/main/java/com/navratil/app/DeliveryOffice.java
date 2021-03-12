package com.navratil.app;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * DeliveryOffice class.
 *
 * @author navratil
 */
public class DeliveryOffice {
    private static final int WEIGHT_PRECISSION = 3;
    private Map<String, Float> packages;

    public DeliveryOffice() {
        packages = new TreeMap<String, Float>();
    }

    /**
     * add package function.
     * @param line
     * @throws Exception
     */
    public void addPackage(String line) throws PackageParseException, NumberFormatException {
        String[] token = line.split(" ");
        if (token.length != 2) {
            throw new PackageParseException("Wrong number of tokens");
        }

        Float weight = Float.parseFloat(token[0]);
        if (weight < 0) {
            throw new PackageParseException("Negative value of weight");
        }
        if ((token[0].length() - token[0].indexOf(".") - 1) > WEIGHT_PRECISSION) {
            throw new PackageParseException("Too many decimal points in weight (>3)");
        }

        if (!token[1].matches("\\d{5}")) {
            throw new PackageParseException("Wrong format of postal code");
        }
        String postalCode = token[1];

        Float oldWeight = packages.get(postalCode);
        if (oldWeight == null) {
            packages.put(postalCode, weight);
        } else {
            packages.replace(postalCode, oldWeight + weight);
        }
    }

    /**
     * list packages function.
     * @return
     */
    public String listPackages() {
        String output = "";
        for (Map.Entry<String, Float> entry : packages.entrySet()) {
            output += String.format(Locale.ROOT, "%s %.3f\n", entry.getKey(), entry.getValue());
        }
        return output;
    }
}
