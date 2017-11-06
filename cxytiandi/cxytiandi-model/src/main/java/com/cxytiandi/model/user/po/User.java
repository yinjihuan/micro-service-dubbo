package com.cxytiandi.model.user.po;

import java.io.Serializable;
import com.cxytiandi.jdbc.annotation.Field;
import com.cxytiandi.jdbc.annotation.TableName;

@TableName(value="user", desc="用户表", author="yinjihuan")
public class User implements Serializable {

	private static final long serialVersionUID = 8169572460745864768L;

	@Field(value="id", desc="主键ID")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
