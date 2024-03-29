package org.example;
public class Jeu {
    private Banque banque;
    private boolean ouvert;

    public Jeu(Banque labanque) {
        this.banque = labanque;
        this.ouvert = true;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if (!ouvert) {
            throw new JeuFermeException("Le jeu est fermé");
        }

        int mise = joueur.mise();

        try {
            joueur.debiter(mise); // Débiter le joueur de sa mise
        } catch (DebitImpossibleException e) {
            fermer();
            throw new JeuFermeException("Le joueur n'a pas assez d'argent pour jouer");
        }

        int sommeDes = de1.lancer() + de2.lancer();

        if (sommeDes == 7) {
            joueur.crediter(mise * 2); // Créditer le joueur de deux fois sa mise
        } else {
            fermer(); // Le jeu s'arrête si la somme des dés n'est pas égale à 7
        }

        // Vérifier la solvabilité de la banque
        if (!banque.est_solvable()) {
            fermer();
            throw new JeuFermeException("La banque n'est plus solvable");
        }
    }

    public void fermer() {
        this.ouvert = false;
    }

    public boolean estOuvert() {
        return ouvert;
    }
}
