package com.metal.railfence;

/**
 * Set of methods for applying Rail Fence encryption, with support for all types of characters. Not optimized!
 * More information: https://en.wikipedia.org/wiki/Rail_fence_cipher
 * @author SoldierFC , A.K.A. MetalDroid
 */

public class RailFence {
    private String text = null;
    private int numRails = 0;

    public RailFence() {
    }

    public RailFence(String text, int numRails) {
        this.text = text;
        this.numRails = numRails;
    }

    private char[][] clearRails(int textSize){
        char[][] r = new char[numRails][textSize];
        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < text.length(); j++) {
                r[i][j] = 0;
            }
        }
        return r;
    }

    private char[][] fillRails(String text){
        int textSize = text.length();
        char[][] r = clearRails(textSize);
        int rAct = 0;
        boolean down = true;

        for (int i = 0; i < textSize; i++) {
            if (rAct == 0){
                down = true;
            } else if (rAct == numRails -1){
                down = false;
            }
            r[rAct][i] = text.charAt(i);
            rAct = down ? ++rAct : --rAct;
        }
        return r;
    }

    public String enc(String text, int numRails) throws Exception {
        setText(text);
        setNumRails(numRails);
        StringBuilder res = new StringBuilder();
        char[][] r = fillRails(text);

        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < text.length(); j++) {
                res.append(r[i][j]);
            }
        }
        return res.toString().replaceAll(Character.toString(0), "");
    }

    public String dec(String text, int numRails) throws Exception {
        setText(text);
        setNumRails(numRails);
        char[][] r = fillRails(text);
        StringBuilder res = new StringBuilder();
        int cIndex = 0;

        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < text.length(); j++) {
                r[i][j] = r[i][j] != 0 ? text.charAt(cIndex++) : r[i][j];
            }
        }

        int rAct = 0;
        boolean down = true;

        for (int i = 0; i < text.length(); i++) {
            if (rAct == 0){
                down = true;
            } else if (rAct == numRails -1){
                down = false;
            }
            res.append(r[rAct][i]);
            rAct = down ? ++rAct : --rAct;
        }

        return res.toString();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumRails() {
        return numRails;
    }

    public void setNumRails(int numRails) throws Exception {
        if (numRails <= 1) throw new Exception("Error: NumRails must be greater than 1.");
        this.numRails = numRails;
    }
}
