package io.crowdcode.flaschenlager.product.fixture;

import io.crowdcode.flaschenlager.product.model.Product;

/**
 * Created by idueppe on 24.03.17.
 */
public class ProductFixture {
    public static Product buildDefaultProduct() {
        return new Product()
                .setName("PRODUCT_NAME")
                .setDescription("PRODUCT_DESCRIPTION")
                .setAmount(123.45d)
                .setUnit("1L");
    }


    public static Product buildPersistentProduct() {
        return new Product()
                .setName("PRODUCT_NAME_2")
                .setDescription("PRODUCT_DESCRIPTION_2")
                .setAmount(123.45d)
                .setUnit("1L")
                .setId(-2L);

    }
}
