package net.renfei.entity;

import lombok.Data;
import net.renfei.repository.entity.WeiboDO;

@Data
public class WeiboDOS extends WeiboDO {
    private String img;
    private Long comments;
}
