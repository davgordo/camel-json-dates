package org.openpersuasion.demo.jsondates.model;

import com.google.gson.annotations.JsonAdapter;
import org.openpersuasion.demo.jsondates.adapters.GsonDateAdapter;
import java.util.Date;

public class GsonAnnotatedPojo {

    @JsonAdapter(GsonDateAdapter.class)
    private Date birthDate;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
