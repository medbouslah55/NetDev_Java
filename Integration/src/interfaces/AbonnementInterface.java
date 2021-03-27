/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author Firas
 * @param <a>
 */
public interface AbonnementInterface <a>{
    
    public void ajouterAbonnement(a y);//Terminer
    
    public void supprimerAbonnement(a y);//Terminer
    
    public void supprimerAbonnementById(int y);//Terminer
    
    public void modifierAbonnement(a y , int f);//Terminer
    
    //afficher toutes les Abonnement
    public List<a> listAbonnement();//Terminer
    
    //afficher l'abonnement de type x
    public List<a> afficherAbonnementDeTypeX(String x);//Terminer
    
    //afficher le nombre d'abonnement total
    public int nbAbonnementTotal();//Terminer
    
    //afficher le nombre d'abonnement pour chaque type
    public int nbAbonnementTypeX(String x);//A Faire
    
    public Float budgetAbonnement();// A FAIRE
        
    
}
