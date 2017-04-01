package io.crowdcode.flaschenhals.order.controller;


import io.crowdcode.flaschenhals.order.resource.OrderRequest;
import io.crowdcode.flaschenhals.order.service.OrderService;
import io.crowdcode.flaschenhals.stock.resource.StockEntryResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("Create new stock")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StockEntryResponse> createStock(@RequestBody @Valid OrderRequest orderRequest) {
        return orderService.order(orderRequest.getProductId(),orderRequest.getQuantity());
    }


}
