package net.renfei.entity;

import lombok.Data;
import net.renfei.repository.entity.VideoDOWithBLOBs;
import net.renfei.repository.entity.VideoSourceDOWithBLOBs;
import net.renfei.repository.entity.VideoTrackDOWithBLOBs;

import java.util.List;

@Data
public class VideoDTO extends VideoDOWithBLOBs {
    private String categoryZhName;
    private String categoryEnName;
    private String categoryTypeName;
    private Long comments;
    List<VideoSourceDOWithBLOBs> videoSource;
    List<VideoTrackDOWithBLOBs> videoTrack;
}
