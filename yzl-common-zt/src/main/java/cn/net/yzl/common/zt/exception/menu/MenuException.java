package cn.net.yzl.common.zt.exception.menu;

import cn.net.yzl.common.zt.exception.BiException;

public class MenuException extends BiException {
    private static final long serialVersionUID = 1L;

    public MenuException(String code, Object[] args , String message)
    {
        super("system:menu", code, args, message);
    }

    public MenuException(String code, Object[] args)
    {
        this("system:menu", args, null);
    }

}
