package shop.vo;

import java.sql.Date;

//CREATE TABLE shop(
//product_cod VARCHAR(20) PRIMARY KEY,
//buyer_id VARCHAR(15),
//product_name VARCHAR(50) NOT NULL UNIQUE,
//price INT NOT NULL,
//stock INT NOT NULL,
//product_image VARCHAR(500) NOT NULL,
//product_info VARCHAR(1000) NOT NULL,
//purchase_count INT NOT NULL,
//date DATETIME NOT NULL,
//FOREIGN KEY shop(buyer_id) REFERENCES member(id)
//);

public class ShopBean {
	private String product_cod;
	private String buyer_id;
	private String product_name;
	private int price;
	private int stock;
	private String product_image;
	private String product_info;
	private int purchase_count;
	private Date date;
	
	
	public ShopBean() {}


	public ShopBean(String product_cod, String buyer_id, String product_name, int price, int stock,
			String product_image, String product_info, int purchase_count, Date date) {
		super();
		this.product_cod = product_cod;
		this.buyer_id = buyer_id;
		this.product_name = product_name;
		this.price = price;
		this.stock = stock;
		this.product_image = product_image;
		this.product_info = product_info;
		this.purchase_count = purchase_count;
		this.date = date;
	}


	public String getProduct_cod() {
		return product_cod;
	}


	public void setProduct_cod(String product_cod) {
		this.product_cod = product_cod;
	}


	public String getBuyer_id() {
		return buyer_id;
	}


	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getProduct_image() {
		return product_image;
	}


	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}


	public String getProduct_info() {
		return product_info;
	}


	public void setProduct_info(String product_info) {
		this.product_info = product_info;
	}


	public int getPurchase_count() {
		return purchase_count;
	}


	public void setPurchase_count(int purchase_count) {
		this.purchase_count = purchase_count;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	
	
	
}
