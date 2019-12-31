package cn.onesdream.controller;

import cn.onesdream.pojo.Bill;
import cn.onesdream.pojo.Provider;
import cn.onesdream.service.BillService;
import cn.onesdream.service.ProviderService;
import cn.onesdream.util.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/provider")
public class ProviderController {
    @Resource
    private ProviderService providerService;
    @Resource
    private BillService billService;
    @RequestMapping("/list")
    public String list(HttpSession session, HttpServletRequest request) {
        String proCode = request.getParameter("queryProCode");
        String proName = request.getParameter("queryProName");
        List<Provider> proList = providerService.getProviders(proCode, proName);
        session.setAttribute("proList", proList);
        return "/pro/providerlist";
    }

    @RequestMapping("provideraddsave")
    public String provideraddsave(HttpSession session, HttpServletRequest request, Provider provider) {
        Date date = new Date(System.currentTimeMillis());
        provider.setCreationDate(date);
        providerService.addProvider(provider);
        return "redirect:/provider/list";
    }

    @RequestMapping("provideradd")
    public String provideradd() {
        return "/pro/provideradd";
    }

    @RequestMapping("/providerview")
    public String providerview(HttpServletRequest request, HttpSession session) {
        long proid = Integer.parseInt(request.getParameter("proid"));
        List<Provider> providers = (List) session.getAttribute("proList");
        for (Provider provider : providers) {
            if (provider.getId() == proid) {
                session.setAttribute("provider", provider);
            }
        }
        return "/pro/providerview";
    }

    @RequestMapping("/providermodify")
    public String providermodify(HttpServletRequest request, HttpSession session) {
        long proid = Integer.parseInt(request.getParameter("proid"));
        List<Provider> providers = (List) session.getAttribute("proList");
        for (Provider provider : providers) {
            if (provider.getId() == proid) {
                session.setAttribute("provider", provider);
            }
        }
        return "/pro/providermodify";
    }
    @RequestMapping("/providermodifysave")
    public String providermodifysave(Provider provider,HttpServletRequest request,HttpSession session){
        Long proid = Long.parseLong(request.getParameter("proid"));
        providerService.updateProvider(provider, proid);
        return "redirect:/provider/list";
    }
    @RequestMapping("/delprovider")
    @ResponseBody
    public Object delprovider(HttpServletRequest request, HttpSession session){
        Data data = new Data();
        long proid = Integer.parseInt(request.getParameter("proid"));
        int result = providerService.delProvider(proid,request);
        if(result == -1){
            data.setDelResult(request.getAttribute("billNum").toString());
        }else if(result == 0){
            data.setDelResult("false");
        }else {
            data.setDelResult("true");
        }
        return data;
    }

}
