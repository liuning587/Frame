package com.hzwq.framelibrary.protocol698;

import android.support.annotation.Nullable;

import com.hzwq.framelibrary.protocol698.apdu.client.ClientAPDU;
import com.hzwq.framelibrary.protocol698.apdu.client.set.SetRequestNormalList;
import com.hzwq.framelibrary.protocol698.apdu.link.LinkRequest;
import com.hzwq.framelibrary.protocol698.apdu.link.LinkResponse;
import com.hzwq.framelibrary.protocol698.data.DateTime;
import com.hzwq.framelibrary.protocol698.data.DateTimeS;
import com.hzwq.framelibrary.protocol698.data.MS;
import com.hzwq.framelibrary.protocol698.data.RCSD;
import com.hzwq.framelibrary.protocol698.data.RSD;
import com.hzwq.framelibrary.protocol698.data.datahelpers.SetNormal;
import com.hzwq.framelibrary.protocol698.data.string.OctetString;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


/**
 * Created by qinling on 2019/4/18 15:17
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        //  test();
        System.out.println(ClientAPDU.getRequestNormal("40010200").toHexString());
        System.out.println(ClientAPDU.getRequestNormalList(2, "20000200", "20010200").toHexString());
        System.out.println(ClientAPDU.getRequestRecord(3, "50040200",
                RSD.select1("20210200", new DateTimeS(2016, 1, 20)),
                RCSD.newInstance().addOAD("20210200", "00100200")
        ).toHexString());

        System.out.println(ClientAPDU.getRequestRecord(4, "60120300",
                RSD.select5(new DateTimeS(2016, 1, 20),
                        MS.userAddress("040000000121", "040000000122", "040000000123", "040000000124", "040000000125"))
                , RCSD.newInstance().addOAD("40010200", "60400200", "60410200", "60420200").addROAD("50040200", "00100200", "00200200")
        ).toHexString());
        System.out.println(ClientAPDU.getRequestNxet(9, 1).toHexString());

        System.out.println(ClientAPDU.setRequestNormal(2, "40000200", new DateTimeS(2016, 1, 20, 16, 27, 11)).toHexString());
        System.out.println(ClientAPDU.setRequestNormalList(3, new SetNormal("40010200", new OctetString("000000000001"))).toHexString());
        System.out.println(ClientAPDU.setRequestNormalList(
                new SetRequestNormalList.Builder()
                        .setPiid(3)
                        .addNormal(new SetNormal("40010200", new OctetString("000000000001")))
                        .addNormal(new SetNormal("40000200", new DateTimeS(2016, 1, 20, 16, 27, 11))).build()).toHexString());

        System.out.println(ClientAPDU.getRequestNxet(9, 1).toHexString());
        //   System.out.println((A) new B());
        // System.out.println(getNum());
        getNum();
    }

    private static void getNum() {

        Field[] fields = A.class.getDeclaredFields();
        System.out.println(fields.length);
        try {
            for (int i = 0; i < fields.length; i++) {
                System.out.println("index: "+i+"  "+fields[i].getInt(new A()));
            }
            for (int i = 0; i < fields.length; i++) {
                System.out.println("index: "+i+"  "+fields[i].getAnnotation(SerializedName.class).value());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
       /* for (Annotation annotation : annotations) {

            SerializedName serializedName = (SerializedName) annotation;
            System.out.println(serializedName.value());
        }*/


    }

    @TestA(name = "type", gid = Long.class) //类成员注解
    public static class A {
        // @Nullable
        @SerializedName(2)
        @TestA(name = "type", gid = Long.class)
        int anInt = 1;

        @SerializedName(4)
        int b = 2;

        @SerializedName(4)
        int c = 2;
    }

    public static class B extends A {
        @SerializedName(2)
        int anInt = 2;
    }

    private static void test() {
        // 050303
        // 50040200
        // 01
        // 20210200
        // 1C07E00114000000
        // 02
        // 00
        // 20210200
        // 00
        // 00100200
        // 00

        //
        //
        // test698();
        //厂商代码（size(4)）+ 软件版本号（size(4)）+软件版本日期（size(6)）+硬件版本号（size(4)）+硬件版本日期（size(6)）+厂家扩展信息（size(8)）
        //  TOPS 0102 160731 0102 160731

        //   String visiableString = "54 4F 50 53 30 31 30 32 31 36 30 37 33 31 30 31 30 32 31 36 30 37 33 31 00 00 00 00 00 00 00 00";
        //  String visiableString = "54 4F 50 53 ";
        //   visiableString = visiableString.replaceAll(" ","");
        //  System.out.println( ClientAPDU.getRequest().getRequestRecord(new PIID(1),new OAD(""),new GetRecord(new OAD("20000200"),new RSD(5))).toHexString());
        //  System.out.println(NumberConvert.hexStrToAsciiString(visiableString));
        // System.out.println(  String.format(Locale.CHINA,"%x%x%x%x",1,44,3,4));
        //  System.out.println("0201" + new ROAD("50040200", "00100200", "00200200").toHexString());
        //  System.out.println(RCSD.newInstance().addROAD("50040200", "00100200", "00200200").toHexString());
       /* System.out.println(
                ClientAPDU.getRequest().
                        getRequestRecord(4,
                                "60120300",
                                RSD.select5(new DateTimeS(2016,1,20), MS.userAddress("040000000121","040000000122","040000000123","040000000124","040000000125")),
                             //   new RCSD(CSD.createOAD("40010200","60400200","60410200","60420200"),CSD.createROAD())
                                RCSD.newInstance().addOAD("40010200","60400200")
                               .addOAD("60410200","60420200")
                                        .addROAD("50040200","00100200","00200200")
        ).toHexString());

        System.out.println(
                ClientAPDU.getRequest().
                        getRequestRecord(3,
                                "50040200",
                                RSD.select1("20210200",new DateTimeS(2016,1,20)),
                                RCSD.newInstance().addOAD("20210200","00100200")
                               *//* RSD.select5(new DateTimeS(2016,1,20), MS.userAddress("040000000121","040000000122","040000000123","040000000124","040000000125")),
                                //   new RCSD(CSD.createOAD("40010200","60400200","60410200","60420200"),CSD.createROAD())
                                RCSD.newInstance()
                                        .addOAD("40010200")
                                        .addOAD("60400200")
                                        .addOAD("60410200")
                                        .addOAD("60420200")
                                        .addROAD("50040200","00100200","00200200")
                       *//* ).toHexString());
        System.out.println(
                ClientAPDU.getRequest().
                        getRequestNext(9,1
                                 ).toHexString());
        System.out.println(
                ClientAPDU.actionRequest().actionRequest(5,"00100100", new Integer(0))
                        .toHexString());

        System.out.println(
                ClientAPDU.actionRequest()
                        .actionThenGetRequestNormalList(new PIID(7),
                                SetThenGet
                                )
                        .toHexString());*/

        //  System.out.println((new ClientAPDU((new GetRequestNormal.Builder().setPiid(9).setOad("12345678").build())).setTimeOut(2,TimeUnit.DAY)).toHexString());
        //  System.out.println((new ClientAPDU((new GetRequestMD5().newBuilder().setPiid(9).setOad("12345678").build())).setTimeOut(2, TimeUnit.DAY)).toHexString());
        //  System.out.println((new ClientAPDU((new GetRequestMD5.Builder().setPiid(9).setOad("12345678").build()))).toHexString());
        System.out.println(new LinkRequest.Builder().build().toHexString());
        System.out.println(new LinkRequest().newBuilder().build().toHexString());
        System.out.println(new LinkResponse.Builder().build().toHexString());
        System.out.println(new LinkResponse().newBuilder().build().toHexString());
        // System.out.println((new ClientAPDU((new GetRequestMD5(new ).setPiid(9).setOad("12345678").build()))).toHexString());
        // System.out.println(ClientAPDU.getRequestMD5( new GetRequestMD5().newBuilder().setPiid(9).setOad("12345678").build()).setTimeOut(0, TimeUnit.DAY).toHexString());
        //  System.out.println(ClientAPDU.getRequestNormal( new GetRequestNormal.Builder().setPiid(9).setOad("12345678").build()).setTimeOut(0, TimeUnit.DAY).toHexString());
        //   System.out.println(new ReportResponseRecordList.Builder().setOads("12345678").build().classify());
    }

    private static void test698() {
        Frame698.Parser frame698_0 = new Frame698().parse("683000010507091905162010000081008007E0051304080500008907E0051304080501025F07E005130408050202DA000016");
        Frame698.Parser frame698 = new Frame698().parse("fefefefe68c600e3050100000000000083ae000090008202f585030350040200020020210200000020020001151c07e302050000000105060000000006000000000600000000060000000006000000001c07e302060000000105060000000006000000000600000000060000000006000000001c07e302070000000105060000000006000000000600000000060000000006000000001c07e302080000000105060000000006000000000600000000060000000006000000001c07e302090000000105060000000006acc016");
        Frame698.Parser frame698_1 = new Frame698().parse("681100630501000000000000E9E40080D67816");
        Frame698.Parser frame698_2 = new Frame698().parse("fefefefe68c600e3050100000000000083ae01c0000000000600000000060000000006000000001c07e3020a0000000105060000000006000000000600000000060000000006000000001c07e3020b0000000105060000000006000000000600000000060000000006000000001c07e3020c0000000105060000000006000000000600000000060000000006000000001c07e3020d0000000105060000000006000000000600000000060000000006000000001c07e3020e0000000105060000000006000000000600dd3116");
        // Frame698 frame6981 = new Frame698().newBuilder().setLinkDataStr()
        DateTime dateTime = new DateTime("07E00513040805000089");
        System.out.println(dateTime.format() + "\n");
        //dateTime = new Time(-1,-1,3);

        System.out.println(frame698_0.toFormatString());
        System.out.println(frame698.toFormatString());
        System.out.println("------------" + frame698_0.reBuild());
        System.out.println(frame698_1.toFormatString());
        System.out.println("------------");
        System.out.println(frame698_2.toFormatString());
    }
}
