package tech.dock.paymentapi.core.base;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Reusable model class
 *
 * @author Vinnicius Santos - vinnicius.santos@dcx.ufpb.br
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseModel<K extends Serializable> {

}
