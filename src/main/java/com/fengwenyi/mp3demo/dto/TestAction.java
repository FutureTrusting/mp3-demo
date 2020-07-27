package com.fengwenyi.mp3demo.dto;


public class TestAction {
    public void aTest() {
        TestAction testAction = new TestAction();
        DemoBean demoBean = new DemoBean();
        demoBean.setId("1");
        System.out.println(demoBean.getId());
        testAction.bTest(demoBean);
        System.out.println(demoBean.getId());
    }


    private void bTest(DemoBean demoBean) {
        demoBean.setId("2");
    }

}
