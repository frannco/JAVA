package com.navratil.app;

/**
 * PackageParseException class.
 *
 * @author navratil
 */
public class PackageParseException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -3340153438529836503L;

    public PackageParseException(String errorMessage) {
        super(errorMessage);
    }
}
