package hoperun.pagoda.demo.exception;

import hoperun.pagoda.demo.bean.BaseResponse;
import lombok.Getter;

/**
 * Business exception.
 * 
 * @author zhangxiqin
 *
 */
@Getter
public class BusinessException extends RuntimeException {
    /**
     * serial id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * common response.
     */
    private final BaseResponse<?> response;

    /**
     * business exception.
     * @param mResponse common response.
     */
    public BusinessException(final BaseResponse<?> mResponse) {
        this.response = mResponse;
    }
}
