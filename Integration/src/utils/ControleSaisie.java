/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author trabe
 */
public class ControleSaisie {
    
    public static boolean isString(String text) {

        if (text.matches("^[a-zA-Z]+$")) {
            return true;
        }
        return false;

    }
    
    public static boolean isNull(String text) {

        if (text == "") {
            return true; //null
        }

        return false;//n'est pas vide
    }
    
}
