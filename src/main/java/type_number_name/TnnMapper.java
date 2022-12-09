package type_number_name;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/*
stu[0]:销售日期
stu[1]:商品名
stu[2]:销量
stu[3]:类别
stu[4]:单价
该功能实现统计各类别的商品数及它们的商品名
 */
public class TnnMapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
        //将文件的每一行传递过来，使用split分割后利用字符数组进行接收
        String[] splits = value.toString().split(",");
        //向Reducer传递参数-> Key：类别 Value：商品名
        context.write(new Text(splits[3]),new Text(splits[1]));
    }
}
