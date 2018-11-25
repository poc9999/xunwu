package com.imooc.base.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/18 21:35
 * Description  : 七牛云实现类
 */
@Service
public class QiNiuServiceImpl implements IQiNiuService,InitializingBean {

    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private BucketManager bucketManager;
    @Autowired
    private Auth auth;

    @Value("${qiniu.bucket}")
    private String bucket;

    private StringMap putPolicy;

    @Override
    public Response uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file,null,getUploadToken());
        int retry = 0;
        /**
         * 重传次数
         */
        while(response.needRetry() && retry++ < 3){
            this.uploadManager.put(file,null,getUploadToken());
        }
        return response;
    }

    @Override
    public Response uploadSteam(InputStream inputStream) throws QiniuException {

        String token = getUploadToken();
        Response response = this.uploadManager.put(inputStream,null,token,null,null);

        int retry = 0;
        while(response.needRetry() && retry++ <3){
            this.uploadManager.put(inputStream,null,getUploadToken(),null,null);
        }
        return response;
    }

    @Override
    public Response delete(String key) throws QiniuException {
        Response response = this.bucketManager.delete(bucket,key);
        return response;
    }
    /**
     * 实例化StringMap
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width),\"height\":$(imageInfo.height)}");
    }
    /**
     * 获取上传凭证
     * @return
     */
    private String getUploadToken(){
        return this.auth.uploadToken(bucket,null,3600,putPolicy);
    }
}
