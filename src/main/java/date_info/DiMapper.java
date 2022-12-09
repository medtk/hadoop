package date_info;

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
该功能实现统计每日的销售信息
 */

public class DiMapper extends Mapper<LongWritable,Text,Text,Text> {
    @Override
    protected void map(LongWritable Key1, Text value1,Context context) throws IOException, InterruptedException {
        //将文件的每一行传递过来，使用split分割后利用字符数组进行接收
        String[] splits= value1.toString().split(",");
        //拼接字符串：学生名和成绩
        String date = splits[0];
        String name = splits[1];
        String volume = splits[2];
        String com_info = name + "：" + volume;
        //向Reducer传递参数-> Key：销售日期 Value：商品名+销量
        context.write(new Text(date),new Text(com_info));
    }
}
