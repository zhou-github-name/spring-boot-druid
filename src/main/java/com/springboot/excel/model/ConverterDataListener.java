package com.springboot.excel.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

/**
 * 模板的读取类
 *
 * @author Zhou Yun
 */
public class ConverterDataListener extends AnalysisEventListener<ConverterData> {
    private static final Logger logger = LoggerFactory.getLogger(ConverterDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<ConverterData> list = new ArrayList<ConverterData>();

    @Override
    public void invoke(ConverterData data, AnalysisContext context) {
        logger.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        logger.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        logger.info("{}条数据，开始存储数据库！", list.size());
        logger.info("存储数据库成功！");
    }
}
