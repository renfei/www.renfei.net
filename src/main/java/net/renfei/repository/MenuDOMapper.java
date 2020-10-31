package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.MenuDO;
import net.renfei.repository.entity.MenuDOExample;
import net.renfei.repository.entity.MenuDOWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDOMapper {
    long countByExample(MenuDOExample example);

    int deleteByExample(MenuDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MenuDOWithBLOBs record);

    int insertSelective(MenuDOWithBLOBs record);

    List<MenuDOWithBLOBs> selectByExampleWithBLOBs(MenuDOExample example);

    List<MenuDO> selectByExample(MenuDOExample example);

    MenuDOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MenuDOWithBLOBs record, @Param("example") MenuDOExample example);

    int updateByExampleWithBLOBs(@Param("record") MenuDOWithBLOBs record, @Param("example") MenuDOExample example);

    int updateByExample(@Param("record") MenuDO record, @Param("example") MenuDOExample example);

    int updateByPrimaryKeySelective(MenuDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MenuDOWithBLOBs record);

    int updateByPrimaryKey(MenuDO record);
}