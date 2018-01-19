package fr.apside.demo.exception;

public class NoAddressRetrievedException extends Exception {

    public NoAddressRetrievedException(String msg) {
        super(msg);
    }

    public NoAddressRetrievedException(Throwable e) {
        super(e);
    }

    public NoAddressRetrievedException(String msg, Throwable e) {
        super(msg, e);
    }

}
