
package org.winedirect;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DtcOrderTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DtcOrderTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NotSet"/>
 *     &lt;enumeration value="Club"/>
 *     &lt;enumeration value="Daily"/>
 *     &lt;enumeration value="Special"/>
 *     &lt;enumeration value="Sample"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DtcOrderTypeEnum")
@XmlEnum
public enum DtcOrderTypeEnum {

    @XmlEnumValue("NotSet")
    NOT_SET("NotSet"),
    @XmlEnumValue("Club")
    CLUB("Club"),
    @XmlEnumValue("Daily")
    DAILY("Daily"),
    @XmlEnumValue("Special")
    SPECIAL("Special"),
    @XmlEnumValue("Sample")
    SAMPLE("Sample");
    private final String value;

    DtcOrderTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DtcOrderTypeEnum fromValue(String v) {
        for (DtcOrderTypeEnum c: DtcOrderTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
