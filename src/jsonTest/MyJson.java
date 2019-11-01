package jsonTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaov.bean.ResultInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaov Li
 * @date 2019-10-27 23:54
 */
public class MyJson {
    public static void main(String[] args) {
        //JSON.toJSONString() 实现json对象转化为json字符串和javabean对象转化为json字符串
        List<Student> list = new ArrayList<>();
        Student student1 = new Student("bob",24);
        Student student2 = new Student("lily",23);
        list.add(student1);
        list.add(student2);
        System.out.println("*******javaBean  to jsonString*******");
        String str1 = JSON.toJSONString(student1);
        System.out.println(str1);
        System.out.println(JSON.toJSONString(list));
        System.out.println();

        System.out.println("******jsonString to javaBean*******");
        Student stu1=JSON.parseObject(str1,Student.class);
        System.out.println(stu1);
        System.out.println();

        System.out.println("******javaBean to jsonObject******");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(stu1);
        System.out.println(jsonObject1.getString("name"));
        System.out.println();

        System.out.println("******jsonObject to javaBean******");
        Student stu2 = JSON.toJavaObject(jsonObject1,Student.class);
        System.out.println(stu2);
        System.out.println();

        System.out.println("*******javaBean to jsonArray******");
        List<Student> stuList = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            stuList.add(new Student("student"+i,i+20));
        }
        JSONArray jsonArray = (JSONArray) JSON.toJSON(stuList);
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(jsonArray.getJSONObject(i));
        }
        System.out.println();

        System.out.println("*****jsonArry to javalist******");
        List<Student> list1 = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Student stu3 = JSON.toJavaObject(jsonArray.getJSONObject(i),Student.class);
            list1.add(stu3);
        }
        for (Student student : list1) {
            System.out.println(student);
        }
        System.out.println();

        System.out.println("*****jsonString to jsonArray*****");
        String list1_str = JSON.toJSONString(list1);
        System.out.println(list1_str);
        JSONArray jsonArray1 = JSON.parseArray(list1_str);
        for (int i = 0; i < jsonArray1.size(); i++) {
            System.out.println(jsonArray1.getJSONObject(i));
        }
        System.out.println();

        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setErrorMsg("error");
        System.out.println(info);
        System.out.println(JSON.toJSONString(info));
    }
}
