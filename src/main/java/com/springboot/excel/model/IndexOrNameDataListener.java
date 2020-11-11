package com.springboot.excel.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板的读取类
 *
 * @author Zhou Yun
 */
@Slf4j
public class IndexOrNameDataListener extends AnalysisEventListener<IndexOrNameData> {
	/**
	 * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
	 */
	private static final int BATCH_COUNT = 5;
	List<IndexOrNameData> list = new ArrayList<IndexOrNameData>();

	@Override
	public void invoke(IndexOrNameData data, AnalysisContext context) {
		log.info("解析到一条数据:{}", JSON.toJSONString(data));
		list.add(data);
		if (list.size() >= BATCH_COUNT) {
			saveData();
			list.clear();
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		saveData();
		log.info("所有数据解析完成！");
	}

	/**
	 * 加上存储数据库
	 */
	private void saveData() {
		if(list.size()>0){
			log.info("{}条数据，开始存储数据库！", list.size());
			log.info("存储数据库成功！");
		}
	}
}
