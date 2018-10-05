package org.plc.pocker.exceptions;

public class ExceededCardsException extends Throwable {
    public ExceededCardsException() {
        super("Only five cards by hand, currently there are 6");
    }
}
