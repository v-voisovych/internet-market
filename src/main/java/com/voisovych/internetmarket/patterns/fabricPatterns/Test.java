package com.voisovych.internetmarket.patterns.fabricPatterns;

public class Test {
    public static void main(String[] args) {
        Computer server = FabricPattern.getComputer("Server", "8000", "1T", "4563");
        Computer computer = FabricPattern.getComputer("PC", "1452", "4558", "4585");
        System.out.println(server);
        System.out.println(computer);
    }
}
