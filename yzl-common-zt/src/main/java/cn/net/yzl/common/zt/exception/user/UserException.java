package cn.net.yzl.common.zt.exception.user;

import cn.net.yzl.common.zt.exception.BiException;

public class UserException extends BiException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args , String message)
    {
        super("system:user", code, args, message);
    }

    public UserException(String code, Object[] args)
    {
        this("system:user", args, null);
    }



}
