package club.smartsheep.storagehome.DAO.Mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExecuteMapper {
    
    @Insert("${script}")
    void execute(String script);
}
