package cn.net.yzl.common.zt.exception.user;

import cn.net.yzl.common.enums.ResponseCodeEnums;

public class UserHasNoPermissonException extends UserException{
    private static final long serialVersionUID = 1L;

    public UserHasNoPermissonException()
    {
        super(  ResponseCodeEnums.AUTHOR_ERROR_CODE.getCode().toString() , null , "user.has.no.current.permission");
    }
}
