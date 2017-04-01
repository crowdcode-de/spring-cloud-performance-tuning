package io.crowdcode.flaschenhals.user.fixture;

import io.crowdcode.flaschenhals.user.model.User;

public class UserFixture {
    public static User buildDefaultUser() {
        return new User()
                .setName("Name")
                .setPassword("secret")
                .setEmail("junit@crowdcode.io");
    }

    public static User buildPersistentUser() {
        return new User()
                .setId(-2L)
                .setName("Name2")
                .setPassword("secret2")
                .setEmail("junit2@crowdcode.io");
    }
}
