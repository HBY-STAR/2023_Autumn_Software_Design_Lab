package org.example.receiver;

import lombok.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Document {
    public static List<String> doc_lines = new ArrayList<>();
    public static String file_path = null;

    public void load(String filePath) {
        try{
            File check_file= new File(filePath);
            //检测是否已加载文件
            if(file_path != null){
                System.out.println("文件: "+file_path+" 已加载，请退出后重新进入以加载其他文件");
                return;
            }
            //检测文件是否存在
            if(check_file.exists()){
                file_path=check_file.getAbsolutePath();
            } else {
                //检测父路径
                //如果父路径为空则自动创建文件
                if(check_file.getParentFile()==null){
                    System.out.println("父路径为空，自动在与src相同的父目录下创建文件...");
                    if(!check_file.createNewFile()){
                        System.out.println("异常，文件创建失败！");
                        System.exit(-1);
                    }
                    file_path=check_file.getAbsolutePath();
                    return;
                }
                //若父路径不为空但不为目录
                else if(!check_file.getParentFile().isDirectory()){
                    System.out.println("路径: "+check_file.getParent()+" 不存在");
                    return;
                }
                //父路径正常
                else {
                    if(!check_file.createNewFile()){
                        System.out.println("异常，文件创建失败！");
                        System.exit(-1);
                    }
                    file_path=check_file.getAbsolutePath();
                    return;
                }
            }
            // 构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            String s;
            // 使用readLine方法，一次读一行
            while((s = br.readLine())!=null){
                doc_lines.add(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public  void save(){
        try{
            if(file_path == null){
                System.out.println("暂未加载任何文件！");
                return;
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file_path));
            StringBuilder strBd = new StringBuilder();
            for(String docLine : doc_lines){
                strBd.append(docLine).append('\n');
            }
            bw.write(strBd.toString());
            bw.flush();
            bw.close();
            System.out.println("保存成功！");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void insert(int row, String text) {
        try{
            if(file_path == null){
                System.out.println("暂未加载任何文件！");
                return;
            }
            if(row<=0){
                System.out.println("若指定行号，则行号应为一个自然数");
            }
            else {
                if(row-1<=doc_lines.size()){
                    doc_lines.add(row-1,text);
                }
                else {
                    for(int i=0;i<row-1-doc_lines.size();i++){
                        doc_lines.add("");
                    }
                    doc_lines.add(doc_lines.size(),text);
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void list() {
        try{
            if(file_path == null){
                System.out.println("暂未加载任何文件！");
            }else {
                for (String docLine : doc_lines) {
                    System.out.println(docLine);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete_row(int row) {
        if(row<=0){
            System.out.println("若指定行号，则行号应为一个自然数");
        }
        else {
            if(row-1<=doc_lines.size()){
                doc_lines.remove(row-1);
            }
            else {
                System.out.println("行号超出文件范围");
            }

        }
    }
    public void delete_text(String text) {
        if(text==null){
            System.out.println("指定的字符串为空");
        }else{
            if(!doc_lines.removeIf(docLine -> docLine.equals(text))){
                System.out.println("未查找到指定字符串");
            }
        }
    }
}
