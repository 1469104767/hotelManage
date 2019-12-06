package basics.service;



import basics.entity.Test;
import basics.mapper.TestMapper;
import core.annotation.Transaction;
import core.base.BaseService;


public class TestService extends BaseService<Test,TestMapper> {
	@Transaction
	public void test(String text) throws Exception{
		mapper.delete1(1);
		//mapper.save("x","牛皮",new Date());
		System.out.println(text);
	}

	@Transaction
	public String copy(String id) {
		return null;
	}
}
