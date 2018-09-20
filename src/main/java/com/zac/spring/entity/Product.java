package com.zac.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the dtb_product database table.
 * 
 */
@Entity
@Table(name="dtb_product")
public class Product extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	@Getter
	@Setter
	private int productId;

	@Column(name="category_id")
	@Getter
	@Setter
	private int categoryId;

	@Column(name="creator_id")
	@Getter
	@Setter
	private int creatorId;

	@Column(name="del_flg")
	@Getter
	@Setter
	private short delFlg;

	@Lob
	@Column(name="description_detail")
	@Getter
	@Setter
	private String descriptionDetail;

	@Lob
	@Column(name="description_list")
	@Getter
	@Setter
	private String descriptionList;

	@Lob
	@Column(name="free_area")
	@Getter
	@Setter
	private String freeArea;

	@Lob
	@Column(name="name")
	@Getter
	@Setter
    @NotBlank (message = "Name is required")
	private String name;

	@Lob
	@Column(name="note")
	@Getter
	@Setter
	private String note;

	@Lob
	@Column(name="search_word")
	@Getter
	@Setter
	private String searchWord;

	@Column(name="status")
	@Getter
	@Setter
	private short status;
}