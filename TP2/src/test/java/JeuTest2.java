import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JeuTest2 {

    @Test
    void testVictoire() {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        when(mockBanque.est_solvable()).thenReturn(true); // La banque est solvable
        Jeu jeu = new Jeu(mockBanque);
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);
        when(mockJoueur.mise()).thenReturn(50); // Mise du joueur
        when(mockDe1.lancer()).thenReturn(3); // Simulation du lancer de dés
        when(mockDe2.lancer()).thenReturn(4); // Simulation du lancer de dés

        // Act
        assertDoesNotThrow(() -> jeu.jouer(mockJoueur, mockDe1, mockDe2));

        // Assert
        verify(mockJoueur).crediter(100); // Le joueur doit être crédité de 100 (2 fois sa mise)
    }

    @Test
    void testDefaite() {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        when(mockBanque.est_solvable()).thenReturn(true); // La banque est solvable
        Jeu jeu = new Jeu(mockBanque);
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);
        when(mockJoueur.mise()).thenReturn(50); // Mise du joueur
        when(mockDe1.lancer()).thenReturn(1); // Simulation du lancer de dés
        when(mockDe2.lancer()).thenReturn(2); // Simulation du lancer de dés

        // Act
        assertDoesNotThrow(() -> jeu.jouer(mockJoueur, mockDe1, mockDe2));

        // Assert
        verify(mockJoueur, never()).crediter(anyInt()); // Le joueur ne doit pas être crédité
    }

    @Test
    void testSolvabiliteBanque() {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        when(mockBanque.est_solvable()).thenReturn(true, false); // La banque est solvable puis devient insolvable
        Jeu jeu = new Jeu(mockBanque);
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);
        when(mockJoueur.mise()).thenReturn(50); // Mise du joueur
        when(mockDe1.lancer()).thenReturn(3); // Simulation du lancer de dés
        when(mockDe2.lancer()).thenReturn(4); // Simulation du lancer de dés

        // Act & Assert
        assertDoesNotThrow(() -> jeu.jouer(mockJoueur, mockDe1, mockDe2)); // La banque est solvable
        assertThrows(JeuFermeException.class, () -> jeu.jouer(mockJoueur, mockDe1, mockDe2)); // La banque devient insolvable
    }

    @Test
    void testFermetureJeuGainImportant() {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        when(mockBanque.est_solvable()).thenReturn(true); // La banque est solvable
        Jeu jeu = new Jeu(mockBanque);
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);
        when(mockJoueur.mise()).thenReturn(500); // Mise du joueur

        // Act & Assert
        assertThrows(JeuFermeException.class, () -> jeu.jouer(mockJoueur, mockDe1, mockDe2)); // Le jeu doit fermer en raison du gain important
    }


    @Test
    void testFermetureJeuSolvabiliteInsuffisanteBanque() {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        when(mockBanque.est_solvable()).thenReturn(false); // La banque est insolvable
        Jeu jeu = new Jeu(mockBanque);
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);
        when(mockJoueur.mise()).thenReturn(50); // Mise du joueur

        // Act & Assert
        assertThrows(JeuFermeException.class, () -> jeu.jouer(mockJoueur, mockDe1, mockDe2)); // Le jeu doit fermer en raison de la solvabilité insuffisante de la banque
    }
}
