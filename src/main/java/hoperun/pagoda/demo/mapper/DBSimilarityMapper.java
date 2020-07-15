package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hoperun.pagoda.demo.entity.DBSimilarity;

/**
 * DB similarity mapper.
 *
 * @author zhangxiqin
 *
 */
@Mapper
public interface DBSimilarityMapper {
    /**
     * retrieve all .
     * 
     * @param groupId groupId
     * @return DBSimilarity Data
     */
    List<DBSimilarity> findCateGoryDataByGroupId(int groupId);

    /**
     * insert.
     * 
     * @param dbSimilarity dbSimilarity
     */
    void insert(DBSimilarity dbSimilarity);

    /**
     * delete .
     * 
     * @param groupId groupId
     */
    void deleteByGroupId(int groupId);
}
