package io.crowdcode.flaschenlager.stock.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
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
@Table(indexes = @Index(name = "product_added", columnList = "productId,addedAt"))
public class StockEntry {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @NotNull
    private Long productId;

    @NotNull
    private Long quantity;

    @NotNull
    private BigDecimal price;

    @NotNull
    private LocalDateTime addedAt;

    @ManyToOne
    private Stock stock;
}
