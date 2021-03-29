/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import services.*;
import javafx.collections.ObservableList;

/**
 *
 * @author trabe
 */
public interface IService<T> {
    public void ajouter(T t);
    public void supprimer(T t);
    public void modifier(T t);
    public ObservableList<T> afficher();
}
