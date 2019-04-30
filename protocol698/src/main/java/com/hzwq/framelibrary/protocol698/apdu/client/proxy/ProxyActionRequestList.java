package com.hzwq.framelibrary.protocol698.apdu.client.proxy;

import android.support.annotation.IntRange;

import com.hzwq.framelibrary.protocol698.apdu.APDUBuilder;
import com.hzwq.framelibrary.protocol698.apdu.APDUParser;
import com.hzwq.framelibrary.protocol698.apdu.FormatAble;
import com.hzwq.framelibrary.protocol698.apdu.client.ClientAPDU;
import com.hzwq.framelibrary.protocol698.data.Data;
import com.hzwq.framelibrary.protocol698.data.OAD;
import com.hzwq.framelibrary.protocol698.data.PIID;
import com.hzwq.framelibrary.protocol698.data.TSA;
import com.hzwq.framelibrary.protocol698.data.datahelpers.ProxyAction;
import com.hzwq.framelibrary.protocol698.data.datahelpers.ActionNormal;
import com.hzwq.framelibrary.protocol698.data.number.LongUnsigned;

import java.util.ArrayList;


/**
 * Created by qinling on 2019/4/29 9:00
 * Description:
 */
public class ProxyActionRequestList implements ClientAPDU.ProxyRequest, FormatAble {
    @Override
    public int type() {
        return PROXY_ACTION_REQUEST_LIST;
    }

    private final PIID piid;
    /**
     * 整个代理请求的超时时间   long-unsigned  一般不能为0 ,单位为S
     */
    private final LongUnsigned totalProxyTimeOut;
    private final ArrayList<ProxyAction> proxyActions;

    public ProxyActionRequestList() {
        this(new Builder());
    }


    public Builder newBuilder() {
        return new Builder(this);
    }


    private ProxyActionRequestList(Builder builder) {
        this.piid = builder.piid;
        this.totalProxyTimeOut = builder.totalProxyTimeOut;
        this.proxyActions = builder.proxyActions;

    }

    @Override
    public String toHexString() {
        return newBuilder().toHexString();
    }

    @Override
    public String format(String apduStr) {
        return new Parser(apduStr).toFormatString();
    }

    public static final class Builder extends APDUBuilder<ProxyActionRequestList> {
        // 默认三分钟超时时间
        private static final int DEFAULT_OUT_TIME = 180;
        private static final int MIN_OUT_TIME = 10;
        private PIID piid;
        private LongUnsigned totalProxyTimeOut;
        private ArrayList<ProxyAction> proxyActions;

        public Builder() {
            this.piid = new PIID();
            this.totalProxyTimeOut = new LongUnsigned(DEFAULT_OUT_TIME);
            this.proxyActions = new ArrayList<>();
        }

        public Builder(ProxyActionRequestList getRequestNormal) {
            this.piid = getRequestNormal.piid;
            this.totalProxyTimeOut = getRequestNormal.totalProxyTimeOut;
            this.proxyActions = getRequestNormal.proxyActions;
        }

        public Builder setPiid(int piid) {
            return setPiid(new PIID(piid));
        }

        public Builder setPiid(PIID piid) {
            this.piid = piid;
            return this;
        }

        // 所有的超时时间 不能为0
        public Builder setTotalProxyTimeOut(@IntRange(from = 1, to = LongUnsigned.MAX_VALUE) int totalProxyOutTime) {
            this.totalProxyTimeOut = new LongUnsigned(totalProxyOutTime);
            return this;
        }

        public Builder addProxyActionNormals(String tsa, ActionNormal... oads) {
            return addProxyActionNormals(tsa, 0, oads);
        }

        /**
         * @param tsa     一个目标服务器地址
         * @param timeOut 代理一个服务器的超时时间 单位：秒，0表示对于某个服务器的代理超时时间由服务器自行控制。
         * @param setNormals   若干个对象属性描述符及其数据
         * @return
         */
        private Builder addProxyActionNormals(String tsa, @IntRange(from = 0, to = LongUnsigned.MAX_VALUE) int timeOut, ActionNormal... setNormals) {
            /*ActionNormal[] setNormalArr = new ActionNormal[setNormals.length];
            for (int i = 0; i < setNormals.length; i++) {
                setNormalArr[i] = new ActionNormal(setNormals[i]);
            }*/
            this.proxyActions.add(new ProxyAction(new TSA(tsa), new LongUnsigned(timeOut), setNormals));
            return this;
        }


        @Override
        public ProxyActionRequestList build() {
            return new ProxyActionRequestList(this);
        }

        @Override
        public String toHexString() {
            String proxyGetsHexStr = proxyActions.isEmpty() ? "00" : Data.toString4Array(proxyActions);
            return piid.toHexString()
                    + totalProxyTimeOut.toHexString()
                    + proxyGetsHexStr;
        }
    }


    public static final class Parser extends APDUParser<ProxyActionRequestList> {

        public Parser(String apduStr) {
            super(apduStr);
        }

        @Override
        protected ParseResult checkApduStr(String apduStr) {
            return null;
        }

        @Override
        protected String toFormatString() {
            return null;
        }

        @Override
        protected ProxyActionRequestList reBuild() {
            return null;
        }

        public PIID getPiid() {
            return new PIID(apduStr.substring(0, 2));
        }

        public OAD getOad() {
            return new OAD(apduStr.substring(2, 8));
        }
    }
}