package br.com.itau.currencyconverter.converter.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class CurrencyWithIofTaxableAndOperationalTaxable implements Convertable, IofTaxable, OperationalTaxable, Validatable<BigDecimal> {
    private final BigDecimal IOF_TAX = new BigDecimal("0.011");

    @Override
    public BigDecimal convert(BigDecimal valueForConvert) {
        BigDecimal valueOfIof = calculateIof(valueForConvert);
        BigDecimal valueOfOperationalTax = calculateOperationalTax(valueForConvert);

        BigDecimal totalTaxs = valueOfIof.add(valueOfOperationalTax);

        if (isValid(valueForConvert, totalTaxs)) {
            BigDecimal valueAfterCalculateIof = valueForConvert.subtract(valueOfIof);
            BigDecimal valueAfterCalculateOperationalTax = valueAfterCalculateIof.subtract(valueOfOperationalTax);
            return doConvert(valueAfterCalculateOperationalTax).setScale(2, RoundingMode.DOWN);
        } else
            throw new IllegalArgumentException("Não é possível converter, valor menor que as taxas incidentes.");
    }

    public abstract BigDecimal doConvert(BigDecimal value);

    @Override
    public BigDecimal calculateIof(BigDecimal value) {
        return value.multiply(IOF_TAX);
    }

    @Override
    public abstract BigDecimal calculateOperationalTax(BigDecimal value);

    @Override
    public boolean isValid(BigDecimal valueForConvert, BigDecimal totalTaxes) {
        return valueForConvert.compareTo(totalTaxes) > 0;
    }
}