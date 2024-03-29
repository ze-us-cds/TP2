import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class JeuTest1 {

    @Test
    void testJoueurInsolvable() throws DebitImpossibleException {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        when(mockBanque.est_solvable()).thenReturn(true); // La banque est solvable
        Jeu jeu = new Jeu(mockBanque);
        Joueur mockJoueur = mock(Joueur.class);
        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);
        when(mockJoueur.mise()).thenReturn(100); // Mise du joueur
        doThrow(DebitImpossibleException.class).when(mockJoueur).debiter(100); // Simulation d'une exception de débit impossible

        // Act & Assert
        assertThrows(JeuFermeException.class, () -> jeu.jouer(mockJoueur, mockDe1, mockDe2));
        verify(mockDe1, never()).lancer(); // Vérifie que le dé n'est pas lancé
        verify(mockDe2, never()).lancer(); // Vérifie que le dé n'est pas lancé
    }
}
