/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Firas
 * @param <r>
 */
public interface ReclamationInterface <r> {
    public void creeReclamation(r p);//Terminer
    
    //changer etat reclamation
    public void changerEtatReclamation(int p);
    
    //afficher toutes les reclamation
    public List<r> listeReclamation();//Terminer
    
    //afficher les reclamation pour un utilisateur x
    public List<r> listeReclamationUser(int x);//Terminer
    
    //Chercher une reclamation dans une dateSup
    public List<r> chercherReclamationUserDateSup(Date x);///Terminer
    
    //Chercher une reclamation dans une dateInf
    public List<r> chercherReclamationUserDateInf(Date x);///Terminer
    
    //afficher les reclamation trier selon date
    public List<r> trierreclamationDate();///Terminer
    
    //Afficher le nombre total des reclamations
    public int nombreReclamationTotal();///Terminer
    
    //Chercher une reclamation entre une date x et date y
    public List<r> chercherReclamationUserPeriode(Date x,Date y);//A Faire
    
    
    
    
}
