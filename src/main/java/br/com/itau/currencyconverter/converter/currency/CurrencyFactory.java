package br.com.itau.currencyconverter.converter.currency;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CurrencyFactory {
    private final Map<String, Convertable> convertableMap;

    public CurrencyFactory() {
        this.convertableMap = new HashMap<>();
        this.convertableMap.put("1", new EUR());
        this.convertableMap.put("2", new USD());
        this.convertableMap.put("3", new ARS());
        this.convertableMap.put("4", new CLP());
    }

    public Optional<Convertable> create(String currency) {
        return Optional.ofNullable(this.convertableMap.get(currency));
    }
}
