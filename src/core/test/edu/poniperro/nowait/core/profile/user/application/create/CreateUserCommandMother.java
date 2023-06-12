package edu.poniperro.nowait.core.profile.user.application.create;

public class CreateUserCommandMother {

public static CreateUserCommand create(String name, String email, String password, String anonymous, String type,
            String creationDate) {
        return new CreateUserCommand(name, email, password, anonymous, type, creationDate);
    }

    public static CreateUserCommand random() {
        return new CreateUserCommand("name", "email", "password", "false", "type", "creationDate");
    }

}
