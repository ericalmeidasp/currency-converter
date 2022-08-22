package br.com.itau.currencyconverter.converter.currency;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class CLPTest {

    CurrencyWithIofTaxableAndOperationalTaxable converter = new CLP();

    @Test
    void doConvertDeveriaRetornar17417ClpPara100Reais() {

        BigDecimal value = converter.doConvert(new BigDecimal("100"));

        assertEquals(new BigDecimal("17417.00"), value);
    }

    @Test
    void calculateOperationalTax() {
        BigDecimal value = converter.calculateOperationalTax(new BigDecimal("100"));

        assertEquals(new BigDecimal("12.50"), value.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void convertDeveriaRetornar1504828para100reais() {
        BigDecimal value = converter.convert(new BigDecimal("100"));

        assertEquals(new BigDecimal("15048.28"), value);
    }

    @Test
    void convertDeveriaRetornarIllegalArgumentExceptionParaValorMenorQue10Reais() {

        assertThrows(IllegalArgumentException.class, () -> {
            BigDecimal value = converter.convert(new BigDecimal("10"));
            System.out.println(value);
        });

    }

    @Test
    void calculateIofDeveriaRetornar110ReaisPara100Reais() {
        BigDecimal value = converter.calculateIof(new BigDecimal("100"));

        assertEquals(new BigDecimal("1.10"), value.setScale(2, RoundingMode.HALF_UP));
    }
}