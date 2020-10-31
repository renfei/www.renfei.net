package net.renfei.entity;

import lombok.Data;
import net.renfei.repository.entity.PhotoImgDO;

import java.util.List;

@Data
public class PhotoImgDTO {
    List<PhotoImgDO> photoImgs;
}
