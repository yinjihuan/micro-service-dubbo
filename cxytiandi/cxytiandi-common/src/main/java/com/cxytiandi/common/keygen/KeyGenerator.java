package com.cxytiandi.common.keygen;
/**
 * 主键生成器
 *
 */
public interface KeyGenerator {
	/**
     * 生成主键.
     * @return 自动生成的主键
     */
    Number generateKey();
}
