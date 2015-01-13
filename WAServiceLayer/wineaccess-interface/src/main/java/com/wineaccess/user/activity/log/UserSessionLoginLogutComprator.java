/**
 * 
 */
package com.wineaccess.user.activity.log;

import java.util.Comparator;

import com.wineaccess.data.model.security.TokenModel;

/**
 * @author anurag.jain3
 *
 */
public class UserSessionLoginLogutComprator implements Comparator<TokenModel> {

	@Override
	public int compare(TokenModel source, TokenModel target) {
		
		//return source.getSessionStartTime().compareTo(target.getSessionStartTime());
		
		if (source.getSessionStartTime().before(target.getSessionStartTime())) {
            return 1;
        } else if (source.getSessionStartTime().after(target.getSessionStartTime())) {
            return -1;
        } else {
            return 0;
        }  
	}

}
