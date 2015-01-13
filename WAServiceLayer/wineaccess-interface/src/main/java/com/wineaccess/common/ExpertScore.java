package com.wineaccess.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 * expert score and name
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ExpertScore implements Serializable {

    private static final long serialVersionUID = -6337925972018130621L;

    private String score;
    private String name;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
