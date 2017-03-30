package io.crowdcode.flaschenlager.order.service;

import io.crowdcode.flaschenlager.stock.resource.EntryPullRequest;
import io.crowdcode.flaschenlager.stock.resource.StockEntryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    public List<StockEntryResponse> order(Long productId, Long quantity) {

        log.info("Wait 1 Second");

        EntryPullRequest entryPullRequest = new EntryPullRequest(productId, quantity);

        HttpEntity<EntryPullRequest> httpEntity = new HttpEntity<>(entryPullRequest, jsonContentTypeHeaders());
        ResponseEntity<StockEntryResponse[]> response = restTemplate
                .exchange("http://stock-service/{stockId}/entries", HttpMethod.POST, httpEntity,
                StockEntryResponse[].class, 1l);

        return Arrays.asList(response.getBody());
    }


    private HttpHeaders jsonContentTypeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        return headers;
    }
}
