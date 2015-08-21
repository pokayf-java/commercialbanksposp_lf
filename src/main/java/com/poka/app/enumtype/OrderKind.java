package com.poka.app.enumtype;

public enum OrderKind {

	NO(0), OUT(1), IN(2);
	private int nCode;

    // 构造函数，枚举类型只能为私有

    private OrderKind(int _nCode) {
        this.nCode = _nCode;

    }
    
    @Override
    public String toString() {

        return String.valueOf(this.nCode);

    }
}
