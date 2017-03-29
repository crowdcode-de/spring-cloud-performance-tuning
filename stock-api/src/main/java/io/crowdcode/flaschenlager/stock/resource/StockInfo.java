package io.crowdcode.flaschenlager.stock.resource;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode()
@ToString()
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo {

    private Long stockId;

    private String name;
}
