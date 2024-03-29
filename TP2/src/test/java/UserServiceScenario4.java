import org.example.ServiceException;
import org.example.UserService;
import org.example.Utilisateur;
import org.example.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceScenario4 {

    @Mock
    private UtilisateurApi utilisateurApiMock;

    @Captor
    private ArgumentCaptor<Utilisateur> utilisateurCaptor;

    @Test
    public void testCreerUtilisateur_ArgumentsCaptures() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Bouhafs", "Adem", "bouhafsadem@email.com");

        // Configuration du comportement du mock de l'API utilisateur
        doNothing().when(utilisateurApiMock).creerUtilisateur(any(Utilisateur.class));

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Capture des arguments passés à la méthode creerUtilisateur du mock
        verify(utilisateurApiMock).creerUtilisateur(utilisateurCaptor.capture());
        Utilisateur utilisateurCapture = utilisateurCaptor.getValue();

        // Vérification des arguments capturés
        assertEquals(utilisateur.getNom(), utilisateurCapture.getNom());
        assertEquals(utilisateur.getPrenom(), utilisateurCapture.getPrenom());
        assertEquals(utilisateur.getEmail(), utilisateurCapture.getEmail());
    }
}