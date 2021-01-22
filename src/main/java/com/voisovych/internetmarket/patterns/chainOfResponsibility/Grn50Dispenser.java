package com.voisovych.internetmarket.patterns.chainOfResponsibility;

public class Grn50Dispenser implements DispenseChain{
    private DispenseChain chain;


    @Override
    public void setNexChain(DispenseChain dispenseChain) {
        this.chain = dispenseChain;

    }

    @Override
    public void dispense(Currency currency) {
        if(currency.getAmount() >= 50){
            int number = currency.getAmount()/50;
            int rem = currency.getAmount()%50;
            System.out.println("Видати " + number + " по 50 грн");
            if(rem != 0){
                this.chain.dispense(new Currency(rem));
            }
        }else {
            this.chain.dispense(currency);
        }

    }
}
