package cn.hd.module.repository.service;

import cn.hd.utils.ResponseJSON;

import java.io.InputStream;

/**
 * Created by DELL on 2017/12/18.
 */
public interface FileService {
    /**
     * 保存文件到本地[]
     * @param in 文件流
     * @param fileName 保存本地或者上传到服务器的文件名称
     * @return
     */
    String pushSystemUploadFile(InputStream in, String fileName,String localFilePath);

}
