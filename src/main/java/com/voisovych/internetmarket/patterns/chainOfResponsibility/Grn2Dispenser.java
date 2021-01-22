package com.voisovych.internetmarket.patterns.chainOfResponsibility;

public class Grn2Dispenser implements DispenseChain{

    private DispenseChain chain;


    @Override
    public void setNexChain(DispenseChain dispenseChain) {
        this.chain = dispenseChain;

    }

    @Override
    public void dispense(Currency currency) {
        if(currency.getAmount() >= 2){
            int number = currency.getAmount()/2;
            int rem = currency.getAmount()%2;
            System.out.println("Видати " + number + " по 2 грн");
            if(rem != 0){
                this.chain.dispense(new Currency(rem));
            }
        }else {
            this.chain.dispense(currency);
        }

    }
}
