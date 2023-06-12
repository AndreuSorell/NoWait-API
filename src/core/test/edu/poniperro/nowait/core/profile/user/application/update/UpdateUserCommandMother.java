package edu.poniperro.nowait.core.profile.user.application.update;

public class UpdateUserCommandMother {

    public static UpdateUserCommand create(String name, String email, String password, String anonymous) {
        return new UpdateUserCommand(name, email, password, anonymous);
    }

    public static UpdateUserCommand random() {
        return create("name", "email", "password", "false");
    }
}
