package com.yc.damai.po;

import java.util.List;

public class DmCategory {
    private Integer id;

    private String cname;

    private Integer pid;

    //当前子类的集合
    private List<DmCategory> chrildren;
    
   
	public List<DmCategory> getChrildren() {
		return chrildren;
	}

	public void setChrildren(List<DmCategory> chrildren) {
		this.chrildren = chrildren;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}