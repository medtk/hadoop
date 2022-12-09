package max_min_avg;

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
该功能实现的计算出每日销量中的最高值、最低值、平均值
 */

public class MmaMapper extends Mapper<LongWritable,Text,Text,Text> {
    @Override
    protected void map(LongWritable key1,Text value1,Context context)throws IOException,InterruptedException{
        //将文件的每一行传递过来，使用split分割后利用字符数组进行接收
        String[] splits = value1.toString().split(",");

        //向Reducer传递参数-> Key：日期 Value：销量
        context.write(new Text(splits[0]),new Text(splits[2]));
    }
}
