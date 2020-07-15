package hoperun.pagoda.demo.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import hoperun.pagoda.dxlAnalyse.constant.Constants;

/**
 * file utils.
 * @author zhangxiqin
 *
 */
public final class FileUtil {

    /**
     */
    private FileUtil() {
    }

    /**
     * down load file.
     * @param res http servelet response.
     * @param filePath file path .
     */
    public static void download(final HttpServletResponse res, final String filePath) {
        byte[] buff = new byte[Constants.BYTELENGTH];
        try (OutputStream os = res.getOutputStream(); BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath)))) {
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
