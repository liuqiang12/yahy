package cn.hd.module.repository.service.impl;

import cn.hd.module.repository.service.FileService;
import cn.hd.utils.ResponseJSON;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DELL on 2017/12/18.
 */
@Component("fileService")
public class FileServiceImpl implements FileService {
    @Override
    public String pushSystemUploadFile(InputStream in, String fileName,String localFilePath) {
        System.out.println("-------------------保存到本地----------------------");
        /*  */
        String outFilePath = wirteLocalFile(in,fileName,localFilePath);
        return outFilePath;
    }


    public String wirteLocalFile(InputStream input,String fileName,String localFilePath){
        System.out.println("保存本地文件目录:["+localFilePath+"]");
        File file = new File(localFilePath);
        if(!file.exists()){
            file.mkdirs();
        }
        //fileName
        //创建的文件名称是:文件名称任意
        String outFilePath = file.getPath()+File.separator+fileName;
        try {
            FileOutputStream out = new FileOutputStream(outFilePath);
            byte[] buf = new byte[1024 * 8];
            while (true) {
                int read = 0;
                if (input != null) {
                    read = input.read(buf);
                }
                if (read == -1) {
                    break;
                }
                out.write(buf, 0, read);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传文件:[----------------失败--------------]");
            return null;
        }
        System.out.println("上传文件:[----------------成功--------------]");
        return outFilePath;
    }
}
