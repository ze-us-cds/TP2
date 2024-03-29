import org.example.ServiceException;
import org.example.UserService;
import org.example.Utilisateur;
import org.example.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceScenario3 {

    @Mock
    private UtilisateurApi utilisateurApiMock;

    @Test
    public void testCreerUtilisateur_IdentifiantUnique() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        // Définition d'un ID fictif
        int idUtilisateur = 123;

        // Configuration du comportement du mock de l'API utilisateur
        when(utilisateurApiMock.creerUtilisateurAvecRetour(utilisateur)).thenReturn(true); // Supposons que la création réussisse
        when(utilisateurApiMock.getIdUtilisateurCree()).thenReturn(idUtilisateur); // Retourne l'ID fictif

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Vérification de l'ID de l'utilisateur
        int idUtilisateurCree = utilisateurApiMock.getIdUtilisateurCree();
        assertEquals(idUtilisateur, idUtilisateurCree); // Vérifie que l'identifiant fictif retourné par l'API est bien attribué à l'utilisateur


    }
}
