package net.renfei.entity;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: HeadVO</p>
 * <p>Description: </p>
 *
 * @author RenFei
 */
@Data
public class HeadVO {
    private String description;
    private String keywords;
    private List<String> css;
    private List<String> jss;
    private String cssText;
    private String jsText;
    private OGprotocol ogprotocol;
}
