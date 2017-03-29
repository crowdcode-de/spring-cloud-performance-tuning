package io.crowdcode.flaschenlager.apigateway.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class OrderRequest {

    private Long productId;

    private Long quantity;

}
