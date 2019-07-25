package hoperun.pagoda.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Business exception
 * @author zhangxiqin
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = -5148603229935097596L;
	private  String errorCode;
	private  String errorMsg;
}
