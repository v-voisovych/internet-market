package com.voisovych.internetmarket.patterns.fabricPatterns;

public class FabricPattern {
    public static Computer getComputer(String type, String ram, String hdd, String cpu) {
        if ("PC".equals(type)) {
            return new PersonalComputer(ram, hdd, cpu);
        } else {
            if ("Server".equals(type)){
                return new Server(ram, hdd, cpu);
            }
        }
        return null;
    }
}
