package io.crowdcode.flaschenlager.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class User {

    @Id
    private Long id;

    @Version
    private Long version;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

}
