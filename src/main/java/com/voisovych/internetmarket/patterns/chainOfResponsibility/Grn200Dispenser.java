package com.voisovych.internetmarket.patterns.chainOfResponsibility;

public class Grn200Dispenser implements DispenseChain{
    private DispenseChain chain;


    @Override
    public void setNexChain(DispenseChain dispenseChain) {
        this.chain = dispenseChain;

    }

    @Override
    public void dispense(Currency currency) {
        if(currency.getAmount() >= 200){
            int number = currency.getAmount()/200;
            int rem = currency.getAmount()%200;
            System.out.println("Видати " + number + " по 200 грн");
            if(rem != 0){
                this.chain.dispense(new Currency(rem));
            }
        }else {
            this.chain.dispense(currency);
        }

    }
}
