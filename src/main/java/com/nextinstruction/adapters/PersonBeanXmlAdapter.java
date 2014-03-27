package com.nextinstruction.adapters;


import org.apache.commons.lang.time.FastDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;


public class PersonBeanXmlAdapter extends XmlAdapter<String, Date> {

    private final FastDateFormat fdf =
            FastDateFormat.getInstance("yyyyMMdd");


    @Override
    public Date unmarshal(String s) throws Exception {
        return (Date)fdf.parseObject(s);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return fdf.format(date);
    }
}
