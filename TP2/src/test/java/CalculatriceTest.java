import org.example.Calculatrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CalculatriceTest {
    @Mock
    private Calculatrice calculatrice;

    @Test
    public void testAdditionner() {
        // Définition du comportement de la méthode "additionner"
        when(calculatrice.additionner(5, 7)).thenReturn(12);

        // Appel de la méthode à tester
        int result = calculatrice.additionner(5, 7);

        // Vérification du résultat
        assertEquals(12, result);

        // Vérification que la méthode "additionner" a été appelée
        // avec les arguments 5 et 7, utiliser la directive « verify »
        verify(calculatrice).additionner(5, 7);

        // Vérification qu'aucune autre méthode n'a été appelée sur
        // l'objet après l'appel de la méthode "additionner", utiliser la
        // méthode « verifyNoMoreInteractions »
        verifyNoMoreInteractions(calculatrice);

        // Vérification de l'état de l'objet après l'appel de la
        // méthode "additionner", penser à utiliser la méthode
        // « getState() » de la directive « verify » : // exemple :
        when(calculatrice.getState()).thenReturn(10);
        int state = calculatrice.getState();
        assertEquals(10, state);
        verify(calculatrice).getState();
    }
}
