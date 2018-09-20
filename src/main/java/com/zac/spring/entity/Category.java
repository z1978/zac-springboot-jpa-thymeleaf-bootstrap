package com.zac.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the dtb_category database table.
 * 
 */
@Entity
@Table(name="dtb_category")
public class Category extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="category_id")
	@Getter
	@Setter
	private int categoryId;

	@Column(name="parent_category_id")
	@Getter
	@Setter
	private int parentCategoryId;

	@Column(name="creator_id")
	@Getter
	@Setter
	private int creatorId;

	@Column(name="category_name")
	@Getter
	@Setter
	private String categoryName;

	@Column(name="level")
	@Getter
	@Setter
	private int level;

	@Column(name="rank")
	@Getter
	@Setter
	private int rank;

	@Column(name="del_flg")
	@Getter
	@Setter
	private short delFlg;

}