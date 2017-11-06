package com.cxytiandi.open.api.ld.rest;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxytiandi.model.ld.dto.LouDongCountDto;
import com.cxytiandi.model.ld.dto.LouDongDto;
import com.cxytiandi.open.api.anno.ApiRateLimit;
import com.cxytiandi.open.api.anno.NoAuth;
import com.cxytiandi.open.api.ld.service.LdRestService;
import com.cxytiandi.common.base.ResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "楼栋接口")
@RestController
@RequestMapping("/rest/ld")
public class LdRestApi {
	
	@Autowired
	private LdRestService ldRestService;
	
	@Autowired
	private RedissonClient redissonClient;
	
	@ApiOperation(value = "楼栋总量接口", notes = "获取楼栋数据的总数量")
	@ApiResponses(
		@ApiResponse(response = LouDongCountDto.class, code = 200, message = "")
	)
	@GetMapping("/count")
	@NoAuth
	public ResponseData count() {
		return LouDongCountDto.ok(ldRestService.count());
		//return LouDongCountDto.ok(100);
	}
	
	@ApiOperation(value = "楼栋列表接口", notes = "所有楼栋数据")
	@ApiResponses(
		@ApiResponse(response = LouDongDto.class, code = 200, message = "")
	)
	@GetMapping("/list")
	public ResponseData list() {
		return LouDongDto.ok(ldRestService.findAll());
	}
	
	@ApiRateLimit(confKey = "open.api.listCityLimit")
	@ApiOperation(value = "城市下的楼栋接口", notes = "获取城市下的楼栋数据")
	@ApiImplicitParam(name = "city", value = "城市", required = true, paramType = "path", dataType = "String")
	@ApiResponses(
		@ApiResponse(response = LouDongDto.class, code = 200, message = "")
	)
	@GetMapping("/list/{city}")
	public ResponseData list(@PathVariable String city) {
		//RLock lock = redissonClient.getLock(city);
		//lock.lock();
		ResponseData response = LouDongDto.ok(ldRestService.find(city));
		//lock.unlock();
		return response;
	}
	
	@ApiRateLimit(confKey = "open.api.listCityRegionLimit")
	@ApiOperation(value = "城市区域下的楼栋接口", notes = "获取城市区域下的楼栋数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "city", value = "城市", required = true, paramType = "path", dataType = "String"),
		@ApiImplicitParam(name = "region", value = "区域", required = true, paramType = "path", dataType = "String")
	})
	@ApiResponses(
		@ApiResponse(response = LouDongDto.class, code = 200, message = "")
	)
	@GetMapping("/list/{city}/{region}")
	public ResponseData list(@PathVariable String city, @PathVariable String region) {
		return LouDongDto.ok(ldRestService.find(city, region));
	}
	
}
