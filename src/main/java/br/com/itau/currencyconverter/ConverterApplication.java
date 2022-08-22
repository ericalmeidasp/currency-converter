package br.com.itau.currencyconverter;

import br.com.itau.currencyconverter.converter.view.MainView;

public class ConverterApplication {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao conversor de moedas.");
        var menu = new MainView();
        menu.mainMenuCurrencyConverter();
    }
}
