package org.openpersuasion.demo.jsondates.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.openpersuasion.demo.jsondates.adapters.JaxbDateAdapter;
import java.util.Date;

public class JaxbAnnotatedPojo {

    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    private Date birthDate;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
