package com.yc.damai.po;

public class DmOrderitem {
    private Integer id;

    private Integer count;

    private Double total;

    private Integer pid;

    private Integer oid;
    
    //订单明细记录的商品的对象
    private DmProduct product;
    
    public DmProduct getProduct() {
		return product;
	}

	public void setProduct(DmProduct product) {
		this.product = product;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }
}