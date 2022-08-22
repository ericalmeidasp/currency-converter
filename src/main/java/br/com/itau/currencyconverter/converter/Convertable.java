package br.com.itau.currencyconverter.converter;

import java.math.BigDecimal;

public interface Convertable {
    BigDecimal convert(BigDecimal value);
}
