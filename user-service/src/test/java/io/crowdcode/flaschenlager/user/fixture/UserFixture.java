package io.crowdcode.flaschenlager.user.fixture;

import io.crowdcode.flaschenlager.user.model.User;

/**
 * Created by idueppe on 24.03.17.
 */
public class UserFixture {
    public static User buildDefaultUser() {
        return new User()
                .setName("Name")
                .setPassword("secret")
                .setEmail("junit@crowdcode.io");
    }
}
