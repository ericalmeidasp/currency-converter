package br.com.itau.currencyconverter.converter.currency;

import java.math.BigDecimal;

public interface Convertable {
    BigDecimal convert(BigDecimal value);
}
