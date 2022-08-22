package br.com.itau.currencyconverter.converter.currency;

import java.math.BigDecimal;

public interface OperationalTaxable {
    BigDecimal calculateOperationalTax(BigDecimal value);
}
