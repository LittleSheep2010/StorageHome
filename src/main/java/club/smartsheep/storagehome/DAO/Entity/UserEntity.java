package club.smartsheep.storagehome.DAO.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("users")
public class UserEntity {
    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String password;
    private String email;
    private String username;
    private String role;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
