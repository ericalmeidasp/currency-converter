package br.com.itau.currencyconverter.converter.currency;

public interface Validatable<T> {
    boolean isValid(T value, T taxs);
}
