package com.metal;

import com.metal.railfence.RailFence;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        RailFence railFence = new RailFence();
        try {
            String cr = railFence.enc("This is a test", 3);
            System.out.println("cr = " + cr);
            String decr = railFence.dec("T ashsi  etist", 3);
            System.out.println("decr = " + decr);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
