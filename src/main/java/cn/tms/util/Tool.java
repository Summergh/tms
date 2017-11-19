package cn.tms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Happy on 2017-11-06.
 */
public class Tool {
    public static void main(String[] args) {
        //批量插入数据的方式
        BaseDao dao=new BaseDao();
        //读取一个文本文件
        try {
            String encoding="GBK";
            File file=new File("d:\\file.txt");
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int i=3;
                while((lineTxt = bufferedReader.readLine()) != null){
                    i++;
                    dao.executeUpdate("INSERT INTO `cm_column` VALUES (UUID(),"+i+" , '"+lineTxt+"', null, '0', '0', null)");
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }


        for (int i=3;i<3;i++){

        }

    }
}
