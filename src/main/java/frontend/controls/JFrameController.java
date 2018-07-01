/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.controls;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import li3.TADCommunity;

/**
 *
 * @author Admin
 */
public class JFrameController extends java.awt.event.WindowAdapter {


    private TADCommunity sistema;
    
    public JFrameController(TADCommunity sistema) {
        this.sistema = sistema;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        sistema.clear();
        e.getWindow().dispose();
    }
    
}
