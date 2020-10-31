package net.renfei.entity;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: ListData</p>
 * <p>Description: 数据承载类</p>
 *
 * @author RenFei
 */
@Data
public class ListData<T> {
    List<T> data;
    Long total;
}
