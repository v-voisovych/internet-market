package com.voisovych.internetmarket.patterns.chainOfResponsibility;

import java.util.Scanner;

public class ATM {

    private DispenseChain m1;

    public ATM() {
        this.m1 = new Grn1000Dispenser();
        DispenseChain m500 = new Grn500Dispenser();
        DispenseChain m200 = new Grn200Dispenser();
        DispenseChain m100 = new Grn100Dispenser();
        DispenseChain m50 = new Grn50Dispenser();
        DispenseChain m20 = new Grn20Dispenser();
        DispenseChain m10 = new Grn10Dispenser();
        DispenseChain m5 = new Grn5Dispenser();
        DispenseChain m2 = new Grn2Dispenser();
        DispenseChain mOne = new Grn1Dispenser();

        this.m1.setNexChain(m500);
        m500.setNexChain(m200);
        m200.setNexChain(m100);
        m100.setNexChain(m50);
        m50.setNexChain(m20);
        m20.setNexChain(m10);
        m10.setNexChain(m5);
        m5.setNexChain(m2);
        m2.setNexChain(mOne);

    }

    public static void main(String[] args) {

        ATM atm = new ATM();
        while (true) {

            Scanner scanner = new Scanner(System.in);
            try {
                int amount = scanner.nextInt();
                while (amount <= 0) {
                    System.out.println("Введено некоректне число");
                    amount = scanner.nextInt();
                }
                atm.m1.dispense(new Currency(amount));
            } catch (Exception exception) {
                System.out.println("Невірний тип даних");
            }
        }
    }
}


