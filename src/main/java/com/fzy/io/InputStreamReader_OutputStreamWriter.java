package com.fzy.io;

import org.junit.Test;

import java.io.*;

/**
 * InputStreamReader和OutputStreamWriter（相比writer和reader更通用，可指定编码）
 *
 * -----FileReader和FileWriter只能使用默认的编码格式来输入输出字符，当需要使用其他编码格式时，可以使用更加通用的类InputStreamReader和OutputStreamWriter--
 * Created by fuzhongyu on 2017/2/13.
 */
public class InputStreamReader_OutputStreamWriter {

    @Test
    public void test() {

        String filepath="src/main/java/com/fzy/io/test_file/myfile.txt";
        String info="write info to myfile! 写入信息到myfile文件";

        inputToFileWithEncoding(info,filepath);

        readFile(filepath,"gbk");
        readFile(filepath,"utf-8");

    }

    /**
     * 写入文件
     * @param info
     * @param filepath
     */
    private void inputToFileWithEncoding(String info, String filepath){
        try {
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(new FileOutputStream(filepath), "gbk");
            //写10行
            for (int i=0;i<10;i++){
                outputStreamWriter.write(info);
                //换行
                outputStreamWriter.write(System.getProperty("line.separator"));
            }


            outputStreamWriter.flush();
            outputStreamWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 读出文件
     * @param filepath
     * @param charset
     */
    private void readFile(String filepath,String charset){
        try {
            InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(filepath),charset);
            CharArrayWriter charArrayWriter=new CharArrayWriter();
            char[] chars=new char[100];
            int len=0;
            while ((len=inputStreamReader.read(chars))!=-1){
                charArrayWriter.write(chars,0,len);
            }
            String str=charArrayWriter.toString();
            System.out.println(str);
            inputStreamReader.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
