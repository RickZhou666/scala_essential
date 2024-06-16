package com.rick.scala.playground;

/**
 * @Author: Rick
 * @Date: 2024/6/15 21:07
 */
public class JavaPlayground {
    public static void main(String[] args) {
        System.out.println(Person.N_EYES); // from class not from instance

    }
}
class Person {
    public static final int N_EYES = 2; // define a constant does not rely on instance of class
}
