package net.renfei.entity;

import lombok.Data;

/**
 * <p>Title: UEditorUploadVO</p>
 * <p>Description: </p>
 *
 * @author RenFei
 */
@Data
public class UEditorUploadVO {
    private String state;
    private String url;
    private String title;
    private String original;
    private Long size;
    private String type;
}
