package edu.poniperro.nowait.core.profile.user.domain;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

public final class User {
    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private String anonymous;
    private String type;
    private LocalDateTime creationDate;

    public User(ObjectId id, String name, String email, String password, String anonymous, String type,
                LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.anonymous = anonymous;
        this.type = type;
        this.creationDate = creationDate;
    }

    public User(String name, String email, String password, String anonymous, String type,
                LocalDateTime creationDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.anonymous = anonymous;
        this.type = type;
        this.creationDate = creationDate;
    }

    public User() {
    }

    public static User create(String name, String email, String password, String anonymous, String type,
                              LocalDateTime creationDate) {
        return new User(name, email, password, anonymous, type, creationDate);
    }

    public HashMap<String, Object> toPrimitives() {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("email", email);
            put("password", password);
            put("anonymous", anonymous);
            put("type", type);
            put("creationDate", creationDate);
        }};
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(anonymous, user.anonymous)) return false;
        if (!Objects.equals(type, user.type)) return false;
        return Objects.equals(creationDate, user.creationDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (anonymous != null ? anonymous.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
