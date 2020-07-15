package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hoperun.pagoda.dxlAnalyse.quantity.entity.AgentInfo;

/**
 * agent info mapper.
 *
 * @author zhangxiqin
 *
 */
@Mapper
public interface AgentInfoMapper {
    /**
     * retrieve all .
     * 
     * @param dbName dbName
     * @return agentInfo Data
     */
    List<AgentInfo> findByDB(String dbName);

    /**
     * insert.
     * 
     * @param agentInfo  Info
     */
    void insert(AgentInfo agentInfo);

    /**
     * delete by db name.
     * @param dbName db name
     */
    void deleteByDBName(String dbName);

}
