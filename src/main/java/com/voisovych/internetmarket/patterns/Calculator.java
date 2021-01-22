package com.voisovych.internetmarket.patterns;

public class Calculator {

    public int calc(int a, int b, boolean c) {
        int res;
        if (c) {
            res = a + b;
        } else {
            res = a - b;
        }
        return res;
    }

    public double log (int a, int b) {
        return Math.log(b)/Math.log(a);
    }
}
