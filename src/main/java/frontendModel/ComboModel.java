/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontendModel;

import frontend.interfaces.Constants;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Nuno
 */
public class ComboModel extends DefaultComboBoxModel {
    
    public ComboModel() {
        super(Constants.COMBO_VALUES);
    }
    
    public Integer[] getValuesArray() {
        return Constants.COMBO_VALUES;
    }
    
}
