package com.poka.app.enumtype;

public enum StateType {

	 NONE(0),SENDED(1),ERROR(2);
	 
	 private int nCode;

     // 构造函数，枚举类型只能为私有

     private StateType(int _nCode) {

         this.nCode = _nCode;

     }
     
     @Override
     public String toString() {

         return String.valueOf(this.nCode);

     }
}
