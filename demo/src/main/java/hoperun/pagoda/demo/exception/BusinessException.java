package hoperun.pagoda.demo.exception;

import hoperun.pagoda.demo.bean.BaseResponse;
import lombok.Getter;

/**
 * Business exception
 * 
 * @author zhangxiqin
 *
 */
@Getter
public class BusinessException extends RuntimeException {
    private BaseResponse response;

    public BusinessException(BaseResponse response) {
        this.response = response;
    }
}
