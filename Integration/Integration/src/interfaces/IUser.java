/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author mohamedbouslah
 */
public interface IUser <T> {
    public void ajouter(T t);
    public void supprimer(T t);
    public void modifier(T t);
    public List<T> afficher();
    public List<T> userListe();
    public List<T> TrieParNom();
    //public T ChercherUserParNom(String nom);
    
}
