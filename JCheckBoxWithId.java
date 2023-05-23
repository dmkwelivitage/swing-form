/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import javax.swing.JCheckBox;

/**
 *
 * @author nirod
 */
public class JCheckBoxWithId extends JCheckBox {

    private Integer _id;

    public JCheckBoxWithId(String text, Integer id) {
        super(text);
        _id = id;
    }

    public void setId(Integer id) {
        _id = id;
    }

    public Integer getId() {
        return _id;
    }

}
