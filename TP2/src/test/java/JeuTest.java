import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class JeuTest {

    @Test
    void testJeuFerme() {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        Jeu jeu = new Jeu(mockBanque);
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);
        jeu.fermer(); // On ferme le jeu

        // Act & Assert
        assertThrows(JeuFermeException.class, () -> jeu.jouer(mockJoueur, mockDe1, mockDe2));
    }
}
