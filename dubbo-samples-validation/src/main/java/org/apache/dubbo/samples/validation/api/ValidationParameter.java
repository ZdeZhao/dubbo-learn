package org.apache.dubbo.samples.validation.api;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZDZ on 2019/5/18.
 */
public class ValidationParameter implements Serializable{

    private static final long serialVersionUID = -7623759362380546154L;
    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull(groups = ValidationService.Save.class)
    @Pattern(regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")
    private String email;

    @Min(18)
    @Max(100)
    private int age;

    @Past   // must be a past time
    private Date loginDate;

    @Future // must be a future time
    private Date expiryDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
