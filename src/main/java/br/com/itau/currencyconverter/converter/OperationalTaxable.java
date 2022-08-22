package br.com.itau.currencyconverter.converter;

import java.math.BigDecimal;

public interface OperationalTaxable {
    BigDecimal calculateOperationalTax(BigDecimal value);
}
