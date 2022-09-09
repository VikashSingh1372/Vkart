package com.Vkart.DTO;

import lombok.Data;

@Data
public class ProductDTO {

	private Long productId;
	private String productName;
	private String productDescription;
	private Double productWeight;
	private Double productPrice;
	private String productImage;
	private int categoryid;

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Double getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(Double productWeight) {
		this.productWeight = productWeight;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
}
