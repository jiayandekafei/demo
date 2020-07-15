package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.AAETransformRequest;
import hoperun.pagoda.demo.bean.BaseResponse;

/**
 * AAE transform service.
 * 
 * @author zhangxiqin
 *
 */
public interface AAETransformService {

    /**
     * transform.
     * @param request aae transform request.
     * @return BaseResponse<String>
     */
    BaseResponse<String> transform(AAETransformRequest request);

}
