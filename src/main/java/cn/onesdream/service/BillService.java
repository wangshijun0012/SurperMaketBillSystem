package cn.onesdream.service;

import cn.onesdream.pojo.Bill;
import cn.onesdream.pojo.Provider;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.List;
import java.util.Map;

public interface BillService {

    List<Provider> getAllProvider();
    Map<String,Object> getPageBill(int currentPageNo, int pageSize, Wrapper<Bill> wrapper);
    Bill getBillByCode(String billCode);
    String delBill(Integer billid);
    Integer modifyBill(Integer billid,Bill bill);
    boolean addBill(Bill bill);


}
