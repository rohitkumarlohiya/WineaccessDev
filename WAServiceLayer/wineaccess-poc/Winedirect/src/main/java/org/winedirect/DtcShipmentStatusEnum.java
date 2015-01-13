
package org.winedirect;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DtcShipmentStatusEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DtcShipmentStatusEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NotSet"/>
 *     &lt;enumeration value="Shipped"/>
 *     &lt;enumeration value="InTransit"/>
 *     &lt;enumeration value="Delivered"/>
 *     &lt;enumeration value="Exception"/>
 *     &lt;enumeration value="Returned"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DtcShipmentStatusEnum")
@XmlEnum
public enum DtcShipmentStatusEnum {

    @XmlEnumValue("NotSet")
    NOT_SET("NotSet"),
    @XmlEnumValue("Shipped")
    SHIPPED("Shipped"),
    @XmlEnumValue("InTransit")
    IN_TRANSIT("InTransit"),
    @XmlEnumValue("Delivered")
    DELIVERED("Delivered"),
    @XmlEnumValue("Exception")
    EXCEPTION("Exception"),
    @XmlEnumValue("Returned")
    RETURNED("Returned");
    private final String value;

    DtcShipmentStatusEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DtcShipmentStatusEnum fromValue(String v) {
        for (DtcShipmentStatusEnum c: DtcShipmentStatusEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
