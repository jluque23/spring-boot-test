package com.globalsoftwaresupport.spring.views.products;

import com.globalsoftwaresupport.spring.dto.request.ProductRequest;
import com.globalsoftwaresupport.spring.dto.response.ProductResponse;
import com.globalsoftwaresupport.spring.services.interfaces.IProductService;
import com.globalsoftwaresupport.spring.views.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Route(value = "productView", layout = MainView.class)
@RouteAlias(value = "productView", layout = MainView.class)
@PageTitle("Products View")
public class ProductsView extends HorizontalLayout {

    private final IProductService productService;

    private Grid<ProductResponse> grid;

    @Autowired
    public ProductsView(final IProductService productService) {
        this.productService = productService;

        grid = generateProductGrid();

        getAllProducts();

        Button addProductBtn = new Button("Add Product");
        Dialog addProductModal = new Dialog();
        AddProductForm productForm = new AddProductForm(null);
        VerticalLayout addProductLayout = getAddProductLayout(productService, productForm, addProductModal);
        addProductModal.add(addProductLayout);
        addProductBtn.addClickListener(event -> addProductModal.open());

        VerticalLayout layout = new VerticalLayout(grid, addProductBtn);

        Dialog editProductModal = new Dialog();

        grid.addItemDoubleClickListener(event -> {
            editProductModal.removeAll();
            AddProductForm editProductForm = new AddProductForm(event.getItem());
            VerticalLayout editProductLayout = getEditProductLayout(productService, editProductForm, editProductModal);
            editProductModal.add(editProductLayout);
            editProductModal.open();
            getAllProducts();
        });



        add(layout);
    }

    private void getAllProducts() {
        grid.setItems(productService.findAllProducts());
    }

    private VerticalLayout getAddProductLayout(IProductService productService, AddProductForm productForm, Dialog addProductModal) {
        Button submitButton = new Button("Submit", event -> {
            // Handle form submission logic
            ProductRequest request = productForm.validateProduct();

            if (request != null) {
                productService.saveProduct(request);
                Notification.show("Product added successfully", 3000, Notification.Position.MIDDLE);
                getAllProducts();

            } else {
                Notification notification = Notification.show("You must complete everything", 3000, Notification.Position.MIDDLE);
                notification.getElement().getStyle().set("background-color", "red");
                notification.getElement().getStyle().set("color", "white"); // Set text color for contrast
                notification.getElement().getStyle().set("border-radius", "4px"); // Optional: rounded corners
                notification.getElement().getStyle().set("padding", "10px");
            }

            productForm.cleanUI();
            addProductModal.close();

        });

        // Add form and buttons to the dialog
        return new VerticalLayout(
                new Label("Please fill out the form:"),
                productForm,
                new HorizontalLayout(submitButton, new Button("Close", event -> {
                    productForm.cleanUI();
                    addProductModal.close();
                }))
        );
    }

    private VerticalLayout getEditProductLayout(IProductService productService, AddProductForm productForm, Dialog addProductModal) {
        Button editButton = new Button("Edit", event -> {
            // Handle form submission logic
            ProductRequest request = productForm.validateProduct();

            if (request != null) {
                request.setId(productForm.getCurrentProduct().getProduct_id());
                request.setCreatedAt(productForm.getCurrentProduct().getCreated_date());
                request.setUpdatedAt(new Date());
                productService.updateProduct(request);
                Notification.show("Product edited successfully", 3000, Notification.Position.MIDDLE);
                getAllProducts();

            } else {
                Notification notification = Notification.show("You must complete everything", 3000, Notification.Position.MIDDLE);
                notification.getElement().getStyle().set("background-color", "red");
                notification.getElement().getStyle().set("color", "white"); // Set text color for contrast
                notification.getElement().getStyle().set("border-radius", "4px"); // Optional: rounded corners
                notification.getElement().getStyle().set("padding", "10px");
            }

            productForm.cleanUI();
            addProductModal.close();
        });


        // Add form and buttons to the dialog
        return new VerticalLayout(
                new Label("Please fill out the form:"),
                productForm,
                new HorizontalLayout(editButton, new Button("Close", event -> {
                    productForm.cleanUI();
                    addProductModal.close();
                }))
        );
    }

    private static Grid<ProductResponse> generateProductGrid() {
        Grid<ProductResponse> grid = new Grid<>(ProductResponse.class, false);

        grid.addColumn(ProductResponse::getProduct_name).setHeader("Name");
        grid.addColumn(ProductResponse::getProduct_description).setHeader("Description");
        grid.addColumn(ProductResponse::getProduct_price).setHeader("Price");
        grid.addColumn(ProductResponse::getCategory).setHeader("Category");
        grid.addColumn(ProductResponse::getStock_quantity).setHeader("Stock Quantity");
        grid.addColumn(ProductResponse::getCreated_date).setHeader("Created Date");
        grid.addColumn(ProductResponse::getUpdated_date).setHeader("Updated Date");

        return grid;
    }


}
