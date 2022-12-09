package commodity_info;

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
该功能实现统计所有商品的信息
 */
public class CiMapper extends Mapper<LongWritable,Text,Text,Text> {
    @Override
    protected void map(LongWritable Key1, Text value1,Context context) throws IOException, InterruptedException {
        //将文件的每一行传递过来，使用split分割后利用字符数组进行接收
        String[] splits= value1.toString().split(",");
        //拼接商品名+类别+单价
        String name = splits[1];
        String type = splits[3];
        String price = splits[4];
        String com_info = "商品名:"+name+"-----类别:"+type+"-----单价:"+price;
        //拼接销售日期+销量
        String date = splits[0];
        String volume = splits[2];
        String course_info = date+"--销量:"+volume;
        //向Reducer传递参数-> Key：商品名+类别+单价  Value：销售日期+销量
        context.write(new Text(com_info),new Text(course_info));
    }
}
