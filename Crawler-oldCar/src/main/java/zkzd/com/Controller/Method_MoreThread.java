package zkzd.com.Controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import zkzd.com.Dao.DaoFather;
import zkzd.com.Entity.Bean_detail_diyi;
import zkzd.com.Until.SaveUntil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Method_MoreThread implements Runnable{

    private List<Bean_detail_diyi> list;
    private String savePath;

    public Method_MoreThread(List<Bean_detail_diyi> list,String savePath) {
        this.list = list;
        this.savePath = savePath;
    }

    @Override
    public void run() {
        DaoFather daoFather = new DaoFather(0,0);
        SaveUntil saveUntil = new SaveUntil();
        Thread currentThread = Thread.currentThread();
        System.out.println("Thread " + currentThread.getName() + " (ID: " + currentThread.getId() + ") is processing group.");

        for (Bean_detail_diyi beanDetailDiyi : list) {
            if (beanDetailDiyi.getC_downState().equals("否")) {
                int C_ID = beanDetailDiyi.getC_ID();
                String url = beanDetailDiyi.getC_deatileUrl();
                String content = Method_DownHtml(url);
                if (content != null) {
                    saveUntil.Method_SaveFile(savePath + C_ID + "_.txt", content);
                    daoFather.Method_OnlyChangeState(C_ID);
                    System.out.printf("下载完成-->" + C_ID);
                } else {
                    System.out.printf("Error---->" + C_ID);
                }
            }
        }

    }


    public String Method_DownHtml(String firstUrl) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("_df_id", "110.229.66.28_49803c99a72d417bbc383680b8b1dc63_1717660730");
            map.put("_df", "07ee3d738ac026343453f31da1255798");

            Document document = Jsoup.connect(firstUrl)
//                    .proxy(proxy)
                    .timeout(20000)
                    .header("Accept", "*/*")
                    .method(Connection.Method.GET)
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 OPR/79.0.4143.72")
                    .cookies(map)
                    .get();
            System.out.println(document.select("title").text());
            Random random = new Random();
            int sleepTime = (random.nextInt(3) + 1) * 1000;
            Thread.sleep(sleepTime);
            return String.valueOf(document.html());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
