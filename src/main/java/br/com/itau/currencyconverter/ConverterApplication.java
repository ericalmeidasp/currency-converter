package br.com.itau.currencyconverter;

import br.com.itau.currencyconverter.converter.view.MainView;

public class ConverterApplication {
    public static void main(String[] args) {
        var menu = new MainView();
        menu.welcomeToCurrencyConverter();
    }
}
