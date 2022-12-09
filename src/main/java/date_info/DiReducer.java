package date_info;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DiReducer extends Reducer<Text, Text,Text, Text> {
    @Override
    protected void reduce(Text key,Iterable<Text> values,Context context)throws IOException,InterruptedException{
        //拼接销售日期的商品名和销量
        String commodityInfo = "\n";
        for(Text Info:values){
            commodityInfo = commodityInfo + Info + "   ";
        }
        System.out.println("***********************************************************************************************************************");
        System.out.println(key.toString()+"："+commodityInfo);
        context.write(key,new Text(commodityInfo));
    }
}
