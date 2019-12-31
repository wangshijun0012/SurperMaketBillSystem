package cn.onesdream.service;

import cn.onesdream.dao.BillMapper;
import cn.onesdream.dao.ProviderMapper;
import cn.onesdream.pojo.Bill;
import cn.onesdream.pojo.Provider;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Resource
    private ProviderMapper providerMapper;
    @Resource
    private BillMapper billMapper;

    @Override
    public List<Provider> getProviders(String proCode, String proName) {
        EntityWrapper wrapper = new EntityWrapper();
        if (proName != null && !"".equals(proName)) {
            wrapper.like("proName", proName);
        }
        if (proCode != null && !"".equals(proCode)) {
            wrapper.like("proCode", proCode);
        }
        List<Provider> providers = providerMapper.selectList(wrapper);
        return providers;
    }

    @Override
    public int addProvider(Provider provider) {
        int result = providerMapper.insert(provider);
        return result;
    }

    @Override
    public int delProvider(Long proid, HttpServletRequest request) {
        Integer result = null;
        EntityWrapper proWrapper = new EntityWrapper();
        EntityWrapper billWrapper = new EntityWrapper();
        billWrapper.eq("providerId", proid);
        proWrapper.eq("id", proid);
        List<Bill> bills = billMapper.selectList(billWrapper);
        System.out.println(bills);
        if (!bills.isEmpty()) {
            result = -1;
            request.setAttribute("billNum", bills.size());
        } else {
            result = providerMapper.delete(proWrapper);
        }
        return result;
    }

    @Override
    public int updateProvider(Provider provider,Long proid) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("id", proid);
        int result = providerMapper.update(provider,wrapper);
        return result;
    }
}
