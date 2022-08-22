package br.com.itau.currencyconverter.converter.currency;

import br.com.itau.currencyconverter.converter.Convertable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CurrencyFactory {
    private final Map<String, CurrencyWithIofTaxableAndOperationalTaxable> convertableMap;

    public CurrencyFactory() {
        this.convertableMap = new HashMap<>();
        this.convertableMap.put("USD", new DollarCurrency());
        this.convertableMap.put("EUR", new EuroCurrency());
        this.convertableMap.put("ARS", new ArgentinePesoCurrency());
        this.convertableMap.put("CLP", new ChileanPesoCurrency());
    }

    public Optional<CurrencyWithIofTaxableAndOperationalTaxable> create(String currency) {
        return Optional.ofNullable(this.convertableMap.get(currency));
    }
}
