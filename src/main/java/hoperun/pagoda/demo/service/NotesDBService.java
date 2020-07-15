package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.DBInfoRequest;
import hoperun.pagoda.demo.bean.DBInfoResponse;
import hoperun.pagoda.demo.bean.DBSimiListResponse;
import hoperun.pagoda.demo.bean.FormSimiListResponse;

/**
 * notes DB service.
 * 
 * @author zhangxiqin
 *
 */
public interface NotesDBService {

    /**
     * export DB info.
     * @param type 1 if export single DB otherwise 2
     * @param groupId target group id
     * @param dbName target db name(only for single export)
     * @return BaseResponse<String>
     */
    BaseResponse<String> export(int type, int groupId, String dbName);

    /**
     * retrieve DB List.
     * @param request request
     * @return  DBInfoResponse
     */
    DBInfoResponse retrieveDBInfo(DBInfoRequest request);

    /**
     * DB similarity analyze.
     * @param type compare type
     * @param groupId target group id
     * @param hideExcept if except hide field
     * @param simiValue similarity value
     * @return BaseResponse<String>
     */
    BaseResponse<String> dbSimilarityAnalyze(int type, int groupId, boolean hideExcept, double simiValue);

    /**
     * retrieve DB similarity data.
     * @param groupId group id
     * @return  DBSimiListResponses
     */
    DBSimiListResponse retrieveDBSimiData(int groupId);

    /**
     * Form similarity analyze.
     * @param dbName db name
     * @param groupId groupId
     * @return  BaseResponse<String>
     */
    BaseResponse<String> formSimilarityAnalyze(String dbName, int groupId);

    /**
     * retrieve form similarity data.
     * @param dbName dbName
     * @return  FormSimiListResponse
     */
    FormSimiListResponse retrieveFormSimiData(String dbName);

}
