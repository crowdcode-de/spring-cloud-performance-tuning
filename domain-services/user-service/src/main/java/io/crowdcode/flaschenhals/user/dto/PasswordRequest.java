package io.crowdcode.flaschenhals.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class PasswordRequest {

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;

}
