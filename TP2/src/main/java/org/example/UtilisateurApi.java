package org.example;
import org.example.ServiceException;
public interface UtilisateurApi {
    void creerUtilisateur(Utilisateur utilisateur) throws ServiceException;

    int getIdUtilisateurCree();

    Object creerUtilisateurAvecRetour(Utilisateur utilisateur);
}