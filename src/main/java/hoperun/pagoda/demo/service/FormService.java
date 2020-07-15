package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.FormInfoRequest;
import hoperun.pagoda.demo.bean.FormInfoResponse;

/**
 * form service.
 * 
 * @author zhangxiqin
 *
 */
public interface FormService {

    /**
     * retrieve from List.
     * @param request request
     * @return  FormInfoResponse
     */
    FormInfoResponse retrieveFormInfo(FormInfoRequest request);

}
