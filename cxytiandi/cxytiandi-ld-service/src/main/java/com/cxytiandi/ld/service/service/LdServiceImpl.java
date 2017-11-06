package com.cxytiandi.ld.service.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.cxytiandi.jdbc.EntityService;
import com.cxytiandi.jdbc.PageQueryParam;
import com.cxytiandi.model.ld.bdo.LouDongDo;
import com.cxytiandi.model.ld.po.LouDong;
import com.cxytiandi.common.util.JsonUtils;

@Service
public class LdServiceImpl extends EntityService<LouDong> implements LdService {
	private static DruidStatManagerFacade statManagerFacade = DruidStatManagerFacade.getInstance();
	public long count() {
		List<Map<String, Object>> array = statManagerFacade.getSqlStatDataList(null);
		if (array != null) {
			for (Map<String, Object> map : array) {
				System.err.println(JsonUtils.toJson(map));
			}
		}
		return super.count();
	}

	public List<LouDong> findAll() {
		return super.list(LouDong.ORDER_FIELDS);
	}

	public List<LouDong> find(String city) {
		return super.list("city", city);
	}

	public List<LouDong> find(String city, String region) {
		return super.list(new String[]{"city", "region"}, new Object[] {city, region});
	}

	public List<LouDong> find(String city, String region, String name) {
		return super.list(LouDong.SHOW_FIELDS, LouDong.QUERRY_FIELDS, new Object[] {city, region, name});
	}

	public List<LouDong> findAll(PageQueryParam page) {
		return super.listForPage(page.getStart(), page.getLimit(), LouDong.ORDER_FIELDS);
	}

	public boolean exists(String city) {
		return super.exists("city", city);
	}

	public List<LouDong> in(String[] names) {
		return super.in(new String[]{"city", "region"}, "name", names);
	}

	public List<LouDongDo> group() {
		return super.getJdbcTemplate().list(LouDongDo.class, "select city,count(id) as count from loudong GROUP BY city");
	}

	public LouDong get(String id) {
		return super.getById("id", id);
	}

	@Transactional
	public void delete(String name) {
		super.deleteById("name", name);
	}

	@Transactional
	public void save(LouDong louDong) {
		if (this.getById("name", louDong.getName()) == null) {
			super.save(louDong);
		}
	}

	@Transactional
	public void saveList(List<LouDong> list) {
		super.batchSave(list);
	}

	@Transactional
	public void update(LouDong louDong) {
		super.update(louDong, "id");
	}

	@Transactional
	public void updateList(List<LouDong> list) {
		super.batchUpdateByContainsFields(list, "id", "city");
	}

}
