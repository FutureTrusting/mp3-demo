package com.fengwenyi.mp3demo.controller;

import com.baidu.unbiz.easymapper.MapperFactory;
import com.baomidou.mybatisplus.extension.api.R;
import com.fengwenyi.mp3demo.annotation.UnSign;
import com.fengwenyi.mp3demo.dto.*;
import com.fengwenyi.mp3demo.enums.GenderEnum;
import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.model.Student;
import com.fengwenyi.mp3demo.service.TestService;
import com.fengwenyi.mp3demo.util.Resolver;
import com.fengwenyi.mp3demo.vo.StudentVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/4/26 11:10
 */

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController3 {


    /**
     * 1000条为一个分组
     */
    private static final Integer MAX_NUMBER = 1000;


    @Autowired
    TestService testService;

    @ApiOperation("增加列表")
    @PostMapping("/studentTest")
    @UnSign
    public List<StudentVO> queryAllStudentDataStore(@Valid @NotBlank(message = "111111") String id) {
        System.out.println(id);
//        List<StudentVO> studentList = testService.queryAllStudentDataStore();
        return new ArrayList<StudentVO>();
    }

    @ApiOperation("增加列表")
    @PostMapping("/student")
//    public R addStudent(@RequestBody @Validated StudentDto studentDto) {
    public R addStudent() {
        List<Student> students = new ArrayList<>();
        List<City> cities = new ArrayList<>();
        for (int i = 1; i < 999999; i++) {
            City city = new City();
            city.setId((long) i);
            city.setName("上海" + i);
            Student student = new Student();
            student.setId((long) i);
            student.setAge(i);
            student.setIdcardId((long) i);
            student.setCityId((long) i);
            student.setGender(GenderEnum.MALE);
            student.setCreateTime(new Date());
            student.setName(String.valueOf(i));
            student.setInfo(String.valueOf(i));
            students.add(student);
            cities.add(city);
        }

        List<PersonDto> personDtoList = cities.stream().map(p -> MapperFactory.getCopyByRefMapper().map(p,
                PersonDto.class)).collect(Collectors.toList());


//        long start = System.currentTimeMillis();
//        //java8 并发流对list进行切割
//        int limit = countStep(cities.size());
//        List<List<City>> lists = StreamUtil.iterate(0, n -> n + 1)
//                .limit(limit).parallel()
//                .map(a -> cities.stream().skip(a * MAX_NUMBER).limit(MAX_NUMBER).parallel().collect(Collectors.toList()))
//                .collect(Collectors.toList());
//        long end = System.currentTimeMillis();
//        log.info("StreamUtil 对list进行切割花费的时间为:{} 毫秒", end - start);
//
//        lists.forEach(u->{
//            log.info("StreamUtil 对list进行为:{}", u.size());
//        });

        long start1 = System.currentTimeMillis();
        //推荐使用GUAVA 的list切割，会缓存
        List<List<City>> listList = Lists.partition(cities, MAX_NUMBER);
        long end1 = System.currentTimeMillis();
        log.info("GUAVA 对list进行切割花费的时间为:{} 毫秒", end1 - start1);
        listList.forEach(u->{
            System.out.println(u);
            log.info("StreamUtil 对list进行为:{}", u.size());
        });

        HashMap<Object, Object> hashMap = new HashMap<>(10);
        HashMap<Object, Object> objectHashMap = Maps.newHashMapWithExpectedSize(100);

        return null;
//      return testService.addStudent(cities, students) ? R.ok("请求成功！") : R.failed("请求失败！");
    }

    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size) {
        return (size + MAX_NUMBER - 1) / MAX_NUMBER;
    }

    @ApiOperation("删除列表")
    @GetMapping("/del/student/{studentId}")
    public R delStudent(@PathVariable(value = "studentId") String studentId) {
        return testService.delStudent(studentId) ? R.ok("请求成功！") : R.failed("请求失败！");
    }

    @ApiOperation("修改列表")
    @GetMapping("/edit/student")
    public R editStudent() {
        StudentDto build = StudentDto.builder()
                .id(2L)
                .age(18)
                .idcardId(1035789714388168706L)
                .cityId(1035762001753501698L)
                .gender(GenderEnum.MALE)
                .createTime(new Date())
                .name("975098371")
                .info("你怎么穿着品如的衣服")
                .build();
        return testService.editStudent(build) ? R.ok("请求成功！") : R.failed("请求失败！");
    }

    @ApiOperation("获取列表")
    @GetMapping("/students")
    public List<StudentVO> getStudents() {
        List<StudentVO> students = testService.getStudentList();
        return students;
    }

    @ApiOperation("根据Id获取列表")
    @PostMapping("/studentList")
    @UnSign
    public List<Student> getStudentsByIds(@RequestBody StudentDto studentDto) {
        System.out.println(studentDto);
        List<Student> student = testService.getStudentById();
        return student;
    }

//    public static <T> OptionalNullPointException<T> resolve(Supplier<T> resolver) {
//        try {
//            T result = resolver.get();
//            return OptionalNullPointException.ofNullable(result);
//        }
//        catch (NullPointerException e) {
//            // 可能会抛出空指针异常，直接返回一个空的 OptionalNullPointException 对象
//            return OptionalNullPointException.empty();
//        }
//    }

//    public static void main(String[] args) {
//        // 繁琐的代码
//        Outer outer = new Outer();
//        if (outer != null && outer.getNested() != null && outer.getNested().getInner() != null) {
//            System.out.println(outer.getNested().getInner().getFoo());
//        }
//
//        OptionalNullPointException.of(new Outer())
//                .map(Outer::getNested)
//                .map(Nested::getInner)
//                // 如果不为空，最终输出 foo 的值
//                .map(Inner::getFoo).ifPresent(System.out::println);
//
//        Outer obj = new Outer();
//        // 直接调用 resolve 方法，内部做空指针的处理
//        // 如果不为空，最终输出 foo 的值
//        resolve(() -> obj.getNested().getInner().getFoo()).ifPresent(System.out::println);
//
//    }

    public static void main(String[] args) {

        BigDecimal decimal = new BigDecimal("-1.3333");
        if (decimal.signum() == (-1)) {
            System.out.println("负数");
        }
        System.out.println("2222");


        BigDecimal bigDecimal = new BigDecimal("12444");
        System.out.println(bigDecimal);

        Outer outer = new Outer();
        Inner inner = Optional.ofNullable(outer.getNested()).isPresent() ? outer.getNested().getInner() : null;
        System.out.println(inner);

        String orElse4 = Optional.of(new Outer()).map(Outer::getNested).map(Nested::getInner)
                // 如果不为空，最终输出 foo 的值
                .map(Inner::getFoo).orElse("萨菲拉");
        System.out.println(orElse4);


        //创建一个空对象
        Optional<String> optStr = Optional.empty();
//        System.out.println(optStr);


        Student student = new Student();
        student.setId(1L);
        student.setName("周杰伦");
        student.setAge(46);

        Long aLong = Optional.ofNullable(student.getCityId()).isPresent() ? student.getCityId() : null;

        Long orElse3 = Optional.ofNullable(student.getCityId()).orElse(null);
        System.out.println(orElse3);

        Outer obj = new Outer();
        // 直接调用 resolve 方法，内部做空指针的处理
        // 如果不为空，最终输出 foo 的值

        Resolver.resolve(() -> obj.getNested().getInner().getFoo()).ifPresent(System.out::println);

        String orElse5 = Optional.of(new Outer()).map(Outer::getNested).map(Nested::getInner)
                // 如果不为空，最终输出 foo 的值
                .map(Inner::getFoo).orElse("为什么");
        System.out.println(orElse5);

        String orElse2 = Resolver.resolve(() -> obj.getNested().getInner().getFoo()).orElse("1111111");
        System.out.println(orElse2);

        String str = null;
        Integer integer = Optional.ofNullable(str).map(String::length).orElse(0);

        // 如果str是null，则创建一个空对象
        Optional<String> optStr2 = Optional.ofNullable(str);

        Optional<Long> optionalPhone = Optional.empty();

        UserDto userDto = new UserDto("Caixin", 28, optionalPhone);
        String name = Optional.of(userDto).map(UserDto::getName).orElse("no name");
        System.out.println(name);

        Optional<UserDto> optionalUser = Optional.of(userDto);

        Long orElse1 = optionalUser.get().getPhone().orElse(null);
        System.out.println(orElse1);

        Long orElse = optionalUser.map(UserDto::getPhone).get().orElse(null);
        System.out.println("====>>>>>>>" + orElse);


        long phone = optionalUser.flatMap(UserDto::getPhone).orElse(-1L);


        long phone2 = optionalUser.flatMap(UserDto::getPhone).orElseGet(() -> {
            return 12345566L;
        });
        System.out.println(phone2);

        Long phone3 = optionalUser.map(UserDto::getPhone).map(Optional::get).orElse(-1L);
        System.out.println(phone3);


        optionalUser.filter(u -> u.getAge() >= 18).ifPresent(u -> System.out.println("Adult:" + u.getId()));
//        System.out.println(integer);

//        System.out.println(optStr2);

    }
}
