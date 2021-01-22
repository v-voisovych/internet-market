package com.voisovych.internetmarket.patterns.fabricPatterns;

public class PersonalComputer extends Computer{

    private String ram;
    private String hdd;
    private String cpu;

    @Override
    public String getRam() {
        return ram;
    }

    @Override
    public String getHdd() {
        return hdd;
    }

    @Override
    public String getCpu() {
        return cpu;
    }

    public PersonalComputer(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }


}
