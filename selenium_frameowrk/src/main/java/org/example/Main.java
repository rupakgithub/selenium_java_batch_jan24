package org.example;

public class Main {
    public static void main(String[] args) {

        String text  = "Tax: $4.48";
        text = text.split("\\$")[1];
        System.out.println(text);
    }
}