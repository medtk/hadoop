package commodity_info;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CiReducer extends Reducer<Text, Text,Text, Text> {

    @Override
    protected void reduce(Text key,Iterable<Text> values,Context context)throws IOException,InterruptedException{
        //拼接各件商品每日的销售信息
        String volumeInfo = "";
        for(Text value:values){
            volumeInfo = volumeInfo + value+"   ";
        }
        System.out.println("********************************************************");
        System.out.println(key.toString()+"\n"+volumeInfo);
        context.write(key,new Text(volumeInfo));
    }
}
