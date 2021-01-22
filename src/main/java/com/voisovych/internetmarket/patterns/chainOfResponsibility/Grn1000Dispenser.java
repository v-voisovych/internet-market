package com.voisovych.internetmarket.patterns.chainOfResponsibility;

public class Grn1000Dispenser implements DispenseChain {
    private DispenseChain chain;


    @Override
    public void setNexChain(DispenseChain dispenseChain) {
        this.chain = dispenseChain;

    }

    @Override
    public void dispense(Currency currency) {

        if(currency.getAmount() >= 1000){
            int number = currency.getAmount()/1000;
            int rem = currency.getAmount()%1000;
            System.out.println("Видати " + number + " по 1000 грн");
            if(rem != 0){
                this.chain.dispense(new Currency(rem));
            }
        }else {
            this.chain.dispense(currency);
        }

    }
}
