package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Te07_qa"
 */

public class Te07_qa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 958918964510726016L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te07_num"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	 /**
 * 问题
 */
 private String question;

 /**
 * @hibernate.property column="question"
 * @return Returns the question.
 */
 public String getQuestion() {
    return question;
 }

 public void setQuestion(String question) {
    this.question = question;
  }

 /**
 * 回答
 */
 private String answer;

 /**
 * @hibernate.property column="answer"
 * @return Returns the answer.
 */
 public String getAnswer() {
    return answer;
 }

 public void setAnswer(String answer) {
    this.answer = answer;
  }

 /**
 * 排序号
 */
 private Long ord;

 /**
 * @hibernate.property column="ord"
 * @return Returns the ord.
 */
 public Long getOrd() {
    return ord;
 }

 public void setOrd(Long ord) {
    this.ord = ord;
  }

 /**
 * 创建日期
 */
 private Date cjrq;

 /**
 * @hibernate.property column="cjrq"
 * @return Returns the cjrq.
 */
 public Date getCjrq() {
    return cjrq;
 }

 public void setCjrq(Date cjrq) {
    this.cjrq = cjrq;
  }

 /**
 * 点击数
 */
 private Long dj;

 /**
 * @hibernate.property column="dj"
 * @return Returns the dj.
 */
 public Long getDj() {
    return dj;
 }

 public void setDj(Long dj) {
    this.dj = dj;
  }


}
