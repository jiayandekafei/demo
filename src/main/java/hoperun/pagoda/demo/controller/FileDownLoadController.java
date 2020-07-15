package hoperun.pagoda.demo.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import hoperun.pagoda.demo.bean.FileDownLoadRequest;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.service.FileDownService;
import hoperun.pagoda.demo.utils.FileUtil;
import hoperun.pagoda.dxlAnalyse.constant.Constants;
import io.swagger.annotations.ApiOperation;

/**
 * Customer Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/file")
public class FileDownLoadController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownLoadController.class);

    /**
     * file download service.
     */
    @Autowired
    FileDownService fileDownLoadService;

    /**
     * File down load.
     * @param request request
     * @throws UnsupportedEncodingException  UnsupportedEncodingException
     */
    @GetMapping("/download")
    @ApiOperation(value = "result download")
    public void fileDownLoad(final FileDownLoadRequest request) throws UnsupportedEncodingException {
        final String method = "fileDownLoad";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "file download start started");
        }
        String path = fileDownLoadService.genTargetFilepath(request);
        String name = request.getFileName().lastIndexOf('.') > 1
                ? request.getFileName().substring(0, request.getFileName().lastIndexOf('.'))
                : request.getFileName();
        name = request.getType() == Constants.NUM_5 ? name : fileDownLoadService.getTargetFileName(request.getType(), path);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        String targetName = new String(name.getBytes("utf-8"), StandardCharsets.ISO_8859_1);
        response.setHeader("Content-Disposition", "attachment;filename=" + targetName);
        FileUtil.download(response, path.concat(File.separator).concat(name));

    }
}
