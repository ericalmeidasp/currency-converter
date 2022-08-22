package br.com.itau.currencyconverter.converter.currency;

import java.math.BigDecimal;

public interface IofTaxable {
    BigDecimal calculateIof(BigDecimal value);
}
