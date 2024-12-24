package com.globalsoftwaresupport.spring.dto.response;

import java.util.Date;

public class ProductsResponse {

    private Long product_id;
    private String product_name;
    private String product_description;
    private Double product_price;
    private Integer stock_quantity;
    private String category;
    private Date created_date;
    private Date updated_date;
    private boolean deleted;

    public ProductsResponse() {
    }

    public ProductsResponse(Long product_id, String product_name, String product_description, Double product_price, Integer stock_quantity, String category, Date created_date, Date updated_date, boolean deleted) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.stock_quantity = stock_quantity;
        this.category = category;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.deleted = deleted;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public Integer getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
