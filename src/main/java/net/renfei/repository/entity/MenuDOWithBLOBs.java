package net.renfei.repository.entity;

public class MenuDOWithBLOBs extends MenuDO {
    private String menuText;

    private String menuLink;

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText == null ? null : menuText.trim();
    }

    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink == null ? null : menuLink.trim();
    }
}