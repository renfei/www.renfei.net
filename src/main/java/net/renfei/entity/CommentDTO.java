package net.renfei.entity;

import lombok.Data;
import net.renfei.repository.entity.CommentsDOWithBLOBs;

import java.util.List;

@Data
public class CommentDTO extends CommentsDOWithBLOBs {
    private List<CommentDTO> child;
}
