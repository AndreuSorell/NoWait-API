package edu.poniperro.nowait.core.profile.user.application.create;

import edu.poniperro.nowait.shared.domain.bus.command.Command;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreateUserCommand implements Command {
    private String name;
    private String email;
    private String password;
    private String anonymous;
    private String type;
    private LocalDateTime creationDate;

    public CreateUserCommand(String name, String email, String password, String anonymous, String type,
                             LocalDateTime creationDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.anonymous = anonymous;
        this.type = type;
        this.creationDate = creationDate;
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

        CreateUserCommand that = (CreateUserCommand) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(password, that.password)) return false;
        if (!Objects.equals(anonymous, that.anonymous)) return false;
        if (!Objects.equals(type, that.type)) return false;
        return Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (anonymous != null ? anonymous.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
