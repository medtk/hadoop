package type_number_name;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TnnMain {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
        //创建job和任务的入口
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(TnnMain.class);

        //设置Mapper和Reducer的入口
        job.setMapperClass(TnnMapper.class);
        job.setReducerClass(TnnReducer.class);

        //设置Mapper的输入输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //设置Reducer的输入输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //指定输入输出路径
        String inputPath = "hdfs://localhost:9000/mapreduce/input/商品列表.csv";
        String outputPath = "hdfs://localhost:9000/mapreduce/output/各类别商品数及它们的商品名.txt";
        FileInputFormat.setInputPaths(job,new Path(inputPath));
        FileOutputFormat.setOutputPath(job,new Path(outputPath));

        //输出路径存在的话就删除，不然就只能手动删除，否则会报该文件已存在的异常
        FileSystem fileSystem = FileSystem.get(new URI(outputPath), conf);
        if (fileSystem.exists(new Path(outputPath))) {
            fileSystem.delete(new Path(outputPath), true);
        }

        //执行job
        job.waitForCompletion(true);
    }
}
