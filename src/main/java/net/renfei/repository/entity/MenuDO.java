package net.renfei.repository.entity;

public class MenuDO {
    private Long id;

    private Long pid;

    private Boolean isNewWin;

    private Boolean isEnable;

    private Integer menuType;

    private Integer orderNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Boolean getIsNewWin() {
        return isNewWin;
    }

    public void setIsNewWin(Boolean isNewWin) {
        this.isNewWin = isNewWin;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}