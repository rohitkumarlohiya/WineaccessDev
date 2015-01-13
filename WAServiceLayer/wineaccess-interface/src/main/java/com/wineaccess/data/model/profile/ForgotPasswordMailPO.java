package com.wineaccess.data.model.profile;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement(name="forgotPasswordMail")
public class ForgotPasswordMailPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="USERACTIVATION102")
	private String email;
	private String url;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
