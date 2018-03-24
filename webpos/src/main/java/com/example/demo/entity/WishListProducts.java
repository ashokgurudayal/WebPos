package com.example.demo.entity;
import java.util.Date;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * The entity for storing the different wish list products.
 * @author Ashok Sen Gurudayal
 *
 */
@Entity
public class WishListProducts {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long wish_list_products_id;
	@Temporal(TemporalType.DATE)
	private Date createts;
	@Temporal(TemporalType.DATE)
	private Date modifyts;
	
	//ManyToOne relationship with WishList
	@ManyToOne
	@JoinColumn(name = "wishListId")
	private WishList wishList;
	
	//ManyToOne relationship with Products
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	
	public WishList getWishList() {
		return wishList;
	}
	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public long getWish_list_products_id() {
		return wish_list_products_id;
	}
	public void setWish_list_products_id(long wish_list_products_id) {
		this.wish_list_products_id = wish_list_products_id;
	}
	public Date getCreatets() {
		return createts;
	}
	public void setCreatets(Date createts) {
		this.createts = createts;
	}
	public Date getModifyts() {
		return modifyts;
	}
	public void setModifyts(Date modifyts) {
		this.modifyts = modifyts;
	}
	@PrePersist
	protected void onCreate() {
		this.createts = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.modifyts = new Date();
	}
}
