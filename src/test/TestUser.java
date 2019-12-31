import cn.onesdream.dao.BillMapper;
import cn.onesdream.pojo.Bill;
import cn.onesdream.service.BillService;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestUser {
    @Resource
    private BillService billService;
    @Resource
    private BillMapper billMapper;
    @Test
    public void test01(){
        Page<Bill> page = new Page<>(1,4);
        List<Bill> list = billMapper.selectPage(page, null);
        page.setRecords(list);

        long i = page.getPages();
        System.out.println(i);
    }
}
