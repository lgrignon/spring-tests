package fr.wcs.springtests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import fr.wcs.springtests.data.DemoService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

record Product(String name, float price) {
}

public class DemoFeatureTest {

    List<Product> products;
    DemoService demoService = new DemoService();
    List<Product> selectedProducts;

    @Given("the following products:")
    public void givenProducts(List<List<String>> products) {
        System.out.println("given products: " + products);
        this.products = products.stream()
                .map(row -> new Product(row.get(0), row.size() > 1 ? Float.parseFloat(row.get(1)) : 0)).toList();
    }

    @Given("the following catalog:")
    public void givenCatalog(List<List<String>> catalog) {
        System.out.println("given catalog: " + catalog);
    }

    @When("I select liquid products")
    public void selectLiquidProducts() {
        selectedProducts = products.stream().filter(product -> demoService.isLiquid(product.name())).toList();
    }

    @When("I select first product which is not liquid")
    public void selectFirstNotLiquidProduct() {
        selectedProducts = products.stream().filter(product -> !demoService.isLiquid(product.name())).limit(1).toList();
    }

    @Then("{int} product(s) selected and name is {string}")
    public void assertSelectedProducts(int count, String name) {
        System.out.println("assertSelectedProducts - selected=" + selectedProducts);
        assertEquals(count, selectedProducts.size());
        assertEquals(name, selectedProducts.get(0).name());
    }
}
