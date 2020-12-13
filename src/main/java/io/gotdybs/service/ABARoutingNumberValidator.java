package io.gotdybs.service;

@FunctionalInterface
public interface ABARoutingNumberValidator {

    boolean isValid(String abaRoutingNumber);

}