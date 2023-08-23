package org.example;

import java.util.Objects;

public class User {
    int personId;
    String username;
    String password;

    public User(int personId, String username, String password) {
        this.personId = personId;
        this.username = username;
        this.password = password;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getPersonId() == user.getPersonId() && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "personId=" + personId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
