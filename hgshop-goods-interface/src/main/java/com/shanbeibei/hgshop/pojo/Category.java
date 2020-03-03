package com.shanbeibei.hgshop.pojo;

import java.io.Serializable;
/**
 * 
 * @author shanbeibei
  *分类
 */
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5401575918676996649L;
	private Integer id;
	private Integer parentId; 
	private String name;
	private String path;
	public Integer getId() {
		return id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public String getName() {
		return name;
	}
	public String getPath() {
		return path;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
