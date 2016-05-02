package com.formento.ecommerce.productPrice.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.productPrice.model.ProductPrice;

import java.time.LocalDate;

public class ProductPriceTemplate implements TemplateLoader {

    public static String VALID_CURRENT_PRODUCT_PRICE = "validCurrentProductPrice";
    public static String VALID_PRODUCT_PRICE_FROM_ONE_MONTH_AGO = "validProductPriceFromOneMonthAgo";
    public static String VALID_PRODUCT_PRICE_TO_NEXT_MONTH = "validProductPriceToNextMonth";

    @Override
    public void load() {
        Fixture.of(ProductPrice.class)
                .addTemplate(VALID_CURRENT_PRODUCT_PRICE, new Rule() {{
                    add("id", 10l);
                    add("initialDate", LocalDate.now());
                    add("product", new Product(1l, null, null, null, null));
                }})
                .addTemplate(VALID_PRODUCT_PRICE_FROM_ONE_MONTH_AGO, new Rule() {{
                    add("id", 11l);
                    add("initialDate", LocalDate.now().minusMonths(1));
                    add("product", new Product(1l, null, null, null, null));
                }})
                .addTemplate(VALID_PRODUCT_PRICE_TO_NEXT_MONTH, new Rule() {{
                    add("id", 12l);
                    add("initialDate", LocalDate.now().plusMonths(1));
                    add("product", new Product(1l, null, null, null, null));
                }});
    }

}
