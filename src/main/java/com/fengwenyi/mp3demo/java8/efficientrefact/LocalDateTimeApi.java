package com.fengwenyi.mp3demo.java8.efficientrefact;

import java.time.*;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * @author : Caixin
 * @date 2019/7/23 15:06
 */
public class LocalDateTimeApi {

    //12.1  LocalDate 、 LocalTime 、 Instant 、 Duration 以及 Period
    //12.1.1 使用 LocalDate 和 LocalTime

    //你可以通过静态工厂方法 of 创建一个 LocalDate 实例。 LocalDate 实例提供了多种方法来读取常用的值，比如年份、月份、星期几等，如下所示。

    public static void main(String[] args) {
        //代码清单12-1 创建一个 LocalDate 对象并读取其值
        //2014-03-18
        LocalDate date = LocalDate.of(2014, 3, 18);
//        System.out.println(date99);
        //2014
        int year = date.getYear();
        //System.out.println(year);
        //MARCH
        Month month = date.getMonth();
        //3
        int monthValue = date.getMonthValue();
//        System.out.println(monthValue);
//        System.out.println(month);
        //18
        int day = date.getDayOfMonth();
//        System.out.println(day);
//        //TUESDAY
        DayOfWeek dow = date.getDayOfWeek();
//        System.out.println(dow);
//        //31 (days in March)
        int lengthOfMonth = date.lengthOfMonth();
//        System.out.println(lengthOfMonth);
//        //false (not a leap year) 是否是闰年
        boolean leap = date.isLeapYear();
//        System.out.println(leap);

        //你还可以使用工厂方法从系统时钟中获取当前的日期：
        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate);

        // 本章剩余的部分会探讨所有日期-时间类，这些类都提供了类似的工厂方法。你还可以通过
        // 传递一个 TemporalField 参数给 get 方法拿到同样的信息。 TemporalField 是一个接口，它定
        // 义了如何访问 temporal 对象某个字段的值。 ChronoField 枚举实现了这一接口，所以你可以很
        // 方便地使用 get 方法得到枚举元素的值，如下所示。

        //代码清单12-2 使用 TemporalField 读取 LocalDate 的值
        int year1 = date.get(ChronoField.YEAR);
        int month1 = date.get(ChronoField.MONTH_OF_YEAR);
        int day1 = date.get(ChronoField.DAY_OF_MONTH);
//        System.out.println(year1);
//        System.out.println(month1);
//        System.out.println(day1);

        // 类似地，一天中的时间，比如13:45:20，可以使用 LocalTime 类表示。你可以使用 of 重载的
        // 两个工厂方法创建 LocalTime 的实例。第一个重载函数接收小时和分钟，第二个重载函数同时方法访问这些变量的值还
        // 接收秒。同 LocalDate 一样， LocalTime 类也提供了一些 getter ，如下所示。
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
//        System.out.println("13:45:20=="+time);
//        System.out.println("13=="+hour);
//        System.out.println("45=="+minute);
//        System.out.println("20=="+second);

        //LocalDate 和 LocalTime 都可以通过解析代表它们的字符串创建。使用静态方法 parse ，你可以实现这一目的：
        LocalDate localDate1 = LocalDate.parse("2014-03-18");
        LocalTime time1 = LocalTime.parse("13:45:20");
//        System.out.println(localDate1);
//        System.out.println(time1);
        //12.1.2 合并日期和时间
        // 这个复合类名叫 LocalDateTime ，是 LocalDate 和 LocalTime 的合体。它同时表示了日期
        // 和时间，但不带有时区信息，你可以直接创建，也可以通过合并日期和时间对象构造，如下所示。

        //代码清单12-4 直接创建 LocalDateTime 对象，或者通过合并日期和时间的方式创建
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(2014, 3, 18, 13, 45, 20);
        LocalDateTime dt3 = LocalDateTime.of(date, time);
        LocalDateTime dt4 = date.atTime(13, 45, 20);
        LocalDateTime dt5 = date.atTime(time);
        LocalDateTime dt6 = time.atDate(date);
        // 2014-03-18T13:45:20
//        System.out.println(dt1);
//        System.out.println(dt2);
//        System.out.println(dt3);
//        System.out.println(dt4);
//        System.out.println(dt5);
//        System.out.println(dt6);

        // 注意，通过它们各自的 atTime 或者 atDate 方法，向 LocalDate 传递一个时间对象，或者向
        // LocalTime 传递一个日期对象的方式，你可以创建一个 LocalDateTime 对象。你也可以使用
        // toLocalDate 或者 toLocalTime 方法，从 LocalDateTime 中提取 LocalDate 或者 LocalTime 组件：
        LocalDate localDate2 = dt1.toLocalDate();
        LocalTime localTime = dt1.toLocalTime();
//        System.out.println("2014-03-18>>>>>"+localDate2);
//        System.out.println("13:45:20>>>>"+localTime);

        //12.1.3 机器的日期和时间格式

        // 你可以通过向静态工厂方法 ofEpochSecond 传递一个代表秒数的值创建一个该类的实例。静
        // 态工厂方法 ofEpochSecond 还有一个增强的重载版本，它接收第二个以纳秒为单位的参数值，对
        // 传入作为秒数的参数进行调整。重载的版本会调整纳秒参数，确保保存的纳秒分片在0到999 999
        // 999之间。这意味着下面这些对 ofEpochSecond 工厂方法的调用会返回几乎同样的 Instant 对象：
        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        //2秒 之 后 再 加上100万纳秒（1秒）
        Instant.ofEpochSecond(2, 1_000_000_000);
        //4秒之前的100万纳秒（1秒）
        Instant.ofEpochSecond(4, -1_000_000_000);

        // 正如你已经在 LocalDate 及其他为便于阅读而设计的日期时间类中所看到的那样，
        // Instant 类也支持静态工厂方法 now ，它能够帮你获取当前时刻的时间戳。我们想要特别强调一
        // 点， Instant 的设计初衷是为了便于机器使用。它包含的是由秒及纳秒所构成的数字。所以，它
        // 无法处理那些我们非常容易理解的时间单位。比如下面这段语句：
//        int day2 = Instant.now().get(ChronoField.DAY_OF_MONTH);
//        它会抛出下面这样的异常：
//        java.time.temporal.UnsupportedTemporalTypeException: Unsupported field:DayOfMonth

        //12.1.4 定义 Duration 或 Period

        // 目前为止，你看到的所有类都实现了 Temporal 接口， Temporal 接口定义了如何读取和操纵
        // 为时间建模的对象的值。之前的介绍中，我们已经了解了创建 Temporal 实例的几种方法。很自
        // 然地你会想到，我们需要创建两个 Temporal 对象之间的 duration 。 Duration 类的静态工厂方
        // 法 between 就是为这个目的而设计的。你可以创建两个 LocalTimes 对象、两个 LocalDateTimes
        // 对象，或者两个 Instant 对象之间的 duration ，如下所示：
        LocalTime time2 = LocalTime.parse("13:45:20");
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.now();
        Instant instant1 = Instant.now();
        Instant instant2 = Instant.now();

        Duration d5 = Duration.between(time1, time2);
        Duration d6 = Duration.between(dateTime1, dateTime2);
        Duration d7 = Duration.between(instant1, instant2);
//        System.out.println(d5);
//        System.out.println(d6);
//        System.out.println(d7);
        //由于 LocalDateTime 和 Instant 是为不同的目的而设计的，一个是为了便于人阅读使用，
        //另一个是为了便于机器处理，所以你不能将二者混用。如果你试图在这两类对象之间创建
        //duration ，会触发一个 DateTimeException 异常。此外，由于 Duration 类主要用于以秒和纳
        //秒衡量时间的长短，你不能仅向 between 方法传递一个 LocalDate 对象做参数。

//        如果你需要以年、月或者日的方式对多个时间单位建模，可以使用 Period 类。使用该类的
//        工厂方法 between ，你可以使用得到两个 LocalDate 之间的时长，如下所示：
        Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));

        //代码清单12-5 创建 Duration 和 Period 对象
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays3 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

        //12.2 操纵、解析和格式化日期
        //  如果你已经有一个 LocalDate 对象，想要创建它的一个修改版，最直接也最简单的方法是使
        //  用 withAttribute 方法。 withAttribute 方法会创建对象的一个副本，并按照需要修改它的属
        //  性。注意，下面的这段代码中所有的方法都返回一个修改了属性的对象。它们都不会修改原来的对象！
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println("2014-03-18>>>>>" + date1);
        System.out.println("2011-03-18>>>>>" + date2);
        System.out.println("2011-03-25>>>>>" + date3);
        System.out.println("2011-09-25>>>>>" + date4);
        // 采用更通用的 with 方法能达到同样的目的，它接受的第一个参数是一个 TemporalField 对
        // 象，格式类似代码清单12-6的最后一行。最后这一行中使用的 with 方法和代码清单12-2中的 get
        // 方法有些类似。它们都声明于 Temporal 接口，所有的日期和时间API类都实现这两个方法，它
        // 们定义了单点的时间，比如 LocalDate 、 LocalTime 、 LocalDateTime 以及 Instant 。更确切
        // 地说，使用 get 和 with 方法，我们可以将 Temporal 对象值的读取和修改区分开。如果 Temporal
        // 对象不支持请求访问的字段，它会抛出一个 UnsupportedTemporalTypeException 异常，比
        // 如试图访问 Instant 对象的 ChronoField.MONTH_OF_YEAR 字段，或者 LocalDate 对象的
        // ChronoField.NANO_OF_SECOND 字段时都会抛出这样的异常。
        // 它甚至能以声明的方式操纵 LocalDate 对象。比如，你可以像下面这段代码那样加上或者减去一段时间。

        //代码清单12-7 以相对方式修改 LocalDate 对象的属性
        LocalDate date11 = LocalDate.of(2014, 3, 18);
        LocalDate date22 = date11.plusWeeks(1);
        //减少三年
        LocalDate date33 = date22.minusYears(3);
        LocalDate date44 = date33.plus(6, ChronoUnit.MONTHS);

        LocalDate date55 = date22.plusYears(3);

        System.out.println("2014-03-18>>>" + date11);
        System.out.println("2014-03-25>>>" + date22);
        System.out.println("2011-03-25>>>" + date33);
        System.out.println("2011-09-25>>>" + date44);
        System.out.println("2017-03-25>>>" + date55);
        // 与我们刚才介绍的 get 和 with 方法类似，代码清单12-7中最后一行使用的 plus 方法也是通用
        // 方法，它和 minus 方法都声明于 Temporal 接口中。通过这些方法，对 TemporalUnit 对象加上
        // 或者减去一个数字，我们能非常方便地将 Temporal 对象前溯或者回滚至某个时间段，通过
        // ChronoUnit 枚举我们可以非常方便地实现 TemporalUnit 接口。
        // 大概你已经猜到，像 LocalDate 、 LocalTime 、 LocalDateTime 以及 Instant 这样表示时
        // 间点的日期-时间类提供了大量通用的方法，表12-2对这些通用的方法进行了总结。


//        测验12.1 操纵 LocalDate 对象
//        经过下面这些操作， date99 变量的值是什么？
//        LocalDate date99 = LocalDate.of(2014, 3, 18);       //2014-03-18
//        date99 = date99.with(ChronoField.MONTH_OF_YEAR, 9);   //2014-09-18
//        date99 = date99.plusYears(2).minusDays(10);           //2016-09-28
//        date99.withYear(2011);
//        答案： 2016-09-08 。
//        正如我们刚才看到的，你可以通过绝对的方式，也能以相对的方式操纵日期。你甚至还可
//        以在一个语句中连接多个操作，因为每个动作都会创建一个新的 LocalDate 对象，后续的方
//        法调用可以操纵前一方法创建的对象。这段代码的最后一句不会产生任何我们能看到的效果，
//        因为它像前面的那些操作一样，会创建一个新的 LocalDate 实例，不过我们并没有将这个新
//        创建的值赋给任何的变量。

        //12.2.1 使用 TemporalAdjuster

        // 截至目前，你所看到的所有日期操作都是相对比较直接的。有的时候，你需要进行一些更加
        // 复杂的操作，比如，将日期调整到下个周日、下个工作日，或者是本月的最后一天。这时，你可
        // 以使用重载版本的 with 方法，向其传递一个提供了更多定制化选择的 TemporalAdjuster 对象，
        // 更加灵活地处理日期。对于最常见的用例，日期和时间API已经提供了大量预定义的
        // TemporalAdjuster 。你可以通过 TemporalAdjuster 类的静态工厂方法访问它们，如下所示。

        //代码清单12-8 使用预定义的 TemporalAdjuster

        LocalDate date111 = LocalDate.of(2014, 3, 18); //2014-03-18
        //获取下一个周日
        LocalDate date222 = date111.with(nextOrSame(DayOfWeek.SUNDAY));       //2014-03-23 //周日
        //获取三月的最后一天
        LocalDate date333 = date222.with(lastDayOfMonth());                   //2014-03-31 //三月的最后一天

        LocalDate localDate3 = LocalDate.of(2019, 7, 18);
        // 2019-07-01 //七月的第一天
        LocalDate localDate4 = localDate3.with(firstDayOfMonth());
        System.out.println(date333);
        System.out.println(localDate4);

        // 正如我们看到的，使用 TemporalAdjuster 我们可以进行更加复杂的日期操作，而且这些方
        // 法的名称也非常直观，方法名基本就是问题陈述。此外，即使你没有找到符合你要求的预定义的
        // TemporalAdjuster ，创建你自己的 TemporalAdjuster 也并非难事。实际上， TemporalAdjuster
        // 接口只声明了单一的一个方法（这使得它成为了一个函数式接口），定义如下。

        //代码清单12-9  TemporalAdjuster 接口
//        @FunctionalInterface
//        public interface TemporalAdjuster {
//            Temporal adjustInto(Temporal temporal);
//        }

//        这意味着 TemporalAdjuster 接口的实现需要定义如何将一个 Temporal 对象转换为另一
//        个 Temporal 对象。你可以把它看成一个 UnaryOperator<Temporal> 。花几分钟时间完成测验
//        12.2，练习一下我们到目前为止所学习的东西，请实现你自己的 TemporalAdjuster 。


//        测验12.2 实现一个定制的 TemporalAdjuster
//        请设计一个 NextWorkingDay 类，该类实现了 TemporalAdjuster 接口，能够计算明天
//        的日期，同时过滤掉周六和周日这些节假日。格式如下所示：
//        date99 = date99.with(new NextWorkingDay());
//        如果当天的星期介于周一至周五之间，日期向后移动一天；如果当天是周六或者周日，则
//        返回下一个周一。
//        答案：下面是参考的 NextWorkingDay 类的实现。
//        public class NextWorkingDay implements TemporalAdjuster {
//            @Override
//            public Temporal adjustInto(Temporal temporal) {
//                DayOfWeek dow =DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
//                int dayToAdd = 1;
//                if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
//                else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
//                return temporal.plus(dayToAdd, ChronoUnit.DAYS);
//            }
//        }
//        该 TemporalAdjuster 通常情况下将日期往后顺延一天，如果当天是周六或者周日，则
//        依据情况分别将日期顺延3天或者2天。注意，由于 TemporalAdjuster 是一个函数式接口，
//        你只能以Lambda表达式的方式向该 adjuster 接口传递行为：
//        date99 = date99.with(temporal -> {
//            DayOfWeek dow =
//                    DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
//            int dayToAdd = 1;
//            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
//            else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
//            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
//        });
//        你大概会希望在你代码的多个地方使用同样的方式去操作日期，为了达到这一目的，我们
//        建议你像我们的示例那样将它的逻辑封装到一个类中。对于你经常使用的操作，都应该采用类
//        似的方式，进行封装。最终，你会创建自己的类库，让你和你的团队能轻松地实现代码复用。
//        如果你想要使用Lambda表达式定义 TemporalAdjuster 对象，推荐使用 TemporalAdjusters
//        类的静态工厂方法 ofDateAdjuster ，它接受一个 UnaryOperator<LocalDate> 类型的参数，代码如下：
//        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
//                temporal -> {
//                    DayOfWeek dow =
//                            DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
//                    int dayToAdd = 1;
//                    if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
//                    if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
//                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
//                });
//        date99 = date99.with(nextWorkingDay);

        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateTime3 = dateTime.with(temporal -> {
            DayOfWeek dow2 =DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow2 == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dow2 == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(dateTime3);

       TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow3 =
                            DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if (dow3 == DayOfWeek.FRIDAY) {
                        dayToAdd = 3;
                    }
                    if (dow3 == DayOfWeek.SATURDAY) {
                        dayToAdd = 2;
                    }
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                });
        LocalDate localDate5 = date.with(nextWorkingDay);


        //12.2.2 打印输出及解析日期-时间对象
        //  处理日期和时间对象时，格式化以及解析日期时间对象是另一个非常重要的功能。新的
        //  java.time.format 包就是特别为这个目的而设计的。这个包中，最重要的类是 DateTime-
        //  Formatter 。创建格式器最简单的方法是通过它的静态工厂方法以及常量。像 BASIC_ISO_DATE
        //  和 ISO_LOCAL_DATE 这 样 的 常 量 是 DateTimeFormatter 类 的 预 定 义 实 例 。 所 有 的
        //  DateTimeFormatter 实例都能用于以一定的格式创建代表特定日期或时间的字符串。比如，下
        //  面的这个例子中，我们使用了两个不同的格式器生成了字符串：
        LocalDate date10 = LocalDate.of(2014, 3, 18);
        String s1 = date10.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date10.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("20140318>>>>>>>>>"+s1);
        System.out.println("2014-03-18>>>>>>>>>"+s2);

        //你也可以通过解析代表日期或时间的字符串重新创建该日期对象。所有的日期和时间API
        //都提供了表示时间点或者时间段的工厂方法，你可以使用工厂方法 parse 达到重创该日期对象的目的：
        LocalDate date23 = LocalDate.parse("20140318",DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date24 = LocalDate.parse("2014-03-18",DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date23);
        System.out.println(date24);
        // 和老的 java.util.DateFormat 相比较，所有的 DateTimeFormatter 实例都是线程安全
        // 的。所以，你能够以单例模式创建格式器实例，就像 DateTimeFormatter 所定义的那些常量，
        // 并能在多个线程间共享这些实例。 DateTimeFormatter 类还支持一个静态工厂方法，它可以按
        // 照某个特定的模式创建格式器，代码清单如下。

        //代码清单12-10 按照某个模式创建 DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate6 = LocalDate.of(2014, 3, 18);
        String formattedDate = localDate6.format(formatter);

        System.out.println("formattedDate>>>>>"+formattedDate);
        LocalDate date25 = LocalDate.parse(formattedDate, formatter);
//        LocalDate date26 = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(date25);
//        System.out.println(date26);

        // 这段代码中， LocalDate 的 formate 方法使用指定的模式生成了一个代表该日期的字符串。
        // 紧接着，静态的 parse 方法使用同样的格式器解析了刚才生成的字符串，并重建了该日期对象。
        // ofPattern 方法也提供了一个重载的版本，使用它你可以创建某个 Locale 的格式器，代码清单
        // 如下所示。

        //代码清单12-11 创建一个本地化的 DateTimeFormatter
        DateTimeFormatter italianFormatter =DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.CHINA);
        LocalDate date34 = LocalDate.of(2014, 3, 18);
        String formattedDate34 = date.format(italianFormatter); // 18. marzo 2014
        LocalDate date35 = LocalDate.parse(formattedDate34, italianFormatter);

        System.out.println("formattedDate34>>>>>>>>"+formattedDate34);
        System.out.println("date35"+date35);

        // 最后，如果你还需要更加细粒度的控制， DateTimeFormatterBuilder 类还提供了更复杂
        // 的格式器，你可以选择恰当的方法，一步一步地构造自己的格式器。另外，它还提供了非常强大
        // 的解析功能，比如区分大小写的解析、柔性解析（允许解析器使用启发式的机制去解析输入，不
        // 精确地匹配指定的模式）、填充，以及在格式器中指定可选节。比如，你可以通过
        // DateTimeFormatterBuilder 自己编程实现我们在代码清单12-11中使用的 italianFormatter ，代码清单如下。

        //代码清单12-12 构造一个 DateTimeFormatter
        DateTimeFormatter italianFormatter222 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        System.out.println("italianFormatter222>>>>>>>>>"+italianFormatter222);

        //12.3 处理不同的时区和历法
        // 之前你看到的日期和时间的种类都不包含时区信息。时区的处理是新版日期和时间API新增
        // 加的重要功能，使用新版日期和时间API时区的处理被极大地简化了。新的 java.time.ZoneId
        // 类是老版 java.util.TimeZone 的替代品。它的设计目标就是要让你无需为时区处理的复杂和
        // 繁琐而操心，比如处理日光时（Daylight Saving Time，DST）这种问题。跟其他日期和时间类一
        // 样， ZoneId 类也是无法修改的。

        // 时区是按照一定的规则将区域划分成的标准时间相同的区间。在 ZoneRules 这个类中包含了
        // 40个这样的实例。你可以简单地通过调用 ZoneId 的 getRules() 得到指定时区的规则。每个特定
        // 的 ZoneId 对象都由一个地区ID标识，比如：
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        // 地区ID都为“{区域}/{城市}”的格式，这些地区集合的设定都由英特网编号分配机构（IANA）
        // 的时区数据库提供。你可以通过Java 8的新方法 toZoneId 将一个老的时区对象转换为 ZoneId ：
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        // 一旦得到一个 ZoneId 对象，你就可以将它与 LocalDate 、 LocalDateTime 或者是 Instant
        // 对象整合起来，构造为一个 ZonedDateTime 实例，它代表了相对于指定时区的时间点，代码清单如下所示。

        //代码清单12-13 为时间点添加时区信息
        System.out.println(zoneId);
        LocalDate date56 = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date56.atStartOfDay(romeZone);

        LocalDateTime dateTime57 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime57.atZone(romeZone);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
        //图12-1对 ZonedDateTime 的组成部分进行了说明，相信能够帮助你理解 LocaleDate 、LocalTime 、 LocalDateTime 以及 ZoneId 之间的差异。

        //通过 ZoneId ，你还可以将 LocalDateTime 转换为 Instant ：
        LocalDateTime dateTime58 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        Instant instantFromDateTime = dateTime58.toInstant((ZoneOffset) romeZone);
        //你也可以通过反向的方式得到 LocalDateTime 对象：
        Instant instant59 = Instant.now();
        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant59, romeZone);

        //12.3.1 利用和 UTC/格林尼治时间的固定偏差计算时区

        //12.3.2 使用别的日历系统

        // ISO-8601日历系统是世界文明日历系统的事实标准。但是，Java 8中另外还提供了4种其他的
        // 日历系统。这些日历系统中的每一个都有一个对应的日志类，分别是 ThaiBuddhistDate 、
        // MinguoDate 、 JapaneseDate 以及 HijrahDate 。所有这些类以及 LocalDate 都实现了
        // ChronoLocalDate 接口，能够对公历的日期进行建模。利用 LocalDate 对象，你可以创建这些
        // 类的实例。更通用地说，使用它们提供的静态工厂方法，你可以创建任何一个 Temporal 对象的
        // 实例，如下所示：
        LocalDate date61 = LocalDate.of(2014, Month.MARCH, 18);
        JapaneseDate japaneseDate = JapaneseDate.from(date);

        // 12.4 小结
        //  这一章中，你应该掌握下面这些内容。
        //    Java 8之前老版的 java.util.Date 类以及其他用于建模日期时间的类有很多不一致及
        //  设计上的缺陷，包括易变性以及糟糕的偏移值、默认值和命名。
        //    新版的日期和时间API中，日期-时间对象是不可变的。
        //    新的API提供了两种不同的时间表示方式，有效地区分了运行时人和机器的不同需求。
        //    你可以用绝对或者相对的方式操纵日期和时间，操作的结果总是返回一个新的实例，老
        //  的日期时间对象不会发生变化。
        //    TemporalAdjuster 让你能够用更精细的方式操纵日期，不再局限于一次只能改变它的
        //  一个值，并且你还可按照需求定义自己的日期转换器。
        //    你现在可以按照特定的格式需求，定义自己的格式器，打印输出或者解析日期时间对象。
        //  这些格式器可以通过模板创建，也可以自己编程创建，并且它们都是线程安全的。
        //    你可以用相对于某个地区/位置的方式，或者以与UTC/格林尼治时间的绝对偏差的方式表
        //  示时区，并将其应用到日期时间对象上，对其进行本地化。
        //    你现在可以使用不同于ISO-8601标准系统的其他日历系统了。

        //在Java 8中将Date转换为LocalDateTime
        Date date99 = new Date();
        Instant instant99 = date99.toInstant();
        ZoneId zoneId99 = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant99.atZone(zoneId99).toLocalDateTime();
        System.out.println("Date = " + date99);
        System.out.println("LocalDateTime = " + localDateTime);

        //在Java 8中将LocalDateTime转换为Date
        ZoneId zoneId88 = ZoneId.systemDefault();
        LocalDateTime localDateTime88 = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime88.atZone(zoneId88);
        Date date88 = Date.from(zdt.toInstant());
        System.out.println("LocalDateTime = " + localDateTime88);
        System.out.println("Date = " + date88);
    }
































}
