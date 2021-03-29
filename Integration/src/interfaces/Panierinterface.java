/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author HAMZA
 * @param <e>
 */
public interface Panierinterface <e> {
    public void addpanier (e p);
    public void deletepanier(int x) ;
    public List<e> displaypanier() ;
    public void updatepanier (e p) ;
    
    
}
