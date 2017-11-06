package com.cxytiandi.common.keygen;

public class KeyGeneratorFactory {
	
	public KeyGeneratorFactory() {
		super();
	}

	/**
     * 创建主键生成器.
     * @param keyGeneratorClass 主键生成器类
     * @return 主键生成器实例
     */
    public static KeyGenerator createKeyGenerator(final Class<? extends KeyGenerator> keyGeneratorClass) {
        try {
            return keyGeneratorClass.newInstance();
        } catch (final InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException(String.format("Class %s should have public privilege and no argument constructor", keyGeneratorClass.getName()));
        }
    }
}
