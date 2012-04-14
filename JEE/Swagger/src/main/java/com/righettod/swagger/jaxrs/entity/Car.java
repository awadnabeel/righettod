package com.righettod.swagger.jaxrs.entity;

import com.wordnik.swagger.core.ApiProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity representing a Car
 * 
 * @author Dominique Righetto
 */
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Car {   
    
    @XmlElement(name = "id")
    @ApiProperty(value = "Car name",notes="Car identifer", allowableValues = "Only number")        
    public String id = "";
    
    @XmlElement(name = "name")
    @ApiProperty(value = "Car name", allowableValues = "All unless space")        
    public String name = "";
    
    @XmlElement(name = "constructor")
    @ApiProperty(value = "Car constructor", allowableValues = "opel, ford, audi")    
    public String constructor = "";

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
}
