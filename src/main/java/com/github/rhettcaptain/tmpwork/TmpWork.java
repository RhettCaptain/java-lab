package com.github.rhettcaptain.tmpwork;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TmpWork {
    private static final String PRE_CHECK = "[{\"checkId\":";
    public static void main(String[] args) {
        TmpWork tmpWork = new TmpWork();
        List<ErrorVo> stErrorList = tmpWork.readJsonDir(
                Paths.get("/home/rhett/Tmp/MT-COST-200408-142400"));
        List<ErrorVo> mtErrorList = tmpWork.readJsonDir(
                Paths.get("/home/rhett/Tmp/MT-COST-200408-180300"));
        stErrorList.sort(Comparator.comparingLong(ErrorVo::getLineNo).thenComparingInt(ErrorVo::getCheckId));
        mtErrorList.sort(Comparator.comparingLong(ErrorVo::getLineNo).thenComparingInt(ErrorVo::getCheckId));
//        System.out.println("------  st  ------");
//        System.out.println(stErrorList);
//        System.out.println("------  mt  ------");
//        System.out.println(mtErrorList);
        System.out.println("------  res  ------");
        System.out.println("st size: " + stErrorList.size());
        System.out.println("mt size: " + mtErrorList.size());
        System.out.println("st=mt: " + stErrorList.equals(mtErrorList));

    }

    private List<ErrorVo> readJsonDir(Path dir){
        List<ErrorVo> errorVoList = new ArrayList<>();
        try {
            Files.list(dir).filter(filePath -> filePath.getFileName().toString().startsWith("IEDebugTempFile"))
                    .forEach(filePath -> errorVoList.addAll(readJsonFile(filePath.toFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorVoList;
    }

    private List<ErrorVo> readJsonFile(File file){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
            String json = br.readLine();
            if(json.startsWith(PRE_CHECK)){
                return readJsonList(json);
            }else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<ErrorVo> readJsonList(String json){
        return JSONArray.parseObject(json, new TypeReference<ArrayList<ErrorVo>>(){});
    }

}
