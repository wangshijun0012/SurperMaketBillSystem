package cn.onesdream.service;

import cn.onesdream.dao.BillMapper;
import cn.onesdream.dao.ProviderMapper;
import cn.onesdream.pojo.Bill;
import cn.onesdream.pojo.Provider;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl implements BillService {
    @Resource
    private BillMapper billMapper;
    @Resource
    private ProviderMapper providerMapper;

    @Override
    public List<Provider> getAllProvider() {

        List<Provider> list = providerMapper.selectList(null);
        return list;
    }

    @Override
    public Map<String,Object> getPageBill(int currentPageNo, int pageSize, Wrapper<Bill> wrapper) {
        Map<String,Object> map = new HashMap();
        Page<Bill> page = new Page<Bill>(currentPageNo,pageSize);
        List<Bill> bills = billMapper.selectPage(page, wrapper);
        List<Provider> providers = providerMapper.selectList(null);
        for (Bill bill : bills) {
            for (Provider provider : providers) {

                if (provider.getId().equals(bill.getProviderId())) {
                    bill.setProvider(provider);
                }
            }
        }
        page.setRecords(bills);
        map.put("blist", bills);
        map.put("totalPageCount", page.getPages());
        map.put("totalCount", page.getTotal());
        return map;
    }

    @Override
    public Bill getBillByCode(String billCode) {
        Bill bill = new Bill();
        bill.setBillCode(billCode);
        Bill bill1 = billMapper.selectOne(bill);
        return bill1;
    }

    @Override
    public String delBill(Integer billid) {
        Wrapper<Bill> billWrapper = new EntityWrapper<Bill>();
        billWrapper.eq("id", billid);
        Integer delete = billMapper.delete(billWrapper);
        if(delete > 0 ){
            return "true";
        }else {
            return "notexist";
        }
    }

    @Override
    public Integer modifyBill(Integer billid,Bill bill) {
        EntityWrapper<Bill> wrapper = new EntityWrapper<>();
        wrapper.eq("id",billid);
        int result = billMapper.update(bill, wrapper);
        return result;
    }

    @Override
    public boolean addBill(Bill bill) {
        Integer insert = billMapper.insert(bill);
        if(insert > 0 ){
            return true;
        }
        return false;
    }
}
