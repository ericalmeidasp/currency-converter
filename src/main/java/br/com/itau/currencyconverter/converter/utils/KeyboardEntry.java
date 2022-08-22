package br.com.itau.currencyconverter.converter.utils;

import java.util.Scanner;

public class KeyboardEntry implements InputData {
    private final Scanner scanner;

    public KeyboardEntry() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String textEntry() {
        return scanner.next();
    }

    @Override
    public double doubleEntry() {
        return scanner.nextDouble();
    }

    public int intEntry() {
        return scanner.nextInt();
    }

    public void close() {
        scanner.close();
    }
}
