package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hoperun.pagoda.demo.entity.FormSimilarity;

/**
 * Form similarity mapper.
 *
 * @author zhangxiqin
 *
 */
@Mapper
public interface FormSimilarityMapper {
    /**
     * retrieve all .
     * 
     * @param dbName dbName
     * @return FormSimilarity Data
     */
    List<FormSimilarity> findFormSimiDataByDbName(String dbName);

    /**
     * insert.
     * 
     * @param formSimilarity formSimilarity
     */
    void insert(FormSimilarity formSimilarity);

    /**
     * delete .
     * 
     * @param dbName dbName
     */
    void deleteByDBName(String dbName);
}
