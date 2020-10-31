package net.renfei.repository.entity;

public class SettingDO {
    private Long id;

    private String keys;

    private Integer orders;

    private String extend;

    private String values;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys == null ? null : keys.trim();
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend == null ? null : extend.trim();
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values == null ? null : values.trim();
    }
}