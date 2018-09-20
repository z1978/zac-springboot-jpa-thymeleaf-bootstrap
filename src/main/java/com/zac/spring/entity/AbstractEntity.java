package com.zac.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Version;

import lombok.Getter;
import lombok.Setter;

/**
 * DB共通Entity
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 登録日 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	@Getter
	@Setter
	private Date createDate;

	/** 更新日 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	@Getter
	@Setter
	private Date updateDate;

	/** バージョン */
//	@Version
//	@Column(name = "VERSION")
//	private Integer version;

	/**
	 * 登録前処理
	 */
	@PrePersist
	public void prePersist() {
		// 登録日、更新日を設定
		Date date = new Date();
		createDate = date;
		updateDate = date;
	}

	/**
	 * 更新前処理
	 */
	@PreUpdate
	public void preUpdate() {
		// 更新日を設定
		updateDate = new Date();
	}
}