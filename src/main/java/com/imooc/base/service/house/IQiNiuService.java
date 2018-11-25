package com.imooc.base.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/18 21:32
 * Description  : 七牛云文件上传抽象服务
 */
public interface IQiNiuService {

    Response uploadFile(File file) throws QiniuException;

    Response uploadSteam(InputStream inputStream) throws QiniuException;

    Response delete(String key) throws QiniuException;
}
