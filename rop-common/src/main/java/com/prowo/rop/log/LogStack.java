package com.prowo.rop.log;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LogStack {

    public enum LogStackType {
        PROCEED, END
    }

    private Long startTime;

    private Long endTime;

    private String tag;

    private int deep = 1;

    private List<String> texts = new ArrayList<String>();

    private List<LogStack> subLogs;

    private List<Object> seq = new ArrayList<Object>();

    private LogStackType type = LogStackType.PROCEED;

    public LogStack() {
    }

    public LogStack(String tag) {
        this.tag = tag;
    }

    public static LogStack create(String tag) {
        LogStack stack = new LogStack(tag);
        stack.start();
        return stack;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void end() {
        endTime = System.currentTimeMillis();
        type = LogStackType.END;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<LogStack> getSubLogs() {
        return subLogs;
    }

    public void setSubLogs(List<LogStack> subLogs) {
        this.subLogs = subLogs;
    }

    public LogStackType getType() {
        return type;
    }

    public void setType(LogStackType type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public List<Object> getSeq() {
        return seq;
    }

    public void setSeq(List<Object> seq) {
        this.seq = seq;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public Long getMillis() {
        if (endTime == null || startTime == null) {
            return -1L;
        }
        return endTime - startTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[").append(tag).append(']');
        sb.append(" costTime: ").append(getMillis()).append("ms");
        if (CollectionUtils.isEmpty(seq)) {
            return sb.toString();
        }

        sb.append('\n');
        for (int i = 0; i < seq.size(); i++) {
            sb.append(StringUtils.repeat('\t', deep)).append(seq.get(i));
            if (i != seq.size() - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
