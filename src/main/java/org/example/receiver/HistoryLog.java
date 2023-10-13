package org.example.receiver;

import lombok.Data;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Data
public class HistoryLog {
    public static final String file_path = "history.log";

    public static LocalDateTime ses_start;
    public static LocalDateTime cmd_start;

    public static List<String> history_lines=new ArrayList<>();

    public void ses_start_log() throws IOException {
        ses_start = LocalDateTime.now();
        check_and_create_file_if_not_exist();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file_path,true));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        stringBuilder.append("session start at ").append(fmt.format(ses_start)).append('\n');
        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
    }
    public void cmd_log(String cmd, List<String> cmd_args) throws IOException {
        cmd_start = LocalDateTime.now();
        check_and_create_file_if_not_exist();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file_path,true));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        stringBuilder.append(fmt.format(cmd_start)).append(' ').append(cmd);
        for(String cmd_arg:cmd_args){
            stringBuilder.append(" ").append(cmd_arg);
        }
        stringBuilder.append('\n');
        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
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

    public boolean history_by_num(int num){
        try{
            if(num<0){
                System.out.println("history参数错误");
                return false;
            } else{
                BufferedReader br = new BufferedReader(new FileReader(file_path));
                String s;
                for (int i=0;i<num;i++){
                    if((s = br.readLine())!=null){
                        if(s.charAt(0)!='s'){
                            System.out.println(s);
                        }
                    }
                }
                br.close();
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean history_all(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            String s;
            // 使用readLine方法，一次读一行
            while((s = br.readLine())!=null){
                if(s.charAt(0)!='s'){
                    System.out.println(s);
                }
            }
            br.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
