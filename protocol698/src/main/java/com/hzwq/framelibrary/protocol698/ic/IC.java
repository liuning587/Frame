package com.hzwq.framelibrary.protocol698.ic;

import com.hzwq.framelibrary.protocol698.data.OI;
import com.hzwq.framelibrary.protocol698.data.number.Integer;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by qinling on 2019/4/25 10:19
 * Description: 接口类 通用
 */
public abstract class IC {
    private ArrayDeque<String> objQueue = new ArrayDeque();
    public final String oiStr;
   // private StringBuilder stringBuilder = new StringBuilder();
    public IC() {
        this.oiStr = logicName();
    }

    // 提供给子类，让子类来赋值
    protected abstract String logicName();

    // 复位
    public void reset(){

    };

    // 执行
    public void execute(){

    }

   /* public String toHexString(){

    }*/
}
