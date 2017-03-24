package io.crowdcode.flaschenlager.product.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String unit;

    @NotNull
    private Double amount;

}
