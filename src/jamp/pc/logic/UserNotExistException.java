/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jamp.pc.logic;

import jamp.pc.ui.controller.*;

/**
 *
 * @author paula
 */
public class UserNotExistException extends Exception {
    
    public UserNotExistException(String msg){
    
        super(msg);
        
    }

    public UserNotExistException() {
    }
    
}
