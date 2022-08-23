package br.com.itau.currencyconverter.converter.view;

import br.com.itau.currencyconverter.converter.currency.CurrencyFactory;
import br.com.itau.currencyconverter.converter.currency.CurrencyWithIofTaxableAndOperationalTaxable;
import br.com.itau.currencyconverter.converter.utils.InputData;
import br.com.itau.currencyconverter.converter.utils.KeyboardEntry;

import java.math.BigDecimal;
import java.util.Optional;

public class MainView {

    public void mainMenuCurrencyConverter() {
        int opcaoSelecionada;
        do {
            InputData inputData = new KeyboardEntry();

            System.out.println("Escolha a opçâo desejada:");
            System.out.println("0 - Sair");
            System.out.println("1 - Abrir o menu de conversâo de Moedas");

            try {
                opcaoSelecionada = inputData.intEntry();
            } catch (Exception ex) {
                System.out.println("Digite apenas numeros inteiros\n");
                opcaoSelecionada = -1;
            }

            switch (opcaoSelecionada) {
                case 0:
                    System.out.println("Obrigado por utilizar o conversor.");
                    break;
                case 1:
                    currencyMenuConverter();
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente\n");
                    break;
            }
        } while (opcaoSelecionada != 0);
    }

    private void currencyMenuConverter() {
        InputData inputData = new KeyboardEntry();
        BigDecimal valueForConvert;

        try {
            System.out.print("\nDigite o valor em reais (R$): ");
            double entry = inputData.doubleEntry();
            valueForConvert = BigDecimal.valueOf(entry);
        } catch (Exception ex) {
            System.out.println("Digite apenas números\n");
            return;
        }

        System.out.println("Digite a moeda de destino: \n 1 - Euro \n 2 - Dólar \n 3 - Peso Argentino \n 4 - Peso Chileno");
        String currencyCode = inputData.textEntry();

        Optional<CurrencyWithIofTaxableAndOperationalTaxable> converter = new CurrencyFactory().create(currencyCode);

        if (converter.isEmpty()) {
            System.out.println("Moeda não disponivel\n\n");
            return;
        }

        try {
            BigDecimal valueInFinalCurrency = converter.get().convert(valueForConvert);

            System.out.printf("%nValor em reais:    R$ %.02f%n", valueForConvert);
            System.out.printf("IOF:               R$ %.02f%n", converter.get().calculateIof(valueForConvert));
            System.out.printf("Taxa de Operaçâo:  R$ %.02f%n", converter.get().calculateOperationalTax(valueForConvert));
            System.out.println("---------------------------");
            System.out.printf("Total Convertido:  %s %.02f%n%n", converter.get().getClass().getSimpleName(), valueInFinalCurrency);
        } catch (IllegalArgumentException ex) {
            System.out.printf("%s%n%n", ex.getLocalizedMessage());
        }
    }
}