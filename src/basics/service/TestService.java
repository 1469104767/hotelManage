package basics.service;

import basics.dao.TestDao;
import basics.entity.Test;
import core.annotation.Transaction;
import core.base.BaseService;

public class TestService extends BaseService<Test,TestDao> {
	@Transaction(readOnly=true)
	public void test(String text) throws Exception{
		dao.delete(1);
		System.out.println(text);
	}

	public String copy(String id) {
		return null;
	}
}
