package com.voisovych.internetmarket.patterns.builder;

public class Person {

    private String name;
    private String secondName;
    private int age;
    private String phoneNumber;
    private String address;
    private String weight;
    private String height;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                '}';
    }

    private Person(Builder builder){
        this.name = builder.name;
        this.secondName = builder.secondName;
        this.age = builder.age;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.weight = builder.weight;
        this.height = builder.height;
    }

    public static class Builder {

        private String name;
        private String secondName;
        private int age;
        private String phoneNumber;
        private String address;
        private String weight;
        private String height;

        public Builder(String name) {
            this.name = name;
        }

        public Builder secondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder weight(String weight) {
            this.weight = weight;
            return this;
        }

        public Builder height(String height) {
            this.height = height;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
