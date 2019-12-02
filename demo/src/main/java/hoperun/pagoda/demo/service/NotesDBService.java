package hoperun.pagoda.demo.service;

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
     */
    void export(int type, int groupId, String dbName);

}
