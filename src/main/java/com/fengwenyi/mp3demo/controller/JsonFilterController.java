package com.fengwenyi.mp3demo.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.IdUtil;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.fasterxml.jackson.annotation.JsonView;
import com.fengwenyi.mp3demo.dao.StudentDao;
import com.fengwenyi.mp3demo.dto.PersonDto;
import com.fengwenyi.mp3demo.dto.UsOrderDo;
import com.fengwenyi.mp3demo.enums.GenderEnum;
import com.fengwenyi.mp3demo.model.Person;
import com.fengwenyi.mp3demo.model.Student;
import com.fengwenyi.mp3demo.service.TestService;
import com.fengwenyi.mp3demo.vo.StudentDto;
import com.fengwenyi.mp3demo.vo.StudentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.fengwenyi.mp3demo.threadpool.ConcurrentCompletableFuture.sleep;

/**
 * @author : Caixin
 * @date 2019/8/28 10:36
 */

@Slf4j
@RequestMapping("/json")
@RestController
public class JsonFilterController {

    @Autowired
    TestService testService;

    @GetMapping("/filter")
    @JsonView(StudentDto.UserSimpView.class)
    public List<StudentDto> testJsonFilter() {
        List<StudentVO> students = testService.queryAllStudentDataStore();
        MapperFactory.getCopyByRefMapper().mapClass(StudentVO.class, StudentDto.class).register();
        List<StudentDto> collect = students.stream()
                .map(x -> MapperFactory.getCopyByRefMapper()
                        .map(x, StudentDto.class))
                .collect(Collectors.toList());

        return collect;
    }

    @GetMapping("/filter3")
    @JsonView(StudentDto.UserDetailView.class)
    @ApiOperation(value = "判断Excel格式")
    public void testJsonFilter3(@RequestParam MultipartFile file) {
        // 判断文件类型
        File tempFile = null;
        if (file != null && file.getSize() > 0) {
            InputStream ins = null;
            try {
                ins = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            inputStreamToFile(ins, tempFile);
        }
        if (tempFile == null) {
            log.error("文件格式错误！请重新选择 OFFICE EXCEL文件！");
            throw new ExcelException("340", "文件格式错误！请重新选择 OFFICE EXCEL文件！");
        }
        FileInputStream fileInputStream;
        FileType type = null;
        try {
            fileInputStream = new FileInputStream(tempFile);
            type = FileUtil.getType(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
          //避免异常之前删除文件
          deleteFile(tempFile);
        }
        if (type == null) {
            log.error("文件格式错误！请重新选择 OFFICE EXCEL文件！");
            throw new ExcelException("340", "文件格式错误！请重新选择 OFFICE EXCEL文件！");
        }
        String name = type.name();
        if (StringUtils.isBlank(name)) {
            log.error("文件格式错误！请重新选择 OFFICE EXCEL文件！");
            throw new ExcelException("340", "文件格式错误！请重新选择 OFFICE EXCEL文件！");
        }
        if (!(FileType.DOCX_XLSX.name().equals(name) || FileType.ppt_doc_xls.name().equals(name))) {
            log.error("文件格式错误！请重新选择 OFFICE EXCEL文件！");
            throw new ExcelException("340", "文件格式错误！请重新选择 OFFICE EXCEL文件！");
        }
    }

    private static void deleteFile(File... files) {
        if (Objects.nonNull(files)) {
            Arrays.stream(files).filter(File::exists).forEach(file -> {
                boolean delete = file.delete();
            });
        }
    }

    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, FileConst.BUFFER_BYTES_READ_MAX)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @GetMapping("/filter2")
    @JsonView(StudentDto.UserDetailView.class)
    public List<StudentDto> testJsonFilter2() {
        List<StudentVO> students = testService.queryAllStudentDataStore();
        MapperFactory.getCopyByRefMapper().mapClass(StudentVO.class, StudentDto.class).register();
        List<StudentDto> collect = students.stream()
                .map(x -> MapperFactory.getCopyByRefMapper()
                        .map(x, StudentDto.class))
                .collect(Collectors.toList());

        return collect;
    }

    @Resource(name = "importPool")
    private ThreadPoolExecutor poolExecutor;





    @GetMapping("/filter4")
    public String testJsonFilter3() {
        System.out.println(poolExecutor);
        //任务1：洗水壶->烧开水
        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                    return "白开水";
                },poolExecutor);
//任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                },poolExecutor);
//任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombineAsync(f2, (f11, f22)->{
                    System.out.println("T1:拿到水:" + f11);
                    System.out.println("T1:泡茶..."+f22);
                    return "上茶:" + f11;
                },poolExecutor);
//等待任务3执行结果
        System.out.println("f3.join()"+f3.join());
        System.out.println("2222222");
        return f3.join();
    }

    @Autowired
    StudentDao studentDao;

    @GetMapping("/filter5")
    public String testJsonFilter5() {
        UsOrderDo orderDo1 = new UsOrderDo();
        UsOrderDo orderDo2 = new UsOrderDo();

//        orderDo1.setOrderStatus(1);
        orderDo1.setTrackingOrderStatus(1);
        orderDo1.setTrackingAbnormalReason("aaaaaa");
        orderDo1.setStatus(1);
        orderDo1.setAbnormalReason("aaaaaaaaaaaa");
        orderDo1.setMainMailNo("1131615254");

//        orderDo2.setOrderStatus(2);
        orderDo2.setTrackingOrderStatus(2);
        orderDo2.setTrackingAbnormalReason("bbbbb");
        orderDo2.setStatus(2);
        orderDo2.setAbnormalReason("bbbbbbbbbb");
        orderDo2.setMainMailNo("975098371");


        List<UsOrderDo> usOrderDoList = Arrays.asList(orderDo1, orderDo2);
        studentDao.batchUpdateRouteByMailNo(usOrderDoList);
        return "";
    }
}
