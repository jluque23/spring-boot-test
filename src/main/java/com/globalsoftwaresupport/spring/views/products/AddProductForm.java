package com.globalsoftwaresupport.spring.views.products;

import com.globalsoftwaresupport.spring.dto.request.ProductRequest;
import com.globalsoftwaresupport.spring.dto.response.ProductResponse;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Route("/addProductForm")
public class AddProductForm extends FormLayout {

    private TextField nameField = new TextField("Name");
    private TextField descriptionField = new TextField("Description");
    private NumberField priceField = new NumberField("Price");
    private IntegerField stockQuantityField = new IntegerField("Stock Quantity");
    private TextField categoryField = new TextField("Category");
    private Checkbox activeBox = new Checkbox("Active");

    private ProductResponse currentProduct;

    @Autowired
    public AddProductForm(ProductResponse productResponse) {
        this.currentProduct = productResponse;
        VerticalLayout layout = new VerticalLayout();
        layout.add(nameField, descriptionField, priceField, stockQuantityField, categoryField, activeBox);
        add(layout);
        setProduct(productResponse);
    }

    public ProductRequest validateProduct() {
        ProductRequest pr = new ProductRequest();

        if (nameField.getValue().isBlank() ||
                nameField.getValue().isEmpty() ||
                descriptionField.getValue().isBlank() ||
                descriptionField.getValue().isEmpty() ||
                priceField.isEmpty() ||
                priceField.getOptionalValue().isEmpty() ||
                stockQuantityField.getValue() < 1 ||
                stockQuantityField.getValue() < 1 ||
                categoryField.getValue().isBlank() ||
                categoryField.getValue().isEmpty()) {
            return null;
        }

        pr.setName(nameField.getValue());
        pr.setDescription(descriptionField.getValue());
        pr.setPrice(priceField.getValue());
        pr.setStockQuantity(stockQuantityField.getValue());
        pr.setCategory(categoryField.getValue());
        pr.setActive(activeBox.getValue());

        return pr;
    }

    public void setProduct(ProductResponse productResponse) {
        this.currentProduct = productResponse;

        if (productResponse != null) {
            nameField.setValue(productResponse.getProduct_name());
            descriptionField.setValue(productResponse.getProduct_description());
            priceField.setValue(productResponse.getProduct_price());
            stockQuantityField.setValue(productResponse.getStock_quantity());
            categoryField.setValue(productResponse.getCategory());
        }

    }

    public void cleanUI() {
        nameField.clear();
        descriptionField.clear();
        priceField.clear();
        stockQuantityField.clear();
        categoryField.clear();
        activeBox.setValue(false);
    }

    public ProductResponse getCurrentProduct() {
        return currentProduct;
    }
}
