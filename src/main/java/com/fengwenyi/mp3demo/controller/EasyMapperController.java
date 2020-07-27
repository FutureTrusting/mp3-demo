package com.fengwenyi.mp3demo.controller;


import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.baidu.unbiz.easymapper.transformer.Transformer;
import com.fengwenyi.mp3demo.dto.CityDataTreeResponse;
import com.fengwenyi.mp3demo.dto.PersonDto;
import com.fengwenyi.mp3demo.model.Address;
import com.fengwenyi.mp3demo.model.AddressDto;
import com.fengwenyi.mp3demo.model.Person;
import com.fengwenyi.mp3demo.model.UsCityData;
import com.fengwenyi.mp3demo.service.MPUsCityDataService;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Wenyi Feng
 * @since 2019-06-27
 */

@Api(tags = "Future测试")
@Slf4j
@RestController
@RequestMapping("/easyMapper")
public class EasyMapperController {

    public static void main(String[] args) {
        Person p = new Person();
        p.setFirstName("JACK");
        p.setLastName("rose");
        p.setJobTitles(Lists.newArrayList("AA", "BBB", "CCCC"));
        p.setSalary(99998L);

        //与高阶函数搭配使用
        //和guava一起使用做集合的转换
        List<Person> personList = new ArrayList<>();
        personList.add(new Person());
        personList.add(p);

        //和guava一起使用做集合的转换
//        MapperFactory.getCopyByRefMapper().mapClass(Address.class, AddressDto.class).register();
//        MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class).register();
//        Collection<PersonDto> personDtoList = Collections2.transform(personList,
//                person -> MapperFactory.getCopyByRefMapper().map(person, PersonDto.class));
//        System.out.println(personDtoList);

        //和Java8的stream API的配合做map
        //使用做集合的转换
        p.setAddress(new Address("浙江省", "宁波市", "象山县"));
        MapperFactory.getCopyByRefMapper().mapClass(Address.class, AddressDto.class).register();
        MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class).register();
        List<PersonDto> personDtoList = personList.stream().map(p1 -> MapperFactory.getCopyByRefMapper().map(p1,
                PersonDto.class)).collect(Collectors.toList());
        System.out.println("和Java8的stream API的配合做map" + personDtoList);


        //级联映射
//        MapperFactory.getCopyByRefMapper().mapClass(Address.class, AddressDto.class).register();
//        p.setAddress(new Address("浙江省", "宁波市", "象山县"));
//        PersonDto dto = MapperFactory.getCopyByRefMapper()
//                .mapClass(Person.class, PersonDto.class)
//                .register()
//                .map(p, PersonDto.class);
//        System.out.println("级联映射" + dto);


        //源属性为空是否映射
        //如果源属性为空，那么默认则不映射到目标属性，可以强制赋空
//        PersonDto dto = MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class)
//                .mapOnNull(true)
//                .register()
//                .map(p, PersonDto.class);
//        System.out.println("源属性为空是否映射"+dto);


        //映射已经新建的对象
//        PersonDto dto = new PersonDto();
//        MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class).registerAndMap(p, dto);
//        System.out.println("映射已经新建的对象"+dto);


        //自定义额外的全局转换
        PersonDto dto = new PersonDto();
        MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class)
                .customMapping((a, b) -> b.setLastName(a.getLastName().toUpperCase()))
                .register()
                .map(p, dto);
        System.out.println("自定义额外的全局转换"+dto);


        //自定义属性转换
        //Java8的lambda表达式使用方式如下
//        PersonDto dto = MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class)
//                .field("firstName", "firstName", (String s) -> s.toLowerCase())
//                .register()
//                .map(p, PersonDto.class);
//        System.out.println("自定义属性转换"+dto);

        //Java8的stream方式如下
//        PersonDto dto = MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class)
//                .field("jobTitles", "jobTitleLetterCounts",
//                        (List<String> s) -> s.stream().map(String::length).toArray(Integer[]::new))
//                .register()
//                .map(p, PersonDto.class);
//        System.out.println("自定义属性转换"+dto);


        //如果指定了属性了类型，那么lambda表达式则不用写类型，Java编译器可以推测
//        PersonDto personDto = MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class)
//                .field("firstName", "firstName", String.class, String.class, String::toLowerCase)
//                .register()
//                .map(p, PersonDto.class);
//        System.out.println("lambda表达式则不用写类型" + personDto);


        //忽略某个属性
//        PersonDto dto = MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class)
//                .exclude("lastName")
//                .register()
//                .map(p, PersonDto.class);
//        System.out.println("忽略某个属性" + dto);


        //指定属性名称
//        PersonDto dto = MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class)
//                .field("salary", "salaries")
//                .register()
//                .map(p, PersonDto.class);
//        System.out.println("指定属性名称" + dto);


        // 注册和映射分开
//        PersonDto dto = MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class)
//                .register()
//                .map(p, PersonDto.class);

        //映射之Helloworld
        PersonDto dto1 = MapperFactory.getCopyByRefMapper()
                .mapClass(Person.class, PersonDto.class)
                .registerAndMap(p, PersonDto.class);
        System.out.println("映射之Helloworld==>>>" + dto1);

    }

}

