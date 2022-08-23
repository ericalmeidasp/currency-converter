package br.com.itau.currencyconverter.converter.currency;

import java.math.BigDecimal;

public final class ARS extends CurrencyWithIofTaxableAndOperationalTaxable {
    private final BigDecimal CONVERT_PRICE = new BigDecimal("26.24");
    private final BigDecimal FIX_FEE = new BigDecimal("2.50");
    private final BigDecimal PERCENTAGE_TAX_IN_DECIMAL = new BigDecimal("0.015");

    @Override
    public BigDecimal doConvert(BigDecimal value) {
        return value.multiply(CONVERT_PRICE);
    }

    @Override
    public BigDecimal calculateOperationalTax(BigDecimal value) {
        BigDecimal valuePercentagemTax = value.multiply(PERCENTAGE_TAX_IN_DECIMAL);
        return FIX_FEE.add(valuePercentagemTax);
    }
}
