/**
 * 
 */
package com.wineaccess.data.model.profile;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement
public class CreateUserPasswordVO implements Serializable {

    private static final long serialVersionUID = 5385635682659975741L;
    private boolean isPasswordSet = false;
    private String message;
    
    public boolean isPasswordSet() {
        return isPasswordSet;
    }
    public void setPasswordSet(boolean isPasswordSet) {
        this.isPasswordSet = isPasswordSet;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }




}
