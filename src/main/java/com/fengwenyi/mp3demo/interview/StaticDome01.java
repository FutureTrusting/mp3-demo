package com.fengwenyi.mp3demo.interview;

/**
 * @author ECHO
 */
public class StaticDome01  {
    private int a = 10;//实例成员
    private static int B = 20;//

    static class StaticClass{
        public static int c = 30;
        public int d = 40;
        //类方法
        public static void print(){
            //下面代码会报错，静态内部类不能访问外部类实例成员
            //System.out.println(a);

            //静态内部类只可以访问外部类类成员
            System.out.println("静态内部类的静态方法，调用外部类类成员b");
            System.out.println("外部类类成员b="+B);

        }
        //实例方法
        public void print01(){
            //静态内部内所处的类中的方法，调用静态内部类的实例方法，属于外部类中调用静态内部类的实例方法
            System.out.println("静态内部类的实例方法，调用静态内部类的实例方法");
            StaticClass sc = new StaticClass();
            print();
        }
    }
    public static void main(String[] args) {
        /*
         * 要想访问静态内部类的实例成员，需先创建实例对象，才可以调用，因为实例成员是属于实例的， 创建方法：外部类名.内部类名 name = new
         * 外部类名.内部类名()
         * 通过“ 外部类.内部类.属性（方法）” 的方式直接调用静态内部类中的静态属性和方法
         */
        StaticDome01.StaticClass st = new StaticDome01.StaticClass();
        //访问静态内部类的实例方法
        st.print01();
        //访问静态内部类的实例成员
        int i = st.d;
        System.out.println("非外部类调用静态内部类的实例成员d = "+i);
        //访问静态内部类的类成员
        int j = StaticDome01.StaticClass.c;
        System.out.println("非外部类调用静态内部类的类成员C = "+j);
        //访问静态内部类的类方法
        System.out.println("访问静态内部类的类方法：");
        StaticDome01.StaticClass.print();
    }
}
