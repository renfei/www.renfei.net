package net.renfei.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.renfei.sdk.comm.StateCode;

/**
 * <p>Title: ServiceEx</p>
 * <p>Description: 业务服务异常</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    private StateCode stateCode;

    private ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
        this.stateCode = StateCode.Failure;
    }

    public ServiceException(StateCode stateCode, String message) {
        super(message);
        this.stateCode = stateCode;
    }
}
