package com.bahadirakin.rest.api;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlType(name = "user", propOrder = {
        "id",
        "username",
        "email",
        "password"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlElement(name = "id", required = false, nillable = false)
    private String id;
    @XmlElement(name = "username", required = true, nillable = false)
    private String username;
    @XmlElement(name = "email", required = true, nillable = false)
    private String email;
    @XmlElement(name = "password", required = false, nillable = false)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
