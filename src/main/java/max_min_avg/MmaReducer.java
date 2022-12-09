package max_min_avg;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MmaReducer extends Reducer<Text, Text,Text, Text> {

    @Override
    protected void reduce(Text key,Iterable<Text> value,Context context)throws IOException,InterruptedException{
        //Arraylist集合储存所有的销量数据，借用collections的方法求最大值最小值
        List<Integer> list = new ArrayList<>();
        for(Text v: value){
            list.add(Integer.valueOf(v.toString()));
        }
        //求max及min
        int maxVolume = Collections.max(list);
        int minVolume = Collections.min(list);
        // 求平均销量
        int sum = 0;
        for(int volume: list){
            sum += volume;
        }
        double avg = sum  / list.size();
        System.out.println("*****************************************");
        String result = "的最高销量:"+maxVolume+"    最低销量:"+minVolume+"    平均销量:"+avg;
        System.out.println(key.toString()+result);

        context.write(key,new Text(result));
    }
}
