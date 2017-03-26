package io.crowdcode.flaschenlager.accounting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id", "version"})
@ToString(exclude = "stock")
@Table(indexes =
        {
                @Index(name = "by_customer_user", columnList = "customerId,userId,bookedat"),
                @Index(name = "by_customer_date", columnList = "customerId, bookedAt")
        })
public class AccountEntry {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @NotNull
    private Long customerId;

    @NotNull
    private Long userId;

    @NotNull
    private Long quantity;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Long productId;

    @NotNull
    private String description;

    @NotNull
    private LocalDateTime bookedAt;

    public BigDecimal getTotalAmount() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

}