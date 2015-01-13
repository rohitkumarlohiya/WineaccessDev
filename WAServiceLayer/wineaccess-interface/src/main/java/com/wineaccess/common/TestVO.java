package com.wineaccess.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;
import com.wineaccess.commad.search.users.UserModelVO;

public class TestVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4498640366700401431L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     JAXB.marshal(new MyPO(), System.out);
     String json = new Gson().toJson(new MyPO(), MyPO.class);
     System.out.println(json);
    System.out.println(new Gson().fromJson(json, MyPO.class).toString());
    
    MyDeletePO delPO = new MyDeletePO();
    List<Long> notExists =  new ArrayList<Long>();
    notExists.add(1L);
    notExists.add(2L);
    
    delPO.setNonExistsList(notExists);
    
    
    DeleteVO<UserModelVO> deleteVO =new DeleteVO<UserModelVO>();
    
    List<UserModelVO> deleteList = new ArrayList<UserModelVO>();
    deleteList.add(new UserModelVO());
    deleteVO.setElements(deleteList);
    delPO.setFailureList(deleteVO);
    json = new Gson().toJson(delPO, MyDeletePO.class);
    System.out.println(json);
    
    JAXB.marshal(delPO, System.out);
	}

}

@XmlSeeAlso(UserModelVO.class)
class MyDeletePO extends BulkDeleteVO<UserModelVO>{
	
}

class MyPO extends SearchVO<String>{
	
	public MyPO(){
		super();
		this.searchResult="aaaa";
	}
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
class MyVo extends SearchPO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2200465914347627266L;

	public MyVo(){
		super();
	}
	
}