package net.renfei.entity;

import lombok.Data;
import net.renfei.repository.entity.PhotoDOWithBLOBs;

import java.util.List;

@Data
public class PhotoListDTO {
    private Long count;
    private List<PhotoDOWithBLOBs> photoDOWithBLOBs;
}
