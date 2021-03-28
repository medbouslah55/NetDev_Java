/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.ResultSet;
import java.util.List;
import models.Activite;

/**
 *
 * @author DELL
 */
public interface IActivite<T>{
    public void ajouterActivite(T t);
    public void supprimerActivite(T t);
    public void supprimerActivite(int id);
    public void modifierActivite(T t);
    public List<T> ActiviteListe() ;
    public T getActivite(int id);
    public T ChercherActParNom(String nom) ;
    public T ChercherActParCategorie(String categorie) ;
    public List<T> ChercherListActParCategorie(String categorie) ;
    public List<T> CentreActTrierParPrix() ;
    public List<T> ActListeTrierParPrixReverse() ;
    public ResultSet StatCategorie() ;
    public List<T> listeAct_Par_Centre(int id) ;
    public List<T> ChercherListActParCategorie(String categorie, List<Activite> l) ;
}

