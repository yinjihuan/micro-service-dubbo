package com.cxytiandi.open.api.user.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxytiandi.model.user.dto.AuthDto;
import com.cxytiandi.model.user.po.User;
import com.cxytiandi.model.user.query.AuthQuery;
import com.cxytiandi.open.api.anno.NoAuth;
import com.cxytiandi.open.api.user.service.AuthRestService;
import com.cxytiandi.common.base.ResponseData;
import com.cxytiandi.common.util.JWTUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * API调用认证服务
 * @author yinjihuan
 *
 */
@Api(value = "API认证接口")
@RestController
@RequestMapping("/rest/auth")
public class AuthRestApi {
	
	@Autowired
	private AuthRestService authService;
	
	@ApiOperation(value = "API调用认证接口", notes = "API调用认证接口")
	@ApiResponses(@ApiResponse(response = AuthDto.class, code = 200, message = ""))
	@PostMapping("")
	@NoAuth
	public ResponseData auth(@ApiParam(value = "认证参数", required = true) @RequestBody AuthQuery query) throws Exception {
		if (!StringUtils.hasText(query.getAccessKey()) || !StringUtils.hasText(query.getSecretKey())) {
			return ResponseData.failByParam("accessKey and secretKey not null");
		}
		
		User apiUser = authService.getUser(query.getAccessKey(), query.getSecretKey());
		if (apiUser == null) {
			return ResponseData.failByParam("认证失败");
		}
		
		return ResponseData.ok(JWTUtils.getToken(apiUser.getId()));
	}
	
}
