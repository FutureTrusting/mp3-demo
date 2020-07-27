package com.fengwenyi.mp3demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fengwenyi.javalib.util.StringUtil;
import com.fengwenyi.mp3demo.business.AppBusiness;
import com.fengwenyi.mp3demo.enums.GenderEnum;
import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.model.Idcard;
import com.fengwenyi.mp3demo.model.Student;
import com.fengwenyi.mp3demo.service.MPCityService;
import com.fengwenyi.mp3demo.service.MPStudentService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 测试
 *
 * @author Wenyi Feng
 * @since 2018-09-01
 */
@RestController
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "App 测试示例")
public class AppController {

    @GetMapping("/jsoup")
    public void testJsoup() {
//        String htmlStr = "<table id=kbtable >"
//                + "<tr> "
//                + "<td width=123>"
//                    + "<div id=12>这里是要获取的数据1</div>"
//                    + "<div id=13>这里是要获取的数据2</div>"
//                + "</td>"
//                    + "<td width=123>"
//                    + "<div id=12>这里是要获取的数据3</div>"
//                    + "<div id=13>这里是要获取的数据4</div>"
//                    + "</td>	"
//                + "</tr>"
//                + "</table>";
//        Document doc = Jsoup.parse(htmlStr);
        Document doc = null;
        try {
            doc = Jsoup.connect("https://live.aicai.com/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 根据id获取table
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        System.out.println(format);
        Elements table = doc != null ? doc.getElementsByClass("tbody_body jq_match_" + format + "_tbody jq_match_bodyHead") : null;
//        Element table = doc.getElementById("jq_jsbf_body");
        // 使用选择器选择该table内所有的<tr> <tr/>
        Elements trs = table.select("tr");
        //遍历该表格内的所有的<tr> <tr/>
        for (int i = 0; i < trs.size(); ++i) {
            // 获取一个tr
            Element tr = trs.get(i);
            // 获取该行的所有td节点
            Elements tds = tr.select("td");
            // 选择某一个td节点
            for (int j = 0; j < tds.size(); ++j) {
                Element td = tds.get(j);
                // 获取td节点的所有div
                Elements divs = td.select("div");
                // 选择一个div
                for (int k = 0; k < divs.size(); k++) {
                    Element div = divs.get(k);
                    //获取文本信息
                    String text = div.text();
                    //输出到控制台
                    System.out.println(text);
                }
            }
        }
    }


    @Autowired
    private MPCityService mpCityService;

    @Autowired
    private AppBusiness appBusiness;

    @Autowired
    private MPStudentService mpStudentService;

    // 查询所有城市
    @GetMapping("/queryCityAll")
    public List<City> queryCityAll() {
//        this.testLambda();
        return mpCityService.queryCityAll();
    }


    @GetMapping("/lambda")
    public List<City> testLambda() {
//        new Thread(() -> System.out.println("hello lambda1")).start();
//        new Thread(() -> System.out.println("hello lambda2")).start();
//
//        List<String> list = Arrays.asList(new String[]{"b", "c", "a"});
//        System.out.println(list);
//        Collections.sort(list, (str1,str2) -> str1.compareTo(str2));
//        System.out.println(list);
//        List<String> list2 = Arrays.asList(new String[]{"c", "b", "a"});
//        Collections.sort(list2, String::compareTo);
//        System.out.println(list2);
//        List<String> list3 = Arrays.asList(new String[]{"1", "3", "2"});
//        Collections.sort(list3, Comparator.naturalOrder());
//        System.out.println(list3);
//
//        List<String> list4 = Arrays.asList(new String[]{"CNm", "Mlgb", "gNn", "hAHakJKFHFNn"});
//        List<String>toLowerCaseName= list4.stream().map(name -> {
//            return name.toLowerCase();
//        }).collect(Collectors.toList());
//
//        List<String> lowercaseNames1 = list4.stream().map(name -> {
//            return name.toLowerCase();
//        }).collect(Collectors.toList());
//
//        System.out.println(list4);
//        System.out.println(toLowerCaseName);
//
//        List<String> list5 = Arrays.asList(new String[]{"werwer243", "FFDE32432", "FDFE322", "fewfe#RFDF"});
//
//        List<String>toLowerCaseName3= list4.stream().map(name -> name.toLowerCase()).collect(Collectors.toList());
//
//        List<String>toLowerCaseName2= list5.stream().map(String::toLowerCase).collect(Collectors.toList());
//        System.out.println(list5);
//        System.out.println("list5==============>"+toLowerCaseName2);

//            String waibu="lambda:";
//            List<String> proStrs  = Arrays.asList(new String[]{"Ni", "HaO", "A", "lIyinGhE"});
//
//            List<String> execStrs  =proStrs.stream().map(chuandi -> {
//                long zidingyi = System.currentTimeMillis();
//                return  waibu+chuandi+"==========>"+zidingyi;
//            }).collect(Collectors.toList());
//             execStrs.forEach(System.out::println);
//             execStrs.forEach(r ->System.out.println(r));

        //转全小写
//            List<String> proStrs = Arrays.asList("Ni","Hao","Lambda");
//            List<String> execStrs = proStrs.stream().map(str -> {
//                System.out.println(this.getClass().getName());
//                return str.toLowerCase();
//            }).collect(Collectors.toList());
//            execStrs.forEach(System.out::println);

//             List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
//            long count = nums.stream().filter(num -> num != null).count();
//            long count1 = nums.stream().filter(Objects::nonNull).count();
//            System.out.println(nums);
//            System.out.println(count);
//            System.out.println(count1);

//        StreamUtil<Integer> integerStream = StreamUtil.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
//        StreamUtil<String> taoBao = StreamUtil.of("TaoBao");
//        System.out.println(integerStream);
//        System.out.println(taoBao);
//
//        StreamUtil<Double> generate = StreamUtil.generate(new Supplier<Double>() {
//            @Override
//            public Double get() {
//                return Math.random();
//            }
//        });
//        System.out.println(generate);
//
//
//        StreamUtil<Double> generate1 = StreamUtil.generate(() -> Math.random());
//        StreamUtil<Double> generate2 = StreamUtil.generate(Math::random);
//
//        System.out.println(generate1);
//        System.out.println(generate2);

//        StreamUtil<Integer> limit = StreamUtil.iterate(1, item -> item + 1).limit(1000);
//        StreamUtil.iterate(1, item -> item + 1).forEach(r-> System.out.println(r));
//        limit.forEach(r->System.out.println(r));
//        limit.forEach(System.out::println);

        //    List<Integer> integers = Lists.newArrayList(1,null,3,4,5,5,8,7,6,9,null);

//        List<Integer> collect = integers.stream().distinct().collect(Collectors.toList());
        //System.out.println(collect);


//        List<Integer> collect1 = integers.stream().filter(r -> !r .equals(5)).collect(Collectors.toList());
//        System.out.println(collect1);

//        List<Integer> nums  = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
//        System.out.println(nums.stream().filter(num->num!=null).distinct().mapToInt(num->num*2).peek(c-> System.out.println(c)).skip(2).limit(4).sum());

//        List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
//        System.out.println(ints.stream().anyMatch(r -> r==10 ));

//        String[] array = {"a", "b", "c"};
//        for(Integer i : Lists.newArrayList(1,2,3)){
//            StreamUtil<String> stream = StreamUtil.of(array).map(item -> Strings.padEnd(item, i, '@'));
//            stream.forEach(System.out::println);
//
//            StreamUtil.of(array).map(item -> Strings.padEnd(item, i, '@')).forEach(System.out::println);
//        }

//        List<String> names = new ArrayList<>();
//        names.add("TaoBao");
//        names.add("ZhiFuBao");
//        List<Character> collect = names.stream().map(name -> name.charAt(0)).collect(Collectors.toList());
//        System.out.println(collect);
//        collect.forEach(System.out::println);


//        Map<Integer, String> map = new HashMap<>();
//        map.put(1, "one");
//        map.put(2, "two");
//        map.put(3, "three");
//        map.replaceAll((k, v) -> v.toUpperCase());
//        System.out.println("=============>replaceAll"+map);
//
//         map.merge(3, "four", (v1, v2) -> v1 + v2);
//        System.out.println("=============merge"+map);
//
//        map.forEach((k,v)->{
//            System.out.println("=========>forEach"+k);
//            System.out.println("=========>forEach"+v);
//        });


//        StreamUtil<String> stream = StreamUtil.of("I", "love", "you", "too");
//        stream.forEach(System.out::println);

//        List<String> arrayList = Lists.newArrayList("I", "love", "you", "too");
//        arrayList.stream().filter(r->r.length()==3).forEach(System.out::println);

//        List<String>  stringList= Lists.newArrayList("I", "i", "love", "you", "you","too", "too");
//        stringList.stream().distinct().forEach(System.out::println);

//        List<String>  stringList= Lists.newArrayList("I", "eight", "loving", "wanna", "you","wanna", "too", "i");
//        stringList.stream().sorted((str1,str2)->str1.length()-str2.length()).forEach(System.out::println);
        // stringList.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);


//        List<String> list = Lists.newArrayList("I", "i", "love", "you", "you","too", "too");
//     //   List<String> collect = list.stream().map(r -> r.toUpperCase()).collect(Collectors.toList());
//         list.stream().map(String::toUpperCase).forEach(System.out::println);
        //  list.stream().map(String::toUpperCase).forEach(r-> System.out.println(r));


//        List<List<Integer>> interList = Lists.newArrayList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
//        interList.stream().flatMap(list->list.stream()).forEach(System.out::println);


//        List<String> arrayList = Lists.newArrayList("I", "love", "you", "too");
//        OptionalNullPointException<String> reduce = arrayList.stream().reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
//
//        OptionalNullPointException<String> max = arrayList.stream().max((s1, s2) -> s1.length() - s2.length());
//        OptionalNullPointException<String> max2 = arrayList.stream().max(Comparator.comparingInt(String::length));
//
//        System.out.println("==========>reduce"+reduce.get());
//        System.out.println("==========>max"+max.get());


//        List<String> arrayList = Lists.newArrayList("I", "love", "you", "too");
//
//        int sum = arrayList.stream().mapToInt(str -> str.length()).sum();
//        int sum2 = arrayList.stream().mapToInt(String::length).sum();
//        System.out.println("=============>mapToInt==>sum"+sum);
//
//        Integer reduce = arrayList.stream().reduce(0, (s1, s2) -> s1 + s2.length(), (a, b) -> a + b);
//        System.out.println("=============>reduce"+reduce);

        List<String> stringList = Lists.newArrayList("I", "love", "you", "too");

//        List<String> collect1 = stringList.stream().collect(Collectors.toList());
//        List<String> collect2 = new ArrayList<>(stringList);
//        Set<String> collect3 = new HashSet<>(stringList);
//        Set<String> collect4 = stringList.stream().collect(Collectors.toSet());

//        Map<String, Integer> map = stringList.stream().collect(Collectors.toMap(Function.identity(), r->r.length()));
//        Map<String, Integer> map2 = stringList.stream().collect(Collectors.toMap(Function.identity(), String::length));
//        System.out.println(map);
//        System.out.println(map2);
//        System.out.println(map.get("love"));

//        ArrayList<String> collect = stringList.stream().collect(Collectors.toCollection(ArrayList::new));
//        System.out.println(collect);
//        HashSet<String> collect5 = stringList.stream().collect(Collectors.toCollection(HashSet::new));
//        System.out.println(collect5);

        Student student = new Student();
        student.setAge(199);
        student.setCityId(1131615254L);
        student.setGender(GenderEnum.SECRECY);
        student.setIdcardId(975098371L);
        student.setName("Caixin");
        student.setId(336300L);

        Student student2 = new Student();
        student2.setAge(19999999);
        student2.setCityId(11316152541111L);
        student2.setGender(GenderEnum.SECRECY);
        student2.setIdcardId(9750983711111L);
        student2.setName("Caixin2");
        student2.setId(3363001111L);

        List<Student> students = Lists.newArrayList();
        students.add(student);
        students.add(student2);

//        Map<Student, String> collect = students.stream().collect(Collectors.toMap(Function.identity(), Student::getName));
//        System.out.println(collect);


        //partitioningBy
//        Map<Boolean, List<Student>> collect = students.stream().collect(Collectors.partitioningBy(s -> s.getAge() >= 1000));
//        System.out.println(collect);

        //groupingBy
//        Map<String, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getName));
//        System.out.println(collect);

        //groupingBy上游收集器    counting下游收集器
//        Map<GenderEnum, Long> collect1= students.stream().collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));
//        System.out.println(collect1);


//        Map<Long, List<String>> collect = students.stream().collect(Collectors.groupingBy(Student::getIdcardId, Collectors.mapping(Student::getName, Collectors.toList())));
//        System.out.println(collect);


//        List<String> stream = Lists.newArrayList("I", "love", "you", "too");
//        String collect = stream.stream().collect(Collectors.joining());
//        System.out.println(collect);
//        String collect1 = stream.stream().collect(Collectors.joining(","));
//        System.out.println(collect1);
//        String collect2 = stream.stream().collect(Collectors.joining(",", "{", "}"));
//        System.out.println(collect2);

        List<String> strings = Lists.newArrayList("IA", "love", "you", "Atoo", "Aeight");
        OptionalInt max = strings.stream()
                .filter(s -> s.startsWith("A"))
                .mapToInt(String::length)
                .max();
        System.out.println(max);


//        return mpCityService.queryCityAll();

        return null;
    }


    public interface Collection<E> extends Iterable<E> {
        //其他方法省略
        default Stream<E> stream() {
            return StreamSupport.stream(spliterator(), false);
        }

    }


    // 添加城市
    @PostMapping("/addCity")
    public boolean addCity(String name) {
        if (StringUtil.isEmpty(name)) {
            return false;
        }
        return mpCityService.addCity(new City().setName(name));
    }

    // 添加学生
    @PostMapping("/addStudent")
    public boolean addStudent(String name, Integer age, String gender, String info, String idCardCode, String cityName) {

        // 检验参数
        if (StringUtil.isEmpty(name)
                || age == null
                || StringUtil.isEmpty(gender)
                || StringUtil.isEmpty(info)
                || StringUtil.isEmpty(idCardCode)
                || StringUtil.isEmpty(cityName)) {
            return false;
        }

        // 获取GenderEnum
        GenderEnum genderEnum = GenderEnum.getEnumByDesc(gender);

        // student
        Student student = new Student()
                .setName(name)
                .setAge(age)
                .setGender(genderEnum)
                .setInfo(info);

        // city
        City city = new City().setName(cityName);

        // idCard
        Idcard idcard = new Idcard().setCode(idCardCode);

        // service
        return appBusiness.addStudent(student, city, idcard);
    }

    // 分页查询学生
    @GetMapping("/queryStudentByPage/{currentPage}")
    public IPage<Student> queryStudentByPage(@PathVariable("currentPage") Long currentPage) {
        if (currentPage == null) {
            return null;
        }
        return mpStudentService.queryStudentByPage(currentPage);
    }
}
