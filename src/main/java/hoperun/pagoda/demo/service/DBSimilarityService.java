package hoperun.pagoda.demo.service;

import java.util.List;

import hoperun.pagoda.demo.bean.DBSimiPieResponse;
import hoperun.pagoda.demo.entity.DBSimilarity;

/**
 *  DB similarity service.
 * 
 * @author zhangxiqin
 *
 */
public interface DBSimilarityService {

    /**
     * insert data.
     * @param dbSimilarity dbSimilarity
     */
    void insert(DBSimilarity dbSimilarity);

    /**
     * retrieve similarity data by group.
     * @param groupId groupId
     * @return List<DBSimilarity> DB similarity data
     */
    List<DBSimilarity> getSimiDataByGroup(int groupId);

    /**
     * delete.
     * @param groupId groupId
     */
    void deleteSimiDataByGroupId(int groupId);

    /**
     * get db simi distribution data.
     * @param groupId group id
     * @return DB similarity pie data
     * 
     */
    DBSimiPieResponse getDBSimiDistributionData(int groupId);

}
