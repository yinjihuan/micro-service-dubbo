package com.cxytiandi.ld.service.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cxytiandi.jdbc.PageQueryParam;
import com.cxytiandi.ld.service.service.LdService;
import com.cxytiandi.model.ld.bdo.LouDongDo;
import com.cxytiandi.model.ld.po.LouDong;
import com.cxytiandi.common.util.JsonUtils;

/**
 * 楼栋业务测试类
 * @author yinjihuan
 *
 */
public class LdServiceTest extends TestBase {
	
	@Autowired
	LdService ldService;
	
	@Test
	public void testCount() {
		System.out.println(ldService.count());
	}
	
	@Test
	public void testFindAll() {
		List<LouDong> list = ldService.findAll();
		System.out.println(JsonUtils.toJson(list));
	}
	
	@Test
	public void testFind() {
		long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 1000; i++) {
			executorService.execute(new Runnable() {
				
				public void run() {
					List<LouDong> list = ldService.find("上海", "虹口");
					System.out.println(JsonUtils.toJson(list));
				}
			});
		}
		executorService.shutdown();
		while(!executorService.isTerminated()){}
		
		long end = System.currentTimeMillis();
		System.err.println(end - start);
		
	}
	
	@Test
	public void testFindAllByPage() {
		List<LouDong> list = ldService.findAll(new PageQueryParam(1, 10));
		System.out.println(JsonUtils.toJson(list));
	}
	
	@Test
	public void testExists() {
		System.out.println(ldService.exists("上海"));
	}
	
	@Test
	public void testIn() {
		List<LouDong> list =ldService.in(new String[] {"大二小区", "大三小区"});
		System.out.println(JsonUtils.toJson(list));
	}
	
	@Test
	public void testGroup() {
		List<LouDongDo> list =ldService.group();
		System.out.println(JsonUtils.toJson(list));
	}
	
	@Test
	public void testGet() {
		LouDong loudong =ldService.get("1001");
		System.out.println(JsonUtils.toJson(loudong));
	}
	
	@Test
	public void testDelete() {
		ldService.delete("大二小区");
	}
	
	@Test
	public void testSave() {
		LouDong louDong = new LouDong();
		louDong.setId("8888");
		louDong.setCity("长沙");
		louDong.setRegion("岳麓");
		louDong.setName("达美D6区");
		louDong.setLdNum("6");
		louDong.setUnitNum("1");
		ldService.save(louDong);
	}
	
	@Test
	public void testSaveList() {
		List<LouDong> list = new ArrayList<LouDong>();
		LouDong louDong = new LouDong();
		louDong.setId("8888");
		louDong.setCity("长沙");
		louDong.setRegion("岳麓");
		louDong.setName("达美D6区");
		louDong.setLdNum("6");
		louDong.setUnitNum("1");
		list.add(louDong);
		
		louDong = new LouDong();
		louDong.setId("8888");
		louDong.setCity("长沙");
		louDong.setRegion("岳麓");
		louDong.setName("达美D6区");
		louDong.setLdNum("6");
		louDong.setUnitNum("1");
		list.add(louDong);
		
		ldService.saveList(list);
	}
	
	@Test
	public void testUpdate() {
		LouDong louDong = new LouDong();
		louDong.setId("8888");
		louDong.setCity("长沙");
		louDong.setRegion("岳麓");
		louDong.setName("达美D6区");
		louDong.setLdNum("61");
		louDong.setUnitNum("11");
		ldService.update(louDong);
	}
	
	@Test
	public void testUpdateList() {
		List<LouDong> list = new ArrayList<LouDong>();
		LouDong louDong = new LouDong();
		louDong.setId("8888");
		louDong.setCity("长沙");
		louDong.setRegion("岳麓");
		louDong.setName("达美D6区");
		louDong.setLdNum("6");
		louDong.setUnitNum("1");
		list.add(louDong);
		
		louDong = new LouDong();
		louDong.setId("8888");
		louDong.setCity("长沙");
		louDong.setRegion("岳麓");
		louDong.setName("达美D6区");
		louDong.setLdNum("6");
		louDong.setUnitNum("1");
		list.add(louDong);
		
		ldService.updateList(list);
	}
}
