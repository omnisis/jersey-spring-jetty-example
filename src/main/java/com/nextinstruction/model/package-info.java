@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(value= PersonBeanXmlAdapter.class, type=Date.class)
})
package com.nextinstruction.model;


import com.nextinstruction.adapters.PersonBeanXmlAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.util.Date;

