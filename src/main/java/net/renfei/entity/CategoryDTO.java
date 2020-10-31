package net.renfei.entity;

import lombok.Data;
import net.renfei.repository.entity.CategoryDO;

@Data
public class CategoryDTO extends CategoryDO {
    private String typeName;
    private String uriPath;
}
