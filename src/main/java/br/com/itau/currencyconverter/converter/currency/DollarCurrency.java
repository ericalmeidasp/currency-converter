package br.com.itau.currencyconverter.converter.currency;

import java.math.BigDecimal;

public final class DollarCurrency extends CurrencyWithIofTaxableAndOperationalTaxable {
    private final BigDecimal CONVERT_PRICE = new BigDecimal("0.19");
    private final BigDecimal FIX_TAX = new BigDecimal("5.00");
    private final BigDecimal PERCENTAGE_TAX_IN_DECIMAL = new BigDecimal("0.03");

    @Override
    public BigDecimal doConvert(BigDecimal value) {
        return value.multiply(CONVERT_PRICE);
    }

    @Override
    public BigDecimal calculateOperationalTax(BigDecimal value) {
        BigDecimal valuePercentagemTax = value.multiply(PERCENTAGE_TAX_IN_DECIMAL);
        return FIX_TAX.add(valuePercentagemTax);
    }
}
