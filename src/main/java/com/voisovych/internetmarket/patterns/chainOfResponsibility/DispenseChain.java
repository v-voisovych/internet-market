package com.voisovych.internetmarket.patterns.chainOfResponsibility;

public interface DispenseChain {

    void setNexChain(DispenseChain dispense);

    void dispense(Currency currency);
}
