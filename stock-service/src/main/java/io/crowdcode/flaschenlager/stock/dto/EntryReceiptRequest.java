package io.crowdcode.flaschenlager.stock.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id", "version"})
@ToString(exclude = "stock")
@AllArgsConstructor
@NoArgsConstructor
public class EntryReceiptRequest {

    @NotNull
    private Long productId;
    @NotNull
    private Long quantity;
    @NotNull
    private BigDecimal price;

}
