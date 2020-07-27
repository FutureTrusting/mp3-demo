package com.fengwenyi.mp3demo.service.impl;

import com.fengwenyi.mp3demo.config.MyBatisBatch;
import com.fengwenyi.mp3demo.rediskey.biz.RedisKey;
import com.fengwenyi.mp3demo.dao.CityDao;
import com.fengwenyi.mp3demo.dao.IdcardDao;
import com.fengwenyi.mp3demo.dao.StudentDao;
import com.fengwenyi.mp3demo.dto.StudentDto;
import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.model.Idcard;
import com.fengwenyi.mp3demo.model.Student;
import com.fengwenyi.mp3demo.service.TestService;
import com.fengwenyi.mp3demo.util.BeanCopierUtils;
import com.fengwenyi.mp3demo.util.CommonCompletableFutureUtil;
import com.fengwenyi.mp3demo.vo.StudentVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/4/26 11:14
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private Throwable throwable = new Throwable();

    @Autowired
    CityDao cityDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IdcardDao idcardDao;

//    @Override
//    public  IPage<Map<String, Object>> getStudents() {
//        IPage<Map<String, Object>> selectMapsPage = studentDao.selectMapsPage(new Page<>(1, 2),
//                new QueryWrapper<Student>()
//                        .between("age", 2, 25)
//                        .in("gender", 0, 1)
//                        .orderByDesc("age"));
//        System.out.println(selectMapsPage);
//
//        return selectMapsPage;
//    }

    @Override
    public List<Student> getStudentById() {
        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        longs.add(2L);
        List<Student> students = studentDao.selectBatchIds(longs);
        System.out.println(students);
        return students;
    }

    private static final int CORE_POOL_SIZE = 2 * (Runtime.getRuntime().availableProcessors()) + 1;

    @Override
    public List<StudentVO> queryAllStudentDataStore() {
        List<City> cities = cityDao.selectAll();
        /**
         *      集合Map                            key                 value                  说明
         *      HashTable                         不能为null      不能为null          线程安全
         *      ConcurrentHashMap       不能为null      不能为null           线程安全
         *      TreeMap                           不能为null      可以为null           线程安全
         *      HashMap                          可以为null      可以为null           线程安全
         */
        HashMap<Long, String> map = new HashMap<>(1000);
        cities.forEach(d -> map.put(d.getId(), StringUtils.trim(d.getName())));

        List<Student> selectAllStoreRows = studentDao.selectAll();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int poolSize = 2 * availableProcessors + 1;
        log.error(">>>>>>>>>>>>>>>>>>>{}", availableProcessors);
        log.error(">>>>>>>>>>>>>>>>>>>{}", poolSize);
        //32767 maxNum
        ForkJoinPool forkJoinPool = new ForkJoinPool(CORE_POOL_SIZE);
        log.info("Runtime.getRuntime().availableProcessors(): {}  poolSize: {}", availableProcessors, poolSize);
        List<CompletableFuture<StudentVO>> completableFutures = selectAllStoreRows.stream()
                .map(r -> CompletableFuture.supplyAsync(() -> {
                    try {
                        log.info("处理Student 数据名字 start : {}   on thread : {}", r.getName(), Thread.currentThread().getName());
                        return this.processRowChecking(r, map);
                    } catch (Exception e) {
                        log.warn("处理Student 数据名字 start: {}" + r.getName() + "_" + e.getMessage() + "_", e);
                        throw e;
                    }
                }, forkJoinPool))
                .collect(Collectors.toList());
        List<StudentVO> joined;
        try {
            //流式获取结果:此处是根据任务添加顺序获取的结果
            log.info("CommonCompletableFutureUtil.sequence  start");
            // 当任意异常抛出 终止并回滚全部
            joined = CommonCompletableFutureUtil.sequenceExceptionallyCompleteMeetFirstFailure(completableFutures).join();
        } catch (Exception e) {
            log.warn(" xlsxSaxRead failed. Excel导入出错" + e.getMessage() + "_", e);
            throw e;
        } finally {
            log.info("forkJoinPool.shutdownNow success 释放forkJoinPool资源");
            forkJoinPool.shutdownNow();
        }
        log.info("CommonCompletableFutureUtil.sequence  end");
        joined.forEach(u -> {
            log.error("打印获取的StudentVO", u);
        });
        return joined;
    }

    /**
     * check
     *
     * @return
     */
    private StudentVO processRowChecking(Student student, HashMap<Long, String> map) {
        Optional<Student> studentOptional = Optional.of(student);
        StudentVO buildStudentVO = new StudentVO();
        buildStudentVO.setIsDelete(studentOptional.map(Student::getIsDelete).orElse(null));
        buildStudentVO.setAge(studentOptional.map(Student::getAge).orElse(null));
        buildStudentVO.setCardCode(StringUtils.trim(student.getName()));
        buildStudentVO.setId(studentOptional.map(Student::getId).orElse(null));
        buildStudentVO.setCityName(map.get(student.getCityId()));

//        cities.forEach(r -> {
//            // todo  list问题
//            //            boolean equals = ;
//            //            buildStudentVO.setCityName((studentOptional.map(Student::getCityId).orElse(null)).equals(r.getId()) ? StringUtils.trim(r.getName()) : "2334");
////            if ((studentOptional.map(Student::getCityId).orElse(null)).equals(r.getId())) {
////                buildStudentVO.setCityName(StringUtils.trim(r.getName()));
////            } else {
////                buildStudentVO.setCityName("34242");
////            }
//        });
        return buildStudentVO;
    }

    @Override
    public List<StudentVO> getStudentList() {
        String redisKey = RedisKey.getRedisConstantKey.getPrefix();
        System.out.println(redisKey);
        if (redisTemplate.hasKey(redisKey)) {
            List<StudentVO> studentVO = (List<StudentVO>) redisTemplate.opsForValue().get(redisKey);
            return studentVO;
        }
        List<Student> studentList = studentDao.selectAll();
        List<StudentVO> voArrayList = new ArrayList<>();
        studentList.forEach(u -> {
            StudentVO studentVO = new StudentVO();
            BeanCopierUtils.copyProperties(u, studentVO);
            voArrayList.add(studentVO);
        });
        List<StudentVO> studentVOS = buildCarResponseList(voArrayList);
        redisTemplate.opsForValue().set(redisKey, studentVOS);
        return studentVOS;
    }

    private List<StudentVO> buildCarResponseList(List<StudentVO> collect) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(CORE_POOL_SIZE);
        List<CompletableFuture<StudentVO>> completableFutures = collect.stream()
                .map(r -> CompletableFuture.supplyAsync(() -> {
                    try {
                        City cityId = cityDao.selectById(r.getCityId());
                        Idcard idcard = idcardDao.selectById(r.getIdcardId());
                        r.setCityName(cityId == null ? "" : cityId.getName());
                        r.setCardCode(idcard == null ? "" : idcard.getCode());
                        return r;
                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                }, forkJoinPool))
                .collect(Collectors.toList());
        List<StudentVO> joined;
        try {
            //流式获取结果:此处是根据任务添加顺序获取的结果
            joined = CommonCompletableFutureUtil.sequenceExceptionallyCompleteMeetFirstFailure(completableFutures).join();
        } catch (Exception e) {
            throw e;
        } finally {
            forkJoinPool.shutdownNow();
        }
        return joined;
    }

//    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
//
//    private ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000),new DefaultThreadFactory()) {
//        @Override
//        protected void afterExecute(Runnable r, Throwable t) {
//            super.afterExecute(r, t);
//            printException(r, t);
//        }
//    };

    private static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // ignore/reset
            }
        }
        if (t != null) {
            log.error(t.getMessage(), t);
        }
    }

    @Autowired
    MyBatisBatch myBatisBatch;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "student_list", key = "caches[0].name")
    public boolean addStudent(List<City> cities, List<Student> students) {

//        countdownlatch latch = new countdownlatch(2);
//        executor.execute(() -> {
//            try {
//                //查询所有派送员信息
//                //插入用户表
//                cities.forEach(u -> {
//                    int insertUserRole = cityDao.insert(u);
//                    log.warn("插入城市表===>>>>>{}", insertUserRole);
//                    throw new BusinessException("=====抛出一个运行时异常=====>>>>>>>{}");
//                });
//            } finally {
//                latch.countDown();
//            }
//        });
//        executor.execute(() -> {
//            try {
//                //查询所有匹配业务关系
//                students.forEach(u -> {
//                    int insertUserRole = studentDao.insert(u);
//                    log.warn("插入学生表===>>>>>{}", insertUserRole);
//                    throw new BusinessException("=====抛出一个运行时异常====>>>>>>>{}");
//                });
//            } finally {
//                latch.countDown();
//            }
//        });
//
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {

//        long millis = Clock.systemDefaultZone().millis();
//        List<List<Student>> partitions = Lists.partition(students, 1000);
//        partitions.forEach(u->{
//            int batchRows=studentDao.batchInsertStudent(u);
//        });
//        long millis2 = Clock.systemDefaultZone().millis();
//        System.out.println(millis2-millis);
//     int batchRows=studentDao.batchInsertStudent(students);


        //注意 UsOrder里主键id 需要有  @Id 注解 参考 tk.mapper
        long millis = Clock.systemDefaultZone().millis();
        try {
            List<List<Student>> partitions = Lists.partition(students, 1000);
            myBatisBatch.doBatch(StudentDao.class, studentDao -> {
                partitions.forEach(u -> {
                    int insertStudent = studentDao.batchInsertStudent(u);
                });
            });
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        long millis2 = Clock.systemDefaultZone().millis();
        log.error("==耗时=>>>>>>{}", millis2 - millis);

//            countdownlatch latch = new countdownlatch(2);
//
//            CityInsertTask cityInsertTask = new CityInsertTask(cities, cityDao, latch);
//            threadPoolTaskExecutor.execute(cityInsertTask);
//
//            StudentInsertTask studentInsertTask = new StudentInsertTask(students, studentDao, latch);
//            threadPoolTaskExecutor.execute(studentInsertTask);
//
//            throw new BusinessException("=========>>>>>");
//        } catch (Exception e) {
//            log.info("=====扑捉线程异常=======>>>>>{}",e.getMessage());
//            e.printStackTrace();
//        }
        return true;
    }


    @Override
    @CacheEvict(value = "student_list", key = "caches[0].name")
    public boolean delStudent(String studentId) {
        return studentDao.deleteById(studentId) > 0;
    }

    @Override
    @CacheEvict(value = "student_list", key = "caches[0].name")
    public boolean editStudent(StudentDto build) {
        Student student = new Student();
        BeanCopierUtils.copyProperties(build, student);
        return studentDao.updateById(student) > 0;
    }
}
