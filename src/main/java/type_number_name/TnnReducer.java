package type_number_name;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TnnReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //创建集合来去除重复值(HashSet不允许重复值的存在，故可用来去重)
        List<String> names= new ArrayList<>();
        for (Text value:values){
            names.add(value.toString());
        }
        HashSet<String> singleNames = new HashSet(names);
        //创建StringBuffer用来接收同类别商品的商品名
        StringBuffer sb = new StringBuffer();
        //拼接商品名以及统计商品数
        int num = 0;
        for(String singleName:singleNames){
            sb.append(singleName.toString()).append(",");
            num++;
        }
        sb.deleteCharAt(sb.length() - 1);
        //输出
        String result = "类别一共有" + num + "件,分别为:" +sb.toString();
        System.out.println("********************************************");
        System.out.println(key.toString() + result);
        context.write(key,new Text(result));
    }
}
