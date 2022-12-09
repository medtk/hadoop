import date_info.DiMain;
import max_min_avg.MmaMain;
import type_number_name.TnnMain;
import commodity_info.CiMain;
import sum_avg_sort.SasMain;

import java.lang.reflect.Method;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("=========基于MapReduce的商场商品销售情况分析=========");
                System.out.println("1、计算每日销售额的最高值、最低值、平均值");
                System.out.println("2、计算每件商品的总销量及日均销量并进行排序");
                System.out.println("3、统计所有商品的信息");
                System.out.println("4、统计各类别的商品数及它们的商品名");
                System.out.println("5、统计每日的销售信息");
                System.out.println("6、退出");
                System.out.print("请输入你想要选择的功能：");
                int option = scanner.nextInt();
                Method method = null;
                switch(option){
                    case 1:
                        method = MmaMain.class.getMethod("main", String[].class);
                        method.invoke(null, (Object) new String[] {});
                        break;
                    case 2:
                        method = SasMain.class.getMethod("main", String[].class);
                        method.invoke(null, (Object) new String[] {});
                        break;
                    case 3:
                        method = CiMain.class.getMethod("main", String[].class);
                        method.invoke(null, (Object) new String[] {});
                        break;
                    case 4:
                        method = TnnMain.class.getMethod("main", String[].class);
                        method.invoke(null, (Object) new String[] {});
                        break;
                    case 5:
                        method = DiMain.class.getMethod("main", String[].class);
                        method.invoke(null, (Object) new String[] {});
                        break;
                    case 6:
                        System.exit(1);
                        break;
                    default:
                        System.out.println("输入正确的功能按键！！");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
