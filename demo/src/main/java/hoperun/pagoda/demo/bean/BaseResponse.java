package hoperun.pagoda.demo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *coomon response bean 
 *
 * @author zhangxiqin
 * @param <T>
 */
@ToString
@NoArgsConstructor
@Data
public class BaseResponse<T> {
	protected  Integer status=1;
	private  String errorCode;
	private  String errorMsg;
	private  T data;
	
	public BaseResponse(Integer status, T data) {
		this.status = status;
		this.data = data;
	}
	
	public BaseResponse(Integer status, String errorCode, String errorMsg) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public static <T>BaseResponse<T> success(T data) {
	   return new BaseResponse<T>(0,data);
	}
	
	public static <T>BaseResponse<T> fail(String errorCode,String errorMsg) {
		return new BaseResponse<T>(1,errorCode,errorMsg);
	}

}
