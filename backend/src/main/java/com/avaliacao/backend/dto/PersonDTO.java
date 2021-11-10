package com.avaliacao.backend.dto;

import com.avaliacao.backend.entities.Email;
import com.avaliacao.backend.entities.Phone;

public class PersonDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    private Email email;
    private Phone phone;

}
