package br.com.itau.currencyconverter.converter.currency;

import java.math.BigDecimal;

public final class EUR extends CurrencyWithIofTaxableAndOperationalTaxable {
    private final BigDecimal CONVERT_PRICE = new BigDecimal("0.20");
    private final BigDecimal FIX_TAX = new BigDecimal("6.00");

    @Override
    public BigDecimal doConvert(BigDecimal value) {
        return value.multiply(CONVERT_PRICE);
    }

    @Override
    public BigDecimal calculateOperationalTax(BigDecimal value) {
        return FIX_TAX;
    }
}
