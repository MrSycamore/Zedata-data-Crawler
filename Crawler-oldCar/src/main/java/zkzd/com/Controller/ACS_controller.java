package zkzd.com.Controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ACS_controller {

    public static String Method_DownHtml(String firstUrl,int numberBath) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("ACSEnt", "646525_6105_1718348212241");
            map.put("MAID", "ijGUgI7GcwNxdKgZmLZc+Q==");
            map.put("_gid","GA1.2.681604270.1718348215");
            map.put("visid_incap_2209364","fwGPvc1dSxuoeh3aVCg23rrpa2YAAAAAQUIPAAAAAACBoIy/J2SWqq3ajygJEAqA");
            map.put("SnapABugHistory","1#");
            map.put("ELOQUA","GUID=CEFF8A15DF504A848A255DFEF5A59CAC");
            map.put("_hjSessionUser_3120009","eyJpZCI6IjI4NzgzZDJiLTU0MTgtNWU2Ni04MTRmLTEwZmUzYTI3MDhlOCIsImNyZWF0ZWQiOjE3MTgzNDgyMTkwNDEsImV4aXN0aW5nIjp0cnVlfQ==");
            map.put("MACHINE_LAST_SEEN","2024-06-14T06%3A20%3A06.198-07%3A00");
            map.put("JSESSIONID","73EEFD314FC9CCBE11F794B27E5DB2F6");
            map.put("__cf_bm","WgcQIfDeNzqoou_Fo3K_F5O5QdaTu_KtzJO8Nvzd0_U-1718371206-1.0.1.1-S0wGYW81Jn2ELi.jSYzP9PK87mss8tyNhB.WOc6u8YJA0ucNZcsBSm0CBIeZZKtLNQJLZzMHYvw.RhKd7ufxlw");
            map.put("SnapABugRef","https%3A%2F%2Fpubs.acs.org%2Fdoi%2F10.1021%2Fcg2008057%20https%3A%2F%2Fpubs.acs.org%2Fdoi%2F10.1021%2Fcg200799n");
            map.put("_hjSession_3120009","eyJpZCI6IjZjNzY5OGFjLWU3NzEtNGM5MS1hZGQ4LWRjNjNlODAxMDBiZCIsImMiOjE3MTgzNjg4MTcwODEsInMiOjAsInIiOjAsInNiIjowLCJzciI6MCwic2UiOjAsImZzIjowLCJzcCI6MH0=");
            map.put("incap_ses_1512_2209364","3mHcCsFRkXsBtRuBFLT7FDE6bGYAAAAAvH8HZzkdRt838dM+zZC5mw==");
            map.put("cf_clearance","kew8vykrmPmkrAZaG5jZcIIPW_8RDgcS8igPWi9Xa78-1718368818-1.0.1.1-gNql8NjaIDlfNqjcwEwC4hP34p9DXRJRugY0En1jGWg_Re3PPUsEEF.5VGZXhnpmWzrh6aVoks3BVOkt3DhmEQ");
            map.put("SnapABugUserAlias","%23");

            Random random = new Random();
            int randomNumber = random.nextInt(2) + 1; // 生成1或2
            map.put("_ga","GA1."+randomNumber+".1297612870.1718348214");

            // 获取当前时间戳（秒级）
            long currentTimestamp = System.currentTimeMillis() / 1000;
            int randomTime  = random.nextInt(59)+10;
            map.put("_ga_ESS0NB66T4","GS1.1.1718371206.3.1."+currentTimestamp+"."+randomTime+".0.0");

            map.put("SnapABugVisit",numberBath+"#1718348219");
            long currentTimestamp2 = System.currentTimeMillis() / 1000;
            int randomTime2  = random.nextInt(59)+10;
            map.put("_ga_Q72ZQB7GTR","GS1.1.1718371206.3.1."+currentTimestamp2+"."+randomTime2+".0.0");


            System.out.print(map);
            Document document = Jsoup.connect(firstUrl)
//                    .proxy(proxy)
                    .timeout(20000)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
                    .method(Connection.Method.GET)
                    .header("Host","pubs.acs.org")
                    .header("Referer",firstUrl)
//                    .header("Connection","keep-alive")
//                    .header("Accept-Encoding", "gzip, deflate")
//                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("User-Agent", "MMozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/113.0")
//                    .header("Upgrade-Insecure-Requests","1")
//                    .header("Sec-Fetch-Dest","document")
//                    .header("Sec-Fetch-Mode","navigate")
//                    .header("Sec-Fetch-Site","none")
//                    .header("Sec-Fetch-User","?1")
                    .cookies(map)
                    .get();
            System.out.println(document.select("title").text());
            Random random3 = new Random();
            int sleepTime = (random3.nextInt(3) + 1) * 1000;
            Thread.sleep(sleepTime);
            return String.valueOf(document.html());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String url = "https://pubs.acs.org/doi/10.1021/cg2008478";
        Method_DownHtml(url,14);
    }

}