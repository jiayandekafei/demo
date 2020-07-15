package hoperun.pagoda.demo.service;

import hoperun.pagoda.demo.bean.FileDownLoadRequest;

/**
 * AAE transform service.
 * 
 * @author zhangxiqin
 *
 */
public interface FileDownService {

    /**
     * get target path.
     * @param request aae transform request.
     * @return target path
     */
    String genTargetFilepath(FileDownLoadRequest request);

    /**
     * get target file name.
     * @param type export file type.
     * @param path target path.
     * @return file name
     */
    String getTargetFileName(int type, String path);

}
