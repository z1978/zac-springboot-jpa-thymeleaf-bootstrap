package com.zac.spring.values;

//↓ここから下の部分はそのままコピーしてください
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//↑ここから上の部分はそのままコピーしてください
//独自に記述する必要があるのは enum の後の列挙名(ここでは TicketStatusValues) と
public enum TicketStatusValues implements Values {

 // 定数部分の２ヶ所です
 WAITING("1", "未着手")
 , IN_PROGRESS("2", "作業中")
 , COMPLETE("3", "完了");

 // ↓ここから下の部分はそのままコピーしてください
 private final String value;
 private final String text;

}