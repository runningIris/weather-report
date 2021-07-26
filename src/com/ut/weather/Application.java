package com.ut.weather;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("check recent weather forecast!");
        System.out.println("type 1, check weather forecast in the next 24 hours.");
        System.out.println("type 2, check weather forecast in the next 3 days.");
        System.out.println("type 3, check weather forecast in the next 7 days.");
        System.out.print("Please type your chose: ");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.println("Number " + i + " has be typed.");
    }
}
