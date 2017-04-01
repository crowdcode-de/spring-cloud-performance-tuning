package io.crowdcode.flaschenhals.stock.resource;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id", "version"})
@ToString(exclude = "stock")
@AllArgsConstructor
@NoArgsConstructor
public class EntryReceiptRequest {

    private Long productId;
    private Long quantity;
    private BigDecimal price;

}
