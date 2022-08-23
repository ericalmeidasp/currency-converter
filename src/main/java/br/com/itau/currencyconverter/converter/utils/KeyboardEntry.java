package br.com.itau.currencyconverter.converter.utils;

import java.util.Scanner;

public class KeyboardEntry implements InputData {
    private final Scanner scanner;

    public KeyboardEntry() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String textEntry() {
        try {
            return scanner.nextLine();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Texto inválido\n");
        }
    }

    @Override
    public double doubleEntry() {
        try {
            return scanner.nextDouble();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Digite apenas numeros\n");
        }
    }

    public int intEntry() {
        try {
            return scanner.nextInt();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Digite apenas numeros inteiros\n");
        }
    }

    public void close() {
        scanner.close();
    }
}
