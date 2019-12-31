package cn.onesdream.controller;

import cn.onesdream.pojo.Bill;
import cn.onesdream.service.BillService;
import cn.onesdream.util.Data;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Resource
    private BillService billService;
    static int pageSize = 12;


    @RequestMapping("/list")
    public String bill(HttpSession session, HttpServletRequest request) {
        String pageIndex = request.getParameter("pageIndex");
        String productName = request.getParameter("productName");
        String providerId = request.getParameter("providerId");
        String isPayment = request.getParameter("isPayment");
        int currentPageNo = (pageIndex == null || "".equals(pageIndex)) ? 1 : Integer.parseInt(pageIndex);


        EntityWrapper<Bill> billWrapper = new EntityWrapper<Bill>();
        if (productName != null && !"".equals(productName)) {
            billWrapper.like("productname", productName);
            session.setAttribute("productName", productName);
        } else {
            session.setAttribute("productName", null);
        }
        if (providerId != null && !"0".equals(providerId)) {
            billWrapper.eq("providerId", Integer.parseInt(providerId));
            session.setAttribute("providerId", providerId);
        } else {
            session.setAttribute("providerId", 0);
        }
        if (isPayment != null && !"0".equals(isPayment)) {
            billWrapper.eq("ispayment", isPayment);
            session.setAttribute("isPayment", isPayment);
        } else {
            session.setAttribute("isPayment", 0);
        }

        Map<String, Object> map = billService.getPageBill(currentPageNo, pageSize, billWrapper);
        session.setAttribute("plist", billService.getAllProvider());
        session.setAttribute("blist", (List) map.get("blist"));
        session.setAttribute("totalCount", (long) map.get("totalCount"));
        session.setAttribute("totalPageCount", (long) map.get("totalPageCount"));
        session.setAttribute("currentPageNo", currentPageNo);
        return "/bill/billlist";
    }

    @RequestMapping("/billview")
    public String billview(HttpServletRequest request, HttpSession session) {
        long billid = Integer.parseInt(request.getParameter("billid"));
        long pageIndex = (Integer) session.getAttribute("currentPageNo");
        List<Bill> bills = (List) session.getAttribute("blist");
        for (Bill bill : bills) {
            if (bill.getId() == billid) {
                session.setAttribute("bill", bill);
                break;
            }
        }
        return "/bill/billview";
    }

    @RequestMapping("/billdel")
    @ResponseBody
    public Object billdel(Integer billid) {

        String result = billService.delBill(billid);
        Data data = new Data();
        data.setDelResult(result);
        return data;
        /*ObjectMapper mapper = new ObjectMapper();
        String delResult = null;
        try {
            delResult = mapper.writeValueAsString(data);
            return delResult;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
*/
    }
    @RequestMapping("/billmodify")
    public String modify(HttpServletRequest request,HttpSession session){
        long billid = Integer.parseInt(request.getParameter("billid"));
        List<Bill> bills = (List)session.getAttribute("blist");
        for (Bill bill : bills) {
            if (bill.getId() == billid) {
                session.setAttribute("bill", bill);
                break;
            }
        }

        return "/bill/billmodify";
    }
    @RequestMapping("/dobillmodify")
    public String domodify(HttpServletRequest request,Bill bill){
//        Bill bill = new Bill();
//        bill.setBillCode(request.getParameter("billCode"));
//        bill.setProductName(request.getParameter("productName"));
//        bill.setProductUnit(request.getParameter("productUnit"));
//        bill.setProductCount(new BigDecimal(request.getParameter("productCount")));
//        bill.setTotalPrice(new BigDecimal(request.getParameter("totalPrice")));
//        bill.setProviderId(Long.parseLong(request.getParameter("providerId")));
//        bill.setIsPayment(Integer.parseInt(request.getParameter("isPayment")));
        billService.modifyBill(Integer.parseInt(request.getParameter("id")),bill);

        return "redirect:/bill/list";
    }
    @RequestMapping("/billselect")
    @ResponseBody
    public Object select(HttpSession session){
        List plist = (List) session.getAttribute("plist");
        return plist;
        /*ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(plist);

            return json;
        } catch (Exception e) {
            e.printStackTrace();
        } return null;*/
    }
    @RequestMapping("/billadd")
    public String billadd(){
        return "/bill/billadd";
    }
    @RequestMapping("/dobilladd")
    public String dobilladd(HttpServletRequest request,Bill bill){
        Date date = new Date(System.currentTimeMillis());
        bill.setCreationDate(date);
        billService.addBill(bill);
        return ("redirect:/bill/list");

    }


}
