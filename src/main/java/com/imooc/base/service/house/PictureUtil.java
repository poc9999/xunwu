package com.imooc.base.service.house;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/18 22:47
 * Description  :
 */
public class PictureUtil {

    public static String accessKey = "ztLuaY8zxOYgilGa9ytmlfW2q86sygBr40LpqMOs";
    public static String secretKey = "CLwa2dACDel17_hMH7_u0ZIzKNbTsZPncZ_3-9ns";
    public static String bucket = "xunwu";

    private static String QINIU_IMAGE_DOMAIN = "http://pie1bczvx.bkt.clouddn.com";//改成他给你的免费的域名，或者自己解析一个域名，买一个很便宜

    //普通本地上传
    public static String upload(String localFilePath) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());// 这里是华南，对应的2
        UploadManager uploadManager = new UploadManager(cfg);
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        DefaultPutRet putRet = null;

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            // 解析上传成功的结果
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                // ignore
            }
        }
        return QINIU_IMAGE_DOMAIN +"/"+ putRet.hash;
    }

    //文件流上传
    public static String upload(InputStream file) throws IOException {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        DefaultPutRet putRet = null;
        try {
//            byte[] uploadBytes = file.getBytes();
//            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    // ignore
                }
            }
        } catch (Exception ex) {
            // ignore
        }
        return QINIU_IMAGE_DOMAIN + putRet.hash;
    }

    public static void main(String[] args) {
//        String url = PictureUtil.upload("E:\\WorkSpace\\xunwu\\temp\\a.jpg");

        File file = new File("E:\\WorkSpace\\xunwu\\temp\\a.jpg");

        try {
            InputStream inputStream  = new FileInputStream(file);

            PictureUtil.upload(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(url);
    }

}
