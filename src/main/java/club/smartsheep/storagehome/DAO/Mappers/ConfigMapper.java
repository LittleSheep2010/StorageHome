package club.smartsheep.storagehome.DAO.Mappers;

import club.smartsheep.storagehome.DAO.Entity.ConfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;

@Mapper
public interface ConfigMapper extends BaseMapper<ConfigEntity> {

    @Select("SELECT * FROM configs WHERE name='${name}'")
    @Cacheable(value="config", key="#name")
    ConfigEntity selectByName(String name) throws DataAccessException;
}
