package sum_avg_sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SasReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
        System.out.println("*********************************************************************");
        //定义一个ArrayList集合接收该商品的各日销量
        List<Integer> volumes = new ArrayList<>();
        for(Text value:values){
            volumes.add(Integer.valueOf(value.toString()));
        }
        //对该商品进行求总销量、日均销量
        int num = 0, sum = 0;
        for(Integer volume:volumes){
            sum = sum + volume.intValue();
            num = num + 1;
        }
        float avg = sum / num;
        //销量排序
        Collections.sort(volumes);
        String sort = "的总销量:"+sum+" 日均销量:"+avg+" 该商品的日销量从低到高排序是:";
        for(Integer volume:volumes){
            sort = sort + volume + "  ";
        }
        System.out.println(key.toString()+sort);
        //输出
        context.write(key,new Text(sort));
    }
}
