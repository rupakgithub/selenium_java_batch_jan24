package org.example;

public class Main {
    public static void main(String[] args) {

//        Vehicle vehicle = new Vehicle("Mike");
//        vehicle.print();
//        Vehicle vehicle2 = new Vehicle("John");
//        vehicle2.print();
        Animal animal = Animal.getSingletonInstance();

    }

}

class Animal{
    private static Animal singletonInstance = null;

    public static Animal getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Animal();
        }
        return singletonInstance;
    }
}

class Vehicle{
    private String name;
    Vehicle(String username){
        name = username;
    }
    public void print(){
        System.out.println("My name is: "+name);
    }
}