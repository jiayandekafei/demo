package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hoperun.pagoda.dxlAnalyse.quantity.entity.ViewInfo;

/**
 * View info mapper.
 *
 * @author zhangxiqin
 *
 */
@Mapper
public interface ViewInfoMapper {
    /**
     * retrieve all .
     * 
     * @param dbName dbName
     * @return AgentInfo Data
     */
    List<ViewInfo> findByDB(String dbName);

    /**
     * insert.
     * 
     * @param viewInfo  Info
     */
    void insert(ViewInfo viewInfo);

    /**
     * delete by db name.
     * @param dbName db name
     */
    void deleteByDBName(String dbName);

}
