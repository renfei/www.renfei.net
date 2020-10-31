package net.renfei.entity;

import lombok.Data;
import net.renfei.repository.entity.VideoDOWithBLOBs;

import java.util.List;

@Data
public class VideoListDTO {
    private Long count;
    private List<VideoDOWithBLOBs> videos;
}
