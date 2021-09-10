package com.dahelka.gbh.domain;

/**
 * Tipos de formatos de textos a tratar en las páginas.
 * @author dolivo
 */
public enum EFormatType {
    plain {
        @Override
        public String toString() {
            return "plain";
        }
    },
    html {
        @Override
        public String toString() {
            return "html";
        }
    }
}
