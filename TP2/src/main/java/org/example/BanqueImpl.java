package org.example;

public class BanqueImpl implements Banque {
    private int fond;
    private final int fondMinimum;

    public BanqueImpl(int fondInitial, int fondMinimum) {
        this.fond = fondInitial;
        this.fondMinimum = fondMinimum;
    }

    @Override
    public void crediter(int somme) {
        fond += somme;
    }

    @Override
    public boolean est_solvable() {
        return fond >= fondMinimum;
    }

    @Override
    public void debiter(int somme) {
        fond -= somme;
    }
}
