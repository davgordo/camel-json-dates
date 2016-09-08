package org.openpersuasion.demo.jsondates.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JaxbDateAdapter extends XmlAdapter<String, Date> {

    private static final String FORMAT = "yyyy-mm-dd";
    private SimpleDateFormat format = new SimpleDateFormat();

    @Override
    public Date unmarshal(String date) throws Exception {

        format.applyPattern(FORMAT);

        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }

    }

    @Override
    public String marshal(Date date) throws Exception {

        format.applyPattern(FORMAT);
        return format.format(date);

    }

}