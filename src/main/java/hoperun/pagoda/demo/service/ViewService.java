package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.ViewInfoRequest;
import hoperun.pagoda.demo.bean.ViewInfoResponse;

/**
 * view service.
 * 
 * @author zhangxiqin
 *
 */
public interface ViewService {

    /**
     * retrieve view List.
     * @param request request
     * @return  ViewInfoResponse
     */
    ViewInfoResponse retrieveViewInfo(ViewInfoRequest request);

}
