package com.example.converter;

import com.example.domain.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);
        assertThat(result).isEqualTo("10");
    }

    @Test
    void stringToIpPort() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        IpPort result = converter.convert("192.168.0.1:7776");
        assertThat(result).isEqualTo(new IpPort("192.168.0.1", 7776));
    }

    @Test
    void IpPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        String result = converter.convert(new IpPort("192.168.0.1", 7776));
        assertThat(result).isEqualTo("192.168.0.1:7776");
    }
}
