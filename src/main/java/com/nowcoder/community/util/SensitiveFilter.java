package com.nowcoder.community.util;/*
    @author AYU
    */

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component//过滤铭感词功能存在漏洞
public class SensitiveFilter {
    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);
    //敏感字符的替换
    private static final String REPALCEMENT = "***";
    //根节点
    private TireNode rootNode = new TireNode();
    //初始化
    @PostConstruct
    public void init(){
            try {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                String keyword;
                //添加到前缀树
                while (((keyword = bufferedReader.readLine()) != null)) {
                    this.addKeyword(keyword);
                }
            } catch (IOException e) {
                logger.error("加载敏感文件失败" + e.getMessage());
            }
    }
    //将铭感词添加到前缀树
    private void addKeyword(String keyword){
        TireNode tempNode = rootNode;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            TireNode subNode = tempNode.getSubNode(c);
            if (subNode == null) {
                //初始化节点
                subNode = new TireNode();
                tempNode.addSubNode(c, subNode);
            }
            //指向子节点
            tempNode = subNode;
            //设置结束标志
            if (i == keyword.length() - 1) {
                tempNode.setKeywordEnd(true);
            }
        }
    }
    //实现过滤铭感词
    public String filter(String text) {
        if(StringUtils.isBlank(text)){
            return null;
        }
        //指针1前缀树
        TireNode temp = rootNode;
        //指针2开始
        int begin = 0;
        //指针3结束
        int position = 0;
        StringBuilder sb = new StringBuilder();
        while (position < text.length()){
            char c = text.charAt(position);
            //判断是否其他符号
            if (isSymbol(c)) {
                if (temp == rootNode){
                    sb.append(c);
                    begin++;
                }
                //无论符号在中间还是开头，指针向下走一步
                position++;
                continue;
            }
            //检查下级节点
            temp = temp.getSubNode(c);
            if (temp == null) {
                //以bigin开头的字符串不是铭感词
                sb.append(text.charAt(begin));
                position = ++begin;
                temp = rootNode;
            }else if (temp.isKeywordEnd()) {
                //铭感词
                sb.append(REPALCEMENT);
                begin = ++position;
                temp = rootNode;
            }else {
                position++;
            }
        }
        //跳出循环后，将最后的计入结果
        sb.append(text.substring(begin));
        return  sb.toString();
    }
    private boolean isSymbol(char c){
        //  c < 0x2E80 || c > 0x9FFF 东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }
    //前缀树结构
    private class TireNode {
        //关键字结束标志
        private boolean isKeywordEnd;
        //子节点
        private Map<Character, TireNode> subNodes = new HashMap<>();
        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        //添加子节点
        public void addSubNode(Character c, TireNode node) {
            subNodes.put(c, node);
        }

        //获取子节点
        public TireNode getSubNode(Character c) {
            return subNodes.get(c);
        }

    }

}

