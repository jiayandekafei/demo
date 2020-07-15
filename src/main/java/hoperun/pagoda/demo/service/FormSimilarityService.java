package hoperun.pagoda.demo.service;

import java.util.List;

import hoperun.pagoda.demo.entity.FormSimilarity;

/**
 *  DB similarity service.
 * 
 * @author zhangxiqin
 *
 */
public interface FormSimilarityService {

    /**
     * insert data.
     * @param formSimilarity formSimilarity
     */
    void insert(FormSimilarity formSimilarity);

    /**
     * retrieve form similarity data by db name.
     * @param dbName dbName
     * @return List<FormSimilarity> form similarity data
     */
    List<FormSimilarity> getSimiDataByName(String dbName);

    /**
     * delete.
     * @param dbName dbName
     */
    void deleteByDbName(String dbName);

}
