package club.smartsheep.storagehome.DAO.Mappers;

import club.smartsheep.storagehome.DAO.Entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
