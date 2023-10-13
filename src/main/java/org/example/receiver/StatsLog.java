package org.example.receiver;

import lombok.Data;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class StatsLog {
    public static LocalDateTime ses_start;
    public static LocalDateTime ses_end;
    public static LocalDateTime current_file_start;
    public static LocalDateTime current_file_end;

    public static List<String> current_stats=new ArrayList<>();
    public static String current_file=null;

    public static final String file_path = "stats.log";

    public static List<String> log_lines=new ArrayList<>();

    public void ses_start_log() {
        ses_start = LocalDateTime.now();
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        stringBuilder.append("session start at ").append(fmt.format(ses_start)).append('\n');
        current_stats.add(stringBuilder.toString());
    }
    public void ses_end_log() throws IOException {
        ses_end = LocalDateTime.now();
        StringBuilder stringBuilder = new StringBuilder();
        for(String cur : current_stats){
            stringBuilder.append(cur);
        }
        check_and_create_file_if_not_exist();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file_path,true));
        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
    }
    public void file_start_log(String file_path) {
        current_file_start=LocalDateTime.now();
        current_file=file_path;
    }
    public void file_end_log() {
        current_file_end=LocalDateTime.now();
        File getFileName = new File(current_file);
        current_stats.add("./" + getFileName.getName() + " " + current_file_end.getSecond() + " 秒\n");
        current_file=null;
    }

    private static void check_and_create_file_if_not_exist() throws IOException {
        File check_file= new File(file_path);
        //检测文件是否存在
        if(!check_file.exists()){
            if(!check_file.createNewFile()){
                System.out.println("异常，文件创建失败！");
                System.exit(-1);
            }
        }
    }

    public boolean stats_current() {
        for(String cur : current_stats){
            System.out.print(cur);
        }
        return true;
    }

    public boolean stats_all() {
        try{
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            String s;
            // 使用readLine方法，一次读一行
            while((s = br.readLine())!=null){
                System.out.println(s);
            }
            br.close();
            stats_current();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
