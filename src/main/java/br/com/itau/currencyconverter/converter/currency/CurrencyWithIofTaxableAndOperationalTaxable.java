package br.com.itau.currencyconverter.converter.currency;

import br.com.itau.currencyconverter.converter.Convertable;
import br.com.itau.currencyconverter.converter.IofTaxable;
import br.com.itau.currencyconverter.converter.OperationalTaxable;

import java.math.BigDecimal;

public abstract class CurrencyWithIofTaxableAndOperationalTaxable implements Convertable, IofTaxable, OperationalTaxable {
    private final BigDecimal IOF_TAX = new BigDecimal("0.011");

    @Override
    public BigDecimal convert(BigDecimal valueForConvert) {
        BigDecimal valueOfIof = calculateIof(valueForConvert);
        BigDecimal valueOfOperationalTax = calculateOperationalTax(valueForConvert);

        BigDecimal valueAfterCalculateIof = valueForConvert.subtract(valueOfIof);
        BigDecimal valueAfterCalculateOperationalTax = valueAfterCalculateIof.subtract(valueOfOperationalTax);

        return doConvert(valueAfterCalculateOperationalTax);
    }

    public abstract BigDecimal doConvert(BigDecimal value);

    @Override
    public BigDecimal calculateIof(BigDecimal value) {
        return value.multiply(IOF_TAX);
    }

    @Override
    public abstract BigDecimal calculateOperationalTax(BigDecimal value);
}
