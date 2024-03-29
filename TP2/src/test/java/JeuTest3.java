import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JeuTest3 {

    @Test
    void testBanqueFondMinimumAtteint() {
        // Arrange
        BanqueImpl banque = new BanqueImpl(200, 100); // Fond initial de 200 et fond minimum de 100
        Jeu jeu = new Jeu(banque);
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);
        when(mockJoueur.mise()).thenReturn(150); // Mise du joueur
        when(mockDe1.lancer()).thenReturn(1); // Simulation du lancer de dés
        when(mockDe2.lancer()).thenReturn(6); // Simulation du lancer de dés

        // Act & Assert
        assertDoesNotThrow(() -> jeu.jouer(mockJoueur, mockDe1, mockDe2)); // Le jeu doit continuer malgré la victoire
        assertFalse(jeu.estOuvert()); // Le jeu doit être fermé car la banque n'est plus solvable
    }
}
