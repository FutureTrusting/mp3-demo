package com.fengwenyi.mp3demo.dto;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-03-20 11:04
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailNoDTO extends BaseBean {

    private String mainMailNo ;

    private String subMailNo;

    public static void main(String[] args) {
        Table<Integer, Integer, String> tt = HashBasedTable.create();
        tt.put(1, 2, "huyan");

        String name = tt.get(1, 2);
        Map<Integer, String> row = tt.row(1);
        Map<Integer, String> colum = tt.column(2);
        Set<Table.Cell<Integer, Integer, String>> ha = tt.cellSet();

        System.err.println("name>>>>"+name);
        System.err.println("row>>>>"+row);
        System.err.println("colum>>>>"+colum);
        System.err.println("ha>>>>"+ha);
    }
}
