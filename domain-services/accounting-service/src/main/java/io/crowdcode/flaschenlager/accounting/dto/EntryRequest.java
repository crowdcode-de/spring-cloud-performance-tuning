package io.crowdcode.flaschenlager.accounting.dto;

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
@EqualsAndHashCode()
@ToString()
@AllArgsConstructor
@NoArgsConstructor
public class EntryRequest {

    @NotNull
    private Long quantity;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Long productId;

}
