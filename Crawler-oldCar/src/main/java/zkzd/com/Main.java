package zkzd.com;

import zkzd.com.Controller.Controller_diyi_detail;

public class Main {
    public static void main(String[] args) {

      Controller_diyi_detail controllerDiyiDetail = new Controller_diyi_detail();

      String savePath = "D:\\ZKZD2024\\二手车ryk\\diyiche_detail\\";
//      controllerDiyiDetail.Method_More_Thread(savePath);

        controllerDiyiDetail.Method_Analysis_diyi_detail(savePath);
    }
}