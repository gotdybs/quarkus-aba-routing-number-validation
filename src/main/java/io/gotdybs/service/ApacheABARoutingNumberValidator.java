package io.gotdybs.service;

import org.apache.commons.validator.routines.checkdigit.ABANumberCheckDigit;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApacheABARoutingNumberValidator implements ABARoutingNumberValidator {

    @Override
    public boolean isValid(String abaRoutingNumber) {
        return ABANumberCheckDigit.ABAN_CHECK_DIGIT.isValid(abaRoutingNumber);
    }
}
