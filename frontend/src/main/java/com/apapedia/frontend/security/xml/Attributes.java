package com.apapedia.frontend.security.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class Attributes {

    @XmlElement(name = "http://www.yale.edu/tp/cas")
    private String ldap_cn;

    @XmlElement(name = "http://www.yale.edu/tp/cas")
    private String kd_org;

    @XmlElement(name = "http://www.yale.edu/tp/cas")
    private String peran_user;

    @XmlElement(name = "http://www.yale.edu/tp/cas")
    private String nama;

    @XmlElement(name = "http://www.yale.edu/tp/cas")
    private String npm;

}

