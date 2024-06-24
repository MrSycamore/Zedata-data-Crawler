package zkzd.com.Controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import zkzd.com.Dao.DaoFather;
import zkzd.com.Entity.Bean_detail_diyi;
import zkzd.com.Entity.Bean_diyi_detail_new;
import zkzd.com.Until.ReadUntil;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller_diyi_detail {


    // 1. 多线程分配数据
    public void Method_More_Thread(String savePath){


        DaoFather daoFather = new DaoFather(0,0);
        ArrayList<Object> beanDetailDiyis = daoFather.Method_Find();
        List<Bean_detail_diyi> BeanList = new ArrayList<>();
        for (Object obj : beanDetailDiyis){
            Bean_detail_diyi beanDetailDiyi = (Bean_detail_diyi) obj;
            BeanList.add(beanDetailDiyi);
        }


        List<List<Bean_detail_diyi>> splitBeans = IntStream.range(0, 6)
                .mapToObj(i -> BeanList.subList(i * (BeanList.size() + 5) / 6, Math.min((i + 1) * (BeanList.size() + 5) / 6, BeanList.size())))
                .collect(Collectors.toList());

        for (int i = 0; i < splitBeans.size(); i++) {
            List<Bean_detail_diyi> groupList = splitBeans.get(i);
            Method_MoreThread methodMoreThread = new Method_MoreThread(groupList, savePath);
            Thread thread = new Thread(methodMoreThread);
            thread.start();
        }
    }

    // 2. 解析方法
    public void Method_Analysis_diyi_detail(String savePath){

        DaoFather daoFather = new DaoFather(0,0);
        DaoFather daoFatherNew = new DaoFather(0,1);
        ReadUntil readUntil = new ReadUntil();
        List<Object> beanList = daoFather.Method_FindIsDown();

        for (int i = 0; i < beanList.size(); i++) {

            Bean_detail_diyi beanDetailDiyi = (Bean_detail_diyi) beanList.get(i);
            int C_ID = beanDetailDiyi.getC_ID();

            String html = readUntil.Method_ReadFile(savePath+C_ID+"_.txt");

            Document document = Jsoup.parse(html);

            //New car price including tax
            String carDetailTile = document.select("h1.title.de-drei-col").select("span").text();
            // 新车含税价格
            String newCarPriceIncludeTax = document.select("div.priceloan").select(".left-normal").text();

            Elements priceElements = document.select("div.priceloan").select(".loan-left").select(".loan-other").select("p");
            //New car guide price:
            String newCarGuidePrice  = "";
            //Vehicle purchase tax
            String vehiclePurchaseTax = "";

            if (priceElements.size()==2){
                newCarGuidePrice = priceElements.get(0).text();
                vehiclePurchaseTax = priceElements.get(1).text();
            }

            Elements elementDis = document.select(".sub-dealer.sub-dealer-box").select(".name").select("a");
            String distributors = "";
            String distributorsUrl = "";

            if (elementDis.size()>0){

                if (elementDis.size()==1){
                    distributors = elementDis.text();
                    distributorsUrl = elementDis.attr("href");
                }

                if (elementDis.size()>1){
                    distributors = elementDis.get(0).text();
                    distributorsUrl = elementDis.get(0).attr("href");
                }

            }


            System.out.printf(carDetailTile+'\n');

            System.out.print(newCarPriceIncludeTax+"\t"+newCarGuidePrice+"\t"+vehiclePurchaseTax+'\n');

            System.out.printf(distributors+'\t'+distributorsUrl+'\n');

            Bean_diyi_detail_new bean = new Bean_diyi_detail_new();
            bean.setC_carDetailTile(carDetailTile);
            bean.setC_newCarGuidePrice(newCarGuidePrice.replace("新车指导价：",""));
            bean.setC_newCarPriceIncludeTax(newCarPriceIncludeTax.replace("新车含税价：",""));
            bean.setC_vehiclePurchaseTax(vehiclePurchaseTax.replace("车辆购置税：",""));
            bean.setC_distributors(distributors);
            bean.setC_distributorsUrl(distributorsUrl);

            bean.setC_county(beanDetailDiyi.getC_county());
            bean.setC_mileage(beanDetailDiyi.getC_mileage());
            bean.setC_carDetailTile(bean.getC_carDetailTile());
            bean.setC_fileName(C_ID+"_.txt");
            bean.setC_deatileUrl(beanDetailDiyi.getC_deatileUrl());
            bean.setC_name(beanDetailDiyi.getC_name());
            bean.setC_price(beanDetailDiyi.getC_price());
            bean.setC_licenseDate(beanDetailDiyi.getC_licenseDate());

            daoFatherNew.Method_Insert(bean);
        }
    }
}
