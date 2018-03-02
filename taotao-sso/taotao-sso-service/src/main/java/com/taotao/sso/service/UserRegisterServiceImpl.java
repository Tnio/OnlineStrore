package com.taotao.sso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
/**
 * 用户注册处理service
 */


@Service
public class UserRegisterServiceImpl implements UserRegisterService{

	@Autowired
	private TbUserMapper userMapper;
	
	public TaotaoResult checkUserInfo(String param, int type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		//判断数据的类型，1代表username	2代表phone	3代表email
		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		}else if (type ==2) {
			criteria.andPhoneEqualTo(param);
		}else if (type == 3) {
			criteria.andEmailEqualTo(param);
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example );
		//如果数据库中没有，说明可以注册
		if (list == null  || list.size() ==0) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

}
