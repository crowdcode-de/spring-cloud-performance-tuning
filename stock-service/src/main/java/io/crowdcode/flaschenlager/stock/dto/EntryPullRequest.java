package io.crowdcode.flaschenlager.stock.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode()
@ToString()
@AllArgsConstructor
@NoArgsConstructor
public class EntryPullRequest {

    @NotNull
    private Long productId;
    @NotNull
    private Long quantity;

}
