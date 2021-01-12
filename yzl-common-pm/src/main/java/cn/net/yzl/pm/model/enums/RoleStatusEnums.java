package cn.net.yzl.pm.model.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * 角色状态  1开启 0关闭 枚举
 */
public enum RoleStatusEnums {
    /**
     * 开启
     */
    ENABLE_STATUS(1,"开启"),
    /**
     * 关闭
     */
    CLOSE_STATUS(0,"关闭");

    @Getter
    @Setter
    private Integer code;

    @Getter
    @Setter
    private String name;

    RoleStatusEnums(Integer code, String name){
        this.code=code;
        this.name=name;
    }

    /**
     * 根据业务状态编码返回名称
     * @param code
     * @return
     */
    public static String getNameByCode(Integer code) {
        String result="";
        try {
            result= Arrays.asList(RoleStatusEnums.values()).stream().filter(e->e.getCode().equals(code)).findFirst().get().getName();
        }catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }
}
