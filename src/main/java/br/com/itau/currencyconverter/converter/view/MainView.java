package br.com.itau.currencyconverter.converter.view;

import br.com.itau.currencyconverter.converter.currency.Convertable;
import br.com.itau.currencyconverter.converter.currency.CurrencyFactory;
import br.com.itau.currencyconverter.converter.currency.CurrencyWithIofTaxableAndOperationalTaxable;
import br.com.itau.currencyconverter.converter.utils.InputData;
import br.com.itau.currencyconverter.converter.utils.KeyboardEntry;

import java.math.BigDecimal;
import java.util.Optional;

public class MainView {

    public void mainMenuCurrencyConverter() {
        int opcaoSelecionada = -1;
        do {
            InputData inputData = new KeyboardEntry();

            System.out.println("Escolha a opçâo desejada:");
            System.out.println("0 - Sair");
            System.out.println("1 - Abrir o menu de conversâo de Moedas");

            try {
                opcaoSelecionada = inputData.intEntry();
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }

            switch (opcaoSelecionada) {
                case 0:
                    System.out.println("Obrigado por utilizar o conversor.");
                    break;
                case 1:
                    try {
                        currencyMenuConverter();
                    } catch (IllegalArgumentException ex) {
                        System.out.printf("%s%n%n", ex.getLocalizedMessage());
                    }
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente\n");
                    break;
            }
        } while (opcaoSelecionada != 0);
    }

    private void currencyMenuConverter() {
        BigDecimal valueForConvert = retrieveValueforConvert();
        String currencyCode = retrieveCurrencyCodeForConvert();

        Optional<Convertable> converter = new CurrencyFactory().create(currencyCode);

        if (converter.isPresent()) {
            printResultOfConvertion(converter.get(), valueForConvert);
        } else {
            throw new IllegalArgumentException("Moeda não disponível");
        }
    }

    private BigDecimal retrieveValueforConvert() {
        InputData inputData = new KeyboardEntry();
        System.out.print("\nDigite o valor em reais (R$): ");
        double entry = inputData.doubleEntry();
        return BigDecimal.valueOf(entry);
    }

    private String retrieveCurrencyCodeForConvert() {
        InputData inputData = new KeyboardEntry();
        System.out.println("Digite a moeda de destino: \n 1 - Euro \n 2 - Dólar \n 3 - Peso Argentino \n 4 - Peso Chileno");
        return inputData.textEntry();
    }

    private void printResultOfConvertion(Convertable converter, BigDecimal valueForConvert) {
        CurrencyWithIofTaxableAndOperationalTaxable converterCurrency = (CurrencyWithIofTaxableAndOperationalTaxable) converter;

        BigDecimal valueInFinalCurrency = converterCurrency.convert(valueForConvert);

        System.out.printf("%nValor em reais:    R$ %.02f%n", valueForConvert);
        System.out.printf("IOF:               R$ %.02f%n", converterCurrency.calculateIof(valueForConvert));
        System.out.printf("Taxa de Operaçâo:  R$ %.02f%n", converterCurrency.calculateOperationalTax(valueForConvert));
        System.out.println("---------------------------");
        System.out.printf("Total Convertido:  %s %.02f%n%n", converterCurrency.getClass().getSimpleName(), valueInFinalCurrency);
    }
}