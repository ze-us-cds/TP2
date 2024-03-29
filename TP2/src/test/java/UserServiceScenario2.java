import org.example.ServiceException;
import org.example.UserService;
import org.example.Utilisateur;
import org.example.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceScenario2{
    @Mock
    private UtilisateurApi utilisateurApiMock;
    @Test
    public void testCreerUtilisateur() throws ServiceException {
    // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "email_invalid");
    // TODO : Configuration du comportement du mock, utiliser la
    //   directive « when » avec sa méthode « thenReturn » and throw exception
        doThrow(new ServiceException("Echec de la création de l'utilisateur")).when(utilisateurApiMock).creerUtilisateur(utilisateur);
    // TODO : Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);
    // TODO : Appel de la méthode à tester7
        try {
            userService.creerUtilisateur(utilisateur);
        } catch (ServiceException e) {
            // Vérification que l'exception attendue a été levée
            assertEquals("Echec de la création de l'utilisateur", e.getMessage());
        }
    // TODO : Vérification de l'appel à l'API Nous vérifions également que la méthode creerUtilisateur de l'API utilisateur
    //  n'a jamais été appelée avec cet utilisateur en utilisant verify(utilisateurApiMock, never()).creerUtilisateur(utilisateur).
    //  Cela garantit que l'appel à l'API utilisateur n'a pas eu lieu en cas d'erreur de validation.
        verify(utilisateurApiMock, never()).creerUtilisateur(utilisateur);
    }
}