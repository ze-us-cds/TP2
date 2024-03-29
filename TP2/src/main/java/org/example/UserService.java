package org.example;

public class UserService {
    private final UtilisateurApi utilisateurApi;
    public UserService(UtilisateurApi utilisateurApi) {
        this.utilisateurApi = utilisateurApi;
    }
    public void creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
        utilisateurApi.creerUtilisateur(utilisateur);
        int idUtilisateurCree = utilisateurApi.getIdUtilisateurCree();
        utilisateur.setId(idUtilisateurCree);
    }
    public boolean creerUtilisateurAvecRetour(Utilisateur utilisateur) throws ServiceException {
        try {
            utilisateurApi.creerUtilisateur(utilisateur);
            return true; // La création a réussi
        } catch (ServiceException e) {
            return false; // La création a échoué
        }
    }

}