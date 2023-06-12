package edu.poniperro.nowait.core.profile.user.application.find;

public class FindUserByEmailQueryMother {
    public static FindUserByEmailQuery create(String email) {
        return new FindUserByEmailQuery(email);
    }

    public static FindUserByEmailQuery random() {
        return create("email");
    }
}
