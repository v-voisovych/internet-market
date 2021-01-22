package com.voisovych.internetmarket.patterns.builder;

public class Test {
    public static void main(String[] args) {
        Person person = new Person
                .Builder("Ivan")
                .address("ghhj")
                .age(45)
                .height("192")
                .phoneNumber("4155565")
                .secondName("Ivanov")
                .weight("45445")
                .build();

        System.out.println(person);
    }
}
