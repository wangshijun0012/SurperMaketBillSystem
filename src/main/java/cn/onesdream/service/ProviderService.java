package cn.onesdream.service;

import cn.onesdream.pojo.Provider;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProviderService {
    List<Provider> getProviders(String proCode,String proName);
    int addProvider(Provider provider);
    int delProvider(Long proid, HttpServletRequest request);
    int updateProvider(Provider provider,Long proid);
}
