package com.myorganization.app.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class User {

    private long id;
    private String name;
    private List<ERight> rights;

    public User(long id, String name, List<ERight> rights) {
        this.id = id;
        this.name = name;
        this.rights = rights;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ERight> getRights() {
        return rights;
    }

    public void setRights(List<ERight> rights) {
        this.rights = rights;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(id, user.id)
                .append(name, user.name)
                .append(rights, user.rights)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(rights)
                .toHashCode();
    }
}
