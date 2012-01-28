package com.drighetto.jaxrs.representation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class representing a Pet Representation in REST terminology (in XML format though JAXB annotation)
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
@XmlRootElement(name = "pet")
public class PetBean {

    /**Pet name*/
    @XmlElement(name = "petName")
    private String name = null;
    /**Pet age*/
    @XmlElement(name = "petAge")
    private Integer age = null;

    /**
     * Default constructor
     */
    public PetBean() {
        super();
    }

    /**
     * Constructor
     * @param pName Pet name
     * @param pAge pet age
     */
    public PetBean(String pName, Integer pAge) {
        super();
        this.name = pName;
        this.age = pAge;
    }

    /**
     * Method to get the name of the current pet
     *
     * (JAXB do not support Getter/Setter if JAXB mapping annotations are placed on attributes)
     * 
     * @return the pat name
     */
    public String getPetNameValue(){
        return this.name;
    }
}
