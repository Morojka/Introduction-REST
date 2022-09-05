package com.intro.IntroductionREST.enums;

import org.codehaus.jackson.annotate.JsonProperty;

public enum DocumentType {
        @JsonProperty("passport")
        PASSPORT,
        @JsonProperty("internationalPassport")
        INTERNATIONAL_PASSPORT,
        @JsonProperty("driver")
        DRIVER
}