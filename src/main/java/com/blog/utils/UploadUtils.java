package com.blog.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectRequest;
import com.blog.comment.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author yangxs
 */
@Slf4j
public class UploadUtils {

    public static void uploadAliyun(String key,byte[] bytes){

        OSSClient client = new OSSClient(Constants.END_POINT, Constants.ACCESS_KEY_ID, Constants.ACCESS_KEY_SECRET);
        try {
            /**
             * Note that there is the way of uploading an object to your bucket,
             *  this way of uploading by byte.
             */
            try {
                client.putObject(new PutObjectRequest(Constants.BUCKET_NAME, key, new ByteArrayInputStream(bytes)));
                client.setObjectAcl(Constants.BUCKET_NAME, key, CannedAccessControlList.PublicRead);
            } catch (Exception e) {
                log.error("upload object error",e);
            }
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message:" + oe.getErrorCode());
            log.error("Error Code:" + oe.getErrorCode());
            log.error("Request ID:" + oe.getRequestId());
            log.error("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
    }

    public static void delete(String key){
        OSSClient client = new OSSClient(Constants.END_POINT, Constants.ACCESS_KEY_ID, Constants.ACCESS_KEY_SECRET);
        try {
            client.deleteObject(Constants.BUCKET_NAME, key);
        } catch (OSSException oe) {
            log.error("delete object error",oe);
        }finally {
            client.shutdown();
        }
    }
}
