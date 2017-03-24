package io.crowdcode.flaschenlager.user.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserInfo {

    private Long id;
    private String email;
    private String name;

}
