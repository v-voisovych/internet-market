package com.voisovych.internetmarket.patterns.fabricPatterns;

public abstract class Computer {
    public abstract String getRam();
    public abstract String getHdd();
    public abstract String getCpu();

    @Override
    public String toString() {
        return "Ram = " + this.getRam() +  ", Hdd = " + this.getHdd() + ", Cpu = " + this.getCpu();
    }
}
