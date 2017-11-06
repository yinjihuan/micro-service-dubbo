package com.cxytiandi.house.service.rpc;

import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.cxytiandi.api.house.HouseRpcService;
import com.cxytiandi.common.base.Constants;

@Component("HouseRpcService")
@Service(interfaceClass = HouseRpcService.class, version = Constants.V1)
public class HouseRpcServiceImpl implements HouseRpcService {

	public long count() {
		return 0;
	}

}
