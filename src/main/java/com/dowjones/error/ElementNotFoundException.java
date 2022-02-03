package com.dowjones.error;

public class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException(Long id) {
        super("Element id not found : " + id);
    }

}
