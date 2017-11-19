package cn.tms.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.log4j.Logger;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * 阿里云oss文件上传工具类
 * Created by gz on 2017/10/25.
 * qq:1293443962
 */
public class OssUtil {

    private static Logger logger = Logger.getLogger(OssUtil.class);

    private static String bucket_name = "image1024";
    private static String bucket_source = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIYkTqKBP4aiVk";
    private static String secretAccessKey = "lORhaPDsXAnUAoC3GIaUHLKAVectC3";

    /**
     * 文件上传
     *
     * @param filepath 本地地址
     * @return
     */
    public static String getOSSUrl(String filepath) {
        try {
            File file = new File(filepath);
            String file_name = filepath.split("/")[filepath.split("/").length - 1];
            String OssUrl = null;
            if (file.exists() && file.length() > 0) {
                OssUrl = OssUtil.getOssFilePath(file_name, filepath);
            }
            return OssUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getOssFilePath(String fileName, String uploadFilePath) {
        URL uri = null;
        OSSClient client = getOSSUitl();
        //上传图片
        String key = "guohua/" + fileName; //指定文件上传到bucket下面的那个文件夹下及文件名
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        boolean isSuccess = uploadFile(client, bucket_name, key, uploadFilePath);//指定bucket
        if (isSuccess) {
            uri = client.generatePresignedUrl(bucket_name, key, expiration);
        }
        return uri.toString();
    }

    private static boolean uploadFile(OSSClient client, String bucketName, String key, String filePath) {
        int MAX_TRY = 3;
        int downloadTurn = 0;
        boolean uploadSuccess = false;
        while (downloadTurn < MAX_TRY) {
            try {
                File file = new File(filePath);
                if ((!file.exists()) || file.length() == 0) {
                    uploadSuccess = false;
                    break;
                }
                ObjectMetadata objectMeta = new ObjectMetadata();
                objectMeta.setContentLength(file.length());
                if (!client.doesObjectExist(bucketName, key)) {
                    InputStream input = new FileInputStream(file);
                    client.putObject(bucketName, key, input, objectMeta);
                    System.out.println(filePath + "上传成功!");
                    uploadSuccess = true;
                    break;
                } else {
                    uploadSuccess = true;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return uploadSuccess;
    }

    /**
     * 删除指定文件
     *
     * @param folder 文件名
     * @param key    文件名
     */
    public static void deleteOSSFileUitl(String folder, String key) {
        OSSClient ossClient = getOSSUitl();
        ossClient.deleteObject(bucket_name, folder + key);
        logger.info("删除" + bucket_name + "下文件" + folder + key + "成功");
        System.out.println("删除" + bucket_name + "下文件" + folder + key + "成功");
    }

    /**
     * 获取oss对象
     *
     * @return
     */
    public static OSSClient getOSSUitl() {
        OSSClient client = new OSSClient(bucket_source, accessKeyId, secretAccessKey);
        return client;
    }


}
