package com.fengwenyi.mp3demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengwenyi.mp3demo.dao.UsCityDataDao;
import com.fengwenyi.mp3demo.dto.CityDataTreeResponse;
import com.fengwenyi.mp3demo.dto.Menu;
import com.fengwenyi.mp3demo.model.UsCityData;
import com.fengwenyi.mp3demo.service.MPUsCityDataService;
import com.fengwenyi.mp3demo.util.BeanCopierUtils;
import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Wenyi Feng
 * @since 2019-06-27
 */
@Service
@Slf4j
public class UsCityDataServiceImpl extends ServiceImpl<UsCityDataDao, UsCityData> implements MPUsCityDataService {

    @Autowired
    UsCityDataDao usCityDataDao;

    @Override
    public List<CityDataTreeResponse> getUsCityDataAll() {
        List<UsCityData> cityDataList = usCityDataDao.selectAll();
        log.info("地址信息list {}", new Gson().toJson(cityDataList));

//        List<CityDataTreeResponse> tree = new ArrayList<>();
//        cityDataList.forEach(u->{
//            CityDataTreeResponse dataTree = new CityDataTreeResponse();
//            BeanCopierUtils.copyProperties(u,dataTree);
//            tree.add(dataTree);
//        });

        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<String, CityDataTreeResponse> tree = cityDataList.stream().collect(Collectors.toMap(UsCityData::getCode, c -> {
            CityDataTreeResponse t = new CityDataTreeResponse();
            BeanCopierUtils.copyProperties(c, t);
            t.setCity(new ArrayList<>());
            return t;
        }));

        tree.forEach((k, v) -> {
            CityDataTreeResponse proposedParent;
            String parentCode = v.getParentCode();
            if (tree.containsKey(parentCode)) {
                proposedParent = tree.get(parentCode);
                proposedParent.getCity().add(v);
            }
        });

        List<CityDataTreeResponse> treeList = tree.values()
                .stream()
                .filter(c -> StringUtils.isBlank(c.getParentCode()))
                .collect(Collectors.toList());
        stopwatch.stop();
        long milliseconds = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        log.info("时间 {}", milliseconds);
        log.info("地址信息treeList {}", new Gson().toJson(treeList));

//        Stopwatch stopwatch = Stopwatch.createStarted();
//        List<CityDataTreeResponse> treeList = this.buildCityTree(tree, "0");
//        long milliseconds = stopwatch.elapsed(TimeUnit.MILLISECONDS);
//        log.info("build tree 时间:{}", milliseconds);

        return treeList;
    }

    /**
     * @param list 为传入的List
     * @param root root为最顶层节点
     * @return
     */
    private  List<CityDataTreeResponse> buildCityTree(List<CityDataTreeResponse> list, String root) {
        List<CityDataTreeResponse> menus = new ArrayList<>();
        for (CityDataTreeResponse menu : list) {
            String menuCode = menu.getCode();
            String parentCode = menu.getParentCode();
            if (root.equalsIgnoreCase(parentCode)) {
                List<CityDataTreeResponse> menuLists = buildCityTree(list, menuCode);
                menu.setCity(menuLists);
                menus.add(menu);
            }
        }
        return menus;
    }

    /**
     * @param list 传入的List
     * @param root  传入的父级Id
     * @return
     */
    private static List<Menu> buildTree(List<Menu> list, int root) {
        List<Menu> menus = new ArrayList<>();
        for (Menu menu : list) {
            int menuId = menu.getPid();
            int parentId= menu.getParentId();
            if (root == parentId) {
                List<Menu> menuLists = buildTree(list, menuId);
                menu.setChildMenu(menuLists);
                menus.add(menu);
            }
        }
        return menus;
    }

    public static void main(String[] args) {
        List<Menu> list = new ArrayList<>();
        Menu menu1 = new Menu(1, "父级1", 0, 1);
        Menu menu2 = new Menu(2, "父级2", 0, 2);
        Menu menu3 = new Menu(3, "子级1_1", 1, 1);
        Menu menu4 = new Menu(4, "子级1_2", 1, 2);
        Menu menu5 = new Menu(5, "子级1_2_1", 4, 2);
        Menu menu6 = new Menu(6, "子级1_2_2", 2, 2);
        list.add(menu1);
        list.add(menu2);
        list.add(menu3);
        list.add(menu4);
        list.add(menu5);
        list.add(menu6);
        List<Menu> listTree = buildTree(list, 0);
        log.info("build Tree :{}", new Gson().toJson(listTree));
    }

}
