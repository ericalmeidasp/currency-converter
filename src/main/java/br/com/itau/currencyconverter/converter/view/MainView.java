package br.com.itau.currencyconverter.converter.view;

import br.com.itau.currencyconverter.converter.Convertable;
import br.com.itau.currencyconverter.converter.IofTaxable;
import br.com.itau.currencyconverter.converter.currency.CurrencyFactory;
import br.com.itau.currencyconverter.converter.currency.CurrencyWithIofTaxableAndOperationalTaxable;
import br.com.itau.currencyconverter.converter.utils.InputData;
import br.com.itau.currencyconverter.converter.utils.KeyboardEntry;

import java.math.BigDecimal;
import java.util.Optional;

public class MainView {

    private final InputData inputData = new KeyboardEntry();

    public void welcomeToCurrencyConverter() {
        int opcaoSelecionada;
        do {
            System.out.println("Bem vindo ao conversor de moedas.");
            System.out.println("Escolha a opçâo desejada:");
            System.out.println("0 - para sair");
            System.out.println("1 - para abrir o menu de conversâo");
            opcaoSelecionada = inputData.intEntry();

            switch (opcaoSelecionada) {
                case 0:
                    System.out.println("Obrigado por utilizar o conversor.");
                    break;
                case 1:
                    menuCurrencyConverter();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcaoSelecionada != 0);
    }

    private void menuCurrencyConverter() {

        System.out.println("Digite o valor em reais (R$): ");
        double entry = inputData.doubleEntry();
        BigDecimal valueForConvert = BigDecimal.valueOf(entry);

        System.out.println("Digite a Sigla da Moeda deseja: ");
        String currencyCode = inputData.textEntry();

        Optional<CurrencyWithIofTaxableAndOperationalTaxable> converter = new CurrencyFactory().create(currencyCode);

        if (converter.isEmpty()) {
            System.out.println("Moeda não disponivel\n\n");
            return;
        }

        BigDecimal valueInFinalCurrency = converter.get().convert(valueForConvert);

        System.out.printf("Valor em reais: R$ %.02f%n", valueForConvert);
        System.out.printf("IOF: R$ %.02f%n", converter.get().calculateIof(valueForConvert));
        System.out.printf("Taxa de Operaçâo: R$ %.02f%n", converter.get().calculateOperationalTax(valueForConvert));
        System.out.printf("Valor Convertido: %s %.02f%n%n%n", currencyCode, valueInFinalCurrency);

    }
}
