package net.renfei.exception;

/**
 * <p>Title: MyException</p>
 * <p>Description: 业务逻辑异常</p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable t) {
        super(msg);
    }
}
