package io.crowdcode.flaschenlager.stock.model;

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
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class StockEntryRequest {

    private Long productId;

    private Long quantity;

    private BigDecimal price;

}
