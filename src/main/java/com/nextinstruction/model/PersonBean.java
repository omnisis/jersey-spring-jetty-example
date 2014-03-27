package com.nextinstruction.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonBean {
    private String name;
    private int age;

    @XmlElement(name="dob")
    // EXPLICIT -- @XmlJavaTypeAdapter(PersonBeanXmlAdapter.class)
    // IMPLICIT -- done thru "package-info.java"
    private Date dob;

    @XmlTransient
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
