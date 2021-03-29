/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author DELL
 */
public interface ICentre<T> {
    public void ajouterCentre(T t);
    public void ajouterCentre2(T t);
    public void supprimerCentre(int id);
     public void supprimerCentre(T t);
    public void modifierCentre(T t);
    public List<T> CentreListe() ;
    public T getCentre(int id);
    public T ChercherCentreParNom(String nom) ;
    public List<T> CentreListeTrierParNom() ;
    public List<T> CentreListeTrierParNomReverse() ;
    public List<T> ChercherListCentreParNom(String categorie);
    public List<String> getMails();
    public T ChercherCentreParMail(String mail) ;
    public T ChercherCentreParMail(String mail, int id) ;
    public T ChercherCentreParNom(String nom,int id) ;
}

