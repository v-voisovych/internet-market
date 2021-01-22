package com.voisovych.internetmarket.patterns.chainOfResponsibility;

public class Grn500Dispenser implements DispenseChain{
    private DispenseChain chain;


    @Override
    public void setNexChain(DispenseChain dispenseChain) {
        this.chain = dispenseChain;

    }

    @Override
    public void dispense(Currency currency) {
        if(currency.getAmount() >= 500){
            int number = currency.getAmount()/500;
            int rem = currency.getAmount()%500;
            System.out.println("Видати " + number + " по 500 грн");
            if(rem != 0){
                this.chain.dispense(new Currency(rem));
            }
        }else {
            this.chain.dispense(currency);
        }

    }
}
