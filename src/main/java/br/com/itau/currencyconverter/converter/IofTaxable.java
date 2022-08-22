package br.com.itau.currencyconverter.converter;

import java.math.BigDecimal;

public interface IofTaxable {
    BigDecimal calculateIof(BigDecimal value);
}
