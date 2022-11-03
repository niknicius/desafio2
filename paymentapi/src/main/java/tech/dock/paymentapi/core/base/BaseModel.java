package tech.dock.paymentapi.core.base;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseModel<K extends Serializable> {

}
