package com.fengwenyi.mp3demo.controller;


import com.baidu.unbiz.easymapper.MapperFactory;
import com.fengwenyi.mp3demo.dto.PersonDto;
import com.fengwenyi.mp3demo.model.*;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
public class EasyMapperTestController {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        for (int i = 1; i < 999999; i++) {
            Person person = new Person();
            String valueOf = String.valueOf(i);
            person.setAddress(new Address(valueOf,valueOf,valueOf));
            person.setFirstName("wuyanzu"+i);
            person.setLastName("jinchengwu"+i);
            person.setSalary((long)i);
            person.setJobTitles(Lists.newArrayList(valueOf,valueOf));
            personList.add(person);
        }
        System.out.println(personList);

//        long start = System.currentTimeMillis();
//        List<PersonDto> personDtos = new ArrayList<>();
//        personList.forEach(u->{
//            PersonDto personDto = new PersonDto();
//            BeanCopierUtils.copyProperties(u,personDto);
//            personDtos.add(personDto);
//        });
//        System.out.println(personDtos);
//        long start2 = System.currentTimeMillis();
//        System.out.println("999999 copy 耗时:"+(start2-start));


        long start = System.currentTimeMillis();
//        MapperFactory.getCopyByRefMapper().mapClass(Address.class, AddressDto.class).register();

        MapperFactory.getCopyByRefMapper()
                .mapClass(Person.class, PersonDto.class)
                .register();

        List<PersonDto> personDtoList = personList.stream()
                .map(
                        p1 -> MapperFactory.getCopyByRefMapper()
                        .map(p1, PersonDto.class))
                .collect(Collectors.toList());
//        System.out.println("和Java8的stream API的配合做map" + personDtoList);
        long start2 = System.currentTimeMillis();
        System.out.println("999999 copy 耗时:"+(start2-start));


        Person p = new Person();
        p.setFirstName("JACK");
        p.setLastName("rose");
        p.setJobTitles(Lists.newArrayList("AA", "BBB", "CCCC"));
        p.setSalary(99998L);

          PersonDto dto = MapperFactory.getCopyByRefMapper().mapClass(Person.class, PersonDto.class)
                .field("jobTitles", "jobTitleLetterCounts",
                        (List<String> s) -> s.stream().map(String::length).toArray(Integer[]::new))
                .register()
                .map(p, PersonDto.class);
        System.out.println("自定义属性转换"+dto);

    }

}

