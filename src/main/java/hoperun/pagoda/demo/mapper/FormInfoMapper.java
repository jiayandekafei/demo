package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hoperun.pagoda.dxlAnalyse.quantity.entity.FormInfo;

/**
 * Form info mapper.
 *
 * @author zhangxiqin
 *
 */
@Mapper
public interface FormInfoMapper {
    /**
     * retrieve all .
     * 
     * @param dbName dbName
     * @return FormInfo Data
     */
    List<FormInfo> findAllFormByDB(String dbName);

    /**
     * insert.
     * 
     * @param formInfo  Info
     */
    void insert(FormInfo formInfo);

    /**
     * delete by db name.
     * @param dbName db name
     */
    void deleteByDBName(String dbName);

}
