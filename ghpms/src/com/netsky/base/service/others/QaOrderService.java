
package com.netsky.base.service.others;

/**
 * 问与答排序
 * @author wangflan
 * @create 2010-08-09
 */

public interface QaOrderService{
	public void saveOrder(String order,Long save_id,Long save_ord);
	public void delOrder();
}
