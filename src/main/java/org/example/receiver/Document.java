package org.example.receiver;

import lombok.Data;
import java.io.*;
import java.util.*;

@Data
public class Document {
    public static List<List<String>> doc_lines = new ArrayList<>();
    public static String file_path = null;

    public boolean load(String filePath) {
        try{
            File check_file= new File(filePath);
            //检测是否已加载文件
            if(file_path != null){
                System.out.println("文件: "+file_path+" 已关闭，开始加载新文件...");
                file_path=null;
                doc_lines=new ArrayList<>();
                return load(filePath);
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
                    return true;
                }
                //若父路径不为空但不为目录
                else if(!check_file.getParentFile().isDirectory()){
                    System.out.println("路径: "+check_file.getParent()+" 不存在");
                    return false;
                }
                //父路径正常
                else {
                    if(!check_file.createNewFile()){
                        System.out.println("异常，文件创建失败！");
                        System.exit(-1);
                    }
                    file_path=check_file.getAbsolutePath();
                    return true;
                }
            }
            // 构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            String s;
            // 使用readLine方法，一次读一行
            while((s = br.readLine())!=null){
                List<String> add = getListStrings(s);
                doc_lines.add(add);
            }
            br.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean save(){
        try{
            if(file_path == null){
                System.out.println("暂未加载任何文件！");
                return false;
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file_path));
            StringBuilder strBd = new StringBuilder();
            for(List<String> docLine : doc_lines){
                for (String docElement: docLine){
                    strBd.append(docElement).append(" ");
                }
                strBd.append("\n");
            }
            bw.write(strBd.toString());
            bw.flush();
            bw.close();
            System.out.println("保存成功！");
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean insert(int row, List<String> text) {
        try{
            if(file_path == null){
                System.out.println("暂未加载任何文件！");
                return false;
            }
            if(row<=0){
                System.out.println("若指定行号，则行号应为一个自然数");
                return false;
            }
            else {
                if(row-1<=doc_lines.size()){
                    doc_lines.add(row-1,text);
                }
                else {
                    for(int i=0;i<row-1-doc_lines.size();i++){
                        doc_lines.add(getListStrings(""));
                    }
                    doc_lines.add(doc_lines.size(),text);
                }
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean list() {
        try{
            if(file_path == null){
                System.out.println("暂未加载任何文件！");
            }else {
                for (List<String> docLine : doc_lines) {
                    for (String docElement : docLine){
                        System.out.print(docElement);
                        System.out.print(" ");
                    }
                    System.out.print("\n");
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete_row(int row) {
        if(row<=0){
            System.out.println("若指定行号，则行号应为一个自然数");
            return false;
        }
        else {
            if(row-1<=doc_lines.size()){
                doc_lines.remove(row-1);
                return true;
            }
            else {
                System.out.println("行号超出文件范围");
                return false;
            }
        }
    }
    public boolean delete_text(List<String> text) {
        if(text.size()==0){
            System.out.println("指定的字符串为空");
            return false;
        }else{
            int flag=0;
            for (int i=0;i<doc_lines.size();i++){
               for (int j=1;j<doc_lines.get(i).size();j++){
                   if(doc_lines.get(i).get(j).equals(text.get(0))){
                       doc_lines.remove(doc_lines.get(i));
                       flag++;
                   }
               }
            }
            if(flag==0){
                System.out.println("未查找到指定字符串");
                return false;
            }
            else{
                System.out.println("共删除 "+flag+" 条语句");
                return true;
            }
        }
    }


    private static List<String> getListStrings(String s) {
        List<String> add = new ArrayList<>();
        Scanner line_scanner = new Scanner(s);
        while (line_scanner.hasNext()){
            add.add(line_scanner.next());
        }
        return add;
    }
}
