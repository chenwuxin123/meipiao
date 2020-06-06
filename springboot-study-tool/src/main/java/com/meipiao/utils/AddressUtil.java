package com.meipiao.utils;

import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;

/**
 * 获取ip地址 输出格式:`马来西亚|0|0|0|0` or `内网IP|0|0|内网IP|内网IP`
 *
 * 使用此方法  添加依赖 以及ip2region.db文件
 *         <dependency>
 *             <groupId>org.lionsoul</groupId>
 *             <artifactId>ip2region</artifactId>
 *             <version>1.7</version>
 *         </dependency>
 *
 *
 * @author MrBird
 */
public class AddressUtil {

    private static Logger log = LoggerFactory.getLogger(AddressUtil.class);

    public static String getCityInfo(String ip){

        File file;
        try {
            //db
            String dbPath = AddressUtil.class.getResource("/ip2region/ip2region.db").getPath();
             file= new File(dbPath);
            if (file.exists() == false) {
                String tmpDir = System.getProperties().getProperty("java.io.tmpdir");   //获取操作系统的缓存临时目录
                dbPath = tmpDir + "ip.db";
                file = new File(dbPath);
                FileUtils.copyInputStreamToFile(AddressUtil.class.getClassLoader().getResourceAsStream("classpath:ip2region/ip2region.db"), file);
            }

            //查询算法
            int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
            //DbSearcher.BINARY_ALGORITHM //Binary
            //DbSearcher.MEMORY_ALGORITYM //Memory
            try {
                DbConfig config = new DbConfig();
                DbSearcher searcher = new DbSearcher(config, dbPath);

                //define the method
                Method method = null;
                switch (algorithm) {
                    case DbSearcher.BTREE_ALGORITHM:
                        method = searcher.getClass().getMethod("btreeSearch", String.class);
                        break;
                    case DbSearcher.BINARY_ALGORITHM:
                        method = searcher.getClass().getMethod("binarySearch", String.class);
                        break;
                    case DbSearcher.MEMORY_ALGORITYM:
                        method = searcher.getClass().getMethod("memorySearch", String.class);
                        break;
                }

                DataBlock dataBlock = null;
                if (Util.isIpAddress(ip) == false) {
                    System.out.println("Error: Invalid ip address");
                }

                dataBlock = (DataBlock) method.invoke(searcher, ip);

                return dataBlock.getRegion();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {

        }

        return null;
    }
    public static void main(String[] args)  throws Exception{
        System.err.println(getCityInfo("127.0.0.1"));
    }
}
