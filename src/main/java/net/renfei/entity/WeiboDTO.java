package net.renfei.entity;

import lombok.Data;

import java.util.List;

@Data
public class WeiboDTO {
    private Long count;
    private List<WeiboDOS> weiboDOList;
}
